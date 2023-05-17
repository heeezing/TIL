<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 값 변경</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies != null && cookies.length > 0){
		for(int i=0 ; i<cookies.length ; i++){
			//쿠키의 값을 변경하고자 하는 쿠키가 존재하는지 체크
			//문자열 비교 - .equals() 활용
			if(cookies[i].getName().equals("name")){
				//같은 서버의 쿠키는 쿠키명이 유니크하므로 덮어씌우면 수정이 됨.
				Cookie cookie = new Cookie("name",URLEncoder.encode("JSP프로그래밍","UTF-8"));
				//생성된 쿠키를 클라이언트로 전송
				response.addCookie(cookie);
				
				out.println("name 쿠키의 값을 변경합니다.");
			}
		}
	}else {
		out.println("쿠키가 존재하지 않습니다.");
	}

%>
</body>
</html>