function query(){
	var textnum=$("#textnum").val();
	$.ajax({
		type : 'POST',
		url : getRootPath()+'home/query',
		data :{
			"du" : textnum		
		},
		datatype : 'json',
		success : function(data){
			if(data.exception){
				alert("系统出错，请联系管理员");
			}else if(data.list!=null){
				var maintable="";
				maintable += " <thead><tr>";
				maintable += " <th>被叫号码</th> ";
				maintable += " <th>主叫号码</th> ";
				maintable += " <th>短信内容</th> ";
				maintable += " <th>存入时间</th> ";
				maintable += "</tr></thead>";
				for(var key in data.list){
					maintalbe += " <tr><th>"+ fixNull(data.list[key].du)+"</th>";
					maintable += " <th>"+fixNull(data.list[key].ou)+"</th>";
					maintable += " <th>"+fixNull(data.list[key].text)+"</th>";
					maintable += " <th>"+fixNull(data.list[key].logtime)+"</th>";
					maintable += " </tr>";
				}
				cinitPage(data, "maintable");
				$("#besTable").children().remove();
				$("#besTable").append(maintable);
			}else{
				alert("无相关记录");
			}
		}
	});
}
	$("body").on('click', '.Pages ul li a', function() {
		if (csetPageActiveAndPageNow(this,"maintalbe")) {
			query();
		}
	});
