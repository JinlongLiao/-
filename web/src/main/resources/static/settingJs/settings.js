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
/**
模态框设置
*/
function modelShow(orders) {
	$.ajax({
			url: './getNavByOrder',
			type: 'POST',
			dataType: 'json',
			data: {
				order: orders
			},
		})
		.done(function(datas) {
			$('#myModal').modal({
				keyboard: false
			})

			$("#id").val(datas.id);
			$("#order").val(datas.order);
			$("#title").val(datas.title);
			$("#content").val(datas.content);
			$("#url").val(datas.url);
			$("#desc").val(datas.desc);
			$('#targets').selectpicker('val', datas.target);
		})
		.fail(function() {
			console.log("error");
		})
		.always(function() {
			console.log("complete");
		});
}
// 修改 nav 信息
function doUpdateNav() {
	var data = {};
	data.id = $("#id").val();
	data.order = $("#order").val();
	data.title = $("#title").val();
	data.context = $("#content").val();
	data.url = $("#url").val();
	data.desc = $("#desc").val();
	data.target = $('#targets').selectpicker('val');
	$.post('/updateNavs', data, function(data, textStatus, xhr) {
		/*optional stuff to do after success */
		console.log(data)

	}, 'json');
}