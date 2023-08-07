<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 장바구니 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/shop.cart.js"></script>
<div class="page-main">
	<h2>장바구니</h2>
	
	<c:if test="${empty list}">
	<div class="result-display">
		장바구니에 담은 상품이 없습니다.
	</div>
	</c:if>
	
	<c:if test="${!empty list}">
	<form id="cart_order" method="post" 
		  action="${pageContext.request.contextPath}/order/orderForm.do">
		<table class="basic-table">
			<tr>
				<th>
					<input type="checkbox" checked="checked" id="chk_all">
				</th>
				<th>상품명</th>
				<th>수량</th>
				<th>상품가격</th>
				<th>배송비</th>
				<th>합계</th>
			</tr>
			<c:forEach var="cart" items="${list}">
				<tr>
					<td class="align-center">
						<input type="checkbox" name="cart_numbers" checked="checked" 
							   class="choice-btn" value="${cart.cart_num}">
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/item/detail.do?item_num=${cart.item_num}">
							<img src="${pageContext.request.contextPath}/item/imageView.do?item_num=${cart.item_num}&item_type=1" 
								 width="80" height="80">
							${cart.itemVO.name}
						</a>
					</td>
					<td class="align-center">
						<div class="order-quantity">
						<c:if test="${cart.itemVO.status == 1 
									or cart.itemVO.quantity < cart.order_quantity}">[판매중지]</c:if>
						<c:if test="${cart.itemVO.status == 2 
									and cart.itemVO.quantity >= cart.order_quantity}">
						<input type="number" name="order_quantity" min="1" max="${cart.itemVO.quantity}" 
							   autocomplete="off" value="${cart.order_quantity}" class="quantity-width">
						<input type="button" value="변경" class="cart-modify" 
							   data-cartnum="${cart.cart_num}" data-itemnum="${cart.item_num}">
						</c:if>
						</div>
					</td>
					<td class="align-center">
						<span class="item-price" data-price="${cart.itemVO.price}">
							<fmt:formatNumber value="${cart.itemVO.price}"/>원
						</span>
					</td>
					<td class="align-center">
						<span class="delivery-fee" data-fee="${cart.itemVO.delivery_fee}">
							<fmt:formatNumber value="${cart.itemVO.delivery_fee}"/>원
						</span>
					</td>
					<td class="align-center">
						<div class="sub-total" data-total="${cart.sub_total}">
							<fmt:formatNumber value="${cart.sub_total}"/>원
							<br>
							<input type="button" value="삭제" class="cart-del" data-cartnum="${cart.cart_num}">
						</div>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="5" class="align-right"><b>총 구매 금액</b></td>
					<td class="align-center">
						<span class="all-total" data-alltotal="${all_total}">
							<fmt:formatNumber value="${all_total}"/>원
						</span>
					</td>
				</tr>
		</table>
		
		<div class="align-center">
			<input type="submit" id="order_btn" value="구매하기">
		</div>
	</form>
	</c:if>
</div>
<!-- 장바구니 끝 -->