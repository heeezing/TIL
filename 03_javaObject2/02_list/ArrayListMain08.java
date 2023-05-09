package kr.s02.list;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class ArrayListMain08 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 로또 프로그램 제작
		 * 1~45 사이의 중복되지 않는 난수를 6개 생성
		 */
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		Random ra = new Random();
		
		while(list.size()<6) {
			int num = ra.nextInt(45) + 1;  //1~45 난수 생성
			//중복 값 제거
			if(!list.contains(num)) {
				//값이 중복되지 않으면 ArrayList에 저장
				list.add(num);
			}
		}
		//오름차순으로 정렬
		Collections.sort(list);
		
		//데이터 출력
		for(int num : list) {
			System.out.print(num + "\t");
		}
	}
}
