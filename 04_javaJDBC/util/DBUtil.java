package kr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DBUtil {
		private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
		private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; //경로
		private static final String DB_ID = "c##user001";
		private static final String DB_PASSWORD = "1234";
		
		//Connection 객체를 생성해서 반환하는 메서드 생성
		//static이므로 이후 객체 생성 필요X -> 매번 비슷한 작업 안 해도 되어 편리.
		public static Connection getConnection() throws ClassNotFoundException,
														SQLException{
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(DB_DRIVER);
			
			//JDBC 수행 2단계 : Connection 객체 생성
			return DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);	
		}
		
		//자원 정리 메서드 생성
		public static void executeClose(ResultSet rs,
										PreparedStatement pstmt, Connection conn) {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
		
		
}
