<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 비밀번호 변경 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/changePassword.js"></script>
<div class="page-main">
	<h2>비밀번호 변경</h2>
	<form:form modelAttribute="memberVO" action="changePassword.do" id="member_change">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<form:label path="now_passwd">현재 비밀번호</form:label>			
				<form:password path="now_passwd"/>
				<form:errors path="now_passwd" cssClass="error-color"/>
			</li>
			<li>
				<form:label path="passwd">새 비밀번호</form:label>			
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<!-- 자바빈에 있는 게 아니므로 커스텀 태그를 쓸 수 없다 -->
				<label for="confirm_passwd">새 비밀번호 확인</label>			
				<input type="password" id="confirm_passwd">
				<span id="message_id"></span>
			</li>
		</ul>
		
		<div class="align-center">
			<form:button class="default-btn">전송</form:button>
			<input type="button" value="홈으로" class="default-btn" 
				   onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>
<!-- 비밀번호 변경 끝 -->