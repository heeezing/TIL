<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date time = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 정보</title>
</head>
<body>
세션 ID : <%= session.getId() %><br>
세션 생성 시간 : <%= session.getCreationTime() %><br>
<%
	time.setTime(session.getCreationTime()); //long타입의 시간을 변환
%>
세션 생성 시간 : <%= sf.format(time) %><br> <!-- 원하는 포맷으로 출력 -->
최근 접근 시간 : <%= session.getLastAccessedTime() %><br>
<%
	time.setTime(session.getLastAccessedTime()); //long타입의 시간을 변환
%>
최근 접근 시간 : <%= sf.format(time) %><br> <!-- 원하는 포맷으로 출력 -->
세션 유지 시간 변경하기 (기본 세션 유지 시간 : 30분)<br>
<%
	session.setMaxInactiveInterval(60*20);
	//web.xml에 50분으로 지정된 상태임
	//(jsp에서 세팅한 정보가 현재 세션에 우선 반영됨)
%>
세션 유지 시간 : <%= session.getMaxInactiveInterval() %>초
</body>
</html>