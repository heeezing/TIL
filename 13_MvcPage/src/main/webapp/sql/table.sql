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


--상품
CREATE TABLE zitem(
	item_num number,
	name varchar2(30) not null,
	price number(8) not null,
	quantity number(5) not null,
	photo1 varchar2(34) not null,
	photo2 varchar2(34) not null,
	detail clob not null,
	reg_date date default SYSDATE not null,
	modify_date date,
	status number(1) not null, --판매 가능 여부
	constraint zitem_pk primary key (item_num)
);

CREATE SEQUENCE zitem_seq;


--주문
CREATE TABLE zorder(
	order_num number,
	item_name varchar2(60) not null, --대표 상품명(생략가능)
	order_total number(9) not null,
	payment number(1) not null, --결제 방식
	status number(1) default 1 not null, --배송 상태
	receive_name varchar2(30) not null, --받는 사람의 이름
	receive_post varchar2(5) not null,
	receive_address1 varchar2(90) not null,
	receive_address2 varchar2(90) not null,
	receive_phone varchar2(15) not null,
	notice varchar2(4000),
	reg_date date default SYSDATE not null,
	modify_date date,
	mem_num number not null,
	constraint zorder_pk primary key (order_num),
	constraint zorder_fk foreign key (mem_num) references zmember (mem_num)
);

CREATE SEQUENCE zorder_seq;


--주문 상세
CREATE TABLE zorder_detail(
	detail_num number,
	item_num number not null,
	item_name varchar2(30) not null,
	item_price number(8) not null,
	item_total number(8) not null,
	order_quantity number(7) not null,
	order_num number not null,
	constraint zorder_detail_pk primary key (detail_num),
	constraint zorder_detail_fk foreign key (order_num) references zorder (order_num)
);

CREATE SEQUENCE zorder_detail_seq;


--계층형 게시판
CREATE TABLE zboardlevel(
	boardv_num number,
	subject varchar2(40) not null,
	content clob not null,
	reg_date date default SYSDATE not null,
	modify_date date,
	readcount number(6) default 0,
	parent_num number not null, --부모들의 번호가 들어감, 자식글이 아니라 부모글일 경우 0
	dept number not null, --자식글의 깊이. 부모글의 자식글A 1, 자식글A의 자식글B 2, 부모글의 경우 0
	ip varchar2(40) not null,
	image varchar2(150),
	mem_num number not null,
	constraint zboardlevel_pk primary key (boardv_num),
	constraint zboardlevel_fk foreign key (mem_num) references zmember (mem_num)
);

CREATE SEQUENCE zboardv_seq;









