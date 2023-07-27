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
}
