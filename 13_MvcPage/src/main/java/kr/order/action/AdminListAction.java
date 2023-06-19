package kr.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;
import kr.util.PageUtil;

public class AdminListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response)
								  throws Exception {
		//[로그인 여부 체크 ]
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 X
			return "redirect:/member/loginForm.do";
		}
		
		//[관리자 여부 체크]
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth < 9) { //관리자 X
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//[관리자로 로그인한 경우]
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1"; //넘어갈 페이지가 없으면 1페이지로 간주
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		OrderDAO dao = OrderDAO.getInstance();
		int count = dao.getOrderCount(keyfield, keyword);
		
		//페이지 처리
		PageUtil page = new PageUtil(keyfield,keyword,Integer.parseInt(pageNum),count,20,10,"list.do");
		List<OrderVO> list = null;
		if(count > 0){
			list = dao.getListOrder(page.getStartRow(),page.getEndRow(),keyfield,keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/order/admin_list.jsp";
	}

}
