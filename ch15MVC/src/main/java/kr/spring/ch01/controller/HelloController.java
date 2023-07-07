package kr.spring.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //모델클래스로 인식하게 하는 어노테이션 (명시X - 일반 클래스로 인식)
public class HelloController {
	//요청 URL과 실행 메서드 연결
	@RequestMapping("/hello.do")
	public ModelAndView hello() { //메서드명은 자유롭게 명시 (겹치지만 않으면 됨)
		//데이터와 뷰를 실어나르는 객체(Model->data를 의미)
		ModelAndView mav = new ModelAndView(); 
		
		/* [뷰 이름 지정]
		 : servlet-context.xml에 view의 경로(/WEB-INF/views/)와 확장자(.jsp)를 명시해놨기 때문에
		   hello만 입력해도 "/WEB-INF/views/hello.jsp"로 인식 */
		mav.setViewName("hello");
		
		/* [뷰에서 사용할 데이터 세팅] */
					   //속성명       속성값
  		mav.addObject("greeting", "안녕하세요"); 
		
		return mav;
		//DispatcherServlet으로 반환(return).
		//DispatcherServlet이 data를 읽어들여 request에 저장해주고, hello.jsp를 띄움.
	}
}