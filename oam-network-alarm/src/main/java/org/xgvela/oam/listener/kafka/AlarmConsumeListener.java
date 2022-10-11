package org.xgvela.oam.listener.kafka;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.config.AlarmConfirmConfig;
import org.xgvela.oam.config.KafkaProducerSingleTon;
import org.xgvela.oam.entity.alarm.OmcVnfAlarm;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.active.AlarmDto;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.listener.process.AlarmSaveService;
import org.xgvela.oam.mapper.subscribe.OamSubscribeMapper;
import org.xgvela.oam.service.RedisCacheServiceImpl;
import org.xgvela.oam.service.alarm.AlarmService;
import org.xgvela.oam.utils.AlarmParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AlarmConsumeListener {
    @Autowired
    private AlarmConfirmConfig alarmConfirmConfig;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private RedisCacheServiceImpl redisCacheService;
    @Autowired
    private AlarmSaveService alarmSaveService;
    @Autowired
    private OamSubscribeMapper oamSubscribeMapper;

    private static final String ALARM_SUB_TOPIC = "alarm_sub_pub";
    
    @Value("${alarm.save.consumer.topic}")
    private String saveTopic;
    
    @Value("${notice.kafka.topic}")
    private String noticeTopic;

    @Value("${cnet.alarm.analysis.kafka.topic}")
    private String analysisTopic;

    @Value("${cnet.alarm.redis.active.alarm.name}")
    private String redisActiveAlarm;

    @Bean
    public ConsumerAwareListenerErrorHandler alarmConsumeListenerErrorHandler() {
        return (message, exception, consumer) -> {
            log.info("-------consume faild --message: " + message.getPayload() + "---exceptionInfo: " + exception.getStackTrace());
            return "stop consume";
        };
    }

    @KafkaListener(id = "alarmConsume", clientIdPrefix = "${alarm.consumer.groupId}", topics = "${alarm.consumer.topic}", containerFactory = "batchContainerFactory",
            groupId = "${alarm.consumer.groupId}", idIsGroup = false, errorHandler = "alarmConsumeListenerErrorHandler")
    public void alarmConsume(List<ConsumerRecord<String, String>> records, Consumer consumer) {
        
        try{
            List<ActiveAlarm> recordsLst = new ArrayList<>();
            Map<String, List<ActiveAlarm>> alarmMap = new HashMap<String, List<ActiveAlarm>>(8);
            
            for (ConsumerRecord<String, String> record : records) {
                try {
                    ActiveAlarm activeAlarm = alarmService.convertToActiveAlarm(JSON.parseObject(record.value()));

                    if(StringUtils.isEmpty(activeAlarm.getGroupId())){
                        activeAlarm.setGroupId("default");
                    }
//                    activeAlarm.setId(record.offset()+1);
                    activeAlarm.setKafkaOffSet(record.offset());
                    if (StringUtils.isNotEmpty(activeAlarm.getAlarmId()) && StringUtils.isNotEmpty(activeAlarm.getSpecificProblemId())) {
                        recordsLst.add(activeAlarm);
                        alarmService.groupingByAlarmId(alarmMap, activeAlarm);
                    } else {
                        log.error("Wrong data format: " + record.value());
                    }
                } catch (Exception e) {
                    log.error("Wrong data format: " + record.value());
                    continue;
                }
            }
            List<String> keySetList = new ArrayList<>();
            keySetList.addAll(alarmMap.keySet());

            List<Boolean> isMemberList = redisCacheService.isMemberList(redisActiveAlarm,keySetList);

            List<String> redisAlarmList = new ArrayList<>();
            List<String> removeRedisAlarmList = new ArrayList<>();
            List<ActiveAlarm> list11 = new ArrayList<ActiveAlarm>();
            for (int i = 0; i < keySetList.size(); i++) {
                boolean flag = isMemberList.get(i);
                List<ActiveAlarm> list = removeCommonAndAssembleInfo(alarmMap.get(keySetList.get(i)), flag);
                if (list.size() > 0 && 1 == list.get(0).getAlarmStatusType()) {
                    removeRedisAlarmList.add(list.get(0).getAlarmId());
                }
                if (list.size() > 0 && 0 == list.get(list.size() - 1).getAlarmStatusType()) {
                    redisAlarmList.add(list.get(list.size() - 1).getAlarmId());
                }
                list11.addAll(list);
            }

            sendAlarmInterResult(list11);

            redisCacheService.putSetKey(redisActiveAlarm, removeRedisAlarmList, redisAlarmList);
            log.info("------------consume offsets: " + records.get(0).offset());
        }catch (Exception e) {
            log.error("alarmConsume fail", e);
        }finally {
            consumer.commitAsync();
        }
    }

    @KafkaListener(id = "alarmConsumeNorth", clientIdPrefix = "Alarm-ConsumerNorth", topics = "${alarm.save.consumer.topic}", containerFactory = "batchContainerFactory",
            groupId = "Alarm-ConsumerNorth", idIsGroup = false, errorHandler = "alarmConsumeListenerErrorHandler")
    public void alarmConsumeToNorthAlarm(List<ConsumerRecord<String, String>> records, Consumer consumer) {
        KafkaProducer<String, String> producer = KafkaProducerSingleTon.getProducer();
        records.forEach(record -> {
            Map mapTypes = JSON.parseObject(record.value());
            AlarmDto alarmDto = AlarmParser.parse(mapTypes);
            alarmDto.setAlarmSeq(record.offset() + 1);
            ProducerRecord<String, String> northRecord = new ProducerRecord<>("AlarmForNorth", JSON.toJSONStringWithDateFormat(alarmDto, "yyyy-MM-dd HH:mm:ss"));
            producer.send(northRecord);
        });
        consumer.commitAsync();
    }


    @KafkaListener(id = "alarmOamSubNorth", clientIdPrefix = "Alarm-OamSubNorth", topics = "${alarm.save.consumer.topic}", containerFactory = "batchContainerFactory",
            groupId = "Alarm-OamSubNorth", idIsGroup = false, errorHandler = "alarmConsumeListenerErrorHandler")
    public void alarmConsumeToOamSubNorthAlarm(List<ConsumerRecord<String, String>> records, Consumer consumer) {
        KafkaProducer<String, String> producer = KafkaProducerSingleTon.getProducer();
        Set<String> neIdSet = oamSubscribeMapper.selectList(new LambdaQueryWrapper<OamSubscribe>().like(OamSubscribe::getDataType, "alarm")).stream().map(OamSubscribe::getNeId).collect(Collectors.toSet());

        records.forEach(record -> {
            ActiveAlarm activeAlarm = JSON.parseObject(record.value(), ActiveAlarm.class);
            if (neIdSet.contains(activeAlarm.getNeId())) {
                activeAlarm.setId(record.offset() + 1);
                ProducerRecord<String, String> northRecord = new ProducerRecord<>(ALARM_SUB_TOPIC, JSON.toJSONStringWithDateFormat(activeAlarm, "yyyy-MM-dd HH:mm:ss"));
                producer.send(northRecord);
            }
        });
        consumer.commitAsync();
    }
    
    private List<ActiveAlarm> removeCommonAndAssembleInfo(List<ActiveAlarm> activeList, Boolean flag) {
        List<ActiveAlarm> list = new ArrayList<>();
        List<ActiveAlarm> actList = new ArrayList<>();
        ActiveAlarm compareAlarm = activeList.get(0);
        int startIndex = 0;
        int compareIndex = 0;
        if (!flag) {
            //redis中不存在alarmStatusType=0的告警
            startIndex = catchActiveListStartIndex(activeList, false);
        } else {
            startIndex = catchActiveListStartIndex(activeList, true);
        }
        if (startIndex < activeList.size()) {
            compareAlarm = activeList.get(startIndex);
            list.add(activeList.get(startIndex));
        }
        compareIndex = startIndex + 1;
        for (int i = compareIndex; i < activeList.size(); i++) {
            if (!compareAlarm.getAlarmStatusType().equals(activeList.get(i).getAlarmStatusType())) {
                list.add(activeList.get(i));
                compareAlarm = activeList.get(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            ActiveAlarm activeAlarm = assembleAlarmWithVnf(list.get(i));
            if (activeAlarm != null) {
                actList.add(activeAlarm);
            }
        }
        return actList;
    }

    private int catchActiveListStartIndex(List<ActiveAlarm> activeList, boolean hasAlarm) {
        int alarmStatusType = 0;
        if (hasAlarm) {
            alarmStatusType = 1;
        }
        int startIndex = activeList.size();
        for (int i = 0; i < activeList.size(); i++) {
            if (alarmStatusType == activeList.get(i).getAlarmStatusType()) {
                startIndex = i;
                break;
            }
        }
        return startIndex;
    }

    private ActiveAlarm assembleAlarmWithVnf(ActiveAlarm active) {
      
        if (alarmConfirmConfig.getAlarmLevel().equals(active.getAlarmLevel()) && alarmConfirmConfig.getAlarmType().equals(active.getAlarmType())
                && alarmConfirmConfig.getAlarmStatusType().equals(active.getAlarmStatusType())) {
            log.info("This alarm is automatically confirmed" + active.getAlarmId());
            active.setAckState(1);
            active.setAlarmConfirmUserid(String.valueOf(-1L));
            active.setAlarmConfirmUsername("System");
            active.setAlarmConfirmTime(alarmService.timeStamp2Date(System.currentTimeMillis()));
        }
        active.setMergeFlag(0);
        Map<String, OmcVnfAlarm> omcVnfMap = redisCacheService.getMapValue("OmcVnf");
        if (Objects.nonNull(omcVnfMap)) {
            active.setNeName(omcVnfMap.get(active.getNeId()) == null ? active.getNeName() : omcVnfMap.get(active.getNeId()).getVnfName());
        }
        active.setAlarmObject(active.getAlarmObject() == null ? active.getNeName() : active.getAlarmObject());
        active.setAlarmAddInfo(active.getAlarmAddInfo() == null ? active.getAlarmDetail() : active.getAlarmAddInfo());
        return active;
    }

    private void sendAlarmInterResult(List<ActiveAlarm> activeList) {
        KafkaProducer<String, String> producer = KafkaProducerSingleTon.getProducer();
        for (ActiveAlarm activeAlarm : activeList) {
            ProducerRecord<String, String> record = new ProducerRecord<>(saveTopic, JSON.toJSONStringWithDateFormat(activeAlarm, "yyyy-MM-dd HH:mm:ss"));
            producer.send(record);
        }
    }

    private void sendAlarmSaveDB(List<ActiveAlarm> activeList) {
        KafkaProducer<String, String> producer = KafkaProducerSingleTon.getProducer();
        for (ActiveAlarm activeAlarm : activeList) {
            ProducerRecord<String, String> record = new ProducerRecord<>(saveTopic, JSON.toJSONStringWithDateFormat(activeAlarm, "yyyy-MM-dd HH:mm:ss"));
            producer.send(record);
        }
    }
}
