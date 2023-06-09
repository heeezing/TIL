package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

public class MyPageAction implements Action {

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws Exception {
		///로그인 여부 체크
		HttpSession session = request.getSession(); 
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		//로그인이 안 되어있는 경우
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 되어 있는 경우
		MemberDAO dao = MemberDAO.getInstance();
		//회원 정보
		MemberVO member = dao.getMember(user_num);
		
		BoardDAO boardDao = BoardDAO.getInstance();
		//게시판 글
		List<BoardVO> boardList = boardDao.getListBoardFav(1, 5, user_num);
		//주문 정보
		OrderDAO orderDao = OrderDAO.getInstance();
		List<OrderVO> orderList = orderDao.getListOrderByMem_num(1, 5, null, null, user_num);
		
		request.setAttribute("member", member);
		request.setAttribute("boardList", boardList);
		request.setAttribute("orderList", orderList);
		
		//JSP 경로 반환		
		return "/WEB-INF/views/member/myPage.jsp";
	}

}
