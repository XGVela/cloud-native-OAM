package org.xgvela.oam.utils;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class PrometheusUtils extends HttpUtils {
    private static final String DEFAULT_HOST = "http://prom-prometheus-server";

    private static JsonNode querys;

    private PrometheusUtils() {
    }

    static {
        loadQuerys();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(PrometheusUtils::loadQuerys, 10, 10, TimeUnit.SECONDS);
    }

    private static void loadQuerys() {
        try {
            log.debug("同步query.yaml");
            querys = YamlUtils.ym2jn(IOUtils.toString(new ClassPathResource("promql/query.yaml").getInputStream(), StandardCharsets.UTF_8.toString()));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static JsonNode query_range(String host, String query, Long start, Long end, Long step) {
        try {
            Response response = getHttpClient().newCall(new Request.Builder().url(Objects.requireNonNull(HttpUrl.parse((StringUtils.isNotBlank(host) ? host : DEFAULT_HOST) + "/api/v1/query_range")).newBuilder()
                    .addQueryParameter("query", query)
                    .addQueryParameter("start", BigDecimal.valueOf(1.0D * Optional.ofNullable(start).orElse(System.currentTimeMillis() - 60 * 60 * 1000) / 1000).toString())
                    .addQueryParameter("end", BigDecimal.valueOf(1.0D * Optional.ofNullable(end).orElse(System.currentTimeMillis()) / 1000).toString())
                    .addQueryParameter("step", Long.toString(!Objects.isNull(step) && step >= 30 ? step : 30))
                    .build()).build()).execute();
            return JsonUtils.js2jn(Objects.requireNonNull(response.body()).string());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonUtils.createArrayNode();
        }
    }

    public static JsonNode query(String host, String query) {
        try {
            Response response = getHttpClient().newCall(new Request.Builder().url(Objects.requireNonNull(HttpUrl.parse((StringUtils.isNotBlank(host) ? host : DEFAULT_HOST) + "/api/v1/query")).newBuilder()
                    .addQueryParameter("query", query)
                    .build()).build()).execute();
            return JsonUtils.js2jn(Objects.requireNonNull(response.body()).string());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonUtils.createArrayNode();
        }
    }

    public static JsonNode query(String host, String query, Long time) {
        try {
            Response response = getHttpClient().newCall(new Request.Builder().url(Objects.requireNonNull(HttpUrl.parse((StringUtils.isNotBlank(host) ? host : DEFAULT_HOST) + "/api/v1/query")).newBuilder()
                    .addQueryParameter("query", query)
                    .addQueryParameter("time", BigDecimal.valueOf(1.0D * time / 1000).toString())
                    .build()).build()).execute();
            return JsonUtils.js2jn(Objects.requireNonNull(response.body()).string());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return JsonUtils.createArrayNode();
        }
    }

    public static String getQuery(String key) {
        return querys.get(key).get("query").asText();
    }

    public static JsonNode getResult(JsonNode jn) {
        if (jn.get("status") != null && "success".equals(jn.get("status").asText())) {
            return jn.get("data").get("result");
        }
        return JsonUtils.createArrayNode();
    }

    public static String getValue(JsonNode jn) {
        if (jn.size() == 0) {
            return "0";
        }
        return jn.get(0).get("value").get(1).asText();
    }

    public static double f2d(String val) {
        return new BigDecimal(val).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 根据查询语句，查询到具体值
     *
     * @param host
     * @param query
     * @param time
     * @return
     */
    public static double getValue(String host, String query, Long time) {
        JsonNode result = query(host, query, time);
        JsonNode successResult = getResult(result);
        String value = getValue(successResult);
        return f2d(value);
    }

}
