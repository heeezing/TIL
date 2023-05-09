package kr.s04.callablestatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.CallableStatement;

import kr.util.DBUtil;

public class CallableStatementMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("이름 : ");
			String name = br.readLine();
			System.out.print("인상률 : ");
			//String -> float 변환
			float rate = Float.parseFloat(br.readLine());
			
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//프로시저 호출 문장 작성
			//자바에선 프로시저 호출 시 exec이 아닌 {call 프로시저명(?,?, ...)} 문장 사용
			sql = "{call adjust(?,?)}";
			//JDBC 수행 3단계 : CallableStatement 객체 생성
			cstmt = conn.prepareCall(sql);
			//?에 데이터 바인딩
			cstmt.setString(1, name);
			cstmt.setFloat(2, rate);
			//JDBC 수행 4단계 : 프로시저 실행
			int count = cstmt.executeUpdate();
			System.out.println(count + "개의 급여 정보를 수정했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			DBUtil.executeClose(cstmt, conn);
			if(br!=null) try {br.close();} catch(IOException e) {}
		}
	}
}
