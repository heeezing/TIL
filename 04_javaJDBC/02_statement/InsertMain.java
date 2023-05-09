package kr.s02.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe"; //경로
		String db_id = "c##user001";
		String db_password = "1234";
		
		//변수 선언 (try블럭 안에서 하게 되면 지역변수로 인식해 finally에서 인식X)
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver); //드라이버를 메모리로 로드
			
			//JDBX 수행 2단계 : Connection 객체를 생성해서 오라클에 접속
			conn = DriverManager.getConnection(db_url, db_id, db_password);
			
			//SQL문 작성 (이미 테이블은 생성되있으므로 데이터 바로 입력)
			//하나의 sql문만 있기 때문에 Connection 객체의 auto commit 기능으로도 가능.
			//따로 commit, 예외체크, rollback 명시할 필요 X
			sql = "INSERT INTO test1 (id, age) VALUES ('d''star', 20)";
			
			//JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement(); 
			
			//JDBD 수행 4단계 : SQL문을 실행해서 하나의 행을 삽입
			//                삽입 작업 후 삽입한 행의 개수를 반환
			int count = stmt.executeUpdate(sql);
			System.out.println(count + "개의 행을 추가했습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
}
