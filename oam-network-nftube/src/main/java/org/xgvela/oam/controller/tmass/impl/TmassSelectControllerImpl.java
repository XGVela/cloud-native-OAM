package org.xgvela.oam.controller.tmass.impl;

import org.xgvela.oam.controller.tmass.ITmassSelectController;
import org.xgvela.oam.entity.response.RestResponse;
import org.xgvela.oam.entity.response.ResultCode;
import org.xgvela.oam.service.tmass.ITmassSelectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TmassSelectControllerImpl implements ITmassSelectController {

    @Autowired
    private ITmassSelectService iTmassSelectService;

    @Override
    public RestResponse<Object> getNfResouce(String neType, String neId) {
        Object tmassNfResouce = iTmassSelectService.getNfResourceFromTmassPlatform(neType, neId);
        RestResponse<Object> restResponse = RestResponse.builder().data(tmassNfResouce).status(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMessage()).build();
        return restResponse;
    }
}
