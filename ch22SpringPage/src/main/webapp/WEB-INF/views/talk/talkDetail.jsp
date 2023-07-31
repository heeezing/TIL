<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 채팅 메시지 처리 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/message.js"></script>
<div class="page-main">
	<h1 id="chatroom_title">
		<span id="chatroom_name">${room_name}</span> 채팅방
		<input type="button" value="채팅방이름 변경" id="change_name">
	</h1>
	<div class="align-right">
		<input type="button" value="멤버 추가" id="opener">
		<input type="button" value="방탈출" id="delete_talkroom">
		<input type="button" value="목록" onclick="location.href='talkList.do'">
	</div>
	<p>
		채팅 멤버 : 
		<span id="chat_member">${chatMember}</span>
		<span id="chat_mcount">(${chatCount}명)</span>
	</p>
	<div id="chatting_message"></div>
	
	<form method="post" id="detail_form">
		<input type="hidden" name="talkroom_num" value="${param.talkroom_num}" id="talkroom_num">
		<ul>
			<li>
				<label for="message">내용</label>
				<textarea rows="5" cols="60" name="message" id="message"></textarea>
			</li>
		</ul>
		<div class="align-center" id="message_btn">
			<input type="submit" value="전송">
		</div>
	</form>
</div>
<!-- 채팅 메시지 처리 끝 -->