package org.xgvela.oam.mapper.alarm.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.notice.TransmitEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * TransmitEvent Mapper interface
 * </p>
 */
@Mapper
public interface TransmitEventMapper extends BaseMapper<TransmitEvent> {

    /**
     * selectEventList
     * @param id
     * @param name
     * @param tableName
     * @param start
     * @param pageSize
     * @return
     */
    public List<TransmitEvent> selectEventList(@Param("id") String id, @Param("name") String name,
                                               @Param("tableName") String tableName, @Param("start") Long start, @Param("pageSize") Long pageSize);

    /**
     * selectEventListCount
     * @param id
     * @param name
     * @param tableName
     * @return
     */
    public int selectEventListCount(@Param("id") String id, @Param("name") String name, @Param("tableName") String tableName);
}
