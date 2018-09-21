$(function() {
	$(".business_left ul li a").hover(function() {
		$(this).css('-webkit-animation', 'Product 0.4s linear 0s both');
		$(this).css('animation', 'Product 0.4s linear 0s both');
	}, function() {
		$(this).css('-webkit-animation', 'Product1 0.2s linear 0s both');
		$(this).css('animation', 'Product1 0.2s linear 0s both');
	});
});

$(function() {
	$(window).scroll(function() {
		if ($(window).scrollTop() > 223) {
			$(".fixed").css({
				position : 'fixed',
				top : '0',
				left : '50%',
				"margin-left" : '-536px',
			});
		} else {
			$(".fixed").css({
				position : 'static',
				"margin-left" : '0',
			});
		}
	});
});

$(function() {
	try {
		if ($(".container-fluid").height() + 138 < $(window).height()) {
			$(".container-fluid").css({
				minHeight : $(window).height() - 138
			});
		}
	} catch (e) {
	}
})

// table中tr奇数偶数样式
$(function() {
	odd = {
		"background" : "#fff"
	};// 奇数样式
	even = {
		"background" : "#fcfcfc"
	};// 偶数样式
	odd_even(".data_table", odd, even);

	function odd_even(id, odd, even) {
		$(id).find("tr").each(function(index, element) {
			if (index % 2 == 1)
				$(this).css(odd);
			else
				$(this).css(even);
		});
	}
});
