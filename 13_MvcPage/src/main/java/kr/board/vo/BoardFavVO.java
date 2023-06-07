package kr.board.vo;

public class BoardFavVO {
	private int fav_num; //좋아요 번호
	private int board_num; //게시글 번호
	private int mem_num; //회원 번호
	
	//디폴트 생성자
	public BoardFavVO() {}
	
	//생성자 정의
	public BoardFavVO(int board_num, int mem_num) {
		this.board_num = board_num;
		this.mem_num = mem_num;
	}
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
}
