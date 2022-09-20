package org.xgvela.oam.utils;

import lombok.extern.slf4j.Slf4j;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.RemoteFile;
import net.schmizz.sshj.sftp.RemoteResourceInfo;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SftpUtils {
    private final String host;
    private final int port ;
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

    /**
     * Create a default ftp client
     *
     * @param host     host or ip
     * @param username ftp username
     * @param password ftp password
     * @return com.siniswift.efb.acars.utils.SftpUtils
     */
    public static SftpUtils createFtpCli(String host, String username, String password) {
        return new SftpUtils(host, username, password);
    }

    /**
     * Create an ftp client for custom properties
     *
     * @param host     host or ip
     * @param port     ftp port
     * @param username ftp username
     * @param password ftp password
     * @return com.siniswift.efb.acars.utils.SftpUtils
     */
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

    /**
     * @param sftpPathAndName  e.g. upload/test.txt
     * @param file             e.g. local/test2.txt
     * @throws IOException     Exception handling: Close the connection
     */
    public void download(String sftpPathAndName, File file) throws IOException {
        sftpClient.get(sftpPathAndName, new FileSystemFile(file));
    }

    /**
     * Download the contents of the folder to a local folder
     *
     * @param sftpPathAndName  e.g. upload/data-20210608164833
     * @param file             e.g. local/data
     * @throws IOException Exception handling: Close the connection
     */
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


    /**
     * Download the sftp server under folder to local
     *
     * @param sftpPathAndName 
     * @param localPath       
     */
    public void downloadDir(String sftpPathAndName, String localPath, String netype, String vnfinstanceid) throws IOException {
        try {
            try {
                localPath = localPath.replace("\\\\", "/");
                File file = new File(localPath);
                if (!file.exists()) {
                    FileUtils.forceMkdir(file);
                }
                List<RemoteResourceInfo> infos = sftpClient.ls(sftpPathAndName);
                for (int i = 0; infos != null && i < infos.size(); i++) {
                    RemoteResourceInfo rri = infos.get(i);
                    if (rri.isDirectory() && !".".equals(rri.getName()) && !"..".equals(rri.getName())) {
                        downloadDir(String.format("%s/%s", sftpPathAndName, rri.getName()), String.format("%s/%s", localPath, rri.getName()), netype, vnfinstanceid);
                    } else {
                        download(String.format("%s/%s", sftpPathAndName, rri.getName()), new File(String.format("%s/%s", localPath, rri.getName())));
                    }
                }
            } finally {
//                sftpClient.close();
            }
        } finally {
//            sshClient.disconnect();
        }
    }

    public void existState(String path) {
        File folder = new File(path);
        log.info("folder.1 {}", folder);
        if (folder.exists()) {
            try {
                log.info("folder.2 {}", folder);
                FileUtils.forceDelete(folder);
            } catch (Exception e) {
                log.error("existState ::", e);
            }
        }
    }

    /**
     * Gets the remote file
     *
     * @param sftpPathAndName  e.g. upload/test.txt
     * @throws IOException     Exception handling: Close the connection
     */
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

    /**
     * Gets the remote file
     *
     * @param sftpPathAndName  e.g. upload/test.txt
     * @throws IOException     Exception handling: Close the connection
     */
    public List<RemoteResourceInfo> ls(String sftpPathAndName) throws IOException {
        List<RemoteResourceInfo> remoteFileList;
        try {
            try {
//                remoteFileList = sftpClient.ls(sftpPathAndName);
                sftpClient.rename("/omc-deploy/sftp/ddd", "/omc-deploy/sftp/cc");
            } finally {
//                sftpClient.close();
            }
        } finally {
//            sshClient.disconnect();
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

    /**
     * Gets the remote file
     *
     * @param sftpPath     e.g. upload/test.txt
     * @throws IOException Exception handling: Close the connection
     */
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

    /**
     * @param sftpPath  e.g. upload/
     *                  e.g. local/test2.txt
     * @throws IOException Exception handling: Close the connection
     */
    public void upload(String sftpPath, File file) throws IOException {
        try {
            try {
                if (null == sftpClient.statExistence(sftpPath)) {
                    log.info("sftpPath exist : {}", sftpPath);
                    sftpClient.mkdirs(sftpPath);
                    sftpClient.put(new FileSystemFile(file), sftpPath);
                } else {
                    sftpClient.put(new FileSystemFile(file), sftpPath);
                }
            } finally {
                sftpClient.close();
            }
        } finally {
            sshClient.disconnect();
        }
    }

}
