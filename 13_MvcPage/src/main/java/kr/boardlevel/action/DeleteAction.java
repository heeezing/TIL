package kr.boardlevel.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.boardlevel.dao.BoardLevelDAO;
import kr.boardlevel.vo.BoardLevelVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		int boardv_num = Integer.parseInt(request.getParameter("boardv_num"));
		BoardLevelDAO dao = BoardLevelDAO.getInstance();
		BoardLevelVO db_board = dao.getBoard(boardv_num);
		
		//로그인한 회원번호와 작성자 회원번호가 다를 경우 
		if(user_num != db_board.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//삭제할 파일명을 반환받음
		List<String> list = dao.getImageDeleted(boardv_num);
		
		//DB에서 데이터 삭제
		dao.deleteBoard(boardv_num);
		
		//파일 삭제
		for(String image : list) {
			FileUtil.removeFile(request, image); //이미지 삭제
		}
		
		request.setAttribute("notice_msg", "정상적으로 삭제되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/boardLevel/list.do");
		
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}
}
