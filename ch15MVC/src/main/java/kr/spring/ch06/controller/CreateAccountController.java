package kr.spring.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch06.validator.MemberInfoValidator;
import kr.spring.ch06.vo.MemberInfo;

@Controller
public class CreateAccountController {
	
	//유효성 체크(커스텀 태그 사용) 시 자바빈(VO) 초기화 필수!
	//객체 메서드가 호출 되기 "전"에 꼭 자바빈 초기화(객체 생성)를 해놔야한다.
	@ModelAttribute("vo")
	public MemberInfo initCommand() {
		return new MemberInfo();
	}
	
	@GetMapping("/account/create.do")
	public String form() {
		return "account/creationForm"; //뷰 이름
	}
	
	@PostMapping("/account/create.do")								 //에러 정보가 저장되는 객체(부모-Errors)
	public String submit(@ModelAttribute("vo") MemberInfo memberInfo, BindingResult result) {
		System.out.println("전송된 데이터 : " + memberInfo);
		
		//전송된 데이터 유효성 체크
		new MemberInfoValidator().validate(memberInfo, result);
		
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return "account/creationForm";
		}
		
		return "account/created"; //뷰 이름
	}
}
