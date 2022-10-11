package org.xgvela.oam.mapper.alarm.analysis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.xgvela.oam.entity.alarm.analysis.AlarmAnalysisRule;
import org.apache.ibatis.annotations.Param;

public interface AlarmAnalysisRuleMapper extends BaseMapper<AlarmAnalysisRule> {

    IPage<AlarmAnalysisRule> selectForAlarmAnalysisRule(@Param("id") Integer id,
                                                        @Param("ruleName") String ruleName,
                                                        @Param("timeWindow") String timeWindow,
                                                        @Param("status") Boolean status,
                                                        Page<AlarmAnalysisRule> page);

    AlarmAnalysisRule selectById(@Param("id") Integer id);
}
