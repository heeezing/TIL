package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.item.dao.ItemDAO;
import kr.item.vo.ItemVO;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws Exception {
		//신규 상품 데이터 처리
		ItemDAO itemDao = ItemDAO.getInstance();                      
		List<ItemVO> itemList = itemDao.getListItem(1, 16, null, null, 1); //status를 1로 명시->표시 상품(2)만 반환
		
		//게시판 신규 데이터 처리
		BoardDAO boardDao = BoardDAO.getInstance();
		List<BoardVO> boardList = boardDao.getListBoard(1, 5, null, null);
		
		request.setAttribute("itemList", itemList);
		request.setAttribute("boardList", boardList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}

}
