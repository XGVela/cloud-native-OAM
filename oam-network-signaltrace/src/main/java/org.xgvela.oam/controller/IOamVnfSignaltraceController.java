package org.xgvela.oam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.signaltrace.OamVnfSignaltrace;


@RestController
@RequestMapping("/oam-vnf-signaltraces")
@Api(tags = {"signaltraces"})
public interface IOamVnfSignaltraceController {

    @PostMapping("/createTask")
    @ApiOperation(value = "createTask")
    Response<OamVnfSignaltrace> createTask(@RequestBody OamVnfSignaltrace oamVnfSignaltrace);
}