package kr.boardlevel.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.boardlevel.dao.BoardLevelDAO;
import kr.boardlevel.vo.BoardLevelVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, 
						  HttpServletResponse response)
								  throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 X
			mapAjax.put("result", "logout");
		}else { //로그인 O
			int boardv_num = Integer.parseInt(request.getParameter("boardv_num"));
			BoardLevelDAO dao = BoardLevelDAO.getInstance();
			BoardLevelVO db_board = dao.getBoard(boardv_num);
			if(user_num != db_board.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else { //로그인 회원 번호 = 작성자 회원 번호 일치
				dao.deleteFile(boardv_num); //DB에서 파일명 삭제
				FileUtil.removeFile(request, db_board.getImage()); //실제 업로드폴더에서 파일 삭제
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
