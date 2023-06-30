package kr.boardlevel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.boardlevel.dao.BoardLevelDAO;
import kr.boardlevel.vo.BoardLevelVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		int boardv_num = Integer.parseInt(request.getParameter("boardv_num"));
		BoardLevelDAO dao = BoardLevelDAO.getInstance();
		BoardLevelVO board = dao.getBoard(boardv_num);
		
		//큰 따옴표 처리(수정폼의 input 태그만 명시)
		board.setSubject(StringUtil.parseQuot(board.getSubject()));
		request.setAttribute("board", board);
		
		return "/WEB-INF/views/boardLevel/updateForm.jsp";
	}
}
