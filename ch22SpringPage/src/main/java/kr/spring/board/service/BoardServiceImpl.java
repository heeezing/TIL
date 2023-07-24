package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardMapper;
import kr.spring.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardMapper boardMapper;

	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		return boardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return boardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoard(BoardVO board) {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardVO selectBoard(Integer board_num) {
		return boardMapper.selectBoard(board_num);
	}

	@Override
	public void updateHit(Integer board_num) {
		boardMapper.updateHit(board_num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(Integer board_num) {
		//부모글 좋아요 삭제
		//댓글 존재 시 댓글 우선 삭제 후 부모글 삭제
		
		//부모글 삭제
		boardMapper.deleteBoard(board_num);
	}

}
