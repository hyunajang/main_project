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
    <title>해요-체크리스트</title>
    <link href="../image/icon.ico" rel="shortcut icon" type="image/x-icon">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="resources/css/header.css" rel="stylesheet">
    <link href="resources/css/profession/application.css" rel="stylesheet">
    <script src="resources/js/jquery-1.12.4.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=75d6c9cf371d5f0cd64d40592c350582&libraries=services"></script>
	<script src="resources/js/profession/application.js" defer></script>
    <script>
    window.onload = function(){
    	let subCate = ${Catetxt};
    	insertCate(subCate);
    }
    </script>
<body>
    <wrap>
        <!---------------------HeaderStart--------------------->
        <header id="header">
            <nav id="nav">
                <logo id="logo">
                    <a href="index.html"><img src="resources/image/logo2.png" alt="로고"></a>
                </logo>
                <ul>
                    <li><a href="#">같이해요</a></li>
                    <li><a href="#">혼자해요</a></li>
                    <li><a href="#">전문가 신청</a></li>
                    <li class="btn-group">
                        <ul class="dropdown-toggle bell" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-bell"></i></ul>
                        <div class="dropdown-menu dropdown-menu-right alarm1">
                            <p>알림</p>
                            <div id="alarmTxt">회원님이 결제를 완료하셨습니다.</div>
                            <span>23분전</span>
                        </div>
                    </li>
                    <li class="btn-group">
                        <div class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">회원님</div>
                        <div class="dropdown-menu dropdown-menu-right" id="profile">
                            <div id="profiteTxt"><span>반갑습니다! </span>회원님</div>
                            <ul class="profileMenu">
                                <li><i class="fas fa-user menuico"></i>마이페이지</li>
                                <li><i class="fas fa-sign-out-alt menuico"></i>로그아웃</li>
                                <li><i class="fas fa-exchange-alt menuico"></i>전문가로 전환</li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </header>
        <!---------------------headerEnd--------------------->

         <!---------------------main--------------------->
         <main>
            <section id="conbg" class="clearfix">
                <div id="requestlistWrap" class="clearfix">
                    <div id="requestlist" class="clearfix">
                        <p>전문가 신청서</p>
                        <form action="insertReser.do" method="GET">
                        	<input type="hidden" id="pNo" name="pNo" value="">
                        	<input type="hidden" id="rsvCategory" name="rsvCategory" value=""/>
                            <div class="box">
                                <ul id="sub_category" class="clearfix">
                                </ul>
                            </div>
                            <div class="box">
                                <label>사진을 첨부하시겠어요?</label><br>
                                <ul>
                                    <li><input type="radio" name="rsvPic" value="Y" class="radio_btn" onclick="value_check()"><span>예</span></li>
                                    <li><input type="radio" name="rsvPic" value="null" class="radio_btn" onclick="value_check()"><span>아니요</span></li>
                                </ul>
                                <div id="photoWrap">
                                    <div id="btn_wrap" class="clearfix">
                                        <button type="button" onclick="addFile()"><i class="fas fa-plus"></i></button>
                                    </div>
                                    <div id="fileWrap" class="clearfix">
                                        <label for="pic-thumb"></label>
                                       <!--   <input type="file" name="rsvPic" value="" id="pic-thumb" class="filebtn" accept="image/png, image/jpeg" onchange="thumbnail(event)"/>
                                        <button type="button">-</button>-->
                                    </div>
                                    <div id="photo" class="clearfix">
                                        <div id="thumb-img" class="pic_thumb"><img src=""></div>
                                    </div>
                                </div>
                            </div>
                            <div class="box">
                                <label>어느 지역이신가요?</label><br>
                                <input type="text" id="searchLoc" name="rsvLoc"/><input type="button" id="searchBtn" onclick="PostCode()" value="주소 검색"/>
                                <div id="map"></div>
                            </div>
                            <div class="box">
                                <label>일정을 입력해주세요</label><br>
                               	<input type="date" id="rsvDate" name="rsvDate" value=""/>
                            </div>
                            <div class="box">
                                <label>세부사항을 입력해주세요</label><br>
                                <textarea name="rsvDetail" cols="50" rows="5"></textarea><br>
                            </div>
                            <button type="submit" class="heayo_btn">제출하기</button>
                        </form>
                    </div>
                    <div id="checkInfo">
                        <h3><i class="far fa-check-circle"></i> 전문가 신청서</h3>
                        <p>사소하고 작은 서비스가 필요하셨나요?<br/>
                            해요는 <span>1인가구를 위한 전문 서비스</span>입니다.
                            간단한 수리, 청소, 이사를 해요에게 맡겨주세요
                            <br/>
                            <br/>
                            <span>서비스가 필요한 고객과 서비스를 제공하는 전문가</span>를 쉽고
                            빠르게 연결해드리는 전문가 매칭 서비스입니다.<br/>
                            1분 내외의 요청서를 작성하면, 고객님께 맞는 전문가 리스트를
                            뽑아드립니다.
                            <br/>
                            <p>전문가에게 보여 줄 사진을 첨부하실 수 있고 지역을 입력하시면 간단하게 일정등록이 완료됩니다!
                                일정을 완료하기 위해선 <span class="red">기본 예약금 10,000원</span>을 결제 하셔야 됩니다.</p><p>(예약 등록일 일주일전 전액환불 가능합니다)</p>
                        </p>
                    </div>
                    <div id="chatbotWrap">
                        <div id="chatTalk">
                            <p>저에게 물어봐 주세요!</p>
                        </div>
                        <div id="chatbot">
                            <span><i class="far fa-smile fa-spin fa-fw"></i></span>
                        </div>
                    </div>
                </div>
            </section>
        </main>

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