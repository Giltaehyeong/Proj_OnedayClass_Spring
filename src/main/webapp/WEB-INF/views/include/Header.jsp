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
	<title>헤더</title>
	<link rel="stylesheet" href="${path }/resources/style/style.css">
</head>
<body>
<header id="header" class="flex-container">
	<div id="headerLogo">
	    <a href="/"><img src="${path }/resources/img/logo.png" alt="로고"></a>
	</div>
	<!-- div#headerLogo -->
	
	<div id="headerRight">
		<ul class="flex-container">
			<!-- sessData.uId 값이 있을 경우 -->
			<c:choose>
				<c:when test="${not empty sessData.uId }">
					<c:choose>
						<c:when test="${sessData.uLevel eq '3'}">
							<li><a href="/adminPage">${sessData.uName }님 안녕하세요.</a></li>
							<li></li>
							<li><a href="/logout">로그아웃</a></li>                				
						</c:when>
						
						<c:when test="${sessData.uLevel eq '2'}">
							<li><a href="/myPage">${sessData.uName }선생님 안녕하세요.</a></li>
							<li></li>
							<li><a href="/logout">로그아웃</a></li>
						</c:when>
						
						<c:otherwise>
							<li><a href="/myPage">${sessData.uName }님 환영합니다.</a></li>
							<li></li>
							<li><a href="/logout">로그아웃</a></li>
							<li></li>
							<li>
							<div id="headerCart">
							<a href="/cart.jsp"><img src="${path }/resources/img/cart.png" alt=""><span><%-- <%=cartCnt %> --%>0</span></a>
							</div>
							</li>
						</c:otherwise>
					</c:choose>
				</c:when>
				
				<c:otherwise>
					<li><a href="/login">Sign In</a></li>
					<li></li>
					<li><a href="/member">Sign Up</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<!-- div#headerRight -->
</header>
<!-- header#header -->
       
<nav id="gnb">
    <ul class="flex-container">
        <li><a href="/" >Home</a></li>
        <li><a href="/adminQnaList">About</a></li>
        <li><a href="/classOnList">Online</a></li>
        <li><a href="/classOffList">Offline</a></li>
    </ul>
</nav>
</body>
</html>