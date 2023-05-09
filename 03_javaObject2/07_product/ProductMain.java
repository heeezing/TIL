package kr.s07.product;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProductMain {
	ArrayList<Product> list;
	BufferedReader br;
	
	//생성자
	public ProductMain() {
		list = new ArrayList<Product>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(IOException e) {
			//예외문구를 콘솔에 출력
			e.printStackTrace();
		}finally {
			//자원 정리
			if(br!=null) try {br.close();} catch (IOException e) {} 
		}
	}
	
	//메뉴 
	public void callMenu() throws IOException { //readline에서 IOException 발생 가능
		while(true) {                           //IOException은 치명적인 예외라 루프 범위 안에 넣지 않고 throw함
			try {
				System.out.print("1.상품입력 | 2.상품목록보기 | 3.종료 > ");
				int num = Integer.parseInt(br.readLine()); //parseInt에서 NumberFormatException 발생 가능
				if(num==1) {
					//상품입력
					Product p = new Product();
					System.out.print("상품명 : ");
					p.setName(br.readLine());
					System.out.print("상품번호 : ");
					p.setNum(br.readLine());
					System.out.print("상품가격 : ");
					p.setPrice(Integer.parseInt(br.readLine()));
					System.out.print("제조사 : ");
					p.setMaker(br.readLine());
					System.out.print("재고 : ");
					p.setStock(Integer.parseInt(br.readLine()));
					
					//ArrayList에 Product를 저장
					list.add(p);
				}else if(num==2) {
					//상품 목록 보기
					System.out.println("상품 리스트 : 총 상품 수(" + list.size() + ")");
					System.out.println("상품명\t상품번호\t가격\t제조사\t재고수");
					System.out.println("-------------------------------");
					//확장 for문
					for(Product p : list) {
						System.out.printf("%s\t", p.getName());
						System.out.printf("%s\t", p.getNum());
						System.out.printf("%,d원\t", p.getPrice());
						System.out.printf("%s\t", p.getMaker());
						System.out.printf("%,d%n", p.getStock());
					}
					
				}else if(num==3) {
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력!");
			}
		}
	}	
	
	public static void main(String[] args) {
		new ProductMain();  //생성자 main
	}
}
