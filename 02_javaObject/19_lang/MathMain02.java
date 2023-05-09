package kr.s19.lang;

import java.util.Random;

public class MathMain02 {
	public static void main(String[] args) {
		String[] gift = {"스마트폰", "TV", "냉장고", "꽝"};
		//                   0       1      2      3		
		//0.0 ~ 1.0 미만의 난수 발생
		double ran = Math.random();
		System.out.println("발생한 난수 : " + ran); //ex)발생한 난수 : 0.6494965778781141		
		//0 ~ 3 범위의 난수로 가공
		int index = (int)(ran*4);
		System.out.println("발생한 난수 : " + index);
		System.out.println("오늘의 선물 : " + gift[index]);
		
		System.out.println("---------------");
		
		String[] luck = {"귀인을 만남", "로또 당첨", "저렴한 상품 득템", "피곤해~~"};
		//                    0           1            2             3
		Random r1 = new Random(); // 객체 생성		
		//0부터 인자로 전달된 값의 "전"까지를 범위로 난수 발생. 0~3
		index = r1.nextInt(4);
		System.out.println("발생한 난수 : " + index);
		System.out.println("오늘의 운세 : " + luck[index]);
	}
}
