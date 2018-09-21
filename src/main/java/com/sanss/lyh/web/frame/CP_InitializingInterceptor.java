/**
 * File Name:CP_InitializingInterceptor.java 
 * Package Name:com.sanss.fptc.web.frame.web.core 
 * Date:2015年3月17日下午2:33:15 
 * Copyright (c) 2015, hyphantom@gmail.com All Rights Reserved. 
 */
package com.sanss.lyh.web.frame;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * ClassName: CP_InitializingInterceptor <br/>
 * date: 2015年3月17日 下午2:33:15 <br/>
 * 
 * @author phantom洋
 * @version 1.0
 * @since JDK 1.8
 */
public class CP_InitializingInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(CP_InitializingInterceptor.class);
	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// log追加：用户名 - sessionID - IP - URL - 请求参数
		StringBuffer log = new StringBuffer();
		String url = request.getServletPath();
//		String ip = IPUtil.getRemoteHost(request);
//		log.append(" - ip:").append(ip);
		log.append(" - path:").append(url);
		if (request.getQueryString() != null) {
			log.append(" - param(get):").append(request.getQueryString()).append(" - ");
		} else {
			Map<String, String[]> parameters = request.getParameterMap();
			if (parameters.size() != 0) {
				log.append(" - param(post):[");
			}
			for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				// if("random".equals(key)){
				// if(value!=null&&!value.equals(session.getAttribute("random"))){
				// return false;
				// }
				// }
				String message = "";
				if (value.getClass().isArray()) {
					Object[] args = (Object[]) value;
					message = " " + key + "=" + Arrays.toString(args) + " ";
				} else {
					message = key + "=" + (String) value + " ";
				}
				log.append(message);
			}
			if (parameters.size() != 0) {
				log.append("]");
			}
		}
//		log.append(" - sessionid:").append(request.getSession().getId());
//		logger.info(log.toString());
//		if (url != null && !"".equals(url) && !"/".equals(url) && !url.equals("/query")) {
//			String user=(String)session.getAttribute("loginuser");
//			logger.debug("CP_InitializingInterceptor:"+session.getAttributeNames());
//				if(user == null){
//					request.getRequestDispatcher("/login").forward(request,
//							 response);
//					return false;
//				}else{
//					return true;
//				}
//		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("_contextPath", request.getContextPath());
		String serverName = "http://" + request.getServerName();
		String serverPort = ":" + request.getServerPort();
		String httpPath = serverName + serverPort;
		request.setAttribute("_serverPath", httpPath);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}