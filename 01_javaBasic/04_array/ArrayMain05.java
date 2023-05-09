package kr.s04.array;

public class ArrayMain05 {
	public static void main(String[] args) {
		//배열 선언, 생성, 초기화
		int array[] = {10,20,30,40,50};
		//int[] array 뿐만 아니라 int array[]도 가능. ([]의 위치)
		
		//배열의 요소 출력
		System.out.println(array[0]);
		System.out.println(array[1]);
		System.out.println(array[2]);
		System.out.println(array[3]);
		System.out.println(array[4]);
		System.out.println("------");
		
		//반복문을 이용한 배열의 요소 출력
		for(int i=0 ; i<array.length ; i++) {
			System.out.println("array[" + i + "]:" + array[i]);
		}
		System.out.println("------");
		
		//개선된 루프 (확장 for문) -> 배열에만 쓰이는 반복문
		//      요소    배열
		for(int num : array) {
			System.out.println(num);
		}
			
	}
}
