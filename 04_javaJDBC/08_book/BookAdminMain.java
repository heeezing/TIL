package kr.s08.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BookAdminMain {
	private BufferedReader br;
	private BookDAO dao;
	
	public BookAdminMain() {
		try {
			dao = new BookDAO();
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null) try {br.close();} catch(IOException e) {}
		}
	}
	
	public void callMenu() throws IOException {
		while(true) {
			System.out.println("====================================================");
			System.out.print("1.도서등록 | 2.도서목록 | 3.대출목록 | 4.회원목록 | 5.종료 > ");
			try {
				int no = Integer.parseInt(br.readLine());
				System.out.println("====================================================");
				if(no==1) { //도서등록
					System.out.print("제목 : ");
					String bk_name = br.readLine();
					System.out.print("카테고리 : ");
					String bk_category = br.readLine();
					dao.registerBook(bk_name, bk_category);
				}else if(no==2) { //도서목록
					dao.selectBookList();
				}else if(no==3) { //대출목록
					dao.selectLoanList();
				}else if(no==4) { //회원목록
					dao.selectMember();
				}else if(no==5) { //종료
					System.out.println("프로그램을 종료합니다.");
					break;
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능합니다.");
			}
		}	
	}
	

	public static void main(String[] args) {
		new BookAdminMain();
	}
}
