<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="headertop">
<sec:authentication property="name" var="sname" />
	<div class="wrapper clearfix">
		<div class="logo">
			<img src="${ctx}/static/images/logo.png" alt="">
			<font style="font-size: 25px;">翼动拇指系统</font>
		</div>
		
</div>
</div>
