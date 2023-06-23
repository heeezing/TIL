package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

public class UserOrderCancelAction implements Action{

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 X
			return "redirect:/member/loginForm.do";
		}

		//메서드 변수 & get방식 url처리를 위해 order_num 정보 받아옴
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		
		//주문 취소(로 상태 변경) 전, 배송 상태를 한 번 더 체크
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO db_order = dao.getOrder(order_num);
		if(db_order.getStatus() > 1) {
			//취소하려는 찰나 관리자가 배송 상태를 변경했을 시(배송 시작되버리면) 주문자가 변경할 수 없음
			request.setAttribute("notice_msg", "배송 상태가 변경되어 주문자가 주문을 취소할 수 없음");
			request.setAttribute("notice_url", request.getContextPath()+"/order/orderList.do");
			
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//주문 취소(상태 변경) 메서드 실행
		dao.updateOrderCancel(order_num);
		
		request.setAttribute("notice_msg", "주문 취소가 완료되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/order/orderModifyForm.do?order_num="+order_num);
		
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}

}
