<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<%--
 [실습] 상품 등록/목록/상세정보/수정/삭제
 등록 항목 : 상품명, 가격, 재고, 원산지 입력
 목록 : 상품번호, 상품명, 가격, 재고
 상세 페이지 : 상품번호, 가격, 재고, 원산지, 등록일
 수정 : 가격, 재고, 원산지
 --%>
<div class="page-main">
	<h2>상품 등록</h2>
	<form id="myForm" action="insertTest.jsp" method="post">
		<ul>
			<li>
				<label for="name">상품명</label>
				<input type="text" name="name" id="name" size="20" maxlength="30">
			</li>
			<li>
				<label for="price">가격</label>
				<input type="number" name="price" id="price" size="20" min="0">
			</li>
			<li>
				<label for="stock">재고</label>
				<input type="number" name="stock" id="stock" size="20" min="1">
			</li>
			<li>
				<label for="origin">원산지</label>
				<input type="text" name="origin" id="origin" size="20" maxlength="30">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="목록" onclick="location.href='selectTest.jsp'">
		</div>
	</form>
</div>

</body>
</html>