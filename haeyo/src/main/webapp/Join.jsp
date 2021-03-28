<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Join</title>
<link href="resources/image/icon.ico" rel="shortcut icon"
	type="image/x-icon">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="resources/css/join.css" rel="stylesheet">
<link href="resources/css/header.css" rel="stylesheet">
<script type="text/javascript" src="resources/js/join.js" defer></script>
<script defer src="resources/js/common.js"></script>
<script defer src="resources/js/join_addr.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52c7f3d664707319c1c8977f5f4a2421&libraries=services"></script>
</head>

<body>
	<wrap> <!--modalStart-->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">어떤 서비스를
						원하시나요?</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="modal-body">
					<form action="#" method="get">
						<button type="button" class="btn-secondary"
							onclick="location.href='#.jsp'">수 리</button>
						<button type="button" class="btn-secondary">이 사</button>
						<button type="button" class="btn-secondary">청 소</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--modalEnd--> <!------- headerStart -------> <header id="header">
		<nav id="nav">
			<logo id="logo"> <a href="index.html"><img
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
						aria-haspopup="true" aria-expanded="false">회원님</div>
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
	</header> <!------- headerEND -------> <!------- joinStart ------->
	<div id="join-body">
		<div id="join_title">
			<h1>함께</h1>
			<span><img src="resources/image/logo2.png"></span>
		</div>
		<form action="insertUser.do" name=form method="post"
			onsubmit="return join_check();">
			<!-- <form action="insertUser.do" method="post" enctype="text/plain" > -->
			<fieldset>
				<div class="form-group">
					<div id="userpic-wrapper">
						<img id="thumb-img"> <label class="click-icon"
							for="user-thumb"><i class="fas fa-camera"></i></label> <input
							type="file" name="file" id="user-thumb"
							class="upload-box upload-plus" accept="image/*"
							onchange="thumbnail(event)">
					</div>
				</div>
				<div class="form-group">
					<label for="uName">이 름</label> <input type="text" name="uName"
						class="form-control" id="uName" placeholder="이수민">
				</div>
				<div class="form-group">
					<label for="uNick">별 명</label> <input type="text" name="uNick"
						class="form-control" id="uNick" placeholder="Zn">
				</div>
				<!-- 이메일인증 -->
		<div class="form-group">
			<label for="uEmail">이메일 인증</label> 
			<input type="text" name="uEmail" class="form-control" placeholder="Enter email">
		</div>
		<div class="form-inline mb-3">
			<div class="mail_check_input_box" id="mail_check_input_box_false">
				<input type="text" class="mail_check_input form-control col-8" disabled="disabled">
				<button id="mail_check_button" class="btn btn-outline-primary btn-sm">인증번호 전송</button>
			</div>
		</div>
		<!-- 인증번호 확인 -->
		<div class="alert alert-success" id="alert-success-email">인증번호가 일치합니다.</div>
		<div class="alert alert-danger" id="alert-danger-email">인증번호가 일치하지 않습니다.</div>
				<div class="form-group">
					<label for="uEmail">이메일</label> <input type="text" name="uEmail"
						class="form-control" id="uEmail" placeholder="email@bit.com">
				</div>
				<div class="form-group">
					<label for="uPhone">휴대전화번호</label> <input type="text" name="uPhone"
						class="form-control" id="uPhone" placeholder="010-1234-5678">
				</div>
				<div class="form-group">
					<label for="uPwd">비밀번호</label> <input type="password" name="uPwd"
						class="form-control" id="uPwd"
						placeholder="영문+숫자 조합 8자리 이상 입력해주세요">
				</div>
				<div class="form-group">
					<label for="uPwd">비밀번호 확인</label> <input type="password"
						name="uPwd2" class="form-control" id="uPwd2" placeholder="">
				</div>
				<div class="form-group">
					<div id="address-group" class="clearfix">
						<label for="uAddress">주 소</label> <input type="text"
							name="uAddress" class="form-control" id="uAddress"
							placeholder="서울시 마포구"> <input type="button"
							id="searchBtn" class="button_adrr"
							onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
						<div id="map"></div>
						<input type="hidden" id="uY" name="uLat" value="" /> <input
							type="hidden" id="uX" name="uLon" value="" />
					</div>
				</div>

				<div class="form-group">
					<div class="uAgreecheck">
						<input type="checkbox" class="uAgreecheck" id="uAgreecheck1"
							name="uAgreecheck" value="1"> <input type="hidden"
							class="uAgreecheck" id="uAgreecheck0" name="uAgreecheck"
							value="0"><label class="uAgreecheck-label"
							for="uAgreecheck">
							<p>
								'해요'의 <span>이용약관</span> 및 <span>개인정보 처리방침</span>에 동의합니다
							</p>
						</label> <a href="Agree.jsp" target="_blank">자세히</a>
					</div>
				</div>
				<div class="box">
					<div id="join1-button">
						<input type="submit" name="join" class="btn-secondary"
							value="회 원 가 입" />
					</div>
					<div id="join2-button">

						<!-- <button type="submit" class="btn-secondary"
							onclick="location.href='Join_pro.jsp'">전문가로 가입하기</button>
 						-->
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<!------- joinEnd -------> <!------- footerStart -------> <footer
		id="footer">
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
	</footer> <!------- footerEND -------> </wrap>
</body>

</html>