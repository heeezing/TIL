<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form:form action="login.do" modelAttribute="loginVO">
	<%-- 에러메시지는 기본적으로 span태그로 명시하는데, 줄바꿈 처리를 위해 element="div"로 설정 --%>
	<form:errors element="div"/> <%-- 필드가 없는 에러문구가 띄워짐 = 아이디 또는 비밀번호가 불일치합니다. --%>
	아이디 : <form:input path="userId"/>
	<form:errors path="userId"/><br>
	비밀번호 : <form:password path="password"/>
	<form:errors path="password"/><br>
	<form:button>전송</form:button>
</form:form>
</body>
</html>