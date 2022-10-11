package org.xgvela.oam.controller.subscribe.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.controller.subscribe.IOamSubscribeController;
import org.xgvela.oam.entity.nftube.OamVnf;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import org.xgvela.oam.exception.ServiceException;
import org.xgvela.oam.service.nfservice.InfService;
import org.xgvela.oam.service.subscribe.IOamSubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
public class OamSubscribeControllerImpl implements IOamSubscribeController {

    private final IOamSubscribeService oamSubscribeService;
    private final InfService infService;
    private static final String RegisterDataType = "register";

    @Override
    public Response<Boolean> add(OamSubscribe oamSubscribe) {
        List<String> neIds = new ArrayList<>();
        if (oamSubscribe.getNeId().contains(",")) {
            neIds = Arrays.asList(oamSubscribe.getNeId().split("\\,").clone());
        } else {
            neIds.add(oamSubscribe.getNeId());
        }
        List<String> dataTypes = new ArrayList<>();
        if (oamSubscribe.getDataType().contains(",")) {
            dataTypes = Arrays.asList(oamSubscribe.getDataType().split("\\,").clone());
        } else {
            dataTypes.add(oamSubscribe.getDataType());
        }
        LambdaQueryWrapper<OamVnf> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(OamVnf::getNeId, neIds);
        lambdaQueryWrapper.eq(OamVnf::getSystemId, oamSubscribe.getSystemId());
        List<OamVnf> oamVnfs = infService.list(lambdaQueryWrapper);
        //Verifies whether the NE is managed by the upper-layer OSS
        if (oamVnfs == null || oamVnfs.size() == 0) {
            throw new ServiceException(" Upper-layer OSS does not manage this NE: + oamSubscribe.getNeId()，Failed to create a subscription task");
        }
        if (oamVnfs.size() > 0) {
            for (OamVnf oamVnf : oamVnfs) {
                if (!" 1".equals(oamVnf.getVnfManageStatus())) {
                    throw new ServiceException(" Upper-layer OSS does not manage this NE: + oamVnf.getNeId()  creating a subscription task failed");
                }
            }
        }
        //Assembles the input parameter
        List<OamSubscribe> oamSubscribes = new ArrayList<>();
        for (String neId : neIds) {
            for (String dataType : dataTypes) {
                OamSubscribe oamSubscribe1 = new OamSubscribe();
                oamSubscribe1.setSystemId(oamSubscribe.getSystemId());
                oamSubscribe1.setDataType(dataType);
                oamSubscribe1.setCallbackUrl(oamSubscribe.getCallbackUrl());
                oamSubscribe1.setNeId(neId);
                oamSubscribes.add(oamSubscribe1);
            }
        }
      //Checks for duplicate subscriptions
        boolean subscribeReult = false;
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(OamSubscribe::getSystemId, oamSubscribe.getSystemId());
        lambdaQueryWrapper1.ne(OamSubscribe::getDataType, RegisterDataType);
        List<OamSubscribe> oamSubscribesOrignal = oamSubscribeService.list(lambdaQueryWrapper1);
        if (oamSubscribesOrignal != null && oamSubscribesOrignal.size() > 0) {
            for (OamSubscribe oamSubscribe1 : oamSubscribesOrignal) {
                for (OamSubscribe oamSubscribe2 : oamSubscribes) {
                    if (oamSubscribe1.getSystemId().equals(oamSubscribe2.getSystemId()) && oamSubscribe1.getNeId().equals(oamSubscribe2.getNeId())
                            && oamSubscribe1.getDataType().equals(oamSubscribe2.getDataType()) && oamSubscribe1.getCallbackUrl().equals(oamSubscribe2.getCallbackUrl())) {
                        throw new ServiceException(" The upper-layer OSS has created this subscription task, creating the subscription task failed ");
                    }
                }
            }
        } else {
            subscribeReult = oamSubscribeService.saveBatch(oamSubscribes);
        }

        return ResponseFactory.getResponse(200, "successfully", subscribeReult);
    }

    @Override
    public Response<Boolean> del(String systemId) {
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamSubscribe::getSystemId, systemId);
        lambdaQueryWrapper.ne(OamSubscribe::getDataType, RegisterDataType);
        return ResponseFactory.getResponse(200, "successfully", oamSubscribeService.remove(lambdaQueryWrapper));
    }

    @Override
    public Response<List<OamSubscribe>> info(String systemId) {
        LambdaQueryWrapper<OamSubscribe> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OamSubscribe::getSystemId, systemId);
        lambdaQueryWrapper.ne(OamSubscribe::getDataType, RegisterDataType);
        List<OamSubscribe> oamSubscribes = oamSubscribeService.list(lambdaQueryWrapper);
        Map<String, List<OamSubscribe>> oamSubscribesMap = oamSubscribes.stream().collect(Collectors.groupingBy(OamSubscribe::getNeId));
        List<OamSubscribe> oamSubscribeList = new ArrayList<>();
        if (oamSubscribesMap != null && oamSubscribesMap.size() > 0) {
            for (Map.Entry<String, List<OamSubscribe>> entry : oamSubscribesMap.entrySet()) {
                OamSubscribe oamSubscribe1 = new OamSubscribe();
                StringBuilder dataType = new StringBuilder();
                for (int i = 0; i < entry.getValue().size(); i++) {
                    if (i == entry.getValue().size() - 1) {
                        dataType.append(entry.getValue().get(i).getDataType());
                    } else {
                        dataType.append(entry.getValue().get(i).getDataType()).append(",");
                    }
                }
                oamSubscribe1.setNeId(entry.getKey());
                oamSubscribe1.setCallbackUrl(entry.getValue().get(0).getCallbackUrl());
                oamSubscribe1.setSystemId(entry.getValue().get(0).getSystemId());
                oamSubscribe1.setDataType(dataType.toString());
                oamSubscribeList.add(oamSubscribe1);
            }
        }
        return ResponseFactory.getSuccessData(oamSubscribeList);
    }

}