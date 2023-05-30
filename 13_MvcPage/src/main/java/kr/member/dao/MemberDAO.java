package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	//회원가입
	public void insertMember(MemberVO member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0; //시퀀스 번호 저장
		
		try {
			//커넥션풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//오토커밋 해제 (sql문이 3개이므로 오토커밋을 해제하고 수작업 필요) 
			conn.setAutoCommit(false);
			
			//회원번호(mem_num) 구하기
			sql = "SELECT zmember_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1); //컬럼인덱스를 통해 가져옴(컬럼명X)
			}
			
			//zmember 테이블에 데이터 저장
			sql = "INSERT INTO zmember (mem_num,id) VALUES (?,?)";
			//?에 데이터 바인딩
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num); //시퀀스
			pstmt2.setString(2, member.getId()); //시퀀스
			pstmt2.executeUpdate();
			
			//zmember_detail 테이블에 데이터 저장
			sql = "INSERT INTO zmember_detail (mem_num,name,passwd,phone,email,zipcode,address1,address2)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getPasswd());
			pstmt3.setString(4, member.getPhone());
			pstmt3.setString(5, member.getEmail());
			pstmt3.setString(6, member.getZipcode());
			pstmt3.setString(7, member.getAddress1());
			pstmt3.setString(8, member.getAddress2());
			pstmt3.executeUpdate();
			
			//SQL문을 실행해서 모두 성공하면 commit
			conn.commit();
		}catch(Exception e) {
			//SQL문이 하나라도 실패하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
	}
	
	
	//ID중복체크 및 로그인처리
	public MemberVO checkMember(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//SQL문 생성
			sql = "SELECT * FROM zmember m "
				+ "LEFT OUTER JOIN zmember_detail d "
				+ "ON m.mem_num = d.mem_num WHERE m.id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, id);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
				member.setPhoto(rs.getString("photo"));
				member.setEmail(rs.getString("email"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return member;
	}
	
	
	//회원상세정보
	//회원정보 수정
	//비밀번호 수정
	//프로필사진 수정
	//회원 탈퇴 (회원정보 삭제)
	
	
	//관리자
	//전체글 개수, 검색글 개수
	//목록, 검색 글 목록
	//회원정보 수정
	
}
