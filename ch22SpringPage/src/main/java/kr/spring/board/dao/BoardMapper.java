package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	/*----------[부모글]----------*/
	
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
	
	
	/*----------[좋아요]----------*/
	
	@Select("SELECT * FROM spboard_fav WHERE board_num=#{board_num} AND mem_num=#{mem_num}")
	public BoardFavVO selectFav(BoardFavVO fav);
	
	@Select("SELECT COUNT(*) FROM spboard_fav WHERE board_num=#{board_num}")
	public int selectFavCount(Integer board_num);
	
	@Insert("INSERT INTO spboard_fav (fav_num,board_num,mem_num) "
		  + "VALUES (refav_seq.nextval,#{board_num},#{mem_num})")
	public void insertFav(BoardFavVO fav);
	
	@Delete("DELETE FROM spboard_fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	
	//부모글 삭제 시 좋아요가 존재하면, 부모글이 삭제되기 전 좋아요를 먼저 삭제
	@Delete("DELETE FROM spboard_fav WHERE board_num=#{board_num}")
	public void deleteFavByBoardNum(Integer board_num); 
	
	
	/*----------[댓글]----------*/
	
	public List<BoardReplyVO> selectListReply(Map<String,Object> map);
	
	@Select("SELECT COUNT(*) FROM spboard_reply JOIN spmember USING (mem_num) WHERE board_num=#{board_num}")
	public int selectRowCountReply(Map<String,Object> map);
	
	@Select("SELECT * FROM spboard_reply WHERE re_num=#{re_num}")
	public BoardReplyVO selectReply (Integer re_num);
	
	public void insertReply(BoardReplyVO boardReply);
	
	@Update("UPDATE spboard_reply SET re_content=#{re_content},re_ip=#{re_ip},re_mdate=SYSDATE WHERE re_num=#{re_num}")
	public void updateReply(BoardReplyVO boardReply);
	
	@Delete("DELETE FROM spboard_reply WHERE re_num=#{re_num}")
	public void deleteReply(Integer re_num);
	
	//부모글 삭제 시 댓글이 존재하면, 부모글이 삭제되기 전 댓글을 먼저 삭제
	public void deleteReplyByBoardNum(Integer board_num);
	
}
