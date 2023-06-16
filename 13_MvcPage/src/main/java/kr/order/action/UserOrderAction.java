package kr.order.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;
import kr.item.dao.ItemDAO;
import kr.item.vo.ItemVO;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;

public class UserOrderAction implements Action{

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
		
		//POST 방식의 접근만 허용
		if(request.getMethod().toUpperCase().equals("GET")) {
			return "redirect:/item/itemList.do";
		}

		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//정상적인 접근인지 유효성 체크
		CartDAO dao = CartDAO.getInstance();
		int all_total = dao.getTotalByMem_num(user_num);
		if(all_total <= 0) {
			request.setAttribute("notice_msg", "정상적인 주문이 아니거나 상품의 수량이 부족합니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/item/itemList.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//장바구니에 담겨 있는 상품 정보 반환
		List<CartVO> cartList = dao.getListCart(user_num);
		//주문 상품의 대표 상품명 생성
		String item_name;
		if(cartList.size() == 1) { //상품이 하나인 경우 
			item_name = cartList.get(0).getItemVO().getName(); //인덱스0에 담긴 이름 불러옴
		}else {
			item_name = cartList.get(0).getItemVO().getName() + "외 " + (cartList.size()-1) + "건";
		}
		
		//개별 상품 정보 담기
		List<OrderDetailVO> orderDetailList = new ArrayList<OrderDetailVO>();
		ItemDAO itemDao = ItemDAO.getInstance();
		//주문 전 유효성 체크
		for(CartVO cart : cartList) {
			ItemVO item = itemDao.getItem(cart.getItem_num()); //주문하는 상품 정보 읽어옴
			 //주문하는 사이 미표시 상품이 되버린 경우
			if(item.getStatus() == 1) {
				request.setAttribute("notice_msg", "["+item.getName()+"] 상품 판매 중지");
				request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
				return "/WEB-INF/views/common/alert_singleView.jsp";
			}
			//주문하는 사이 주문 수량보다 재고가 부족해진 경우
			if(item.getQuantity() < cart.getOrder_quantity()) {
				request.setAttribute("notice_msg", "["+item.getName()+"] 재고 수량 부족으로 주문 불가");
				request.setAttribute("notice_url", request.getContextPath()+"/cart/list.do");
				return "/WEB-INF/views/common/alert_singleView.jsp";
			}
			
			//cart에 있는 데이터를 주문상세(자바빈)에 저장
			OrderDetailVO orderDetail = new OrderDetailVO();
			orderDetail.setItem_num(cart.getItem_num()); 
			orderDetail.setItem_name(cart.getItemVO().getName()); 
			orderDetail.setItem_price(cart.getItemVO().getPrice()); 
			orderDetail.setOrder_quantity(cart.getOrder_quantity()); 
			orderDetail.setItem_total(cart.getSub_total()); 
			//ArrayList에 저장
			orderDetailList.add(orderDetail);
		}//end of for
		
		//구매 정보 담기
		OrderVO order = new OrderVO();
		order.setItem_name(item_name);
		order.setOrder_total(all_total);
		order.setPayment(Integer.parseInt(request.getParameter("payment")));
		order.setReceive_name(request.getParameter("receive_name"));
		order.setReceive_post(request.getParameter("receive_post"));
		order.setReceive_address1(request.getParameter("receive_address1"));
		order.setReceive_address2(request.getParameter("receive_address2"));
		order.setReceive_phone(request.getParameter("receive_phone"));
		order.setNotice(request.getParameter("notice"));
		order.setMem_num(user_num);
		
		OrderDAO orderDao = OrderDAO.getInstance();
		orderDao.insertOrder(order, orderDetailList);
		
		//refresh 정보를 응답 헤더에 추가
		//구매 완료 시 2초 후 main페이지로 이동하도록 설정
		response.addHeader("Refresh", "2;url=../main/main.do");
		
		request.setAttribute("accessMsg", "주문이 완료되었습니다.");
		request.setAttribute("accessUrl", request.getContextPath()+"/main/main.do");
		
		return "/WEB-INF/views/common/notice.jsp";
	}

}
