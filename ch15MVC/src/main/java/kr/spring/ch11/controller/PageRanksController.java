package kr.spring.ch11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch11.vo.PageRank;

@Controller
public class PageRanksController {
	@RequestMapping("/pageRanksExcel.do")
	public ModelAndView handle() {
		List<PageRank> pageRanks = new ArrayList<PageRank>();
		pageRanks.add(new PageRank(1, "/bbs/life/list.do"));
		pageRanks.add(new PageRank(2, "/bbs/job/list.do"));
		pageRanks.add(new PageRank(3, "/bbs/money/list.do"));
								//뷰 이름			속성명		속성값
		return new ModelAndView("pageRanks", "pageRanks", pageRanks);
	}
	
	@RequestMapping("/pageJsonReport.do")
	@ResponseBody //응답하는 몸체를 자동으로(json형식으로) 생성해준다. 데이터가 list면 대괄호, 객체면 중괄호 형식으로 반환
	public List<PageRank> jsonReport() {
		List<PageRank> pageRanks = new ArrayList<PageRank>();
		pageRanks.add(new PageRank(1, "/mvc/board/list.do"));
		pageRanks.add(new PageRank(2, "/mvc/notice/list.do"));
		pageRanks.add(new PageRank(3, "/mvc/event/list.do"));
		
		return pageRanks;
	}
}
