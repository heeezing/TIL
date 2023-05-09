package kr.s11.array;

import java.util.Scanner;

public class ScoreMain {
	/*
	 * [실습]
	 * 1. 배열 생성(길이 4)
	 * 2. Score 객체를 생성하고 초기화
	 * 3. 객체 저장된 정보를 출력
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		//배열 선언 및 생성
		Score[] scoreArray = new Score[4];
		
		/* 하드 코딩 시
		scoreArray[0] = new Score("홍길동", 90, 92, 94);
		scoreArray[1] = new Score("이순신", 86, 89, 97);
		scoreArray[2] = new Score("장영실", 77, 98, 82);
		scoreArray[3] = new Score("김유신", 70, 99, 85);
		*/
		
		//Score 객체를 4개 생성해서 배열에 저장
		//각 4명의 성적 정보를 입력 받아서 객체에 저장하고 객체를 배열에 저장함
		for(int i=0 ; i<scoreArray.length; i++) {
			System.out.print("이름:");
			String name = input.nextLine();
			System.out.print("국어:");
			int korean = input.nextInt();
			System.out.print("영어:");
			int english  = input.nextInt();
			System.out.print("수학:");
			int math  = input.nextInt();
			input.nextLine(); //enter 흡수
			scoreArray[i] = new Score(name,korean,english,math);
			System.out.println("-------------------");
		}	
		
		for(Score s : scoreArray) {
			System.out.printf("%s\t", s.getName());
			System.out.printf("%d점\t", s.getKorean());
			System.out.printf("%d점\t", s.getEnglish());
			System.out.printf("%d점\t", s.getMath());
			System.out.printf("총점:%d점\t", s.makeSum());
			System.out.printf("평균:%d점\t", s.makeAvg());
			System.out.printf("등급:%s\t", s.makeGrade());
			System.out.println();
		}
		
		input.close();
	}
}
