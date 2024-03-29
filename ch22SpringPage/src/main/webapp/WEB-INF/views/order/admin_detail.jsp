<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 주문 내역 시작 -->
<div class="page-main">
	<h2>주문 내역(관리자 용)</h2>
	<table class="basic-table">
		<tr>
			<th>상품명</th>
			<th>수량</th>
			<th>가격</th>
			<th>배송비</th>
			<th>합계</th>
		</tr>
		<c:forEach var="detail" items="${detailList}">
			<tr>
				<td>${detail.item_name}</td>
				<td class="align-center"><fmt:formatNumber
						value="${detail.order_quantity}" /></td>
				<td class="align-center"><fmt:formatNumber
						value="${detail.item_price}" />원</td>
				<td class="align-center"><fmt:formatNumber
						value="${detail.item_delivery}" />원</td>
				<td class="align-center"><fmt:formatNumber
						value="${detail.item_total}" />원</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4" class="align-right"><b>총 구매금액</b></td>
			<td class="align-center"><fmt:formatNumber
					value="${orderVO.order_total}" /></td>
		</tr>
	</table>

	<ul>
		<li>
			<span>받는 사람 : ${orderVO.receive_name}</span>
		</li>
		<li>
			<span>우편 번호 : ${orderVO.receive_post}</span>
		</li>
		<li>
			<span>주소 : ${orderVO.receive_address1} ${orderVO.receive_address2}</span>
		</li>
		<li>
			<span>전화번호 : ${orderVO.receive_phone}</span>
		</li>
		<li>
			<span>남기실 말씀 : ${orderVO.notice}</span>
		</li>
		<li>
			<span>결제수단 : 
			<c:if test="${orderVO.payment == 1}">통장입급</c:if>
			<c:if test="${orderVO.payment == 2}">카드결제</c:if>
			</span>
		</li>
		<li>
			<span>배송상태 : 
			<c:if test="${orderVO.status == 1}">배송대기</c:if>
			<c:if test="${orderVO.status == 2}">배송준비중</c:if>
			<c:if test="${orderVO.status == 3}">배송중</c:if>
			<c:if test="${orderVO.status == 4}">배송완료</c:if>
			<c:if test="${orderVO.status == 5}">주문취소</c:if>
			</span>
		</li>
	</ul>

	<div class="align-center">
		<c:if test="${orderVO.status == 1}">
		<input type="button" value="배송지정보수정" class="default-btn" 
			   onclick="location.href='admin_modify.do?order_num=${orderVO.order_num}'">
		</c:if>
		
		<c:if test="${orderVO.status != 5}">
		<input type="button" value="배송상태수정" class="default-btn" 
			   onclick="location.href='admin_status.do?order_num=${orderVO.order_num}'">
		</c:if>
		
		<input type="button" value="주문목록" class="default-btn"
			   onclick="location.href='${pageContext.request.contextPath}/order/admin_list.do'">
	</div>
</div>
<!-- 주문 내역 끝 -->