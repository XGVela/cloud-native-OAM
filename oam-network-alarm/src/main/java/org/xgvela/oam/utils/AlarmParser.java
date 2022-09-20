package org.xgvela.oam.utils;

import org.xgvela.oam.entity.alarm.active.AlarmDto;
import org.apache.commons.collections4.MapUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

public class AlarmParser {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    public static AlarmDto parse(Map<String, String> dbAlarm) {
        AlarmDto alarmDto = new AlarmDto();
        alarmDto.setAlarmSeq(MapUtils.getLong(dbAlarm, "id"));
        alarmDto.setAlarmTitle(MapUtils.getString(dbAlarm, "alarmEventTitle"));
        alarmDto.setAlarmStatus(MapUtils.getIntValue(dbAlarm, "alarmStatusType"));
        alarmDto.setAlarmType(MapUtils.getString(dbAlarm, "alarmType"));
        alarmDto.setOrigSeverity(MapUtils.getIntValue(dbAlarm, "alarmLevel"));
        if(null != MapUtils.getString(dbAlarm, "alarmEventTime")) {
        	alarmDto.setEventTime(LocalDateTime.ofEpochSecond(str2timeStamp(MapUtils.getString(dbAlarm, "alarmEventTime")) / 1000, 0, ZoneOffset.ofHours(8)).toString().replaceAll("T", " "));
        }else if(null != MapUtils.getString(dbAlarm, "alarmClearedTime")){
        	alarmDto.setEventTime(LocalDateTime.ofEpochSecond(str2timeStamp(MapUtils.getString(dbAlarm, "alarmClearedTime")) / 1000, 0, ZoneOffset.ofHours(8)).toString().replaceAll("T", " "));
        }
        alarmDto.setAlarmId(MapUtils.getString(dbAlarm, "alarmId"));
        alarmDto.setSpecificProblemID(MapUtils.getString(dbAlarm, "specificProblemId"));
        alarmDto.setSpecificProblem(MapUtils.getString(dbAlarm, "specificProblem"));
        alarmDto.setNeUID(MapUtils.getString(dbAlarm, "neId"));
        alarmDto.setNeName(MapUtils.getString(dbAlarm, "neName"));
        alarmDto.setNeType(MapUtils.getString(dbAlarm, "neType"));
        alarmDto.setObjectUID(MapUtils.getString(dbAlarm, "alarmObjectUid"));
        alarmDto.setObjectName(MapUtils.getString(dbAlarm, "alarmObjectName"));
        alarmDto.setObjectType(MapUtils.getString(dbAlarm, "alarmObjectType"));
        alarmDto.setLocationInfo(MapUtils.getString(dbAlarm, "alarmLocationInfo"));
        alarmDto.setAddInfo(MapUtils.getString(dbAlarm, "alarmAddInfo"));
        alarmDto.setPVFlag(MapUtils.getString(dbAlarm, "alarmPvFlag"));
        alarmDto.setProvince(MapUtils.getString(dbAlarm, "province"));
        return alarmDto;
    }
    
    public static long str2timeStamp(String dateStr) {
    	try {
			return sdf.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return 0;
    }
}
