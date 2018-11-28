<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../ssi/ssi.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function mlist() {
	var url = "list.do";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
</script>
</head>
<body>
	<div>
	<c:choose>
	<c:when test="${flag }">메모를 삭제했습니다.</c:when>
	<c:otherwise>
	메모삭제를 실패했습니다.
	</c:otherwise>
	</c:choose>
		<br> <input type='button' value='목록' onclick="location.href='list.do'">
	</div>
</body>
</html>