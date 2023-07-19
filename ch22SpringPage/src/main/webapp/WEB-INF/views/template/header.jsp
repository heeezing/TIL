<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">SpringPage</h2>
<div class="align-right">
	<!-- 우리가 필요한 정보를 통째로 user에 담아 세션에 저장할 예정 -->
	
	<!-- 로그인O, 일반회원인 경우 -->
	<c:if test="${!empty user && user.auth == 2}">
	<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>
	
	<!-- 로그인O -->
	<c:if test="${!empty user}">
	<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
	</c:if>
	
	<!-- 로그인O, 닉네임 있는 경우 -->
	<c:if test="${!empty user && !empty user.nick_name}">
		[<span class="user_name">${user.nick_name}</span>]
	</c:if>
	<!-- 로그인O, 닉네임 없는 경우 -->
	<c:if test="${!empty user && empty user.nick_name}">
		[<span class="user_name">${user.id}</span>]
	</c:if>
	
	<!-- 로그인 상태인 경우 -->
	<c:if test="${!empty user}">
	<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	</c:if>
	
	<!-- 로그아웃 상태인 경우 -->
	<c:if test="${empty user}">
	<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
	<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>
	</c:if>
	
	<c:if test="${empty user || user.auth < 9}">
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
	</c:if>
</div>
<!-- 상단 끝 -->
