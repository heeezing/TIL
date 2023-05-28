--게시판
CREATE TABLE mboard(
	num number primary key,
	title varchar2(150) not null,
	name varchar2(30) not null,
	passwd varchar2(12) not null,
	email varchar2(50) not null,
	phone varchar2(15),
	content clob not null,
	ip varchar2(30) not null,
	reg_date date not null
);

CREATE SEQUENCE mboard_seq;