package kr.s05.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.util.DBUtil;

public class TransactionMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			//트랜잭션을 수동 처리하기 위해 auto commit을 해제
			conn.setAutoCommit(false);  //원래 true가 기본값인데 false를 입력함으로서 해제
			
			//SQL문 작성
			sql = "INSERT INTO test1 VALUES ('Korea', 500)";
			//JDBC 수행 3단계
			pstmt1 = conn.prepareStatement(sql);
			//JBDC 수행 4단계
			pstmt1.executeUpdate();
			
			sql = "INSERT INTO test1 VALUES ('England', 400)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.executeUpdate();
			
			//테스트 용으로 오류가 있는 SQL문 작성
			sql = "INSERT INTO test1 VALUES ('China, 300)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			//정상적으로 작업이 완료되면 commit
			conn.commit();
			
			System.out.println("작업 완료!!!");
		}catch(Exception e) {
			e.printStackTrace();
			//예외가 발생했을 경우 rollback
			try {
				conn.rollback();
			}catch(SQLException es) {
				es.printStackTrace();
			}
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	}
}
