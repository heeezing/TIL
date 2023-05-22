<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ include file="dbInfo.jspf" %>
<%-- 전체 데이터를 목록 형태로 보여주기 때문에 배열형태 [] 안에 작성--%>
[<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	try{
		//JDBC 수행 1단계
		Class.forName(driverName);
		
		//JDBC 수행 2단계
		conn = DriverManager.getConnection(jdbcUrl,dbId,dbPass);
		//SQL문 작성
		sql = "SELECT * FROM people ORDER BY id ASC";
		
		//JDBC 수행 3단계
		pstmt = conn.prepareStatement(sql);
		
		//JDBC 수행 4단계
		rs = pstmt.executeQuery();
		while(rs.next()){
			String id = rs.getString("id");
			String name = rs.getString("name");
			String job = rs.getString("job");
			String address = rs.getString("address");
			String blood_type = rs.getString("blood_type");
			
			//.getRow(): 행번호를 구해주는 메서드 (1번부터 출발. 0번X)
			if(rs.getRow()>1){
				out.print(",");
			}
%>
			{
				"id":"<%= id %>",
				"name":"<%= name %>",
				"job":"<%= job %>",
				"address":"<%= address %>",
				"blood_type":"<%= blood_type %>"
			}
<%			
		}	
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		//자원 정리
		if(rs!=null) try{rs.close();} catch(SQLException e){}
		if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
		if(conn!=null) try{conn.close();} catch(SQLException e){}
	}
%>]