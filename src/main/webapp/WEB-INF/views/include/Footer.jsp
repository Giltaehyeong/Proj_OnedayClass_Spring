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
	<title>푸터</title>
	<link rel="stylesheet" href="${path }/resources/style/style.css">
</head>
<footer id="footer">
	<div id="footerTop" class="flex-container">
	    <nav id="footerLnbArea">
	        <ul id="footermainMenu" class="flex-container">
	            <li class="footerMainLi"><a href="#">서비스이용약관</a></li>
	            <li class="footerMainLi"></li>
	            <li class="footerMainLi"><a href="#">개인정보처리방침</a></li>
	            <li class="footerMainLi"></li>
	            <li class="footerMainLi"><a href="#">이메일무단수집거부</a></li>
	            <li class="footerMainLi"></li>
	            <li class="footerMainLi"><a href="#">인터넷증명발급</a></li>
	        </ul>
	    </nav>
	    <!-- nav#footerLnbArea -->
	</div>
	<!-- div#footerTop 회사 관련 정보 영역 -->

	<div id="footerBottom" class="flex-container">
	    <nav id="footerArea">
	        <ul id="footerBtm" class="flex-container">
	            <li class="footerBtmLi"><a href="#">주소</a></li>
	            <li class="footerBtmLi"><a href="#">대표</a></li>
	            <li class="footerBtmLi"><a href="#">사업자번호</a></li>
	            <li class="footerBtmLi"><a href="#">전화번호</a></li>
	        </ul>
	    </nav>
	    <!-- nav#footerArea -->
	</div>
	<!-- div#footerBottom -->
</footer>
<!-- footer#footer -->
</body>
</html>