<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function getThumbnailPrivew(input, targetId) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var element = window.document.getElementById(targetId);
            element.setAttribute("src", e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

function updatePasswd(){
	var url = "updatePasswd";
	url += "?gno=${dto.gno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
		location.href = url;
	}

	function inputCheck(f) {
		if (f.writer.value == "") {
			alert("작성자를 입력해 주세요.");
			f.writer.focus();
			return false;
		}
		if (f.pw.value == "") {
			alert("비밀번호를 입력해 주세요.");
			f.pw.focus();
			return false;
		}
		if (f.title.value == "") {
			alert("제목을 입력해 주세요.");
			f.title.focus();
			return false;
		}
		if (f.content.value == "") {
			alert("내용을 입력해 주세요.");
			f.content.focus();
			return false;
		}
<%-- <%boolean flag = dao.passwdCheck(map);
			if (!flag) {%>
	alert("현재의 비밀번호가 일치하지않습니다.");
		return false;
<%}%> --%>
	}
</script>
</head>

<body>

	<FORM name='frm' method='POST' action='./update'
		enctype="multipart/form-data" onsubmit="return inputCheck(this)">
		<input type="hidden" name="gno"
			value="${param.gno }"> <input
			type="hidden" name="oldfile"
			value="${param.oldfile }">

		<div class="container">

			<h2>
				<span class="glyphicon glyphicon-pencil"></span> 글 수정
			</h2>

			<TABLE>
				<tr>
					<td rowspan='6'
						style="width: 800px; height: 600px; overflow: hidden; text-align: center">
						<img alt="미리보기" src="../gallery/storage/${dto.fname}" id="avatar_image" >
					</td>
				</tr>
				<TR>
					<TD>					
					<input type="file" name="fnameMF" accept=".jpg,.png,.gif"
						onchange="getThumbnailPrivew(this, 'avatar_image');">					
					</TD>	
					
				</TR>
				<TR>
					<TD>
					<div class="form-group" >
					 <label for="title">Title:</label>					
					<input class="w3-input" type="text" name="title" size="50"
						placeholder="Title" style="width: 400px" value="${dto.title}">
					</div>
						</TD>
				</TR>
				<TR>

					<TD>
					<div class="form-group" >
					 <label for="content">Content:</label>	
					<textarea class="w3-input" rows="10" cols="50" name="content"
							placeholder="Content" style="width: 400px; resize: none;">${dto.content}</textarea>
					</div>		
					</TD>
				</TR>
				<TR>
					<TD>
					<div class="form-group" >
					 <label for="usr">Writer:</label>						
					<input class="w3-input" type="text" name="writer" size="20"
						placeholder="Writer" style="width: 400px" value="${dto.writer}">
					</div>
					</TD>
				</TR>
				<TR>

					<TD><input class="w3-input" type="password" name="pw" size="15" placeholder="password" style="width: 400px">
					<br><button type="button" class="btn btn-default btn-sm" onclick="updatePasswd()"><span class="glyphicon glyphicon-edit"></span>암호수정</button></TD>
				</TR>
			</TABLE>

		</div>
		<br>
		<DIV class='bottom' style="text-align:center">

			<button type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-edit"></span>수정</button>
			 <button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span>뒤로가기</button>
		</DIV>

	</FORM>

</body>
</html>
