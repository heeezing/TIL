package kr.s04.array;

public class ArraySecondMain03 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		//과목명 
		String[] course = {"국어","영어","수학"};
		//인원수
		int num = 2;
		//점수가 저장될 2차원 배열
		//                     인원수      과목수
		int[][] score = new int[num][course.length];
		//총점
		int[] sum = new int[num];
		//평균
		float[] avg = new float[num];
		
		//성적을 입력 받고 총점과 평균 구하기
		//                행의 길이
		for(int i=0 ; i<score.length ; i++) { //i:행 번호
		//                      열의 길이
			for(int j=0 ; j<score[i].length ; j++) { //j:열 번호
				//성적을 입력 받음
				do {
					//과목명 출력
					System.out.print(course[j] + "=");
					score[i][j] = input.nextInt();
				}while(score[i][j]<0 || score[i][j]>100);
				//총점 구하기(누적)
				sum[i] += score[i][j];
			}
			//평균 구하기
			
			//        총점         과목 수
			avg[i] = sum[i] / (float)score[i].length;
			System.out.println(); //줄바꿈
		}
		
		//총점과 평균 출력
		for(int i=0 ; i<num ; i++) {
			System.out.println(); //줄바꿈
			System.out.printf("총점 = %d%n", sum[i]);
			System.out.printf("평균 = %.2f%n", avg[i]);
		}
		
		input.close();
	}
}
