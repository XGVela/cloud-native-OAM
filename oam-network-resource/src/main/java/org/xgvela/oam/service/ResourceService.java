package org.xgvela.oam.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.xgvela.oam.config.SftpConfig;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.utils.FileTreeUtils;
import org.xgvela.oam.utils.SftpUtils;

import javax.annotation.Resource;
import java.util.Date;

import static java.lang.String.format;


@Component
@Slf4j
@AllArgsConstructor
public class ResourceService extends QuartzJobBean {
    @Resource
    private final SftpConfig sftpConfig;
    @Resource
    private final OamVnfMapper vnfMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        downloadResourceFileFromAgent();
    }

    private void downloadResourceFileFromAgent() {
        vnfMapper.selectList(Wrappers.lambdaQuery()).forEach(vnf -> {
            if (OamVnf.vnfManageStatusType.MANAGED.getId().equals(vnf.getVnfManageStatus())) {
                String neType=vnf.getNeType();
                String neId=vnf.getNeId();
                String version = String.valueOf(new Date().getTime());
                String resourceReadPath = format("/root/sftp/read/resource/%s/%s/", neType, neId);
                String sftpAgentReadPath = format("%s/read/resource/%s/%s/", sftpConfig.getSftpAgentPath(), neType, neId);
                log.info("downloadResourceFileFromAgent neType [{}], neId [{}],resourceReadPath {},sftpAgentReadPath {}", neType, neId, resourceReadPath,sftpAgentReadPath);
                SftpUtils sftpAgentClient = new SftpUtils(sftpConfig.getSftpAgentSeverIp(), Integer.parseInt(sftpConfig.getSftpAgentPort()), sftpConfig.getSftpAgentUser(), sftpConfig.getSftpAgentPasswd());
                SftpUtils sftpConfigClient = new SftpUtils(sftpConfig.getSftpConfSeverIp(), Integer.parseInt(sftpConfig.getSftpConfigPort()), sftpConfig.getSftpConfigUser(), sftpConfig.getSftpConfigPasswd());
                FileTreeUtils.collectFileFromSftpToLocalForVersion(resourceReadPath, sftpAgentReadPath, sftpAgentClient, sftpConfigClient, "readwithVersion", version);
            }
        });
    }
}
