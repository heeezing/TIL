<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 에러가 발생할 때 보여질 페이지 지정 --%>
<%@ page errorPage="/ch04-errorPage/error/viewErrorMessage.jsp" %>
		<%--맨 앞의 '/' : 컨텍스트 루트를 품고 있음. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파라미터 출력</title>
</head>
<body>
<!-- 테스트 목적으로 의도적 예외 발생 (NullPointerException) -->
<!-- 값이 없어서 null인데 대문자로 변경 -->
name 파라미터 값 : <%= request.getParameter("name").toUpperCase() %>
</body>
</html>