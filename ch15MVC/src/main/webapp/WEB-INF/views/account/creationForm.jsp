<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<!-- 커스텀 태그 -->
	<!-- modelAttribute="vo"를 기재해야 컨트롤러에서 초기화한 자바빈(vo)의 데이터를 가져옴. -->
	<form:form action="create.do" modelAttribute="vo"> <!-- method="post"는 생략 가능(기본값) -->
		아이디 : <form:input path="id"/>
		<form:errors path="id"/>
		<!-- BindingResult와 연동하면서, 오류가 있을 시 [<span>필수항목입니다.</span>] 문구로 변경 --><br>
		이름 : <form:input path="name"/>
		<form:errors path="name"/><br>
		우편번호 : <form:input path="zipcode"/>
		<form:errors path="zipcode"/><br>
		주소1 : <form:input path="address1"/>
		<form:errors path="address1"/><br>
		주소2 : <form:input path="address2"/>
		<form:errors path="address2"/><br>
		<form:button>전송</form:button>
	</form:form>
</body>
</html>