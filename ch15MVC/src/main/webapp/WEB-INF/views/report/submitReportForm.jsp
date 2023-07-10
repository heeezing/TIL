<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리포트 등록 폼</title>
</head>
<body>
<form:form action="submitReport.do" enctype="multipart/form-data" modelAttribute="report">
	<p>
		<label for="subject">제목</label>
		<form:input path="subject"/>
		<form:errors path="subject"/>
	</p>
	<p>
		<label for="reportFile">리포트 파일</label>
		<!-- 파일 타입은 커스텀 태그가 없다. -->
		<input type="file" id="reportFile" name="reportFile">
		<form:errors path="reportFile"/>
	</p>
	<p>
		<form:button>리포트 전송</form:button>
	</p>
</form:form>
</body>
</html>