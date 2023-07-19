<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- MY페이지 시작 -->
<div class="page-main">
	<h2>회원상세정보<input type="button" value="회원정보수정" onclick="location.href='update.do'"></h2>
	<ul>
		<li>이름 : ${member.name}</li>
		<li>별명 : ${member.nick_name}</li>
		<li>전화번호 : ${member.phone}</li>
		<li>이메일 : ${member.email}</li>
		<li>우편번호 : ${member.zipcode}</li>
		<li>주소 : ${member.address1} ${member.address2}</li>
		<li>가입일 : ${member.reg_date}</li>
		<c:if test="${!empty member.modify_date}">
		<li>정보수정일 : ${member.modify_date}</li>
		</c:if>
	</ul>
</div>
<!-- MY페이지 끝 -->