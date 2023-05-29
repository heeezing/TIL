<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript">
	window.onload=function(){
		let myForm = document.getElementById('modify_form');
		//이벤트 연결
		myForm.onsubmit=function(){
			let items = 
				document.querySelectorAll('.input-check');
			for(let i=0;i<items.length;i++){
				if(items[i].value.trim()==''){
					let label = document.querySelector('label[for="'+items[i].id+'"]');
					alert(label.textContent + ' 항목 필수 입력');
					items[i].value = '';
					items[i].focus();
					return false;
				}
			}
		};
	};
</script>
</head>
<body>
<div class="page-main">
	<h2>상품 정보 수정</h2>
	<form id="modify_form" action="modify.do" method="post">
		<!-- 식별을 위해 글번호를 hidden으로 가져옴 -->
		<input type="hidden" name="num" value="${boardVO.num}">
		<ul>
			<li>
				<label for="name">상품명</label>
				<input type="text" name="name" id="name" size="10" 
				       maxlength="10" class="input-check" value="${boardVO.name}">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" size="12"
					   maxlength="12" class="input-check">
			</li>
			<li>
				<label for="price">가격</label>
				<input type="number" name="price" id="price" size="10" min="0" class="input-check" value="${boardVO.price}">
			</li>
			<li>
				<label for="stock">재고</label>
				<input type="number" name="stock" id="stock" size="10" min="0" class="input-check" value="${boardVO.stock}">
			</li>
			<li>
				<label for="origin">원산지</label>
				<input type="text" name="origin" id="origin" size="15" maxlength="15" class="input-check" value="${boardVO.origin}">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form>
</div>
</body>
</html>