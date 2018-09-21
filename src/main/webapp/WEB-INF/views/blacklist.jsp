<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="panel panel-warning">
		<div class="panel-heading dmain-head" style="font-weight: bold;">短信屏蔽</div>
		<div class="panel-body">
			<div class="col-lg-4">
			<div class="panel panel-info">
				<div class="panel-heading dmain-head" style="font-weight: bold;">屏蔽关键字</div>
				<div class="panel-body">
					<div class="col-sm-12" id="addkeyword"></div>
					<div class="col-sm-12">
						<div class="col-sm-2" style="margin-top: 5px;">
						<a href="javasrcipt:void(0);" title="添加关键字" id="toaddkey" style="text-align: center;font-size: 18px;">
							<span class="glyphicon glyphicon-plus"></span>
						</a>
						</div>
						<div class="col-sm-10" id="heimingdan" style="display: none;">
						<div class="col-sm-6">
							<input type="text" class="form-control" id="keyword">
						</div>
						<div class="col-4">
							<button type="button" class="btn btn-info btn-sm" id="addkeybtn">添加短信关键字</button>
						</div>
						</div>
					
				</div>
			</div>
			</div>
			</div>
			<div class="col-lg-5">
				<div class="panel panel-info">
				<div class="panel-heading dmain-head" style="font-weight: bold;">黑名单号码</div>
				<div class="panel-body">
					<div class="col-sm-12" id="addblacknum">
					</div>	
					<div class="col-sm-12">
						<div class="col-sm-2" style="margin-top: 5px;">
						<a href="javasrcipt:void(0);" title="添加黑名单" id="toaddblacknum" style="text-align: center;font-size: 18px;">
							<span class="glyphicon glyphicon-plus"></span>
						</a>
						</div>
						<div class="col-sm-10" id="huanxinhei" style="display: none;">
						<div class="col-sm-6">
							<input type="text" class="form-control" id="shieldnum">
						</div>
						<div class="col-4">
							<button type="button" class="btn btn-info btn-sm" id="addshbtn">添加短信黑名单</button>
						</div>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
</div>
