----------회원 관리----------

CREATE TABLE spmember(
	mem_num number,
	id varchar2(12) unique not null,
	nick_name varchar2(30),
	auth number(1) default 2 not null,--0탈퇴회원,1정지회원,2일반회원,9관리자
	constraint spmember_pk primary key (mem_num)
);

CREATE TABLE spmember_detail(
	mem_num number,
	au_id varchar2(36) unique, --자동 로그인에 사용되는 식별값
	name varchar2(30) not null,
	passwd varchar2(35) not null,
	phone varchar2(15) not null,
	email varchar2(50) not null,
	zipcode varchar2(90) not null,
	address1 varchar2(90) not null,
	address2 varchar2(90) not null,
	photo blob,
	photo_name varchar2(100), --확장자 표시를 위해 생성 (생략 가능)
	reg_date date default SYSDATE not null,
	modify_date date,
	constraint spmember_detail_pk primary key (mem_num),
	constraint spmember_detail_fk foreign key (mem_num) references spmember (mem_num)
);

CREATE SEQUENCE spmember_seq;


----------게시판----------

CREATE TABLE spboard(
	board_num number,
	title varchar2(90) not null,
	content clob not null,
	hit number(8) default 0 not null,
	reg_date date default SYSDATE not null,
	modify_date date,
	ip varchar2(40) not null,
	mem_num number not null,
	constraint spboard_pk primary key (board_num),
	constraint spboard_fk1 foreign key (mem_num) references spmember (mem_num)
);

CREATE SEQUENCE spboard_seq;


--게시판 댓글
CREATE TABLE spboard_reply(
	re_num number,
	re_content varchar2(900) not null,
	re_date date default SYSDATE not null,
	re_mdate date,
	re_ip varchar2(40) not null,
	board_num number not null,
	mem_num number not null,
	constraint spboard_reply_pk primary key (re_num),
	constraint spboard_reply_fk1 foreign key (board_num) references spboard (board_num),
	constraint spboard_reply_fk2 foreign key (mem_num) references spmember (mem_num)
);

CREATE SEQUENCE spreply_seq;


--게시판 좋아요
CREATE TABLE spboard_fav(
	fav_num number,
	board_num number not null,
	mem_num number not null,
	constraint spboard_fav_pk primary key (fav_num),
	constraint spboard_fav_fk1 foreign key (board_num) references spboard (board_num),
	constraint spboard_fav_fk2 foreign key (mem_num) references spmember (mem_num)
);

CREATE SEQUENCE refav_seq;


----------그룹 채팅----------

--채팅방
CREATE TABLE sptalkroom(
	talkroom_num number,
	basic_name varchar2(900) not null,--채팅 멤버를 추가할 때 채팅방 이름을 basic_name에서 가져다 씀	
	talkroom_date date default SYSDATE not null,
	constraint sptalkroom_pk primary key (talkroom_num)
);

CREATE SEQUENCE sptalkroom_seq;

--채팅멤버
CREATE TABLE sptalk_member(
	talkroom_num number not null,
	mem_num number not null,
	room_name varchar2(900) not null,
	member_date date default SYSDATE not null,
	constraint sptalkmember_fk1 foreign key (talkroom_num) references sptalkroom (talkroom_num),
	constraint sptalkmember_fk2 foreign key (mem_num) references spmember (mem_num)
);

--채팅메시지
CREATE TABLE sptalk(
	talk_num number,
	talkroom_num number not null,--수신그룹
	mem_num number not null,--발신자
	message varchar2(4000) not null,
	chat_date date default SYSDATE not null,
	constraint sptalk_pk primary key (talk_num),
	constraint sptalk_fk1 foreign key (talkroom_num) references sptalkroom (talkroom_num),
	constraint sptalk_fk2 foreign key (mem_num) references spmember (mem_num)
);

CREATE SEQUENCE sptalk_seq;

--메시지 읽음 체크
CREATE TABLE sptalk_read(
	talkroom_num number not null,
	talk_num number not null,
	mem_num number not null,
	constraint read_fk foreign key (talkroom_num) references sptalkroom (talkroom_num),
	constraint read_fk2 foreign key (talk_num) references sptalk (talk_num),
	constraint read_fk3 foreign key (mem_num) references spmember (mem_num)
);

