<%@ include file="/ssi/ssi.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
</head> 

<body>

<DIV class="container"><h2>파일 수정</h2>
 
<FORM name='frm' method='POST' action='./updateFile' enctype="multipart/form-data">
  <input type="hidden" name="id" value=${param.id }>
  <input type="hidden" name="oldfile" value="${param.oldfile }">
  <TABLE class="table table-bordered">
  	<TR>
      <TD colspan="2" style="text-align: center">
      <img src = "./storage/${param.oldfile }">
      </TD>
    </TR>
    <TR>
      <TH>파일</TH>
      <TD><input type="file" name="fnameMF"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 </DIV>
</body>
</html> 
