package kr.spring.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;
import kr.spring.item.service.ItemService;
import kr.spring.item.vo.ItemVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderDetailVO;
import kr.spring.order.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private MemberService memberService;
	
	
	/*======================
	        자바빈 초기화
	======================*/
	@ModelAttribute
	public OrderVO initCommand() {
		return new OrderVO();
	}
	
	
	/*======================
		 	상품 구매
	======================*/
	
	//주문 폼 호출
	@PostMapping("/order/orderForm.do")
	public String form(OrderVO orderVO, HttpSession session, 
					   Model model, HttpServletRequest request) {
		//장바구니에서 선택한 상품번호들 debug로 확인
		log.debug("<<cart_numbers>> : " + orderVO.getCart_numbers());
		
		if(orderVO.getCart_numbers() == null || orderVO.getCart_numbers().length == 0) {
			model.addAttribute("message", "정상적인 주문이 아닙니다.");
			model.addAttribute("url", request.getContextPath()+"/cart/list.do");
			return "common/resultView"; //자바스크립트 공용 파일로 이동
		}

		MemberVO user = (MemberVO)session.getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		map.put("cart_numbers", orderVO.getCart_numbers());
		int all_total = cartService.selectTotalByMem_num(map);
		if(all_total <= 0) {
			model.addAttribute("message", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			model.addAttribute("url", request.getContextPath()+"/cart/list.do");
			return "common/resultView"; 
		}
		
		//장바구니에 담겨있는 상품 정보 호출
		List<CartVO> cartList = cartService.selectListCart(map);
		for(CartVO cart : cartList) {
			ItemVO item = itemService.selectItem(cart.getItem_num());
			//상품이 미표시 상태로 바뀌었을 경우
			if(item.getStatus() == 1) {
				model.addAttribute("message", "["+item.getName()+"]판매 중지");
				model.addAttribute("url", request.getContextPath()+"/cart/list.do");
				return "common/resultView";
			}
			//상품 재고가 주문 수량보다 부족해졌을 경우
			if(item.getQuantity() < cart.getOrder_quantity()) {
				model.addAttribute("message", "["+item.getName()+"]재고 수량 부족으로 주문 불가");
				model.addAttribute("url", request.getContextPath()+"/cart/list.do");
				return "common/resultView";
			}
			
			model.addAttribute("list", cartList);
			model.addAttribute("all_total", all_total);
		}
		
		return "orderForm";
	}

	
	//주문 폼 - 회원 주소 읽기(배송지 선택)
	@RequestMapping("/order/getMemberAddress.do")
	@ResponseBody
	public Map<String,Object> formAddress(HttpSession session){
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			//회원 정보 읽어오기
			MemberVO db_member = memberService.selectMember(user.getMem_num());
			mapJson.put("result", "success");
			mapJson.put("zipcode", db_member.getZipcode());
			mapJson.put("address1", db_member.getAddress1());
			mapJson.put("address2", db_member.getAddress2());
			mapJson.put("phone", db_member.getPhone());
		}
		
		return mapJson;
	}
	
	
	//전송된 데이터 처리
	@PostMapping("/order/order.do")
	public String submit(@Valid OrderVO orderVO, BindingResult result,
						 HttpSession session, Model model,
						 HttpServletRequest request,
						 HttpServletResponse response) {
		log.debug("<<OrderVO>> : " + orderVO);
		
		//전송된 데이터 유효성 체크 결과, 오류 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return form(orderVO,session,model,request);
		}
		//비정상적인 주문의 경우 - 담긴 상품이 없는데 오류로 인해 넘어왔을 시
		if(orderVO.getCart_numbers() == null || orderVO.getCart_numbers().length == 0) {
			model.addAttribute("message", "정상적인 주문이 아닙니다.");
			model.addAttribute("url", request.getContextPath()+"/cart/list.do");
			return "common/resultView"; //자바스크립트 공용 파일로 이동
		}
		//비정상적인 주문의 경우 - 총 주문 금액이 0이나 음수일 시
		MemberVO user = (MemberVO)session.getAttribute("user");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		map.put("cart_numbers", orderVO.getCart_numbers());
		int all_total = cartService.selectTotalByMem_num(map);
		if(all_total <= 0) {
			model.addAttribute("message", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			model.addAttribute("url", request.getContextPath()+"/cart/list.do");
			return "common/resultView"; //자바스크립트 공용 파일로 이동
		}
		
		//장바구니에 담겨있는 상품 정보를 호출해 list에 저장
		List<CartVO> cartList = cartService.selectListCart(map);
		//정상적으로 주문될 시 개별 주문 상품 정보를 저장할 list 생성
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		
		for(CartVO cart : cartList) {
			ItemVO item = itemService.selectItem(cart.getItem_num());
			//상품이 미표시 상태로 바뀌었을 경우
			if(item.getStatus() == 1) {
				model.addAttribute("message", "["+item.getName()+"]판매 중지");
				model.addAttribute("url", request.getContextPath()+"/cart/list.do");
				return "common/resultView";
			}
			//상품 재고가 주문 수량보다 부족해졌을 경우
			if(item.getQuantity() < cart.getOrder_quantity()) {
				model.addAttribute("message", "["+item.getName()+"]재고 수량 부족으로 주문 불가");
				model.addAttribute("url", request.getContextPath()+"/cart/list.do");
				return "common/resultView";
			}
			
			OrderDetailVO orderDetail = new OrderDetailVO();
			orderDetail.setItem_num(cart.getItem_num());
			orderDetail.setItem_name(cart.getItemVO().getName());
			orderDetail.setItem_price(cart.getItemVO().getPrice());
			//배송비 세팅
			//배송비 면제금액이 있고, 면제금액 이상의 금액을 주문 시
			if(cart.getItemVO().getDelivery_limit() != 0 && 
				cart.getOrder_quantity() * cart.getItemVO().getPrice() >= cart.getItemVO().getDelivery_limit()) {
				orderDetail.setItem_delivery(0); //배송비 무료 처리
			}else {
				orderDetail.setItem_delivery(cart.getItemVO().getDelivery_fee()); //배송비 적용
			}
			orderDetail.setOrder_quantity(cart.getOrder_quantity());
			orderDetail.setItem_total(cart.getSub_total()); //동일 상품의 합계 금액 
			
			orderDetailList.add(orderDetail);
		}
		
		//주문 상품의 대표 상품명 세팅
		String item_name = "";
		if(cartList.size() == 1) { //상품 하나 구매 시
			item_name = cartList.get(0).getItemVO().getName();
		}else { //여러 상품 구매 시 
			item_name = cartList.get(0).getItemVO().getName() + "외 " + (cartList.size()-1) + "건";
		}
		orderVO.setItem_name(item_name);
		
		orderVO.setOrder_total(all_total);
		orderVO.setMem_num(user.getMem_num());
		
		//주문 등록 처리
		orderService.insertOrder(orderVO, orderDetailList);
		
		//Refresh 정보를 응답 헤더에 추가 (2초 동안 메시지 보여준 후 메인으로 이동)
		response.addHeader("Refresh", "2;url=../main/main.do");
		model.addAttribute("accessMsg", "주문이 완료되었습니다.");
		
		return "common/notice"; //jsp
	}
	
	
}
