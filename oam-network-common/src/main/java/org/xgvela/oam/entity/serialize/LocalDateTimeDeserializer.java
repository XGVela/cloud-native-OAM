package org.xgvela.oam.entity.serialize;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.xgvela.oam.utils.LocalDateTimeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author wangyongbo01
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {


    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            long milliseconds = p.getValueAsLong();
            return LocalDateTimeUtils.getLocalDateTime(milliseconds);
        } catch (Exception e) {
        }

        String value = null;
        try {
            value = p.getValueAsString();
            if (StringUtils.isEmpty(value)) {
                return null;
            }
        } catch (IOException e) {
            throw e;
        }

        if (value.indexOf(StringPool.COLON) >= 0 && value.length() == LocalDateTimeUtils.DATETIME_PATTERN.length()) {
            return LocalDateTime.parse(value, LocalDateTimeUtils.DATETIME_FORMAT);
        } else if (value.indexOf(StringPool.COLON) >= 0 && value.length() == LocalDateTimeUtils.MINUTE_PATTERN.length()) {
            return LocalDateTime.parse(value, LocalDateTimeUtils.MINUTE_FORMAT);
        } else if (value.indexOf(StringPool.COLON) == -1 && value.length() == LocalDateTimeUtils.DATE_PATTERN.length()) {
            return LocalDateTime.parse(value, LocalDateTimeUtils.DATE_FORMAT);
        }

        throw new IOException("unexcepted LocalDateTime format"+ p.getText());
    }
}
