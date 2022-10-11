package org.xgvela.oam.controller.impl;

import org.xgvela.oam.controller.IOamVnfConfigFileController;
import org.xgvela.oam.entity.conf.OamVnfConfigFile;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.service.IOamVnfConfigFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 */
@RestController
@AllArgsConstructor
public class OamVnfConfigFileControllerImpl implements IOamVnfConfigFileController {
    private final IOamVnfConfigFileService oamVnfConfigFileService;

    @Override
    public Response<List<OamVnfConfigFile>> confInfo(String neId, String neType, String vnfName) {
        return ResponseFactory.getSuccessData(oamVnfConfigFileService.confInfo(neId,neType,vnfName));
    }

    @Override
    public Response<Boolean> confDelivery(OamVnfConfigFile.VnfConfigDeliveryRequest deliveryRequest) {
        return ResponseFactory.getSuccessData(oamVnfConfigFileService.confDelivery(deliveryRequest));
    }

    @Override
    public Response<Boolean> confSwitch(OamVnfConfigFile.VnfConfigDeliveryRequest request) {
        return ResponseFactory.getSuccessData(oamVnfConfigFileService.confSwitch(request));
    }

    @Override
    public Response<Boolean> confResult(OamVnfConfigFile.ConfUpdateRequest confUpdateRequest) {
        return ResponseFactory.getSuccessData(oamVnfConfigFileService.confResult(confUpdateRequest));
    }

    @Override
    public Response<Boolean> notifyDownLoadFiles(OamVnfConfigFile.Vnf request) {
        return ResponseFactory.getSuccessData(oamVnfConfigFileService.notifyDownLoadFiles(request));
    }

    @Override
    public Response<Boolean> cfgUpdateResultNotify(OamVnfConfigFile.VnfRequest request) {
        return ResponseFactory.getSuccessData(oamVnfConfigFileService.cfgUpdateResultNotify(request));
    }

    @Override
    public Response<Boolean> deleteFileAndTask(String id) {
        return ResponseFactory.getSuccessData(oamVnfConfigFileService.deleteFileAndTask(id));
    }
}