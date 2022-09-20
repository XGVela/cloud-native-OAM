package org.xgvela.oam.service.alarm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inspur.cnet.common.core.exception.ServiceException;
import com.inspur.cnet.common.core.utils.DateUtil;
import com.inspur.cnet.security.service.utils.SecurityUtils;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
public abstract class AlarmBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IAlarmBaseService<T> {

	@Override
	public IPage<T> alarmPage(String userId, String alarmNO, String alarmName, Integer alarmLevel,
                              Integer alarmType, String source, Integer alarmPvFlag, String neId, String neName, String neType, String alarmObjectUid, String startTime, String endTime, int pageNum,
                              int pageSize, String alarmFlag) {
		QueryWrapper<T> entityWrapper = buildAlarmPageWrapper(userId, alarmNO, alarmName, alarmLevel,
			 alarmType, source, alarmPvFlag,neId, neName, neType, alarmObjectUid, startTime, endTime,alarmFlag);
		if(entityWrapper==null){
			return null;
		}
		alarmPageOrder(entityWrapper);
		return this.page(new Page<>(pageNum, pageSize), entityWrapper);
	}
	
	private boolean checkNumberIsEmpty(Integer num) {
		if(num != null && num.toString() != "") {
			return true;
		}
		return false;
	}
	
	private String sqlEncode(String str) {
		if("[".equals(str)) {
			return "[[]";
		}else if("_".equals(str)) {
			return "[_]";
		}else if("%".equals(str)) {
			return "[%]";
		}
		return str;
	}
	
