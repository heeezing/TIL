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
	<h2>상품 목록(관리자 용)</h2>
	<!-- 검색창 -->
	<form action="admin_list.do" id="search_form" method="get">
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
				<input type="button" value="목록" onclick="location.href='admin_list.do'">
			</li>
		</ul>
		<div class="align-right">
			<input type="button" value="상품등록" onclick="location.href='admin_write.do'">
		</div>
	</form>
	
	<!-- 목록 -->
	<c:if test="${count == 0}">
	<div class="result-display">표시할 상품이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>번호</th>
			<th>상품명</th>
			<th>가격</th>
			<th>재고</th>
			<th>등록일</th>
			<th>상태</th>
		</tr>
		<c:forEach var="item" items="${list}">
		<tr>
			<td class="align-center">${item.item_num}</td>
			<td class="align-center">
				<a href="admin_modify.do?item_num=${item.item_num}">${item.name}</a>
			</td>
			<td class="align-center">
				<fmt:formatNumber value="${item.price}"/>
			</td>
			<td class="align-center">
				<fmt:formatNumber value="${item.quantity}"/>
			</td>
			<td class="align-center">${item.reg_date}</td>
			<td class="align-center">
				<c:if test="${item.status == 1}">미표시</c:if>
				<c:if test="${item.status == 2}">표시</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!-- 페이지 -->
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 상품 목록 끝 -->