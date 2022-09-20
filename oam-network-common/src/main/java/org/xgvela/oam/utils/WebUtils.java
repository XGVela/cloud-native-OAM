package org.xgvela.oam.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebUtils {
    public static final String HEADER_NAME_AUTH = "Authorization";
    public static final String HEADER_VALUE_AUTH = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjM3ODcwMTUsInVzZXJJZCI6MSwiYWNjb3VudCI6ImFkbWluIn0.h1HXvmBP3JnvRF-u8jQpfyCPM9sn_IC2JanD6cHniD8";

    private WebUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
    }

    public static JsonNode getParams() {
        return JsonUtils.o2jn(getRequest().getParameterMap());
    }

    public static Map<String, String[]> getParamsMultiValueMap() {
        return getRequest().getParameterMap();
    }

    public static Map<String, String> getParamsSingleValueMap() {
        Map<String, String> map = new LinkedHashMap<>();
        getParamsMultiValueMap().forEach((key, values) -> map.put(key, values[0]));
        return map;
    }

    public static String getAuthorization() {
        return getRequest().getHeader(HEADER_NAME_AUTH);
    }
}
