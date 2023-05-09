package kr.s16.supertest;

//부모 클래스
class Point{
	int x;
	int y;
	//생성자
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String getLocation() {
		return "x : " + x + ", y : " + y;
	}
}

//자식 클래스
class Point3D extends Point {
	int z;
	//생성자
	public Point3D(int x, int y, int z) {
		//부모 클래스에 인자가 있는 생성자를 호출
		super(x,y);
		this.z = z;
	}
	//메서드 오버라이딩
	@Override
	public String getLocation() {
		return "x : " + x + ", y : " + y + ", z : " + z;
	}
}

public class SuperMain04 {
	public static void main(String[] args) {
		Point3D p3 = new Point3D(10,20,30);
		System.out.println(p3.getLocation());
	}
}
