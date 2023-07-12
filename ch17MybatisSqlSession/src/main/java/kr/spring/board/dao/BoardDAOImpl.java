package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository //컨테이너 보관 + 자동 스캔 대상
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	//jdbcTemplate은 select 제외하고는 모두 update메서드 였으나, mybatis는 다 각각 있음.

	@Override
	public void insertBoard(BoardVO board) {
		//[인자1]xml파일에서 sql문장을 갖고있는(식별하는) 태그의 id, [인자2]데이터(자바빈)
		sqlSession.insert("insertBoard",board);
	}

	@Override
	public int selectBoardCount() {
		//selectOne() : 한 건의 데이터를 가져옴
		return sqlSession.selectOne("selectBoardCount");
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Object> map) {
		//selectList() : 데이터 목록을 가져옴
		return sqlSession.selectList("selectBoardList", map);
	}

	@Override
	public BoardVO selectBoard(int num) {
		return sqlSession.selectOne("selectBoard", num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		sqlSession.update("updateBoard", board);
	}

	@Override
	public void deleteBoard(int num) {
		// TODO Auto-generated method stub
		
	}

}
