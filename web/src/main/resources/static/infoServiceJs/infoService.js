$(function() {
	$.ajax({
		url : '/getInfoService',
		type : 'POST',
		dataType : 'json',
		complete : function(xhr, textStatus) {
			// called when complete
		},
		success : function(data, textStatus, xhr) {
			// called when successful
			var content = '';
			// console.log(data);
			for (key in data) {
				// statement
				var temp = data[key];
				content += '<tr><th scope="row">' + (new Number(key) + 1)
				+ '</th><td> <a target="_blank" href="./getInfoPhpContent?id='
				+ temp.id + '">' + temp.title + '</a></td><td>'
				+ temp.addTime + '</td><td>' + temp.updateTime
				+ '</td></tr>'
			}
			$('#coantainer').html(content);
		},
		error : function(xhr, textStatus, errorThrown) {
			// called when there is an error
		}
	});

});