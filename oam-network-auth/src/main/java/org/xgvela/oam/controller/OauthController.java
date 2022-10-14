package org.xgvela.oam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import org.xgvela.oam.entity.auth.OamUsers;
import org.xgvela.oam.entity.response.Response;
import org.xgvela.oam.entity.response.ResponseFactory;
import org.xgvela.oam.service.auth.IOamUsersService;

import org.xgvela.oam.annotation.Log;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.xgvela.oam.utils.JwtUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Api(tags = "共享层-认证接口")
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/rest")
public class OauthController {

    private final IOamUsersService oamUsersService;

    private final static int DELAY_TIME = 5; //5分钟

    @Log("用户登录")
    @ApiOperation(value = "获取token")
    @PostMapping("/securityManagement/{apiVersion}/oauth/token")
    public Response<Map<String, Object>> login(@RequestParam(value = "grantType") String grantType,
                                               @RequestParam(value = "userName") String username,
                                               @RequestParam(value = "value") String password,
                                               @PathVariable String apiVersion,
                                               @RequestHeader(value = "Authorization", required = false) String authorization,
                                               HttpServletResponse response) {

        Assert.notNull(username, "用户名不能为空");
        Assert.notNull(password, "密码不能为空");
        // 获取用户密码混淆值
        OamUsers user = oamUsersService.getOne(new LambdaQueryWrapper<OamUsers>().eq(OamUsers::getUserId, username));

        if (null == user) {
            throw new UnknownAccountException("用户不存在");
        }

        if (!password.equals(user.getUserPass())){
            throw new IncorrectCredentialsException("用户名或密码错误");
        }
        return ResponseFactory.getSuccessData("accessToken", JwtUtils.sign(username, password), "expires", 86400000L);
    }

}
