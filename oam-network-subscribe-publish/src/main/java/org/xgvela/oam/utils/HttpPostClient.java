package org.xgvela.oam.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpPostClient {

    @Autowired
    private RestTemplate restTemplate;

    public boolean send(String url, Object obj){
        restTemplate.postForEntity(url, obj, Object.class);
        return true;
    }
}
