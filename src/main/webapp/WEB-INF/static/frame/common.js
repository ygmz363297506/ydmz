function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/' + webName;
}

function cgetHiddenForAjax(div) {
	var result = {};
	$("#" + div + ' input[type="hidden"][name]').each(function() {
		if (this.value != null && this.value != "")
			eval("result." + fixNull(this.name.toString()) + '="' + fixNull(this.value) + '"');
	});
	return result;
}

function fixNull(num) {
	return num == null ? "" : $.trim(num);
}


//function cgetHiddenForAjax(div) {
//	var result = {};
//	$("#" + div + ' input[type="hidden"][name]').each(function() {
//		if (this.value != null && this.value != "")
//			eval("result." + fixNull(this.name.toString()) + '="' + fixNull(this.value) + '"');
//	});
//	result.cts = $("#cts").val();
//	return result;
//}
/**
 * frameMain警告框显示
 * 
 * @param level:danger,info,warning,success
 */
function showAlert(level, msg) {
	try {
		var html = '<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-hidden="true">';
		html += '<div class="modal-dialog modal-sm">';
		html += '<div class="modal-body">';
		html += '<div class="alert alert-' + level + '" style="margin-bottom:0;">';
		html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
		html += '<strong>' + msg + '</strong>';
		html += '</div></div></div></div>';
		$("#content").prepend(html);
		setTimeout("$('#alertModal').modal('show')", 300);
	} catch (e) {
	}
}