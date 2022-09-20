package org.xgvela.oam.service.publish;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.SubscribeInfo;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import org.xgvela.oam.utils.HttpPostClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RegisterSubscribeService {

    @Autowired
    private IOamSubscribeService iOamSubscribeService;
    @Autowired
    private HttpPostClient httpPostClient;

    private static final String RegisterSubscribeTopic = "north_neinfo";
    private static final String RegisterDataType = "register";

    @KafkaListener(topics = {RegisterSubscribeTopic}, groupId = "${oam.subscribe.consumerGroupId}")
    public void handleMessageAndSend(ConsumerRecord record){
        log.info(RegisterSubscribeTopic + " : " + record.value());
        OamVnf oamVnf = JSON.parseObject((String) record.value(), OamVnf.class);
        String neId = oamVnf.getNeId();
        LambdaQueryWrapper<OamSubscribe> oamSubscribeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getNeId, neId);
        oamSubscribeLambdaQueryWrapper.eq(OamSubscribe::getDataType, RegisterDataType);
        List<OamSubscribe> oamSubscribes = iOamSubscribeService.list(oamSubscribeLambdaQueryWrapper);
        if(oamSubscribes != null && oamSubscribes.size() > 0){
            for(OamSubscribe oamSubscribe: oamSubscribes){
                String callBackUrl = oamSubscribe.getCallbackUrl();
                SubscribeInfo subscribeInfo = SubscribeInfo.builder().neId(neId).alarm(null).perf(null).register(null).build();
                httpPostClient.send(callBackUrl, subscribeInfo);
            }
        }
    }

}
