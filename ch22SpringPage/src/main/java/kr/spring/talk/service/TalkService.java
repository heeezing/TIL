package kr.spring.talk.service;

import java.util.List;
import java.util.Map;

import kr.spring.talk.vo.TalkRoomVO;

public interface TalkService {
	//채팅방 목록
	public List<TalkRoomVO> selectTalkRoomList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	//채팅방 번호 생성
	public int selectTalkRoomNum();
	//채팅방 생성
	public void insertTalkRoom(TalkRoomVO talkRoomVO);
}
