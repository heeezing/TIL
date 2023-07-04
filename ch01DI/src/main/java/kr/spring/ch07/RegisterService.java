package kr.spring.ch07;

public class RegisterService {
	//프로퍼티 (멤버 변수)
	private RegisterDAO registerDAO;
	
	//의존 관계 설정을 위해서 public 메서드를 정의
	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}
	
	public void write() {
		System.out.println("ResisterService의 write() 메서드 실행");
		registerDAO.insert();
	}
}
