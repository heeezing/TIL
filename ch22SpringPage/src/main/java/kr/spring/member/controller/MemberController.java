package kr.spring.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.service.OrderService;
import kr.spring.order.vo.OrderVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderService;
	
	/*======================
	       자바빈 초기화
	======================*/
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	
	
	/*======================
			회원 가입
	======================*/
	
	//아이디 중복 체크
	@RequestMapping("/member/confirmId.do")
	@ResponseBody //제이슨 문자열로 만듬 - ajax통신을 위해 (Map or 자바빈을 넘기면 됨)
	public Map<String,String> confirmId(@RequestParam String id){
		log.debug("<<아이디 중복 체크>> : " + id);
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		MemberVO member = memberService.selectCheckMember(id);
		if(member!=null) {
			//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}else {
			if(!Pattern.matches("^[A-Za-z0-9]{4,12}$", id)) {
				//패턴 불일치
				mapAjax.put("result","notMatchPattern");
			}else {
				//패턴 일치 + 아이디 미중복
				mapAjax.put("result", "idNotFound");
			}
		}
		
		return mapAjax;
	}
	
	
	//회원가입 폼 호출
	@GetMapping("/member/registerUser.do")
	public String form() {
		return "memberRegister";
	}
	
	//회원가입 처리
	@PostMapping("/member/registerUser.do")	//데이터 세팅(notice.jsp보여주기 위해)하기 위해 model에 넣음
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model) {
	      log.debug("<<회원가입>> : " + memberVO);
		
		//유효성 체크 결과, 오류가 있을 시 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//정상 처리 시 - 회원 가입 
		memberService.insertMember(memberVO);
		
		model.addAttribute("accessMsg", "회원가입이 완료되었습니다.");
		
		return "common/notice";
	}
	
	
	/*======================
			회원 로그인
	======================*/
	
	//로그인 폼
	@GetMapping("/member/login.do")
	public String formLogin() {
		return "memberLogin"; //member.xml의 definition name
	}
	
	//로그인 처리
	@PostMapping("/member/login.do")
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, 
							  HttpSession session, Model model, HttpServletResponse response) {
		log.debug("<<회원로그인>> : " + memberVO);
		
		//id, passwd 필드만 유효성 체크. 오류 있을 시 폼 호출
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		//로그인 체크 (id,passwd 일치 여부 체크)
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(memberVO.getId());
			boolean check = false;
			
			if(member != null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPassword(memberVO.getPasswd());
			}
			
			//인증 성공
			if(check) { 
				//======자동 로그인 체크 시작======// 											//자동로그인 체크했을 경우:on
				boolean autoLogin = memberVO.getAuto() != null && memberVO.getAuto().equals("on");
				if(autoLogin) {
					//자동 로그인 체크를 한 경우
					String au_id = member.getAu_id();
					if(au_id == null) {
						//자동 로그인 체크 식별값 생성
						au_id = UUID.randomUUID().toString();
						log.debug("<<au_id>> : " + au_id);
						memberService.updateAu_id(au_id, member.getMem_num());
					}
					
					//쿠키 생성
					Cookie auto_cookie = new Cookie("au-log", au_id);
					auto_cookie.setMaxAge(60*60*24*7); //쿠키 유효 시간 설정 (1주)
					auto_cookie.setPath("/"); //쿠키 경로 지정 (/밑으로는 다 사용할 수 있도록 설정)
					//쿠키 전달
					response.addCookie(auto_cookie);
				}
				//======자동 로그인 체크 끝======//
				
				//로그인 처리
				session.setAttribute("user", member); //세션에 통째로 저장
				log.debug("<<인증 성공>>");
				log.debug("<<id>> : " + member.getId());
				log.debug("<<auth>> : " + member.getAuth());
				log.debug("<<au_id>> : " + member.getAu_id());
				
				if(member.getAuth() == 9) {
					return "redirect:/main/admin.do";
				}else {
					return "redirect:/main/main.do";
				}
			}
			
			//인증 실패
			throw new AuthCheckException();
			
		}catch(AuthCheckException e) {
			//인증 실패 - 로그인 폼 호출
			if(member!=null && member.getAuth()==1) {
				//정지 회원 메시지 표시
				result.reject("noAuthority");
			}else {
				result.reject("invalidIdOrPassword");
			}
			log.debug("<<인증 실패>>");
			
			return formLogin();
		}
	}
	
	
	/*======================
			로그아웃
	======================*/
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session, HttpServletResponse response) {
		//로그아웃 처리
		session.invalidate();
		
		//===자동 로그인 해제 시작===//
		//클라이언트 쿠키 처리
		Cookie auto_cookie = new Cookie("au-log","");
		auto_cookie.setMaxAge(0); //덮어씌움으로서 쿠키 삭제
		auto_cookie.setPath("/"); //삭제할 때도 경로 지정해야 함
		
		response.addCookie(auto_cookie);
		//===자동 로그인 해제 끝===//
		
		return "redirect:/main/main.do";
	}
	
	
	/*======================
			MY페이지
	======================*/
	
	@RequestMapping("/member/myPage.do")
	public String myPage(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		//회원 정보 반환
		MemberVO member = memberService.selectMember(user.getMem_num());
		//주문 정보 반환
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("mem_num", user.getMem_num());
		//최신 주문 정보 10건만 표시
		map.put("start", 1);
		map.put("end", 10);
		List<OrderVO> orderList = orderService.selectListOrderByMem_num(map);
		
		//반환 받은 정보를 request에 저장
		model.addAttribute("member", member);
		model.addAttribute("orderList", orderList);
		
		return "myPage"; //tiles설정 name
	}
	
	
	/*======================
		 프로필 사진 출력	
	======================*/
	
	//프로필 사진 출력 (로그인 전용)
	@RequestMapping("/member/photoView.do")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		log.debug("<<photoView>> : " + user);
		
		if(user == null) { //로그인 X
			//기본 이미지 읽기													//상대 경로를 넣으면 절대 경로를 구해줌
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename", "face.png");
		}else { //로그인 O
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			viewProfile(memberVO,request,model);
		}
		return "imageView";
	}
	
	//프로필 사진 출력 (회원 번호 지정)
	@RequestMapping("/member/viewProfile.do")
	public String getProfileByMem_num(@RequestParam int mem_num,
									  HttpServletRequest request,
									  Model model) {
		MemberVO memberVO = memberService.selectMember(mem_num);
		viewProfile(memberVO, request, model); //만들어놓은 공통 코드에 넘겨주기만 하면 된다.
		
		return "imageView";
	}
	
	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, Model model) {
		if(memberVO==null || memberVO.getPhoto_name()==null) {
			//기본 이미지 읽기													//상대 경로를 넣으면 절대 경로를 구해줌
			byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
			model.addAttribute("imageFile", readbyte);
			model.addAttribute("filename", "face.png");
		}else { //업로드한 프로필 사진이 있는 경우
			model.addAttribute("imageFile", memberVO.getPhoto());
			model.addAttribute("filename", memberVO.getPhoto_name());
		}
	}
	
	
	/*======================
	 	프로필 사진 업데이트	
	======================*/
	
	@RequestMapping("/member/updateMyPhoto.do")
	@ResponseBody
	public Map<String,String> updateProfile(MemberVO memberVO, HttpSession session){
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null){
			mapAjax.put("result", "logout");
		}else {
			memberVO.setMem_num(user.getMem_num());
			memberService.updateProfile(memberVO);
			mapAjax.put("result", "success");
		}
		
		return mapAjax;
	}
	
	
	/*======================
 		  회원 정보 수정	
	======================*/
	
	//수정 폼 호출
	@GetMapping("/member/update.do")
	public String formUpdate(HttpSession session, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		model.addAttribute("memberVO", memberVO); //request에 저장
		
		return "memberModify";
	}
	
	//수정 처리
	@PostMapping("/member/update.do")
	public String submitUpdate(@Valid MemberVO memberVO,
								BindingResult result,
								HttpSession session) {
		//먼저 로그 처리로 입력 정보 확인! (toString 재정의해놨기때문에 O)
		log.debug("<<회원정보수정>> : " + memberVO);
		
		//유효성 체크 결과 오류 발생 시 폼을 다시 호출
		if(result.hasErrors()) {
			return "memberModify";
		}
		
		//통재로 user에 저장해놨기 때문에 hidden값으로 안 넘겨도 세션에서 꺼내 사용 가능.
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		//회원정보수정
		memberService.updateMember(memberVO);
		
		//세션에 저장된 정보 변경
		user.setNick_name(memberVO.getNick_name());
		user.setEmail(memberVO.getEmail());
		
		return "redirect:/member/myPage.do";
	}
	
	
	/*======================
	  	   비밀번호 변경	
	======================*/
	
	//폼 호출
	@GetMapping("/member/changePassword.do")
	public String formChangePassword() {
		return "memberChangePassword";
	}
	
	//전송된 데이터 처리
	@PostMapping("/member/changePassword.do")
	public String submitChangePassword(@Valid MemberVO memberVO, 
										BindingResult result,
										HttpSession session,
										HttpServletRequest request,
										Model model) {
		log.debug("<<비밀번호 변경 처리>> : " + memberVO);
		
		//now_passwd, passwd 유효성 체크 결과 오류가 있을 시 폼을 다시 호출
		if(result.hasFieldErrors("now_passwd") || result.hasFieldErrors("passwd")) {
			return formChangePassword();
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		MemberVO db_member = memberService.selectMember(memberVO.getMem_num());
		//DB에 등록되어있는 비밀번호와 폼에서 새로 전송(입력)한 비밀번호가 일치한지 체크
		if(!db_member.getPasswd().equals(memberVO.getNow_passwd())) {
			//불일치
			result.rejectValue("now_passwd", "invalidPassword");
			return formChangePassword();
		}
		
		//일치 시 - 비밀번호 변경 
		memberService.updatePassword(memberVO);
		
		//설정되어 있는 자동 로그인 기능 해제 (모든 브라우저에서 해제됨)
		memberService.deleteAu_id(memberVO.getMem_num());
		
		//View에 표시할 메시지 처리
		model.addAttribute("message", "비밀번호 변경 완료(*재접속 시 설정되어 있는 자동 로그인 기능 해제)");
		model.addAttribute("url", request.getContextPath() + "/member/myPage.do");
		
		return "common/resultView"; //jsp 호출(폴더/파일명 형태)
	}
	
	
	/*======================
	   		회원 탈퇴	
	======================*/
	
	//탈퇴 폼 호출
	@GetMapping("/member/delete.do")
	public String formDelete() {
		return "memberDelete"; //tiles 호출
	}
	
	//전송된 데이터 처리
	@PostMapping("/member/delete.do")
	public String submitDelete(@Valid MemberVO memberVO,
								BindingResult result,
								HttpSession session,
								Model model) {
		log.debug("<<회원탈퇴>> : " + memberVO);
		
		//id,passwd 유효성 체크 결과 오류 발생 시 폼을 다시 호출
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formDelete();
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO db_member = memberService.selectMember(user.getMem_num());
		boolean check = false;
		//아이디, 비밀번호 일치 여부 체크
		try {
			if(db_member!=null && db_member.getId().equals(memberVO.getId())) {
				//비밀번호 일치 여부 체크
				check = db_member.isCheckedPassword(memberVO.getPasswd());
			}
			if(check) {
				//인증 성공, 회원 정보 삭제
				memberService.deleteMember(user.getMem_num());
				//로그아웃 처리
				session.invalidate();
				//문구 데이터 저장
				model.addAttribute("accessMsg", "회원탈퇴를 완료했습니다.");
				//jsp 호출
				return "common/notice";
			}
			//인증 실패
			throw new AuthCheckException();
		}catch(AuthCheckException e){
			result.reject("invalidIdOrPassword");
			return formDelete();
		}
		
	}
	
}
