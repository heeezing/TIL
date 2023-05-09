package kr.s09.score;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ScoreMain {
	/*
	 * [실습]
	 * ArrayList에 Score 저장하는 프로그램
	 * 메뉴 :  1.성적입력 2.성적출력 3.종료
	 * 조건 체크 : 성적 유효 범위 체크 (0 ~ 100의 값만 인정)
	 */
	ArrayList<Score> list;
	BufferedReader br;
	
	public ScoreMain() {
		list = new ArrayList<Score>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callmenu();
		}catch(IOException e) {
			e.printStackTrace(); //예외문구
		}catch(Exception e) {
			e.printStackTrace(); //예외문구
		}finally {
			if(br!=null) try {br.close();} catch(IOException e) {} //자원정리
		}
	}
	//메뉴
	public void callmenu() throws IOException{
		while(true) {
			try {
				System.out.print("1.성적입력 | 2.성적출력 | 3.종료 > ");
				int num = Integer.parseInt(br.readLine());
				if(num==1) {
					inputScore();
				}else if(num==2) {
					printScore();
				}else if(num==3) {
					System.out.println("프로그램 종료");
					break;
				}else {  //1,2,3 외의 숫자 입력 시
					System.out.println("잘못 입력하셨습니다.");
				}
			}catch(NumberFormatException e) {  //문자 입력 시
				System.out.println("숫자만 입력할 수 있습니다.");
			}
		}
	}
	//성적 입력
	public void inputScore() throws IOException {
		Score s = new Score();
		System.out.print("이름 : ");
		s.setName(br.readLine());
		s.setKorean(parseInputData("국어 : "));
		s.setEnglish(parseInputData("영어 : "));
		s.setMath(parseInputData("수학 : "));
		list.add(s);
	}
	
	//성적 출력
	public void printScore() {
		System.out.println("학생 수 : " + list.size() + "명");
		System.out.println("--------------------------------------");
		System.out.println("이름\t국어\t영어\t수학\t총합\t평균\t등급");
		System.out.println("--------------------------------------");
		for(Score s : list) {
			System.out.print(s.getName() + "\t");
			System.out.print(s.getKorean() + "\t");
			System.out.print(s.getEnglish() + "\t");
			System.out.print(s.getMath() + "\t");
			System.out.print(s.makeSum() + "\t");
			System.out.printf("%.2f\t", s.makeAvg());
			System.out.print(s.makeGrade() + "\n");
		}
	}
	
	//성적 입력 조건 체크
	public int parseInputData(String course) throws IOException {
		while(true) {
			System.out.print(course); //과목 표시
			try {
				int num = Integer.parseInt(br.readLine());
				//성적 유효 범위(0 ~ 100)
				if(num<0 || num>100) {
				throw new ScoreValueException("0~100사이의 값만 인정");
				}
				return num;
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능");
			}catch(ScoreValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		new ScoreMain();
	}
}
