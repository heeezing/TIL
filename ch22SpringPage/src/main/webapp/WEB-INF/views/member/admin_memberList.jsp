<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- [관리자]회원 목록 시작 -->
<script type="text/javascript">
	$(function(){
		//검색 유효성 체크
		$('#search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
				alert('검색어를 입력하세요!');
				$('#keyword').val('').focus();
				return false;
			}
			
		});
	});
</script>
<div class="page-main">
	<h2>회원 목록(관리자 용)</h2>
	<!-- 검색창 시작 -->
	<form action="admin_list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" 
					 <c:if test="${param.keyfield==1}">selected</c:if>>ID</option>
					<option value="2" 
					 <c:if test="${param.keyfield==2}">selected</c:if>>이름</option>
					<option value="3" 
					 <c:if test="${param.keyfield==3}">selected</c:if>>이메일</option>
					<option value="4" 
					 <c:if test="${param.keyfield==4}">selected</c:if>>전체</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='admin_list.do'">
			</li>
		</ul>
	</form>
	<!-- 검색창 끝 -->
	<!-- 목록 시작 -->
	<c:if test="${count == 0}">
		<div class="result-display">표시할 회원 정보가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="striped-table">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>권한</th>
		</tr>
		<c:forEach var="member" items="${list}">
		<tr>
			<td>
				<c:if test="${member.auth == 0}">${member.id}</c:if>
				<c:if test="${member.auth > 0}">
				  <a href="admin_update.do?mem_num=${member.mem_num}">${member.id}</a>
				</c:if>
			</td>
			<td>${member.name}</td>
			<td>${member.phone}</td>
			<td>${member.email}</td>
			<td>${member.reg_date}</td>
			<td>
				<c:if test="${member.auth == 0}">탈퇴</c:if>
				<c:if test="${member.auth == 1}">정지</c:if>
				<c:if test="${member.auth == 2}">일반</c:if>
				<c:if test="${member.auth == 9}">관리자</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<!-- 페이지 처리 -->
	<div class="align-center">${page}</div>
	</c:if>
	<!-- 목록 끝 -->
</div>
<!-- [관리자]회원 목록 끝 -->