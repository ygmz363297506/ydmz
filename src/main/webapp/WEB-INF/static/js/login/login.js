$(function(){
	$("#hidebtn").click();
	$('body').on('click','#loginto',function(){
//		var username=$("#username").val();
//		var passwrod=$("#password").val();
//		var code=$("#code").val();
//		if(username == "" || username == undefined || username == null){
//			alert("请填写用户名！");
//			return;
//		}else if(passwrod == "" || passwrod == undefined || passwrod == null){
//			alert("请填写密码！");
//			return;
//		}else if(code == "" || code == undefined || code == null){
//			alert("请填写验证码！");
//			return;
//		}else{
//			$("#login_form").submit();
//		}
		
		var phonenumber=$("#phonenumber").val();
		var code=$("#code").val();
		var phonecode=$("#phonecode").val();
		if(phonenumber == "" || phonenumber == undefined || phonenumber == null){
			alert("请填写手机号码！");
			return false;
		}else if(code == "" || code == undefined || code == null){
			alert("请填写验证码!");
			return false;
		}else if(phonecode == "" || phonecode == undefined || phonecode == null){
			alert("请填写短信验证码!");
		}else{
			
			$.ajax({
				type : "POST",
				url : getRootPath()+"/login",
				data :{
					"phonenumber" : phonenumber,
					"phonecode" : phonecode
				},
				dataType : 'json',
				success : function(data){
					if(data.msg!=null && data.msg!=""){
						alert(data.msg);
					}else{
						//验证成功
						$("#login_form").submit();
					}
				}
			})
		}
	});
	
	$("body").on('click', '#gaincode', function() {
		var count = 1;
		var obj = document.getElementById("gaincode");
		var phone = $("#phonenumber").val();
		var code = $("#code").val();
		if (phone == "" || phone == null || phone == undefined) {
			alert("手机号码不能为空！");
			return;
		} else {
			if (phone.length != 11) {
				alert("电话格式错误");
				return;
			}
			var sum = 60;

			$.ajax({
				type : "GET",
				url : getRootPath() + "/sendcode",
				data : {
					"phonenumber" : phone,
					"code" : code
				},
				dataType : "json",
				success : function(data) {
					if(data.msg !=null && data.msg!=""){
						alert(data.msg);
					}else{
						var i = setInterval(function() {
							if (count > 59) {
								obj.disabled = undefined;
								obj.value = '重新获取验证码';
								clearInterval(i);
							} else {
								obj.disabled = 'false';
								obj.value = ' ' + parseInt(sum - count) + '秒 后  ';
							}
							count++;
						}, 1000);
					}
				}
			});
		}
	});
	
	
});

function chgUrl(url) {
	var timestamp = (new Date()).valueOf();
	/* url = url.substring(0, 17); */
	if ((url.indexOf("&") >= 0)) {
		url = url + "×tamp=" + timestamp;
	} else {
		url = url + "?timestamp=" + timestamp;
	}
	return url;
}

function changeImg() {
	var imgSrc=document.getElementById("imgObj"); 
	var src = imgSrc.src;
	imgSrc.src=chgUrl(src);
}











//	$('#switch_qlogin').click(function(){
//		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
//		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
//		$('#switch_bottom').animate({left:'0px',width:'70px'});
//		$('#qlogin').css('display','none');
//		$('#web_qr_login').css('display','block');
//		
//		});
//	$('#switch_login').click(function(){
//		
//		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
//		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
//		$('#switch_bottom').animate({left:'154px',width:'70px'});
//		
//		$('#qlogin').css('display','block');
//		$('#web_qr_login').css('display','none');
//		});
//	
	
//		
//	
//if(getParam("a")=='0')
//{
//	$('#switch_login').trigger('click');
//}
//
//	});
//	
//function logintab(){
//	scrollTo(0);
//	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
//	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
//	$('#switch_bottom').animate({left:'154px',width:'96px'});
//	$('#qlogin').css('display','none');
//	$('#web_qr_login').css('display','block');
//	
//}
//
//
////根据参数名获得该参数 pname等于想要的参数名 
//function getParam(pname) { 
//    var params = location.search.substr(1); // 获取参数 平且去掉？ 
//    var ArrParam = params.split('&'); 
//    if (ArrParam.length == 1) { 
//        //只有一个参数的情况 
//        return params.split('=')[1]; 
//    } 
//    else { 
//         //多个参数参数的情况 
//        for (var i = 0; i < ArrParam.length; i++) { 
//            if (ArrParam[i].split('=')[0] == pname) { 
//                return ArrParam[i].split('=')[1]; 
//            } 
//        } 
//    } 
//}  
//
//
//var reMethod = "GET",
//	pwdmin = 6;
//
//$(document).ready(function() {
//
//
//	$('#reg').click(function() {
//
//		if ($('#user').val() == "") {
//			$('#user').focus().css({
//				border: "1px solid red",
//				boxShadow: "0 0 2px red"
//			});
//			$('#userCue').html("<font color='red'><b>×用户名不能为空</b></font>");
//			return false;
//		}
//
//
//
//		if ($('#user').val().length < 4 || $('#user').val().length > 16) {
//
//			$('#user').focus().css({
//				border: "1px solid red",
//				boxShadow: "0 0 2px red"
//			});
//			$('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
//			return false;
//
//		}
//		$.ajax({
//			type: reMethod,
//			url: "/member/ajaxyz.php",
//			data: "uid=" + $("#user").val() + '&temp=' + new Date(),
//			dataType: 'html',
//			success: function(result) {
//
//				if (result.length > 2) {
//					$('#user').focus().css({
//						border: "1px solid red",
//						boxShadow: "0 0 2px red"
//					});$("#userCue").html(result);
//					return false;
//				} else {
//					$('#user').css({
//						border: "1px solid #D7D7D7",
//						boxShadow: "none"
//					});
//				}
//
//			}
//		});
//
//
//		if ($('#passwd').val().length < pwdmin) {
//			$('#passwd').focus();
//			$('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
//			return false;
//		}
//		if ($('#passwd2').val() != $('#passwd').val()) {
//			$('#passwd2').focus();
//			$('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
//			return false;
//		}
//
//		var sqq = /^[1-9]{1}[0-9]{4,9}$/;
//		if (!sqq.test($('#qq').val()) || $('#qq').val().length < 5 || $('#qq').val().length > 12) {
//			$('#qq').focus().css({
//				border: "1px solid red",
//				boxShadow: "0 0 2px red"
//			});
//			$('#userCue').html("<font color='red'><b>×QQ号码格式不正确</b></font>");return false;
//		} else {
//			$('#qq').css({
//				border: "1px solid #D7D7D7",
//				boxShadow: "none"
//			});
//			
//		}
//
//		$('#regUser').submit();
//	});
//	

//});