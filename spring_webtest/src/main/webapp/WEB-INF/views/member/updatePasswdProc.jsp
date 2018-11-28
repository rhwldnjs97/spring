<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<script type="text/javascript">
	function read() {
		var url = "read";
		url = url + "?id=${param.id}";
		location.href = url;
	}
</script>
</head>

<body>

	<DIV class="container">
		<h2>비밀번호 변경</h2>
		<c:choose>
			<c:when test="${flag }">
			비밀번호 변경 성공.
					<input type='button' value='내 정보' onclick="read()">
			</c:when>
			<c:otherwise>
			비밀번호 변경 실패.
			<input type='button' value='다시 시도' onclick="history.back()">
			</c:otherwise>
		</c:choose>
	</DIV>
</body>
</html>
