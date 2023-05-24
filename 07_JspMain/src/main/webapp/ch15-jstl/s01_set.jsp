<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.web.member.Member" %>
<%-- prefix : 접두사 설정 / uri : jar파일에 있는 식별자 명시 --%>
<%-- core라이브러리 : 접두사 c, uri끝에 core --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>set 태그</title>
</head>
<body>
<%-- <c:set></c:set> 혹은 <c:set/> --%>

<%--       속성명		   속성값		   영역 --%>
<c:set var="msg" value="봄" scope="page"/> <%--setAttribute와 같은 역할 --%>
${pageScope.msg}, ${msg}<br> <%--getAttribute와 같은 역할 --%>

<%
	Member member = new Member();
%>
<c:set var="member" value="<%= member %>"/><%-- scope 명시X : page영역에 저장 --%>
<%--             타겟객체    객체의 프로퍼티명        저장할 값--%>
<c:set target="${member}" property="name" value="홍길동"/> <%-- member.setName("홍길동") 동작--%>
회원 이름 : ${member.name}, ${member.getName()} <%-- member.getName() 동작 --%>
											  <%-- ${member.name} 권장 --%>
</body>
</html>