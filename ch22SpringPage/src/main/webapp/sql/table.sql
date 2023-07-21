--회원 관리

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


--게시판
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













