<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습</title>
</head>
<body>
<%--
[실습]
배열(score) 생성,
국어/영어/수학 점수를 배열에 저장하고 배열에 저장된 값을 이용해서 총점/평균을 구하시오.
[출력 예시]
국어 : 88
영어 : 99
수학 : 87
총점 : 274
평균 : 91
--%>
<%!
	//배열 생성
	String[] course = {"국어", "영어", "수학"};
	int[] score = {88,99,87};
%>

<%
	int sum = 0;
	for(int i=0 ; i<score.length ; i++){
%>
	<%= course[i] %> : <%= score[i] %><br>
<%
		sum += score[i];
	}
	int avg = sum / score.length;
%>
<%-- course 배열 없을 시
국어 : <%= score[0] %><br>
영어 : <%= score[1] %><br>
수학 : <%= score[2] %><br>
--%>
총점 : <%= sum %><br>
평균 : <%= avg %>

</body>
</html>