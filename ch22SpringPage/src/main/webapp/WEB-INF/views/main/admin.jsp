<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 관리자 메인 시작 -->
<div class="page-main">
	<h2>회원목록</h2>
	<table class="striped-table">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>권한</th>
		</tr>
		<c:forEach var="member" items="${memberList}">
		<tr>
			<td>
				<c:if test="${member.auth == 0}">${member.id}</c:if>
				<c:if test="${member.auth > 0}">
				  <a href="${pageContext.request.contextPath}/member/admin_update.do?mem_num=${member.mem_num}">${member.id}</a>
				</c:if>
			</td>
			<td>${member.name}</td>
			<td>${member.phone}</td>
			<td>${member.email}</td>
			<td>${member.reg_date}</td>
			<td>
				<c:if test="${member.auth == 0}">탈퇴</c:if>
				<c:if test="${member.auth == 1}">정지</c:if>
				<c:if test="${member.auth == 2}">일반</c:if>
				<c:if test="${member.auth == 9}">관리자</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<h2>상품목록</h2>
	<table class="striped-table">
		<tr>
			<th>번호</th>
			<th>상품명</th>
			<th>가격</th>
			<th>재고수</th>
			<th>등록일</th>
			<th>상태</th>
		</tr>
		<c:forEach var="item" items="${itemList}">
		<tr>
			<td>${item.item_num}</td>
			<td>
				<a href="${pageCotext.request.contextPath}/item/admin_modify.do?item_num=${item.item_num}">${item.name}</a>
			</td>
			<td><fmt:formatNumber value="${item.price}"/></td>
			<td><fmt:formatNumber value="${item.quantity}"/></td>
			<td>${item.reg_date}</td>
			<td>
				<c:if test="${item.status == 1}">미표시</c:if>
				<c:if test="${item.status == 2}">표시</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<h2>주문목록</h2>
	<table class="striped-table">
		<tr>
			<th>주문번호</th>
			<th>주문자ID</th>
			<th>상품명</th>
			<th>총주문금액</th>
			<th>주문날짜</th>
			<th>배송상태</th>
		</tr>
		<c:forEach var="order" items="${orderList}">
		<tr>
			<td>${order.order_num}</td>
			<td>${order.id}</td>
			<td>
				<a href="${pageCotext.request.contextPath}/order/admin_modify.do?order_num=${order.order_num}">
					${order.item_name}
				</a>
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
</div>
<!-- 관리자 메인 끝 -->