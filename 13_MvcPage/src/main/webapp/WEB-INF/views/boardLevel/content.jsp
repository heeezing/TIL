<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h3>${board.subject}</h3>
		<ul>
			<li>글번호 : ${board.boardv_num}</li>
			<li>조회수 : ${board.readcount}</li>
			<li>작성자 : ${board.id}</li>
		</ul>
		<hr width="100%" noshade size="1">
		<c:if test="${!empty board.image}">
		<img src="${pageContext.request.contextPath}/upload/${board.image}" width="500"><br>
		</c:if>
		<p>${board.content}</p>
		<hr width="100%" noshade size="1">
		<div align="right">
			<c:if test="${!empty board.modify_date}">
			최근 수정일 : ${board.modify_date}
			</c:if>
			작성일 : ${board.reg_date}
		</div>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>