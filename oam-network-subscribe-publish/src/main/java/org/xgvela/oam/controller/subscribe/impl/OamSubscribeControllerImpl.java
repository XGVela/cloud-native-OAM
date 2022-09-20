package org.xgvela.oam.controller.subscribe.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import org.xgvela.oam.controller.subscribe.IOamSubscribeController;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.service.nfservice.InfService;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  OamSubscribeControllerImpl
 * </p>
 */
@RestController
@AllArgsConstructor
public class OamSubscribeControllerImpl implements IOamSubscribeController {

    private final IOamSubscribeService oamSubscribeService;
    private final InfService infService;
    private static final String RegisterDataType = "register";

    @Override
    public Response<Boolean> add(OamSubscribe oamSubscribe) {
        List<String> neIds = new ArrayList<>();
        if(oamSubscribe.getNeId().contains(",")){
            neIds = Arrays.asList(oamSubscribe.getNeId().split("\\,").clone());
        }else{
            neIds.add(oamSubscribe.getNeId());
        }
        LambdaQueryWrapper<OamVnf> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(OamVnf::getNeId, neIds);
        lambdaQueryWrapper.eq(OamVnf::getSystemId, oamSubscribe.getSystemId());
        List<OamVnf> oamVnfs = infService.list(lambdaQueryWrapper);
        if(oamVnfs == null){
            return ResponseFactory.getError("The upper-layer network management did not manage the network element, and the creation of the subscription task failed");
        }
        if(oamVnfs.size() > 0){
            for(OamVnf oamVnf: oamVnfs){
                if(!"1".equals(oamVnf.getVnfManageStatus())){
                        return ResponseFactory.getError("The upper-level network management does not manage the network element: " + oamVnf.getNeId() + ",Failed to create subscription task");
                }
            }
        }
        List<OamSubscribe> oamSubscribes = new ArrayList<>();
        for(String neId: neIds){
            OamSubscribe oamSubscribe1 = new OamSubscribe();
            oamSubscribe1.setSystemId(oamSubscribe.getSystemId());
            oamSubscribe1.setDataType(oamSubscribe.getDataType());
            oamSubscribe1.setCallbackUrl(oamSubscribe.getCallbackUrl());
            oamSubscribe1.setNeId(neId);
            oamSubscribes.add(oamSubscribe1);
        }
        del(oamSubscribe.getSystemId());
        return ResponseFactory.getSuccessData(oamSubscribeService.saveBatch(oamSubscribes));
    }

    @Override
    public Response<Boolean> del(String systemId) {
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamSubscribe::getSystemId, systemId);
        lambdaQueryWrapper.ne(OamSubscribe::getDataType, RegisterDataType);
        return ResponseFactory.getSuccessData(oamSubscribeService.remove(lambdaQueryWrapper));
    }

    @Override
    public Response<List<OamSubscribe>> info(String systemId) {
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamSubscribe::getSystemId, systemId);
        lambdaQueryWrapper.ne(OamSubscribe::getDataType, RegisterDataType);
        List<OamSubscribe> oamSubscribes = oamSubscribeService.list(lambdaQueryWrapper);
        return ResponseFactory.getSuccessData(oamSubscribes);
    }

}