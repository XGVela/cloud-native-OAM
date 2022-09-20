package org.xgvela.oam.listener.process;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.history.HistoryAlarm;
import org.xgvela.oam.service.RedisCacheServiceImpl;
import org.xgvela.oam.service.alarm.ActiveAlarmServiceImpl;
import org.xgvela.oam.service.alarm.AlarmService;
import org.xgvela.oam.service.history.HistoryAlarmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AlarmSaveService {
    @Autowired
    private ActiveAlarmServiceImpl activeAlarmService;
    @Autowired
    private HistoryAlarmServiceImpl historyAlarmService;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private RedisCacheServiceImpl redisCacheService;

    public void  saveAlarmToDatabase(List<ActiveAlarm> activeAlarms){

        try{
            List<ActiveAlarm> recordsLst = new ArrayList<>();
            Map<String, List<ActiveAlarm>> alarmMap = new HashMap<String, List<ActiveAlarm>>(8);
            for (ActiveAlarm activeAlarm : activeAlarms) {
                recordsLst.add(activeAlarm);
                alarmService.groupingByAlarmId(alarmMap, activeAlarm);
            }

            List<String> keyList = new ArrayList<>();
            keyList.addAll(alarmMap.keySet());
            List<String> idList = redisCacheService.getIdByCombineKey("ActiveAlarmDB",keyList);

            List<ActiveAlarm> actList = new ArrayList<>();
            List<HistoryAlarm> hisList = new ArrayList<>();
            List<String> removeRedisList = new ArrayList<>();
            List<ActiveAlarm> addRedisList = new ArrayList<>();
            List<Long> removeIds = new ArrayList<Long>();


            Set<String> listSet = new HashSet<String>();

            Set<String> voiceSet = new HashSet<String>();

            for (int i = 0; i < keyList.size(); i++) {
                if (idList.get(i) != null) {

                    if (0 == (1 + alarmMap.get(keyList.get(i)).size()) % 2) {

                        ActiveAlarm zeroAlarm = assembleUpdateAlarmList(idList.get(i), alarmMap.get(keyList.get(i)).get(0));
                        removeIds.add(zeroAlarm.getId());
                        listSet.add(zeroAlarm.getDeveloperId());
                        removeRedisList.add(keyList.get(i));

                        HistoryAlarm historyAlarm = assembleHistoryAlarm(zeroAlarm, alarmMap.get(keyList.get(i)).get(0));
                        hisList.add(historyAlarm);
                        assembleActAndHistoryAlarm(alarmMap.get(keyList.get(i)), actList, hisList, alarmMap.get(keyList.get(i)).size(),
                                false, listSet, voiceSet);
                    } else {
                        ActiveAlarm zeroAlarm = assembleUpdateAlarmList(idList.get(i), alarmMap.get(keyList.get(i)).get(0));
                        removeIds.add(zeroAlarm.getId());
                        listSet.add(zeroAlarm.getDeveloperId());
                        removeRedisList.add(keyList.get(i));

                        HistoryAlarm historyAlarm = assembleHistoryAlarm(zeroAlarm, alarmMap.get(keyList.get(i)).get(0));
                        hisList.add(historyAlarm);
                        assembleActAndHistoryAlarm(alarmMap.get(keyList.get(i)), actList, hisList, alarmMap.get(keyList.get(i)).size() - 1,
                                false, listSet, voiceSet);
                        addRedisList.add(alarmMap.get(keyList.get(i)).get(alarmMap.get(keyList.get(i)).size() - 1));
                    }
                } else {
                    if (alarmMap.get(keyList.get(i)).size() % 2 == 0) {

                        assembleActAndHistoryAlarm(alarmMap.get(keyList.get(i)), actList, hisList, alarmMap.get(keyList.get(i)).size(),
                                true, listSet, voiceSet);
                    } else {
                        assembleActAndHistoryAlarm(alarmMap.get(keyList.get(i)), actList, hisList, alarmMap.get(keyList.get(i)).size() - 1,
                                true, listSet, voiceSet);
                        addRedisList.add(alarmMap.get(keyList.get(i)).get(alarmMap.get(keyList.get(i)).size() - 1));
                    }
                }
            }

            activeAlarmService.saveBatch(actList);
            activeAlarmService.removeByIds(removeIds);
            historyAlarmService.saveBatch(hisList);
            redisCacheService.udpateActiveAlarmDb("ActiveAlarmDB", removeRedisList, addRedisList);

            sendWebsocketMessage(listSet, voiceSet);

        }catch (Exception e) {
            log.error("save alarm fail", e);
        }
    }

    /**
     * sendWebsocketMessage
     * @param listSet
     * @param voiceSet
     */
    private void sendWebsocketMessage(Set<String> listSet, Set<String> voiceSet) {
        if(CollectionUtils.isNotEmpty(listSet)){
            log.info("---------send list Refresh Alarm Message 2 All Server----- --");
            redisCacheService.sendAlarmMessage("channel:alarmMessage", listSet.toString());
        }

        if(CollectionUtils.isNotEmpty(voiceSet)){
            log.info("---------send voice Alarm Message 2 All Server----- --");
            redisCacheService.sendAlarmMessage("channel:alarmVoice", voiceSet.toString());
        }
    }

    private ActiveAlarm assembleUpdateAlarmList(String idAndEventTime, ActiveAlarm nextAlarm) {
        ActiveAlarm zeroAlarm = new ActiveAlarm();
        BeanUtils.copyProperties(nextAlarm, zeroAlarm);
        zeroAlarm.setId(Long.parseLong(idAndEventTime.split("-")[0]));
        zeroAlarm.setMergeFlag(1);
        zeroAlarm.setAlarmStatusType(0);
        zeroAlarm.setAlarmEventTime(alarmService.timeStamp2Date(Long.parseLong(idAndEventTime.split("-")[1])));
        zeroAlarm.setAckState(Integer.parseInt(idAndEventTime.split("-")[2]));
        zeroAlarm.setAlarmClearedTime(null);
        return zeroAlarm;
    }

    private HistoryAlarm assembleHistoryAlarm(ActiveAlarm currentAlarm, ActiveAlarm nextAlarm) {
        HistoryAlarm historyAlarm = new HistoryAlarm();
        BeanUtils.copyProperties(currentAlarm, historyAlarm);
        historyAlarm.setAlarmClearedTime(nextAlarm.getAlarmClearedTime());
        historyAlarm.setAlarmClearedType(1);
        historyAlarm.setAlarmStorageTime(currentAlarm.getAlarmEventTime());
        return historyAlarm;
    }

    private void assembleActAndHistoryAlarm(List<ActiveAlarm> alarmList, List<ActiveAlarm> actList, List<HistoryAlarm> hisList,
                                            int size, boolean isEven, Set<String> listSet, Set<String> voiceSet) {
        for (int i = 0; i < alarmList.size(); i++) {
            if (i < size) {
                alarmList.get(i).setMergeFlag(1);
                if (!isEven) {
                    if (0 != i % 2) {
                        hisList.add(alarmService.assembleHistoryAlarm(alarmList.get(i), alarmList.get(i + 1)));
                    }
                } else {
                    if (0 == i % 2) {
                        hisList.add(alarmService.assembleHistoryAlarm(alarmList.get(i), alarmList.get(i + 1)));
                    }
                }
            } else {
                actList.add(alarmList.get(i));
                voiceSet.add(alarmList.get(i).getDeveloperId());
                listSet.add(alarmList.get(i).getDeveloperId());
            }
        }
    }


}
