<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="${path }/resources/style/member.css">
</head>
<body>
    <div id="wrap">
    	<c:import url="/header" charEncoding="UTF-8" />
    	<!-- 헤더 끝 -->
    	
        <!-- 로그인시작 -->
        <div id="signin">
        <form id="loginFrm" name="loginFrm" method="post">
			<table>
				<caption><hr><h3>로그인</h3></caption>
				<tbody>
					<tr>
						<td>
							<input type="text" class="loginInput" name="uId" id="uId" autofocus size="30"
                            placeholder="아이디">
						</td>
					</tr>
					<tr>
						<td>
							<input type="password" class="loginInput" name="uPw" id="uPw" size="30"
                            placeholder="비밀번호">
						</td>
					</tr>
					<tr>
						<td colspan="2">						
							<button type="button" id="loginBtn">로그인</button>
							<button type="button" id="signUpBtn">회원가입</button>
						</td>						
					</tr>
				</tbody>
			</table>
		</form>
        </div>
        <!-- 로그인끝 -->
        
    	<c:import url="/footer" charEncoding="UTF-8" />
		<!-- 푸터 끝 -->
    </div>
</body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${path }/resources/script/member.js"></script>
</html>