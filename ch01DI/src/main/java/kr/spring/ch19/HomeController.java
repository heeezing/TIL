package kr.spring.ch19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * <context:component-scan/> 태그를 추가하면
 * 스프링은 지정한 패키지에서 @Component 어노테이션이 적용된 클래스를 검색하여 bean으로 등록.
 * 
 * 자동 등록된 bean의 id는 클래스 이름의 첫 글자를 소문자로 바꿔서 사용.
 * ex)클래스명 [HomeController] -> bean이름 [homeController]
 * 
 * bean의 이름을 지정하고 싶으면 
 * 1) @Component("home") 또는
 * 2) @Component
 *    @Named("home") 와 같이 명시.
 */
@Component
public class HomeController {
	@Autowired
	private Camera camera;

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	@Override
	public String toString() {
		return "HomeController [camera=" + camera + "]";
	}
}
