package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberAdminController {
	@Autowired
	private MemberService memberService;
	
	/*======================
		 관리자 - 회원 목록	
	======================*/
	
	@RequestMapping("/member/admin_list.do")
	public ModelAndView getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								String keyfield, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//전체or검색 레코드 수
		int count = memberService.selectRowCount(map);
		log.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,
										 20,10,"admin_list.do");
		
		//목록 읽어오기
		List<MemberVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = memberService.selectList(map);
			log.debug("<<목록>> : " + list);
		}
		
		//뷰에서 사용할 수 있도록 데이터 넣어놓기
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin_memberList"); //tiles 설정
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	
	/*======================
	 	관리자 - 회원 권한 변경	
	======================*/
	
	//수정 폼
	@GetMapping("/member/admin_update.do")
	public String form(@RequestParam int mem_num, Model model) {
		MemberVO memberVO = memberService.selectMember(mem_num);
		model.addAttribute("memberVO", memberVO);
		
		return "admin_memberModify"; //tiles 설정
	}	

	//전송된 데이터 처리
	@PostMapping("/member/admin_update.do")
	public String submit(MemberVO memberVO, Model model,
							HttpServletRequest request) {
		log.debug("<<관리자 회원 권한 수정>> : " + memberVO);
		
		//회원 권한 수정
		memberService.updateByAdmin(memberVO);
		
		//뷰에 표시할 메시지 저장
		model.addAttribute("message", "회원 권한 수정 완료");
		model.addAttribute("url", request.getContextPath()
								  +"/member/admin_update.do?mem_num="
								  +memberVO.getMem_num());
		
		return "common/resultView";
	}
	
}
