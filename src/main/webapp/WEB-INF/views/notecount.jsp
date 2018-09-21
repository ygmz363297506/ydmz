<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="panel panel-warning">
		<div class="panel-heading dmain-head" style="font-weight: bold;">短信数统计</div>
		<div class="panel-body" id="notecontdiv">
			<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-1 control-label">开始时间:</label>
				<div class=" col-sm-2">
					<input type="text" class="form-control" maxlength="20" id="notestarttime" placeholder="输入开始时间">
				</div>
				<label class="col-sm-1 control-label">结束时间:</label>
				<div class=" col-sm-2">
					<input type="text" class="form-control" maxlength="20" id="noteendtime" placeholder="输入结束时间">
				</div>
				<label class="col-sm-1 control-label">号码:</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="duskeyword" placeholder="输入电话号码">
				</div>
				<div class="col-sm-1">
					<button class="btn btn-primary" type="button" id="dussearch">
						<span class="glyphicon glyphicon-search"></span>搜索
					</button>				
				</div>
			</div>
			</form>
			<table class="table table-hover table-striped tablesorter table-condensed" id="tabnotecount">
				<!-- <thead>
					<tr>
						<td style='font-weight:bold;'>主叫号码</td> 
						<td style='font-weight:bold;'>短信内容</td> 
						<td style='font-weight:bold;'>存入时间</td>
						<td style='font-weight:bold;'>操作</td> 
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>189****1932</td>
						<td>短信内容</td>
						<td>18917607138</td>
						<td></td>
						<td></td>
					</tr>
				</tbody> -->
			</table>
			<div class="Pages text-center"></div>
		</div>
</div>
