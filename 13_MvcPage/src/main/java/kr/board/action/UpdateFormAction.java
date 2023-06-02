package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
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
		//로그인되지 않은 경우
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		//로그인되어 있을 경우
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(board_num);
		//로그인한 회원번호 = 작성자 회원번호 일치 여부 체크
		//불일치할 경우
		if(user_num != board.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//일치할 경우
		//큰 따옴표 처리 (수정폼의 input태그에서 오동작 방지)
		board.setTitle(StringUtil.parseQuot(board.getTitle()));
		request.setAttribute("board", board);		
		return "/WEB-INF/views/board/updateForm.jsp";
	}

}
