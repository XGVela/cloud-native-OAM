package org.xgvela.oam.mapper.alarm.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.notice.TransmitRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TransmitRuleMapper extends BaseMapper<TransmitRule> {


    List<TransmitRule> selectRuleList(@Param("developerId") String developerId, @Param("id") Long id, @Param("name") String name,
                                      @Param("eventId") String eventId, @Param("eventName") String eventName,
                                      @Param("templateId") Long templateId, @Param("templateName") String templateName,
                                      @Param("sendMethod") String sendMethod);
}
