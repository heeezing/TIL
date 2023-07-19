package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.FileUtil;

@Controller
public class MemberController {
    //로그 대상 지정
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
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
		logger.debug("<<아이디 중복 체크>> : " + id);
		
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
	      logger.debug("<<회원가입>> : " + memberVO);
		
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
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {
		logger.debug("<<회원로그인>> : " + memberVO);
		
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
				//===자동 로그인 체크 시작===//
				
				//===자동 로그인 체크 끝===//
				
				//로그인 처리
				session.setAttribute("user", member); //세션에 통째로 저장
				logger.debug("<<인증 성공>>");
				logger.debug("<<id>> : " + member.getId());
				logger.debug("<<auth>> : " + member.getAuth());
				logger.debug("<<au_id>> : " + member.getAu_id());
				
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
			logger.debug("<<인증 실패>>");
			
			return formLogin();
		}
	}
	
	
	/*======================
			로그아웃
	======================*/
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		//로그아웃 처리
		session.invalidate();
		
		//===자동 로그인 해제 시작===//
		
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
		//반환 받은 회원 정보를 request에 저장
		model.addAttribute("member", member);
		
		return "myPage"; //tiles설정 name
	}
	
	
	/*======================
		 프로필 사진 출력	
	======================*/
	
	//프로필 사진 출력 (로그인 전용)
	@RequestMapping("/member/photoView.do")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		logger.debug("<<photoView>> : " + user);
		
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
		logger.debug("<<회원정보수정>> : " + memberVO);
		
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
		logger.debug("<<비밀번호 변경 처리>> : " + memberVO);
		
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
		
		return "common/resultView";
	}
	
}
