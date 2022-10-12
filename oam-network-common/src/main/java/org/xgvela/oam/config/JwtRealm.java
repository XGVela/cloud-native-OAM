package org.xgvela.oam.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.xgvela.oam.entity.auth.OamUsers;
import org.xgvela.oam.entity.security.JWTToken;
import org.xgvela.oam.service.auth.IOamUsersService;
import org.xgvela.oam.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class JwtRealm extends AuthenticatingRealm {

    @Autowired
    private IOamUsersService oamUsersService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        String token = (String)authcToken.getCredentials();
        String userId = JwtUtils.getUserId(token);
        OamUsers user = oamUsersService.getOne(new LambdaQueryWrapper<OamUsers>().eq(OamUsers::getUserId, userId));
        if (Objects.nonNull(user)) {
            if (!JwtUtils.verify(token, user.getUserId(), user.getUserPass())) {
                throw new UnauthorizedException("用户名或密码错误");
            }
        } else {
            throw new UnknownAccountException("用户不存在");
        }
        return new SimpleAuthenticationInfo(token, token, "JwtRealm");
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
}
