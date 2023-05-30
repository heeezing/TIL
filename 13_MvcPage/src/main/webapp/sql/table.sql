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