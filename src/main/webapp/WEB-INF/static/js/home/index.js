$(function(){
	
	//自写短信querylinkmanofone()
	querylinkmanofone();
	$("body").on('click','#updatepwd',function(){
		$("#qupwd").css('display','block');
	});
	$("body").on('click','#closeup',function(){
		$("#qupwd").css('display','none');
	});
	$("body").on('click','#updateto',function(){
		var oldpwd=$("#oldname").val();
		var newpwd=$("#newname").val();
		if(oldpwd == "" || oldpwd == undefined || oldpwd == null){
			alert("请填写旧密码！");
			return false;
		}else if(newpwd == "" || newpwd == undefined || newpwd == null){
			alert("请填写新密码！");
			return false;
		}else{
			$.ajax({
				type : "POST",
				url : getRootPath() + "/updatepwd",
				data : {
					"oldpwd" : oldpwd,
					"newpwd" : newpwd
				},
				dataType : 'json',
				success : function(data) {
					alert(data.msg);
					if(data.code==1){
						$("#qupwd").css('display','none');
					}
				}
		})
		}
	});
	
	
	//自写发送短信
	$('body').on('click','#tosendnote',function(){
		var notes =$("#notes").val();
		$("#hiddennotes").val(notes);
		$("#hiddenlinkman").val($("#alltosendman").val());
		var hiddenlinkman=$("#hiddenlinkman").val();
		if(hiddenlinkman==null || hiddenlinkman==""){
			alert("请添加收信人！");
			return false;
		}else if(notes==null || notes==""){
			alert("请填写短信内容！");
			return false;
		}else{
			sendnotesing();
		}
		
	})
	
	//定时短信发送
	$('body').on('click','#totimesendnote',function(){
		var notes=$("#timeingnotes").val();
		$("#hidtimenotes").val(notes);
		$("#hidtimelinkman").val($("#allsendmans").val());
		var hidtimelinkman=$("#allsendmans").val();
		var sendtime=$("#datetimepicker").val();
		$("#sendtimes").val(sendtime);
		if(hidtimelinkman==null || hidtimelinkman==""){
			alert("请添加收信人！");
			return false;
		}else if(notes==null || notes==""){
			alert("请填写短信内容！");
			return false;
		}else if(sendtime == null || sendtime==""){
			alert("请选择时间");
			return false;
		}else{
			sendnotestime();
		}
		
	})
	
	$('body').on('click','#menulist li a',function(){
		$("#menulist li").removeAttr("class");
		var text=$(this).text();
		$(this).parent().attr("class","active");
		$('#notesbody div[data="mdl"]').hide();
		$('#notesbody div[name="'+text+'"] input[type="text"]').val("");
		$('#notesbody input[type="hidden"]').val("");
		$('#notesbody div[name="'+text+'"]').show(500,function(){});
	})
	
	//点击查询发送记录
	$('body').on('click','#search',function(){
		var textnum=$("#textnum").val();
		var text=$("#notetext").val();
		$("#sendcalled").val(textnum);
		$("#sendnotet").val(text);
		query();
	});
	
	//点击查询定时短信记录
	$('body').on('click','#timgsearch',function(){
		var textnum=$("#timgtextnum").val();
		var text=$("#timgnotetext").val();
		$("#timgsendcalled").val(textnum);
		$("#timgsendnotet").val(text);
		querytimingnote();
	});
	
//	$('body').on('click','#asd',function(){
//		var type=document.getElementsByName("linkmansss");
//	        if(type[0].checked){
//	        	$("#testsss").val("nihao");
//	        }else{
//	        	$("#testsss").val("");
//	        }
//	});
	
	
	$('body').on('click','#oneselflinkman a',function(){
		var name = $(this).attr("name");
		var data= $(this).attr("data");
			if(data == "1"){
				$('#oneselflinkman [data="'+name+'"]:checkbox').removeAttr("checked");
				$(this).attr('data','0');
			}else{
				//此处因为浏览器兼容问题用了prop
				$('#oneselflinkman [data="'+name+'"]:checkbox').prop("checked", true);
				$(this).attr('data','1');
			}
	});
	
	$('body').on('click','#timinglinkman a',function(){
		var name = $(this).attr("name");
		var data= $(this).attr("data");
			if(data == "1"){
				$('#timinglinkman [data="'+name+'"]:checkbox').removeAttr("checked");
				$(this).attr('data','0');
			}else{
				//此处因为浏览器兼容问题用了prop
				$('#timinglinkman [data="'+name+'"]:checkbox').prop("checked", true);
				$(this).attr('data','1');
			}
	});
	
	
	
	//自发短信添加联系人
	$('body').on('click','#zixienote #linkmaninsert',function(){
		var linkmanval="";
		var hidenval="";
		var allsendman = $("#alltosendman");
		$("#zixienote input:checkbox[name='linkman']:checked").each(function() { // 遍历name=test的多选框
			if($(this).attr("datas")==0){
				linkmanval+=$(this).attr("data1")+"（"+$(this).val()+"）;";
				hidenval+=$(this).attr("data1")+" ";
				$(this).attr("datas","1");
			}
		});
	    allsendman.val(allsendman.val()+linkmanval);
	    $("#hiddenlinkman").val(allsendman.val());
	});
	
	//定时短信添加联系人
	$('body').on('click','#timingnote #linkmaninsert',function(){
		var linkmanval="";
		var hidenval="";
		var allsendman = $("#allsendmans");
		$("#timingnote input:checkbox[name='linkmans']:checked").each(function() { // 遍历name=test的多选框
			if($(this).attr("datas")==0){
				linkmanval+=$(this).attr("data1")+"（"+$(this).val()+"）;";
				hidenval+=$(this).attr("data1")+" ";
				$(this).attr("datas","1");
			}
		});
	    allsendman.val(allsendman.val()+linkmanval);
	    $("#hidtimelinkman").val(allsendman.val());
	});
	
	
	//自发短信模板
	$('body').on('click','#modelnotes',function(){
		var data=$("#modelnotes").attr("data");
		if(data==0){
			$("#templatenotes").hide();
			$("#modelnotes").attr("data","1");
		}else{
			querynotemodels();
			$("#templatenotes").show(500,function(){});
			$("#templatenotes").removeAttr("style");
			$("#modelnotes").attr("data","0");
		
		}
	});
	
	//点击设置定时短信
	$('body').on('click','#addtimgnote',function(){
		querytimglinkmano();
		$("#settimgnote").show(500,function(){});
	})
	//定时短信发送种子模板点击
	$('body').on('click','#modelnotest',function(){
		var data=$("#modelnotest").attr("data");
		if(data==0){
			$("#templatenotestime").hide();
			$("#modelnotest").attr("data","1");
		}else{
			querytimenotmodels();
			$("#templatenotestime").show(500,function(){});
			$("#templatenotestime").removeAttr("style");
			$("#modelnotest").attr("data","0");
		}
	});
	
	$('body').on('click','#templatenotes button',function(){
		var data=$(this).attr("data");
		$("#notes").text("");
		$("#notes").append(data);
	});
	
	$('body').on('click','#templatenotestime button',function(){
		var data=$(this).attr("data");
		$("#timeingnotes").text("");
		$("#timeingnotes").append(data);
	});
	
	$('body').on('click' ,'#removelinkman', function(){
		$("#alltosendman").val("");
		  $('input[name="linkman"]').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数      
			  $(this).attr("datas","0");
		  });  
//		$("#timingnote input:checkbox").attr("datas","0");
		$("#hiddenlinkman").val("");
	});
	
	$('body').on('click' ,'#removelinkmans', function(){
		$("#allsendmans").val("");
		  $('input[name="linkmans"]').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数      
			  $(this).attr("datas","0");
		  }); 
		$("#hidtimelinkman").val("");
	})
	
	//显示编辑种子短信
	$('body').on('click' ,'#addmodelnote', function(){
		$("#notesvalue").text("");
		$("#insertnote").show();
		$("#updatenote").hide();
		$("#writenotes").show(500,function(){});
		
	});
	//添加种子短信
	$('body').on('click' ,'#insertnote', function(){
		var text=$("#notesvalue").val();
		if(text!=null && text!=""){
			$.ajax({
				type : 'POST',
				url : getRootPath()+'/addmodelnote',
				data :{
					"callermodel" :text
				},
				dataType : 'json',
				success : function(data){
					if(data.result){
//						showAlert("success", "添加成功！");
						querymodel();
					}
				}
			});
		}else{
			alert("请填写短信内容！");
			return false;
		}
	});
	
	//加添修改信息
	$('body').on('click' ,'#caozuonotes button', function(){
		var data=$(this).attr("data");
		var id=$(this).attr("data1");
		$("#insertnote").hide();
		$("#notesvalue").text(data);
		$("#updatenote").val(id);
		$("#updatenote").show();
		$("#writenotes").show(500,function(){});
	});
	//修改种子短信
	$('body').on('click' ,'#updatenote', function(){
		var text=$("#notesvalue").val();
		var id=$("#updatenote").val();
		if(text!=null && text!=""){
			$.ajax({
				type : 'POST',
				url : getRootPath()+'/updatemodelnote',
				data :{
					"id" : id,
					"callermodel" :text
				},
				dataType : 'json',
				success : function(data){
					if(data.result){
						querymodel();
					}
				}
			});
		}else{
			alert("请填写短信内容！");
		}
	});
	
	
	$('body').on('click' ,'#removenotes', function(){
	var id=$(this).attr("data");
	var mes=confirm("您确定要删除吗？");
	if(mes==true){ 
		$.ajax({
			type : 'POST',
			url : getRootPath()+'/deletemodelnote',
			data :{
				"id" :id
			},
			datatype : 'json',
			success : function(data){
				if(data.result){
//					showAlert("success", "删除成功！");
					querymodel();
				}
			}
		});
	}
	});
	
	
	//定时短信的分页
	$("body").on('click', '#timingnote .Pages ul li a', function() {
		if (csetPageActiveAndPageNow(this,"timingnote")) {
			querytimingnote();
		}
	});
	//发送记录的分页
	$("body").on('click', '#sendrecord .Pages ul li a', function() {
		if (csetPageActiveAndPageNow(this,"sendrecord")) {
			query();
		}
	});
	//联系人分页
	$("body").on('click', '#linkman .Pages ul li a', function() {
		if (csetPageActiveAndPageNow(this,"linkman")) {
			querylinkman()
		}
	});
	//接收短信分页
	$("body").on('click', '#receivenote .Pages ul li a', function() {
		if (csetPageActiveAndPageNow(this,"receivenote")) {
			receverquery();
		}
	});
	//备份分页
	$("body").on('click', '#backupnotes .Pages ul li a', function() {
		if (csetPageActiveAndPageNow(this,"backupnotes")) {
			querybackup();
		}
	});
	//垃圾箱分页
	$("body").on('click', '#dustbin .Pages ul li a', function() {
		if (csetPageActiveAndPageNow(this,"dustbin")) {
			querydusbin();
		}
	});
	
	//linkman查询
	$('body').on('click','#slinkman',function(){
		var group=$("#textgroup").val();
		var contname=$("#textcontname").val();
		$("#groupname").val(group);
		$("#contname").val(contname);
		querylinkman();
//		query();
	});
	$('body').on('click','#uplinkmodel a',function(){
		var data=$(this).attr("data");
		$("#"+data+" input").attr("value","");
		$("#"+data).hide();
//		query();
	});
	
	
	//点击linkman修改
	$('body').on('click','#linkmantable a[name="updates"]',function(){
		var name=$(this).attr("data1");
		var number=$(this).attr("data2");
		var group=$(this).attr("data3");
		$("#upcontname").val(name);
		$("#upcallnum").val(number);
		$("#upgroup").val(group);
		$("#uplinkmodel").show(500,function(){});
		
	});
	//点击linkman删除
	$('body').on('click','#linkmantable a[name="deletes"]',function(){
		var callnumber=$(this).attr("data");
		var mes=confirm("您确定要删除吗？");
		if(mes==true){ 
			$.ajax({
				type : 'POST',
				url : getRootPath()+'/removelinkman',
				data :{
					"callednumber" :callnumber
				},
				datatype : 'json',
				success : function(data){
					if(data.result){
//						showAlert("success", "删除成功！");
						var page = $("#linkman #pageNow").val();
						var math=($("#linkman td").length-4)/4;
						if(math==1){
							$("#linkman #pageNow").val(page-1)
						}
						querylinkman();
					}else{
//						showAlert("danger", "删除失败！");
					}
				}
			});	
		}
	});
	
	//linkupdate
	$('body').on('click','#updatelinkman',function(){
		var contname=$("#upcontname").val();
		var group=$("#upgroup").val();
		var upcallnum=$("#upcallnum").val();
			$.ajax({
				type : 'POST',
				url : getRootPath()+'/updatelinkman',
				data :{
					"contname" : contname,
					"group" : group,
					"callednumber" :upcallnum
				},
				datatype : 'json',
				success : function(data){
					if(data.result){
						$("#uplinkmodel").hide();
						querylinkman();
					}else{
					}
				}
				
			});
	});
	//linkinsert
	$('body').on('click','#insertlinkman',function(){
		var contname=$("#addcontname").val();
		var group=$("#addgroup").val();
		var incallnum=$("#addcallnum").val();
		if(contname==null || contname==""){
			alert("请填写备注名字!");
			return false; 
		}else if(incallnum=="" || incallnum==null){
			alert("请填写电话号码!");
			return false;
		}else{
			$.ajax({
				type : 'POST',
				url : getRootPath()+'/addlinkman',
				data :{
					"contname" : contname,
					"group" : group,
					"callednumber" : incallnum
				},
				datatype : 'json',
				success : function(data){
					if(data.result){
						alert("添加成功!");
						$("#addcontname").val("");
						$("#addcallnum").val("");
						$("#addgroup").val("");
						querylinkman();
					}else{
						alert("添加失败！");
					}
				}
			
		});
		}
	});
	
	
	
	$('body').on('click','#shieldnote a[title="删除"]', function(){
		var idname = $(this).attr("data");
		var mes=confirm("您确定要删除吗？");
		if(mes==true){ 
		$.ajax({
			type : 'POST',
			url : getRootPath()+'/deleteblacklist',
			data :{
				"id" :idname
			},
			datatype : 'json',
			success : function(data){
				if(data.result){
//					showAlert("success", "删除成功！");
					queryblacklist();
				}else{
//					showAlert("danger", "删除失败！");
				}
			}
		});
		}
	});
	
	//短信屏蔽相关操作
	$('body').on('click','#toaddkey',function(){
		$("#heimingdan").show(500,function(){})
	});
	
	$('body').on('click','#toaddblacknum',function(){
		$("#huanxinhei").show(500,function(){})
	});
	
	$('body').on('click','#addkeybtn',function(){
		var keyword=$("#keyword").val();
		if(keyword !=null && keyword!=""){
		$.ajax({
			type : 'POST',
			url : getRootPath()+'/addblacklist',
			data :{
				"keyword" :keyword
			},
			datatype : 'json',
			success : function(data){
				if(data.result){
					$("#keyword").val("");
					queryblacklist();
				}else{
				}
			}
		});
		}else{
			alert("请填写关键字");
		}
	});
	
	$('body').on('click','#addshbtn',function(){
		var keyword=$("#shieldnum").val();
		if(keyword !=null && keyword!=""){
		$.ajax({
			type : 'POST',
			url : getRootPath()+'/addblacklist',
			data :{
				"shieldnum" :keyword
			},
			datatype : 'json',
			success : function(data){
				if(data.result){
					$("#shieldnum").val("");
					queryblacklist();
				}else{
				}
			}
		});
		}else{
			alert("请填写黑名单！");
		}
	});
	
	
	/**
	 * 点击查询接收短信
	 */
	$('body').on('click','#receiversearch',function(){
		var recetextnum=$("#recetextnum").val();
		var recenotetext=$("#recenotetext").val();
		$("#receivecalled").val(recetextnum);
		$("#receivenotet").val(recenotetext);
		receverquery();
	});
	
	$('body').on('click','#dussearch',function(){
		var dustbinnum = $("#dustbinnum").val();
		var duskeyword = $("#duskeyword").val();
		$("#dustbincalled").val(dustbinnum);
		$("#dustbinkey").val(duskeyword);
		querydusbin();
	});
	
	//添加备份
	$('body').on('click','#receivenote a[title="云备份"]',function(){
		var caller=$(this).attr("data1");
		var text=$(this).attr("data2");
		var recordtime=$(this).attr("data3");
		var mes=confirm("您确定要备份吗？");
		if(mes==true){ 
		$.ajax({
			type : 'POST',
			url : getRootPath()+'/addblackup',
			data :{
				"caller" : caller,
				"text" : text,
				"time" : recordtime
			},
			datatype : 'json',
			success : function(data){
				if(data.result){
					alert("备份成功！");
				}else{
					alert("备份失败！");
				}
			}
		});
		}
	});
	
	//点击查询云备份文件
	$('body').on('click','#backupsearch',function(){
		var caller=$("#backupcaller").val();
		var text=$("#textbackupnote").val();
		$("#hidenbackupcaller").val(caller);
		$("#hidenbackupnotes").val(text);
		querybackup();
	})
	
	//点击删除
	$('body').on('click','#backupnotes a[title="删除"]',function(){
		var data=$(this).attr("data");
		var mes=confirm("您确定要删除吗？");
		if(mes==true){ 
		$.ajax({
			type : 'POST',
			url : getRootPath()+'/removeblackup',
			data :{
				"id" : data,
			},
			datatype : 'json',
			success : function(data){
				if(data.result){
					var page = $("#backupnotes #pageNow").val();
					var math=($("#backupnotes td").length-5)/5;
					alert(math);
					if(math==1){
						$("#backupnotes #pageNow").val(page-1)
					}
					querybackup();
				}else{
				}
			}
		});
		}
	})
});


