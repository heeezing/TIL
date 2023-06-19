package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderDetailVO;
import kr.order.vo.OrderVO;

public class AdminModifyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//[로그인 여부 체크]
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 X
			return "redirect:/member/loginForm.do";
		}
		
		//[관리자 여부 체크]
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth < 9) { //관리자로 로그인 X
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//[관리자로 로그인한 경우]
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		OrderDAO dao = OrderDAO.getInstance();
		OrderVO order = dao.getOrder(order_num);
		List<OrderDetailVO> detailList = dao.getListOrderDetail(order_num);
		
		request.setAttribute("order", order);
		request.setAttribute("detailList", detailList);
		
		return "/WEB-INF/views/order/admin_modifyForm.jsp";
	}

}
