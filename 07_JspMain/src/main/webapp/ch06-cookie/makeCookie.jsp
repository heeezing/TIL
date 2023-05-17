<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%
	//쿠키클래스는 서블릿이 가지고 있기 때문에 같은 경로이므로 바로 호출하면 됨
							  //쿠키명   		 쿠키값(한글이라 인코딩)
	Cookie cookie = new Cookie("name",URLEncoder.encode("홍길동","UTF-8"));

	//쿠키 유효시간 지정(단위:초)
	//쿠키 유효시간 지정 시 -> 클라이언트 영역에 파일을 생성해서 쿠키 정보를 보관
	//(파일을 생성해놨기 때문에 유효시간이 지나지 않은 이상 브라우저를 껐다 켜도 사용 가능)
	//쿠키 유효시간 미지정 시 -> 메모리에 쿠키 정보를 보관 (브라우저 끄면 파일 사라짐)
	
	//cookie.setMaxAge(30*60); //60초*30 = 유효시간 30분
	//cookie.setMaxAge(-1); //메모리에 쿠키 정보 보관
	
	//클라이언트에 쿠키 전송
	response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 생성</title>
</head>
<body>
<%= cookie.getName() %> 쿠키의 값은 <%= cookie.getValue() %>
</body>
</html>