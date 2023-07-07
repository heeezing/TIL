package kr.spring.ch18;

import javax.annotation.Resource;

public class HomeController {
	//프로퍼티
	//bean의 이름과 프로퍼티명이 일치하면 의존 관계 설정
	@Resource
	private Camera camera1;
	
	//@Resource(name="bean객체의이름") - bean객체의 이름 지정 가능. 프로퍼티명과 일치하지 않아도 됨.
	@Resource(name="cameraz") // cameraz -> 빈의 이름
	private Camera camera2; // camera2 -> 프로퍼티 명

	private Camera camera3;
	
	
	public void setCamera1(Camera camera1) {
		this.camera1 = camera1;
	}

	public void setCamera2(Camera camera2) {
		this.camera2 = camera2;
	}

	@Resource
	public void setCamera3(Camera camera3) {
		this.camera3 = camera3;
	}

	@Override
	public String toString() {
		return "HomeController [camera1=" + camera1 + ", camera2=" + camera2 + ", camera3=" + camera3 + "]";
	}
}
