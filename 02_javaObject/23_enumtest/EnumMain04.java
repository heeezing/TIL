package kr.s23.enumtest;

enum Item2 {
	ADD(5), DEL(11), SEARCH(2), CANCEL(22);
	
	//위에 지정한 상수 값들을 저장하기 위한 공간 생성
	private final int var;
	
	//생성자
	Item2(int var) {
		this.var = var;
	}
	
	//메서드
	public int getVar() {
		return var;
	}
}

public class EnumMain04 {
	public static void main(String[] args) {
		System.out.println(Item2.ADD);  //ADD
		System.out.println(Item2.DEL);  //DEL
		System.out.println(Item2.SEARCH);  //SEARCH
		System.out.println(Item2.CANCEL);  //CANCEL
		System.out.println("----------");
		
		System.out.println(Item2.ADD.getVar());  //5
		System.out.println(Item2.DEL.getVar());  //11
		System.out.println(Item2.SEARCH.getVar());  //2
		System.out.println(Item2.CANCEL.getVar());  //22
	}
}
