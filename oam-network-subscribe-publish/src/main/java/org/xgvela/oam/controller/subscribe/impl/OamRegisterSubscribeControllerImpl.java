package org.xgvela.oam.controller.subscribe.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.controller.subscribe.IOamRegisterSubscribeController;
import org.xgvela.oam.entity.RegisterSubRequest;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class OamRegisterSubscribeControllerImpl implements IOamRegisterSubscribeController {

    private final IOamSubscribeService oamSubscribeService;
    private static final String RegisterDataType = "register";

    @Override
    public Response<Boolean> add(RegisterSubRequest registerSubRequest) {
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamSubscribe::getSystemId, registerSubRequest.getSystemId());
        lambdaQueryWrapper.eq(OamSubscribe::getDataType, RegisterDataType);
        List<OamSubscribe> oamSubscribes = oamSubscribeService.list(lambdaQueryWrapper);
        if (oamSubscribes != null && oamSubscribes.size() > 0) {
            throw new ServiceException(" The upper-layer OSS has created a registration subscription task, but failed to create a subscription task ");
        }
        OamSubscribe oamSubscribe = new OamSubscribe();
        oamSubscribe.setSystemId(registerSubRequest.getSystemId());
        oamSubscribe.setCallbackUrl(registerSubRequest.getCallbackUrl());
        oamSubscribe.setDataType(RegisterDataType);
        return ResponseFactory.getSuccessData(oamSubscribeService.save(oamSubscribe));
    }

    @Override
    public Response<Boolean> del(String systemId) {
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamSubscribe::getSystemId, systemId);
        lambdaQueryWrapper.eq(OamSubscribe::getDataType, RegisterDataType);
        return ResponseFactory.getSuccessData(oamSubscribeService.remove(lambdaQueryWrapper));
    }

    @Override
    public Response<List<RegisterSubRequest>> info(String systemId) {
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamSubscribe::getSystemId, systemId);
        lambdaQueryWrapper.eq(OamSubscribe::getDataType, RegisterDataType);
        List<OamSubscribe> oamSubscribes = oamSubscribeService.list(lambdaQueryWrapper);
        List<RegisterSubRequest> registerSubRequests = assembleRegisterSubInfo(oamSubscribes);
        return ResponseFactory.getSuccessData(registerSubRequests);
    }

    private List<RegisterSubRequest> assembleRegisterSubInfo(List<OamSubscribe> oamSubscribes){
        if(oamSubscribes != null && oamSubscribes.size() > 0){
            List<RegisterSubRequest> registerSubRequests = new ArrayList<>();
            for(OamSubscribe oamSubscribe: oamSubscribes){
                RegisterSubRequest registerSubRequest = RegisterSubRequest.builder().systemId(oamSubscribe.getSystemId()).callbackUrl(oamSubscribe.getCallbackUrl()).build();
                registerSubRequests.add(registerSubRequest);
            }
            return registerSubRequests;
        }
        return new ArrayList<>();
    }


}
