package org.xgvela.oam.service.publish;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.SubscribeInfo;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import org.xgvela.oam.utils.HttpPostClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//The delivery in September does not include performance subscription push, and does not adapt to performance push events
@Component
@Slf4j
public class PerformanceSubscribeService {

    @Autowired
    private IOamSubscribeService iOamSubscribeService;
    @Autowired
    private HttpPostClient httpPostClient;

    private static final String PerfSubscribeTopic = "perf_sub_pub";
    private static final String PerfDataType = "perf";

    @KafkaListener(topics = {PerfSubscribeTopic}, groupId = "${oam.subscribe.consumerGroupId}")
    public void handleMessageAndSend(ConsumerRecord record){
        Map<String,String> map = JSON.parseObject(record.value().toString(), HashMap.class);
        String neId = map.get("neId");
        LambdaQueryWrapper<OamSubscribe> oamSubscribeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getNeId, neId);
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getDataType, PerfDataType);
        OamSubscribe oamSubscribe = iOamSubscribeService.getOne(oamSubscribeLambdaQueryWrapper);
        if(oamSubscribe != null){
            log.info(PerfSubscribeTopic + " : " + record.value());
            String callBackUrl = oamSubscribe.getCallbackUrl();
            log.info(PerfSubscribeTopic + " CallBackUrl: " + callBackUrl);
            List<String> perfList = new ArrayList<>();
            perfList.add(record.value().toString());
            SubscribeInfo subscribeInfo = SubscribeInfo.builder().neId(neId).alarm(null).perf(perfList).register(null).build();
            String subscribeSendInfo = JSON.toJSONString(subscribeInfo);
            log.info(PerfSubscribeTopic + " SubscribeInfo: " + subscribeSendInfo);
            httpPostClient.send(callBackUrl, subscribeInfo);
        }
    }

}
