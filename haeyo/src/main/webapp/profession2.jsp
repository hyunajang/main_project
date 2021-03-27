<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.haeyo.biz.user.UserVO" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>전문가 리스트</title>
    <link href="resources/image/icon.ico" rel="shortcut icon" type="image/x-icon">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="resources/css/header.css" rel="stylesheet">
    <link href="resources/css/profession.css" rel="stylesheet">
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <script src="resources/js/jquery-1.12.4.min.js"></script>
</head>
<script>
	function categoryList(){
		/*
			JSON.stringify = 객체(자바스크립트)를 문자열로 전환
			JSON.parse = 문자열을 객체(자바스크립트)로 전환
		*/
		$.ajax({
			url:"recommend.do",
			type:'GET',
			data: allData,
			success: function {
				alert("성공")
			},
			error: function {
				alert("실패")
			}
		});
	}
</script>

<body>
<%
	session = request.getSession();
	UserVO user = (UserVO)session.getAttribute("user");
%>
    <wrap>
        <!---------------------HeaderStart--------------------->
        <header id="header">
            <nav id="nav">
                <logo id="logo">
                    <a href="index"><img src="resources/image/logo2.png" alt="로고"></a>
                </logo>
                <ul>
                    <li><a href="#">같이해요</a></li>
                    <li><a href="#">혼자해요</a></li>
                    <li><a href="#">전문가 신청</a></li>
                    <li class="btn-group">
                        <ul class="dropdown-toggle bell" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false"><i class="fas fa-bell"></i></ul>
                        <div class="dropdown-menu dropdown-menu-right alarm1">
                            <p>알림</p>
                            <div id="alarmTxt">회원님이 결제를 완료하셨습니다.</div>
                            <span>23분전</span>
                        </div>
                    </li>
                    <li class="btn-group">
                        <div class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${user.uNick}님</div>
                        <div class="dropdown-menu dropdown-menu-right" id="profile">
                            <div id="profiteTxt"><span>반갑습니다! </span>${user.uNick}님</div>
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

       <!---------------------mainStart--------------------->
       <main>
            <section id="recommendWrap">
                <div id="recommend" class="clearfix">
                    <h1>맞춤 전문가</h1>
                    <c:forEach var="proRecom" items="${proRecom}">
                    <div class="box">
                        <a href="detail.do?pNo=${proRecom.pNo}">
                            <div class="box_image">
                                <span class="pCate">${proRecom.pCategory}</span><h2>${proRecom.pAddress}</h2><h2>${proRecom.uName}</h2>
                                <div class="starWrap">
                                    <ul class="star">
                                        <li><i class="fas fa-star"></i></li>
                                        <li><i class="fas fa-star"></i></li>
                                        <li><i class="fas fa-star"></i></li>
                                        <li><i class="fas fa-star"></i></li>
                                        <li><i class="fas fa-star-half-alt"></i></li>
                                    </ul>
                                    <span>별점 ${proRecom.score}</span>
                                </div>
                            </div>
                        </a>
                            <span class="profile_img" style="background-image: url(resources/image/${proRecom.pPic}.jpg);"></span>
                            <span class="r_con"></span>
                    </div>
                    </c:forEach>
                </div>
            </section>
            <!--------------------section2-------------------->
             <section id="basicWrap">
                <div id="basic" class="clearfix">
                    <h1>전문가 리스트</h1>
                    <div id="dropWrap" class="btn-group" role="group" aria-label="Button group with nested dropdown">
                 		<button type="button" class="btn btn-primary"><span>넣을곳</span></button>
                        <div class="btn-group" role="group">
                        	<button id="btnGroupDrop1" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
                        	<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                        		<a class="dropdown-item" data-value="" 	onclick='categoryList()'>등록순</a>
                        		<a class="dropdown-item" data-value="1" onclick='categoryList()'>별점순</a>
                        		<a class="dropdown-item" data-value="2" onclick='categoryList()'>리뷰순</a>
                        		<a class="dropdown-item" data-value="3" onclick='categoryList()'>거리순</a>
                        	</div>
                        </div>
                    </div>
               
                    <c:forEach var="proList" items="${proList}">
                    <div class="box">
                        <a href="">
                            <div class="box_image">
                                <span class="pCate">${proList.pCategory}</span><h2>${proList.pAddress}</h2><h2>${proList.uName}</h2>
                                <div class="starWrap">
                                    <ul class="star">
                                    </ul>
                                    <span class="score">${proList.score}</span>
                                </div>
                            </div>
                        </a>
                            <span class="profile_img" style= "background-image: url(resources/image/${proList.pPic}.jpg);"></span>
                            <div class="r_con"></div>
                    </div>
                    </c:forEach>
                </div>
            </section>
          
       </main>
       <!---------------------mainEnd--------------------->
    </wrap>
    <script defer src="resources/js/professionList.js" type="text/javascript"></script>
</body>

</html>