package kr.boardlevel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.boardlevel.dao.BoardLevelDAO;
import kr.boardlevel.vo.BoardLevelVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int boardv_num = Integer.parseInt(multi.getParameter("boardv_num"));
		
		//전송된 이미지
		String image = multi.getFilesystemName("image");
		//기존 정보를 가져옴
		BoardLevelDAO dao = BoardLevelDAO.getInstance();
		BoardLevelVO db_board = dao.getBoard(boardv_num);
		
		//로그인한 회원번호와 글 작성자의 회원번호가 다를 경우
		if(user_num != db_board.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//전송받은 데이터를 자바빈에 저장
		BoardLevelVO board = new BoardLevelVO();
		board.setBoardv_num(boardv_num);
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		board.setIp(request.getRemoteAddr());
		board.setImage(image);
		dao.updateBoard(board);
		
		//새 이미지로 교체할 경우 원래 이미지는 제거
		if(image != null) {
			FileUtil.removeFile(request, db_board.getImage());
		}
		
		request.setAttribute("notice_msg", "정상적으로 수정되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/boardLevel/content.do?boardv_num="+boardv_num);
		
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}

}
