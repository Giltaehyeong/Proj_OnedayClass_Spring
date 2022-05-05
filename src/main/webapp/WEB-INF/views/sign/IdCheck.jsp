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
	<title>ID 중복체크</title>
    <style>
	   	div#wrap{
			width: 100%;
			text-align: center;
			padding: 20px;
		}
		div#wrap h2{
			color: #ec665d;	
			font-size: 50px;
			margin-bottom: 25px;
		}
		div#wrap button{
			font-weight: bold;
			background: #fff;
			border: none;
    		color:#000;
			margin: 35px auto;
			font-size: 20px;
			opacity: 60%;
			width: 20%;
		}
		div#wrap button:hover{
			opacity: 100%;	
		}
    </style>
    <script>
    /* 사용가능한 아이디일 경우 */
    function fnClosePos() {    	
    	opener.regFrm.uIdBtnClickChk.value = "1";    
    	opener.regFrm.uId.focus();
    	window.close();
    }
    /* 중복된 아이디일 경우 */
    function fnCloseNeg() {    	
    	opener.regFrm.uIdBtnClickChk.value = "0";    
    	opener.regFrm.uId.focus();
    	window.close();
    }
    </script>
</head>
<body>
	<div id="wrap">
		<h2>${param.uId }</h2>

		<!-- 받은 데이터 값이 비었을 경우 -->
		<c:choose>
			<c:when test="${empty checkIdData.uId }">
				<b>사용가능한 아이디입니다.</b>
				<button type="button" onclick="fnClosePos()">확인</button>
			</c:when>
			<c:otherwise>
				<b>사용중인 아이디입니다.</b>
				<button type="button" onclick="fnCloseNeg()">확인</button>
			</c:otherwise>
		</c:choose>
		<br>
	
	</div>
	<!-- div#wrap -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
</body>
</html>