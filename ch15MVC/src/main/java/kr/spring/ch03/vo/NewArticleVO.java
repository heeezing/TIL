package kr.spring.ch03.vo;

public class NewArticleVO {
	private String title;
	private String content;
	private int parent_id;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	@Override
	public String toString() {
		return "NewArticleVO [title=" + title + ", content=" + content + ", parent_id=" + parent_id + "]";
	}
	
}
