package kr.spring.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.cart.service.CartService;
import kr.spring.cart.vo.CartVO;
import kr.spring.item.service.ItemService;
import kr.spring.item.vo.ItemVO;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private ItemService itemService;
	
	
	/*======================
		 장바구니에 상품 담기
	======================*/
	
	@RequestMapping("/cart/write.do")
	@ResponseBody
	public Map<String,String> submit(CartVO cartVO, HttpSession session){
		log.debug("<<CartVO>> : " + cartVO);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		
		//로그인 체크
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			cartVO.setMem_num(user.getMem_num());
			//동일 상품이 있는지 체크를 위해 정보를 가져옴
			CartVO db_cart = cartService.selectCart(cartVO);
			if(db_cart == null) { //등록된 동일 상품이 없음
				//장바구니에 상품 등록
				cartService.insertCart(cartVO);
				mapJson.put("result", "success");
			}else { //등록된 동일 상품이 있음
				//재고 수를 구하기 위해 상품 정보 호출
				ItemVO db_item = itemService.selectItem(db_cart.getItem_num());			
				//구매 수량 합치기 (기존 구매수량 + 새로 입력한 구매수량)
				int order_quantity = db_cart.getOrder_quantity()+cartVO.getOrder_quantity();
				//상품 재고 수량과 비교
				if(db_item.getQuantity() < order_quantity) {
					//상품 재고 수량 < 장바구니에 담은 구매 수량
					mapJson.put("result", "overquantity");
				}else {
					//수량 합산 처리
					cartVO.setOrder_quantity(order_quantity);
					//장바구니 업데이트
					cartService.updateCartByItem_num(cartVO);
					
					mapJson.put("result", "success");
				}
			}
		}
		
		return mapJson;
	}
	
	
	
	/*======================
		   장바구니 목록
	======================*/
	
	@RequestMapping("/cart/list.do")
	public String list(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num",user.getMem_num());
		//회원 번호 별 총 구매액
		int all_total = cartService.selectTotalByMem_num(map);
		
		List<CartVO> list = null;
		if(all_total > 0) { //=담은 데이터가 있다.
			list = cartService.selectListCart(map);
		}
		
		model.addAttribute("all_total", all_total);
		model.addAttribute("list", list);
		
		return "cartList";
	}
	
	
	
	/*======================
		장바구니 상품 수량 변경
	======================*/
	
	@RequestMapping("/cart/modifyCart.do")	
	@ResponseBody
	public Map<String,String> submitModify(CartVO cartVO, HttpSession session){
		Map<String,String> mapJson = new HashMap<String,String>();
		//로그인 체크
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			//현재 구매하고자 하는 상품의 재고수를 구함
			ItemVO item = itemService.selectItem(cartVO.getItem_num());
			if(item.getStatus() == 1) {
				mapJson.put("result", "noSale");
			}else if(item.getQuantity() < cartVO.getOrder_quantity()) {
				mapJson.put("result", "noQuantity"); //재고 부족
			}else { //상품 수량 변경이 가능한 상태
				cartService.updateCart(cartVO);
				mapJson.put("result", "success");
			}
		}
		return mapJson;
	}	
	
	
	
	/*======================
		 장바구니 상품 삭제
	======================*/
	
	@RequestMapping("/cart/deleteCart.do")
	@ResponseBody
	public Map<String,String> delete(@RequestParam int cart_num, HttpSession session){
		Map<String,String> mapJson = new HashMap<String,String>();
		//로그인 체크
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			cartService.deleteCart(cart_num);
			mapJson.put("result", "success");
		}
		return mapJson;
	}
	
}
