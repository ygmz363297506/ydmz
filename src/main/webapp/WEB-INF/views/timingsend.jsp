<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx }/static/css/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
<div class="col-lg-12">
	<div class="panel panel-info">
		<div class="panel-heading dmain-head" style="font-weight: bold;">定时短信</div>
		<div class="panel-body">
			<div class="col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading dmain-head" style="font-weight: bold;">发送记录</div>
					<div class="panel-body" id="pageoftimg">
						<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-1 control-label">被叫号码:</label>
							<div class=" col-sm-4">
								<input type="text" class="form-control" maxlength="11" id="timgtextnum" placeholder="输入被叫号码进行查询">
							</div>
							<label class="col-sm-1 control-label">短信内容:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="timgnotetext" placeholder="输入含短信关键字进行查询，例如：‘话费’">
							</div>
							<div class="col-sm-1">
								<button class="btn btn-primary" type="button" id="timgsearch">
									<span class="glyphicon glyphicon-search"></span>搜索
								</button>				
							</div>	
							<div class="col-sm-1">
								<button class="btn btn-warning" type="button" id="addtimgnote">设置定时短信</button>
							</div>
						</div>
						</form>
						<table class="table table-hover table-striped tablesorter table-condensed" id="timgmaintable"></table>
						<div class="Pages text-center"></div>
						<input type="hidden" id="hidorderby" name="orderby" />
						<input type="hidden" id="timgsendcalled" name="called">
						<input type="hidden" id="timgsendnotet" name="text">
						<input type="hidden" id="hidorderasc" name="orderasc" />
					</div>
				</div>
			</div>
			<div id="settimgnote" style="display: none;">
			<div class="col-sm-8">
					<div class="panel panel-primary dmain">
						<div class="panel-heading dmain-head" style="font-weight: bold;">定时短信</div>
						<div class="panel-body">
							<form class="form-horizontal" role="form">
							<input type="hidden" id="hidtimelinkman" name="alllinkmans">
							<input type="hidden" id="hidtimenotes" name="text">
							<input type="hidden" id="sendtimes" name="sendtime">
							<div class="form-group">
								<label class="col-sm-2 control-label">接收人:</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="allsendmans">
									<!-- <div class="form-control" contentEditable="true" id="allsendmans">
									</div> -->
								</div>
								<div class="col-sm-2">
									<a href="javascript:void(0)" title="清空接收人" id="removelinkmans"><span style="padding-top: 10px;" class="glyphicon glyphicon-remove"></span></a>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2">
									<label class="control-labe" style="float: right;">短信内容:</label>
								</div>
								<div class="col-sm-8">
									<textarea rows="3" cols="1" class="form-control" id="timeingnotes" maxlength="100"></textarea>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn-default btn-sm" data="1" id="modelnotest">种子模板</button>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">发送时间:</label>
									<div class="col-sm-8">
										<input type="text" id="datetimepicker" class="form-control">
									</div>
							</div>
							</form>
						</div>
						<div class="panel-footer">
								<button type="button" class="btn btn-success" style="margin-left: 90%;" id="totimesendnote">发送</button>
						</div>
					</div>
			</div>
		
			<div class="col-sm-4">
			<div class="panel panel-primary dmain">
				<div class="panel-heading dmain-head" style="font-weight: bold;">联系人</div>
				<div class="panel-body">
					<table class="table table-hover table-striped tablesorter table-condensed" id="maintable">
					<thead>
						<tr>
							<td id="linkname">名字</th>
							<td id="callednum">号码</th>
							<td id="grouplist">群组</th>
							<td></td>
						</tr>
					</thead>
					<tbody id="timinglinkman"></tbody>
					</table>
					
					<div style="float: right; margin-right: 20px; margin-top: 10px;">
						<button class="btn btn-success btn-sm" title="确认添加" id="linkmaninsert">添加接收人</button>
					</div>
				</div>
					
			</div>
			</div>
			</div>
			<div class="col-sm-8" id="templatenotestime" style="display: none;">
				<div class="panel panel-primary dmain">
					<div class="panel-heading dmain-head" style="font-weight: bold;">种子短信模板</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" id="timingnotmodel">
						</form>
					</div>
					<div class="panel-footer">
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="${ctx }/static/css/jquery.js"></script>
	<script src="${ctx }/static/build/jquery.datetimepicker.full.js"></script>
<script>
$.datetimepicker.setLocale('en');
var myDate = new Date();
//获取当前年
var year=myDate.getFullYear();
//获取当前月
var month=myDate.getMonth()+1;
//获取当前日
var date=myDate.getDate(); 

var h=myDate.getHours();       //获取当前小时数(0-23)
var m=myDate.getMinutes();     //获取当前分钟数(0-59)
var s=myDate.getSeconds();
var nowtime=year+'-'+month+'-'+date+' '+h+':'+m+":"+s
$('#datetimepicker').datetimepicker({
dayOfWeekStart : 1,
lang:'en',
disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
startDate:	''+nowtime
});
$('#datetimepicker').datetimepicker({value:''+nowtime,step:10});

$('.some_class').datetimepicker();

</script>