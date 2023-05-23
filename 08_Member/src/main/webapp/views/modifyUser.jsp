<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num == null){ //로그인 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else { //로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//자바빈 생성, 전송된 데이터 저장
		MemberVO member = new MemberVO();
		member.setNum(user_num);
		member.setName(request.getParameter("name"));
		member.setPasswd(request.getParameter("passwd"));
		member.setEmail(request.getParameter("email"));
		member.setPhone(request.getParameter("phone"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 완료</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
<div class="page-main">
	<h1>회원 정보 수정 완료</h1>
	<div class="result-display">
		회원 정보 수정 완료!<br>
		<button onclick="location.href='myPage.jsp'">MyPage</button>
	</div>
</div>
</body>
</html>
<%
	}
%>