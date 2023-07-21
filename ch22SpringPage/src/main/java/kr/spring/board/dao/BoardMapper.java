package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	//[부모글]
	public List<BoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(BoardVO board);
	public BoardVO selectBoard(Integer board_num);
	public void updateHit(Integer board_num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(Integer board_num);
}
