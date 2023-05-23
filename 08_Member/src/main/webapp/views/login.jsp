<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	//전송된 데이터에 대한 인코딩 처리
	request.setCharacterEncoding("utf-8");
	//전송된 데이터 반환
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	MemberDAO dao = MemberDAO.getInstance(); //객체 생성
	
	//아이디 존재 여부
	MemberVO member = dao.checkMember(id);
	boolean check = false;
	
	if(member!=null){ //아이디가 존재할 경우
		//비밀번호 일치 여부 체크
		check = member.isCheckedPassword(passwd);		
	}
	
	if(check){ //아이디,비밀번호 인증 성공
		//로그인 처리 
		//num,id - 로그인 됐는지 여부 확인용, 레코드 읽어올 때 식별자용
		session.setAttribute("user_num", member.getNum());
		session.setAttribute("user_id", id);
		
		//main.jsp로 redirect 처리
		response.sendRedirect("main.jsp");
	}else{ //아이디 또는 비밀번호 불일치
%>
		<script>
			alert('아이디 또는 비밀번호 불일치');
			history.go(-1);
		</script>
<%	
	}
%>
