package org.xgvela.oam.utils;

import org.xgvela.oam.entity.conf.FileTree;
import org.xgvela.oam.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.lang.String.format;

@Slf4j
public class FileTreeUtils {
    private FileTreeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void collectFileFromSftpToLocal(String localWorkPath, String sftpPath, SftpUtils sftp) {
        try {
            sftp.downloadDir(sftpPath, localWorkPath, sftp, "", null);
            sftp.close();
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }

    // sftpAgentClient, version,sftpConfigClient
    public static void collectFileFromSftpToLocalForVersion(String localWorkPath, String sftpPath, SftpUtils sftpAgentClient, SftpUtils sftpConfigClient, String method, String version) {
        try {
            if (method.equals("readWithoutVersion")) {
                Optional.ofNullable(isFileExisted(localWorkPath)).ifPresent(item -> {
                    try {
                        FileUtils.forceDelete(item);
                    } catch (Exception e) {
                        log.error("Exception::", e);
                    }
                });
            }
            sftpAgentClient.downloadDir(sftpPath, localWorkPath, sftpConfigClient, version, method);
            sftpAgentClient.close();
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }

    public static File isFileExisted(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static List<FileTree> getLocalDirectory(String localDirectory) {
        List<FileTree> trees = new ArrayList<>();
        int level = 0;
        log.info("localDirectory，{}", localDirectory);
        return getFile(localDirectory, 1, level, trees);
    }

    public static void uploadConfigSftp(String localWorkPath, SftpUtils sftp, FileTree tree, String sftpPath) {
        String path = format("%s/%s", localWorkPath, tree.getName());
        File originFile = new File(path);
        try {
            sftp.upload(sftpPath, originFile);
            sftp.close();
        } catch (Exception e) {
            log.info("Exception::", e);
        }
    }

    public static List<FileTree> getFile(String path, int id, int pid, List<FileTree> trees) {
        File file = new File(path);
        try {
            if (file.exists()) {
                File[] array = file.listFiles();
                int i = 0;
                while (i < Objects.requireNonNull(array).length) {
                    FileTree tree = new FileTree();
                    tree.setId(pid);
                    tree.setId(id);
                    tree.setName(array[i].getName());
                    if (array[i].isDirectory()) {
                        tree.setIsDirectory(true);
                        trees.add(tree);
                        tree.setSize(String.valueOf(FileUtils.sizeOfDirectory(array[i])));
                        getFile(array[i].getPath(), id * 10 + 1 + i, id, trees);
                    } else {
                        tree.setCreatedTime(Objects.requireNonNull(getLastModfiyTime(array[i])));
                        tree.setIsDirectory(false);
                        tree.setSize(String.valueOf(new FileInputStream(array[i]).available()));
                        trees.add(tree);
                    }
                    id++;
                    i++;
                }
            }
        } catch (Exception e) {
            log.error("file available error::", e);
            throw new ServiceException("file available error::", e);
        }
        return trees;
    }

    public static Date getLastModfiyTime(File file) {
        try {
            Path path = Paths.get(file.getCanonicalPath());
            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();
            return new Date(attr.lastModifiedTime().toMillis());
        } catch (Exception e) {
            log.error("file time error::", e);
            return null;
        }
    }

    public static String date2TimeVersion(Object o) throws Exception {
        Date date;
        if (o instanceof Date) {
            date = (Date) o;
        } else {
            date = DateUtils.parseDate((String) o, "yyyy.MM.dd");
        }
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static void fileUploadSftp(String localWorkingDir, SftpUtils sftp, String sftpPath) {
        File originFile = new File(localWorkingDir);
        try {
            sftp.upload(sftpPath, originFile);
            sftp.close();
        } catch (Exception e) {
            log.error("Exception::", e);
        }
    }
}
