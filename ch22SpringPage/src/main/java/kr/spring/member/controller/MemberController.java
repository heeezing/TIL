package kr.spring.member.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

@Controller
public class MemberController {
    //로그 대상 지정
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	/*======================
	       자바빈 초기화
	======================*/
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	
	/*======================
			회원 가입
	======================*/
	//회원가입 폼 호출
	@GetMapping("/member/registerUser.do")
	public String form() {
		return "memberRegister";
	}
	
	//회원가입 처리
	@PostMapping("/member/registerUser.do")	//데이터 세팅(notice.jsp보여주기 위해)하기 위해 model에 넣음
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model) {
	      logger.debug("<<회원가입>> : " + memberVO);
		
		//유효성 체크 결과, 오류가 있을 시 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//정상 처리 시 - 회원 가입 
		memberService.insertMember(memberVO);
		
		model.addAttribute("accessMsg", "회원가입이 완료되었습니다.");
		
		return "common/notice";
	}
}
