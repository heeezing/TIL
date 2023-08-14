package kr.spring.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderAdminController {
	@Autowired
	private OrderService orderService;
	
	/*======================
	 		주문 목록
	======================*/
	@RequestMapping("/order/admin_list.do")
	public ModelAndView admin_list(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
									String keyfield, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//전체or검색 레코드 수 읽어오기
		int count = orderService.selectOrderCount(map);
		log.debug("<<count>> : " + count);
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,20,10,"admin_list.do");
		
		//전체or검색 목록 읽어오기
		List<OrderVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = orderService.selectListOrder(map);
		}
		
		//데이터 세팅
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminOrderList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	
	
	/*======================
	 		주문 내역
	======================*/
	
	@RequestMapping("/order/admin_detail.do")
	public String adminDetail(@RequestParam int order_num, Model model) {
		
		
		return "adminOrderDetail";
	}
	
	
}
