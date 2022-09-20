package org.xgvela.oam.service.alarm;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.inspur.cnet.common.core.utils.DateUtil;
import com.inspur.cnet.common.core.utils.StringUtils;
import com.inspur.cnet.security.entity.Developer;
import com.inspur.cnet.security.service.DeveloperServiceImpl;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.active.AlarmKnowledge;
import org.xgvela.oam.entity.alarm.history.HistoryAlarm;
import org.xgvela.oam.entity.alarm.sys.Dict;
import org.xgvela.oam.service.RedisCacheServiceImpl;
import org.xgvela.oam.service.history.HistoryAlarmServiceImpl;
import org.xgvela.oam.utils.JsonUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class AlarmService {

    private static final String ALARM_CLEARED_TIME = "alarmClearedTime";
    private static final String ALARM_EVENT_TIME = "alarmEventTime";
    private static final String ACK_STATE = "ackState";
    private static final String ALARM_STORAGE_TIME = "alarmStorageTime";

    private static final String TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FORMATTER_PATTERN);
    @Autowired
    private ActiveAlarmServiceImpl activeAlarmService;
    @Autowired
    private HistoryAlarmServiceImpl historyAlarmService;
    @Autowired
    private AlarmKnowledgeServiceImpl alarmKnowledgeService;
    @Autowired
    private DeveloperServiceImpl developerService;
    @Autowired
    private RedisCacheServiceImpl redisCacheService;

    @Value("${cnet.alarm.redis.active.alarm.name:CnetActiveAlarmLocal}")
    private String redisActiveAlarm;
    @Value("${cnet.alarm.save.redis.active.alarm.name:CnetActiveAlarmDBLocal}")
    private String redisSaveActiveAlarmDB;

    public ActiveAlarm consumleActiveAlarm(String recordValue) {
        JSONObject jsonObj = JSONObject.parseObject(recordValue);
        Field[] fields = ActiveAlarm.class.getDeclaredFields();
        ActiveAlarm activeAlarm = new ActiveAlarm();
        for (int i = 0; i < fields.length; i++) {
            if (!Modifier.isStatic(fields[i].getModifiers())) {
                if (jsonObj.get(fields[i].getName()) != null) {
                    fields[i].setAccessible(true);
                    try {
                        if ("class java.lang.Integer".equals(fields[i].getGenericType().toString()) && "java.lang.Integer".equals(jsonObj.get(fields[i].getName()).getClass().getTypeName())) {
                            fields[i].set(activeAlarm, jsonObj.get(fields[i].getName()));
                        } else if ("class java.lang.String".equals(fields[i].getGenericType().toString()) && "java.lang.String".equals(jsonObj.get(fields[i].getName()).getClass().getTypeName())) {
                            fields[i].set(activeAlarm, jsonObj.get(fields[i].getName()) != null ? jsonObj.get(fields[i].getName()).toString() : null);
                        } else if ("class java.lang.String".equals(fields[i].getGenericType().toString()) && "com.alibaba.fastjson.JSONObject".equals(jsonObj.get(fields[i].getName()).getClass().getTypeName())) {
                            fields[i].set(activeAlarm, jsonObj.get(fields[i].getName()) != null ? jsonObj.get(fields[i].getName()).toString() : null);
                        } else if ("class java.util.Date".equals(fields[i].getGenericType().toString()) && "java.lang.String".equals(jsonObj.get(fields[i].getName()).getClass().getTypeName())) {
                            String timeStr = jsonObj.get(fields[i].getName()) != null ? jsonObj.get(fields[i].getName()).toString() : null;
                            if ("".equals(timeStr)) {
                                fields[i].set(activeAlarm, null);
                            } else {
                                Date time = DateUtil.parse(timeStr, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
                                fields[i].set(activeAlarm, time);
                            }
                        }
                    } catch (IllegalArgumentException e) {
                    } catch (IllegalAccessException e) {
                    } catch (ParseException e) {
                    }
                }
            }
        }
        return activeAlarm;
    }

    public ActiveAlarm convertToActiveAlarm(JSONObject recordJson) {
        ActiveAlarm activeAlarm = new ActiveAlarm();
        activeAlarm.setAckState(recordJson.getInteger("ackState"));
        activeAlarm.setAlarmAddInfo(recordJson.getString("alarmAddInfo"));
        if (null != recordJson.getDate(ALARM_CLEARED_TIME)) {
            activeAlarm.setAlarmClearedTime(string2Date(LocalDateTime.ofEpochSecond(recordJson.getDate("alarmClearedTime").getTime() / 1000, 0, ZoneOffset.ofHours(8)).format(dateTimeFormatter)));
        }
        activeAlarm.setAlarmClearedType(recordJson.getInteger("alarmClearedType"));
        activeAlarm.setAlarmConfirmTime(recordJson.getDate("alarmConfirmTime"));
        activeAlarm.setAlarmConfirmUserid(recordJson.getString("alarmConfirmUserid"));
        activeAlarm.setAlarmConfirmUsername(recordJson.getString("alarmConfirmUsername"));
        activeAlarm.setAlarmDetail(recordJson.getString("alarmDetail"));
        activeAlarm.setAlarmDeviceType(recordJson.getString("alarmDeviceType"));
        if (null != recordJson.getDate(ALARM_EVENT_TIME)) {
            activeAlarm.setAlarmEventTime(string2Date(LocalDateTime.ofEpochSecond(recordJson.getDate("alarmEventTime").getTime() / 1000, 0, ZoneOffset.ofHours(8)).format(dateTimeFormatter)));
        }
        activeAlarm.setAlarmEventTime(recordJson.getDate("alarmEventTime"));
        activeAlarm.setAlarmEventTitle(recordJson.getString("alarmEventTitle"));
        activeAlarm.setAlarmId(recordJson.getString("alarmId"));
        activeAlarm.setAlarmLevel(recordJson.getInteger("alarmLevel"));
        activeAlarm.setAlarmLocationInfo(recordJson.getString("alarmLocationInfo"));
        activeAlarm.setAlarmName(recordJson.getString("alarmName"));
        activeAlarm.setAlarmObjectType(Optional.ofNullable(recordJson.getString("alarmObjectType")).orElse("ManageElement"));
        activeAlarm.setAlarmStatusType(recordJson.getInteger("alarmStatusType"));
        activeAlarm.setAlarmType(recordJson.getInteger("alarmType"));
        activeAlarm.setMergeFlag(recordJson.getInteger("mergeFlag"));
        activeAlarm.setAlarmObjectUid(recordJson.getString("alarmObjectUid"));
        activeAlarm.setAlarmObjectName(recordJson.getString("alarmObjectName"));
        activeAlarm.setSpecificProblem(recordJson.getString("specificProblem"));
        activeAlarm.setSpecificProblemId(recordJson.getString("specificProblemId"));
        activeAlarm.setNeId(recordJson.getString("neId"));
        activeAlarm.setNeName(recordJson.getString("neName"));
        activeAlarm.setNeType(recordJson.getString("neType"));
        activeAlarm.setOffLine(Optional.ofNullable(recordJson.getInteger("offLine")).orElse(0));
        activeAlarm.setSource(Optional.ofNullable(recordJson.getString("source")).orElse("5GC"));
        activeAlarm.setAlarmPvFlag(Optional.ofNullable(recordJson.getInteger("alarmPvFlag")).orElse(0));
        if (null == recordJson.getInteger(ACK_STATE)) {
            activeAlarm.setAckState(0);
        } else {
            activeAlarm.setAckState(recordJson.getInteger("ackState"));
        }
        if (null != recordJson.getDate(ALARM_STORAGE_TIME)) {
            activeAlarm.setAlarmStorageTime(string2Date(LocalDateTime.ofEpochSecond(recordJson.getDate("alarmStorageTime").getTime() / 1000, 0, ZoneOffset.ofHours(8)).format(dateTimeFormatter)));
        }
        activeAlarm.setDeveloperId(Optional.ofNullable(recordJson.getString("developerId")).orElse(null));
        activeAlarm.setAlarmNO(AlarmNoCreator.createAlarmNo(recordJson));
        return activeAlarm;
    }

    public HistoryAlarm assembleHistoryAlarm(ActiveAlarm currentAlarm, ActiveAlarm nextAlarm) {
        HistoryAlarm historyAlarm = new HistoryAlarm();
        BeanUtils.copyProperties(currentAlarm, historyAlarm);
        historyAlarm.setAlarmClearedTime(nextAlarm.getAlarmClearedTime());
        historyAlarm.setAlarmClearedType(1);
        return historyAlarm;
    }

    public Date timeStamp2Date(long timeLong) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMATTER_PATTERN);
            Date date = sdf.parse(sdf.format(timeLong));
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date string2Date(String dateStr) {
        if (!Objects.nonNull(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * groupingByAlarmId
     *
     * @param alarmMap
     * @param activeAlarm
     */
    public void groupingByAlarmId(Map<String, List<ActiveAlarm>> alarmMap, ActiveAlarm activeAlarm) {

        String alarmId = activeAlarm.getAlarmId();
        if (alarmMap.containsKey(alarmId)) {
            List<ActiveAlarm> alarmList = alarmMap.get(alarmId);
            alarmList.add(activeAlarm);
            alarmMap.put(alarmId, alarmList);
        } else {
            List<ActiveAlarm> alarmList = new ArrayList<ActiveAlarm>();
            alarmList.add(activeAlarm);
            alarmMap.put(alarmId, alarmList);
        }
    }

    public Boolean createRestActiveAlarm(Object object) {
        List<ActiveAlarm> activeAlarmList = convertAlarms(object);
        if (CollectionUtils.isNotEmpty(activeAlarmList)) {
            Developer developer = developerService.getOne(Wrappers.<Developer>lambdaQuery().eq(Developer::getName, "admin"));
            for (ActiveAlarm activeAlarm : activeAlarmList) {
                if(developer != null){
                    activeAlarm.setDeveloperId(developer.getId());
                }
                if (activeAlarm.getAlarmStatusType() == 1) {
                    updAlarmCurrent(activeAlarm);
                } else {
                    delAlarmCurrent(activeAlarm);
                }
            }
        }
        return true;
    }

    private void updAlarmCurrent(ActiveAlarm alarm) {
        int count = activeAlarmService.count(Wrappers.<ActiveAlarm>query()
                .eq("alarm_object_type", alarm.getAlarmObjectType())
                .eq("alarm_object_uid", alarm.getAlarmObjectUid())
                .eq("alarm_type", alarm.getAlarmType())
                .eq(StringUtils.isNotBlank(alarm.getDeveloperId()),"developer_id", alarm.getDeveloperId()));
        if (count > 0) {
            alarm.setAlarmClearedType(0);
            alarm.setAlarmStorageTime(timeStamp2Date(System.currentTimeMillis()));
            activeAlarmService.update(alarm, Wrappers.<ActiveAlarm>query()
                    .eq("alarm_object_type", alarm.getAlarmObjectType())
                    .eq("alarm_object_uid", alarm.getAlarmObjectUid())
                    .eq("alarm_type", alarm.getAlarmType()));

        } else {
            alarm.setAlarmClearedType(0);
            alarm.setAckState(0);
            alarm.setAlarmStorageTime(timeStamp2Date(System.currentTimeMillis()));
            activeAlarmService.save(alarm);

        }
    }

    private void delAlarmCurrent(ActiveAlarm alarm) {
        if (Objects.nonNull(alarm)) {
            List<HistoryAlarm> historyAlarmList = new ArrayList<>();
            List<ActiveAlarm> activeAlarmList = activeAlarmService.list(Wrappers.<ActiveAlarm>query()
                    .eq("alarm_object_type", alarm.getAlarmObjectType())
                    .eq("alarm_object_uid", alarm.getAlarmObjectUid())
                    .eq("alarm_type", alarm.getAlarmType())
                    .eq(StringUtils.isNotBlank(alarm.getDeveloperId()),"developer_id", alarm.getDeveloperId()));
            if(CollectionUtils.isNotEmpty(activeAlarmList)){
                activeAlarmList.forEach(activeAlarm -> {
                    HistoryAlarm historyAlarm = new HistoryAlarm();
                    BeanUtils.copyProperties(activeAlarm, historyAlarm);
                    historyAlarm.setId(null);
                    historyAlarm.setAlarmClearedTime(timeStamp2Date(System.currentTimeMillis()));
                    historyAlarm.setAlarmClearedType(1);
                    historyAlarmList.add(historyAlarm);
                });
            }else {
                HistoryAlarm historyAlarm = new HistoryAlarm();
                BeanUtils.copyProperties(alarm, historyAlarm);
                historyAlarm.setId(null);
                historyAlarm.setAckState(0);
                historyAlarm.setAlarmClearedTime(timeStamp2Date(System.currentTimeMillis()));
                historyAlarm.setAlarmStorageTime(timeStamp2Date(System.currentTimeMillis()));
                historyAlarm.setAlarmClearedType(1);
                historyAlarmList.add(historyAlarm);
            }
            activeAlarmService.remove(Wrappers.<ActiveAlarm>query()
                    .eq("alarm_object_type", alarm.getAlarmObjectType())
                    .eq("alarm_object_uid", alarm.getAlarmObjectUid())
                    .eq("alarm_type", alarm.getAlarmType())
                    .eq(StringUtils.isNotBlank(alarm.getDeveloperId()),"developer_id", alarm.getDeveloperId()));
            historyAlarmService.saveBatch(historyAlarmList);
        }
    }

    private List<ActiveAlarm> convertAlarms(Object object) {

        List<ActiveAlarm> activeAlarms = new ArrayList<>();
        ArrayNode arrayNode = (ArrayNode) JsonUtils.o2jn(object).get("alerts");
        for (JsonNode jsonNode : arrayNode) {
            JsonNode annotations = jsonNode.get("annotations");
            JsonNode labels = jsonNode.get("labels");
            if (Objects.isNull(labels.get("resourceType"))) {
                continue;
            }
            if ((StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "host"))
                    || (StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "vm"))
                    || (StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "openstack"))
                    || (StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "mysql"))
                    || (StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "rabbitmq"))
                    || (StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "ceph"))
                    || (StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "prometheus"))
                    || (StringUtils.equalsIgnoreCase(labels.get("resourceType").asText(), "alertmanager"))) {
                ActiveAlarm activeAlarm = new ActiveAlarm();
                activeAlarm.setAlarmId(labels.get("alarm_id").asText());
                activeAlarm.setAlarmNO(jsonNode.get("fingerprint").asText());
                activeAlarm.setAlarmName(Optional.ofNullable(labels.get("alertname")).map(value -> value.asText()).orElse(null));
                activeAlarm.setAlarmEventTitle(Optional.ofNullable(labels.get("alertname")).map(value -> value.asText()).orElse(null));
                activeAlarm.setAlarmStatusType(Optional.ofNullable(MapUtils.putAll(new HashMap<String, Integer>(), new Object[]{"firing", 1, "resolved", 0}).get(jsonNode.get("status").asText())).orElse(null));
                //activeAlarm.setAlarmType(alarmMappingValues("AlarmType", Optional.ofNullable(labels.get("entity_type_id")).map(value -> value.asText()).orElse(null)));
                activeAlarm.setAlarmLevel(Optional.ofNullable(MapUtils.putAll(new HashMap<String, Integer>(), new Object[]{"critical", 1, "major", 2, "minor", 3, "warning", 4}).get(labels.get("severity").asText())).orElse(null));

                activeAlarm.setAlarmEventTime(stringT2Date(Optional.ofNullable(jsonNode.get("startsAt")).map(value -> value.asText()).orElse(null)));
                activeAlarm.setSpecificProblemId(labels.get("alarm_id").asText());
                activeAlarm.setSpecificProblem(Optional.ofNullable(annotations.get("description")).map(value -> value.asText()).orElse(null));
                activeAlarm.setAlarmLocationInfo(Optional.ofNullable("Namespace:" + labels.get("namespace").asText() + "; Service:" + labels.get("service").asText()).orElse(null));
                activeAlarm.setAlarmAddInfo(buildAlarmAddInfo(labels));
                activeAlarm.setAlarmDetail(Optional.ofNullable(annotations.get("description")).map(value -> value.asText()).orElse(null));
                if (Objects.nonNull(labels.get("cluster_id"))) {
                    activeAlarm.setNeId(Optional.ofNullable(labels.get("cluster_id")).map(JsonNode::asText).orElse(null));
                } else {
                    activeAlarm.setNeId(Optional.ofNullable(labels.get("ne_id")).map(JsonNode::asText).orElse(null));
                }
                if (Objects.nonNull(labels.get("cluster_name"))) {
                    activeAlarm.setNeName(Optional.ofNullable(labels.get("cluster_name")).map(JsonNode::asText).orElse(null));
                } else {
                    activeAlarm.setNeName(Optional.ofNullable(labels.get("ne_name")).map(JsonNode::asText).orElse(null));
                }
//				activeAlarm.setNeId(Optional.ofNullable(labels.get("entity_instance_id")).map(value -> value.asText()).orElse(null));
//				if(Objects.nonNull(labels.get("instanceName"))){
//					activeAlarm.setNeName(Optional.ofNullable(labels.get("instanceName").asText()).orElse(null));
//				}else {
//					activeAlarm.setNeName(Optional.ofNullable(labels.get("entity_instance_id")).map(value -> value.asText()).orElse(null));
//				}
//				activeAlarm.setNeType(Optional.ofNullable(labels.get("entity_type_id")).map(value -> value.asText()).orElse(null));

                if (Objects.nonNull(labels.get("instanceId"))) {
                    activeAlarm.setAlarmObjectUid(Optional.ofNullable(labels.get("instanceId")).map(value -> value.asText()).orElse(null));
                } else {
                    activeAlarm.setAlarmObjectUid(Optional.ofNullable(labels.get("entity_instance_id")).map(value -> value.asText()).orElse(null));
                }

                if (Objects.nonNull(labels.get("entity_instance_id"))) {
                    activeAlarm.setAlarmObject(Optional.ofNullable(labels.get("entity_instance_id")).map(value -> value.asText()).orElse(null));
                    activeAlarm.setAlarmObjectName(Optional.ofNullable(labels.get("entity_instance_id")).map(value -> value.asText()).orElse(null));
                } else if (Objects.nonNull(labels.get("instanceName"))) {
                    activeAlarm.setAlarmObject(Optional.ofNullable(labels.get("instanceName").asText()).orElse(null));
                    activeAlarm.setAlarmObjectName(Optional.ofNullable(labels.get("instanceName").asText()).orElse(null));
                } else {
                    activeAlarm.setAlarmObject(Optional.ofNullable(labels.get("instance").asText()).orElse(null));
                    activeAlarm.setAlarmObjectName(Optional.ofNullable(labels.get("instance").asText()).orElse(null));
                }
                activeAlarm.setAlarmObjectType(Optional.ofNullable(labels.get("entity_type_id")).map(value -> value.asText()).orElse(null));
                activeAlarm.setAlarmDeviceType(Optional.ofNullable(labels.get("resourceType")).map(value -> value.asText()).orElse(null));

                activeAlarm.setAlarmPvFlag(0);
                activeAlarm.setOffLine(0);
                activeAlarm.setAlarmPvFlag(0);

                activeAlarm.setDomainName(Optional.ofNullable(labels.get("domain")).map(value -> value.asText()).orElse(null));
                activeAlarm.setProjectId(Optional.ofNullable(labels.get("projectId")).map(value -> value.asText()).orElse(null));
                activeAlarm.setProjectName(Optional.ofNullable(labels.get("projectName")).map(value -> value.asText()).orElse(null));
                activeAlarm.setSource(Optional.ofNullable(labels.get("job")).map(value -> value.asText()).orElse(null));
                if (Objects.nonNull(annotations.get("proposed_repair_action"))) {
                    AlarmKnowledge alarmKnowledge = new AlarmKnowledge();
                    alarmKnowledge.setAlarmId(activeAlarm.getAlarmId());
                    alarmKnowledge.setSpecificProblem(Optional.ofNullable(activeAlarm.getSpecificProblem()).orElse(null));
                    alarmKnowledge.setSpecificAnalyse(annotations.get("proposed_repair_action").asText());
                    alarmKnowledge.setSpecificProblemId(Optional.ofNullable(activeAlarm.getSpecificProblemId()).orElse(null));
                    alarmKnowledge.setSpecificTime(Optional.ofNullable(activeAlarm.getAlarmEventTime()).orElse(null));
                    updAlarmKnowledge(alarmKnowledge);
                }

                activeAlarms.add(activeAlarm);
            }
        }
        return activeAlarms;
    }

    private String buildAlarmAddInfo(JsonNode labels) {
        List<String> addInfoList = new ArrayList<>();
        if (labels.get("alarm_type") != null && StringUtils.isNotBlank(labels.get("alarm_type").asText())) {
            addInfoList.add("AlarmType:" + labels.get("alarm_type").asText());
        }
        if (labels.get("nodename") != null && StringUtils.isNotBlank(labels.get("nodename").asText())) {
            addInfoList.add("NodeName:" + labels.get("nodename").asText());
        }
        if (labels.get("vhost") != null && StringUtils.isNotBlank(labels.get("vhost").asText())) {
            addInfoList.add("VHost:" + labels.get("vhost").asText());
        }
        if (labels.get("pod") != null && StringUtils.isNotBlank(labels.get("pod").asText())) {
            addInfoList.add("Pod:" + labels.get("pod").asText());
        }
        if (labels.get("container") != null && StringUtils.isNotBlank(labels.get("container").asText())) {
            addInfoList.add("Container:" + labels.get("container").asText());
        }
        if (labels.get("flavorName") != null && StringUtils.isNotBlank(labels.get("flavorName").asText())) {
            addInfoList.add("FlavorName:" + labels.get("flavorName").asText());
        }
        if (labels.get("prometheus") != null && StringUtils.isNotBlank(labels.get("prometheus").asText())) {
            addInfoList.add("Prometheus:" + labels.get("prometheus").asText());
        }
        return String.join("; ", addInfoList);
    }

    private void updAlarmKnowledge(AlarmKnowledge alarmKnowledge) {
        alarmKnowledgeService.save(alarmKnowledge);
    }

    private Date stringT2Date(String dateStr) {

        FastDateFormat df = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss", TimeZone.getTimeZone("UTC"));
        try {
            return df.parse(dateStr.substring(0, 19));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean manualDeleteAlarm(ActiveAlarm activeAlarm, String developerId) {
        redisCacheService.remMapValue(redisSaveActiveAlarmDB, activeAlarm.getAlarmId());
        redisCacheService.remSetKey(redisActiveAlarm, activeAlarm.getAlarmId());
        HistoryAlarm historyAlarm = new HistoryAlarm();
        BeanUtils.copyProperties(activeAlarm, historyAlarm);
        historyAlarm.setId(null);
        activeAlarmService.remove(new LambdaQueryWrapper<ActiveAlarm>()
                .eq(ActiveAlarm::getId, activeAlarm.getId())
                .eq(StringUtils.isNotEmpty(developerId), ActiveAlarm::getDeveloperId, developerId));
        historyAlarm.setAlarmClearedTime(timeStamp2Date(System.currentTimeMillis()));
        historyAlarm.setAlarmClearedType(2);
        return historyAlarmService.save(historyAlarm);
    }


    public Object decodeToken(String token, String claim) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(claim).as(Object.class);
    }

}
