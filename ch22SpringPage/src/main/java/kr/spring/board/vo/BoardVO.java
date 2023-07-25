package kr.spring.board.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private int board_num; //게시판 번호
	@NotEmpty
	private String title; //제목
	@NotEmpty
	private String content; //내용
	private int hit; //조회수
	private Date reg_date; //등록일
	private Date modify_date; //수정일
	private String ip; //ip주소
	private int mem_num; //회원 번호
	
	private String id; //아이디
	private String nick_name; //별명
	
	private int re_cnt; //댓글 개수
	private int fav_cnt; //좋아요 개수
}
