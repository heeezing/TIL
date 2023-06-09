package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.jdi.request.ClassPrepareRequest;

import kr.board.vo.BoardFavVO;
import kr.board.vo.BoardReplyVO;
import kr.board.vo.BoardVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class BoardDAO {
	//싱글턴 패턴
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {}
	
	
	//글 등록
	public void insertBoard(BoardVO board) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO zboard (board_num,title,content,filename,ip,mem_num) "
				+ "VALUES (zboard_seq.nextval,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFilename());
			pstmt.setString(4, board.getIp());
			pstmt.setInt(5, board.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//총 레코드 수 (검색 레코드 수)
	public int getBoardCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//검색 시 추가할 sub_sql문 생성
			if(keyword!=null && !"".equals(keyword)) { //값이 온전히 있을 경우
				if(keyfield.equals("1")) sub_sql += "WHERE b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE b.content LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zboard b JOIN zmember m USING(mem_num) " + sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, "%" + keyword + "%");
			}
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //컬럼인덱스로 간단히 불러옴
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return count;
	}
	
	
	//글 목록 (검색글 목록)
	public List<BoardVO> getListBoard(int start, int end, String keyfield,
									  String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//검색 시 추가할 sub_sql문 생성
			if(keyword!=null && !"".equals(keyword)) { //값이 온전히 있을 경우
				if(keyfield.equals("1")) sub_sql += "WHERE b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE b.content LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT * FROM "
					+ "(SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM zboard b JOIN zmember m "
							+ "USING(mem_num) " + sub_sql + " ORDER BY b.board_num DESC)a) "
					+ "WHERE rnum>=? AND rnum<=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%" + keyword + "%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				board.setHit(rs.getInt("hit"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setId(rs.getString("id"));
				//자바빈을 ArrayList에 저장
				list.add(board);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	//글 상세
	public BoardVO getBoard(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성 (탈퇴시에도 누락되지 않도록 LEFT OUTER JOIN함)
			sql = "SELECT * FROM zboard b "
						+ "JOIN zmember m USING(mem_num) "
						+ "LEFT OUTER JOIN zmember_detail d USING(mem_num) "
				+ "WHERE b.board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setModify_date(rs.getDate("modify_date"));
				board.setFilename(rs.getString("filename"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setId(rs.getString("id"));
				board.setPhoto(rs.getString("photo"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return board;
	}
	
	
	//조회수 증가
	public void updateReadcount(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zboard SET hit=hit+1 WHERE board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//파일 정보 삭제
	public void deleteFile(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zboard SET filename='' WHERE board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//글 수정
	public void updateBoard(BoardVO board) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//조건 체크 - 파일을 업로드한 경우
			if(board.getFilename() != null) {
				sub_sql += ",filename=?";
			}
			//SQL문 작성
			sql = "UPDATE zboard SET title=?,content=?,modify_date=SYSDATE" 
					+ sub_sql + ",ip=? WHERE board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, board.getTitle());
			pstmt.setString(++cnt, board.getContent());
			if(board.getFilename() != null) {
				pstmt.setString(++cnt, board.getFilename());
			}
			pstmt.setString(++cnt, board.getIp());
			pstmt.setInt(++cnt, board.getBoard_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	

	//글 삭제
	public void deleteBoard(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//auto commit 해제
			conn.setAutoCommit(false);
			//좋아요 삭제 (좋아요 및 댓글을 삭제하지 않고 글 삭제 시 오류)
			sql = "DELETE FROM zboard_fav WHERE board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
			//댓글 삭제
			sql = "DELETE FROM zboard_reply WHERE board_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, board_num);
			pstmt2.executeUpdate();
			//부모글 삭제
			sql = "DELETE FROM zboard WHERE board_num=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, board_num);
			pstmt3.executeUpdate();
			
			//예외 발생 없이 정상적으로 SQL문 실행 시 커밋
			conn.commit();
		}catch(Exception e) {
			//SQL문이 하나라도 실패할 시 롤백
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	
	//좋아요 등록
	public void insertFav(BoardFavVO favVO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO zboard_fav (fav_num,board_num,mem_num) "
				+ "VALUES (zboardfav_seq.nextval,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, favVO.getBoard_num());
			pstmt.setInt(2, favVO.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//좋아요 개수
	public int selectFavCount(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zboard_fav WHERE board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //컬럼인덱스로 불러옴
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	
	//회원번호와 게시물번호를 이용한 좋아요 정보
	//(회원이 게시물을 호출할 때 좋아요 선택 여부 표시)
	public BoardFavVO selectFav(BoardFavVO favVO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardFavVO fav = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zboard_fav WHERE board_num=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, favVO.getBoard_num());
			pstmt.setInt(2, favVO.getMem_num());
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fav = new BoardFavVO();
				fav.setFav_num(rs.getInt("fav_num"));
				fav.setBoard_num(rs.getInt("board_num"));
				fav.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return fav;
	}
	
	
	//좋아요 삭제
	public void deleteFav(int fav_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM zboard_fav WHERE fav_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, fav_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//내가 선택한 좋아요 목록
	public List<BoardVO> getListBoardFav(int start, int end, int mem_num) 
														throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * "
				+ "FROM (SELECT a.*, rownum rnum "
						+ "FROM (SELECT * "
								+ "FROM zboard b JOIN zmember m USING(mem_num) JOIN zboard_fav f USING(board_num) "
								+ "WHERE f.mem_num=? ORDER BY board_num DESC)a) "
						+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				board.setReg_date(rs.getDate("reg_date"));
				board.setId(rs.getString("id"));
				
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	//댓글 등록
	public void insertReplyBoard(BoardReplyVO boardReply) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO zboard_reply (re_num,re_content,re_ip,mem_num,board_num) "
				+ "VALUES (zreply_seq.nextval,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, boardReply.getRe_content());
			pstmt.setString(2, boardReply.getRe_ip());
			pstmt.setInt(3, boardReply.getMem_num());
			pstmt.setInt(4, boardReply.getBoard_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//댓글 개수
	public int getReplyBoardCount(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zboard_reply b JOIN zmember m "
				+ "ON b.mem_num = m.mem_num WHERE b.board_num = ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //컬럼인덱스로 불러옴
			}
		}catch(Exception e) { 
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	
	//댓글 목록
	public List<BoardReplyVO> getListReplyBoard(int start, int end, int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardReplyVO> list = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * "
				+ "FROM (SELECT a.*, rownum rnum "
						+ "FROM (SELECT * "
								+ "FROM zboard_reply b JOIN zmember m USING(mem_num) "
								+ "WHERE b.board_num=? ORDER BY b.re_num DESC)a) "
						+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardReplyVO>();
			while(rs.next()) {
				BoardReplyVO reply = new BoardReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				//날짜 -> 1분 전, 1시간 전, 1일 전 형식의 문자열로 변환
				reply.setRe_date(DurationFromNow.getTimeDiffLabel(rs.getString("re_date")));
				if(rs.getString("re_modifydate") != null) {
					reply.setRe_modifydate(DurationFromNow.getTimeDiffLabel(rs.getString("re_modifyDate")));
				}
				reply.setRe_content(StringUtil.useBrNoHtml(rs.getString("re_content")));
				reply.setBoard_num(rs.getInt("board_num"));
				reply.setMem_num(rs.getInt("mem_num"));
				reply.setId(rs.getString("id"));
				
				list.add(reply);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	//댓글 상세 (댓글 수정/삭제 시 작성자 회원번호 체크 용도)
	public BoardReplyVO getReplyBoard(int re_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardReplyVO reply = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zboard_reply WHERE re_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, re_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = new BoardReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				reply.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return reply;
	}
	
	
	//댓글 수정
	public void updateReplyBoard(BoardReplyVO reply) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zboard_reply SET re_content=?,re_modifydate=SYSDATE,re_ip=? WHERE re_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, reply.getRe_content());
			pstmt.setString(2, reply.getRe_ip());
			pstmt.setInt(3, reply.getRe_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//댓글 삭제
	public void deleteReplyBoard(int re_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM zboard_reply WHERE re_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, re_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}
