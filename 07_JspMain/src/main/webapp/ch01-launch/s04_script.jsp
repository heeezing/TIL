<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//메서드 정의
	public int multiply(int a, int b){
		return a * b;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선언부를 이용한 두 정수의 값 연산</title>
</head>
<body>
10 * 25 = <%= multiply(10,25) %>
</body>
</html>