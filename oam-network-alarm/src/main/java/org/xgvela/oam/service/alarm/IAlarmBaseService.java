package org.xgvela.oam.service.alarm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IAlarmBaseService<T> extends IService<T> {
	IPage<T> alarmPage(String userId, String alarmId, String alarmName, Integer alarmLevel,
                       Integer alarmType, String source, Integer alarmPvFlag, String neId, String neName, String neType, String alarmObjectUid, String startTime, String endTime, int pageNum,
                       int pageSize, String alarmFlag);
	List<T> countSeverity(String neName, String alarmId, String alarmObjectName, String alarmName, Integer alarmPvFlag, Integer alarmLevel,
                          Integer alarmType, String source, String startTime, String endTime, String alarmFlag, String developerId);
	List<T> countClass(String neName, String alarmId, String alarmName, Integer alarmPvFlag, Integer alarmLevel,
                       Integer alarmType, String source, String developerId, String startTime, String endTime);

	public long countAlarm(String alarmId, String developerId, String alarmFlag);
}