<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Admin 메뉴 시작 -->
<div class="side-bar">
	<ul>
		<li>
			<input type="button" class="menu-btn" value="회원 관리"
			 onclick="location.href='${pageContext.request.contextPath}/member/admin_list.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="상품 관리"
			 onclick="location.href='${pageContext.request.contextPath}/item/admin_list.do'">
		</li>
		<li>
			<input type="button" class="menu-btn" value="회원 탈퇴"
			 onclick="location.href='${pageContext.request.contextPath}/order/admin_list.do'">
		</li>
	</ul>
</div>
<!-- Admin 메뉴 끝 -->