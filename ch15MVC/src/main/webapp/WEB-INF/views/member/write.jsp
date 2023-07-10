<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<form:form action="write.do" modelAttribute="vo">
	<form:errors element="div"/> <!-- 필드와 매핑되지않는 다른 에러가 났을 시 여기에 표시 -->
	아이디 : <form:input path="id"/>
	<form:errors path="id"/><br>
	비밀번호 : <form:password path="password"/>
	<form:errors path="password"/><br>
	이름 : <form:input path="name"/>
	<form:errors path="name"/><br>
	나이 : <form:input path="age"/>
	<form:errors path="age"/><br>
	이메일 : <form:input path="email"/>
	<form:errors path="email"/><br>
	<form:button>전송</form:button>
</form:form>
</body>
</html>