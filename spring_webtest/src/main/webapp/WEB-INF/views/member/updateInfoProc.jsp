<%@ include file="/ssi/ssi.jsp"%>
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

	<DIV class="container"><h2>정보 수정</h2>
	
	<c:choose>
		<c:when test="${flag}">
			정보 수정 완료.
			<br><input type='button' value='내 정보' onclick="read()">
		</c:when>
		<c:otherwise>
			정보 수정 실패.
			<br><input type='button' value='다시 시도' onclick="history.back()">
		</c:otherwise>
	</c:choose>
</DIV>
</body>
</html>
