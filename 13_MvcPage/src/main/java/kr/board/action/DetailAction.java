package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//글번호
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardDAO dao = BoardDAO.getInstance();
		//조회수 증가
		dao.updateReadcount(board_num);
		
		BoardVO board = dao.getBoard(board_num);
		
		//제목 부분 - HTML 태그를 허용하지 않게 설정 (유틸 클래스의 메서드 사용)
		board.setTitle(StringUtil.useNoHtml(board.getTitle()));
		//내용 부분 - HTML 태그를 허용하지 않으면서 줄바꿈 처리하도록 설정
		board.setContent(StringUtil.useBrNoHtml(board.getContent()));
		
		request.setAttribute("board", board);
		
		//JSP경로 반환		
		return "/WEB-INF/views/board/detail.jsp";
	}
}
