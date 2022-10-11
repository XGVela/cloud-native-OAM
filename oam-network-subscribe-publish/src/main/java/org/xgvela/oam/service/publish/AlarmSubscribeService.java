package org.xgvela.oam.service.publish;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.SubscribeInfo;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import org.xgvela.oam.utils.HttpPostClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class AlarmSubscribeService {

    @Autowired
    private IOamSubscribeService iOamSubscribeService;
    @Autowired
    private HttpPostClient httpPostClient;

    private static final String AlarmSubscribeTopic = "alarm_sub_pub";
    private static final String AlarmDataType = "alarm";

    @KafkaListener(topics = {AlarmSubscribeTopic}, groupId = "${oam.subscribe.consumerGroupId}")
    public void handleMessageAndSend(ConsumerRecord record){
        ActiveAlarm activeAlarm = JSON.parseObject((String) record.value(), ActiveAlarm.class);
        log.info(AlarmSubscribeTopic + " : " + activeAlarm);
        String neId = activeAlarm.getNeId();
        LambdaQueryWrapper<OamSubscribe> oamSubscribeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getNeId, neId);
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getDataType, AlarmDataType);
        OamSubscribe oamSubscribe = iOamSubscribeService.getOne(oamSubscribeLambdaQueryWrapper);
        if(oamSubscribe != null){
            String callBackUrl = oamSubscribe.getCallbackUrl();
            log.info(AlarmSubscribeTopic + " CallBackUrl: " + callBackUrl);
            List<ActiveAlarm> activeAlarmList = new ArrayList<>();
            activeAlarmList.add(activeAlarm);
            SubscribeInfo subscribeInfo = SubscribeInfo.builder().neId(neId).alarm(activeAlarmList).perf(null).register(null).build();
            String subscribeSendInfo = JSON.toJSONString(subscribeInfo);
            log.info(AlarmSubscribeTopic + " SubscribeInfo: " + subscribeSendInfo);
            httpPostClient.send(callBackUrl, subscribeSendInfo);
        }
    }

}
