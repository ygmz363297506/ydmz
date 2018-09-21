<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="panel panel-warning">
		<div class="panel-heading dmain-head" style="font-weight: bold;">垃圾箱</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-1 control-label">黑名单号码:</label>
				<div class=" col-sm-4">
					<input type="text" class="form-control" maxlength="20" id="dustbinnum" placeholder="输入黑名单号码进行查询">
				</div>
				<label class="col-sm-1 control-label">关键字:</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="duskeyword" placeholder="输入含短信关键字进行查询">
				</div>
				<div class="col-sm-1">
					<button class="btn btn-primary" type="button" id="dussearch">
						<span class="glyphicon glyphicon-search"></span>搜索
					</button>				
				</div>
			</div>
			</form>
			<table class="table table-hover table-striped tablesorter table-condensed" id="dustbintable"></table>
			<div class="Pages text-center"></div>
			<input type="hidden" id="hidorderby" name="orderby" />
			<input type="hidden" id="dustbincalled" name="called">
			<input type="hidden" id="dustbinkey" name="keyword">
			<input type="hidden" id="hidorderasc" name="orderasc" />
		</div>
</div>
