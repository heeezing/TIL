package kr.spring.ch18;

public class Camera {
	//프로퍼티
	private int number;

	public void setNumber(int number) { //setter생성
		this.number = number;
	}

	@Override
	public String toString() { //toString() 생성
		return "Camera [number=" + number + "]";
	}
}
