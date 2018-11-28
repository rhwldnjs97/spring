<%@ include file="../ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function use(){
	opener.frm.id.value = '${param.id}';
	self.close();
}
</script>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>
</head>

<body>


	<DIV class="title">ID 확인</DIV>
	<div class="content">
		입력된 ID:${param.id }
		<br>
		<br>
		<c:choose>
			<c:when test="${flag }">
				이미 사용 중인 ID 입니다.<br><br>
			</c:when>
			<c:otherwise>
				사용 가능한 ID입니다.<br><br>
				<button onclick='use()'>사용</button>
			</c:otherwise>		
		</c:choose>
	</div>
	<DIV class='bottom'>
		<input type='button' value='다시시도' onclick="location.href='id_form.jsp'"> 
		<input type='button' value='닫기' onclick="window.close()">
	</DIV>



</body>
</html>
