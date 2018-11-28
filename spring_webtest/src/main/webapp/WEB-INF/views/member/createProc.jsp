<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

</style>
<script type="text/javascript">
function mlist(){
	var url = "list";
	location.href = url;
}
</script>
</head>

<body>

	<h2>회원가입 처리</h2>
	<div class="container">
	<c:choose>
		<c:when test="${flag }">
			가입을 환영합니다.<br>
			<input type='button' value='로그인' onclick="location.href='login'"> 
			<input type='button' value='홈' onclick="location.href='${root}/'">
			<input type='button' value='목록' onclick="mlist()">
		</c:when>
		<c:otherwise>
			회원가입에 실패하였습니다.<br>
			<input type='submit' value='다시시도' onclick="history.back()"> 
			<input type='button' value='홈' onclick="location.href='${root}/'">
		</c:otherwise>
	</c:choose>
	</div>

</body>
</html>
