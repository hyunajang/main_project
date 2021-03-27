$(document).ready(function(){
	var tBookmark = $('#tBookmark').text();
	console.log(tBookmark)
	if(tBookmark == 1){
		$('#tBookmark').addClass('select');
	}
});

function tBookmark(tNo,tBookmark){
	console.log(tNo+","+tBookmark);
	var param = {"tNo":tNo,"tBookmark":tBookmark};
	$.ajax({
		url:"t_checkBookmark.do",
		type: "GET",
		contentType : "application/json; charset=UTF-8",
		data: param,
		success: function(result){
			console.log(result);
			if(result == 1){
				$('#tBookmark').addClass('select');
			}
		}
	});
}