<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.haeyo.biz.user.UserVO"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>전문가 리스트</title>
<link href="resources/image/icon.ico" rel="shortcut icon"
	type="image/x-icon">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="resources/css/header.css" rel="stylesheet">
<link href="resources/css/profession/professionDetail.css"
	rel="stylesheet">
<script src="resources/js/jquery-1.12.4.min.js"></script>
<!--  <script src="http://code.jquery.com/jquery-latest.min.js"></script>-->

<script src="resources/js/profession/professionDetail.js" defer></script>
<!-- <script src="resources/js/profession/professionDeatil.js" defer></script> -->
<script>
let proObj = {
		pNo: ${proDetail.pNo},
		pCategory: "${proDetail.pCategory}"
}


window.onload = function(){
	ajaxTest(proObj);
}

function ajaxTest(proObj){
	$.get({ // 컨트롤러와 통신
			data: proObj,
			url: "subCategory.do",
			success : function(data) {
				console.log(data);
				console.log(JSON.parse(data));
				console.log(data.pToilet);
				console.log();
				//정보 추출
				//console.log(subCategory);
				//subCategory();
				obj = JSON.parse(data);
				let array = [];
				//해당 배열 담기
				for(prop in obj)  {
					if(obj[prop] == 1){
						document.getElementById(prop).style.display = "flex";
						array.push(prop);
					}
				}
				console.log(array);
				labelAdd(array);
			}
	});
}
function labelAdd(array){
	const rsv = "rsv";
	const sub_category = document.getElementById("sub_category");
    for(i=0; i< array.length; i++){
        const li = document.createElement("li");
        const label = document.createElement("label");
        const input = document.createElement("input");
        label.htmlFor = array[i];
        label.innerText = document.getElementById(array[i]).innerText;
        label.addEventListener("click", select);
        input.type = "checkbox";
        input.name = rsv.concat(array[i].substring(1));
        input.value = document.getElementById(array[i]).innerText;
        input.id = array[i];
        li.appendChild(input);
        li.appendChild(label);
        sub_category.appendChild(li);
    }
    
}

