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
			<c:if test="${user_num == board.mem_num}">
			<input type="button" value="글수정" onclick="location.href='updateForm.do?boardv_num=${board.boardv_num}'">
			<input type="button" value="글삭제" id="delete_btn">
			<script type="text/javascript">
				let delete_btn = document.getElementById('delete_btn');
				delete_btn.onclick=function(){
					let choice = confirm('삭제하시겠습니까?');
					if(choice){
						location.href='delete.do?boardv_num=${board.boardv_num}';
					}
				};
			</script>
			</c:if>
			<c:if test="${!empty user_num}">
			<input type="button" value="답글쓰기" onclick="location.href='writeForm.do?parent_num=${board.boardv_num}&depth=${board.depth + 1}'">
			</c:if>
			<input type="button" value="글목록" onclick="location.href='list.do'">
		</div>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>