//查询接收信息
function query(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/sendnotquery',
		data :cgetHiddenForAjax("sendrecord"),
		datatype : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <td style='font-weight:bold;'>被叫号码</th> ";
				maintable += " <td style='font-weight:bold;'>主叫号码</th> ";
				maintable += " <td style='font-weight:bold;'>短信内容</th> ";
				maintable += " <td style='font-weight:bold;'>存入时间</th> ";
				maintable += "</tr></thead>";
				for(var key in data.list){
					maintable += " <tr><td>"+ fixNull(data.list[key].callednumber)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].callernumber)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].text)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].sendtime)+"</td>";
					maintable += " </tr>";
				}
				cinitPage(data, "sendrecord");
				$("#tickettable").children().remove();
				$("#tickettable").append(maintable);
			}else{
//				showAlert("danger", "无相关记录");
				alert("无相关数据!");
			}
		}
	});
}
//查询联系人
function querylinkman(){
	$.ajax({
		type : 'POST',
		url : getRootPath() + '/querylinkman',
		data : cgetHiddenForAjax("linkmanall"),
		dataType : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead style='text-align: center;'><tr>";
				maintable += " <td style='font-weight:bold;'>名字</td> ";
				maintable += " <td style='font-weight:bold;'>电话号码</td> ";
				maintable += " <td style='font-weight:bold;'>群组</td> ";
				maintable += " <td style='font-weight:bold;'>操作</td>"
				maintable += "</tr></thead>";
				for(var key in data.list){
					maintable += " <tr><td>"+ fixNull(data.list[key].contname)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].callednumber)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].group)+"</td>";
					maintable += '<td><a href="javascript:void(0);" name="updates" data1="'+fixNull(data.list[key].contname)+'" data2="' +
					fixNull(data.list[key].callednumber)+'" data3="'+fixNull(data.list[key].group)+'" title="修改"><span class="glyphicon glyphicon-pencil"></span></a>' +
							'&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" name="deletes" data="'+fixNull(data.list[key].callednumber)+'" title="删除"><span class="glyphicon glyphicon-minus"></span></a></td>';
					maintable += ' </tr>';
				}
				cinitPage(data, "linkmanall");
				$("#linkmantable").children().remove();
				$("#linkmantable").append(maintable);
			}else{
//				showAlert("danger", "无相关数据！");
				alert("无相关数据！");
			}
		}
	});
}


