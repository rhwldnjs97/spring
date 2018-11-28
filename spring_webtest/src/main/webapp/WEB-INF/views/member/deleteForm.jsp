<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>

<body>

	<DIV class="title">회원 탈퇴</DIV>

	<FORM name='frm' method='POST' action='./delete'>
	<input type="hidden" name = "id" value="${id}">
			<input type="hidden" name="col" value="${param.col }">
		<input type="hidden" name="word" value="${param.word }">
		<input type="hidden" name="nowPage" value="${param.nowPage }">
		<div class = "content">
		탈퇴된 계정은 복구할 수 없습니다.<br>
		탈퇴하시겠습니까?
		</div>
		
		<DIV class='bottom'>
			<input type='submit' value='예'> 
			<input type='button' value='아니오' onclick="history.back()">
		</DIV>
	</FORM>

</body>
</html>
