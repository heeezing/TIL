--회원관리
CREATE TABLE smember(
	num number primary key,
	id varchar2(12) not null,
	name varchar2(30) not null,
	passwd varchar2(12) not null,
	email varchar2(50) not null,
	phone varchar2(15),
	reg_date date not null
);

CREATE SEQUENCE smember_seq;