<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx }/static/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />

<div class="col-lg-8">
		<div class="panel panel-primary dmain">
			<div class="panel-heading dmain-head" style="font-weight: bold;">种子短信模板
				<div style="float: right;">
					<a href="javascript:void(0);" title="添加短信模板" id="addmodelnote" style="font-size: 17px; color: rgb(78, 166, 215);"><span class="glyphicon glyphicon-plus"></span></a>
				</div>
			</div>
			<div class="panel-body" id="caozuonotes">
				
			</div>
			<div class="panel-footer">
			</div>
		</div>
</div>
<div class="col-lg-4">
	<div class="panel panel-info dmain" id="writenotes" style="display: none;">
			<div class="panel-heading dmain-head" style="font-weight: bold;">编辑短信</div>
			<div class="panel-body">
				<div class="form-group">
					<label class="col-sm-2">短信内容:</label>
					<div class="col-sm-8">
						<textarea cols="60%" rows="3" id="notesvalue" placeholder="请输入要存储的短信内容！"></textarea>
					</div>
				</div>
				<br/><br/><br/><br/>
				<div class="form-group">
					<div class="col-sm-4"></div>
					<div class="col-sm-4" style="text-align: center;">
						<button type="button" class="btn btn-warning btn-sm" style="display: none;" id="insertnote">添加</button>
						<button type="button" class="btn btn-warning btn-sm" style="display: none;" id="updatenote">修改</button>
					</div>
			</div>
			</div>
	</div>
</div>	
	