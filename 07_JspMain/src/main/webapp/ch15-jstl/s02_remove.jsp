<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--	   속성명 		속성값--%>
<c:set var="str" value="서울"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>remove 태그</title>
</head>
<body>
데이터 삭제 전 : ${str}<br>
---------------------------<br>
<c:remove var="str"/>
데이터 삭제 후  : ${str}
</body>
</html>