//查询联系人
function querylinkmanofone(){
	$.ajax({
		type : 'POST',
		url : getRootPath() + '/querylinkofone',
		data : {},
		dataType : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				for(var key in data.list){
					maintable += " <tr>";
					maintable += " <td>"+fixNull(data.list[key].contname)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].callednumber)+"</td>";
					maintable += '<td><a href="javascript:void(0);" data="0" name="'+fixNull(data.list[key].group)+'">'+fixNull(data.list[key].group)+'</a></td>';
					maintable += '<td><input type="checkbox" name="linkman"  datas="0"  data="'+fixNull(data.list[key].group)+'"'
					+'data1="'+fixNull(data.list[key].callednumber)+'" value="'+fixNull(data.list[key].contname)+'"></td>';
					maintable += ' </tr>';
				}
				$("#oneselflinkman").children().remove();
				$("#oneselflinkman").append(maintable);
				$("#alltosendman").val("");
				$("#hiddenlinkman").val("");
			}else{
//				showAlert("danger", "无相关数据！");
				alert("无相关数据！");
			}
		}
	});
}

//定时短信发送联系人
function querytimglinkmano(){
	$.ajax({
		type : 'POST',
		url : getRootPath() + '/querylinkofone',
		data : {},
		dataType : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				for(var key in data.list){
					maintable += " <tr>";
					maintable += " <td>"+fixNull(data.list[key].contname)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].callednumber)+"</td>";
					maintable += '<td><a href="javascript:void(0);" data="0" name="'+fixNull(data.list[key].group)+'">'+fixNull(data.list[key].group)+'</a></td>';
					maintable += '<td><input type="checkbox" name="linkmans" datas="0"  data="'+fixNull(data.list[key].group)+'"'
					+'data1="'+fixNull(data.list[key].callednumber)+'" value="'+fixNull(data.list[key].contname)+'"></td>';
					maintable += ' </tr>';
				}
				$("#timinglinkman").children().remove();
				$("#timinglinkman").append(maintable);
				$("#allsendmans").val("");
				$("#hidtimelinkman").val("");
			}else{
//				showAlert("danger", "无相关数据！");
				alert("无相关数据！");
			}
		}
	});
}

