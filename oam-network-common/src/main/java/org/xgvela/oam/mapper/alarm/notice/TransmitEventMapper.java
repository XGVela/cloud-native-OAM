package org.xgvela.oam.mapper.alarm.notice;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.xgvela.oam.entity.alarm.notice.TransmitEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransmitEventMapper extends BaseMapper<TransmitEvent> {


    public List<TransmitEvent> selectEventList(@Param("id") String id, @Param("name") String name,
                                               @Param("tableName") String tableName, @Param("start") Long start, @Param("pageSize") Long pageSize);

    public int selectEventListCount(@Param("id") String id, @Param("name") String name, @Param("tableName") String tableName);
}
