<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>out 태그</title>
</head>
<body>
<%-- escapeXml="true"(기본값)이면 html태그 불인정 --%>
<c:out value="<h1>오늘은 좋은 날!</h1>" escapeXml="true"/>
<%-- escapeXml="false"이면 html태그 인정 --%>
<c:out value="<h1>내일도 좋은 날!</h1>" escapeXml="false"/>
</body>
</html> 