<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 다중 업로드 처리</title>
</head>
<body>

<%
	String saveFolder = "/upload"; //파일 업로드 경로
	String encType = "utf-8"; //인코딩 타입
	int maxSize = 10*1024*1024; //최대 업로드 파일 크기(10mb)
	String realFolder = application.getRealPath(saveFolder); //upload의 절대 경로
	
	MultipartRequest multi = new MultipartRequest(request,realFolder,maxSize,encType,
													   new DefaultFileRenamePolicy());
	String img1 = multi.getFilesystemName("uploadfile1");
	String img2 = multi.getFilesystemName("uploadfile2");
%>
이름 : <%= multi.getParameter("name") %><br>
제목 : <%= multi.getParameter("title") %><br>
---------------------------------------------<br>
<img src="/ch07-JspMain/upload/<%= img1 %>"><br>
<img src="/ch07-JspMain/upload/<%= img2 %>"><br>

</body>
</html>