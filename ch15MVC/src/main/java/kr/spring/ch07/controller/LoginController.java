package kr.spring.ch07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch07.service.LoginCheckException;
import kr.spring.ch07.service.LoginService;
import kr.spring.ch07.validator.LoginVOValidator;
import kr.spring.ch07.vo.LoginVO;

@Controller
public class LoginController {
	
	@Autowired //필드에 의존관계 형성
	private LoginService loginService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public LoginVO initCommand() {
		return new LoginVO();
	}
	
	@GetMapping("/login/login.do")
	public String form() {
		return "login/form"; //뷰 이름
	}
	
	@PostMapping("/login/login.do")
	public String submit(LoginVO loginVO, BindingResult result) {
		//유효성 체크
		new LoginVOValidator().validate(loginVO, result);
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//로그인 체크
		try {
			//로그인 성공 시
			loginService.checkLogin(loginVO);
			return "redirect:/index.jsp";
		}catch(LoginCheckException e) {
			//로그인 실패 시
			//필드가 따로 없으므로(아이디쪽도 아니고 비밀번호 쪽도 아니라) 에러 코드만 지정
			result.reject("invalidIdOrPassword"); //필드 있을 땐 rejectValue()사용
			return form();
		}
	}
}
