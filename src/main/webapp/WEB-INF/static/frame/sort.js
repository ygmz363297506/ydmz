function sortTable(tableid) {
	try {
		var th = $("#" + tableid).find("th[id='" + $("#hidorderby").val() + "']");
		$("#sortlike").remove();
		if ($("#hidorderasc").val() == "true") {
			th.append('<span id="sortlike" class="glyphicon glyphicon-triangle-top"></span>');
		} else {
			th.append('<span id="sortlike" class="glyphicon glyphicon-triangle-bottom"></span>');
		}
	} catch (e) {
		throw e;
	}
}