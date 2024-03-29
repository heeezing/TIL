package kr.spring.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	/*======================
	  	   자바빈 초기화
	======================*/
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	

	/*======================
		  게시판 글 등록
	======================*/		
	
	//등록 폼
	@GetMapping("/board/write.do")
	public String form() {
		return "boardWrite"; //tiles 설정
	}
	
	//전송된 데이터 처리
	@PostMapping("/board/write.do")
	public String submit(@Valid BoardVO boardVO,BindingResult result,
						 HttpServletRequest request, HttpSession session, Model model) {
		log.debug("<<게시판 글쓰기>> : " + boardVO);
		
		//유효성 체크 결과 오류 발생 시 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//회원 번호 세팅 (좀 길지만 한 번만 쓸거라 따로 변수 안 만들고 진행)
		//세션에 저장해놓은 user를 MemberVO로 다운캐스팅, 거기서 mem_num을 가져와서 boardVO의 mem_num에 set
		boardVO.setMem_num(((MemberVO)session.getAttribute("user")).getMem_num());
		//IP 세팅
		boardVO.setIp(request.getRemoteAddr());
		//글 등록 처리
		boardService.insertBoard(boardVO);
		
		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+"/board/list.do");
		
		return "common/resultView"; //jsp 호출
	}
	
	
	/*======================
		   게시판 목록
	======================*/	
	
	@RequestMapping("/board/list.do")
	public ModelAndView getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								@RequestParam(value="order",defaultValue="1") int order,
								String keyfield, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//전체or검색 레코드 수
		int count = boardService.selectRowCount(map);
		log.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,
										 20,10,"list.do","&order="+order);
		List<BoardVO> list = null;
		if(count > 0) {
			map.put("order", order);
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = boardService.selectList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	
	/*======================
		   게시판 글 상세
	======================*/	
	
	@RequestMapping("/board/detail.do")
	public ModelAndView getDetail(@RequestParam int board_num) {
		log.debug("<<글 상세 - board_num>> : " + board_num);
		//해당 글의 조회수 증가
		boardService.updateHit(board_num);
		//글 상세
		BoardVO board = boardService.selectBoard(board_num);
		//제목에 태그를 허용하지 않음
		board.setTitle(StringUtil.useNoHtml(board.getTitle()));
		//CKEditor를 사용하지 않을 경우 내용에도 태그를 허용하지 않음
		//board.setContent(StringUtil.useBrNoHtml(board.getContent()));
								//뷰 이름	     속성명 	속성값
		return new ModelAndView("boardView","board",board);
	}
	
	
	/*======================
	   	  게시판 글 수정
	======================*/	
	
	//수정 폼 호출
	@GetMapping("/board/update.do")
	public String formUpdate(@RequestParam int board_num, Model model) {
		BoardVO boardVO = boardService.selectBoard(board_num);
		model.addAttribute("boardVO", boardVO);
		
		return "boardModify";
	}
	
	//전송된 데이터 처리
	@PostMapping("/board/update.do")
	public String submitUpdate(@Valid BoardVO boardVO,
								BindingResult result,
								HttpServletRequest request,
								Model model) {
		log.debug("<<글 수정 - BoardVO>> : " + boardVO);
		
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return "boardModify";
		}
		
		//ip 세팅
		boardVO.setIp(request.getRemoteAddr());
		//글 수정
		boardService.updateBoard(boardVO);
		//View에 표시할 메시지
		model.addAttribute("message", "글 수정 완료!");
		model.addAttribute("url", request.getContextPath()+"/board/detail.do?board_num="+boardVO.getBoard_num());
		
		return "common/resultView"; //jsp 호출
	}
		
	
	/*======================
	 	  게시판 글 삭제
	======================*/	
	
	@RequestMapping("/board/delete.do")
	public String submitDelete(@RequestParam int board_num) {
		log.debug("<<글 삭제 - board_num>> : " + board_num);
		
		//글 삭제
		boardService.deleteBoard(board_num);
		
		return "redirect:/board/list.do";
	}
	
	
	/*======================
	   	  게시판 글 좋아요
	======================*/	
	
	//부모글 좋아요 읽어오기
	@RequestMapping("/board/getFav.do")
	@ResponseBody //ajax통신 이용
	public Map<String,Object> getFav(BoardFavVO fav, HttpSession session){
		log.debug("<<게시판 좋아요 읽기>> : " + fav);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		//로그인 회원 정보 가져옴
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("status", "noFav");
		}else { //로그인 O
			//로그인된 회원 번호 세팅
			fav.setMem_num(user.getMem_num());
			
			BoardFavVO boardFav = boardService.selectFav(fav);
			if(boardFav != null) { //로그인 회원이 헤당 글에 좋아요 누른 데이터가 있는 경우
				mapJson.put("status", "yesFav");
			}else { //로그인 회원이 해당 좋아요 누른 데이터가 없는 경우
				mapJson.put("status", "noFav");
			}
		}
		mapJson.put("count", boardService.selectFavCount(fav.getBoard_num()));
		
		return mapJson;
	}
	
	//부모글 좋아요 등록&삭제
	@RequestMapping("/board/writeFav.do")
	@ResponseBody
	public Map<String,Object> writeFav(BoardFavVO fav, HttpSession session){
		log.debug("<<게시판 좋아요 등록/삭제 - BoardFavVO>> : " + fav);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		//로그인 회원 정보 가져옴
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			//로그인된 회원 번호 세팅
			fav.setMem_num(user.getMem_num());
			
			BoardFavVO boardFav = boardService.selectFav(fav);
			if(boardFav != null) { //로그인 회원이 헤당 글에 좋아요 누른 데이터가 있는 경우 -> 삭제
				boardService.deleteFav(boardFav.getFav_num());
				mapJson.put("result", "success");
				mapJson.put("status", "noFav");
			}else { //로그인 회원이 헤당 글에 좋아요 누른 데이터가 없는 경우 -> 등록
				boardService.insertFav(fav);
				mapJson.put("result", "success");
				mapJson.put("status", "yesFav");
			}
			mapJson.put("count", boardService.selectFavCount(fav.getBoard_num()));
		}
		
		return mapJson;
	}
	
	
	/*======================
		 	댓글 등록
	======================*/
	
	@RequestMapping("/board/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(BoardReplyVO boardReplyVO,
										 HttpSession session,
										 HttpServletRequest request){
		log.debug("<<댓글 등록>> : " + boardReplyVO);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else { //로그인 O
			//회원번호 등록
			boardReplyVO.setMem_num(user.getMem_num());
			//ip 등록
			boardReplyVO.setRe_ip(request.getRemoteAddr());
			//댓글 등록
			boardService.insertReply(boardReplyVO);
			mapJson.put("result", "success");
		}
		
		return mapJson;
	}

	
	/*======================
	 		댓글 목록
	======================*/
	
	@RequestMapping("/board/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
									  @RequestParam(value="rowCount",defaultValue="10") int rowCount,
									  @RequestParam int board_num,
									  HttpSession session){
		log.debug("<<currentPage>> : " + currentPage);
		log.debug("<<board_num>> : " + board_num);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("board_num", board_num);
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//전체 레코드 수
		int count = boardService.selectRowCountReply(map);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,1,null);
		
		List<BoardReplyVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			list = boardService.selectListReply(map);
		}else {
			list = Collections.emptyList(); //빈 배열을 만들어 전달
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("list", list);
		
		//로그인한 회원 정보 세팅
		if(user != null) {
			mapJson.put("user_num",user.getMem_num());
		}
		
		return mapJson;
	}
	
	
	/*======================
			댓글 삭제
	======================*/
	
	@RequestMapping("/board/deleteReply.do")
	@ResponseBody //성공했는지 실패했는지 json문자열로 보여주기 위해 설정
	public Map<String,String> deleteReply(@RequestParam int re_num,
										  HttpSession session){
		log.debug("<<re_num>> : " + re_num);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		BoardReplyVO db_reply = boardService.selectReply(re_num); //작성자 회원 번호를 알기 위해서
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else if(user != null && user.getMem_num() == db_reply.getMem_num()) { //작성자 본인일 시
			boardService.deleteReply(re_num);
			mapJson.put("result", "success");
		}else { //로그인은 되어있으나 작성자가 아닐 시
			mapJson.put("result", "wrongAccess");
		}
		
		return mapJson;
	}
	
	
	/*======================
			댓글 수정
	======================*/
	
	@RequestMapping("/board/updateReply.do")
	@ResponseBody
	public Map<String,String> modifyReply(BoardReplyVO boardReplyVO,
										  HttpSession session,
										  HttpServletRequest request){
		log.debug("<<BoardReplyVO>> : " + boardReplyVO);
		
		Map<String,String> mapJson = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		BoardReplyVO db_reply = boardService.selectReply(boardReplyVO.getRe_num());
		if(user == null) { //로그인 X
			mapJson.put("result", "logout");
		}else if(user != null && user.getMem_num() == db_reply.getMem_num()) { //작성자 본인일 시
			//ip 등록
			boardReplyVO.setRe_ip(request.getRemoteAddr());
			//댓글 수정 처리
			boardService.updateReply(boardReplyVO);
			mapJson.put("result", "success");
		}else { //로그인은 되어있으나 작성자가 아닐 시
			mapJson.put("result", "wrongAccess");
		}
		
		return mapJson;
	}
	
}
