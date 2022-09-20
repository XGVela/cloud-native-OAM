package org.xgvela.oam.config;

import com.alibaba.fastjson.JSON;
import com.inspur.cnet.common.core.entity.response.ResponseEnum;
import com.inspur.cnet.common.core.entity.response.ResponseFactory;
import com.inspur.cnet.common.core.exception.ServiceException;
import com.inspur.cnet.security.entity.JWTToken;
import com.inspur.cnet.security.service.utils.SecurityUtils;
import org.xgvela.oam.entity.log.OmcSecurityLog;
import org.xgvela.oam.mapper.log.OmcSecurityLogMapper;
import org.xgvela.oam.utils.SpringUtils;
import org.xgvela.oam.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private long currentTime = 0L;

    @Autowired
    private OmcSecurityLogMapper seLogMapper;

    /**
     * check user is login
     * header contain Authorization or not
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {

        currentTime = System.currentTimeMillis();

        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();
        if (url.contains("/v1/meps")) {
            String requestToken = req.getHeader("X-Auth-Token");
            return requestToken != null;
        } else {
            String authorization = req.getHeader(SecurityUtils.REQUEST_AUTH_HEADER);
            return authorization != null;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI();

        String header = req.getHeader(SecurityUtils.REQUEST_AUTH_HEADER);
        JWTToken token = new JWTToken(header);
        getSubject(request, response).login(token);
        // handle multi pod issue
        Subject currentUser = org.apache.shiro.SecurityUtils.getSubject();
        try {
            Session session = currentUser.getSession();
            Object user = session.getAttribute("user");
            if(!WebUtils.HEADER_VALUE_AUTH.equals(header) && user==null) {
                Long currentUserId = SecurityUtils.getCurrentUserId();
                //boolean b = SpringUtils.getBean(OmcUserActiveServiceImpl.class).removeById(currentUserId);
                //log.info(MessageFormat.format("delete login user:{0}:{1}", currentUserId, b));
                currentUser.logout();
                //new AuthController().timeout();
                return false;
            }
        } catch (Exception e) {
            log.info("The user has automatically exited and the request is no longer responding.");
            return false;
        }

        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!isLoginAttempt(request, response)) {
            response401(request, response);
            return false;
        }
        boolean checkToken;
        try {
            checkToken = executeLogin(request, response);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        if (!checkToken) {
            response401(request, response);
            return false;
        }
        return true;
    }

    /**
     * 401 error code
     */
    private void response401(ServletRequest request, ServletResponse resp) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;

        OmcSecurityLog seLog = OmcSecurityLog.builder().createTime(new Date())
                .action("auth").description("No permissions").exceptionName("No permissions")
                .logType("ERROR").method(httpServletRequest.getRequestURI())
                .params(httpServletRequest.getQueryString())
                .requestIp(getCallIp()).status("ERROR")
                .time(System.currentTimeMillis() - currentTime).build();

        try {

            out = httpServletResponse.getWriter();
            out.append(JSON.toJSONString(ResponseFactory.getError(ResponseEnum.please_login)));
        } catch (IOException e) {
            log.error("Response IOException:" + e.getMessage());
            seLog.setExceptionDetail(e.getMessage());
            throw new ServiceException(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
        try {
            if (!seLog.getMethod().contains("swagger-resources")) {
                if (seLogMapper == null) {
                    seLogMapper = SpringUtils.getBean(OmcSecurityLogMapper.class);
                }
                seLogMapper.insert(seLog);
            }
        } catch (Exception e) {
            log.error("Security log save failed", e);
        }

    }

    /**
     * preHandle
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    private String getCallIp() {
        HttpServletRequest request = WebUtils.getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}
