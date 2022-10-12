package org.xgvela.oam.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class YamlUtils {
    private static final YAMLMapper MAPPER = new YAMLMapper();

    private YamlUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String o2ym(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static JsonNode ym2jn(String yaml) {
        try {
            return MAPPER.readTree(yaml);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T ym2o(String yaml, Class<T> clazz) {
        try {
            return MAPPER.readValue(yaml, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static List<JsonNode> ym2jns(String yaml) {
        try {
            return MAPPER.readValues(new YAMLFactory().createParser(yaml), JsonNode.class).readAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
