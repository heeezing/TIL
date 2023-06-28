package kr.boardlevel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.boardlevel.dao.BoardLevelDAO;
import kr.boardlevel.vo.BoardLevelVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
			
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		BoardLevelVO board = new BoardLevelVO();
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		board.setParent_num(Integer.parseInt(multi.getParameter("parent_num")));
		board.setDepth(Integer.parseInt(multi.getParameter("depth")));
		board.setIp(request.getRemoteAddr());
		board.setImage(multi.getFilesystemName("image"));
		board.setMem_num(user_num);
		
		BoardLevelDAO dao = BoardLevelDAO.getInstance();
		dao.insertBoard(board);
		
		return "/WEB-INF/views/boardLevel/write.jsp";
	}

}
