package org.xgvela.oam.service.publish;

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
        log.info(PerfSubscribeTopic + " : " + record.value());
        String neId = "";
        LambdaQueryWrapper<OamSubscribe> oamSubscribeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getNeId, neId);
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getDataType, PerfDataType);
        OamSubscribe oamSubscribe = iOamSubscribeService.getOne(oamSubscribeLambdaQueryWrapper);
        String callBackUrl = oamSubscribe.getCallbackUrl();
        SubscribeInfo subscribeInfo = SubscribeInfo.builder().neId(neId).alarm(null).perf(null).register(null).build();
        httpPostClient.send(callBackUrl, subscribeInfo);

    }

}
