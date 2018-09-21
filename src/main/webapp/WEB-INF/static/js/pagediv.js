/**
 * page初始化 请在页面上设置class=Pages的div
 * 
 * @param data
 *            ajax调用请传入参数 submit型返回请不要传入data（请在页面上<%@ include
 *            file="page.jsp"%>）
 */
function cinitPage(data, div) {
	try {
		if (data != null) {
			$("#" + div + " #pageNow,#" + div + " #pageCount").remove();
			$("body #" + div).append('<input type="hidden" id="pageNow" name="pageNow" />');
			$("body #" + div).append('<input type="hidden" id="pageCount" name="pageCount" />');
			try {
				$("#" + div + " #pageNow").val(data.page.pageNow);
				$("#" + div + " #pageCount").val(data.page.pageCount);
			} catch (e) {
				$("#" + div + " #pageNow").val(1);
				$("#" + div + " #pageCount").val(1);
			}
		}
		var pageNow = parseInt($("#" + div + " #pageNow").val(), 10);
		if (pageNow < 1 || isNaN(pageNow)) {
			$("#" + div + " #pageNow").val(1);
			pageNow = 1;
		}
		var pageCount = parseInt($("#" + div + " #pageCount").val(), 10);
		if (pageCount > 1) {
			$("#" + div + ' .Pages').children().remove();
			var pages = '<ul class="pagination">';
			pages += '<li><a href="javascript:void(0)" title="首页">首</a></li>'
			pages += '<li><a href="javascript:void(0)" title="上一页">&laquo;</a></li>';
			if (pageCount < 11) {
				for (var t = 1; t < pageCount + 1; t++) {
					pages += '<li><a href="javascript:void(0)">' + t + '</a></li>';
				}
			} else {
				if (pageNow < 11) {
					for (var t = 1; t < 11; t++) {
						pages += '<li><a href="javascript:void(0)">' + t + '</a></li>';
					}
					pages += '<li><a href="javascript:void(0)" title="向后更多">┉</a></li>';
				} else {
					pages += '<li><a href="javascript:void(0)" title="向前更多">┉</a></li>';
					if (pageNow % 10 == 0) {
						for (var t = (Math.floor(pageNow / 10) - 1) * 10 + 1; t < pageNow + 1; t++) {
							pages += '<li><a href="javascript:void(0)">' + t + '</a></li>';
						}
						if (pageNow != pageCount) {
							pages += '<li><a href="javascript:void(0)" title="向后更多">┉</a></li>';
						}
					} else {
						var temp = (Math.floor(pageNow / 10) + 1) * 10;
						if (temp + 1 > pageCount) {
							temp = pageCount;
						}
						for (var t = Math.floor(pageNow / 10) * 10 + 1; t < temp + 1; t++) {
							pages += '<li><a href="javascript:void(0)">' + t + '</a></li>';
						}
						if (temp < pageCount) {
							pages += '<li><a href="javascript:void(0)" title="向后更多">┉</a></li>';
						}
					}
				}
			}
			pages += '<li><a href="javascript:void(0)" title="下一页">&raquo;</a></li>';
			pages += '<li><a href="javascript:void(0)" title="尾页">尾</a></li>';
			pages += '</ul>';
			pages += '<span style="float: right;margin-top:10px;margin-right:20px;">总计: ' + data.page.count + ' 条记录</span>';
			$("#" + div + ' .Pages').append(pages);
			csetPageActive(div);
		}else{
			$("#" + div + ' .Pages').children().remove();
			$("#" + div + ' .Pages').append('<span style="float: right;margin-top:10px;margin-right:20px;">总计: ' + data.page.count + ' 条记录</span>');
		}
	} catch (e) {
		throw e;
	}
}
function csetPageActiveAndPageNow(now, div) {
	try {
		if ($(now).parent().hasClass("disabled")) {
			return false;
		}
		var count=$("#" + div + " #pageCount").val();
		var topage = parseInt($("#" + div + " #pageNow").val(), 10);
		var isSubmit = false;
		if ($(now).is($("#" + div + " .Pages ul li a").first())) {
			isSubmit = true;
			topage = 1;
		} else if ($(now).is($("#" + div + " .Pages ul li a").eq(1))){
			isSubmit = true;
			topage = topage - 1;
		} else if ($(now).is($("#" + div + " .Pages ul li a").last())) {
			isSubmit = true;
			topage = count;
		} else if ($(now).parent().is($("#" + div + " .Pages ul li a").last().parent().prev())){
			isSubmit = true;
			topage = topage + 1;
		} else if ($(now).text() == "┉" && $(now).parent().next().is($("#" + div + " .Pages ul li").last().prev())) {
			isSubmit = false;
			var lastpage = parseInt($(now).parent().prev().find("a").text(), 10);
			var nextlastpage = parseInt($("#" + div + " #pageCount").val(), 10);
			var more = false;
			if (nextlastpage > lastpage + 10) {
				nextlastpage = lastpage + 10;
				more = true;
			}
			var nextpages = "";
			nextpages += '<li><a href="javascript:void(0)" title="向前更多">┉</a></li>';
			for (var i = lastpage + 1; i < nextlastpage + 1; i++) {
				nextpages += '<li><a href="javascript:void(0)">' + i + '</a></li>';
			}
			if (more) {
				nextpages += '<li><a href="javascript:void(0)" title="向后更多">┉</a></li>';
			}
			$("#" + div + " .Pages ul li").not($("#" + div + " .Pages ul li").first()).not($("#" + div + " .Pages ul li").eq(1)).not($("#" + div + " .Pages ul li").last()).not($("#" + div + " .Pages ul li").last().prev()).slideUp();
			$("#" + div + " .Pages ul li").not($("#" + div + " .Pages ul li").first()).not($("#" + div + " .Pages ul li").eq(1)).not($("#" + div + " .Pages ul li").last()).not($("#" + div + " .Pages ul li").last().prev()).remove();
			$("#" + div + " .Pages ul li").eq(1).after(nextpages);
		} else if ($(now).text() == "┉" && $(now).parent().prev().is($("#" + div + " .Pages ul li").eq(1))) {
			isSubmit = false;
			var firstpage = parseInt($(now).parent().next().find("a").text(), 10);
			var prevpages = "";
			if (firstpage > 11) {
				prevpages += '<li><a href="javascript:void(0)" title="向前更多">┉</a></li>';
			}
			for (var i = firstpage - 10; i < firstpage; i++) {
				prevpages += '<li><a href="javascript:void(0)">' + i + '</a></li>';
			}
			prevpages += '<li><a href="javascript:void(0)" title="向后更多">┉</a></li>';
			$("#" + div + " .Pages ul li").not($("#" + div + " .Pages ul li").first()).not($("#" + div + " .Pages ul li").eq(1)).not($("#" + div + " .Pages ul li").last()).not($("#" + div + " .Pages ul li").last().prev()).slideUp();
			$("#" + div + " .Pages ul li").not($("#" + div + " .Pages ul li").first()).not($("#" + div + " .Pages ul li").eq(1)).not($("#" + div + " .Pages ul li").last()).not($("#" + div + " .Pages ul li").last().prev()).remove();
			$("#" + div + " .Pages ul li").eq(1).after(prevpages);
		} else if ($("#" + div + " .Pages ul li[class=active] a").is(now)) {
			isSubmit = false;
		} else {
			isSubmit = true;
			topage = parseInt($(now).text(), 10);
		}
		if (isSubmit) {
			if (topage > count) {
				topage = count;
			}
			$("#" + div + " #pageNow").val(topage); 
			$("#pageNow").val(topage);
		} else {
			csetPageActive(div);
			// $("*[title]").tipTip();
		}
		return isSubmit;
	} catch (e) {
		throw e;
	}
}
function csetPageActive(div) {
	try {
		$("#" + div + " .Pages ul li").removeClass('active');
		if (cfindActivePage(div) != null) {
			cfindActivePage(div).parent().addClass("active");
		}
		if ($("#" + div + " #pageNow").val() == $("#" + div + " .Pages ul li a").first().next().text()) {
			$("#" + div + " .Pages ul li").first().addClass("disabled");
			// $("#"+div+" .Pages ul li").first().remove();
		} else if ($("#" + div + " #pageNow").val() == $("#" + div + " .Pages ul li").last().prev().text()) {
			$("#" + div + " .Pages ul li").last().addClass("disabled");
			// $("#"+div+" .Pages ul li").last().remove();
		}
	} catch (e) {
		throw e;
	}
}

function cfindActivePage(div) {
	try {
		var activePage = null;
		$("#" + div + " .Pages ul li a").each(function() {
			if ($(this).text() == $("#" + div + " #pageNow").val()) {
				activePage = $(this);
			}
		});
		return activePage;
	} catch (e) {
		throw e;
	}
}