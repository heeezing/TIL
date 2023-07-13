package kr.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//로그 처리 (로그 대상 지정)
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	
	//글 등록 폼
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	
	
	//글 등록 처리
	@PostMapping("/insert.do")
	public String submit(@Valid BoardVO boardVO, BindingResult result) {
		logger.debug("<<BoardVO>> : " + boardVO); //데이터가 전달됐는지 확인용
		
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//정상 처리 시 - 글 등록
		boardService.insertBoard(boardVO);
		return "redirect:/list.do";
	}
	
	
	//글 목록
	@RequestMapping("/list.do")
	public ModelAndView getList(@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
		//총 레코드 수
		int count = boardService.selectBoardCount();
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage, count, 10, 10, "list.do");
		
		//목록 호출
		List<BoardVO> list = null;
		if(count > 0) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = boardService.selectBoardList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 설정
		mav.setViewName("selectList");
		//데이터 저장
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	
	//글 상세
	@RequestMapping("/detail.do")
	public ModelAndView detail(@RequestParam int num) {
		BoardVO board = boardService.selectBoard(num);
		//생성자로 반환			//  뷰 이름       속성명    속성값
		return new ModelAndView("selectDetail", "board", board);
	}
	
	
	//글 수정 폼
	@GetMapping("/update.do")
	public String formUpdate(@RequestParam int num, Model model) {
		//글 번호를 받아 한 건의 레코드를 request에 전달
		model.addAttribute("boardVO", boardService.selectBoard(num));
		
		return "updateForm";
	}
	
	
	//글 수정 처리
	@PostMapping("/update.do")
	public String submitUpdate(@Valid BoardVO boardVO, BindingResult result) {
		//유효성 체크 결과 오류 있으면 폼 다시 호출
		if(result.hasErrors()) {
			return "updateForm";
		}
		
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.selectBoard(boardVO.getNum());
		//비밀번호가 일치하지 않을 시
		if(!db_board.getPasswd().equals(boardVO.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "updateForm";
		}
		
		//정상 처리 시 - 글 수정
		boardService.updateBoard(boardVO);
		return "redirect:/list.do";
	}
	
	
	//글 삭제 폼
	@GetMapping("/delete.do")
	//int num으로 인자를 받아도되지만, 이 경우 비밀번호 유효성체크를 하기때문에 vo를 받았다!
	public String formDelete(BoardVO vo) {
					
		return "deleteForm";
	}
	
	
	//글 삭제 처리
	@PostMapping("/delete.do")
	public String submitDelete(@Valid BoardVO boardVO, BindingResult result) {
		//(비밀번호만)유효성 체크 결과 오류 있을 시 폼 다시 호출
		if(result.hasFieldErrors("passwd")) {
			return "deleteForm";
		}
		
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.selectBoard(boardVO.getNum());
		//비밀번호가 일치하지 않을 시
		if(!db_board.getPasswd().equals(boardVO.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "deleteForm";
		}
		
		//정상 처리 시 - 글 삭제
		boardService.deleteBoard(boardVO.getNum());
		return "redirect:/list.do";
	}
	
}
