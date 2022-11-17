package org.xgvela.oam.service.alarm;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.alarm.AlarmReportServiceGrpc;
import org.xgvela.oam.alarm.AlarmRsp;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.mapper.nftube.OamVnfMapper;
import org.xgvela.oam.service.kafkaclient.KafkaProducerService;
import org.xgvela.oam.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@GrpcService
@AllArgsConstructor
@Slf4j
public class AlarmService extends AlarmReportServiceGrpc.AlarmReportServiceImplBase {

    private static final String AlarmTopicName = "south_alarm";

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private OamVnfMapper oamVnfMapper;

    @Override
    public void alarmReport(org.xgvela.oam.alarm.AlarmReq request, io.grpc.stub.StreamObserver<AlarmRsp> responseObserver) {
        log.info("Agent AlarmReportService : {}", request);
        AlarmRsp alarmRsp = AlarmRsp.newBuilder().setResult("0").build();
        ActiveAlarm activeAlarm = new ActiveAlarm();
        activeAlarm.setAlarmId(request.getAlarmId());
        activeAlarm.setSpecificProblemId(request.getSpecificProblemId());
        activeAlarm.setAlarmName(request.getAlarmName());
        activeAlarm.setAlarmType(request.getAlarmType());
        activeAlarm.setAlarmLevel(request.getAlarmLevel());
        activeAlarm.setAlarmPvFlag(1);
        activeAlarm.setSpecificProblem(request.getSpecificProblem());
        activeAlarm.setAlarmStatusType(request.getAlarmStatusType());
        activeAlarm.setAlarmObject(request.getAlarmObject());
        activeAlarm.setAlarmObjectName(request.getAlarmObjectName());
        activeAlarm.setNeId(request.getNeId());
        activeAlarm.setRmUid(request.getRmUid());
        activeAlarm.setNeName(request.getNeName());
        activeAlarm.setNeType(request.getNeType());
        activeAlarm.setAlarmDeviceType(request.getAlarmDeviceType());
        activeAlarm.setAlarmLocationInfo(request.getAlarmLocationInfo());
        activeAlarm.setAlarmEventTime(request.getAlarmStatusType() == 0 ? new Date(request.getAlarmCurrentTime()) : null);
        activeAlarm.setAlarmClearedTime(request.getAlarmStatusType() == 1 ? new Date(request.getAlarmCurrentTime()) : null);
        activeAlarm.setAlarmClearedType(request.getAlarmStatusType() == 1 ? 1 : null);
        activeAlarm.setSyncType(0);
        activeAlarm.setOffLine(0);
        activeAlarm.setSource(request.getSource());
        Set<String> vnfInstanceIdsSet = oamVnfMapper.selectList(new LambdaQueryWrapper<OamVnf>().eq(OamVnf::getVnfManageStatus, "1")).stream().map(OamVnf::getNeId).collect(Collectors.toSet());
        if (vnfInstanceIdsSet.contains(activeAlarm.getNeId())) {
            log.info("agent push alarm message: " + JsonUtils.o2js(activeAlarm));
            Boolean sendResult = kafkaProducerService.send(AlarmTopicName,  JSON.toJSONStringWithDateFormat(activeAlarm, "yyyy-MM-dd HH:mm:ss"));
        }
        responseObserver.onNext(alarmRsp);
        responseObserver.onCompleted();
    }
}
