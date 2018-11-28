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
<script type="text/javascript">
function read(){
	var url = "read.do";
	url = url + "?id=${param.id}";
	location.href = url;
}
</script>
</head>

<body>

	<DIV class="title">사진 변경</DIV>
	<div class="content">
	<c:choose>
		<c:when test="${flag }">
			사진 변경 완료.
			<br><input type='button' value='내 정보' onclick="read()">
		</c:when>
		<c:otherwise>
			사진 변경 실패.
			<br><input type='button' value='다시 시도' onclick="history.back()">
		</c:otherwise>
	</c:choose>
	</DIV>

</body>
</html>
