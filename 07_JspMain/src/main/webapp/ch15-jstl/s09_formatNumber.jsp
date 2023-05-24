<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- fmt 라이브러리이기때문에 접두사,uri을 fmt에 맞게 설정! --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formatNumber 태그</title>
</head>
<body>
숫자 : 
<fmt:formatNumber value="100000" type="number"/>
<br>
통화 : 
<fmt:formatNumber value="100000" type="currency" currencySymbol="$"/>
<br>
통화 : 
<fmt:formatNumber value="100000" type="currency" currencySymbol="\\"/>
<br>
퍼센트 : 
<fmt:formatNumber value="0.3" type="percent"/>
<br>
패턴 : 
<fmt:formatNumber value="12.345" pattern="000000.00"/>
</body>
</html>