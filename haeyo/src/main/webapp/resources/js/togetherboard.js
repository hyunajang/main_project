function insertReply(){
		var replyParam = $('#t_replyForm').serialize();
		console.log(replyParam);
		$('#trContent').val('');
		
		$.ajax({
			url:"t_insertReply.do",
			type: "GET",
			data: replyParam,
			contentType : "application/json; charset=UTF-8",
			dataType: "json",
			success: function(result){
				console.log(result);
				result="<tr id='trNo"+result.trNo+"'>"
					+"<td>"+result.userVO.uPic+"</td>"
					+"<th>"+result.userVO.uNick+"</th>"
					+"<td>"+result.trContent+"</td>"
					+"<td>"+result.trCredate+"</td>"
					+"<td><button class='heayo_btn' onclick='editReply("+result.trNo+","+result.tNo+");'>수정</button></td>"
					+"<td><button class='heayo_btn' onclick='deleteReply("+result.trNo+");'>삭제</button></td>"
					+"</tr>"
				$('#t_replyTable').append(result);
			}
		});
}


function deleteReply(trNo){
	var replyParam = {"trNo":trNo}
	console.log(replyParam);
	$.ajax({
		url:"t_deleteReply.do",
		type: "GET",
		contentType : "application/json; charset=UTF-8",
		data: replyParam,
		dataType: "json",
		success: function(result){
			console.log(result);
			if(result == 1 ){
				$('#trNo'+trNo).remove();
			}
		}
	});
}

function editReply(trNo,tNo){
	var uPic = $('#trNo'+trNo).children().eq(0).text();
	var uNick = $('#trNo'+trNo).children().eq(1).text();
	var trContent = $('#trNo'+trNo).children().eq(2).text();
	var trCredate = $('#trNo'+trNo).children().eq(3).text();
	console.log(uPic+","+uNick+","+trContent+","+trCredate);
	updateReplyInput = "<td>"+uPic+"</td>"
					+"<th>"+uNick+"</th>"
					+"<td><textarea cols='40' rows='3' id='updateTrContent'"
					+"name='updateTrContent'>"+trContent+"</textarea></td>"
					+"<td>"+trCredate+"</td>"
					+"<td><button class='heayo_btn' onclick='updateReply("+trNo+",\""+trContent+"\");'>등록</button></a></td>"
					+"<td><button class='heayo_btn' onclick='cancleReply("+trNo+","+tNo+",\""+trContent+"\");'>취소</button></a></td>";
	$('#trNo'+trNo).html(updateReplyInput);	
}

function cancleReply(trNo,tNo,trContent){
	var uPic = $('#trNo'+trNo).children().eq(0).text();
	var uNick = $('#trNo'+trNo).children().eq(1).text();
	var trCredate = $('#trNo'+trNo).children().eq(3).text();
	
	updateReplyInput = "<td>"+uPic+"</td>"
					+"<th>"+uNick+"</th>"
					+"<td>"+trContent+"</td>"
					+"<td>"+trCredate+"</td>"
					+"<td><button class='heayo_btn' onclick='editReply("+trNo+","+tNo+");'>수정</button></td>"
					+"<td><button class='heayo_btn' onclick='deleteReply("+trNo+");'>삭제</button></td>";
	console.log(uPic+","+uNick+","+trContent+","+trCredate);
	$('#trNo'+trNo).html(updateReplyInput);
}

function updateReply(trNo, trContent){
	var trContent = $('#updateTrContent').val();
	var param = {"trNo":trNo, "trContent":trContent}
	console.log(param);
	$.ajax({
		url:"t_updateReply.do",
		type: "GET",
		contentType : "application/json; charset=UTF-8",
		data: param,
		success: function(result){
			console.log(result);
			var	t_reply_content = "<tr id='trNo"+result.trNo+"'>"
				 + "<td>"+result.userVO.uPic+"</td>"
				 + "<th>"+result.userVO.uNick+"</th>"
				 + "<td>"+result.trContent+"</td>"
				 + "<td>"+result.trCredate+"</td>"
				 + "<td><button class='heayo_btn' onclick='editReply("+trNo+","+result.tNo+");'>수정</button></td>"
				 + "<td><button class='heayo_btn' onclick='deleteReply("+trNo+");'>삭제</button></td>"
				 + "</tr>";
			console.log(t_reply_content);
			$('#trNo'+trNo).replaceWith(t_reply_content);
		}
	});
}

function tJoin(tNo){
	var param = {"tNo":tNo};
	console.log(param);
	$.ajax({
		url:"t_joinNow.do",
		type: "GET",
		contentType : "application/json; charset=UTF-8",
		data: param,
		dataType: 'json',
		success: function(result){
			console.log(result);
			joinMember = result.tJoinNow+"/"+result.tJoinMax
			$('#joinCount').text(joinMember);
			
//			alert("이미 참여신청을 하셨습니다.")
		}
	});
}

$(document).ready(function(){
	var tBookmark = $('#tBookmark').text();
	console.log(tBookmark)
	if(tBookmark == 1){
		$('#tBookmark').addClass('select');
	}
});


function tBookmark(tNo,tBookmark){
	
	if(tBookmark == 1){
		tBookmark = 0
	}else if(tBookmark == 0){
		tBookmark = 1
	}
	
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

