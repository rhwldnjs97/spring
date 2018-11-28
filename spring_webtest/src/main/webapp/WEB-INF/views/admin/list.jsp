<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">

.search {
	text-align: center;
	margin: 3px auto;
}
</style>
<script type="text/javascript">
	function read(id) {
		var url = "../member/read";
		url += "?id=" + id;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		location.href = url;
	}
</script>

</head>

<body>
	<div class="search">
		<FORM name='frm' method='POST' action='./list'>
			<select name="col">
				<option value="mname" <c:if test="${col=='mname' }">selected</c:if>>성명</option>
				<option value="email" <c:if test="${col=='email' }">selected</c:if>>이메일</option>
				<option value="id" <c:if test="${col=='id' }">selected</c:if>>아이디</option>
				<option value="total">전체출력</option>
			</select> <input type="text" name="word" value='${word }'> <input
				type="submit" value="검색"> <input type="button" value="회원가입"
				onclick="location.href='${root}/member/create'">
		</FORM>
	</div>

	<div class="container">
		<h2>
			<span class="glyphicon glyphicon-th-list"></span> 회원 목록
		</h2>

		<c:forEach var="dto" items="${list}">

			<TABLE class="table table-hover">
				<TR>
					<TD rowspan='5' width="30%"><img
						src='${root }/member/storage/${dto.fname}' width='100%'
						height='100%'></TD>
					<TH width="20%">아이디</TH>
					<TD width="50%"><a href="javascript:read('${dto.id }')">${dto.id }</a></TD>
				</TR>
				<TR>
					<TH>성명</TH>
					<TD>${dto.mname }</TD>
				</TR>
				<TR>
					<TH>전화번호</TH>
					<TD>${dto.tel }</TD>
				</TR>
				<TR>
					<TH>이메일</TH>
					<TD>${dto.email }</TD>
				</TR>
				<TR>
					<TH>주소</TH>
					<TD>${dto.address1 }(${dto.address2 })</TD>
				</TR>
			</TABLE>
		</c:forEach>
		${paging }
	</div>
</body>
</html>
