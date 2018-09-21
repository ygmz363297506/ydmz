$(document).ready(function() {
		var codes = "";
		//模糊查询
		$("#button_submit").click(function() {
			var name = $("#inputFlowCode").val();
			var pass = $("#inputFlowName").val();
			var flow = {
				flowCode : name,
				flowName : pass
			};//拼装成json格式  
			$.ajax({
				type : "POST",
				url : "${ctx}/flow/flowinfo",
				data : flow
			});
		});
		//-------批量删除
		//全选
	 	$("#choose").bind("click",function(){
	 		$("[id^='flow']").each(function() {
	 			$(this).prop("checked", true); 	
	 		});
	 	});
		//全不选
	 	$("#noChoose").bind("click",function(){
	 		$("[id^='flow']").each(function() {
	 			$(this).prop("checked", false); 	
	 		});
	 	});
		//批量删除
		$("#delFlow").bind("click",function(){
			$("[id^='flow']").each(function() {
				$(this).prop("checked", true); 
				codes += $(this).val() + ",";
			})
			if (confirm("确认删除吗？")) {
				var code = {
					flowCodes : codes
				};
				$.ajax({
					type : 'post',
					url : '${ctx}/flow/batching',
					data : code,
					success : function(data) {
						alert(data);
					}
				});
			}
		})
		//-----分页
		var page=${page};
		var value;
		var count=${pageCount};
		
		if(page==1){
			$("#upPage").attr("disabled","true");
		}
		if(page==count){
			$("#nextPage").attr("disabled","true");
			$("#lastPage").attr("disabled","true");
		}
		//第一页
		$("#firstPage").bind("click",function(){
			$("#upPage").attr("disabled","true");
			value={Page:1};
			$.ajax({
				type : 'get',
				url : '${ctx}/flow/flowinfo',
				data: value
			})
		})
		//点击下一页
		$("#nextPage").bind("click",function(){
			 value={Page:page+1};
			$.ajax({
				type : 'get',
				url : '${ctx}/flow/flowinfo',
				data: value
			})
		});
		//上一页
		$("#upPage").bind("click",function(){
				 value={Page:page-1};
				$.ajax({
					type : 'get',
					url : '${ctx}/flow/flowinfo',
					data: value
				})
		})
		//最后一页
		$("#lastPage").bind("click",function(){
			$("#nextPage").attr("disabled","true");
				 value={Page:count};
				$.ajax({
					type : 'get',
					url : '${ctx}/flow/flowinfo',
					data: value
				})
		})
		//---
	});