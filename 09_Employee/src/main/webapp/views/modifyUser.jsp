<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{
	//전송된 데이터에 대한 인코딩	
	request.setCharacterEncoding("utf-8");
	EmployeeVO emp = new EmployeeVO();
	//num이 전송되지 않았기 때문에 session에 저장된 num를 사용
  	emp.setNum(user_num);
	emp.setName(request.getParameter("name"));
	emp.setPasswd(request.getParameter("passwd"));
	emp.setSalary(Integer.parseInt(request.getParameter("salary")));
	emp.setJob(request.getParameter("job"));
   

  	EmployeeDAO dao = EmployeeDAO.getInstance();
  	dao.updateEmployee(emp);
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정 완료</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>회원정보수정 완료</h1>
	<div class="result-display">
	    <div class="align-center">
			회원정보수정 완료!<br> 
			<button onclick="location.href='myPage.jsp'">MyPage</button>
		</div>
	</div>
</div>
</body>
</html>
<%
	}
%>






