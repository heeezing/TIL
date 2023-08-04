<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 상품 상세 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/shop.item-detail.js"></script>
<div class="page-main">
	<c:if test="${item.status == 1}">
	<div class="result-display">
		<div class="align-center">
			본 상품은 판매 중지되었습니다.
			<input type="button" value="판매상품 보기" onclick="location.href='list.do'">
		</div>
	</div>
	</c:if>

	<c:if test="${item.status == 2}">
	<h2 class="align-center">${item.name}</h2>
	<div class="item-image">
		<img src="imageView.do?item_num=${item.item_num}&item_type=2" width="400" height="400">
	</div>
	<div class="item-detail">
		<form id="item_cart" method="post">
			<input type="hidden" name="item_num" value="${item.item_num}" id="item_num">
			<input type="hidden" name="item_price" value="${item.price}" id="item_price">
			<input type="hidden" name="item_quantity" value="${item.quantity}" id="item_quantity">
			<ul>
				<li>가격 : <b><fmt:formatNumber value="${item.price}"/></b></li>
				<li>재고 : <span><fmt:formatNumber value="${item.quantity}"/></span></li>
				<!-- 재고가 있을 경우 -->
				<c:if test="${item.quantity > 0}">
				<li>
					<label for="order_quantity">구매수량</label>
					<input type="number" name="order_quantity" min="1" max="${item.quantity}" 
						   id="order_quantity" class="quantity-width">
				</li>
				<li>
					<span id="item_total_txt">총 주문 금액 : 0원</span>
				</li>
				<li>
					<span>배송비 : 
					<c:if test="${item.delivery_fee > 0}">
					<fmt:formatNumber value="${item.delivery_fee}"/>원
					</c:if>
					
					<c:if test="${item.delivery_fee == 0}">
					<span>무료</span>
					</c:if>
					
					<c:if test="${item.delivery_limit > 0}">
					(<fmt:formatNumber value="${item.delivery_limit}"/>원 이상 구매 시 무료배송)
					</c:if>
					</span>
				</li>
				<li>
					<input type="submit" value="장바구니에 담기">
				</li>
				</c:if>
				
				<!-- 재고가 없을 경우 -->
				<c:if test="${item.quantity <= 0}">
				<li class="align-center">
					<span class="sold-out">품절</span>
				</li>
				</c:if>
			</ul>
		</form>
	</div>
	
	<hr size="1" noshade width="100%">
	
	<p>${item.detail}</p>
	
	</c:if>
</div>
<!-- 상품 상세 끝 -->