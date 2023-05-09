package kr.s02.list;

import java.util.ArrayList;

public class ArrayListMain04 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("강호동"); //0
		list.add("유재석"); //1
		list.add("신동엽"); //2
		list.add("박나래"); //3
		list.add("강호동"); //4
		
		//반복문을 이용한 요소 출력
		for(int i=0 ; i<list.size() ; i++) {
			String name = list.get(i);
			System.out.println(i + ":" + name);
		}
		System.out.println("-------");
		
		//요소의 삭제
		//list.remove(2); //삭제하고싶은 데이터의 인덱스 입력
		list.remove("강호동"); //삭제하고싶은 데이터의 요소 입력 (중복의 경우 가장 위에 있는 것이 지워짐)
		
		for(int i=0 ; i<list.size() ; i++) {       //0:유재석
			String name = list.get(i);             //1:신동엽
			System.out.println(i + ":" + name);    //2:박나래
		}                                          //3:강호동
		System.out.println("-------");
		
		/*
		 * list.remove(2); 했을 시 출력
		 * 0:강호동          
		 * 1:유재석            
		 * 2:박나래           
		 * 3:강호동            
		 * 
		 */
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(40);
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list2.add(40);
		
		//반복문을 이용한 요소 출력
		for(int i=0 ; i<list2.size() ; i++) {
			//Integer라고 쓰는게 원칙이지만 int로 써도
			//참조자료형->기본자료형 변환 되어 오류가 발생하지 않음.
			//(auto unboxing)
			int num = list2.get(i);
			System.out.println(i + ":" + num);
		}
		System.out.println("-------");
		
		//요소의 삭제
		//list2.remove(2); //인덱스
		list2.remove((Integer)40);  //그냥 40을 입력하면 인덱스40으로 받아들여 오류.
		
		for(int i=0 ; i<list2.size() ; i++) {    //0:1
			int num = list2.get(i);              //1:2
			System.out.println(i + ":" + num);   //2:3
		}                                        //3:40
		System.out.println("-------");
		
		//요소의 변경
		//      인덱스,데이터
		list2.set(1 , 30);
		
		for(int i=0 ; i<list2.size() ; i++) {    //0:1
			int num = list2.get(i);              //1:30
			System.out.println(i + ":" + num);   //2:3
		}                                        //3:40
		System.out.println("-------");
		
	}
}
