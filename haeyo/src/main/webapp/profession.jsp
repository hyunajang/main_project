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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="resources/css/header.css" rel="stylesheet">
<link href="resources/css/profession/professionList.css" rel="stylesheet">
<script src="resources/js/jquery-1.12.4.min.js"></script>
<script src="resources/js/profession/professionList.js" defer></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
</script>
</head>
<body>
	<%
	session = request.getSession();
	UserVO user = (UserVO) session.getAttribute("user");
	%>
	<wrap> <!---------------------HeaderStart--------------------->
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
	<!---------------------MainStrart--------------------->
	<main>
		<section id="recommendWrap">
			<div id="recommend" class="clearfix">
				<h1>맞춤 전문가</h1>
				<c:forEach var="proRecom" items="${proRecom}">
					<div class="box">
						<a href="detail.do?pNo=${proRecom.pNo}&pCategory=${proRecom.pCategory}">
							<div class="box_image">
								<span class="pCate">${proRecom.pCategory}</span>
								<h1>${proRecom.uName}</h1>
								<h2>${proRecom.pAddress}</h2>
								<div class="starWrap">
									<ul class="star">
										<li><i class="fas fa-star"></i></li>
										<li><i class="fas fa-star"></i></li>
										<li><i class="fas fa-star"></i></li>
										<li><i class="fas fa-star"></i></li>
										<li><i class="fas fa-star-half-alt"></i></li>
									</ul>
									<span class="score">${proRecom.score}</span>
								</div>
							</div>
						</a> <span class="profile_img"><img
							src="resources/image/${proRecom.pPic}.jpg"></span>
						<div class="book"></div>
					</div>
				</c:forEach>

				<h3 id="location">
					<i class="fas fa-map-marker-alt"></i> 서울시 마포구
				</h3>
			</div>
		</section>

		<section id="listWrap">
			<div id="professionList">
				<h1>전문가 리스트</h1>
				<select>
					<option>등록순</option>
					<option>거리순</option>
					<option>리뷰순</option>
				</select>
				<div id="proWrap">
					<c:forEach var="proList" items="${proList}">
					<div class="pro clearfix">
						<span class="proList_img"><img src="resources/image/${proList.pPic}.jpg"></span>
						<div class="pro_txt">
							<div class="proName_txt clearfix">
								<h1>${proList.uName}</h1>
								<span class="pCate">${proList.pCategory}</span>
							</div>
							<h2>${proList.pAddress}</h2>
							<p>🎈하늘을 날아갈 듯한 깨끗함을 만들어 드립니다! 청소 전문가 ㅠ장현아!</p>
							<div class="starWrap">
								<ul class="star clearfix">
									<li><i class="fas fa-star"></i></li>
									<li><i class="fas fa-star"></i></li>
									<li><i class="fas fa-star"></i></li>
									<li><i class="fas fa-star"></i></li>
									<li><i class="fas fa-star-half-alt"></i></li>
								</ul>
								<span class="score">${proList.score}</span>
							</div>
							<div class="rihgt_txt">
								<span class="book2"></span>
								<button class="proList_btn">
									상세 보기 <i class="fas fa-arrow-right"></i>
								</button>
							</div>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>

		</section>
	</main>
	<!---------------------MainEnd--------------------->
	<!------- footerStart ------->
	<footer id="footer">
		<section id="bottom" class="clearfix">
			<div class="phone">
				<p>
					<span>1599-2121</span>
				</p>
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
			<p>(주)해요모바일은 통신판매중개자로서 통신판매의 당사자가 아니며 개별 판매자가 제공하는 서비스에 대한 이행,
				계약사항 등과 관련한 의무와 책임은 거래당사자에게 있습니다.</p>
			<p>상호명:(주)해요모바일 · 대표이사:1조 · 개인정보책임관리자:1조 · 주소:서울특별시 마포구 거구장, 지하
				1층(커틀 타워)</p>
			<p>사업자등록번호:111-22-34323 · 통신판매업신고증:제 2015-서울강남-00567 호 · 직업정보제공사업
				신고번호:서울청 제 2019-21호</p>
			<p>고객센터:1599-2121 · 이메일:heayo@heayo.com</p>
			<p class="copy">Copyright heayo Web Inc. All Rights Reserved.</p>
		</section>
	</footer>
</body>

</html>