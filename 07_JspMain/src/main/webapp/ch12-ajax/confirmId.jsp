<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ include file="dbInfo.jspf" %>
<%
	//전송된 데이터 인코딩 처리
	request.setCharacterEncoding("UTF-8");

	//전송된 데이터 반환
	String id = request.getParameter("id");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	try{
		//JDBC 수행 1단계
		Class.forName(driverName);
		
		//JDBC 수행 2단계
		conn = DriverManager.getConnection(jdbcUrl, dbId,dbPass);
		//SQL문 생성
		sql = "SELECT id FROM people WHERE id=?";
		
		//JDBC 수행 3단계
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setString(1, id);
		
		//JDBC 수행 4단계
		rs = pstmt.executeQuery();
		if(rs.next()){ //행 있음->id 중복
%>
		<%-- JSON문 --%>
		{"result":"idDuplicated"}
<%
		}else{ //행 없음->id 미중복
%>
		{"result":"idNotFound"}
<%	
		}
	}catch(Exception e){
%>
		{"result":"failure"}
<%
		e.printStackTrace();
	}finally{
		//자원 정리
		if(rs!=null) try{rs.close();} catch(SQLException e){}
		if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
		if(conn!=null) try{conn.close();} catch(SQLException e){}
	}
%>