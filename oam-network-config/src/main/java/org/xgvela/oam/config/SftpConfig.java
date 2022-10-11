package org.xgvela.oam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
public class SftpConfig {
    //conf
    @Value("${sftp.oam.conf.ip}")
    private String sftpConfSeverIp;

    @Value("${sftp.oam.conf.port}")
    private String sftpConfigPort;

    @Value("${sftp.oam.conf.user}")
    private String sftpConfigUser;

    @Value("${sftp.oam.conf.passwd}")
    private String sftpConfigPasswd;

    @Value("${sftp.oam.conf.path}")
    private String sftpConfigPath;

    //agent
    @Value("${sftp.oam.agent.ip}")
    private String sftpAgentSeverIp;

    @Value("${sftp.oam.agent.port}")
    private String sftpAgentPort;

    @Value("${sftp.oam.agent.user}")
    private String sftpAgentUser;

    @Value("${sftp.oam.agent.passwd}")
    private String sftpAgentPasswd;

    @Value("${sftp.oam.agent.path}")
    private String sftpAgentPath;
}