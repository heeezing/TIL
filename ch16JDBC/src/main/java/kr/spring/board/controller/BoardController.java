package kr.spring.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.util.PagingUtil;

@Controller //자동 스캔 대상이 되게 하는 역할도 함
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//로그 처리 (로그 대상 지정)
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	/*
	 * [로그 레벨]
	 * FATAL : 가장 심각한 오류
	 * ERROR : 일반적인 오류
	 * WARN : 주의를 요하는 경우(오류x, 경고o)
	 * INFO : 런 타임 시 관심 있는 내용
	 * DEBUG : 시스템 흐름과 관련된 상세 정보
	 * TRACE : 가장 상세한 정보
	 */
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	
	@PostMapping("/insert.do")
	public String submit(@Valid BoardVO boardVO, BindingResult result) {
		logger.debug("<<BoardVO>> : " + boardVO);
		
		//유효성 체크 결과 오류가 발생하면 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//정상 처리 시 - 글 등록
		boardService.insertBoard(boardVO);
		return "redirect:/list.do";
	}
	
	@RequestMapping("/list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage) {
		
		int count = boardService.getBoardCount();
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count,10,10,"list.do");
		
		List<BoardVO> list = null;
		if(count > 0) {
			list = boardService.getBoardList(page.getStartRow(), page.getEndRow());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	@RequestMapping("/detail.do")
	public ModelAndView detail(@RequestParam int num) {
		logger.debug("<<num>> : " + num);
		BoardVO board = boardService.getBoard(num);
								//  뷰 이름		속성명   속성값
		return new ModelAndView("selectDetail","board",board);
	}
	
	@GetMapping("/update.do")
	public String formUpdate(@RequestParam int num, Model model) {
		//한 건의 데이터 읽어오기
		BoardVO vo = boardService.getBoard(num);
		//데이터 세팅 
		//ModelAndView(데이터,뷰 정보 저장) - 생성자로 직접 입력 / Model(데이터 저장) - 인자에 쓰면 컨테이너가 알아서 생성해줌
		model.addAttribute("boardVO", vo);
		
		return "updateForm";
	}
}
