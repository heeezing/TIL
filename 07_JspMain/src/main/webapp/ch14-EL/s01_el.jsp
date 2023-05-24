<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL의 기본적인 사용법</title>
</head>
<body>
<h3>EL의 기본적인 사용법</h3>
<table border="1" style="width:40%;">
	<tr>
		<th>표현식</th>
		<th>값</th>
	</tr>
	<tr>
		<td>\${2 + 5}</td>
		<td>${2 + 5}</td> <!-- 7 -->
	</tr>
	<tr>
		<td>\${"10" + 5}</td>
		<td>${"10" + 5}</td> <!-- 15 -->
	</tr>
	<tr>
		<td>\${"십" + 5}</td>
		<td>에러 발생. (EL에서의 +는 연산만 가능. 연결X)</td>
	</tr>
	<tr>
		<td>\${4/5}</td>
		<td>${4/5}</td> <!-- 0.8 -->
	</tr>
	<tr>
		<td>\${2 += 5}</td>
		<td>${2 += 5} (문자열로 연결)</td> <!-- 25 -->
	</tr>
	<tr>
		<td>\${"한국" += "서울"}</td>
		<td>${"한국" += "서울"} (문자열로 연결)</td> <!-- 한국서울 -->
	</tr>
	<tr>
		<td>\${2 > 3}</td>
		<td>${2 > 3}</td> <!-- false -->
	</tr>
</table>
</body>
</html>