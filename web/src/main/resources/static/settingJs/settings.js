$(function() {

});
function doSort(id, direction) {
	alert(id+""+direction)
	$.ajax({
		url : './sortNav',
		type : 'GET',
		dataType : 'json',
		data : {
			'dir' : direction,
			'sort' : id
		},
	}).done(function() {
		console.log("success");
	}).fail(function(error) {
		console.log(error);
	}).always(function() {
		console.log("complete");
	});
};
