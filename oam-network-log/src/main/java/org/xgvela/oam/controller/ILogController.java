package org.xgvela.oam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.inspur.cnet.common.core.entity.response.Response;
import org.xgvela.oam.entity.log.EsLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/logs")
@Api(tags = {"CIM-log"})
public interface ILogController {
    @GetMapping("/list")
    @ApiOperation(value = "search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "current", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "size", defaultValue = "10")
    })
    Response<IPage<EsLog.VO>> list(
            @RequestParam(required = false, defaultValue = "1") Long current,
            @RequestParam(required = false, defaultValue = "10") Long size,
            EsLog.VO.Request entity
    );

    @GetMapping(params = {"operate=export"}, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = "export")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "current", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "size", defaultValue = "10")
    })
    void export(
            @RequestParam(required = false) Long current,
            @RequestParam(required = false) Long size,
            EsLog.VO.Request entity,
            String exportType,
            HttpServletResponse response
    );

    @DeleteMapping
    @ApiOperation(value = "delete")
    Response<Boolean> del(@RequestBody EsLog.VO entity);

    @GetMapping(value = "/kibana")
    @ApiOperation(value = "kibana url")
    Response<String> kibanaUrl();
}
