package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

public class AdminModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response)
								  throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 X
			//redirect는 .do형태일 경우 사용(클라이언트가 직접 호출할 수 있도록)
			//.jsp는 forward방식으로 보내져야함.
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth < 9) { //관리자로 로그인 X
			//forward 방식
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자로 로그인 O
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//데이터를 자바빈에 저장
		OrderVO order = new OrderVO();
		order.setOrder_num(Integer.parseInt(request.getParameter("order_num")));
		order.setStatus(Integer.parseInt(request.getParameter("status")));
		order.setReceive_name(request.getParameter("receive_name"));
		order.setReceive_post(request.getParameter("receive_post"));
		order.setReceive_address1(request.getParameter("receive_address1"));
		order.setReceive_address2(request.getParameter("receive_address2"));
		order.setReceive_phone(request.getParameter("receive_phone"));
		order.setNotice(request.getParameter("notice"));
		//DAO메서드에 넘겨줌
		OrderDAO orderDao = OrderDAO.getInstance();
		orderDao.updateOrder(order);
		
		request.setAttribute("notice_msg", "정상적으로 수정되었습니다.");
		request.setAttribute("notice_url",
							  request.getContextPath()+"/order/modifyForm.do?order_num="+order.getOrder_num());
		
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}
}
