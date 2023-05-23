<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//로그아웃
	session.invalidate();
	//main.jsp로 redirect
	response.sendRedirect("main.jsp");
%>