function select() {
    (this.classList.contains("select")) ? this.classList.remove("select") : this.classList.add("select");
}
</script>
</head>
<body>
	<%
	session = request.getSession();
	UserVO user = (UserVO) session.getAttribute("user");
	%>
	<wrap> <!---------------------HeaderStart--------------------->
	<!--modalStart-->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
            aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">
                            <p>${proDetail.uName} 전문가님에게</p>어떤 서비스 견적을 받고 싶으신가요?
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div id="modal-body">
                        <form action="application.do" method="GET">
                        	<input type="hidden" name="pNO" value="${proDetail.pNo}"/>
                        	<input type="hidden" name="pCategory" value="${proDetail.pCategory}"/>
                            <div id="sub_cateWrap">
                                <ul id="sub_category" class="clearfix">
                                </ul>
                            </div>
                            <button type="submit" class="btn-secondary">제출하기</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--modalEnd-->
	<header id="header">
		<nav id="nav">
			<logo id="logo"> <a href="index"><img
				src="resources/image/logo2.png" alt="로고"></a> </logo>
			<ul>
				<li><a href="#">같이해요</a></li>
				<li><a href="#">혼자해요</a></li>
				<li><a href="#">전문가 신청</a></li>
				<li class="btn-group">
					<ul class="dropdown-toggle bell" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">
						<i class="fas fa-bell"></i>
					</ul>
					<div class="dropdown-menu dropdown-menu-right alarm1">
						<p>알림</p>
						<div id="alarmTxt">회원님이 결제를 완료하셨습니다.</div>
						<span>23분전</span>
					</div>
				</li>
				<li class="btn-group">
					<div class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">${user.uNick}님</div>
					<div class="dropdown-menu dropdown-menu-right" id="profile">
						<div id="profiteTxt">
							<span>반갑습니다! </span>${user.uNick}님</div>
						<ul class="profileMenu">
							<li><i class="fas fa-user menuico"></i>마이페이지</li>
							<li><i class="fas fa-sign-out-alt menuico"></i>로그아웃</li>
							<li><i class="fas fa-exchange-alt menuico"></i>전문가로 전환</li>
						</ul>
					</div>
				</li>
			</ul>
		</nav>
	</header> <!---------------------headerEnd--------------------->
	<main>
		<section id="profileWrap">
			<div id="profile_top">
				<h1>전문가 상세 정보</h1>
				<h3>
					<i class="fas fa-map-marker-alt"></i> ${proDetail.pAddress}
				</h3>
			</div>

			<div id="profile_t" class="clearfix">
				<div id="pro_img" class="clearfix">
					<img src="resources/image/${proDetail.pPic}.jpg">
				</div>
				<div id="pro_txt">
					<div id="nameWrap">
						<h2>${proDetail.uName}</h2>
						<span id="pcategory">${proDetail.pCategory}</span>
					</div>
					<div class="starWrap clearfix">
						<ul class="star clearfix">
							<li><i class="fas fa-star"></i></li>
							<li><i class="fas fa-star"></i></li>
							<li><i class="fas fa-star"></i></li>
							<li><i class="fas fa-star"></i></li>
							<li><i class="fas fa-star-half-alt"></i></li>
						</ul>
						<span class="score">${proDetail.score}</span>
					</div>
					<ul id="pro_rev">
						<li>후기 <span id="pro_span">${proDetail.count}</span></li>
						<li>찜하기 <span>2</span></li>
					</ul>
				</div>
				<div id="pro_req">
					<div id="book">북마크</div>
					<button id="reqBtn" data-toggle="modal"
						data-target="#exampleModalCenter">견적 요청하기</button>
				</div>
			</div>
		</section>

		<section id="contentsWrap">
			<div id="con01">
				<h3>한줄소개</h3>
				<p>${proDetail.pIntroduce}</p>
			</div>
			<div id="con02" class="clearfix">
				<div id="subCon01">
					<h3>추가 데이터</h3>
					<!-- <span><img src="resources/image/${proDetail.certification}.jpg"></span> -->
				</div>
				<div id="subCon02">
					<h3>제공 서비스</h3>
                    <div id="pLiving">생활수리</div><div id="pElectric">전기설치수리</div><div id="pDoor">문설치수리</div><div id="pOneroom">소형원룸이사</div><div id="pKeep">짐보관</div><div id="pRefrigerator">냉장고청소</div>
                    <div id="pToilet">화장실청소</div><div id="pTransportaition">용달화물운송</div><div id="pWaterpipe">수도설치수리</div>
                    <div id="pTrash">철거정리</div><div id="pArrangement">정리수납컨설팅</div><div id="pHomein">입주이사청소</div>
				</div>
			</div>
			<div id="con03">
				<h3>일정</h3>
				<button>
					<i class="far fa-calendar-alt"></i> 전문가 일정 확인하기<i class="fas fa-arrow-right"></i>
				</button>
				<!-- <div id="schedual">일정</div>-->
				<a href="#Top">
					<div id="top">
						<i class="fas fa-arrow-up"></i>
					</div>
				</a>
			</div>
			<!--<div id="reqBtn_b">견적 요청하기</div>-->
		</section>

		<section id="reviewWrap">
			<div id="reviewTit">
				<h1>
					후기 <span>${proDetail.count}</span>
				</h1>
			</div>
			<c:forEach var="proReview" items="${proReview}">
				<div class="scoreWrap clearfix">
					<ul class="star clearfix">
						<li><i class="fas fa-star"></i></li>
						<li><i class="fas fa-star"></i></li>
						<li><i class="fas fa-star"></i></li>
						<li><i class="fas fa-star"></i></li>
						<li><i class="fas fa-star-half-alt"></i></li>
					</ul>
					<span class="score">${proReview.score}</span> <span class="redate">${proReview.reviewDate}</span>
				</div>
				<div class="userReview clearfix">
					<span class="userImage"><img src="resources/image/${proReview.uPic}.jpg"></span>
					<h1>${proReview.uNick}</h1>
					<p>${proReview.reviewContent}</p>
					<button type="button">수정</button>
					<button type="button">삭제</button>
				</div>
			</c:forEach>
		</section>
	</main>
	<!--main -->
	        <!------- footerStart ------->
        <footer id="footer">
            <section id="bottom" class="clearfix">
                <div class="phone">
                    <p><span>1599-2121</span></p>
                    <p>평일 10:00 - 18:00</p>
                    <p>(점심시간 13:00 - 14:00 제외·주말/공휴일 제외)</p>
                    <ul class="sns">
                        <li><i class="fab fa-facebook"></i></li>
                        <li><i class="fab fa-instagram"></i></li>
                        <li><i class="fab fa-youtube"></i></li>
                    </ul>
                </div>
                <div class="service">
                    <ul>
                        <li class="sevName">해요소개</li>
                        <li>회사소개</li>
                        <li>채용안내</li>
                    </ul>
                    <ul>
                        <li class="sevName">고객안내</li>
                        <li>이용안내</li>
                        <li>안전정책</li>
                        <li>예상금액</li>
                        <li>전문가소개</li>
                    </ul>
                    <ul>
                        <li class="sevName">전문가안내</li>
                        <li>이용안내</li>
                        <li>전문가가이트</li>
                        <li>전문가가입</li>
                    </ul>
                    <ul>
                        <li class="sevName">고객센터</li>
                        <li>공지사항</li>
                        <li>자주묻는질문</li>
                    </ul>
                </div>
            </section>
            <section id="bottomtxt">
                <p>(주)해요모바일은 통신판매중개자로서 통신판매의 당사자가 아니며 개별 판매자가 제공하는 서비스에 대한 이행, 계약사항 등과 관련한 의무와 책임은 거래당사자에게 있습니다.</p>
                <p>상호명:(주)해요모바일 · 대표이사:1조 · 개인정보책임관리자:1조 · 주소:서울특별시 마포구 거구장, 지하 1층(커틀 타워)</p>
                <p>사업자등록번호:111-22-34323 · 통신판매업신고증:제 2015-서울강남-00567 호 · 직업정보제공사업 신고번호:서울청 제 2019-21호</p>
                <p>고객센터:1599-2121 · 이메일:heayo@heayo.com</p>
                <p class="copy">Copyright heayo Web Inc. All Rights Reserved.</p>
            </section>
        </footer>
        <!------- footerEND ------->
	</wrap>
</body>
</html>