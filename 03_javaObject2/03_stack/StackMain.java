package kr.s03.stack;

import java.util.Stack;

public class StackMain {
	public static void main(String[] args) {
		//스택(Stack) : 후입선출 LIFO (Last-In First-Out) 방식
		String[] array = {"진달래", "백합", "개나리", "벚꽃", "장미"};
		
		Stack<String> st = new Stack<String>();
		
		//Stack에 문자열 객체 저장
		for(int i=0 ; i<array.length ; i++) {
			st.push(array[i]);
		}
		
		//Stack의 요소 목록
		System.out.println(st);  //[진달래, 백합, 개나리, 벚꽃, 장미]
		System.out.println("--------------");
		
		//현재 stack이 비어있는지 검사
		while(!st.isEmpty()) {
			//stack에서 저장된 객체를 꺼내고, 꺼낸 객체를 반환
			System.out.println(st.pop()); 
		}
		System.out.println("--------------");
		System.out.println(st);  //[]
	}
}
