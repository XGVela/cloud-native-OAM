package org.xgvela.oam.entity.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.xgvela.oam.utils.LocalDateTimeUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author wangyongbo01
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(LocalDateTimeUtils.getMilliSeconds(value));
        gen.flush();
    }
}
