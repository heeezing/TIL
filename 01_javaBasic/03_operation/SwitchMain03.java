package kr.s03.operation;

public class SwitchMain03 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int score;
		char grade;
		
		System.out.print("성적 입력>");
		score = input.nextInt();
		
		if(score<0 || score>100) {
			System.out.println("성적은 0~100만 입력 가능");
			System.exit(0);
			//프로그램 종료 - 하지 않으면 음수,100이상 숫자도 switch문을 통과->F나옴
		}
		
		switch (score / 10) {
		case 10: case 9:
			grade = 'A';
			break;
		case 8:
			grade = 'B';
			break;
		case 7 :
			grade = 'C';
			break;
		case 6 :
			grade = 'D';
			break;
		default :
			grade = 'F';
		}
		
		System.out.println(); //단순 줄바꿈
		System.out.printf("성적 : %d%n", score);
		System.out.printf("등급 : %c%n", grade);
		
		input.close();
	}
}
