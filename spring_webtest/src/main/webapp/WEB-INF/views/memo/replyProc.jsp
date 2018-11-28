<%@ include file="../ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	function mlist() {
		var url = "list.do";
		location.herf = url;
	}
</script>
</head>

<body>


	<DIV class="title">처리결과</DIV>

	<div class="content">
		<c:choose>
			<c:when test="${flag}">
				답변이 등록되었습니다.
				<br>
				<input type='button' value='목록' onclick="location.href='list.do'">
				<input type='button' value='계속등록' onclick="location.href='reply.do'">
			</c:when>
			<c:otherwise>
				답변등록에 실패했습니다.<br>
				<input type='button' value='계속등록' onclick="location.href='reply.do'">
			</c:otherwise>
		</c:choose>

</body>
</html>
