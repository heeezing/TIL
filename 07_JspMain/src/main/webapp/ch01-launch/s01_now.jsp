<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- 페이지 디렉티브 -->
<%@ page import="java.util.Date" %> <!-- 나중에 서블릿으로 변환될 때 임포트문으로 변함 -->
<%@ page import="java.text.SimpleDateFormat" %>
<% 
	//객체 생성
	Date nowTime = new Date();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 날짜와 시간</title>
</head>
<body>
현재 날짜와 시간은 <%= nowTime %>입니다.
<br>
-----------------------------------------------------------------
<br>
<%
	//객체 생성
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년MM월dd일 a hh:mm:ss");
%>
현재 날짜와 시간은 <%= sf.format(nowTime) %>입니다.
</body>
</html>