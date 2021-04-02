	//북마크
	function tBookmark(tNo){
		var bookmark = $('#'+tNo).text();
		console.log("tNo: "+tNo+", bookmark: "+bookmark);
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
	
	//카테고리별 리스트
	function getTboardList(tHeader, nowPage){
		console.log("tHeader:"+tHeader);
		var param = {"tHeader": tHeader, "nowPage": nowPage};
		
		$.ajax({
			url:"t_getBoardList.do",
			type:"GET",
			contentType: "application/json; charset=UTF-8",
			data: param,
			success: function(result){
				var boardList = result.boardList;
				var paging = result.paging;
				console.log(result);
				
				//글 목록 출력
				var html1 = getCommonBoardList(boardList);
				$('#t_content_box').html(html1);
				
				//분류별 필터 버튼추가
				var html2= "";
				html2 +="<div id='sort_buttons'>"
					 +"<button class='sort_button heayo_btn' onclick='sortBoardList(\"조회수순\",\""+tHeader+"\")'>조회수순</button>"
					 +"<button class='sort_button heayo_btn' onclick='sortBoardList(\"북마크 인기순\",\""+tHeader+"\")'>북마크 인기순</button>"
					 +"<button class='sort_button heayo_btn' onclick='sortBoardList(\"남은인원 많은순\",\""+tHeader+"\")'>남은인원 많은순</button>"
					 +"<button class='sort_button heayo_btn' onclick='sortBoardList(\"최대인원 많은순\",\""+tHeader+"\")'>최대인원 많은순</button>"
					 +"</div>"
				$('#upper_box').html(html2);
				
				//페이징
				var html3 = "";
				html3 += '<ul class="pagination">'
						    +'<li class="page-item disabled">'
						    +  '<a class="page-link" href="#">&laquo;</a></li>'
						    +'<li class="page-item active">'
						    +  '<a class="page-link" id="paging1" href="#" onclick="getTboardList(\''+tHeader+'\','+ paging.startPage+')">'+paging.startPage+'</a></li>'
						    +'<li class="page-item">'
						    +  '<a class="page-link" id="paging2" href="#" onclick="getTboardList(\''+tHeader+'\','+ (paging.startPage+1)+')">'+(paging.startPage+1)+'</a></li>'
						    +'<li class="page-item">'
						    +  '<a class="page-link" id="paging3" href="#" onclick="getTboardList(\''+tHeader+'\','+ (paging.startPage+2)+')">'+(paging.startPage+2)+'</a></li>'
						    +'<li class="page-item">'
						    +  '<a class="page-link" id="paging4" href="#" onclick="getTboardList(\''+tHeader+'\','+ (paging.startPage+3)+')">'+(paging.startPage+3)+'</a></li>'
						    +'<li class="page-item">'
						    +  '<a class="page-link" id="paging5" href="#" onclick="getTboardList(\''+tHeader+'\','+ (paging.startPage+4)+')">'+(paging.startPage+4)+'</a></li>'
						    +'<li class="page-item">'
						    +  '<a class="page-link" id="" href="#">&raquo;</a></li>'
						 +'</ul>';
				$('#paging_box').html(html3);
			}
		});
		
		$('#writingBoard').show();
		$('#sort_buttons').show();
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
				html += getCommonBoardList(result);
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
	
	//분류별 정렬
	function sortBoardList(sortInput, tHeader){
		var param = {"sortInput": sortInput,"tHeader": tHeader};
		console.log(param);
		$.ajax({
			url:"t_sortBoardList.do",
			type:"get",
			contentType:"application/json; charset=utf-8",
			data:param,
			dataType: "json",
			success: function(result){
				console.log(result);
				
				var html = getCommonBoardList(result);
				$('#t_content_box').html(html);
			}
		});
	}
	
	//공통 리스트 출력 메서드
	function getCommonBoardList(result){
		var html= "";
		for(i in result){
			console.log(result[i].tBookmarkVO);
			html += "<div id='t_content'>"
			html +=	"  <div id='tAddress_Bookmark' class='clearfix'>"
			html += "    <div id='tAddress'>"+result[i].tAddress+"</div>"
			if(result[i].tBookmarkVO != null){
				html += "    	<div class='tBookmark select' id='"+result[i].tNo+"' onclick='tBookmark("+result[i].tNo+")'>1</div>"
			}else{
				html += "    	<div class='tBookmark' id='"+result[i].tNo+"' onclick='tBookmark("+result[i].tNo+")'>0</div>"
			}
			html += "  </div>"
			html += "  <div id='imgWrap'>"	
			html += "		<a href='t_getBoard.do?tNo="+result[i].tNo+"'> <img src='/WEB-INF/boardImages/"+result[i].tPic+"' height='250' width='250'></a>"	
			html += "  </div>"	
			html += "  <div id='tTitle'>"+result[i].tTitle+"</div>"
			html += "  <div id='tHeader'>"
			html += "		<button type='submit' class='heayo_btn'>"+result[i].tHeader+"</button>"
			html += "  </div>"
			html += "</div>"
		}
		
		return html;
	}
	
	function paging(result){
		
	}
	
	//페이징 체크 변화
	$(function(){
		var pBtn = $("ul.pagination > li");
		pBtn.find("a").click(function(){ //li의 자식 태그a 찾아 이벤트 등록
			pBtn.removeClass('active');  //li에 active 클래스제거
			$(this).parent().addClass('active'); //눌른 버튼(a)의 부모인 li에  active클래스 추가
			
		})
	})
	
