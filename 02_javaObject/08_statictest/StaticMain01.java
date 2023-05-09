package kr.s08.statictest;

public class StaticMain01 {
	public static void main(String[] args) {
		//객체 선언 및 생성
		StaticCount sc1 = new StaticCount();
		System.out.println("c:" + sc1.c + ", count:" + StaticCount.count);
		//c:1, count:1
		
		StaticCount sc2 = new StaticCount();
		System.out.println("c:" + sc2.c + ", count:" + StaticCount.count);
		//c:1, count:2
		
		StaticCount sc3 = new StaticCount();
		System.out.println("c:" + sc3.c + ", count:" + StaticCount.count);
		//c:1, count:3
	}
}