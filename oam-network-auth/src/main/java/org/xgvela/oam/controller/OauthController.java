package org.xgvela.oam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import net.bytebuddy.implementation.bytecode.Throw;
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
@Api(tags = "Shared layer - authentication interface ")
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/rest")
public class OauthController {

    private final IOamUsersService oamUsersService;

    private final static int DELAY_TIME = 5; // for 5 minutes

    @Log(" User login ")
    @ApiOperation(value = "get token")
    @PostMapping("/securityManagement/{apiVersion}/oauth/token")
    public Response<Map<String, Object>> login(@RequestParam(value = "grantType") String grantType,
                                               @RequestParam(value = "userName") String username,
                                               @RequestParam(value = "value") String password,
                                               @PathVariable String apiVersion,
                                               @RequestHeader(value = "Authorization", required = false) String authorization,
                                               HttpServletResponse response) {

        Assert.notNull(username, "the username cannot be empty ");
        Assert.notNull(password, "password cannot be empty ");
      // Get the user password confusion value
        OamUsers user = oamUsersService.getOne(new LambdaQueryWrapper<OamUsers>().eq(OamUsers::getUserId, username));

        if (null == user) {
            throw new UnknownAccountException(" User does not exist ");
        }

        if (! password.equals(user.getUserPass())){
            throw new IncorrectCredentialsException (" user name or password error ");
        }
        return ResponseFactory.getSuccessData("accessToken", JwtUtils.sign(username, password), "expires", 86400000L);
    }

}