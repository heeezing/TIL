package kr.s16.trainning;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MemberMain {
	/*
	 * [실습]
	 * ArrayList에 Member 객체를 저장
	 * 메뉴 : 회원정보입력, 회원정보출력, 파일생성, 파일읽기, 종료
	 * 조건 체크 : 나이는 1살 이상 입력 가능
	 */
	
	//멤버 변수
	ArrayList<Member> list;
	BufferedReader br;
	
	//생성자
	public MemberMain () {
			list = new ArrayList<Member>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));	
			callmenu();			
		}catch(IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null) try {br.close();} catch(IOException e) {}
		}
	}
	
	//메뉴 생성
	public void callmenu() throws IOException {
		while(true) {
			System.out.print("1.회원정보입력 | 2.회원정보출력 | 3.파일생성 | 4.파일읽기 | 5.종료 > ");
			try {
				int num = Integer.parseInt(br.readLine());
				if(num==1) {
					register();
				}else if(num==2) {
					printUserInfo();
				}else if(num==3) {
					createFile();
				}else if(num==4) {
					readFile();
				}else if(num==5) {
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력할 수 있습니다.");
			}
		}
	}

	//회원정보입력
		public void register() throws IOException {
			Member m = new Member();             //인자가 있는 생성자 이용하고 싶을 땐
			System.out.print("이름 : ");          //System.out.print("이름 : ");
			m.setName(br.readLine());            //String name = br.readLine();
			m.setAge(parseInputData("나이 : "));	 //int age = parseInputData("나이 : ")	;
			System.out.print("직업 : ");          //System.out.print("직업 : ");
			m.setJob(br.readLine());             //String job = br.readLine();
			System.out.print("주소 : ");          //System.out.print("주소 : ");
			m.setAddress(br.readLine());         //String address = br.readLine();
			System.out.print("휴대폰 : ");         //System.out.print("휴대폰 : "); 
			m.setPhone(br.readLine());           //String phone = br.readLine();
			System.out.print("취미 : ");          //System.out.print("취미 : ");
			m.setHobby(br.readLine());           //String hobby = br.readLine();
			                                     //Member m = new Member(name,age,job,address,phone,hobby);
			list.add(m);                         //list.add(m);
		}
		
		
	//나이조건체크
		public int parseInputData(String str) throws IOException {
			while(true) {
				System.out.print(str);
				try {
					int age = Integer.parseInt(br.readLine());
					if(age<=0) {
						System.out.println("나이는 1살부터 입력 가능");
						continue;
					} return age;
				}catch(NumberFormatException e) {
					System.out.println("숫자만 입력 가능");
				}
			}
		}	
		
	//회원정보출력
		public void printUserInfo() {
			System.out.println("총 회원 수(" + list.size() + "명)");
			System.out.println("----------------------------");
			System.out.println("이름\t나이\t직업\t주소\t휴대폰\t취미");
			System.out.println("----------------------------");
			for(Member m : list) {
//				System.out.print(m.getName() + "\t");
//				System.out.print(m.getAge() + "\t");
//				System.out.print(m.getJob() + "\t");
//				System.out.print(m.getAddress() + "\t");
//				System.out.print(m.getPhone() + "\t");
//				System.out.print(m.getHobby() + "\n");
				System.out.print(m.toString());
			}
		}
	
	//파일 생성
		public void createFile() {
			FileWriter fw = null;
			try {
				//덮어쓰기
				fw = new FileWriter("member.txt");
				fw.write("총 회원 수(" + list.size() + "명)\n");
				fw.write("-------------------------------\n");
				fw.write("이름\t나이\t직업\t주소\t휴대폰\t취미\n");
				fw.write("-------------------------------\n");
				
				for(Member m : list) {
//					fw.write(m.getName() + "\t");
//					fw.write(m.getAge() + "\t");
//					fw.write(m.getJob() + "\t");
//					fw.write(m.getAddress() + "\t");
//					fw.write(m.getPhone() + "\t");
//					fw.write(m.getHobby() + "\n");
					fw.write(m.toString());
				}
				fw.flush();
				System.out.println("파일에 회원정보를 저장했습니다.");
			}catch(IOException e) {
				System.out.println("파일 생성 오류");
			}finally {
				if(fw!=null) try {fw.close();} catch(IOException e) {}
			}
		}

	//파일 읽기
		public void readFile() {
			FileReader fr = null;
			int readChar;
			try {
				fr = new FileReader("member.txt");
				while((readChar = fr.read())!=-1) {
					System.out.print((char)readChar);
				}
			}catch(FileNotFoundException e) {
				System.out.println("생성된 파일이 없습니다.");
			}catch(IOException e) {
				System.out.println("파일 읽기 오류");
			}finally {
				if (fr!=null) try{fr.close();} catch(IOException e) {}
			}
		}

	public static void main(String[] args) {
		new MemberMain();
	}
}
