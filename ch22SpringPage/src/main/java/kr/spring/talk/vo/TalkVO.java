package kr.spring.talk.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TalkVO {
	private int talk_num; //채팅 번호
	private int talkroom_num; //수신 그룹
	private int mem_num; //발신자
	private String message; //메시지
	private String chat_date; //채팅 날짜
	
	private int read_count; //채팅 메시지를 읽지 않은 회원 수
	private String id; //채팅 회원
}
