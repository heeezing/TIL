package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

public class UserModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
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
		//메서드 변수 & get방식 url처리를 위해 order_num 정보 받아옴
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		
		//주문 정보 수정 전, 배송 상태를 한 번 더 체크
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO db_order = dao.getOrder(order_num);
		if(db_order.getStatus() > 1) {
			//수정하려는 찰나 관리자가 배송 상태를 변경했을 시(배송 시작되버리면) 주문자가 변경할 수 없음
			request.setAttribute("notice_msg", "배송 상태가 변경되어 주문자가 주문 정보 변경 불가");
			request.setAttribute("notice_url", request.getContextPath()+"/order/orderList.do");
		}
		
		//배송 대기(1) 상태일 경우 -> 수정 가능 -> 수정폼에 입력받은 데이터를 자바빈에 저장
		OrderVO order = new OrderVO();
		order.setOrder_num(order_num);
		order.setStatus(Integer.parseInt(request.getParameter("status")));
		order.setReceive_name(request.getParameter("receive_name"));
		order.setReceive_post(request.getParameter("receive_post"));
		order.setReceive_address1(request.getParameter("receive_address1"));
		order.setReceive_address2(request.getParameter("receive_address2"));
		order.setReceive_phone(request.getParameter("receive_phone"));
		order.setNotice(request.getParameter("notice"));
		
		//배송 관련 주문 정보 수정 메서드 실행
		dao.updateOrder(order);
		
		//자바스크립트 이용해서 결과 표시
		request.setAttribute("notice_msg", "정상적으로 수정되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/order/orderModifyForm.do?order_num="+order_num);
		
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}

}
