package kr.s02.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe"; //경로
		String db_id = "c##user001";
		String db_password = "1234";
		
		//변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			
			//JDBC 수행 2단계 : Connection 객체 생성
			conn = DriverManager.getConnection(db_url, db_id, db_password);
			
			sql = "SELECT * FROM test1";
			
			//JDBC 수행 3단계 : Statement 
			stmt = conn.createStatement();
			
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블로부터 레코드(행)을 전달받아
			//				  결과집합을 만들고 ResultSet 객체에 담아 반환
			rs = stmt.executeQuery(sql);
			
			System.out.println("ID\t나이");
			
			//ResultSet에 보관된 결과집합에 접근해서 행 단위로 데이터를 추출
			while(rs.next()) { //커서 움직이는 메서드. 행이 있으면 true, 더 이상 행 없으면 빠져나옴.
				/*
				///컬럼명을 통해서 데이터를 반환
				//id와 매칭되어있는 데이터를 String타입으로 반환하겠다는 의미
				System.out.print(rs.getString("id") + "\t"); 
				//age와 매칭되어있는 데이터를 int타입으로 반환하겠다는 의미
				System.out.println(rs.getInt("age"));
				*/
				
				///컬럼인덱스를 통해서 데이터를 반환
				//인덱스1의 데이터를 String타입으로 반환하겠다는 의미
				System.out.print(rs.getString(1) + "\t");
				//인덱스2의 데이터를 int타입으로 반환하겠다는 의미
				System.out.println(rs.getInt(2));
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	
	}
}
