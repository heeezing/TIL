package kr.s08.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kr.util.DBUtil;

public class BookDAO {
	//도서 등록 - 관리자
	public void registerBook(String bk_name, String bk_category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "INSERT INTO book(bk_num, bk_name, bk_category) VALUES(book_seq.nextval,?,?)";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//?
			pstmt.setString(1, bk_name);
			pstmt.setString(2, bk_category);
			//JDBC 4
			int count = pstmt.executeUpdate();
			System.out.println(count + "권의 도서가 등록되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//도서 목록 보기 - 관리자, 사용자
	public void selectBookList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "SELECT * FROM book ORDER BY bk_num DESC";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//JDBC 4
			rs = pstmt.executeQuery();
			System.out.println("도서번호\t제목\t카테고리\t등록일");
			System.out.println("------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getInt("bk_num") + "\t");
				System.out.print(rs.getString("bk_name") + "\t");
				System.out.print(rs.getString("bk_category") + "\t");
				System.out.println(rs.getDate("bk_regdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	
	//아이디 중복 체크
	public int checkId(String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT me_id FROM member WHERE me_id=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = 1; //0으로 초기화해놔서 else문 필요X
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count; //아이디 중복 시 1, 미중복 시 0 반환
	}
	
	
	//회원 가입
	public void registerMember(String me_id, String me_passwd, String me_name, String me_phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "INSERT INTO member (me_num, me_id, me_passwd, me_name, me_phone)"
					+ "VALUES (member_seq.nextval,?,?,?,?)";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//?
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			pstmt.setString(3, me_name);
			pstmt.setString(4, me_phone);
			//JDBC 4
			int count = pstmt.executeUpdate();
			System.out.println(count + "분의 회원가입이 완료되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//로그인 체크
	public int loginCheck(String me_id, String me_passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int me_num = 0; //회원 번호
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT me_num FROM member WHERE me_id=? AND me_passwd=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				me_num = rs.getInt("me_num");
			} //me_num이 0으로 초기화되어있으므로 else문 필요x. 0값 전달 시 -> 로그인 실패
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return me_num;
	}
	
	
	//회원 목록 보기 -관리자
	public void selectMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "SELECT * FROM member ORDER BY me_num DESC";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//JDBC 4
			rs = pstmt.executeQuery();
			System.out.println("회원번호\t아이디\t이름\t전화번호\t\t등록일");
			System.out.println("--------------------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getInt("me_num") + "\t");
				System.out.print(rs.getString("me_id") + "\t");
				System.out.print(rs.getString("me_name") + "\t");
				System.out.print(rs.getString("me_phone") + "\t");
				System.out.println(rs.getDate("me_regdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	//도서 대출 여부 확인
	//도서번호(bk_num)로 검색해서 re_status의 값이 0이면 대출 가능, 1이면 대출 불가능
	public int loanCheck(int bk_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int re_status = 2;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "SELECT re_status FROM reservation WHERE bk_num=?";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//? 바인딩
			pstmt.setInt(1, bk_num);
			//JDBC 4
			rs = pstmt.executeQuery();
			if(rs.next()) {
				re_status = rs.getInt("re_status");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}	
		return re_status;
	}
	
	//도서 대출 등록
	public void loanBook(int bk_num, int me_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "INSERT INTO reservation VALUES(reservation_seq.nextval, 1, ?, ?, SYSDATE, NULL)";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//? 바인딩
			pstmt.setInt(1, bk_num);
			pstmt.setInt(2, me_num);
			//JDBC 4
			int count = pstmt.executeUpdate();
			System.out.println(count + "권의 책이 대출되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
		
	//대출 목록 보기 -관리자
	//대출 및 반납 모든 데이터 표시
	public void selectLoanList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "SELECT r.re_num, b.bk_num, b.bk_name, r.re_status, r.me_num, m.me_name, r.re_regdate, r.re_modifydate\r\n"
					+ "FROM reservation r JOIN book b ON b.bk_num = r.bk_num\r\n"
					+ "JOIN member m ON r.me_num = m.me_num ORDER BY r.re_num DESC";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//JDBC 4
			rs = pstmt.executeQuery();
			System.out.println("예약번호\t도서번호\t제목\t대출여부\t회원번호\t이름\t대출일\t\t반납일");
			System.out.println("------------------------------------------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getInt("re_num") + "\t");
				System.out.print(rs.getInt("bk_num") + "\t");
				System.out.print(rs.getString("bk_name") + "\t");
				System.out.print(rs.getInt("re_status") + "\t");
				System.out.print(rs.getInt("me_num") + "\t");
				System.out.print(rs.getString("me_name") + "\t");
				System.out.print(rs.getDate("re_regdate") + "\t");
				System.out.println(rs.getDate("re_modifydate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	
	//MY 대출 목록 보기 (현재 대출한 목록만 표시)
	//회원번호를 통해 re_status가 1인것만 불러오는것
	public void selectMyLoan(int me_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "SELECT r.re_num, b.bk_name, r.re_regdate FROM book b JOIN reservation r ON b.bk_num = r.bk_num"
					+ " AND r.re_status=1 AND r.me_num=? ORDER BY r.re_num DESC";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//? 바인딩
			pstmt.setInt(1, me_num);
			//JDBC 4
			rs = pstmt.executeQuery();
			if(rs.next()) { //행 있음
				System.out.println("예약번호\t도서명\t대출일");
				do {
					System.out.print(rs.getInt("re_num") + "\t");
					System.out.print(rs.getString("bk_name") + "\t");
					System.out.println(rs.getDate("re_regdate"));
				} while (rs.next());
			}else {//행 없음
				System.out.println("대출한 도서가 없습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	
	//반납 가능 여부
		//대출번호(re_num)와 회원번호(me_num)를 함께 조회해서 re_status가 1인 것은 반납 가능,0이면 불가
		public int returnCheck (int re_num, int me_num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int re_status = 0;
			try {
				//JDBC 1,2
				conn = DBUtil.getConnection();
				//SQL
				sql = "SELECT re_status FROM reservation WHERE re_num=? AND me_num=?";
				//JDBC 3
				pstmt = conn.prepareStatement(sql);
				//? 바인딩
				pstmt.setInt(1, re_num);
				pstmt.setInt(2, me_num);
				//JDBC 4
				rs = pstmt.executeQuery();
				if(rs.next()) {
					re_status = 1;	
				}else {
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}	
			return re_status;
		}
	
	//반납 처리
	//status를 0으로 바꾸는 update문
	public void returnBook(int re_num, int me_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2
			conn = DBUtil.getConnection();
			//SQL
			sql = "UPDATE reservation SET re_status=0, re_modifydate=SYSDATE WHERE re_num=? AND me_num=?";
			//JDBC 3
			pstmt = conn.prepareStatement(sql);
			//?
			pstmt.setInt(1, re_num);
			pstmt.setInt(2, me_num);
			//JDBC4
			int count = pstmt.executeUpdate();
			System.out.println(count + "권의 책이 반납 완료되었습니다.");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
