<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">SpringPage</h2>
<div class="align-right">
	<!-- 우리가 필요한 정보를 통째로 user에 담아 세션에 저장할 예정 -->
	<c:if test="${empty user}">
	<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
	<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
	</c:if>
	<c:if test="${empty user || user.auth < 9}">
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
	</c:if>
</div>
<!-- 상단 끝 -->