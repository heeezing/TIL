<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시글 상세 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<h2>${board.title}</h2>
	<ul class="detail-info">
		<li>
			<img src="${pageContext.request.contextPath}/member/viewProfile.do?mem_num=${board.mem_num}" 
				 width="40" height="40" class="my-photo">
		</li>
		<li>
			<c:if test="${!empty board_nick_name}">${board.nick_name}</c:if>
			<c:if test="${empty board_nick_name}">${board.id}</c:if>
			<br>
			<c:if test="${!empty board.modify_date}">최근 수정일 : ${board.modify_date}</c:if>
			<c:if test="${empty board.modify_date}">작성일 : ${board.reg_date}</c:if>
			조회 : ${board.hit}
		</li>
	</ul>
	
	<hr size="1" width="100%">
	
	<div class="detail-content">
		${board.content}
	</div>
	<div>
		<%-- 좋아요 버튼 및 개수 --%>
		<img id="output_fav" data-num="${board.board_num}" 
			 src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
		<span id="output_fcount"></span>
		<%-- 댓글 수 --%>
		<span id="output_rcount"></span>
	</div>
	
	<hr size="1" width="100%">
	
	<div class="align-right">
		<c:if test="${!empty user && user.mem_num==board.mem_num}">
		<input type="button" value="수정" onclick="location.href='update.do?board_num=${board.board_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					//히스토리를 지우면서 이동
					location.replace('delete.do?board_num=${board.board_num}');
				}
			};
		</script>
		</c:if>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
	
	<!-- 댓글 UI 시작 -->
	<div id="reply_div">
		<span class="re-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="board_num" value="${board.board_num}" id="board_num">
			<textarea rows="3" cols="50" name="re_content" id="re_content" class="rep-content"
			<c:if test="${empty user}">disabled="disabled"</c:if>
			><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>
			
			<c:if test="${!empty user}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
				<input type="submit" value="전송">
			</div>
			</c:if>
		</form>
	</div>
	<!-- 댓글 목록 시작 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="더보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif" width="100" height="100">
	</div>
	<!-- 댓글 목록 끝 -->
	<!-- 댓글 UI 끝 -->
</div>
<!-- 게시글 상세 끝 -->