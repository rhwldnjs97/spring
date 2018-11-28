<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${flag}">
				메모를 등록했습니다.
			</c:when>
			<c:otherwise>
				메모등록이 실패했습니다.
			</c:otherwise>
		</c:choose>

		<br> <input type="button" value="계속 등록" onclick="location.href='./create.do'"> 
		<input type="button" value="목록" onclick="location.href='./list.do'">

	</div>
</body>
</html>
