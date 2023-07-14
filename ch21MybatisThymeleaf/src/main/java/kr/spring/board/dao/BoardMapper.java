package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	public void insertBoard(BoardVO board);
	
	@Select("SELECT COUNT(*) FROM aboard") //sql문 길이가 짧은 경우 어노테이션으로 간편히 명시 가능!
	public int selectBoardCount();
	
	public List<BoardVO> selectBoardList(Map<String,Object> map);
	
	@Select("SELECT * FROM aboard WHERE num=#{num}")
	public BoardVO selectBoard(int num);
	
	@Update("UPDATE aboard SET writer=#{writer},title=#{title},content=#{content} WHERE num=#{num}")
	public void updateBoard(BoardVO board);
	
	@Delete("DELETE FROM aboard WHERE num=#{num}")
	public void deleteBoard(int num);
}
