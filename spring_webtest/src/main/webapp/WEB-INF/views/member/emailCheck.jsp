<%@ include file="../ssi/ssi.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %> 

<%
boolean flag = (Boolean)request.getAttribute("flag");

if(flag){
	out.print("중복된 이메일, 사용 불가");
}else{
	out.print("사용가능");
}

%>