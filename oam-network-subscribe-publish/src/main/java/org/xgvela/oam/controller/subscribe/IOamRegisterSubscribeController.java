package org.xgvela.oam.controller.subscribe;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.xgvela.oam.entity.RegisterSubRequest;
import org.xgvela.oam.entity.response.Response;

import java.util.List;

@RestController
@RequestMapping("/system/jobs/register")
@Api(tags = {" Subscribe to NE registration interface services "})
public interface IOamRegisterSubscribeController {

    @PostMapping
    Response<Boolean> add(@RequestBody RegisterSubRequest registerSubRequest);

    @DeleteMapping("/{systemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemId", value = "上层网管id", required = true)
    })
    Response<Boolean> del(@PathVariable String systemId);

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemId", value = "上层网管id", required = true)
    })
    Response<List<RegisterSubRequest>> info(@RequestParam String systemId);

}
