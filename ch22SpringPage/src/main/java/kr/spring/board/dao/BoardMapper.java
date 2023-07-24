package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	//[부모글]
	public List<BoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoard(BoardVO board);
	public BoardVO selectBoard(Integer board_num);
	
	@Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}")
	public void updateHit(Integer board_num);
	
	@Update("UPDATE spboard "
		  + "SET title=#{title},content=#{content},ip=#{ip},modify_date=SYSDATE "
		  + "WHERE board_num=#{board_num}")
	public void updateBoard(BoardVO board);
	
	@Delete("DELETE FROM spboard WHERE board_num=#{board_num}")
	public void deleteBoard(Integer board_num);
}
