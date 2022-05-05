<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" href="${path }/resources/style/style.css">
    <link rel="stylesheet" href="${path }/resources/style/myPage.css">
</head>
<body>  
	<div id="wrap">
		<c:import url="/header" charEncoding="UTF-8" />
		<!-- 헤더 끝 -->
     
		<div id="pageMenu">
		    <ul id="myPageMenu" class="flex-container">
		        <li class="myPageMenuLi">
		            <a href="#"><img src="${path }/resources/img/profile.png" alt="프로필 설정"></a>
		        </li>
		        <li class="myPageMenuLi">
		            <Strong id="uName">${sessData.uName }</Strong>
		    		<button type="button" id="profileModBtn">편집</button>
				</li>
				<li class="myPageMenuLi">
		    		<Strong>회원등급</Strong>
		    		<c:choose>
		    			<c:when test="${sessData.uLevel eq '2'}">
		    				<span id="uGrade">강사</span>
		    			</c:when>
		    			<c:otherwise>
		    				<span id="uGrade">학생</span>
		    			</c:otherwise>
		    		</c:choose>
		        </li>
			</ul>
		</div>
		<main id="main">
		    <nav id="mainNav">
		        <ul id="mainMenu" class="flex-container">
		        	<c:choose>
		        		<c:when test="${sessData.uLevel eq '1'}">
		        			<li id="mainLi1" class="mainLi payList">
								<span>주문 내역</span>
								<div id="subMenu1"  class="subContainer">
									<ul class="subMenu flex-container">         
										<li class="subMenuLi"><a href="#" id="onlineBtn">온라인</a></li>
										<li class="subMenuLi"><a href="#" id="offlineBtn">오프라인</a></li>
									</ul>
								</div>
							</li>                       
		        		</c:when>
		        		
		        		<c:otherwise>
							<li id="mainLi1" class="mainLi">
								<span>클래스 관리</span>
								<div id="subMenu1"  class="subContainer">
									<ul class="subMenu flex-container">           
										<li class="subMenuLi"><a href="/ClassPost.jsp">클래스 등록</a></li>
										<li class="subMenuLi"><a href="/ClassList.jsp" >클래스 관리</a></li>
									</ul>
								</div>
							</li>     
		        			<li id="mainLi2" class="mainLi payList">
		        				<a href="#">수강 결제 내역</a>
								<div id="subMenu2"  class="subContainer">
									<ul class="subMenu flex-container">
										<li class="subMenuLi"><a href="#" id="onlineBtn" id="onlineBtn">온라인</a></li>
										<li class="subMenuLi"><a href="#" id="offlineBtn" id="offlineBtn">오프라인</a></li>
									</ul>
								</div>
							</li>
		        		</c:otherwise>
		        	</c:choose>
		        	
					<li id="mainLi3" class="mainLi" >           
						<a href="#" class=" subMenuLila">내 정보 관리</a>
						<div id="subMenu3" class="subContainer">
							<ul id="subMenuUl"class="subMenu flex-container">
								<c:choose>
									<c:when test="${sessData.uLevel eq '1'}">
										<li class="subMenuLi"><a href="/memberMod" id="memModBtn">회원 정보 수정</a></li>
										<li class="subMenuLi"><a href="/lvlUpPost" id="">등업 신청</a></li>
										<li class="subMenuLi"><a href="#" id="memDrop">회원 탈퇴</a></li>
									</c:when>
									
									<c:otherwise>
										<li class="subMenuLi"><a href="/memberMod" id="memModBtn">회원 정보 수정</a></li>                                
										<li class="subMenuLi"><a id="memDrop">회원 탈퇴</a></li>
									</c:otherwise>
								</c:choose>
		                    </ul>
		                </div>
		            </li>
				</ul>
		    </nav>
		    
		    <div id="detailedContent"></div> 
		</main>

       <c:import url="/footer" charEncoding="UTF-8" />
		<!-- 푸터 끝 -->
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${path }/resources/script/script.js"></script>
<!--     <script>
    $(".payList").click(function(){
    	$.ajax({
			url : "/Proj_OnedayClass/payment/PayList.jsp",      // 컨트롤러에서 대기중인 URL 주소이다.
			type : "GET",            // HTTP method type(GET, POST) 형식이다.
		}).done(function(payList){
			$("#detailedContent").html(payList);
		});
    });
     </script> -->
</body>
</html>