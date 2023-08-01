package kr.spring.talk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.talk.service.TalkService;
import kr.spring.talk.vo.TalkMemberVO;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TalkController {
	@Autowired
	private TalkService talkService;
	@Autowired
	private MemberService memberService;
	
	
	/*======================
	     	채팅방 생성
	======================*/
	//폼 호출
	@GetMapping("/talk/talkRoomWrite.do")
	public String talkRoomWrite() {
		return "talkRoomWrite";
	}
	
	//전송된 데이터 처리
	@PostMapping("/talk/talkRoomWrite.do")
	public String talkRoomSubmit(TalkRoomVO vo, HttpSession session) {
		log.debug("<<채팅방 만들기>> : " + vo);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//채팅 멤버 초대(입퇴장) 문구 설정
		vo.setTalkVO(new TalkVO());
		vo.getTalkVO().setMem_num(user.getMem_num());
		//채팅에서 나오지 않을 법한 문구인 '@{member}@'를 넣어 초대(입퇴장) 문구를 구별
		vo.getTalkVO().setMessage(user.getId()+"님이 "+findMemberId(vo,user)+"님을 초대했습니다.@{member}@");
		
		//채팅방 생성 처리
		talkService.insertTalkRoom(vo);
		
		return "redirect:/talk/talkList.do";
	}
		
	//초대한 회원의 id 구하기
	private String findMemberId(TalkRoomVO vo, MemberVO user) {
		String member_id = "";
		int[] members = vo.getMembers();
		for(int i=0 ; i<members.length ; i++) {
			String temp_id = memberService.selectMember(members[i]).getId();
			//초대한 사람의 아이디는 제외
			if(!user.getId().equals(temp_id)) {
				member_id += temp_id;
				if(i < members.length - 1) member_id += ", ";
			}
		}
		
		return member_id;
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

	
	/*======================
		   채팅방 목록
	======================*/
	@RequestMapping("/talk/talkList.do")
	public String chatList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
							String keyword, HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("mem_num", user.getMem_num());
		
		int count = talkService.selectRowCount(map);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(null,keyword,currentPage,count,30,10,"talkList.do");
		
		List<TalkRoomVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = talkService.selectTalkRoomList(map);
		}
		
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		model.addAttribute("page", page.getPage());
		
		return "talkList";
	}
	
	
	
	/*======================
		  채팅 메시지 처리
	======================*/
	
	//채팅 메시지 페이지 호출
	@RequestMapping("/talk/talkDetail.do")
	public String chatDetail(@RequestParam int talkroom_num, 
							 Model model, HttpSession session) {
		String chatMember = ""; //채팅 멤버
		String room_name = ""; //채팅방 이름
		//로그인한 회원을 알아내어 그 사람의 채팅방 이름을 알아냄
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		List<TalkMemberVO> list = talkService.selectTalkMember(talkroom_num);
		for(int i=0 ; i<list.size() ; i++) {
			TalkMemberVO vo = list.get(i);
			if(user.getMem_num() == vo.getMem_num()) {
				//로그인한 회원의 채팅방 이름 세팅
				room_name = vo.getRoom_name();
			}
			//채팅 멤버 저장(누적)
			if(i>0) chatMember += ",";
			chatMember += list.get(i).getId();
		}
		
		//채팅 멤버 id
		model.addAttribute("chatMember", chatMember);
		//채팅 멤버 수
		model.addAttribute("chatCount", list.size());
		//로그인한 회원의 채팅방 이름
		model.addAttribute("room_name", room_name);
		
		return "talkDetail";
	}
	
	
	//채팅 메시지 전송
	@RequestMapping("/talk/writeTalk.do")
	@ResponseBody
	public Map<String,Object> writeTalkAjax(TalkVO vo, HttpSession session){
		Map<String,Object> mapJson = new HashMap<String,Object>();
		//로그인 체크
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			vo.setMem_num(user.getMem_num()); //발신자
			
			log.debug("<<채팅 메시지 전송>> : " + vo);
			
			talkService.insertTalk(vo);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	
	
	//채팅 메시지 읽기
	@RequestMapping("/talk/talkDetailAjax.do")
	@ResponseBody
	public Map<String,Object> talkDetailAjax(@RequestParam int talkroom_num, 
											 HttpSession session){
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		//로그인 체크
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			Map<String,Integer> map = new HashMap<String,Integer>();
			map.put("talkroom_num", talkroom_num);
			map.put("mem_num", user.getMem_num());
			//데이터 읽어와서 list에 저장
			List<TalkVO> list = talkService.selectTalkDetail(map);
			
			mapJson.put("result", "success");
			mapJson.put("list", list);
			mapJson.put("user_num", user.getMem_num());
		}
		
		return mapJson;
	}
	
	
	
	/*======================
	 	  채팅방 이름 변경
	======================*/
	
	@RequestMapping("/talk/changeName.do")
	@ResponseBody
	public Map<String,String> changeName(TalkMemberVO vo, HttpSession session){
		Map<String,String> mapJson = new HashMap<String,String>();
		//로그인 체크
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			vo.setMem_num(user.getMem_num());
			talkService.changeRoomName(vo);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	
	
	
	/*======================
		  채팅방 멤버 추가
	======================*/
	
	@RequestMapping("/talk/newMemberAjax.do")
	@ResponseBody
	public Map<String,Object> addNewMember(TalkRoomVO vo, HttpSession session){
		Map<String,Object> mapJson = new HashMap<String,Object>();
		//로그인 체크
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			vo.setTalkVO(new TalkVO());
			vo.getTalkVO().setMem_num(user.getMem_num());
			vo.getTalkVO().setMessage(user.getId()+"님이 "+findMemberId(vo, user)+"님을 초대했습니다.@{member}@");
			//채팅방 이름 세팅
			TalkRoomVO db_vo = talkService.selectTalkRoom(vo.getTalkroom_num());
			vo.setBasic_name(db_vo.getBasic_name());
			//멤버 추가 처리
			talkService.insertNewMember(vo);
			
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}
	
}
