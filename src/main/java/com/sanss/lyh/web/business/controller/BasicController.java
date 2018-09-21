package com.sanss.lyh.web.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

public class BasicController {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicController.class);
	
	public static final String SESSION_NAME = "sessionUname";
	
	public static final String SESSION_ID = "sessionforId";
	
	
	// session操作
	// 获取session值
	protected Object getSessionAbbr(HttpServletRequest request, String key) {
		return WebUtils.getSessionAttribute(request, key);
	}
	
	// 往session赋值
	protected void setSessionAbbr(HttpServletRequest request, String key, Object obj) throws Exception{
		WebUtils.setSessionAttribute(request, key, obj);
	}
}