//自写短信的模板
function querynotemodels(){
	$.ajax({
		type : 'POST',
		url : getRootPath() + '/querymodelnote',
		data : {},
		dataType : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				for(var key in data.list){
				maintable += '<div class="form-group">';
				maintable += '<label class="col-sm-1 control-label">创建时间:</label>';
				maintable += '<div class="col-sm-10">';
				maintable += '<div style="padding-top: 5px;">';
				maintable += '<span>'+fixNull(data.list[key].createtime)+'</span>';
				maintable += '</div></div>';
				maintable += '<div class="col-sm-1">';
				maintable += '</div></div>';
				maintable += '<div class="form-group">';
				maintable += '<label class="col-sm-1 control-label">短信内容:</label>';
				maintable += '<div class="col-sm-10">';
				maintable += '<div style="background-color:#fcf8e3;border-color:#faebcc;">'+fixNull(data.list[key].callermodel);
				maintable += '</div></div>';
				maintable += '<div class="col-sm-1">';
				maintable += '<button type="button" class="btn btn-default btn-sm" data="'+fixNull(data.list[key].callermodel)+'">添加此短信</button>';
				maintable += '</div></div>';
				}
				maintable += '</form>';
				$("#formnotemodel").children().remove();
				$("#formnotemodel").append(maintable);
			}else{
//				showAlert("danger", "无相关数据！");
//				alert("系统出错，请联系管理员!");
				alert("无相关数据!");
			}
		}
	});
}

