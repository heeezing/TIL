<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>if 태그</title>
</head>
<body>

<c:if test="true">
	무조건 수행<br>
</c:if>

<%-- 문자열 비교 기본 : 비교연산자 == --%>
<c:if test="${param.name == 'dragon'}">
	name 파라미터의 값은 ${param.name}입니다.<br>
</c:if>

<%-- .equals()도 가능하나 최근 생겨났으므로 기본 방법이 권장됨 --%>
<c:if test="${param.name.equals('dragon')}">
	name 파라미터의 값은 ${param.name}입니다.<br>
</c:if>

<c:if test="${param.age >= 20}">
	당신의 나이는 20세 이상입니다.
</c:if>

</body>
</html>