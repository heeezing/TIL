<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- isErrorPage 속성 : 에러페이지 여부 지정 --%>
<%-- 에러가 발생할 때만 보여질 페이지 --%>
<%@ page isErrorPage="true" %> <%-- =현재 페이지는 에러 페이지 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예외 발생</title>
</head>
<body>
요청 처리 과정에서 예외가 발생했습니다.<br>
빠른 시간 내에 문제를 해결하도록 하겠습니다.
<p> <!-- p태그를 하나만 넣으면 두 줄 띄어짐 -->
<!-- exception : isErrorPage="true"인 에러 페이지에서만 호출 가능한 내장 객체 -->
에러 타입 : <%= exception.getClass().getName() %><br>
에러 메세지 : <b><%= exception.getMessage() %></b>
</body>
</html>