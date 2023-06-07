--회원 관리
CREATE TABLE zmember(
	mem_num number,
	id varchar2(12) unique not null,
	auth number(1) default 2 not null, --회원등급 : 0(탈퇴회원),1(정지회원),2(일반회원),9(관리자)
	constraint zmember_pk primary key (mem_num)
);

CREATE TABLE zmember_detail(
	mem_num number,
	name varchar2(30) not null,
	passwd varchar2(12) not null,
	phone varchar2(15) not null,
	email varchar2(50) not null,
	zipcode varchar2(5) not null, --우편번호
	address1 varchar2(90) not null,
	address2 varchar2(90) not null,
	photo varchar2(150), --파일명
	reg_date date default SYSDATE not null,
	modify_date date,
	constraint zmember_detail_pk primary key (mem_num),
	constraint zmember_detail_fk foreign key (mem_num) references zmember (mem_num)
);

CREATE SEQUENCE zmember_seq;


--게시판
CREATE TABLE zboard(
	board_num number,
	title varchar2(150) not null,
	content clob not null,
	hit number(9) default 0 not null,
	reg_date date default SYSDATE not null,
	modify_date date,
	filename varchar2(150),
	ip varchar2(40) not null,
	mem_num number not null,
	constraint zboard_pk primary key (board_num),
	constraint zboard_fk foreign key (mem_num) references zmember (mem_num)
);

CREATE SEQUENCE zboard_seq;


--좋아요
CREATE TABLE zboard_fav(
	fav_num number,
	board_num number not null,
	mem_num number not null,
	constraint zboard_fav_pk primary key (fav_num),
	constraint zboard_fav_fk1 foreign key (board_num) references zboard (board_num),
	constraint zboard_fav_fk2 foreign key (mem_num) references zmember (mem_num)
);

CREATE SEQUENCE zboardfav_seq;


--댓글
CREATE TABLE zboard_reply(
	re_num number,
	re_content varchar2(900) not null,
	re_date date default sysdate not null,
	re_modifydate date,
	re_ip varchar2(40) not null,
	board_num number not null, -- 부모글 번호
	mem_num number not null,
	constraint zreply_pk primary key (re_num),
	constraint zreply_fk foreign key (board_num) references zboard (board_num),
	constraint zreply_fk2 foreign key (mem_num) references zmember (mem_num)	
);

CREATE SEQUENCE zreply_seq;










