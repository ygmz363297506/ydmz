<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="col-lg-12">
	<div class="panel panel-info">
		<div class="panel-heading dmain-head" style="font-weight: bold;">联系人管理</div>
		<div class="panel-body">
			<div class="col-sm-4">
			<div class="panel panel-warning">
				<div class="panel-heading dmain-head" style="font-weight: bold;">添加联系人</div>
				<div class="panel-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-3 control-label">联系人名字：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addcontname" placeholder="输入联系人姓名">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">联系人电话：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addcallnum" maxlength="11" placeholder="输入联系人电话">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">群组：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addgroup" placeholder="输入联系人所在群组">
						</div>
						<!-- <div class="col-sm-1" style="margin-top: 7px;"><font style="font-size: 18px;">*</font></div> -->
					</div>
					<div class="form-group">
						<div class="col-sm-8"></div>
						<div class="col-sm-4">
							<button type="button" class="btn btn-info btn-sm" id="insertlinkman">添加联系人</button>
						</div>
					</div>
				</form>			
				</div>
			</div>
			
			
			<div class="panel panel-warning" id="uplinkmodel" style="display: none;">
				<div class="panel-heading dmain-head" style="font-weight: bold;">修改联系人
					<div style="float: right; margin-right: 10px;"><a href="javascript:void(0);" data="uplinkmodel" title="隐藏"><span class="glyphicon glyphicon-remove"></span></a></div>
				</div>
				<div class="panel-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-3 control-label">联系人名字：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="upcontname" placeholder="输入联系人姓名">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">联系人电话：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="upcallnum" maxlength="11" placeholder="输入联系人电话">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">群组：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="upgroup" placeholder="输入联系人所在群组">
						</div>
						<div class="col-sm-1" style="margin-top: 7px;"><font style="font-size: 18px;"></font></div>
					</div>
					<div class="form-group">
						<div class="col-sm-8"></div>
						<div class="col-sm-4">
							<button type="button" class="btn btn-info btn-sm" id="updatelinkman">修改联系人</button>
						</div>
					</div>
				</form>			
				</div>
			</div>
			<div>
			
			</div>
		</div>
		<div class="col-lg-8" id="linkmanall">
			<div class="panel panel-primary">
				<div class="panel-heading dmain-head" style="font-weight: bold;">联系人名单</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-1 control-label">群组:</label>
						<div class=" col-sm-4">
							<input type="text" class="form-control" id="textgroup" placeholder="输入群组名进行查询">
						</div>
						<label class="col-sm-1 control-label">名字:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="textcontname" placeholder="输入电话备注名进行查询">
						</div>
						<div class="col-sm-1">
							<button class="btn btn-primary" type="button" id="slinkman">
								<span class="glyphicon glyphicon-search"></span>搜索
							</button>				
						</div>
					</div>
					</form>
					<table class="table table-hover table-striped tablesorter table-condensed" id="linkmantable"></table>
					<div class="Pages text-center"></div>
					<input type="hidden" id="hidorderby" name="orderby" />
					<input type="hidden" id="groupname" name="group">
					<input type="hidden" id="contname" name="contname">
					<input type="hidden" id="hidorderasc" name="orderasc" />
				</div>
		</div>
		</div>
		</div>
</div>
</div>
