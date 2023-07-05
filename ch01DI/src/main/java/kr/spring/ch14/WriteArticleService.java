package kr.spring.ch14;

public class WriteArticleService {
	//프로퍼티
	private WriteArticleDAO writeArticleDAO;

	public void setWriteArticleDAO(WriteArticleDAO writeArticleDAO) {
		this.writeArticleDAO = writeArticleDAO;
	}
	
	public void write() {
		System.out.println("WriteArticleService의 write() 메서드 호출");
		writeArticleDAO.insert();
	}
}
