package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//글번호
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardDAO dao = BoardDAO.getInstance();
		//조회수 증가
		
		BoardVO board = dao.getBoard(board_num);
		
		request.setAttribute("board", board);
		
		//JSP경로 반환		
		return "/WEB-INF/views/board/detail.jsp";
	}
}
