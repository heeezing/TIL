package kr.s04.array;

public class ArrayMain09 {
	                             //인자or파라미터
	public static void main(String[] args) {
		//두 개의 정수를 전달한 후 + 연산
		System.out.println(args[0] + args[1]);
		//10 20 을 입력한다 해도 String[] args이기 때문에 
		//외부에서 받은 값을 문자열로 인식. 결과:1020
		System.out.println("----------------");

		//String -> int 변환
		int num = Integer.parseInt(args[0]);
		int num2 = Integer.parseInt(args[1]);
		
		System.out.println("합계 : " + (num + num2));
	}
}
