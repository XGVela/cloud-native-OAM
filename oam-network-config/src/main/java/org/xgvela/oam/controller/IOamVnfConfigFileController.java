package org.xgvela.oam.controller;

import org.xgvela.oam.entity.conf.OamVnfConfigFile;
import org.xgvela.oam.entity.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ne/config")
@Api(tags = {"OAM- configuration management "})
public interface IOamVnfConfigFileController {
    @GetMapping
    @ApiOperation(value = "config query ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "neId", value = "NE ID"),
            @ApiImplicitParam(name = "neType", value = "NE type "),
            @ApiImplicitParam(name = "vnfName", value = "NE name ")
    })
    Response<List<OamVnfConfigFile>> confInfo(@RequestParam(value = "neId", required = false) String neId, @RequestParam(value = "neType", required = false) String neType, @RequestParam(value = "vnfName", required = false) String vnfName);

    @PostMapping("/confDelivery")
    @ApiOperation(value = "Configuration delivered ")
    Response<Boolean> confDelivery(@RequestBody OamVnfConfigFile.VnfConfigDeliveryRequest request);

    @PostMapping("/switch")
    @ApiOperation(value = "Configuration version switch ")
    Response<Boolean> confSwitch(@RequestBody OamVnfConfigFile.VnfConfigDeliveryRequest request);

    @PostMapping("/confResult")
    @ApiOperation(value = "Report configuration update result ")
    Response<Boolean> confResult(@RequestBody OamVnfConfigFile.ConfUpdateRequest request);

    @PostMapping("/notifyDownLoadFiles")
    @ApiOperation(value = "tube tells config to download configuration files ")
    Response<Boolean> notifyDownLoadFiles(@RequestBody OamVnfConfigFile.Vnf request);

    @PostMapping("/cfgUpdateResultNotify")
    @ApiOperation(value = "agent notifies config of configuration update result ")
    Response<Boolean> cfgUpdateResultNotify(@RequestBody OamVnfConfigFile.VnfRequest request);

    @DeleteMapping("/{neId}")
    @ApiOperation(value = "Clear configuration data ")
    Response<Boolean> deleteFileAndTask(@PathVariable(value = "neId", required = false) String neId);
}