package kr.spring.ch09.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch09.vo.MemberVO;

@Controller
public class MemberWriteController {
	//자바빈(VO) 초기화
	@ModelAttribute("vo")
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	@GetMapping("/member/write.do")
	public String form() {
		return "member/write";
	}
	
	@PostMapping("/member/write.do")
	public String submit(@ModelAttribute("vo") @Valid MemberVO memberVO, BindingResult result) {
											 //@Valid가 있어야 자바빈 안의 어노테이션이 동작됨.
		System.out.println("전송된 데이터 : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		//유효성 체크 결과 오류X, 정상적 처리 시
		return "redirect:/index.jsp";
	}
}
