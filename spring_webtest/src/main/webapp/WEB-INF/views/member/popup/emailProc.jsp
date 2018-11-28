<%@ include file="../ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="dao" class="member.memberDAO" />
<%
	String email = request.getParameter("email");
	boolean flag = dao.duplicateEmail(email);
%>

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
function use(){
	opener.frm.email.value = '<%=email%>';
	self.close();
}
</script>
<link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">
</head>

<body>


	<DIV class="title">이메일 확인</DIV>
	<div class="content">
		입력된 ID:<%=email%>
		<br>
		<br>
		<%
			if (flag) {
				out.print("이미 사용 중인 email 입니다.<br><br>");
			} else {
				out.print("사용 가능한 email 입니다.<br><br>");
				out.print("<button onclick='use()'>사용</button>");
			}
		%>
	</div>
	<DIV class='bottom'>
		<input type='button' value='다시시도' onclick="location.href='email_form.jsp'"> 
		<input type='button' value='닫기' onclick="window.close()">
	</DIV>



</body>
</html>
