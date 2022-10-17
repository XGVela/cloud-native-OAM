package org.xgvela.oam.service.tmass.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.xgvela.oam.config.TmassConfiguration;
import org.xgvela.oam.service.tmass.ITmassSelectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TmassSelectServiceImpl implements ITmassSelectService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TmassConfiguration tmassConfiguration;

    private static final String NetworkFunctions = "network_functions";
    private static final String NfType = "nfType";
    private static final String Name = "name";

    @Override
    public Object getNfResourceFromTmassPlatform(String neType, String neId) {
        String url = "http://" + tmassConfiguration.getIp() + ":" + tmassConfiguration.getPort() + "/" + tmassConfiguration.getRequestPath();
        JSONObject tmassObj = restTemplate.getForObject(url, JSONObject.class);
        Object tmassResult = assembleNfResource(neType, neId, tmassObj);
        return tmassResult;
    }

    private Object assembleNfResource(String nfType, String name, JSONObject tmassInfo){
        log.info("Tmass Info: " + tmassInfo.toJSONString());
        JSONObject jsonObject = JSON.parseObject(tmassInfo.toJSONString());
        JSONArray networkFunctions = (JSONArray) jsonObject.get(NetworkFunctions);
        JSONArray jsonArray = new JSONArray();
        if(networkFunctions != null && networkFunctions.size() > 0){
            for(Object networkFunc: networkFunctions){
                boolean isInstanceId = false;
                boolean isNfType = false;
                JSONObject networkJson = (JSONObject) networkFunc;
                if(nfType.equals(networkJson.get(NfType))){
                    isNfType = true;
                }
                if(name.equals(networkJson.get(Name))){
                    isInstanceId = true;
                }
                boolean isTarget = isInstanceId && isNfType;
                if(isTarget){
                    jsonArray.add(networkJson);
                }
            }
        }
        return jsonArray;
    }

}
