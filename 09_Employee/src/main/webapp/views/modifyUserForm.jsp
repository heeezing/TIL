<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css" type="text/css">
<script type="text/javascript">
	window.onload=function(){
		let form = document.getElementById('modify_form');
		form.onsubmit=function(){
			let items = document.querySelectorAll('.input-check');
    		for(let i=0;i<items.length;i++){
    			if(items[i].value.trim()==''){
    				let label = document.querySelector('label[for="'+items[i].id+'"]');
    				alert(label.textContent  + ' 항목 필수 입력');
    				items[i].value = '';
    				items[i].focus();
    				return false;
    			}	
    		}
		};
	};
</script>
</head>
<body>
<%
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO emp = dao.getEmployee(user_num);
%>

<div class="page-main">
	<h1>회원정보수정</h1>
	<form action="modifyUser.jsp" method="post" id="modify_form">
		<ul>
			<li>
				<label for="name">이름</label>
				<input type="text" name="name" id="name"
					   value="<%= emp.getName() %>"
				       maxlength="10" class="input-check">              
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd"
				       maxlength="12" class="input-check">              
			</li>
			<li>
				<label for="salary">급여</label>
				<input type="number" name="salary" id="salary"
				       value="<%= emp.getSalary() %>"
				       min="1" max="99999999"
				       class="input-check">              
			</li>
			<li>
				<label for="job">업무</label>
				<input type="text" name="job" id="job"
					   value="<%= emp.getJob() %>"
				       maxlength="10" class="input-check">              
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로"
			       onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>
<%
	}
%>