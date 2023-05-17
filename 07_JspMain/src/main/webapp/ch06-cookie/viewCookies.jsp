<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 읽기</title>
</head>
<body>
쿠키 목록<br>
<%
	//클라이언트로부터 전송된 쿠키 정보를 반환
	//보통 쿠키는 1개 이상 존재하므로 배열로 반환
	Cookie[] cookies = request.getCookies();
	  //배열 생성 안 됐을 경우 && 배열이 만들어졌더라도 데이터가 없는 경우
	if(cookies != null && cookies.length > 0){
		for(int i=0 ; i<cookies.length ; i++){
%>
		<%= cookies[i].getName() %> 
		= <%= URLDecoder.decode(cookies[i].getValue(),"UTF-8") %><br>
<%
		}
	}else{
%>
쿠키가 존재하지 않습니다.
<%		
	}
%>
</body>
</html>