	protected QueryWrapper<T> buildAlarmPageWrapper(String userId, String alarmNO, String alarmName, Integer alarmLevel,
                                                    Integer alarmType, String source, Integer alarmPvFlag, String neId, String neName, String neType, String alarmObjectUid, String startTime, String endTime, String alarmFlag) {
		
		QueryWrapper<T> entityWrapper = new QueryWrapper();

		userId = SecurityUtils.getCurrentUserId().toString();
		String userName = SecurityUtils.getCurrentUserAccount();

		if(StringUtils.isNotEmpty(alarmNO)) {
			alarmNO = sqlEncode(alarmNO);
			entityWrapper.like("alarm_no", alarmNO);
		}
		if (StringUtils.isNotEmpty(alarmName)) {
			alarmName = sqlEncode(alarmName);
			entityWrapper.like("alarm_name", alarmName);
		}
		if(checkNumberIsEmpty(alarmLevel)) {
			entityWrapper.like("alarm_level", alarmLevel);
		}
		if(checkNumberIsEmpty(alarmType)) {
			entityWrapper.like("alarm_type", alarmType);
		}
		if(StringUtils.isNotEmpty(source)) {
			entityWrapper.eq("source", source);
		}
		if(checkNumberIsEmpty(alarmPvFlag)) {
			entityWrapper.like("alarm_pv_flag", alarmPvFlag);
		}
		if(StringUtils.isNotEmpty(neId)) {
			entityWrapper.eq("ne_id", neId);
		}
		if (StringUtils.isNotEmpty(neName)) {
			neName = sqlEncode(neName);
			entityWrapper.like("ne_name", neName);
		}
		if (StringUtils.isNotEmpty(neType)) {
			entityWrapper.eq("ne_type", neType);
		}
		if (StringUtils.isNotEmpty(alarmObjectUid)) {
			entityWrapper.eq("alarm_object_uid", alarmObjectUid);
		}

		if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
			try {
				Date startDate = DateUtil.parse(startTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
				Date endDate = DateUtil.parse(endTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
				entityWrapper.ge("alarm_event_time", startDate);
				entityWrapper.le("alarm_event_time", endDate);
			} catch (ParseException e) {
				throw new ServiceException("The time format is incorrect");
			}
		}
		if(alarmFlag.equals("active")) {
			entityWrapper.eq("merge_flag", 0);
		}
		entityWrapper.eq("off_line", 0);
		return entityWrapper;
	}

		protected void alarmPageOrder(QueryWrapper<T> entityWrapper) {
			entityWrapper.orderByDesc("alarm_event_time");
		}

		
		@Override
		public List<T> countSeverity(String neName, String alarmNO, String alarmObjectName, String alarmName,Integer alarmPvFlag, Integer alarmLevel,
				Integer alarmType, String source, String startTime, String endTime, String alarmFlag,String developerId) {

			QueryWrapper<T> queryWrapper = new QueryWrapper<>();
		
			queryWrapper.select("alarm_level", "count(alarm_level) AS count").groupBy("alarm_level");

			if(checkNumberIsEmpty(alarmType)) {
				queryWrapper.eq("alarm_type", alarmType);
			}
			if(StringUtils.isNotEmpty(source)) {
				queryWrapper.eq("source", source);
			}
			if(checkNumberIsEmpty(alarmLevel)) {
				queryWrapper.eq("alarm_level", alarmLevel);
			}
			if(checkNumberIsEmpty(alarmPvFlag)) {
				queryWrapper.eq("alarm_pv_flag", alarmPvFlag);
			}
			if (StringUtils.isNotEmpty(neName)) {
				neName = sqlEncode(neName);
				queryWrapper.like("ne_name", neName);
			}
			if(StringUtils.isNotEmpty(alarmNO)) {
				alarmNO = sqlEncode(alarmNO);
				queryWrapper.like("alarm_no", alarmNO);
			}
			if(StringUtils.isNotEmpty(alarmObjectName)) {
				queryWrapper.like("alarm_object_name", alarmObjectName);
			}
			if(StringUtils.isNotEmpty(alarmName)) {
				alarmName = sqlEncode(alarmName);
				queryWrapper.like("alarm_name", alarmName);
			}
			queryWrapper.eq("off_line", 0);
			if("active".equals(alarmFlag)) {
				queryWrapper.eq("merge_flag", 0);
			}
			if(StringUtils.isNotEmpty(developerId)){
				queryWrapper.eq("developer_id",developerId);
			}
			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				try {
					Date startDate = DateUtil.parse(startTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
					Date endDate = DateUtil.parse(endTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
					queryWrapper.ge("alarm_event_time", startDate);
					queryWrapper.le("alarm_event_time", endDate);
				} catch (ParseException e) {
					throw new ServiceException("The time format is incorrect");
				}
			}
			return list(queryWrapper);
		}

		@Override
		public List<T> countClass(String neName, String alarmNO,String alarmName,Integer alarmPvFlag, Integer alarmLevel,
				Integer alarmType, String source,String developerId, String startTime, String endTime) {

			QueryWrapper<T> queryWrapper = new QueryWrapper<>();
		
			queryWrapper.select("alarm_type", "count(alarm_type) AS count").groupBy("alarm_type");

			if(checkNumberIsEmpty(alarmType)) {
				queryWrapper.eq("alarm_type", alarmType);
			}
			if(StringUtils.isNotEmpty(source)) {
				queryWrapper.eq("source", source);
			}
			if(checkNumberIsEmpty(alarmLevel)) {
				queryWrapper.eq("alarm_level", alarmLevel);
			}
			if(checkNumberIsEmpty(alarmPvFlag)) {
				queryWrapper.eq("alarm_pv_flag", alarmPvFlag);
			}
			if (StringUtils.isNotEmpty(neName)) {
				neName = sqlEncode(neName);
				queryWrapper.like("ne_name", neName);
			}
			if(StringUtils.isNotEmpty(alarmNO)) {
				alarmNO = sqlEncode(alarmNO);
				queryWrapper.like("alarm_no", alarmNO);
			}
			if(StringUtils.isNotEmpty(alarmName)) {
				alarmName = sqlEncode(alarmName);
				queryWrapper.like("alarm_name", alarmName);
			}
			if(StringUtils.isNotEmpty(developerId)){
				queryWrapper.eq("developer_id",developerId);
			}
			queryWrapper.eq("off_line", 0);
			queryWrapper.eq("merge_flag", 0);

			if (StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)) {
				try {
					Date startDate = DateUtil.parse(startTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
					Date endDate = DateUtil.parse(endTime, ActiveAlarm.ALARM_DATE_PARAM_PATTERN);
					queryWrapper.ge("alarm_event_time", startDate);
					queryWrapper.le("alarm_event_time", endDate);
				} catch (ParseException e) {
					throw new ServiceException("The time format is incorrect");
				}
			}
			return list(queryWrapper);
		}


	@Override
	public long countAlarm(String alarmId,String developerId,String alarmFlag) {
		QueryWrapper<T> queryWrapper = new QueryWrapper<>();
		if(StringUtils.isNotEmpty(alarmId)){
			queryWrapper.eq("alarm_id", alarmId);
		}
		queryWrapper.eq("off_line", 0);
		if("active".equals(alarmFlag)) {
			queryWrapper.eq("merge_flag", 0);
		}
	    if(StringUtils.isNotEmpty(developerId)){
	    	queryWrapper.eq("developer_id",developerId);
		}
		List<T> list = list(queryWrapper);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.size();
		}
		return 0;
	}
}

