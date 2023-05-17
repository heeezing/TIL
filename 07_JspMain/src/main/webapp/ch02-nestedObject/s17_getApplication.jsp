<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 기본객체 속성 보기</title>
</head>
<body>
<%										//모든 속성명 반환
	Enumeration attrEnum = application.getAttributeNames();
	while(attrEnum.hasMoreElements()){
		//속성명 구하기
		String name = (String)attrEnum.nextElement();
		//속성값 구하기
		Object value = application.getAttribute(name);
%>
		application 속성 : <b><%= name %></b> = <%= value %><br>
<%
	}
%>
</body>
</html>