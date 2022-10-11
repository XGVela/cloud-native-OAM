package org.xgvela.oam.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String o2js(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String o2jsPretty(Object object) {
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JsonNode o2jn(Object object) {
        try {
            return MAPPER.valueToTree(object);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> T js2o(String json, Class<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> T js2o(String json, TypeReference<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static JsonNode js2jn(String json) {
        return js2o(json, JsonNode.class);
    }

    public static List<JsonNode> js2jns(String json) {
        try {
            return MAPPER.readValues(new JsonFactory().createParser(json), JsonNode.class).readAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> T jn2o(JsonNode jn, Class<T> type) {
        try {
            return MAPPER.convertValue(jn, type);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> T jn2o(JsonNode jn, TypeReference<T> type) {
        try {
            return MAPPER.convertValue(jn, type);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static ObjectNode createObjectNode() {
        return MAPPER.createObjectNode();
    }

    public static ArrayNode createArrayNode() {
        return MAPPER.createArrayNode();
    }

    public static String getString(JsonNode jsonNode, String fieldName) {
        String value = null;
        JsonNode node = jsonNode.get(fieldName);
        if (ObjectUtils.isNotEmpty(node)) {
            if (node.isArray()) {
                node = node.get(0);
            }
            value = node.asText();
        }
        if (StringUtils.isBlank(value)) {
            value = null;
        }
        return value;
    }

    public static String getString(JsonNode jsonNode, String fieldName, String defaultValue) {
        return StringUtils.defaultIfBlank(getString(jsonNode, fieldName), defaultValue);
    }

    public static Integer getInt(JsonNode jsonNode, String fieldName) {
        Integer value = null;
        JsonNode node = jsonNode.get(fieldName);
        if (ObjectUtils.isNotEmpty(node)) {
            if (node.isArray()) {
                node = node.get(0);
            }
            value = node.asInt();
        }
        return value;
    }

    public static Long getLong(JsonNode jsonNode, String fieldName) {
        Long value = null;
        JsonNode node = jsonNode.get(fieldName);
        if (ObjectUtils.isNotEmpty(node)) {
            if (node.isArray()) {
                node = node.get(0);
            }
            value = node.asLong();
        }
        return value;
    }

    public static Double getDouble(JsonNode jsonNode, String fieldName) {
        Double value = null;
        JsonNode node = jsonNode.get(fieldName);
        if (ObjectUtils.isNotEmpty(node)) {
            if (node.isArray()) {
                node = node.get(0);
            }
            value = node.asDouble();
        }
        return value;
    }

    public static Boolean getBoolean(JsonNode jsonNode, String fieldName) {
        Boolean value = null;
        JsonNode node = jsonNode.get(fieldName);
        if (ObjectUtils.isNotEmpty(node)) {
            if (node.isArray()) {
                node = node.get(0);
            }
            value = node.asBoolean();
        }
        return value;
    }

    public static <T> T get(JsonNode jsonNode, String fieldName, TypeReference<T> type) {
        return jn2o(jsonNode.get(fieldName), type);
    }

    public static JsonNode cloneJsonNode(JsonNode jsonNode) throws IOException {
        try {
            return MAPPER.readTree(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
