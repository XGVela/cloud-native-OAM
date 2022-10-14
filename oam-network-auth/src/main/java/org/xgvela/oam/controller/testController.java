package org.xgvela.oam.controller;

import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "oam鉴权-测试")
@RestController
@RequestMapping(value = "api")
public class testController {

    @GetMapping("/status")
    @ApiOperation(value = "状态")
    public Response status() {
        return ResponseFactory.getSuccess();
    }
}
