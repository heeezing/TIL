package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	/*
	 * 싱글턴 패턴 
	 * :생성자를 private으로 지정해서 외부에서 호출할 수 없도록 처리,
	 * 	static 메서드를 호출해서 객체가 한 번만 생성되고 생성된 객체를 공유할 수 있도록 처리하는 방식.
	 *  (객체 1개만 만들어줌. 더 생성 안 됨. 부하 덜 발생.)
	 */
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {} 
	
	//회원 가입
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO smember (num,id,name,passwd,email,phone,reg_date) "
					+ "VALUES (smember_seq.nextval,?,?,?,?,?,SYSDATE)";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			
			//SQL문 실행
			pstmt.executeUpdate();


		}catch(Exception e) {
			//예외여도 jsp에선 에러가 안 난 걸로 인식, 백지 출력
			//따라서 일부러 예외를 던져서 예외가 난 걸로 인식하도록 함.
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	
	//회원 상세 정보
	//아이디 중복체크, 로그인 체크
	//회원 정보 수정
	//회원 탈퇴(회원 정보 삭제)
}
