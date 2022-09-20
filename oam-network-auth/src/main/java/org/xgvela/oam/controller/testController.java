package org.xgvela.oam.controller;


import com.inspur.cnet.common.core.entity.response.Response;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "oam auth-test")
@RestController
@RequestMapping(value = "api")
public class testController {

    @GetMapping("/status")
    @ApiOperation(value = "status")
    public Response status() {
        return ResponseFactory.getSuccess();
    }
}
