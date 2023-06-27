package kr.boardlevel.vo;

import java.sql.Date;

public class BoardLevelVO {
	private int boardv_num; //글번호
	private String subject; //제목
	private int readcount; //조회수
	private String content; //내용
	private Date reg_date; //작성일
	private Date modify_date; //수정일
	private int parent_num; //부모글 번호
	private int depth; //자식글의 깊이
	private String ip; //ip 주소
	private String image; //업로드 이미지
	private int mem_num; //작성자 회원번호
	
	public int getBoardv_num() {
		return boardv_num;
	}
	public void setBoardv_num(int boardv_num) {
		this.boardv_num = boardv_num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public int getParent_num() {
		return parent_num;
	}
	public void setParent_num(int parent_num) {
		this.parent_num = parent_num;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
}
