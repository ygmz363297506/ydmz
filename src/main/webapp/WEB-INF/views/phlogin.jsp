<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
<script type="text/javascript" src="${ctx}/static/frame/jquery-2.1.3.min.js"></script> 
<link href="${ctx }/static/frame/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="${ctx }/static/frame/bootstrap-3.3.4/js/bootstrap.min.js"></script>
<script src="${ctx }/static/js/login/login.js"></script>
<script src="${ctx }/static/frame/common.js"></script>
<link href="${ctx}/static/css/login2.css" rel="stylesheet" type="text/css" />
</head>

<body>
<h1>上海电信翼动拇指平台<sup>V2016</sup></h1>
<h3 style="color: red;">${msg }</h3>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch">
        	<a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<!-- <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a> -->
			<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 270px;">    

            <!--登录-->
            <div class="web_login" id="web_login">
               
               
               <div class="login-box">
    
            
			<div class="login_form">
				<form action="${ctx }/loginvm" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post"><input type="hidden" name="did" value="0"/>
               <input type="hidden" name="to" value="log"/>
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">手机号码：</label>
                <div class="inputOuter" id="uArea">
                    <input type="text" id="phonenumber" name="phonenumber"  class="inputstyle"/>
                </div>
                </div>
               <div class="codeArea" id="codeArea">
                <label class="input-tips" for="c">验证码：</label>
                <div class="inputOuter" id="cArea">
                    <input type="text" id="code" name="code" maxlength="4" class="codestyle" style="width: 75px;" placeholder="验证码"/>
                   	<img id="imgObj"  alt="验证码" src="${ctx }/code" onclick="changeImg()"/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
                    <input type="text" class="phonestyle" id="phonecode" maxlength="6" name="phonecode" style="width: 120px;" placeholder="短信验证码"/>
                    <input type="button" id="gaincode" class="button_green" value=" 获 取 验 证 码 ">
                </div> 
                <div style="padding-left:50px;margin-top:20px;">
                	<input type="button" value="登 录" style="width:150px;" id="loginto" class="button_blue"/>
                </div>
              </form>
           </div>
            	</div>
            </div>
            <!--登录end-->
  </div>
</div>
			<div style="display: none">
				<button class="btn btn-success" type="button" id="hidebtn" data-toggle="modal" data-target="#myModal">显示浮动框</button>
			</div>
<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">用户个人信息保护公告</h4>
						</div>
						<div class="modal-body">
							<img src="${ctx }/static/images/showgeren.jpg" width="100%px">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
<!-- <div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div> -->
</body>
<!-- </body> -->
<!-- </html> -->