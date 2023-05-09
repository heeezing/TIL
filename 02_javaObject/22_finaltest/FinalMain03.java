package kr.s22.finaltest;

//클래스에 final을 명시하면 상속 불허
final class Me1 {
	int var = 100;
	public int getVar() {
		return var;
	}
}

//상속 시 -> 오류
//public class FinalMain03 extends Me1 {

public class FinalMain03 {
	public static void main(String[] args) {
		
	}
}
