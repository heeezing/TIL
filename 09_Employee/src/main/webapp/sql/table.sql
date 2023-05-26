--사원관리
create table semployee(
 num number primary key,
 id varchar2(12) unique not null,
 name varchar2(30) not null,
 passwd varchar2(12) not null,
 salary number(8) not null,
 job varchar2(30) not null,
 reg_date date not null
);

create sequence semployee_seq;