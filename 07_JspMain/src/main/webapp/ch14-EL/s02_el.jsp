<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL을 이용해서 파라미터 값 처리</title>
</head>
<body>
<h3>EL을 이용해서 파라미터 값 처리</h3>
<form action="s02_el.jsp" method="post">
	이름 <input type="text" name="name">
	<input type="submit" value="확인">
</form>
<br>
이름은 <%= request.getParameter("name") %><br>
이름은 ${param.name}<br>
이름은 ${param["name"]}
</body>
</html>