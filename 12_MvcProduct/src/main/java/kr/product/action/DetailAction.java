package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.BoardDAO;
import kr.product.vo.BoardVO;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response)
								  throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO boardVO = dao.getBoard(num);
		request.setAttribute("boardVO", boardVO);
		//JSP경로 반환
		return "/WEB-INF/views/detail.jsp";
	}

}
