package org.xgvela.oam.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Map.Entry;

@Slf4j
public class OKHttpUtils extends BaseHttpUtils{

    private final static int READ_TIMEOUT = 100000;

    private final static int CONNECT_TIMEOUT = 60000;

    private final static int WRITE_TIMEOUT = 60000;

    public static String get(String url, Map<String, Object> map){
        
        try{
            log.debug("http get url: {}, parameter: {}", url, map.toString());
            
            if(!CollectionUtils.isEmpty(map)){
                
                StringBuilder urlSb = new StringBuilder(url).append("?");
                for(Entry<String, Object> entry : map.entrySet()){
                    urlSb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }

                url = urlSb.toString();
                url = url.substring(0, url.length() - 1);
            }

            OkHttpClient client = BaseHttpUtils.getHttpsClient();
            Request request = new Request.Builder().url(url).build();

            Response response = client.newCall(request).execute();

            assert response.body() != null;
            String result = response.body().string();
            log.debug("http get return: {}", result);

            return result;
        }catch (Exception e) {
            log.error("http get request failed", e);
        }
        return null;
    }

    public static String post(String url, String content){
        
        try {
            log.debug("http post url: {}, content: {}", url, content);

            OkHttpClient client = BaseHttpUtils.getHttpsClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);

            Request request = new Request.Builder().url(url).post(body).build();

            Response response = client.newCall(request).execute();

            assert response.body() != null;
            String result = response.body().string();
            log.debug("http post return: {}", result);

            return result;
        }catch (Exception e) {
            log.error("http post request failed", e);
        }
        return null;
    }
    
}
