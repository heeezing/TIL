package kr.spring.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.item.service.ItemService;
import kr.spring.item.vo.ItemVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/")
	public String main() {
		return "redirect:/main/main.do";
	}
	
	@RequestMapping("/main/main.do")
	public String main(Model model) {
		//최신 등록 상품 읽어오기
		Map<String,Object> mapItem = new HashMap<String,Object>();
		mapItem.put("start", 1);
		mapItem.put("end", 12);
		mapItem.put("status", 1); //1보다 커야하니까 2(표시상품)만 불러옴
		List<ItemVO> item_list = itemService.selectItemList(mapItem);
		
		model.addAttribute("item_list", item_list);
		
		return "main"; //타일스 설정의 식별자(main.xml의 definition name)
	}
	
	@RequestMapping("/main/admin.do")
	public String adminMain(Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", 1);
		map.put("end", 5); //5개만 가져오기 위해 end 지정! 페이지 처리 X		
		
		List<MemberVO> memberList = memberService.selectList(map);
		List<OrderVO> orderList = orderService.selectListOrder(map);
		
		//status에 0을 넣어 미표시(1),표시(2) 모두 불러오도록 처리
		map.put("status", 0);
		List<ItemVO> itemList = itemService.selectItemList(map);

		//최신 회원 정보 5건 가져오기
		model.addAttribute("memberList", memberList);
		model.addAttribute("orderList", orderList);
		model.addAttribute("itemList", itemList);
		
		return "admin"; //타일스 설정의 식별자
	}
}
