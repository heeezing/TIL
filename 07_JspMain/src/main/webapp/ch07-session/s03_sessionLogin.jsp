<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");

	//전송된 데이터 반환
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	//테스트 용으로 id와 비밀번호가 일치하면 로그인 처리 (문자열 비교)
	if(id.equals(password)){ //id==password로 하지 않도록 주의
		session.setAttribute("user_id", id);
%>
	<%= id %>님이 로그인했습니다.<br>
	<input type="button" value="로그인 체크" onclick="location.href='s04_sessionLoginCheck.jsp'">
	<input type="button" value="로그아웃" onclick="location.href='s05_sessionLogout.jsp'">
<%
	}else{
%>
	<script type="text/javascript">
		alert('로그인에 실패했습니다.');
		history.go(-1);
	</script>
<%
	}
%>
</body>
</html>