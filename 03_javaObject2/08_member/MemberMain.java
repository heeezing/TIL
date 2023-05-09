package kr.s08.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MemberMain {
	/*
	 * [실습]
	 * ArrayList에 Member객체를 저장하는 방식으로 회원 정보를 관리.
	 * 
	 * 메뉴: 1.회원정보입력 2.회원정보출력 3.종료
	 * 조건체크 : 회원정보를 입력받을 때 나이는 1살 이상 입력 가능.
	 */
	
	ArrayList<Member> list;
	BufferedReader br;
	
	public MemberMain() {
		list = new ArrayList<Member>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(br!=null) try {br.close();} catch (IOException e) {}
		}
	}
	
	//메뉴 선택
	public void callMenu() throws IOException {
		while(true) {
			try {
				System.out.print("1.회원정보입력 | 2.회원정보출력 | 3.종료 > ");
				int num = Integer.parseInt(br.readLine());
				if(num==1) {
					register();
				}else if(num==2) {
					printUserInfo();
				}else if(num==3) {
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력하세요.");
			}
		}
	}
	
	//회원 가입
	public void register() throws IOException {
		Member m = new Member();
		System.out.print("이름 : ");
		m.setName(br.readLine());
		
		m.setAge(parseInputData("나이 : "));
		
		System.out.print("직업 : ");
		m.setJob(br.readLine());;
		System.out.print("주소 : ");
		m.setAddress(br.readLine());
		System.out.print("전화번호 : ");
		m.setPhone(br.readLine());
		
		list.add(m);
	}
	
	//나이 조건 체크 메서드
	public int parseInputData(String data) throws IOException {
		while(true) {
			System.out.print(data);
			try {
				int age = Integer.parseInt(br.readLine());
				if(age<=0) {
					System.out.println("나이는 1살 이상 입력 가능");
					continue;
				}
				return age;
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력하세요.");
			}
		}
	}
	
	//회원 정보 출력
	public void printUserInfo() {
		System.out.println("총 회원 수(" + list.size() + "명)");
		System.out.println("이름\t나이\t직업\t주소\t전화번호");
		System.out.println("--------------------------------------");
		for(Member m : list) {
			System.out.print(m.getName() + "\t");
			System.out.print(m.getAge() + "\t");
			System.out.print(m.getJob() + "\t");
			System.out.print(m.getAddress() + "\t");
			System.out.print(m.getPhone() + "\n");

		}
	}
	
	public static void main(String[] args) {
		new MemberMain();
	}
}
