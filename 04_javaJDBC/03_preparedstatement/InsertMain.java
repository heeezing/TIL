package kr.s03.preparedstatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.util.DBUtil;

public class InsertMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("제목 : ");
			String title = br.readLine();
			System.out.print("이름 : ");
			String name = br.readLine();
			System.out.print("메모 : ");
			String memo = br.readLine();
			System.out.print("이메일 : ");
			String email = br.readLine();
			
			//JDBC 수행 1,2단계 
			conn = DBUtil.getConnection();
			
			//SQL문 작성 (명령어는 넣고 데이터만 빼서 ?로 표기)
			sql = "INSERT INTO test2 (num, title, name, memo, email, reg_date)"
					+ "VALUES (test2_seq.nextval, ?, ?, ?, ?, SYSDATE)";
								//생성해놓은 시퀀스
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql); 
			
			//?에 데이터 바인딩 (PreparedStatement의 메서드 활용)
			pstmt.setString(1, title);  //1번째 물음표에 title
			pstmt.setString(2, name);   //2번째 물음표에 name
			pstmt.setString(3, memo);   //3번째 물음표에 memo
			pstmt.setString(4, email);  //4번째 물음표에 email
			
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블에 행을 추가
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 추가했습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
			if(br!=null) try {br.close();} catch(IOException e) {}
		}	
	}
}
