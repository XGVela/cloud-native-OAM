package org.xgvela.oam.controller.subscribe;

import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/jobs/business")
@Api(tags = {" Subscribe alarm performance interface service "})
public interface IOamSubscribeController {

    @PostMapping
    Response<Boolean> add(@RequestBody OamSubscribe oamSubscribe);

    @DeleteMapping("/{systemId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemId", value = "上层网管id", required = true)
    })
    Response<Boolean> del(@PathVariable String systemId);

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemId", value = "上层网管id", required = true)
    })
    Response<List<OamSubscribe>> info(@RequestParam String systemId);

}