//查询定时短信发送模板
function querytimenotmodels(){
	$.ajax({
		type : 'POST',
		url : getRootPath() + '/querymodelnote',
		data : {},
		dataType : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				for(var key in data.list){
				maintable += '<div class="form-group">';
				maintable += '<label class="col-sm-1 control-label">创建时间:</label>';
				maintable += '<div class="col-sm-10">';
				maintable += '<div style="padding-top: 5px;">';
				maintable += '<span>'+fixNull(data.list[key].createtime)+'</span>';
				maintable += '</div></div>';
				maintable += '<div class="col-sm-1">';
				maintable += '</div></div>';
				maintable += '<div class="form-group">';
				maintable += '<label class="col-sm-1 control-label">短信内容:</label>';
				maintable += '<div class="col-sm-10">';
				maintable += '<div style="background-color:#fcf8e3;border-color:#faebcc;">'+fixNull(data.list[key].callermodel);
				maintable += '</div></div>';
				maintable += '<div class="col-sm-1">';
				maintable += '<button type="button" class="btn btn-default btn-sm" data="'+fixNull(data.list[key].callermodel)+'">添加此短信</button>';
				maintable += '</div></div>';
				}
				maintable += '</form>';
				$("#timingnotmodel").children().remove();
				$("#timingnotmodel").append(maintable);
			}else{
//				showAlert("danger", "无相关数据！");
//				alert("系统出错，请联系管理员!");
				alert("无相关数据!");
			}
		}
	});
}


