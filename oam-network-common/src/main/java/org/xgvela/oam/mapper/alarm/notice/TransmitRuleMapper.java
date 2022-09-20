package org.xgvela.oam.mapper.alarm.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.notice.TransmitRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * TransmitRule Mapper interface
 * </p>
 */
@Mapper
public interface TransmitRuleMapper extends BaseMapper<TransmitRule> {

    /**
     * get selectRuleList
     * @param developerId
     * @param id
     * @param name
     * @param eventId
     * @param eventName
     * @param templateId
     * @param templateName
     * @param sendMethod
     * @return
     */
    List<TransmitRule> selectRuleList(@Param("developerId") String developerId, @Param("id") Long id, @Param("name") String name,
                                      @Param("eventId") String eventId, @Param("eventName") String eventName,
                                      @Param("templateId") Long templateId, @Param("templateName") String templateName,
                                      @Param("sendMethod") String sendMethod);
}
