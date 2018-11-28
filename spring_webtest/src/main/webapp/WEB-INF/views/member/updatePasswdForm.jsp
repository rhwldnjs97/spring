<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

</style>
<script type="text/javascript">
	function inputCheck(f) {
		if (f.passwd.value != f.repasswd.value) {
			alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			f.passwd.focus();
			return false;
		}
		if (f.oldpasswd.value != ${oldpasswd}) {
			alert("현재 비밀번호가 다릅니다.");
			f.oldpasswd.focus();
			return false;
		}
	}
</script>
</head>

<body>

	<DIV class="title">비밀번호 수정</DIV>

	<FORM name='frm' method='POST' action='./updatePasswd'
		onsubmit="return inputCheck(this)">
		<input type="hidden" name="id" value="${dto.id }">
		<TABLE>
			<TR>
				<TH>현재 비밀번호 입력</TH>
				<TD><input type="text" name="oldpasswd" value="1234"></TD>
			</TR>
			<TR>
				<TH>변경할 비밀번호 입력</TH>
				<TD><input type="text" name="passwd" value="123"></TD>
			</TR>
			<TR>
				<TH>비밀번호 재확인</TH>
				<TD><input type="text" name="repasswd" value="123"></TD>
			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='submit' value='수정'> <input type='button'
				value='취소' onclick="history.back()">
		</DIV>
	</FORM>
</body>
</html>