/**
 * 查询定时短信
 */
function querytimingnote(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/querytimingnote',
		data :cgetHiddenForAjax("timingnote"),
		datatype : 'json',
		success : function(data){
			if(data.exception){
				alert("系统出错，请联系管理员");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <td style='font-weight:bold;'>主叫号码</td> ";
				maintable += " <td style='font-weight:bold;'>被叫号码</td> ";
				maintable += " <td style='font-weight:bold;'>短信内容</td> ";
				maintable += " <td style='font-weight:bold;'>发送时间</td> ";
				maintable += "</tr></thead><tbody>";
				for(var key in data.list){
					maintable += "<tr>"
					maintable += " <td>"+fixNull(data.list[key].caller)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].called)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].text)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].sendtime)+"</td>";
					maintable += " </tr>";
				}
				maintable +="</tbody>";
				cinitPage(data, "timingnote");
				$("#timgmaintable").children().remove();
				$("#timgmaintable").append(maintable);
			}else{
				alert("无相关记录");
			}
		}
	});
}


//查询种子模板
function querymodel(){
	$.ajax({
		type : 'POST',
		url : getRootPath() + '/querymodelnote',
		data : {},
		dataType : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				maintable += '<form class="form-horizontal" role="form"> ';
				for(var key in data.list){
				maintable += '<div class="form-group">';
				maintable += '<label class="col-sm-1 control-label">创建时间:</label>';
				maintable += '<div class="col-sm-10">';
				maintable += '<div style="padding-top: 5px;">';
				maintable += '<span>'+fixNull(data.list[key].createtime)+'</span>';
				maintable += '</div></div>';
				maintable += '<div class="col-sm-1">';
				maintable += '<a href="javascript:void(0)" data="'+fixNull(data.list[key].id)+'" title="删除该模板" id="removenotes"><span style="padding-top: 10px;" class="glyphicon glyphicon-remove"></span></a>';
				maintable += '</div></div>';
				maintable += '<div class="form-group">';
				maintable += '<label class="col-sm-1 control-label">短信内容:</label>';
				maintable += '<div class="col-sm-10">';
				maintable += '<div style="background-color:#fcf8e3;border-color:#faebcc;">'+fixNull(data.list[key].callermodel);
				maintable += '</div></div>';
				maintable += '<div class="col-sm-1">';
				maintable += '<button type="button" class="btn btn-default btn-sm" data1="'+fixNull(data.list[key].id)+'" data="'+fixNull(data.list[key].callermodel)+'">修改内容</button>';
				maintable += '</div></div>';
				}
				maintable += '</form>';
				$("#caozuonotes").children().remove();
				$("#caozuonotes").append(maintable);
				$("#writenotes").hide();
				$("#notesvalue").text("");
			}else{
//				showAlert("danger", "无相关数据！");
//				alert("系统出错，请联系管理员!");
				alert("无相关数据!");
			}
		}
	});
}

