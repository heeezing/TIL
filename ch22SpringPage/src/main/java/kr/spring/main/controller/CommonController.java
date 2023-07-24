package kr.spring.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonController {
	@RequestMapping("/common/imageUploader.do")
	@ResponseBody
	public Map<String,Object> uploadImage(MultipartFile upload, 
										  HttpSession session, 
										  HttpServletRequest request,
										  HttpServletResponse response) throws Exception{
		//업로드할 폴더 경로 (상대 경로를 통해 절대 경로를 구함)
		String realFolder = session.getServletContext().getRealPath("/image_upload");
		//업로드할 파일 이름 (원본 파일명)
		String org_filename = upload.getOriginalFilename();
		//파일명이 겹치지 않게 파일명 앞에 현재 시간을 long타입으로 붙임 (저장 파일명)
		String str_filename = System.currentTimeMillis() + "_" + org_filename;
		
		log.debug("<<원본 파일명>> : " + org_filename);
		log.debug("<<저장 파일명>> : " + str_filename);
		
		//경로 정보 처리
		String sub_path; //중간 경로
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) { //로그인 X
			sub_path = "general";
		}else { //로그인 O
			sub_path = String.valueOf(user.getMem_num()); //mem_num을 중간 경로로 사용
		}
		
		//전체 경로 생성 (폴더경로\중간경로\파일명)
		String file_path = realFolder + "/" + sub_path + "/" + str_filename;
		log.debug("<<파일 경로>> : " + file_path);
		
		File f = new File(file_path);
		if(!f.exists()) { //파일 존재 X
			//mkdirs() : 상위 경로까지 생성
			f.mkdirs();
		}
		upload.transferTo(f);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("upload", true);
		map.put("url", request.getContextPath()+"/image_upload/"+sub_path+"/"+str_filename);
		
		return map;
	}
}
