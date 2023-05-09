package kr.s20.util;

import java.util.Random;
import java.util.Arrays;

public class LottoArray {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 로또 프로그램 제작
		 * 배열을 생성하고 
		 * 1 ~ 45 중 중복되지 않은 6개의 숫자를 생성해서 배열에 저장
		 * 난수 발생 : Math.random(), Random클래스 중 택 1
		 */
		//로또 번호를 저장할 배열
		int[] lotto = new int[6];
		
		Random random = new Random();

		for(int i=0 ; i<lotto.length ; i++) {			
			lotto[i] = random.nextInt(45) + 1; //1번
			
			//중복된 숫자가 있는지 검증
			for(int j=0 ; j<i ; j++) {
				if(lotto[i] == lotto[j]) {
					//1번에서 만들어진 숫자를 인덱스 0부터 i전까지 루프를 돌면서 대조
					i--;  //or i -= 1; //중복되면 1번에서 만든 숫자를 불허.
					      //i를 하나 줄어들게 함으로써 루프 돌 때 새 숫자로 덮어씌움.
					break;
				}
			}
		}
		//오름차순(작은 수가 앞, 큰 수가 뒤)으로 정렬
		Arrays.sort(lotto);  //static한 메서드라 객체 생성 안 해도 됨.
		//배열의 요소 출력
		for(int num : lotto) {
			System.out.print(num + "\t");
		}
	}
}
