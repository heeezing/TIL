package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.item.dao.ItemDAO;
import kr.item.vo.ItemVO;

public class UserOrderFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 X
			return "redirect:/member/loginForm.do";
		}
		
		//POST 방식의 접근만 허용하도록 설정 (GET 방식 불허)
		//= 장바구니를 경유해야만 들어올 수 있음. 주소창 직접 입력 X
		if(request.getMethod().toUpperCase().equals("GET")) {
			return "redirect:/item/itemList.do";
		}
		
		CartDAO dao = CartDAO.getInstance();
		int all_total = dao.getTotalByMem_num(user_num);
		if(all_total <= 0) {
			request.setAttribute("notice_msg", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/item/itemList.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//장바구니에 담겨 있는 상품 정보 호출
		List<CartVO> cartList = dao.getListCart(user_num);
		ItemDAO itemDao = ItemDAO.getInstance();
		//변경 사항이 생길 경우에 대해 조건 체크
		for(CartVO cart : cartList) {
			ItemVO item = itemDao.getItem(cart.getItem_num()); //상품 정보
			//상품이 미표시가 됐을 경우
			if(item.getStatus() == 1) {
				request.setAttribute("notice_msg", "["+item.getName()+"] 상품 판매 중지");
				request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
				return "/WEB-INF/views/common/alert_singleView.jsp";
			}
			//주문 수량보다 재고 수량이 적어졌을 경우
			if(item.getQuantity() < cart.getOrder_quantity()) {
				request.setAttribute("notice_msg", "["+item.getName()+"] 재고 수량 부족으로 주문 불가");
				request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
				return "/WEB-INF/views/common/alert_singleView.jsp";
			}
		}
		
		request.setAttribute("list", cartList);
		request.setAttribute("all_total", all_total);
		
		return "/WEB-INF/views/order/user_orderForm.jsp";
	}

}
