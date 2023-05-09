package kr.s02.list;

import java.util.ArrayList;

public class ArrayListMain05 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(20);
		list.add(15);
		list.add(16);
		
		//요소의 목록 출력
		System.out.println(list);  //[10, 20, 15, 16]
		System.out.println("------------");
		
		//짝수를 삭제
		//index 0부터 시작해서 조건 체크를 하면서 데이터를 삭제할 경우,
		//조건 체크를 못 하고 건너뛰는 데이터가 발생할 수 있음. [20, 15]
		/*
		for(int i=0 ; i<list.size(); i++) {
			if(list.get(i) % 2 == 0) { //짝수
				list.remove(i);
			}
		}
		*/
		
		//모든 데이터를 조건 체크하려면
		//뒤에서 앞으로 이동하면서 조건 체크해야 함.
		for(int i=list.size()-1 ; i>=0 ; i--) {
			if(list.get(i) % 2 == 0) { //짝수
				list.remove(i);
			}
		}
		System.out.println(list); //[15]
	}
}
