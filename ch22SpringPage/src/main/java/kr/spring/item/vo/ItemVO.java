package kr.spring.item.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"photo1","photo2"})
public class ItemVO {
	private int item_num;
	@NotEmpty
	private String name;
	@Range(min=1,max=99999999)
	private int price;
	@Range(min=0,max=99999)
	private int quantity;
	private byte[] photo1;
	private String photo_name1;
	private byte[] photo2;
	private String photo_name2;
	@Range(min=0,max=99999999)
	private int delivery_limit;
	@Range(min=0,max=9999999)
	private int delivery_fee;
	@NotEmpty
	private String detail;
	private Date reg_date;
	private Date modify_date;
	private int status;
	
	//photo1 업로드 파일 처리 메서드 생성
	public void setUpload1(MultipartFile upload1) throws IOException{
		//MultipartFile -> byte[] 변환
		setPhoto1(upload1.getBytes());
		//파일명 구하기
		setPhoto_name1(upload1.getOriginalFilename());
	}
	
	//photo2 업로드 파일 처리 메서드 생성
	public void setUpload2(MultipartFile upload2) throws IOException{
		//MultipartFile -> byte[] 변환
		setPhoto2(upload2.getBytes());
		//파일명 구하기
		setPhoto_name2(upload2.getOriginalFilename());
	}
	
}