/**
 * 查询用户屏蔽信息
 */
function queryblacklist(){
	$.ajax({
		type : 'POST',
		url : getRootPath() + '/queryblacklist',
		data : {},
		dataType : 'json',
		success : function(data){
			if(data.exception){
//				showAlert("danger", "系统出错，请联系管理员!");
				alert("系统出错，请联系管理员!");
			}else if(data.list!=null){
				var maintable="";
				var mainblack="";
				maintable += '<form class="form-horizontal" role="form"> ';
				mainblack += '<form class="form-horizontal" role="form"> ';
				for(var key in data.list){
				if(fixNull(data.list[key].keyword)!=null && fixNull(data.list[key].keyword)!=""){
				maintable += '<div class="form-group">';
				maintable += '<div class="col-sm-10">';
				maintable += '<span class="label label-danger">'+fixNull(data.list[key].keyword)+'</span>'
				maintable += '</div>';
				maintable += '<div class="col-sm-2">';
				maintable += '<a href="javascript:void(0);" title="删除" data="'+fixNull(data.list[key].id)+'"><span class="glyphicon glyphicon-remove"></span></a>';
				maintable += '</div></div>';
				}
				if(fixNull(data.list[key].shieldnum)!=null && fixNull(data.list[key].shieldnum)!=""){
				mainblack += '<div class="form-group">';
				mainblack += '<div class="col-sm-10">';
				mainblack += '<span class="label label-danger">'+fixNull(data.list[key].shieldnum)+'</span>'
				mainblack += '</div>';
				mainblack += '<div class="col-sm-2">';
				mainblack += '<a href="javascript:void(0);" title="删除" data="'+fixNull(data.list[key].id)+'"><span class="glyphicon glyphicon-remove"></span></a>';
				mainblack += '</div></div>';
				}
				}
				maintable += '</form>';
				mainblack += '</form>'
				$("#addkeyword").children().remove();
				$("#addkeyword").append(maintable);
				$("#addblacknum").children().remove();
				$("#addblacknum").append(mainblack);
			}else{
//				showAlert("danger", "无相关数据！");
				alert("无相关数据!");
			}
		}
	});
}


/**
 * 查询接收短信
 */
function receverquery(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/recevenotquery',
		data :cgetHiddenForAjax("receivenote"),
		datatype : 'json',
		success : function(data){
			if(data.exception){
				alert("系统出错，请联系管理员");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <td style='font-weight:bold;'>主叫号码</td> ";
				maintable += " <td style='font-weight:bold;'>短信内容</td> ";
				maintable += " <td style='font-weight:bold;'>存入时间</td> ";
				maintable += " <td style='font-weight:bold;'>操作</td> ";
				maintable += "</tr></thead><tbody>";
				for(var key in data.list){
					maintable += "<tr>"
					maintable += " <td>"+fixNull(data.list[key].ou)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].text)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].logtime)+"</td>";
					maintable += '<td><a href="javascript:void(0);" title="云备份" data1="'+fixNull(data.list[key].ou)+'"';
					maintable +=' data2="'+fixNull(data.list[key].text)+'" data3="'+fixNull(data.list[key].logtime)+'">云备份</a></td>'
					maintable += " </tr>";
				}
				maintable +="</tbody>";
				cinitPage(data, "receivenote");
				$("#receivetable").children().remove();
				$("#receivetable").append(maintable);
			}else{
				alert("无相关记录");
			}
		}
	});
}

/**
 * 垃圾箱查询
 */
function querydusbin(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/dustbinquery',
		data :cgetHiddenForAjax("dustbin"),
		datatype : 'json',
		success : function(data){
			if(data.exception){
				alert("系统出错，请联系管理员");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <td style='font-weight:bold;'>拦截号码</td> ";
				maintable += " <td style='font-weight:bold;'>拦截内容</td> ";
				maintable += " <td style='font-weight:bold;'>拦截时间</td> ";
				maintable += "</tr></thead>";
				for(var key in data.list){
					maintable += "<tr>"
					maintable += " <td>"+fixNull(data.list[key].caller)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].text)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].logtime)+"</td>";
					maintable += " </tr>";
				}
				cinitPage(data, "dustbin");
				$("#dustbintable").children().remove();
				$("#dustbintable").append(maintable);
			}else{
				alert("无相关记录");
			}
		}
	});
}

/**
 * 查询备份
 */
