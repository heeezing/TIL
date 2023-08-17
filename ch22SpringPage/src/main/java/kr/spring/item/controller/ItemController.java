package kr.spring.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.item.service.ItemService;
import kr.spring.item.vo.ItemVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	/*======================
			상품 목록
	======================*/		
	
	@RequestMapping("/item/list.do")
	public ModelAndView getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								String keyfield, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		//status에 1을 넣어 표시 상품(2)만 보이게 처리
		map.put("status", 1);
		
		//전체or검색 레코드 수 
		int count = itemService.selectItemCount(map);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,24,10,"list.do");
		List<ItemVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = itemService.selectItemList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("itemList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	
	
	/*======================
			상품 상세
	======================*/	
	
	@RequestMapping("/item/detail.do")
	public String detail(@RequestParam int item_num, Model model) {
		log.debug("<<item_num>> : " + item_num);
		
		ItemVO itemVO = itemService.selectItem(item_num);
		model.addAttribute("item",itemVO);
		
		return "itemView";
	}
	
	
	//이미지 출력
	@RequestMapping("/item/imageView.do")
	public ModelAndView viewImage(@RequestParam int item_num, 
								  @RequestParam int item_type /*사진1or사진2 여부*/) {
		ItemVO itemVO = itemService.selectItem(item_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(item_type==1) { //photo1
			mav.addObject("imageFile", itemVO.getPhoto1());
			mav.addObject("filename", itemVO.getPhoto_name1());
		}else if(item_type==2) {
			mav.addObject("imageFile", itemVO.getPhoto2());
			mav.addObject("filename", itemVO.getPhoto_name2());
		}
		
		return mav;
	}
}
