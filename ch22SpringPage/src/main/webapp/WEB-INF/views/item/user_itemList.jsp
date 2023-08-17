<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 상품 목록 시작 -->
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
				alert('검색어를 입력하세요!');
				#('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
<div class="page-main">
	<h2>상품 목록</h2>
	<!-- 검색창 -->
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" 
					  <c:if test="${param.keyfield == 1}">selected</c:if>>상품명</option>
					<option value="2" 
					  <c:if test="${param.keyfield == 2}">selected</c:if>>내용</option>
					<option value="3" 
					  <c:if test="${param.keyfield == 3}">selected</c:if>>상품명+내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	
	<!-- 목록 -->
	<c:if test="${count == 0}">
	<div class="result-display">표시할 상품이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<div class="item-space">
		<c:forEach var="item" items="${list}">
		<div class="horizontal-area">
			<a href="detail.do?item_num=${item.item_num}">
				<img src="imageView.do?item_num=${item.item_num}&item_type=1">
				<span>${item.name}</span>
				<br>
				<b><fmt:formatNumber value="${item.price}"/>원</b>
			</a>
		</div>
		</c:forEach>
		<!-- 페이지 -->
		<div class="float-clear align-center">${page}</div>
	</div>
	</c:if>
</div>
<!-- 상품 목록 끝 -->