function querybackup(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/querybackup',
		data :cgetHiddenForAjax("backupnotes"),
		datatype : 'json',
		success : function(data){
			if(data.exception){
				alert("系统出错，请联系管理员");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <td style='font-weight:bold;'>主叫号码</td> ";
				maintable += " <td style='font-weight:bold;'>被叫号码</td> ";
				maintable += " <td style='font-weight:bold;'>短信内容</td> ";
				maintable += " <td style='font-weight:bold;'>收到时间</td> ";
				maintable += " <td style='font-weight:bold;'>操作</td> ";
				maintable += "</tr></thead>";
				for(var key in data.list){
					maintable += "<tr>";
					maintable += " <td>"+fixNull(data.list[key].caller)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].called)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].text)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].time)+"</td>";
					maintable += ' <td><a href="javascript:void(0);" title="删除" data="'+fixNull(data.list[key].id)+'">'
					+'<span class="glyphicon glyphicon-remove"></span></a></td>"';
					maintable += " </tr>";
				}
				cinitPage(data, "backupnotes");
				$("#backuptable").children().remove();
				$("#backuptable").append(maintable);
			}else{
				alert("无相关记录");
			}
		}
	});
}


function querynumcount(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/querynumcout',
		data :cgetHiddenForAjax("backupnotes"),
		datatype : 'json',
		success : function(data){
			if(data.exception){
				alert("系统出错，请联系管理员");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <td style='font-weight:bold;'>号码</td> ";
				maintable += " <td style='font-weight:bold;'>创建时间</td> ";
				maintable += "</tr></thead>";
				for(var key in data.list){
					maintable += "<tr>";
					maintable += " <td>"+fixNull(data.list[key].callednumber)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].sendtime)+"</td>";
					maintable += " </tr>";
				}
				cinitPage(data, "numcontdiv");
				$("#tabnumcount").children().remove();
				$("#tabnumcount").append(maintable);
			}else{
				alert("无相关记录");
			}
		}
	});
}

function querynotecount(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/querynumcout',
		data :cgetHiddenForAjax("backupnotes"),
		datatype : 'json',
		success : function(data){
			if(data.exception){
				alert("系统出错，请联系管理员");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <td style='font-weight:bold;'>主叫号码</td> ";
				maintable += " <td style='font-weight:bold;'>被叫号码</td> ";
//				maintable += " <td style='font-weight:bold;'>短信内容</td> ";
				maintable += " <td style='font-weight:bold;'>创建时间</td> ";
				maintable += " <td style='font-weight:bold;'>状态</td> ";
				maintable += "</tr></thead>";
				for(var key in data.list){
					maintable += "<tr>";
					maintable += " <td>"+fixNull(data.list[key].callernumber)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].callednumber)+"</td>";
//					maintable += " <td>"+fixNull(data.list[key].text)+"</td>";
					maintable += " <td>"+fixNull(data.list[key].sendtime)+"</td>";
					maintable += " <td>成功</td>";
					maintable += " </tr>";
				}
				cinitPage(data, "notecontdiv");
				$("#tabnotecount").children().remove();
				$("#tabnotecount").append(maintable);
			}else{
				alert("无相关记录");
			}
		}
	});
}

/**
 * 自写短信发送
 */
function sendnotesing(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/sendnotesing',
		data :cgetHiddenForAjax("zixienote"),
		datatype : 'json',
		success : function(data){
			if(data.result){
				if(data.msg!=null && data.msg!=""){
					alert(data.msg+"号码发送失败，其他号码发送成功！");
					$("#alltosendman").val(data.msg);
				}else{
					alert("请求成功");
					$("#alltosendman").val("");
				}
				$('input[name="linkman"]').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数      
					  $(this).attr("datas","0");
				}); 
				
				$("#hiddenlinkman").val("");
			}else{
				alert("请求失败！");
			}
		}
	});
}

/**
 * 定时发送短信
 */
function sendnotestime(){
	$.ajax({
		type : 'POST',
		url : getRootPath()+'/sendnotestime',
		data :cgetHiddenForAjax("settimgnote"),
		datatype : 'json',
		success : function(data){
			if(data.result){
				if(data.msg!=null && data.msg!=""){
					alert(data.msg+"号码设置失败，其他号码设置成功！");
					$("#timingnote #allsendmans").val(data.msg);
				}else{
					alert("设置成功");
					$("#allsendmans").val("");
				}
				$('input[name="linkmans"]').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数      
					  $(this).attr("datas","0");
				});
				$("#timingnote #hidtimelinkman").val("");
				$("#hidtimenotes").val("");
				
				querytimingnote();
			}else{
				alert("设置失败！");
			}
		}
	});
}
/**
 * 
 * 
 * @param level:danger,info,warning,success
 * @param pid:位置id
 */
function showFrameMianAlert(level, msg, pid) {
	try {
		var html = '<div class="alert alert-' + level + ' alert-dismissible" role="alert">'
		html += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
		html += '<span aria-hidden="true">&times;</span>';
		html += '</button>';
		html += '<strong>' + msg + '</strong>';
		html += '</div>';
		$("#" + pid).prepend(html);
	} catch (e) {
	}
}

