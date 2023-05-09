package kr.s23.enumtest;

enum Item { 
	ADD, DEL, SEARCH, CANCEL
}

public class EnumMain03 {
	public static void main(String[] args) {
		//values()메서드는 열거 타입의 모든 열거 객체들을 
		//배열로 만들어 반환해준다.
		Item[] items = Item.values();
		System.out.println("items.length : " + items.length); //items.length : 4
		
		//저장되어있는 정수값을 확인할 수 있음
		for(Item n : items) {
			System.out.println(n + ":" + n.ordinal());
		}
		  /*
		   ADD:0
		   DEL:1
		   SEARCH:2
		   CANCEL:3
		 */
	}
}
