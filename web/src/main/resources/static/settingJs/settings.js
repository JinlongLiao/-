$(function() {

});

function doSort(id, direction) {
	var result = confirm("是否确定调正 顺序");
	if (result) {
		$.ajax({
			url: '/sortNav',
			type: 'GET',
			dataType: 'json',
			data: {
				'dir': direction,
				'sort': id
			},
		}).done(function() {
			// 存储 localStorage 中
			if (direction == 1) {
				sessionStorage.port = id - 1;
			} else {
				sessionStorage.port = id + 1;
			}
			location.reload();
			console.log("success");
		}).fail(function(error) {
			console.log(error);
		}).always(function() {
			console.log("complete");
		});
	}
};