<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>답글게시판 목록</h2>
		<!-- 검색창 시작 : get방식 -->
		<form id="search_form" action="list.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>작성자</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<!-- 검색창 끝 -->
		<div class="list-space align-right">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'"
			 <c:if test="${empty user_num}">disabled='disabled'</c:if>
			>
			<input type="button" value="목록" onclick="location.href='list.do'">
			<input type="button" value="홈으로" 
			 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		
		<c:if test="${count == 0}">
			<div class="result-display">
				표시할 게시물이 없습니다.
			</div>		
		</c:if>
		
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.boardv_num}</td>
				<td width="40%">
				<c:set var="wid" value="0"/>
				<c:if test="${board.depth > 0}"><%--자식글일 경우 --%>
					<c:set var="wid" value="${20*board.depth}"/> <%-- 넓이를 늘려서 표현 --%>
					<img src="${pageContext.request.contextPath}/images/level.gif" width="${wid}">
					<img src="${pageContext.request.contextPath}/images/re.gif" width="${wid}">
				</c:if>
				<c:if test="${board.depth <= 0}"><%-- 부모글일 경우 --%>
					<img src="${pageContext.request.contextPath}/images/level.gif" width="${wid}">
				</c:if>
				<a href="content.do?boardv_num=${board.boardv_num}">${board.subject}</a>
				</td>
				<td>${board.id}</td>
				<td>${board.reg_date}</td>
				<td>${board.readcount}</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>