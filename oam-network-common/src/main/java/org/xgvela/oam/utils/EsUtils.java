package org.xgvela.oam.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;

@Slf4j
public class EsUtils {
    private EsUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static RestClientBuilder getRestClientBuilder(String host, Integer port, String username, String password) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        }
        return RestClient.builder(new HttpHost(host, port)).setHttpClientConfigCallback(
                clientBuilder -> clientBuilder.setDefaultCredentialsProvider(credentialsProvider).addInterceptorLast((HttpRequestInterceptor) (request, context) -> {
                    try {
                        HttpRequestWrapper r = ((HttpRequestWrapper) request);
                        URIBuilder builder = new URIBuilder(r.getURI());
                        List<NameValuePair> params = builder.getQueryParams();
                        params.removeIf(item -> StringUtils.equalsAny(item.getName(), "ccs_minimize_roundtrips"));
                        builder.setParameters(params);
                        r.setURI(builder.build());
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                })
        );
    }

    public static RestHighLevelClient getRestHighLevelClient(String host, Integer port, String username, String password) {
        return new RestHighLevelClient(getRestClientBuilder(host, port, username, password));
    }

    public static RestHighLevelClient getRestHighLevelClient(String host, Integer port) {
        return getRestHighLevelClient(host, port, "", "");
    }

    public static RestClient getRestClient(String host, Integer port, String username, String password) {
        return getRestClientBuilder(host, port, username, password).build();
    }

    public static RestClient getRestClient(String host, Integer port) {
        return getRestClientBuilder(host, port, "", "").build();
    }
}
