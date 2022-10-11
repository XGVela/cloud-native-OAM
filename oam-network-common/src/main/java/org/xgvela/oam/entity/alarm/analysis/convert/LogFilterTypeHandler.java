package org.xgvela.oam.entity.alarm.analysis.convert;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.xgvela.oam.entity.alarm.analysis.LogFilter;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.util.List;

@MappedTypes({Object.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class LogFilterTypeHandler extends AbstractJsonTypeHandler<Object> {
    private ObjectMapper objectMapper;
    private JavaType javaType;

    public LogFilterTypeHandler(){
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructParametricType(List.class, LogFilter.class);
    }

    @Override
    protected Object parse(String json) {
        try {
            return objectMapper.readValue(json,javaType);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    @Override
    protected String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException var3) {
            throw new RuntimeException(var3);
        }
    }
}
