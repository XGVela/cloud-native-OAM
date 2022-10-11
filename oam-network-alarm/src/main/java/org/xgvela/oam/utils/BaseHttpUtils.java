package org.xgvela.oam.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * HttpUtils
 * </p>
 * @author gengdawen
 * @since 2021/11/5
 */
@Slf4j
public abstract class BaseHttpUtils {
    private final static Object HTTP_LOCK = new Object();
    private final static Object HTTPS_LOCK = new Object();

    private static volatile OkHttpClient client;

    private static final SSLContext SSL_CONTEXT;
    private static final TrustManager[] TRUST_MANAGER = new TrustManager[]{
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
            SSL_CONTEXT = SSLContext.getInstance("TLSV1.2");
            SSL_CONTEXT.init(null, TRUST_MANAGER, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * http client
     */
    protected static OkHttpClient getHttpClient() {
        if (null == client) {
            synchronized (HTTP_LOCK) {
                if (null == client) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    return client = new OkHttpClient().newBuilder()
                            .connectTimeout(5, TimeUnit.SECONDS)
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
     * https On the client, skip ssl authentication
     */
    protected static OkHttpClient getHttpsClient() {
        if (null == client) {
            synchronized (HTTPS_LOCK) {
                if (null == client) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    return client = new OkHttpClient().newBuilder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .connectionPool(new ConnectionPool())
                            .sslSocketFactory(SSL_CONTEXT.getSocketFactory(), (X509TrustManager) TRUST_MANAGER[0])
                            .hostnameVerifier((hostname, session) -> true)
                            .addInterceptor(interceptor).build();
                }
            }
        }
        return client;
    }
}
