package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.BoardDAO;
import kr.product.vo.BoardVO;

public class ModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//자바빈을 생성하고 전송된 데이터를 반환받아서 자바빈에 저장
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Integer.parseInt(request.getParameter("num")));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setPrice(Integer.parseInt(request.getParameter("price")));
		boardVO.setStock(Integer.parseInt(request.getParameter("stock")));
		boardVO.setOrigin(request.getParameter("origin"));
		
		BoardDAO dao = BoardDAO.getInstance();
		//비밀번호 인증을 위해 한 건의 레코드를 자바빈에 담아서 반환
		BoardVO db_board = dao.getBoard(boardVO.getNum());
		boolean check = false;
		if(db_board!=null) {
			//비밀번호 일치 여부 체크
			check = db_board.isCheckedPassword(boardVO.getPasswd());
		}
		if(check) { //비밀번호 일치
			dao.update(boardVO);
			//상세 페이지로 이동하기 위해 글번호 저장
			request.setAttribute("num", boardVO.getNum());
		}
		
		//UI 처리를 위해서 check 저장 (modify.jsp에서 활용)
		request.setAttribute("check", check);
		
		//JSP 경로 반환
		return "/WEB-INF/views/modify.jsp";

	}

}
