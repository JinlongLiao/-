$(function() {
	$.ajax({
		url : './navigation',
		type : 'POST',
		dataType : 'json',
	// data: {param1: 'value1'},
}).done(
function(data) {
	var tempHtml = '';
	for (var i = 0; i < data.length; i++) {
		var temp = data[i];
					// console.log(temp);
					// 获取localStorage 中的 标记
					var port=sessionStorage.port;
					if (typeof(port)=='undefined') {
						localStorage.port=2;
						window.href=location.hostname
					}
					if (i == 0) {
						$('#navigation').html(temp.title)
					} else if (i == 1) {
						$('#main').html(temp.title);
						$('#main').attr('href', temp.url);
						$('#main').attr('target', temp.url);
						// console.log(temp.title);

					} else if (i == port) {
						tempHtml = tempHtml + '<li class="active" onclick=doPort('+i+')><a href="'
						+ temp.url + '" target="' + temp.target + '">'
						+ temp.title + '</a></li>';
					} else {
						tempHtml = tempHtml + '<li onclick=doPort('+i+')><a href="' + temp.url
						+ '" target="' + temp.target + '">'
						+ temp.title + '</a></li>';
					}
				}
				// console.log(tempHtml);
				$('#nav-bar').html(tempHtml);
				console.log("success");
			}).fail(function() {
				console.log("error");
			}).always(function() {
				console.log("complete");
			});

		});
function doPort(port){
// 存储 localStorage 中
	sessionStorage.port=port;
}