package kr.s08.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BookUserMain {
	private BufferedReader br;
	private BookDAO dao;
	private int me_num; //회원 번호
	private boolean flag; //로그인 여부(로그인true, 미로그인false)
	
	public BookUserMain() {
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
			System.out.println("==============================");
			System.out.print("1.로그인 | 2.회원가입 | 3.종료 > ");
			try {
				int no = Integer.parseInt(br.readLine());
				System.out.println("==============================");
				if(no==1) { //로그인
					System.out.print("아이디 : ");
					String me_id = br.readLine();
					System.out.print("비밀번호 : ");
					String me_passwd = br.readLine();
					me_num = dao.loginCheck(me_id, me_passwd);
					if(me_num > 0) {
						System.out.println(me_id + "님 로그인 되었습니다.");
						flag = true;
						break; //while문 빠져나감
					}
					System.out.println("아이디 또는 비밀번호가 불일치합니다.");
				}else if(no==2) { //회원가입
					while(true) {
						System.out.print("아이디 : ");
						String me_id = br.readLine();
						if(dao.checkId(me_id)==0) {
							System.out.print("비밀번호 : ");
							String me_passwd = br.readLine();
							System.out.print("이름 : ");
							String me_name = br.readLine();
							System.out.print("전화번호 : ");
							String me_phone = br.readLine();
							dao.registerMember(me_id, me_passwd, me_name, me_phone);
							break;
						}else {
						System.out.println("이미 등록되어 있는 아이디입니다.");
						}
					}
				}else if(no==3) { //종료
					System.out.println("프로그램을 종료합니다.");
					break;
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능합니다.");
			}
		}//end of while
		
		//로그인 했을 때의 메뉴
		while(flag) {
			System.out.println("===============================================");
			System.out.print("1.도서대출 | 2.MY대출목록 | 3.대출도서반납 | 4.종료 > ");
			try{
				int no = Integer.parseInt(br.readLine());
				System.out.println("===============================================");
				if(no==1) { //도서대출
					//도서목록
					dao.selectBookList();
					System.out.println("-------------------------------------");
					//대출가능여부
					System.out.print("대출할 책의 도서 번호 > ");
					int bk_num = Integer.parseInt(br.readLine());
					if(dao.loanCheck(bk_num)==0) {
						//도서대출
						dao.loanBook(bk_num, me_num);
						System.out.println("대출이 완료되었습니다.");
					}else if(dao.loanCheck(bk_num)==1){
						System.out.println("이미 대출 중인 책입니다.");
					}else {
						System.out.println("없는 도서 번호입니다.");
					}
				}else if(no==2) { //MY대출목록
					//MY대출목록
					dao.selectMyLoan(me_num);
				}else if(no==3) {	//반납가능여부
					//MY대출목록
					dao.selectMyLoan(me_num);
					System.out.println("-----------------------------------");
					System.out.print("반납할 책의 예약 번호 > ");
					int re_num = Integer.parseInt(br.readLine());
					if(dao.returnCheck(re_num, me_num) == 1) {
						//도서 반납
						dao.returnBook(re_num, me_num);
					}else {
						System.out.println("없는 예약 번호입니다.");
					}
				}else if(no==4) { //종료
					System.out.println("프로그램을 종료합니다.");
					break;
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}			
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능합니다.");
			}
		}
		
	}//end of callMenu
	
	
	public static void main(String[] args) {
		new BookUserMain();
	}
}
