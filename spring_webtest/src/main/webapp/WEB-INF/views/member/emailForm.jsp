<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
</style>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript">
	function use(email) {
		opener.frm.email.value = email; //document.frm.email.value;
		self.close();
	}

	function emailCheck(email) {
		if (email == "") {
			alert("이메일을 입력해 주세요");
			document.frm.email.focus();
		} else {
			var url = "emailCheck";

			// ajax로 비동기 통신
			$.ajax({
				url : url,
				dataType : 'text',
				type : 'GET',
				data : {
					"email" : email
				},

				success : function(data) {
					$("#emailcheck").text(data.trim()).css("color", "red");
					if (data.trim().indexOf("사용가능") != -1) {
						$("#emailcheck").append("<button onclick='use()'>적용</button>");
					}
				}
			});
		}
	}
</script>
</head>

<body>


	<DIV class="container">
		<h2>Email 중복 확인</h2>

		<FORM name='frm'>
			Email을 입력해주세요.<br> <br>
			<TABLE class="table table-bordered">
				<TR>
					<TH>Email</TH>
					<TD><input type="text" name="email" size="50"></TD>
				</TR>
			</TABLE>
			<div id="emailcheck"></div>
			<input type='button' value='중복확인'
				onclick="emailCheck(this.form.email.value)"> 
			<input type='button' value='취소' onclick="window.close()">
		</FORM>

	</DIV>

</body>
</html>
