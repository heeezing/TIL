<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="page-main">
	<h2>상품 상세 정보</h2>
	<ul>
		<li>글번호 : ${boardVO.num}</li>
		<li>상품명 : ${boardVO.name}</li>
		<li>가격 : ${boardVO.price}</li>
		<li>재고 : ${boardVO.stock}</li>
		<li>원산지 : ${boardVO.origin}</li>
	</ul>
	<hr size="1" noshade="noshade" width="100%">
	<div class="align-right">
		작성일 : ${boardVO.reg_date}
		<input type="button" value="수정" onclick="location.href='modifyForm.do?num=${boardVO.num}'">
										<!-- location.replace() : 히스토리를 지워서 다시 뒤로 갈 수 없도록 함 -->
		<input type="button" value="삭제" onclick="location.replace('deleteForm.do?num=${boardVO.num}')">
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>	
</div>
</body>
</html>