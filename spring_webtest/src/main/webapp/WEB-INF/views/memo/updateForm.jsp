<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type = "text/javascript">
function input(f){
	if(f.title.value==""){
		alert("제목을 입력하세요.");
		f.title.focus();
		return false;	
	}
	if(f.content.value==""){
		alert("내용을 입력하세요.");
		f.content.focus();
		return false;
	}
}
</script>
</head>
<body>
	<table class="table table-bordered" style="margin-left:200px; margin-right:200px; width:600px">
	<h5>수정</h5>
	<form name="frm" method="POST" action="./update" onsubmit="return input(this)">
	<input type="hidden" name="memono" value="${dto.memono}">
	<input type="hidden" name="col" value="${param.col}">
	<input type="hidden" name="word" value="${param.word}">
	<input type="hidden" name="nowPage" value="${param.nowPage}">
		<table class="table talbe-bordered">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="30" value="${dto.title}" /></td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="30" name="content">${dto.content}</textarea>
				</td>
			</tr>
		</table>
		<div>
			<input type="submit" value="수정">
		</div>
	</form>
	</div>

</body>
</html>