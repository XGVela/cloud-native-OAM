package org.xgvela.oam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.xgvela.oam.entity.signaltrace.OamVnfSignaltrace;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.grpc.SignaltraceGrpcClient;
import org.xgvela.oam.mapper.signaltrace.OamVnfSignaltraceMapper;
import org.xgvela.oam.service.IOamVnfSignaltraceService;


@Service
@AllArgsConstructor
@Slf4j
public class OamVnfSignaltraceServiceImpl extends ServiceImpl<OamVnfSignaltraceMapper, OamVnfSignaltrace> implements IOamVnfSignaltraceService {

    @Autowired
    private SignaltraceGrpcClient signaltraceGrpcClient;

    public boolean save(OamVnfSignaltrace entity) {
        checkWhetherExisted(entity);
        if (BooleanUtils.isTrue(entity.getRunNow())) {
            entity.setStatus(OamVnfSignaltrace.statusEnum.DOING.getName());
        } else {
            entity.setStatus(OamVnfSignaltrace.statusEnum.UNDO.getName());
        }
        baseMapper.insert(entity);
        OamVnfSignaltrace source = baseMapper.selectOne(Wrappers.<OamVnfSignaltrace>lambdaQuery().eq(OamVnfSignaltrace::getVnfInstanceId, entity.getVnfInstanceId()).eq(OamVnfSignaltrace::getTaskName, entity.getTaskName()));
        new Thread(new SyncTaskStatusThread(source)).start();
        OamVnfSignaltrace signaltrace = baseMapper.selectOne(Wrappers.<OamVnfSignaltrace>lambdaQuery().eq(OamVnfSignaltrace::getVnfInstanceId, entity.getVnfInstanceId()).eq(OamVnfSignaltrace::getTaskName, entity.getTaskName()));
        signaltraceGrpcClient.getSignaltraceGrpcClient().createNfTask(signaltrace);
        return true;
    }

    public void checkWhetherExisted(OamVnfSignaltrace entity) {
        if (baseMapper.selectCount(Wrappers.<OamVnfSignaltrace>lambdaQuery().eq(OamVnfSignaltrace::getVnfInstanceId, entity.getVnfInstanceId()).eq(OamVnfSignaltrace::getTaskName, entity.getTaskName())) > 0) {
            throw new ServiceException("this neId has the same taskName");
        }
    }

    @Data
    class SyncTaskStatusThread implements Runnable {
        private OamVnfSignaltrace oamVnfSignaltrace;

        public SyncTaskStatusThread(OamVnfSignaltrace entity) {
            this.oamVnfSignaltrace = entity;
        }

        @Override
        public void run() {
            syncTaskStatus(this.oamVnfSignaltrace);
        }

        private void syncTaskStatus(OamVnfSignaltrace oamVnfSignaltrace) {
            if (System.currentTimeMillis() == oamVnfSignaltrace.getStartTime().getTime()) {
                log.info("Timing task start");
                oamVnfSignaltrace.setStatus(OamVnfSignaltrace.statusEnum.DOING.getName());
                baseMapper.updateById(oamVnfSignaltrace);
                log.info("Timing task status is DOING");

            }
        }
    }
}
