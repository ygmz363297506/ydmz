/**
 * page初始化 请在页面上设置class=Pages的div
 * 
 * @param data
 *            ajax调用请传入参数 submit型返回请不要传入data（请在页面上<%@ include file="page.jsp"%>）
 */
function initPage(data) {
	try {
		if (data != null) {
			$("#pageNow,#pageCount").remove();
			$("body").append('<input type="hidden" id="pageNow" name="pageNow" />');
			$("body").append('<input type="hidden" id="pageCount" name="pageCount" />');
			try {
				$("#pageNow").val(data.page.pageNow);
				$("#pageCount").val(data.page.pageCount);
			} catch (e) {
				$("#pageNow").val(1);
				$("#pageCount").val(1);
			}
		}
		var pageNow = parseInt($("#pageNow").val(), 10);
		if (pageNow < 1 || isNaN(pageNow)) {
			$("#pageNow").val(1);
			pageNow = 1;
		}
		var pageCount = parseInt($("#pageCount").val(), 10);
		if (pageCount > 1) {
			$('.Pages').children().remove();
			var pages = '<ul class="pagination">';
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
			pages += '</ul>';
			pages += '<span style="float: right;margin-top:10px;margin-right:20px;">总计: ' + data.page.count + ' 条记录</span>';
			$('.Pages').append(pages);
			setPageActive();
		}else{
			$("#" + div + ' .Pages').children().remove();
			$('.Pages').append('<span style="float: right;margin-top:10px;margin-right:20px;">总计: ' + data.page.count + ' 条记录</span>');
		}
	} catch (e) {
		throw e;
	}
}
function setPageActiveAndPageNow(now) {
	try {
		if ($(now).parent().hasClass("disabled")) {
			return false;
		}
		var topage = parseInt($("#pageNow").val(), 10);
		var isSubmit = false;
		if ($(now).is($(".Pages ul li a").first())) {
			isSubmit = true;
			topage = topage - 1;
		} else if ($(now).is($(".Pages ul li a").last())) {
			isSubmit = true;
			topage = topage + 1;
		} else if ($(now).text() == "┉" && $(now).parent().next().is($(".Pages ul li").last())) {
			isSubmit = false;
			var lastpage = parseInt($(now).parent().prev().find("a").text(), 10);
			var nextlastpage = parseInt($("#pageCount").val(), 10);
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
			$(".Pages ul li").not($(".Pages ul li").first()).not($(".Pages ul li").last()).slideUp();
			$(".Pages ul li").not($(".Pages ul li").first()).not($(".Pages ul li").last()).remove();
			$(".Pages ul li").first().after(nextpages);
		} else if ($(now).text() == "┉" && $(now).parent().prev().is($(".Pages ul li").first())) {
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
			$(".Pages ul li").not($(".Pages ul li").first()).not($(".Pages ul li").last()).slideUp();
			$(".Pages ul li").not($(".Pages ul li").first()).not($(".Pages ul li").last()).remove();
			$(".Pages ul li").first().after(prevpages);
		} else if ($(".Pages ul li[class=active] a").is(now)) {
			isSubmit = false;
		} else {
			isSubmit = true;
			topage = parseInt($(now).text(), 10);
		}
		if (isSubmit) {
			$("#pageNow").val(topage);
		} else {
			setPageActive();
			// $("*[title]").tipTip();
		}
		return isSubmit;
	} catch (e) {
		throw e;
	}
}
function setPageActive() {
	try {
		$(".Pages ul li").removeClass('active');
		if (findActivePage() != null) {
			findActivePage().parent().addClass("active");
		}
		if ($("#pageNow").val() == $(".Pages ul li").first().next().text()) {
			$(".Pages ul li").first().addClass("disabled");
			// $(".Pages ul li").first().remove();
		} else if ($("#pageNow").val() == $(".Pages ul li").last().prev().text()) {
			$(".Pages ul li").last().addClass("disabled");
			// $(".Pages ul li").last().remove();
		}
	} catch (e) {
		throw e;
	}
}

function findActivePage() {
	try {
		var activePage = null;
		$(".Pages ul li a").each(function() {
			if ($(this).text() == $("#pageNow").val()) {
				activePage = $(this);
			}
		});
		return activePage;
	} catch (e) {
		throw e;
	}
}