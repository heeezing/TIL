package kr.s02.operator;

public class OperatorMain05 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("국어:");
		//입력된 정수를 변수에 대입
		//실수를 입력 받고 싶으면 double korean = input.nextDouble();
		int korean = input.nextInt();
		
		System.out.print("영어:");
		int english = input.nextInt();
		
		System.out.print("수학:");
		int math = input.nextInt();
		
		int sum = korean + english + math;
		double avg = sum / 3.0;
		//그냥 3으로 나누면 둘 다 정수라 정수연산만 함.
		//(double)sum으로 만들거나 3.0으로 만들어야 함.
		
		System.out.printf("국어 = %d%n", korean);
		System.out.printf("영어 = %d%n", english);
		System.out.printf("수학 = %d%n", math);
		System.out.printf("총점 = %d%n", sum);
		System.out.printf("평균 = %.2f%n", avg);
		
		input.close(); // 더 이상 입력을 안 받겠다는 뜻
		
	}
}
