package kr.spring.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.item.service.ItemService;
import kr.spring.item.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	/*======================
			스낵 상세
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
