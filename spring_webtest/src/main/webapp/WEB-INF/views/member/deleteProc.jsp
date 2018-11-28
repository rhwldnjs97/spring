<%@ include file="../ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>
</head>

<body>

	<DIV class="title">회원탈퇴 처리</DIV>

	<div class="content">
		<c:choose>
			<c:when test="${flag }">
				탈퇴되었습니다.
			</c:when>
			<c:otherwise>
				탈퇴에 실패하였습니다.
			</c:otherwise>
		</c:choose>
	</div>
	<DIV class='bottom'>
		<input type='submit' value='홈' onclick="location.href='../index.jsp'">
		<input type='button' value='다시시도' onclick="history.back()">
	</DIV>
</body>
</html>
