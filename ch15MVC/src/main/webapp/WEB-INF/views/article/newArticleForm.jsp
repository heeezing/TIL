<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
</head>
<body>
게시판 글쓰기 폼
<form action="newArticle.do" method="post">
	<input type="hidden" name="parent_id" value="1">
	제목 : <input type="text" name="title"><br>
	내용 : <textarea rows="5" cols="30" name="content"></textarea><br>
	<input type="submit" value="전송">
</form>
</body>
</html>