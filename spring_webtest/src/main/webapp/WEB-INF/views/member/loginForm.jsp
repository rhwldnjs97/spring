<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
</style>
</head>

<body>

	<h2>로그인</h2>
	<div class="container">
		<FORM name='frm' method='POST' action='${root}/member/login'>
			<TABLE class="table table-bordered">
				<TR>
					<TH>ID</TH>
					<TD><input type="text" name="id" size="20"> <c:choose>
							<c:when test="${c_id=='Y' }">
								<input type='checkbox' name='c_id' value='Y' checked='checked'> ID 저장	
					</c:when>
							<c:otherwise>
								<input type='checkbox' name='c_id' value='Y'> ID 저장
					</c:otherwise>
						</c:choose></TD>

				</TR>
				<TR>
					<TH>PASSWORD</TH>
					<TD><input type="text" name="passwd"></TD>
				</TR>
			</TABLE>

			<input type='submit' value='로그인'> <input type='button'
				value='회원가입' onclick="location.href='agree'"> <input
				type='button' value='ID/Password 찾기'
				onclick="location.href='idPwFind'">

			<!--댓글 처리용 -->
			<input type="hidden" name="flag" value="${param.flag}">
			<input type="hidden" name="nPage" value="${param.nPage}">
			<input type="hidden" name="bbsno" value="${param.bbsno}">
			<input type="hidden" name="num" value="${param.num}">
			<input type="hidden" name="col" value="${param.col}">
			<input type="hidden" name="word" value="${param.word}">
			<input type="hidden" name="nowPage" value="${param.nowPage}">
		</FORM>
	</div>
</body>
</html>
