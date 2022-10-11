package org.xgvela.oam.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * acquire HttpServletRequest
 * 
 * @author gengfei01
 */
public class RequestHolder {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String parseUserId(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		String userId = JwtUtils.getUserId(authorization);
		return String.valueOf(userId);
	}

	public static String parseUserName(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		String userName = JwtUtils.getUserId(authorization);
		return userName;
	}
}
