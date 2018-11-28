<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String passwd = (String) request.getAttribute("passwd");

	if (passwd != null) {
		out.print(" 찾으시는 패스워드는 " + passwd + " 입니다.");
	} else {
		out.print("잘못된 정보를 입력하였습니다.");
	}
%>