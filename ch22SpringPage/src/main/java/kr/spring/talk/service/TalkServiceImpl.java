package kr.spring.talk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.talk.dao.TalkMapper;
import kr.spring.talk.vo.TalkMemberVO;
import kr.spring.talk.vo.TalkRoomVO;
import kr.spring.talk.vo.TalkVO;

@Service
@Transactional
public class TalkServiceImpl implements TalkService{
	
	@Autowired
	TalkMapper talkMapper;

	@Override
	public List<TalkRoomVO> selectTalkRoomList(Map<String, Object> map) {
		return talkMapper.selectTalkRoomList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return talkMapper.selectRowCount(map);
	}

	@Override
	public void insertTalkRoom(TalkRoomVO talkRoomVO) {
		//기본키 생성
		talkRoomVO.setTalkroom_num(talkMapper.selectTalkRoomNum());
		//채팅방 생성
		talkMapper.insertTalkRoom(talkRoomVO);
		//입장 메시지 처리
		talkRoomVO.getTalkVO().setTalk_num(talkMapper.selectTalkNum());
		talkRoomVO.getTalkVO().setTalkroom_num(talkRoomVO.getTalkroom_num());
		//채팅 메시지 저장
		talkMapper.insertTalk(talkRoomVO.getTalkVO());
		//채팅방 멤버 생성 (members로부터 mem_num을 뽑아내어 반복문을 돌면서 insert해줌)
		for(Integer mem_num : talkRoomVO.getMembers()) {
			talkMapper.insertTalkRoomMember(talkRoomVO.getTalkroom_num(),
											talkRoomVO.getBasic_name(),
											mem_num);
		}
	}

	@Override
	public List<TalkMemberVO> selectTalkMember(Integer talkroom_num) {
		return talkMapper.selectTalkMember(talkroom_num);
	}

	@Override
	public void insertTalk(TalkVO talkVO) {
		//채팅 번호를 생성해서 자바빈에 저장
		talkVO.setTalk_num(talkMapper.selectTalkNum());
		//채팅 메시지 저장
		talkMapper.insertTalk(talkVO);
		//채팅방 멤버가 읽지 않은 채팅 정보를 저장
		for(TalkMemberVO vo : talkMapper.selectTalkMember(talkVO.getTalkroom_num())) {
			talkMapper.insertTalkRead(talkVO.getTalkroom_num(), //전송받은 데이터
									  talkVO.getTalk_num(), //전송받은 데이터
									  vo.getMem_num()); //메서드로 뽑아내서 구한 데이터
		}
	}

	@Override
	public List<TalkVO> selectTalkDetail(Map<String, Integer> map) {
		//읽은 채팅 기록은 삭제
		talkMapper.deleteTalkRead(map);
		//채팅 메시지 반환
		return talkMapper.selectTalkDetail(map);
	}

	@Override
	public void changeRoomName(TalkMemberVO vo) {
		talkMapper.changeRoomName(vo);
	}

	@Override
	public TalkRoomVO selectTalkRoom(Integer talkroom_num) {
		return talkMapper.selectTalkRoom(talkroom_num);
	}

	@Override
	public void insertNewMember(TalkRoomVO talkRoomVO) {
		//입장 메시지 처리
		talkRoomVO.getTalkVO().setTalk_num(talkMapper.selectTalkNum());
		//메시지 저장
		talkMapper.insertTalk(talkRoomVO.getTalkVO());
		//채팅방 멤버 생성
		for(Integer mem_num : talkRoomVO.getMembers()) {
			talkMapper.insertTalkRoomMember(talkRoomVO.getTalkroom_num(),
											talkRoomVO.getBasic_name(),
											mem_num);
		}
	}

}
