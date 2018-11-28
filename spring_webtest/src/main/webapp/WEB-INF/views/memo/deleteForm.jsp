<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<script type="text/javascript">
function mlist() {
	var url = "list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
</script>
<body>
<co
<c:choose>
<c:when test="${flag}">
	<b>답글이 존재하여 삭제가 불가능</b><br>
	<input type=button ' value='목록' onclick="blist()">
</c:when>
<c:otherwise>
	삭제 확인
	<form method="POST" action="delete">
		<input type="hidden" name="memono" value="${param.memono }">
		<input type="hidden" name="col" value="${param.col }">
	<input type="hidden" name="word" value="${param.word }">
	<input type="hidden" name="nowPage" value="${param.nowPage }">
		<div>
			삭제를 하면 복구 될 수 없습니다.<br>
			<br> 삭제하시려면 삭제버튼을 클릭하세요.<br>
			<br> 취소는 '목록'버튼을 누르세요.<br>
			<br> <input type="submit" value="삭제처리"> <input
				type="button" value="목록" onclick="mlist()">
		</div>
	</form>
</c:otherwise>
</c:choose>
</body>
</html>
