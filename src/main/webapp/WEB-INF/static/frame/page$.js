/**
 * page初始化 请在页面上设置class=Pages的div
 * 
 * @param data
 *            ajax调用请传入参数 submit型返回请不要传入data（请在页面上<%@ include
 *            file="page.jsp"%>）
 */
function initPage$(data, $div) {
	try {
		if (data != null) {
			$div.find('#pageNow').remove();
			$div.find('#pageCount').remove();
			$div.append('<input type="hidden" id="pageNow" name="pageNow" />');
			$div.append('<input type="hidden" id="pageCount" name="pageCount" />');
			try {
				$div.find('#pageNow').val(data.page.pageNow);
				$div.find('#pageCount').val(data.page.pageCount);
			} catch (e) {
				$div.find('#pageNow').val(1);
				$div.find('#pageCount').val(1);
			}
		}
		var pageNow = parseInt($div.find('#pageNow').val(), 10);
		if (pageNow < 1 || isNaN(pageNow)) {
			$div.find('#pageNow').val(1);
			pageNow = 1;
		}
		var pageCount = parseInt($div.find('#pageCount').val(), 10);
		if (pageCount > 1) {
			$div.find('.Pages').children().remove();
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
			$div.find('.Pages').append(pages);
			setPageActive$($div);
		}else{
			$div.find('.Pages').children().remove();
			$div.find('.Pages').append('<span style="float: right;margin-top:10px;margin-right:20px;">总计: ' + data.page.count + ' 条记录</span>');
		}
	} catch (e) {
		throw e;
	}
}

/**
 * page初始化 请在页面上设置class=Pages的div
 * 
 * @param data
 *            ajax调用请传入参数 submit型返回请不要传入data（请在页面上<%@ include
 *            file="page.jsp"%>）
 */
function initPagetwo$(data, $div) {
	try {
		if (data != null) {
			$div.find('#pageNow').remove();
			$div.find('#pageCount').remove();
			$div.append('<input type="hidden" id="pageNow" name="pageNow" />');
			$div.append('<input type="hidden" id="pageCount" name="pageCount" />');
			try {
				$div.find('#pageNow').val(data.page2.pageNow);
				$div.find('#pageCount').val(data.page2.pageCount);
			} catch (e) {
				$div.find('#pageNow').val(1);
				$div.find('#pageCount').val(1);
			}
		}
		var pageNow = parseInt($div.find('#pageNow').val(), 10);
		if (pageNow < 1 || isNaN(pageNow)) {
			$div.find('#pageNow').val(1);
			pageNow = 1;
		}
		var pageCount = parseInt($div.find('#pageCount').val(), 10);
		if (pageCount > 1) {
			$div.find('.Pages').children().remove();
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
			pages += '<span style="float: right;margin-top:10px;margin-right:20px;">总计: ' + data.page2.count + ' 条记录</span>';
			$div.find('.Pages').append(pages);
			setPageActive$($div);
		}else{
			$div.find('.Pages').children().remove();
			$div.find('.Pages').append('<span style="float: right;margin-top:10px;margin-right:20px;">总计: ' + data.page2.count + ' 条记录</span>');
		}
	} catch (e) {
		throw e;
	}
}
function setPageActiveAndPageNow$(now, $div) {
	try {
		if ($(now).parent().hasClass("disabled")) {
			return false;
		}
		var topage = parseInt($div.find('#pageNow').val(), 10);
		var isSubmit = false;
		if ($(now).is($div.find('.Pages ul li a').first())) {
			isSubmit = true;
			topage = topage - 1;
		} else if ($(now).is($div.find('.Pages ul li a').last())) {
			isSubmit = true;
			topage = topage + 1;
		} else if ($(now).text() == "┉" && $(now).parent().next().is($div.find('.Pages ul li').last())) {
			isSubmit = false;
			var lastpage = parseInt($(now).parent().prev().find("a").text(), 10);
			var nextlastpage = parseInt($div.find('#pageCount').val(), 10);
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
			$div.find('.Pages ul li').not($div.find('.Pages ul li').first()).not($div.find('.Pages ul li').last()).slideUp();
			$div.find('.Pages ul li').not($div.find('.Pages ul li').first()).not($div.find('.Pages ul li').last()).remove();
			$div.find('.Pages ul li').first().after(nextpages);
		} else if ($(now).text() == "┉" && $(now).parent().prev().is($div.find('.Pages ul li').first())) {
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
			$div.find('Pages ul li').not($div.find('.Pages ul li').first()).not($div.find('.Pages ul li').last()).slideUp();
			$div.find('.Pages ul li').not($div.find('.Pages ul li').first()).not($div.find('.Pages ul li').last()).remove();
			$div.find('.Pages ul li').first().after(prevpages);
		} else if ($div.find('.Pages ul li[class=active] a').is(now)) {
			isSubmit = false;
		} else {
			isSubmit = true;
			topage = parseInt($(now).text(), 10);
		}
		if (isSubmit) {
			$div.find('#pageNow').val(topage);
		} else {
			setPageActive$($div);
		}
		return isSubmit;
	} catch (e) {
		throw e;
	}
}
function setPageActive$($div) {
	try {
		$div.find('.Pages ul li').removeClass('active');
		if (findActivePage$($div) != null) {
			findActivePage$($div).parent().addClass("active");
		}
		if ($div.find('#pageNow').val() == $div.find('.Pages ul li').first().next().text()) {
			$div.find('.Pages ul li').first().addClass("disabled");
		} else if ($div.find('#pageNow').val() == $div.find('.Pages ul li').last().prev().text()) {
			$div.find('.Pages ul li').last().addClass("disabled");
		}
	} catch (e) {
		throw e;
	}
}

function findActivePage$($div) {
	try {
		var activePage = null;
		$div.find('.Pages ul li a').each(function() {
			if ($(this).text() == $div.find('#pageNow').val()) {
				activePage = $(this);
			}
		});
		return activePage;
	} catch (e) {
		throw e;
	}
}