$(function(){
	var access=$('#access').val();	
	if(access=='true'){		
		$('.drawbutton img').show();
	}else{
		$('.drawbutton img').hide();
	}
	
	$("body").on('click', '.drawbutton img', function() {		
		$("#subform").remove();
		var form = "<form id='subform' action='" + getRootPath() + "/draw/do' method='post' >";
		form += "<input type='hidden' name='prizeId' value='" + $(this).attr("data-option") + "' />";
		form += "</form>";
		$("body").append(form);
		$("#subform").get(0).submit();
		$("#subform").remove();
	});
	
	
})