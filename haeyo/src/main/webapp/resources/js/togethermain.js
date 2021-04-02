	$(document).ready(function(){
		//페이지 시작시 검색 자동 완성창 숨기기
		$('#previewSearch').hide();
	});
	
	//북마크
	function checkBookmark(tNo){
		var bookmark = $('#'+tNo).text();
		console.log(tNo+","+bookmark);
		var param = {"tNo":tNo,"bookmark":bookmark}; //json 문자열로 만들어 넘기기때문에 잘 넘어간다.
		$.ajax({
			url:"t_checkBookmark.do",
			type: "GET",
			contentType : "application/json; charset=UTF-8",
			data: param,
			success: function(result){ //result json객체 타입으로 받아짐
				console.log(result);
				if(result == 1){
					$('#'+tNo).addClass('select');
					$('#'+tNo).text(1);
				}else if(result == 0){
					$('#'+tNo).removeClass('select');
					$('#'+tNo).text(0);
				}
			}
		});
	}
		
	//검색창
	function searchBoardList(){
		var searchInput = $('#searchInput').val();
		var param = {"searchInput": searchInput}
		console.log(param);
		$.ajax({
			url:"t_searchBoardList.do",
			type:"GET",  //문제
			contentType:"application/json; charset=utf-8",
			data:param,
			dataType:"json",
			success: function(result){
				console.log(result);
				var html= "<div>검색결과</div>";
				for(i in result){
					html += "<div id='t_content'>"
					html +=	"  <div id='tAddress_Bookmark' class='clearfix'>"
					html += "    <div id='tAddress'>"+result[i].tAddress+"</div>"
					if(result[i].tBookmark == 1){
						html += "    	<div class='tBookmark select' id='"+result[i].tNo+"' onclick='tBookmark("+result[i].tNo+")'>"+result[i].tBookmark+"</div>"
					}else{
						html += "    	<div class='tBookmark' id='"+result[i].tNo+"' onclick='tBookmark("+result[i].tNo+")'>"+result[i].tBookmark+"</div>"
					}
					html += "  </div>"
					html += "  <div id='imgWrap'>"	
					html += "		<a href='t_getBoard.do?tNo="+result[i].tNo+"'> <img src='/WEB-INF/boardImages/"+result[i].tPic+"' height='250' width='250'></a>"	
					html += "  </div>"	
					html += "  <div id='tTitle'><span>"+result[i].tTitle+"</span></div>"
					html += "  <div id='tHeader'>"
					html += "		<button type='submit' class='heayo_btn'>"+result[i].tHeader+"</button>"
					html += "  </div>"
					html += "</div>"
				}
				$('#t_content_box').html(html);
				$('#sort_buttons').hide();
				$('#writingBoard').hide();
				
			}
		});
	//	$("span:contains('홍대')").css("color", "blue");  글자색바꾸기 나중에
	//	$("#tTitle:contains('"+searchInput+"')").css("color", "blue");
	}
	
	//검색창 자동완성(미리보기)
	function previewBoardList(){
		var searchInput = $('#searchInput').val();
		if(searchInput != ''){
			var param = {"searchInput": searchInput};
			console.log(param);
			$.ajax({
				url:"t_previewBoardList.do",
				type:"GET", 
				contentType:"application/json; charset=utf-8",
				data:param,
				dataType:"json",
				success: function(result){
					console.log(result);
					var html= "";
					for(i in result){
						html += "<a href='t_getBoard.do?tNo="+result[i].tNo+"'>"+result[i].tTitle+"</a></br>"
					}
					$('#previewSearch').html(html).show();
				}
			});
		}else{
			$('#previewSearch').hide();
			
		}
	} 
	
