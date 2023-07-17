package kr.spring.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
}
