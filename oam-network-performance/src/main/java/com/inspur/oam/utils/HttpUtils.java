package org.xgvela.oam.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.ObjectUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class HttpUtils {
    private final static Object httpLock = new Object();
    private final static Object httpsLock = new Object();

    private static volatile OkHttpClient client;

    private static final SSLContext sslContext;
    private static final TrustManager[] trustManager = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }
    };

    static {
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * http客户端
     */
    protected static OkHttpClient getHttpClient() {
        if (ObjectUtils.isEmpty(client)) {
            synchronized (httpLock) {
                if (ObjectUtils.isEmpty(client)) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    return client = new OkHttpClient().newBuilder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .callTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .connectionPool(new ConnectionPool())
                            .addInterceptor(interceptor).build();
                }
            }
        }
        return client;
    }

    /**
     * https客户端，跳过ssl认证
     */
    protected static OkHttpClient getHttpsClient() {
        if (ObjectUtils.isEmpty(client)) {
            synchronized (httpsLock) {
                if (ObjectUtils.isEmpty(client)) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    return client = new OkHttpClient().newBuilder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .callTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .connectionPool(new ConnectionPool())
                            .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManager[0])
                            .hostnameVerifier((hostname, session) -> true)
                            .addInterceptor(interceptor).build();
                }
            }
        }
        return client;
    }
}
