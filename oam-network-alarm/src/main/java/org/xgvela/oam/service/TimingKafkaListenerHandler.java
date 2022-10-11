package org.xgvela.oam.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.service.alarm.ActiveAlarmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TimingKafkaListenerHandler {

    @Autowired
    private KafkaListenerEndpointRegistry registry;
    @Autowired
    private ActiveAlarmServiceImpl activeAlarmService;
    @Autowired
    private RedisCacheServiceImpl<String> redisCacheService;

    @Value("${cnet.alarm.redis.active.alarm.name}")
    private String redisActiveAlarm;

    @Value("${cnet.alarm.save.redis.active.alarm.name}")
    private String redisSaveActiveAlarm;

    public void startListener(String listenerId) {
        log.info("------------------------------------start---listenId: " + listenerId);
        if (!registry.getListenerContainer(listenerId).isRunning()) {
            registry.getListenerContainer(listenerId).start();
        }
        //项目启动的时候监听容器是未启动状态，而resume是恢复的意思不是启动的意思
        registry.getListenerContainer(listenerId).resume();
        log.info("------------------------------------end---listenerId: " + listenerId);
    }


    public void addAboutAlarmDataToRedis() {
        redisCacheService.remObj(redisSaveActiveAlarm);
        List<ActiveAlarm> actList = activeAlarmService.list(new QueryWrapper<ActiveAlarm>().eq("alarm_status_type", 0).eq("merge_flag", 0));
        Map<String, String> actDbMap = actList.stream().
                collect(Collectors.toMap(ActiveAlarm::getAlarmId,
                        part -> (part.getId() + "-" + part.getAlarmEventTime().getTime() + "-" + part.getAckState()),
                        (entity1, entity2) -> entity1 ));
        redisCacheService.setKey(redisSaveActiveAlarm, actDbMap);

        redisCacheService.remStringSet(redisActiveAlarm);
        Set<String> actSet = actList.stream().map(ActiveAlarm::getAlarmId).collect(Collectors.toSet());
        redisCacheService.setSetKey(redisActiveAlarm, actSet);
    }

}
