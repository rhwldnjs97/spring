<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../ssi/ssi.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type = "text/javascript">
function tlist(){
	var url = "list.do";
	
	location.href=url;
}
</script>
</head>
<body>
	<div class="container">
	<h5>처리 결과</h5>
	<div>
		<c:choose>
			<c:when test="${flag }">
				팀원이 등록되었습니다.
			</c:when>
			<c:otherwise>
				팀원 등록에 실패하였습니다.
			</c:otherwise>
		</c:choose>
	</div>
	<div id="title">
		<button onclick="location.href='createForm.do'">계속입력</button>
		<button onclick="tlist()">목록</button>
	</div>
	</div>
</body>
</html>