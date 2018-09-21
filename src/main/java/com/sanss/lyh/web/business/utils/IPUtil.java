/**
 * File Name: IPUtil.java 
 * Package Name:com.sanss.fptc.web.frame.util 
 * Date:2015年4月27日上午10:08:24 
 * Copyright (c) 2015, hyphantom@gmail.com All Rights Reserved. 
 */
package com.sanss.lyh.web.business.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: IPUtil <br/>
 * Function: 获取真实的ip地址 <br/>
 * Reason: 获取真实的ip地址. <br/>
 * date: 2015年4月27日 上午10:08:24 <br/>
 * 
 * @author phantom洋
 * @version 1.0
 * @since JDK 1.8
 */
public class IPUtil {
	/**
	 * 获取真实的ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	/**
	 * 获得客户端真实IP地址
	 * 
	 * @param request
	 * @return msg : ip & how to get
	 */
	public static String[] getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		String[] msg = { ip, "(from-X-Forwarded-For)" };
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| ip.split("[.]").length != 4) {
			ip = request.getHeader("Proxy-Client-IP");
			msg[0] = ip;
			msg[1] = "(from-Proxy-Client-IP)";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| ip.split("[.]").length != 4) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			msg[0] = ip;
			msg[1] = "(from-WL-Proxy-Client-IP)";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| ip.split("[.]").length != 4) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			msg[0] = ip;
			msg[1] = "(from-HTTP_CLIENT_IP)";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| ip.split("[.]").length != 4) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			msg[0] = ip;
			msg[1] = "(from-HTTP_X_FORWARDED_FOR)";
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)
				|| ip.split("[.]").length != 4) {
			ip = request.getRemoteAddr();
			msg[0] = ip;
			msg[1] = "(from-request.getRemoteAddr())";
		}
		return msg;
	}
}
