package kr.boardlevel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.boardlevel.vo.BoardLevelVO;
import kr.util.DBUtil;

public class BoardLevelDAO {
	//싱글턴 패턴
	private static BoardLevelDAO instance = new BoardLevelDAO();
	public static BoardLevelDAO getInstance() {
		return instance;
	}
	private BoardLevelDAO() {}
	
	//글등록
	public void insertBoard(BoardLevelVO board) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO zboardlevel (boardv_num,subject,content,parent_num,depth,ip,image,mem_num) "
				+ "VALUES (zboard_seq.nextval,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getParent_num());
			pstmt.setInt(4, board.getDepth());
			pstmt.setString(5, board.getIp());
			pstmt.setString(6, board.getImage());
			pstmt.setInt(7, board.getMem_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//전체글,검색글 개수
	//전체글,검색글 목록
	//글상세
	//조회수 증가
	//파일 삭제
	//글 수정
	//글 삭제
	//삭제할 글의 글번호 구함
}
