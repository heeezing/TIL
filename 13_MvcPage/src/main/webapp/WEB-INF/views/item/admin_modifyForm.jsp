<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#write_form').submit(function(){
			if($('#name').val().trim()==''){
				alert('상품명을 입력하세요!');
				$('#name').val('').focus();
				return false;
			}
			if($('#price').val()==''){ //type=number -> 공백 체크 알아서 해줌
				alert('가격을 입력하세요!');
				$('#price').focus();
				return false;
			}
			if($('#quantity').val()==''){
				alert('수량을 입력하세요!');
				$('#quantity').focus();
				return false;
			}
			if($('#detail').val().trim()==''){
				alert('상품설명을 입력하세요!');
				$('#detail').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>상품 수정</h2>
		<form action="modify.do" method="post" encType="multipart/form-data" id="write_form">
			<ul>
				<li>
					<label>상품 표시 여부</label>
					<input type="radio" name="status" value="1" id="status1" <c:if test="${item.status == 1}">checked</c:if>>미표시
					<input type="radio" name="status" value="2" id="status2" <c:if test="${item.status == 2}">checked</c:if>>표시
				</li>
				<li>
					<label for="name">상품명</label>
					<input type="text" name="name" id="name" maxlength="10" value="${item.name}">
				</li>
				<li>
					<label for="price">가격</label>
					<input type="number" name="price" id="price" min="1" max="99999999" value="${item.price}">
				</li>
				<li>
					<label for="quantity">수량</label>
					<input type="number" name="quantity" id="quantity" min="0" max="99999" value="${item.quantity}">
				</li>
				<li>
					<label for="photo1">상품사진1</label>
					<img src="${pageContext.request.contextPath}/upload/${item.photo1}" 
						 data-img="${item.photo1}" width="50" height="50" class="my-photo">
					<br>
					<input type="file" name="photo1" id="photo1" accept="image/gif,image/png,image/jpeg" class="form-notice">
				</li>
				<li>
					<label for="photo1">상품사진2</label>
					<img src="${pageContext.request.contextPath}/upload/${item.photo2}" 
						 data-img="${item.photo2}" width="50" height="50" class="my-photo">
					<br>
					<input type="file" name="photo2" id="photo2" accept="image/gif,image/png,image/jpeg" class="form-notice">
				</li>
				<li>
					<label for="detail">상품설명</label>
					<textarea rows="5" cols="30" name="detail" id="detail">${item.detail}</textarea>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="수정">
				<input type="button" value="삭제" onclick="location.href='delete.do?item_num=${item.item_num}'">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</div>
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>