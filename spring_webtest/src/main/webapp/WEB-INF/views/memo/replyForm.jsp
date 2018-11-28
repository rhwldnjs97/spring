<%@ include file="/ssi/ssi.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type = "text/javascript">
function incheck(f){
	if(f.title.value==""){
		alert("제목을 입력하세요.");
		f.title.focus();
		return false;
	} 
	else if(f.content.value==""){
		alert("내용을 입력하세요.");
		f.content.focus();
		return false;
	}
}
</script>
</head>
<body>
	<table class="table table-bordered" style="margin-left:200px; margin-right:200px; width:600px">
	<h5>답변등록</h5>
	<form name="frm" method="POST" action="./reply" onsubmit="return incheck(this)">
	<input type="hidden" name="memono" value="${dto.memono}">
	<input type="hidden" name="grpno" value="${dto.grpno}">
	<input type="hidden" name="indent" value="${dto.indent}">
	<input type="hidden" name="ansnum" value="${dto.ansnum}">
		<table class="table table-bordered">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="30" value="${dto.title}" /></td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="30" name="content"></textarea>
				</td>
			</tr>
		</table>
		<div>
			<input type="submit" value="전송">
		</div>
	</form>
</div>
</body>
</html>
