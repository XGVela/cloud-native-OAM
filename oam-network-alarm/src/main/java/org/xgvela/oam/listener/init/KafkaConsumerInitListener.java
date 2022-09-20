package org.xgvela.oam.listener.init;

import org.xgvela.oam.service.TimingKafkaListenerHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class KafkaConsumerInitListener implements CommandLineRunner {
    @Autowired
    private TimingKafkaListenerHandler timingKafkaListenerHandler;
    
    @Override
    public void run(String... args) throws Exception {

        timingKafkaListenerHandler.addAboutAlarmDataToRedis();
        timingKafkaListenerHandler.startListener("alarmConsume");
        timingKafkaListenerHandler.startListener("save-alarm");

        timingKafkaListenerHandler.startListener("alarmOamSubNorth");
    }
    
}
