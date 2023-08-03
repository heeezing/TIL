package kr.spring.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.item.service.ItemService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	//이미지 출력
	@RequestMapping("/item/imageView.do")
	public ModelAndView viewImage(@RequestParam int item_num, 
								  @RequestParam int item_type) {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}
