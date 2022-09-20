package org.xgvela.oam.controller.subscribe.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import org.xgvela.oam.controller.subscribe.IOamRegisterSubscribeController;
import org.xgvela.oam.entity.RegisterSubRequest;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.service.nfservice.InfService;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class OamRegisterSubscribeControllerImpl implements IOamRegisterSubscribeController {

    private final IOamSubscribeService oamSubscribeService;
    private final InfService infService;
    private static final String RegisterDataType = "register";

    @Override
    public Response<Boolean> add(RegisterSubRequest registerSubRequest) {
        LambdaQueryWrapper<OamVnf> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamVnf::getSystemId, registerSubRequest.getSystemId());
        List<OamVnf> oamVnfs = infService.list(lambdaQueryWrapper);
        if(oamVnfs == null || oamVnfs.size() == 0){
            return ResponseFactory.getError("The upper-layer network management did not manage the network element, and the creation of the subscription task failed");
        }
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(OamSubscribe::getSystemId, registerSubRequest.getSystemId());
        lambdaQueryWrapper1.eq(OamSubscribe::getDataType, RegisterDataType);
        List<OamSubscribe> oamSubscribes = oamSubscribeService.list(lambdaQueryWrapper1);
        if (oamSubscribes != null && oamSubscribes.size() > 0) {
            return ResponseFactory.getError("The upper layer network element created a registration subscription task, and the creation of the subscription task failed");
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
