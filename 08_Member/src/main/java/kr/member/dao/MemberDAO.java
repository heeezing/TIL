package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			//jsp가 아니라 클래스이기 때문에 예외 화면 만들지 못함.
			//예외여도 jsp에선 에러가 안 난 걸로 인식, 백지 출력.
			//따라서 일부러 예외를 던져서 예외가 난 걸로 인식하도록 함.
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	
	//회원 상세 정보 (수정 폼에도 사용 가능)
	public MemberVO getMember(int num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM smember WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO(); //자바빈 객체 생성
				member.setNum(rs.getInt("num"));
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setReg_date(rs.getDate("reg_date"));
			}
			//else일 경우 member => null반환
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return member;
	}

	
	//아이디 중복체크, 로그인 체크
	public MemberVO checkMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM smember WHERE id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, id);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) { //행 있음 = 중복되는 아이디 있음
				member = new MemberVO(); //자바빈 객체 생성
				member.setId(rs.getString("id"));
				member.setNum(rs.getInt("num"));
				member.setPasswd(rs.getString("passwd"));
			}
			//어차피 검색 안되면 if블록으로 들어오지 못해서
			//return member할 때 null로 반환 -> else부분 만들 필요X
			
		}catch(Exception e) {
			throw new Exception(e); //예외 문구 처리
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	
	//회원 정보 수정
	public void updateMember(MemberVO member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//SQL문 생성
			sql = "UPDATE smember SET name=?,passwd=?,email=?,phone=? WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setInt(5, member.getNum());
			//SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//회원 탈퇴(회원 정보 삭제)
	public void deleteMember(int num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션 할당 받음
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM smember WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}
