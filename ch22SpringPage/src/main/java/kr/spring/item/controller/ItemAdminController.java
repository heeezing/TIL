package kr.spring.item.controller;

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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.item.service.ItemService;
import kr.spring.item.vo.ItemVO;
import kr.spring.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ItemAdminController {
	@Autowired
	private ItemService itemService;
	
	//자바빈 초기화
	@ModelAttribute
	public ItemVO initCommand() {
		return new ItemVO();
	}
	
	/*======================
	    	상품 등록
	======================*/
	
	//폼 호출
	@GetMapping("/item/admin_write.do")
	public String form() {
		return "itemAdminWrite";
	}
	
	//전송된 데이터 처리
	@PostMapping("/item/admin_write.do")
	public String submit(@Valid ItemVO vo, BindingResult result, Model model,
						 HttpServletRequest request, HttpSession session) {
		log.debug("<<상품 등록>> : " + vo);
		
		//[상품 이미지 유효성 체크]
		//1.업로드를 안 했을 경우 (null)
		//MultipartFile -> byte[]로 변환한 경우 파일을 업로드하지 않으면 byte[]는 생성되지만 length는 0임
		if(vo.getPhoto1().length == 0) {
			result.rejectValue("photo1","required");
		}
		if(vo.getPhoto2().length == 0) {
			result.rejectValue("photo2","required");
		}
		//2.지정 사이즈 용량보다 클 경우
		if(vo.getPhoto1().length >= 5*1024*1024) { //5MB
							   //필드명,		 에러 코드,	    에러 문구에 전달할 값,  기본 에러 문구
			result.rejectValue("photo1","limitUploadSize", new Object[] {"5MB"}, null);
		}
		if(vo.getPhoto2().length >= 5*1024*1024) {
			result.rejectValue("photo2","limitUploadSize", new Object[] {"5MB"}, null);
		}
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//상품 등록 처리
		itemService.insertItem(vo);
		
		//View에 표시할 메시지
		model.addAttribute("message", "상품 등록이 완료되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/item/admin_list.do");
		
		return "common/resultView";
	}
	
	
	/*======================
			상품 목록
	======================*/
	
	@RequestMapping("/item/admin_list.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
								String keyfield, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		//status가 0이면 미표시(1), 표시(2) 모두 체크
		map.put("status",0);
		
		//전체or검색 레코드 수 
		int count = itemService.selectItemCount(map);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,20,10,"admin_list.do");
		
		List<ItemVO> list = null;
		if(count > 0) {
			map.put("start", page.getStartRow());
			map.put("end", page.getEndRow());
			
			list = itemService.selectItemList(map);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	
	
	/*======================
	    	상품 수정
	======================*/
	
	//폼 호출
	@GetMapping("/item/admin_modify.do")
	public String formUpdate(@RequestParam int item_num, Model model) {
		ItemVO itemVO = (ItemVO)itemService.selectItem(item_num);
		model.addAttribute("itemVO", itemVO);
		
		return "itemAdminModify";
	}
	
	//전송된 데이터 처리
	@PostMapping("/item/admin_modify.do")
	public String submitUpdate(@Valid ItemVO vo, BindingResult result, 
								Model model, HttpServletRequest request) {
		log.debug("<<ItemVO>> : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			//폼 다시 호출 시 사진 파일명도 같이 다시 보여주기 위해 저장
			ItemVO db_item = itemService.selectItem(vo.getItem_num());
			vo.setPhoto_name1(db_item.getPhoto_name1());
			vo.setPhoto_name2(db_item.getPhoto_name2());
			return "itemAdminModify";
		}
		
		//수정 처리
		itemService.updateItem(vo);
		
		model.addAttribute("message", "상품 정보 수정을 완료했습니다.");
		model.addAttribute("url", request.getContextPath()+"/item/admin_modify.do?item_num="+vo.getItem_num());
		
		return "common/resultView";
	}
	
	
	
	
	
}
