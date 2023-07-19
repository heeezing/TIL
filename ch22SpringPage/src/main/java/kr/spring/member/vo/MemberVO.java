package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	private int mem_num;
	
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String id;
	
	private String nick_name;
	private int auth;
	private String auto; //자동 로그인할 때 필요
	private String au_id;
	
	@NotEmpty
	private String name;
	
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String passwd;
	
	@NotEmpty
	private String phone;
	
	@Email
	@NotEmpty
	private String email;
	
	@Size(min=5,max=5)
	private String zipcode;
	
	@NotEmpty
	private String address1;
	@NotEmpty
	private String address2;
	
	private byte[] photo;
	private String photo_name;
	private Date reg_date;
	private Date modify_date;
	
	//비밀번호 변경 시 현재 비밀번호를 저장하는 용도로 사용
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String now_passwd;
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPassword(String userPasswd) {
		if(auth > 1 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	//이미지를 byte[]로 변환 (BLOB처리)
	//주의) 폼에서 파일 업로드 파라미터네임은 반드시 upload로 지정해야 함. photo아님!!
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[] 변환
		setPhoto(upload.getBytes());
		//파일명 저장
		setPhoto_name(upload.getOriginalFilename());
	}
	
	
	
	
	
	
}
