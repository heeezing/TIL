package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/main/main.do")
	public String main(Model model) {
		return "main"; //타일스 설정의 식별자(main.xml의 definition name)
	}
	
	@RequestMapping("/main/admin.do")
	public String adminMain(Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", 1);
		map.put("end", 5); //5개만 가져오기 위해 end 지정! 페이지 처리 X
		
		List<MemberVO> memberList = memberService.selectList(map);
		//최신 회원 정보 5건 가져오기
		model.addAttribute("memberList", memberList);
		
		return "admin"; //타일스 설정의 식별자
	}
}
