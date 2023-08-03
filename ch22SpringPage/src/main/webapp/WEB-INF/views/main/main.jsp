<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<div class="page-main">
	<h3>최신 상품</h3>
	<div class="image-space">
		<c:forEach var="item" items="${item_list}">
		<div class="horizontal-area">
			<a href="${pageContext.request.contextPath}/item/detail.do?item_num=${item.item_num}">
				<img src="${pageContext.request.contextPath}/item/imageView.do?item_num=${item.item_num}&item_type=1">
				<span>${item.name}</span>
				<br>
				<b><fmt:formatNumber value="${item.price}"/>원</b>
			</a>
		</div>
		</c:forEach>
		<hr width="100%" size="1" noshade="noshade" class="float-clear">
	</div>
</div>
<!-- 내용 끝 -->