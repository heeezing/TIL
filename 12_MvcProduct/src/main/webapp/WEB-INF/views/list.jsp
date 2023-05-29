<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>		
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="page-main">
	<h2>상품 목록</h2>
	<div class="align-right">
		<input type="button" value="상품 등록" onclick="location.href='writeForm.do'">
	</div>
	
	<!-- 게시글이 없을 경우 -->
	<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
	</c:if>

	<!-- 게시글이 있을 경우 -->
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>글번호</th>
			<th>상품명</th>
			<th>재고</th>
			<th>작성일</th>
		</tr>
		<c:forEach var="product" items="${list}">
		<tr>
			<td>${product.num}</td>
			<td><a href="detail.do?num=${product.num}">${product.name}</a></td>
			<td>${product.stock}</td>
			<td>${product.reg_date}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">
		${page}
	</div>
	</c:if>

</div>
</body>
</html>