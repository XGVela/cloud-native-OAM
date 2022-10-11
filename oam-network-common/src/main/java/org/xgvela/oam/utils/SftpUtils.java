package org.xgvela.oam.utils;

import lombok.extern.slf4j.Slf4j;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.RemoteFile;
import net.schmizz.sshj.sftp.RemoteResourceInfo;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SftpUtils {
    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final SSHClient sshClient;
    private SFTPClient sftpClient;
    private static final String split = "\\/";

    private SftpUtils(String host, String username, String password) {
        this(host, 21, username, password);
    }

    public SftpUtils(String host, int port, String username, String password) {
        this.sshClient = new SSHClient();
        this.sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        this.host = StringUtils.isEmpty(host) ? "localhost" : host;
        this.port = (port <= 0) ? 21 : port;
        this.username = StringUtils.isEmpty(username) ? "anonymous" : username;
        this.password = password;
        try {
            this.sshClient.connect(host, port);
            this.sshClient.authPassword(username, password);
            this.sftpClient = this.sshClient.newSFTPClient();
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }

//    public static void main(String[] args) {
//        SftpUtils sftp = new SftpUtils("111.111.63.240", 30023, "omc1", "123456");
////        SftpUtils sftp = new SftpUtils("10.180.145.78", 22, "root", "Inspur@123");
//        try {
//            log.info("path: {}", sftp.ls("/omc-deploy/sftp"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String str = "/root/omc-deploy/sftp/write/AMF/amfinstanceid230";
//    }


    public static SftpUtils createFtpCli(String host, String username, String password) {
        return new SftpUtils(host, username, password);
    }

    public static SftpUtils createFtpCli(String host, int port, String username, String password) {
        return new SftpUtils(host, port, username, password);
    }

    public void mkdir(String sftpPath) throws IOException {
        if (null == sftpClient.statExistence(sftpPath)) {
            sftpClient.mkdirs(sftpPath);
        }
    }

    public void close() throws IOException {
        sftpClient.close();
        sshClient.disconnect();
    }

    public void del(String sftpPath) throws IOException {
        try {
            try {
                if (null != sftpClient.statExistence(sftpPath)) {
                    List<RemoteResourceInfo> rList = sftpClient.ls(sftpPath);
                    if (!rList.isEmpty()) {
                        for (RemoteResourceInfo remoteResourceInfo : rList) {
                            log.info("remoteResourceInfo-path: {}", remoteResourceInfo.getPath());
                            sftpClient.rm(remoteResourceInfo.getPath());
                        }
                    }
                    log.info("sftpPath: {}", sftpPath);
                    sftpClient.rmdir(sftpPath);
                }
            } finally {
                sftpClient.close();
            }
        } finally {
            sshClient.disconnect();
        }
    }



    public void downloadDir(String sftpPathAndName, String localPath, SftpUtils sftpConfigClient, String version, String method) throws IOException {
        try {
            try {
                localPath = localPath.replace("\\\\", "/");
                List<RemoteResourceInfo> infos = sftpClient.ls(sftpPathAndName);
                for (int i = 0; infos != null && i < infos.size(); i++) {
                    RemoteResourceInfo rri = infos.get(i);
                    if (rri.isDirectory() && !".".equals(rri.getName()) && !"..".equals(rri.getName())) {
                        downloadDir(String.format("%s/%s", sftpPathAndName, rri.getName()), String.format("%s/%s", localPath, rri.getName()), sftpConfigClient, version, method);
                    } else {
                        if (ObjectUtils.isNotEmpty(version)) {
                            log.info("version: {}",version);
                            downloadFileAndMkdir(String.format("%s/%s", sftpPathAndName, rri.getName()), new File(String.format("%s/%s", localPath, version + "_" + rri.getName())), sftpConfigClient);
                        } else {
                            downloadFileAndMkdir(String.format("%s/%s", sftpPathAndName, rri.getName()), new File(String.format("%s/%s", localPath, rri.getName())), sftpConfigClient);
                        }
                    }
                }
            } finally {
            }
        } finally {
        }
    }


    public void downloadFileAndMkdir(String sftpPathAndName, File file, SftpUtils sftpConfigClient) throws IOException {
        if (ObjectUtils.isNotEmpty(sftpConfigClient)) {
            File fileParent = file.getParentFile();
            log.info("fileParent path {}", fileParent.getPath());
            if (!fileParent.exists() && ObjectUtils.isNotEmpty(sftpConfigClient)) {
//                "/root/sftp/read/UPF/upfinstanceid001"
//                "/sftp-agent/sftp/read/UPF/upfinstanceid001/"
                String configPath = fileParent.getPath().replaceAll("/root/sftp/", "/sftp-conf/sftp/");
                log.info("sftp mkdir configPath: {}", configPath);
                sftpConfigClient.mkdir(configPath);
                sftpConfigClient.close();
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    log.error("Exception:: ", e);
                }
            }
        }
        sftpClient.get(sftpPathAndName, new FileSystemFile(file));
    }

    public void downloadFileDir(String host, int port, String userName, String password, String sftpPathAndName, File file) throws IOException {
        SSHClient sshClient = new SSHClient();
        sshClient.addHostKeyVerifier(new PromiscuousVerifier());
        sshClient.connect(host, port);
        try {
            sshClient.authPassword(userName, password);
            SFTPClient sftpClient = sshClient.newSFTPClient();
            try {
                List<RemoteResourceInfo> infos = sftpClient.ls(sftpPathAndName);
                for (RemoteResourceInfo info : infos) {
                    sftpClient.get(String.format("%s/%s", sftpPathAndName, info.getName()), new FileSystemFile(file));
                }

            } finally {
                sftpClient.close();
            }
        } finally {
            sshClient.disconnect();
        }
    }

    public String read(String userName, String password, String sftpPathAndName) throws IOException {
        RemoteFile remoteFile = null;
        StringBuilder str = new StringBuilder();
        try {
            sshClient.authPassword(userName, password);
            SFTPClient sftpClient = sshClient.newSFTPClient();
            try {
                remoteFile = sftpClient.open(sftpPathAndName);
                try (InputStream is = remoteFile.new RemoteFileInputStream();
                     InputStreamReader isr = new InputStreamReader(is);
                     BufferedReader br = new BufferedReader(isr)) {
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        str.append(line).append("\n");
                    }
                }
            } finally {
                if (remoteFile != null) {
                    remoteFile.close();
                }
                sftpClient.close();
            }
        } finally {
            sshClient.disconnect();
        }

        return str.toString();
    }

    public List<RemoteResourceInfo> ls(String sftpPathAndName) throws IOException {
        List<RemoteResourceInfo> remoteFileList;
        try {
            try {
//                remoteFileList = sftpClient.ls(sftpPathAndName);
                sftpClient.rename("/omc-deploy/sftp/ddd", "/omc-deploy/sftp/cc");
            } finally {
            }
        } finally {
        }
        return null;
    }

    public void rm(String path) throws IOException {
        try {
            try {
                Optional.ofNullable(sftpClient.statExistence(path)).ifPresent(item -> {
                    try {
                        sftpClient.rm(path);
                    } catch (Exception e) {
                        log.info("Exception::", e);
                    }
                });
            } finally {
                sftpClient.close();
            }
        } finally {
            sshClient.disconnect();
        }
    }

    public String download(String sftpPath, String fileName, HttpServletResponse response) throws IOException {
        RemoteFile remoteFile = null;
        String str = "";
        try {
            try {
                String sftpPathAndName = sftpPath + fileName;
                remoteFile = sftpClient.open(sftpPathAndName);
                try (InputStream is = remoteFile.new RemoteFileInputStream();
                     InputStreamReader isr = new InputStreamReader(is);
                     BufferedReader br = new BufferedReader(isr);
                     OutputStream os = response.getOutputStream()) {
                    // 清空response
                    response.reset();
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    response.setHeader("Content-Disposition", String.format("attachment; fileName=%s;filename*=utf-8''%s", fileName, URLEncoder.encode(fileName, "UTF-8")));
                    String line;
                    while ((line = br.readLine()) != null) {
                        os.write(line.getBytes(StandardCharsets.UTF_8));
                    }

                    os.flush();
                }
            } finally {
                if (remoteFile != null) {
                    remoteFile.close();
                }
                sftpClient.close();
            }
        } finally {
            sshClient.disconnect();
        }
        return str;
    }

    public void upload(String sftpPath, File file) throws IOException {
        try {
            try {
                if (file.getName().contains("_")) {
                    log.info("contains _");
                    log.info("upload file filename: {}", file.getAbsoluteFile().getName());
                    List<String> fileNames = Arrays.asList(file.getName().split("_"));
                    File fileWithoutTime = new File(fileNames.get(1));
                    FileUtils.copyFile(file, fileWithoutTime);
                    if (null == sftpClient.statExistence(sftpPath)) {
                        log.info("sftpPath exist : {}", fileWithoutTime.getName());
                        sftpClient.mkdirs(sftpPath);
                        sftpClient.put(new FileSystemFile(fileWithoutTime), sftpPath);
                    } else {
                        sftpClient.put(new FileSystemFile(fileWithoutTime), sftpPath);
                    }
                } else {
                    if (null == sftpClient.statExistence(sftpPath)) {
                        log.info("sftpPath exist : {}", file.getName());
                        sftpClient.mkdirs(sftpPath);
                        sftpClient.put(new FileSystemFile(file), sftpPath);
                    } else {
                        sftpClient.put(new FileSystemFile(file), sftpPath);
                    }
                }
            } finally {
                sftpClient.close();
            }
        } finally {
            sshClient.disconnect();
        }
    }
}
