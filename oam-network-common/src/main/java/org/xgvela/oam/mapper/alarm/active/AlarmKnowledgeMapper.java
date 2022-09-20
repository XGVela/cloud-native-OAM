package org.xgvela.oam.mapper.alarm.active;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.active.AlarmKnowledge;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * AlarmKnowledgeMapper  Mapper interface
 * </p>
 */
public interface AlarmKnowledgeMapper extends BaseMapper<AlarmKnowledge> {

    /**
     * AlarmKnowledge
     * @param alarmId
     * @param specificProblemId
     * @param specificProblem
     * @param specificAnalyse
     * @param specificUsername
     * @param developerId
     * @param startTime
     * @param endTime
     * @param start
     * @param pageSize
     * @param columnName
     * @param sortOrder
     * @return
     */
    public List<AlarmKnowledge> selectForAlarmKnowledge(@Param("alarmId") String alarmId,
                                                        @Param("specificProblemId") String specificProblemId,
                                                        @Param("specificProblem") String specificProblem,
                                                        @Param("specificAnalyse") String specificAnalyse,
                                                        @Param("specificUsername") String specificUsername,
                                                        @Param("developerId") String developerId,
                                                        @Param("startTime") Date startTime,
                                                        @Param("endTime") Date endTime,
                                                        @Param("start") Long start,
                                                        @Param("pageSize") Long pageSize,
                                                        @Param("columnName") String columnName,
                                                        @Param("sortOrder") String sortOrder);

    /**
     * countForAlarmKnowledge
     * @param alarmId
     * @param specificProblemId
     * @param specificProblem
     * @param specificAnalyse
     * @param specificUsername
     * @param developerId
     * @param startTime
     * @param endTime
     * @return
     */
    public Integer countForAlarmKnowledge(@Param("alarmId") String alarmId,
                                          @Param("specificProblemId") String specificProblemId,
                                          @Param("specificProblem") String specificProblem,
                                          @Param("specificAnalyse") String specificAnalyse,
                                          @Param("specificUsername") String specificUsername,
                                          @Param("developerId") String developerId,
                                          @Param("startTime") Date startTime,
                                          @Param("endTime") Date endTime);

}
