<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${ctx}">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 控制浏览器选择webkit内核渲染 -->
<meta name="renderer" content="webkit">
<!-- 针对手持设备优化，主要是针对一些老的不识别viewport的浏览器 -->
<meta name="HandheldFriendly" content="true">
<!-- 启用 WebApp 全屏模式 -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- 隐藏状态栏/设置状态栏颜色 -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<!-- 添加到主屏后的标题 -->
<meta name="apple-mobile-web-app-title" content="能力快速提供平台">
<title>翼动拇指系统<sitemesh:write property='title' /></title>
<%@include file="../static/frame/inc/common.inc"%>
<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" />
<sitemesh:write property='head' />
<script type="text/javascript">
	$(function() {
		/* $(".bannerimg").YEXfocus({
			direction : 'top'
		}); */
		$('#main').css({
			height : document.documentElement.clientHeight * 0.8,
			width : document.documentElement.clientWidth
		});
	});
</script>
</head>
<body>
	 <%@ include file="head.jsp"%>
	<div class="container-fluid">
		<div class="main" id="content">
			<sitemesh:write property='body' />
		</div>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html> 