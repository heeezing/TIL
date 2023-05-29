package kr.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.product.dao.BoardDAO;
import kr.product.vo.BoardVO;

public class WriteAction implements Action{
	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)을 생성하고 전송된 데이터를 반환받아서 자바빈에 저장
		BoardVO boardVO = new BoardVO();
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setPrice(Integer.parseInt(request.getParameter("price")));
		boardVO.setStock(Integer.parseInt(request.getParameter("stock")));
		boardVO.setOrigin(request.getParameter("origin"));
		
		//BoardDAO 객체 생성
		BoardDAO dao = BoardDAO.getInstance();
		dao.insert(boardVO);
		
		//JSP 경로 반환
		return "/WEB-INF/views/write.jsp";
	}
}