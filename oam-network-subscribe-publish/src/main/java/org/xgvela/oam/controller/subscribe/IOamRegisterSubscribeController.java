package org.xgvela.oam.controller.subscribe;

import com.inspur.cnet.common.core.entity.response.Response;
import org.xgvela.oam.entity.RegisterSubRequest;
import org.xgvela.oam.entity.subscribe.OamSubscribe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/jobs/register")
@Api(tags = {"Subscribe nf register interface"})
public interface IOamRegisterSubscribeController {

    @PostMapping
    @ApiOperation(value = "add Subscribe")
    Response<Boolean> add(@RequestBody RegisterSubRequest registerSubRequest);

    @DeleteMapping("/{systemId}")
    @ApiOperation(value = "del Subscribe")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemId", value = "omc id", required = true)
    })
    Response<Boolean> del(@PathVariable String systemId);

    @GetMapping
    @ApiOperation(value = "search Subscribe")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemId", value = "omc id", required = true)
    })
    Response<List<RegisterSubRequest>> info(@RequestParam String systemId);

}
