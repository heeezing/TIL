package kr.spring.ch11.vo;

public class PageRank {
	private int rank;
	private String page;
	
	//디폴트 생성자
	public PageRank() {}
	//인자가 있는 생성자
	public PageRank(int rank, String page) {
		this.rank = rank;
		this.page = page;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
