package kr.s03.method;

public class MethodMain04 {
	/*
	 * [실습]
	 * 배열 요소 수(사람 수)를 입력 받아서 배열 weight 생성.
	 * 입력 받은 정수를 배열 weight에 저장하고
	 * 배열 weight가 가진 모든 요소의 수 중
	 * 최소값을 구하는 minOf 메서드를 작성하시오.
	 * 
	 * [출력 예시]
	 * 가장 마른 사람의 체중 : 24kg입니다.
	 */ 

	//멤버 메서드
	public int minOf(int[] a) {
		int min = a[0];
		for(int i=1 ; i<a.length ; i++) {
			if(min>a[i]) {
				min = a[i];
			}
		}
		return min;
	}
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("사람 수:");
		int num = input.nextInt();
		
		//체중을 저장하는 배열 생성
		int[] weight = new int[num];

		//체중을 입력 받아서 배열에 저장
		for(int i=0 ; i<weight.length ; i++) {
			System.out.print("weight[" + i + "]:");
			weight[i] = input.nextInt();
		}
		
		//객체 선언 및 생성
		MethodMain04 me = new MethodMain04();	
		int result = me.minOf(weight);
		
		System.out.println("가장 마른 사람의 체중 : " + result + "kg입니다.");
		
		input.close();
			
	}
}
