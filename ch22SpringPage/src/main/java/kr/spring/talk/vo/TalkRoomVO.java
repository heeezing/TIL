package kr.spring.talk.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TalkRoomVO {
	private int talkroom_num; //채팅방 번호
	private String basic_name; //채팅방 기본 이름
	private String talkroom_date; //채팅방 생성 날짜
	
	private int[] members; //채팅 멤버들의 회원번호
	private TalkVO talkVO; //채팅 메시지 VO
	private TalkMemberVO talkMemberVO; //채팅 멤버 VO
	
	private int mem_num;
	private int room_cnt; //읽지 않은 메시지 수
}
