<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- [사용자]주문 목록 시작 -->
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
			
		});
	});
</script>
<div class="page-main">
	<h2>주문 목록</h2>
	<!-- 검색창 시작 -->
	<form action="orderList.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" 
					 <c:if test="${param.keyfield==1}">selected</c:if>>주문번호</option>
					<option value="2" 
					 <c:if test="${param.keyfield==2}">selected</c:if>>상품명</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='orderList.do'">
			</li>
		</ul>
	</form>
	<!-- 검색창 끝 -->
	<!-- 목록 시작 -->
	<c:if test="${count == 0}">
		<div class="result-display">표시할 주문 정보가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>주문번호</th>
			<th>상품명</th>
			<th>총 구매 금액</th>
			<th>주문 날짜</th>
			<th>배송 상태</th>
		</tr>
		<c:forEach var="order" items="${list}">
		<tr>
			<td>${order.order_num}</td>
			<td>
				<a href="orderModify.do?order_num=${order.order_num}">${order.item_name}</a>
			</td>
			<td><fmt:formatNumber value="${order.order_total}"/>원</td>
			<td>${order.reg_date}</td>
			<td>
				<c:if test="${order.status == 1}">배송대기</c:if>
				<c:if test="${order.status == 2}">배송준비중</c:if>
				<c:if test="${order.status == 3}">배송중</c:if>
				<c:if test="${order.status == 4}">배송완료</c:if>
				<c:if test="${order.status == 5}">주문취소</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!-- 페이지 처리 -->
	<div class="align-center">${page}</div>
	</c:if>
	<!-- 목록 끝 -->
</div>
<!-- [사용자]주문 목록 끝 -->