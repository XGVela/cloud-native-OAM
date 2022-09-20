package org.xgvela.oam.entity.serialize;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.Instant;

/**
 * @author wangyongbo01
 */
public class DefaultInstantDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            long milliseconds = p.getValueAsLong();
            return Instant.ofEpochMilli(milliseconds);
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

        if (value.indexOf(StringPool.COLON) >= 0) {
            return Instant.parse(value);
        } else if (value.indexOf(StringPool.COLON) >= 0) {
            return Instant.parse(value);
        } else if (value.indexOf(StringPool.COLON) == -1) {
            return Instant.parse(value);
        }

        throw new IOException("unexcepted Instant format" + p.getText());
    }
}
