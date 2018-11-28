<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" %>
<c:choose>
	<c:when test="${empty sessionScope.id}">
		<c:set var="str" value="메인 페이지 입니다." />
	</c:when>
	<c:otherwise>
		<c:set var="str" value="안녕하세요 ${sessionScope.id} 님" />
	</c:otherwise>
</c:choose>

<html>
<head>
	<title>Home</title>
</head>
<body>
<div class="container">
<P>  The time on the server is ${serverTime}. </P>
		<h3>${title}</h3>
		<br>
		<div class="content" style="text-align: center">
			<h2>${str }</h2>
			<img src="${pageContext.request.contextPath }/images/main.jpg" width="50%"> <br>
			<c:choose>
				<c:when test="${empty sessionScope.id }">
					<br>
					<button onclick="location.href='member/login'">로그인</button>
				</c:when>
				<c:otherwise>
					<br>
					<button onclick="location.href='member/logout'">로그아웃</button>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</body>
</html>
