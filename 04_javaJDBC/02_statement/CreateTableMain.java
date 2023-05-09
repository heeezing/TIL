package kr.s02.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe"; //경로
		String db_id = "c##user001";
		String db_password = "1234";
		
		//create, alter, drop 문장 모두 사용 가능
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			
			//JDBC 수행 2단계 : Connection 객체 생성 (url,id,비번 인증)
			conn = DriverManager.getConnection(db_url, db_id, db_password);
			/*
			 * <테이블을 생성하는 SQL문>
			 * 주의) 접속한 계정에 테이블명이 중복되면 에러가 발생하기 때문에
			 * 		동일 계정에서는 고유한 테이블명을 지정해야 함.
			 */
			sql = "CREATE TABLE test1(id VARCHAR2(10), age NUMBER(3))";
			
			//JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement(); //실어나르는 역할
			
			//JDBC 수행 4단계 : SQL문을 실행해서 DB에 테이블을 생성
			stmt.executeUpdate(sql);
			
			System.out.println("테이블이 정상적으로 생성되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
}
