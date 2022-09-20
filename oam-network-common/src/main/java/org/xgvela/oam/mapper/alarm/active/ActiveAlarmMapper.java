package org.xgvela.oam.mapper.alarm.active;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.active.ActiveAlarm;
import org.xgvela.oam.entity.alarm.active.ActiveAlarmSeverity;
import org.xgvela.oam.entity.alarm.statistics.ActiveAlarmStatisticsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * ActiveAlarmMapper Mapper interface
 * </p>
 */
@Mapper
public interface ActiveAlarmMapper extends BaseMapper<ActiveAlarm> {

    /**
     * getCountByLabel
     * @param developerId
     * @param label
     * @return
     */
    public List<ActiveAlarmStatisticsDTO> getCountByLabel(@Param(value = "developerId") String developerId,
                                                          @Param(value = "label") String label);

    public List<ActiveAlarmSeverity> getActiveAlarmSeverities(@Param(value = "alarmType") Integer alarmType,
                                                              @Param(value = "source") String source,
                                                              @Param(value = "alarmLevel") Integer alarmLevel,
                                                              @Param(value = "alarmPvFlag") Integer alarmPvFlag,
                                                              @Param(value = "neName") String neName,
                                                              @Param(value = "alarmNo") String alarmNo,
                                                              @Param(value = "offLine") Integer offLine,
                                                              @Param(value = "mergeFlag") Integer mergeFlag,
                                                              @Param("startTime") String startTime,
                                                              @Param("endTime") String endTime);
}
