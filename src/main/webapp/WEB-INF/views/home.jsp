<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<html>
<html lang="zh-CN">
<head>
	<title>翼动拇指大厅</title>
	 <%@include file="../static/frame/inc/common.inc"%> 
	<link rel="shortcut icon" href="${ctx}/static/images/favicon.ico" /> 
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<script src="${ctx }/static/js/pagediv.js"></script>
	<script src="${ctx }/static/js/home/index.js"></script>
	<link rel="stylesheet" href="${ctx }/static/css/kingTable.css">
</head>
<body>
<h2>翼动拇指大厅</h2>
<div style="float: right; margin-right: 30px;">
			当前用户:<font style="color: red">${username }</font>
			&nbsp;&nbsp;&nbsp;
			<%-- <c:if test="${username !='agent' }">
			<a href="javascript:void()" style="padding-right: 20px;" id="updatepwd">修改密码</a>
			</c:if> --%>
			<a href="${ctx }/drop" id="drop">退出</a>
		</div>
	<br/><br/>
	<!-- 菜单！ -->
	<div class="list-group">
		<ul class="nav nav-tabs" id="menulist">
			<li class="active"><a href="javascript:void(0);" class="list-group-item" onclick=querylinkmanofone()>自写短信</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=query()>发送记录</a></li>
			<li><a href="javascript:void(0);" class="list-group-item">联系人管理</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=querytimingnote();>定时短信</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=querymodel();>种子短信</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=queryblacklist();>短信屏蔽</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=receverquery();>无卡短信接收</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=querybackup();>短信云备份</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=querydusbin();>垃圾箱</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=querynumcount();>号码统计</a></li>
			<li><a href="javascript:void(0);" class="list-group-item" onclick=querynotecount();>短信统计</a></li> 
		</ul>
	</div>
	<div id="notesbody">
	<div class="col-lg-12" id="zixienote" name="自写短信"  data="mdl">
	<div class="panel panel-primary dmain">
	<div class="panel-body">
	<div class="col-lg-8">
		<div class="panel panel-info dmain">
			<div class="panel-heading dmain-head" style="font-weight: bold;">短信发送</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form">
				<div class="form-group">
					<label class="col-sm-1 control-label">接收人:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="alltosendman">
						<input type="hidden" id="hiddenlinkman" name="alllinkmans">
						<input type="hidden" id="hiddennotes" name="text">
					</div>
					<div class="col-sm-1">
						<a href="javascript:void(0)" title="清空接收人" id="removelinkman"><span style="padding-top: 10px;" class="glyphicon glyphicon-remove"></span></a>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-1 control-label">短信内容:</label>
					<div class="col-sm-10">
						<textarea rows="3" cols="1" class="form-control" id="notes" maxlength="100"></textarea>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default btn-sm" data="1" id="modelnotes">种子模板</button>
					</div>
				</div>
				
				</form>
			</div>
			<div class="panel-footer">
					<button type="button" class="btn btn-success" style="margin-left: 90%;" id="tosendnote">发送</button>
			</div>
		</div>
		
		<div id="templatenotes" style="display: none;">
		<div class="panel panel-primary dmain">
			<div class="panel-heading dmain-head" style="font-weight: bold;">种子短信模板</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="formnotemodel">
				</form>
			</div>
			<div class="panel-footer">
			</div>
		</div>
		</div>
		
		<div class="panel panel-primary dmain">
			<div class="panel-heading dmain-head" style="font-weight: bold;">增值业务订购</div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" id="asd">
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="天翼空间"><img src="${ctx }/static/images/1.png" width="100%"></a>
					</div>
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="来电助理"><img src="${ctx }/static/images/2.png" width="100%"></a>
					</div>
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="七彩铃声"><img src="${ctx }/static/images/3.png" width="100%"></a>
					</div>
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="爱英语高级会员"><img src="${ctx }/static/images/4.png" width="100%"></a>
					</div>
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="WIFI动态密码"><img src="${ctx }/static/images/5.png" width="100%"></a>
					</div>
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="爱游戏"><img src="${ctx }/static/images/6.png" width="100%"></a>
					</div>
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="天翼阅读"><img src="${ctx }/static/images/7.png" width="100%"></a>
					</div>
					<div class="col-sm-3">
						<a href="http://service.sh.189.cn/service/prodeal/product" title="天翼视讯"><img src="${ctx }/static/images/8.png" width="100%"></a>
					</div>
				</form>
			</div>
			<div class="panel-footer">
			</div>
		</div>
		
	</div>
	
	
	
	<div class="col-lg-4">
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
				<tbody id="oneselflinkman"></tbody>
				</table>
				<!-- <input type="checkbox" name="linkmansss" id="asd">
				<input type="text" id="testsss"> -->
				<div style="float: right; margin-right: 20px; margin-top: 10px;">
					<button class="btn btn-success btn-sm" title="确认添加" id="linkmaninsert">添加接收人</button>
				</div>
			</div>
				
		</div>
	</div>
	</div>
</div>
	</div>
		<div class="col-lg-12" id="sendrecord" name="发送记录" data="mdl" style="display: none;">
			<jsp:include page="sendrecord.jsp" />
		</div>
		<div class="col-lg-12" id="linkman" name="联系人管理" data="mdl" style="display: none;">
			<jsp:include page="linkman.jsp" />
		</div>
		<div class="col-lg-12" id="timingnote" name="定时短信" data="mdl" style="display: none">
		 	<jsp:include page="timingsend.jsp"/>
		</div>
		<div class="col-lg-12" id="modelsnote" name="种子短信" data="mdl" style="display: none">
			<jsp:include page="modelsnots.jsp"/>
		</div>
		<div class="col-lg-12" id="shieldnote" name="短信屏蔽" data="mdl" style="display: none">
			<jsp:include page="blacklist.jsp"/>
		</div>
		<div class="col-lg-12" id="receivenote" name="无卡短信接收" data="mdl" style="display: none">
			<jsp:include page="receivenotes.jsp"/>
		</div>
		<div class="col-lg-12" id="backupnotes" name="短信云备份" data="mdl" style="display: none">
			<jsp:include page="backup.jsp"/>
		</div>
		<div class="col-lg-12" id="dustbin" name="垃圾箱" data="mdl" style="display: none">
			<jsp:include page="dustbin.jsp" />
		</div>
		<div class="col-lg-12" id="numcout" name="号码统计" data="mdl" style="display: none">
			<jsp:include page="numcount.jsp" />
		</div>
		<div class="col-lg-12" id="notecount" name="短信统计" data="mdl" style="display: none">
			<jsp:include page="notecount.jsp" /> 
		</div>
		
</div>
	<div style="width: 100%;height: 50px;position: fixed;bottom: 0; background: #00A2EA;text-align: center;">
	<p>Copyright &copy;1996-2014&nbsp;&nbsp;&nbsp;&nbsp;Corporation,All Rights Reserved &nbsp; &nbsp;隐私保护 上海电信公司 版权所有
	 网站备案号 沪ICP备10210777号</p>
	</div>
</body>
</html>
