<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("utf-8");
	EmployeeVO emp = new EmployeeVO();
	emp.setId(request.getParameter("id"));
	emp.setName(request.getParameter("name"));
	emp.setPasswd(request.getParameter("passwd"));
	emp.setSalary(Integer.parseInt(request.getParameter("salary")));
	emp.setJob(request.getParameter("job"));

	EmployeeDAO dao = EmployeeDAO.getInstance();
	dao.insertEmployee(emp);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원가입</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>사원가입 완료</h1>
	<div class="result-display">
	    <div class="align-center">
		사원가입 성공! <br>
		<button onclick="location.href='main.jsp'">홈으로</button>
		</div>
	</div>
</div>
</body>
</html>



