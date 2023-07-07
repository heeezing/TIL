package kr.spring.ch04.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	@RequestMapping("/cookie/make.do")
						//서블릿 관련 객체 - 인자로만 명시하면 간편히 읽어올 수 있다.
	public String make(HttpServletResponse response) { 
		//쿠키를 생성해서 클라이언트에 전송
		response.addCookie(new Cookie("auth","1"));
		return "cookie/make"; //뷰 이름
	}
	
	@RequestMapping("/cookie/view.do")
					 //@CookieValue() : 원하는 쿠키만 지정해서 불러올 수 있음
	public String view(@CookieValue(value="auth",defaultValue="0") String auth) {
		
		System.out.println("auth 쿠키 : " + auth);
		
		return "cookie/view";
	}
}
