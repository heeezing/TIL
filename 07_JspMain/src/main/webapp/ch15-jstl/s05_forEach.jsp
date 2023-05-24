<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forEach 태그</title>
</head>
<body>

<h4>1부터 100까지 홀수의 합</h4>
<c:set var="sum" value="0"/> <%-- 마치 변수 선언과 같다 --%>
				<%-- 시작값	   끝값      증가값 --%>
<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="sum" value="${sum+i}"/>
</c:forEach>
결과 : ${sum}

<h4>구구단(5단)</h4>
<ul>
	<c:forEach var="i" begin="1" end="9">
	<li>5 * ${i} = ${5 * i}</li>
	</c:forEach>
</ul>

<h4>int형 배열</h4>
<c:set var="intArray" value="<%= new int[]{10,20,30,40,50} %>"/>
<%--        속성명,    객체(또는 배열),    시작인덱스,  끝인덱스, 반복상태를 처리하는 변수 지정--%>
<c:forEach var="i" items="${intArray}" begin="2" end="4" varStatus="status">
	<%--  인덱스            반복 횟수       데이터  --%>
	${status.index} - ${status.count} - [${i}]<br> <%-- 마치 확장for문과 같다 --%>
</c:forEach>

<h4>Map</h4>
<%
	HashMap<String,String> mapData = new HashMap<String,String>();
	mapData.put("name","홍길동");
	mapData.put("job","탐정");
%>
<c:set var="map" value="<%=mapData%>"/>
<c:forEach var="i" items="${map}">
	${i.key} = ${i.value}<br>
</c:forEach>

</body>
</html>