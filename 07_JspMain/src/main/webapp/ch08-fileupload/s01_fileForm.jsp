<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>
<!-- 파일 업로드 할 때
	- method는 post로만 가능
	- enctype="multipart/form-data" 의무적으로 넣어야 함. -->
<form action="s02_fileUpload.jsp" method="post" enctype="multipart/form-data"> 
	작성자 <input type="text" name="user"><br>
	제목 <input type="text" name="title"><br>
	파일명 <input type="file" name="uploadfile"><br>
	<input type="submit" value="파일 올리기">
</form>
</body>
</html>