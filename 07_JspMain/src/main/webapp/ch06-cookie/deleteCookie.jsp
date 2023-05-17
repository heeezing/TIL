<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 삭제</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	if(cookies != null && cookies.length > 0){
		for(int i=0 ; i<cookies.length ; i++){
			//쿠키명을 이용해서 삭제할 쿠키가 존재하는지 체크
			if(cookies[i].getName().equals("name")){
								//어차피 삭제할거라 값에는 빈 문자열 지정
				Cookie cookie = new Cookie("name","");
				//유효시간을 0으로 설정 -> 켜자마자 만료 -> 삭제
				cookie.setMaxAge(0);
				//생성된 쿠키를 클라이언트로 전송
				response.addCookie(cookie);
				
				out.println("name 쿠키를 삭제합니다.");
			}
		}
	}else{
		out.println("쿠키가 존재하지 않습니다.");
	}
%>
</body>
</html>