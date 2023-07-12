package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;

public interface BoardService {
	public void insertBoard(BoardVO board);
	public int selectBoardCount();
	//mybatis - 하나의 인자만 가능. 따라서 인자를 여러개 보내야할 땐 Map형태로 묶어서 보내야함.
	public List<BoardVO> selectBoardList(Map<String,Object> map);
	public BoardVO selectBoard(int num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int num);
}
