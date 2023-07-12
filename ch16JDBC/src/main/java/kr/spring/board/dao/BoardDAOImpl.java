package kr.spring.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository  //자동 스캔 대상이 된다.
public class BoardDAOImpl implements BoardDAO{
	
	//sql문 - 주로 상수로 정의해서 사용 (메서드 인자로 sql문을 간편하게 넣기 위해)
	private static final String INSERT_SQL 
		= "INSERT INTO aboard (num,writer,title,passwd,content,reg_date) "
		+ "VALUES (aboard_seq.nextval,?,?,?,?,SYSDATE)";
	
	private static final String SELECT_COUNT_SQL 
		= "SELECT COUNT(*) FROM aboard";
	
	private static final String SELECT_LIST_SQL
		= "SELECT * "
		+ "FROM (SELECT a.*, rownum rnum "
			   + "FROM (SELECT * FROM aboard ORDER BY reg_date DESC)a) "
		+ "WHERE rnum>=? AND rnum<=?";
	
	private static final String SELECT_DETAIL_SQL
		= "SELECT * FROM aboard WHERE num=?";
	
	private static final String UPDATE_SQL
	 	= "UPDATE aboard SET writer=?,title=?,content=? WHERE num=?"; 
	
	private static final String DELETE_SQL
		= "DELETE FROM aboard WHERE num=?";
	
	//익명 객체를 만들어 mapRow 정의
	//생성자 뒤에 중괄호가 있다 -> 익명객체(객체 안에 또 다른 객체를 만듬)
	private RowMapper<BoardVO> rowMapper = new RowMapper<BoardVO>() {
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
			BoardVO board = new BoardVO();
			board.setNum(rs.getInt("num"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setPasswd(rs.getString("passwd"));
			board.setContent(rs.getString("content"));
			board.setReg_date(rs.getDate("reg_date"));
			return board;
		}
	};
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertBoard(BoardVO board) {
		//update,delete,insert는 update메서드 사용
		//update(인자1,인자2) -> 인자1:sql문, 인자2:object 배열로 ?에 데이터바인딩
		jdbcTemplate.update(INSERT_SQL, new Object[] {board.getWriter(),
													  board.getTitle(),
													  board.getPasswd(),
													  board.getContent()});
	}

	@Override
	public int getBoardCount() {
		//한 건의 데이터 반환					   //sql문 			반환타입
		return jdbcTemplate.queryForObject(SELECT_COUNT_SQL, Integer.class);
	}

	@Override
	public List<BoardVO> getBoardList(int startRow, int endRow) { 
		//ResultSet에서 받은 정보로 rowMapper가 자바빈을 만들고 query를 통해 그 데이터(자바빈)를 List에 넣어줌
		List<BoardVO> list = jdbcTemplate.query(SELECT_LIST_SQL, new Object[] {startRow, endRow}, rowMapper);
		return list;
	}

	@Override
	public BoardVO getBoard(int num) {		//   sql문		    ?에 전달할 데이터	  rowmapper
		return jdbcTemplate.queryForObject(SELECT_DETAIL_SQL, new Object[] {num}, rowMapper);
	}

	@Override
	public void updateBoard(BoardVO board) {
		jdbcTemplate.update(UPDATE_SQL, new Object[] {board.getWriter(),
													  board.getTitle(),
													  board.getContent(),
													  board.getNum()});
	}

	@Override
	public void deleteBoard(int num) {
		jdbcTemplate.update(DELETE_SQL, new Object[] {num});
	}

}
