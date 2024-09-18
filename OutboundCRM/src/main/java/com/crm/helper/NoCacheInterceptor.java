package com.crm.helper;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoCacheInterceptor implements HandlerInterceptor{
	  @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	        response.setDateHeader("Expires", 0); // Proxies
	        return true;
	    }

}
