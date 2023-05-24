<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 패턴을 적용한 view 사용하기</title>
</head>
<body>
결과 : <%= request.getAttribute("result") %><br>
결과 : ${requestScope.result}<br>
결과 : ${result}
</body>
</html>