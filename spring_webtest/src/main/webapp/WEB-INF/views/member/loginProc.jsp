<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
</style>
</head>

<body>
	<DIV class="title">로그인 처리</DIV>
	<div class = "content">
	
	<c:choose>
		<c:when test="${flag}">
			로그인이 되었습니다.
			<input type="button" value = "홈" onclick="location.href='${root}/'">
		</c:when>
		<c:otherwise>
			아이디 또는 패스워드가 일치하지 않습니다.
			<input type="button" value = "홈" onclick="location.href='${root}/'"> <input type='button' value='다시시도'
				onclick="history.back()">
		</c:otherwise>
	</c:choose>
	</div>
</body>
</html>
