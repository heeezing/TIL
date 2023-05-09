package kr.s04.queue;

import java.util.LinkedList;

public class LinkedListMain {
	public static void main(String[] args) {
		//큐(Queue) : 선입선출 FIFO (First-In First-Out)
		
		String[] array = {"서울", "부산", "대구", "광주", "인천"};
		
		LinkedList<String> list = new LinkedList<String>();
		
		//LinkedList에 문자열 객체 저장
		for(int i=0 ; i<array.length ; i++) {
			list.offer(array[i]);
		}
		
		//LinkedList 요소의 목록
		System.out.println(list);
		System.out.println("-----------");
		
		//Queue에 저장된 첫번째 요소를 검색
		while(list.peek() != null) { 
			//Queue에 저장된 첫번째 요소를 반환하고 제거
			System.out.println(list.poll());
		}
		
		System.out.println(list);
	}
}
