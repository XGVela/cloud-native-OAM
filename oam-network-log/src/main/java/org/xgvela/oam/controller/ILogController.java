package org.xgvela.oam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.xgvela.oam.entity.log.EsLog;
import org.xgvela.oam.entity.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/logs")
@Api(tags = {"OAM-LOG"})
public interface ILogController {

    @GetMapping("/list")
    @ApiOperation(value = "query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", defaultValue = "10")
    })
    Response<IPage<EsLog.VO.EsMessageVO>> list(
            @RequestParam(required = false, defaultValue = "1") Long pageNum,
            @RequestParam(required = false, defaultValue = "10") Long pageSize,
            EsLog.VO.Request entity
    );

    @GetMapping(params = {"operate=export"}, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ApiOperation(value = "export")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", defaultValue = "10")
    })
    void export(
            @RequestParam(required = false) Long pageNum,
            @RequestParam(required = false) Long pageSize,
            EsLog.VO.Request entity,
            String exportType,
            HttpServletResponse response
    );

    @DeleteMapping
    @ApiOperation(value = "delete")
    Response<Boolean> del(@RequestBody EsLog.VO.Request entity);

    @GetMapping(value = "/kibana")
    @ApiOperation(value = "kibana url")
    Response<String> kibanaUrl();
}
