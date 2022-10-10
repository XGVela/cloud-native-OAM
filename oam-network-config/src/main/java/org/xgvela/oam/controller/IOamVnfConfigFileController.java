package org.xgvela.oam.controller;

import org.xgvela.oam.entity.conf.OamVnfConfigFile;
import org.xgvela.oam.entity.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 */
@RestController
@RequestMapping("/ne/config")
@Api(tags = {"OAM-ConfigManager"})
public interface IOamVnfConfigFileController {
    @GetMapping
    @ApiOperation(value = "VnfConfigFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "neId", value = "neId"),
            @ApiImplicitParam(name = "neType", value = "neType"),
            @ApiImplicitParam(name = "vnfName", value = "vnfName")
    })
    Response<List<OamVnfConfigFile>> confInfo(@RequestParam(value = "neId", required = false) String neId, @RequestParam(value = "neType", required = false) String neType, @RequestParam(value = "vnfName", required = false) String vnfName);

    @PostMapping("/confDelivery")
    @ApiOperation(value = "confDelivery")
    Response<Boolean> confDelivery(@RequestBody OamVnfConfigFile.VnfConfigDeliveryRequest request);

    @PostMapping("/switch")
    @ApiOperation(value = "switch")
    Response<Boolean> confSwitch(@RequestBody OamVnfConfigFile.VnfConfigDeliveryRequest request);

    @PostMapping("/confResult")
    @ApiOperation(value = "confResult")
    Response<Boolean> confResult(@RequestBody OamVnfConfigFile.ConfUpdateRequest request);

    @PostMapping("/notifyDownLoadFiles")
    @ApiOperation(value = "notifyDownLoadFiles")
    Response<Boolean> notifyDownLoadFiles(@RequestBody OamVnfConfigFile.Vnf request);

    @PostMapping("/cfgUpdateResultNotify")
    @ApiOperation(value = "cfgUpdateResultNotify")
    Response<Boolean> cfgUpdateResultNotify(@RequestBody OamVnfConfigFile.VnfRequest request);

    @DeleteMapping("/{neId}")
    @ApiOperation(value = "Delete")
    Response<Boolean> deleteFileAndTask(@PathVariable(value = "neId", required = false) String neId);
}