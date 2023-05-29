package kr.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.product.vo.BoardVO;
import kr.util.DBUtil;

public class BoardDAO {
	//싱글턴 패턴
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {}
	
	
	//상품 등록
	public void insert(BoardVO boardVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션 할당받음
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO mproduct (num,name,passwd,price,stock,origin,reg_date) "
					+ "VALUES (mproduct_seq.nextval,?,?,?,?,?,SYSDATE)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, boardVO.getName());
			pstmt.setString(2, boardVO.getPasswd());
			pstmt.setInt(3, boardVO.getPrice());
			pstmt.setInt(4, boardVO.getStock());
			pstmt.setString(5, boardVO.getOrigin());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//상품 목록
	public List<BoardVO> getList(int startRow, int endRow)
										throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * "
				+ "FROM (SELECT a.*, rownum rnum "
						+ "FROM (SELECT * FROM mproduct ORDER BY num DESC)a) "
				+ "WHERE rnum>=? AND rnum<=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(rs.getInt("num"));
				boardVO.setName(rs.getString("name"));
				boardVO.setStock(rs.getInt("stock"));
				boardVO.setReg_date(rs.getDate("reg_date"));
				//자바빈을 ArrayList에 저장
				list.add(boardVO);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	//상품 상세 정보
	public BoardVO getBoard(int num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM mproduct WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setPasswd(rs.getString("passwd"));
				board.setPrice(rs.getInt("price"));
				board.setStock(rs.getInt("stock"));
				board.setOrigin(rs.getString("origin"));
				board.setReg_date(rs.getDate("reg_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return board;
	}
	
	
	//상품 정보 수정
	 public void update(BoardVO boardVO) throws Exception{
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 String sql = null;
		 
		 try {
				//커넥션 풀로부터 커넥션을 할당받음
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE mproduct SET name=?,price=?,stock=?,origin=? WHERE num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setString(1, boardVO.getName());
				pstmt.setInt(2, boardVO.getPrice());
				pstmt.setInt(3, boardVO.getStock());
				pstmt.setString(4, boardVO.getOrigin());
				pstmt.setInt(5, boardVO.getNum());
				//SQL문 실행
				pstmt.executeUpdate();
		 }catch(Exception e) {
			 throw new Exception(e);
		 }finally {
			 DBUtil.executeClose(null, pstmt, conn);
		 }
	 }
	 
	 
	//상품 정보 삭제
	public void delete(int num) throws Exception {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 String sql = null;
		 
		 try {
				//커넥션 풀로부터 커넥션을 할당받음
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM mproduct WHERE num=?";
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
	 
	 
	//상품 정보 총 갯수
	public int getCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM mproduct";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//컬럼 인덱스로 호출. COUNT(*)라고 쓰거나 알리아스 부여해서 써도 됨.
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	} 
 
}
