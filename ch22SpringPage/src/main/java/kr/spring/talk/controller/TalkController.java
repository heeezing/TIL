package kr.spring.talk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.talk.service.TalkService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TalkController {
	@Autowired
	private TalkService talkSevice;
	@Autowired
	private MemberService memberService;
	
	
	/*======================
	     	채팅방 생성
	======================*/
	@GetMapping("/talk/talkRoomWrite.do")
	public String talkRoomWrite() {
		return "talkRoomWrite";
	}
		
	
	/*======================
	 	   채팅 회원 검색
	======================*/
	@RequestMapping("/talk/memberSearchAjax.do")
	@ResponseBody
	public Map<String,Object> memberSearchAjax(@RequestParam String id,
												HttpSession session){
		Map<String,Object> mapJson = new HashMap<String,Object>();
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		if(memberVO == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			List<MemberVO> member = memberService.selectSearchMember(id);
			mapJson.put("result", "success");
			mapJson.put("member", member);
		}
		
		return mapJson;
	}
	
}
