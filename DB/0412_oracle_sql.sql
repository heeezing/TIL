--04/12---------------------------------------------------------------------------------------------------------------------------------------------------------------------
/ primary key & foreign key 제약 조건

CREATE TABLE suser (
    id varchar2(30),
    name varchar2(30),
    constraint suser_pk primary key (id)  --primary key 제약 조건 적용
);

CREATE TABLE sboard (
    num number,
    id varchar2(30) not null,
    content varchar2(4000) not null,
    constraint sboard_pk primary key (num),  --primary key 제약 조건 적용
    constraint sboard_fk foreign key (id) references suser (id)  --foreign key 제약 조건 적용
);

INSERT INTO suser (id, name) VALUES ('star', '홍길동');
INSERT INTO suser (id, name) VALUES ('blue', '박문수');
COMMIT;
SELECT * FROM suser;

INSERT INTO sboard (num, id, content) VALUES (1, 'star', '안녕');
COMMIT;
DELETE FROM suser WHERE id = 'star';  --오류. 자식레코드를 두고 있다면 부모레코드는 삭제할 수 X.
                                                      --지우고 싶다면 자식 레코드 먼저 지우고 부모레코드 지워야.
SELECT * FROM sboard;


<테이블 관리> 
--ALTER : 기존 객체 수정 (DDL)
1) ADD 연산자 : 테이블에 새로운 컬럼을 추가.
ALTER TABLE employee ADD (addr VARCHAR2(50));
/제약 조건 추가
ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY (empno);

2) MODIFY 연산자 : 테이블의 컬럼을 수정하거나 not null 컬럼으로 변경할 수 있음.
ALTER TABLE employee MODIFY (salary NUMBER(10,2) NOT NULL);

3) DROP 연산자 : 컬럼의 삭제
ALTER TABLE employee DROP COLUMN name;

4) RENAME 연산자 : 컬럼명 변경
ALTER TABLE employee RENAME COLUMN salary TO sal;

/ 테이블명 변경
RENAME employee TO employee2;
/ 테이블명 삭제
DROP TABLE employee2;

5) on delete cascade (옵션) : 부모 테이블의 컬럼을 삭제하면 자식 테이블의 자식 데이터를 모두 삭제

CREATE TABLE s_member (
    id varchar2(20) primary key,  --primary key 제약 조건 적용
    name varchar2(30)
);

CREATE TABLE s_member_detail (
    num number primary key,
    content varchar2(4000) not null,
    id varchar2(20) not null references s_member (id) on delete cascade
);


[실습문제]
1) student라는 이름으로 테이블 생성
 컬럼명 id, name, age, score
 데이터타입 varchar2(10), varchar2(30), number(3), number(3)
 제약조건 primary key, not null, not null, not null
 
CREATE TABLE student (
    id varchar2(10) primary key,
    name varchar2(30) not null,
    age number(3) not null,
    score number(3) not null
);

2) 생성된 테이블에 데이터를 아래와 같이 입력
id          name         age        score
dragon    홍길동         21         100
sky         장영실         22          99
blue       박문수         34           88

INSERT INTO student (id, name, age, score) VALUES ('dragon', '홍길동', 21, 100);
INSERT INTO student (id, name, age, score) VALUES ('sky', '장영실', 22, 99);
INSERT INTO student (id, name, age, score) VALUES ('blue', '박문수', 34, 88);
COMMIT;

3) 데이터 읽기 -> student 테이블에서 성적 합계 구하시오.
SELECT SUM(score) FROM student;


< 뷰(View) > : 논리적으로 하나 이상의 테이블에 있는 데이터의 부분 집합

- 사용 목적
1) 데이터 액세스를 제한하기 위해
-- 원본 테이블에서 필요한 정보만 뽑아내서 또 하나의 테이블을 만드는 것과 같다.
-- (가리고 싶은 정보는 가려서 볼 수 있다는 장점)
2) 복잡한 질의를 쉽게 작성하기 위해
-- ex) 빈번하게 조인을 할 경우 복잡한 SQL문을 단순화하기 위해 뷰를 활용할 수 있다.
3) 데이터 독립성을 제공하기 위해
4) 동일한 데이터로부터 다양한 결과를 얻기 위해

1.  VIEW 생성

CREATE [OR REPLACE] VIEW 뷰이름 AS 쿼리;
--뷰 생성 : CREATE / 뷰 생성, 기존 뷰 수정 : CREATE OR REPLACE
CREATE OR REPLACE VIEW emp10_view
AS SELECT empno id_number, ename name, sal*12 ann_salary FROM emp WHERE deptno=10;
--sal*12처럼 원본 데이터에 없이 메모리상에서 생성된 데이터(가상 열)가 있을 시엔 등록과 변경에 제한을 받음.
--따라서 주로 보는(조회) 용으로 쓰인다.

/조인해서 얻어지는 결과를 뷰로 생성
CREATE OR REPLACE VIEW emp_info_view 
AS SELECT e.empno, e.ename, d.deptno, d.loc, d.dname
     FROM emp e, dept d 
     WHERE e.deptno = d.deptno;

SELECT * FROM emp_info_view;

2. VIEW 를 통한 데이터 변경
--일반적으로 VIEW는 조회용으로 많이 사용되지만, 데이터 변경도 가능함.
UPDATE emp10_view SET name = 'SCOTT' WHERE id_number = 7839;
SELECT * FROM emp;  --VIEW의 데이터를 변경하면 원본 테이블의 정보도 변경됨.

INSERT INTO emp10_view (id_number, name, ann_salary) VALUES (8000, 'JOHN', 19000);
-- 오류. 가상 열(ann_salary)은 사용할 수 없음.

INSERT INTO emp10_view (id_number, name) VALUES (8000, 'JOHN');
-- 가상 열을 제외하면 삽입 가능.

SELECT * FROM emp10_view;
--10번 부서만 보여지게 제한된 뷰이기 때문에 삽입한 것이 안 보임. (삽입 -> 부서번호 입력 안 함)
SELECT * FROM emp;
-- emp테이블에 1행이 추가된 것을 확인할 수 있음.
ROLLBACK;

- WITH READ ONLY : 읽기 전용 뷰를 생성하는 옵션
CREATE OR REPLACE VIEW emp20_view
AS SELECT empno id_number, ename name, sal*12 ann_salary 
    FROM emp WHERE deptno = 20
    WITH READ ONLY;

UPDATE emp20_view SET name = 'DAVID' WHERE id_number = 7902;
-- 오류. 읽기 전용 뷰에서는 DML 작업(삽입,수정,삭제)을 수행할 수 없다.

3.  VIEW 삭제
DROP VIEW emp10_view;


<SEQUENCE> : 유일한 값을 생성해주는 오라클 객체

- 시퀀스를 생성하면 기본키와 같이 순차적으로 증가하는 컬럼을 자동적으로 생성할 수 있음.
- primary key 값 생성 시 유용.

1. 시퀀스 생성
CREATE SEQUENCE test_seq
start with 1
increment by 1
maxvalue 100000;
--시작 값이 1이고, 1씩 증가하고, 최대값이 100000이 되는 시퀀스 생성

2. 시퀀스 호출
- currval : 현재 값을 반환
- nextval : 현재 시퀀스 값의 다음 값을 반환
SELECT test_seq.nextval FROM dual;  --1. 최초에 nextval을 해야 시작값이 시작됨.
SELECT test_seq.currval FROM dual;  --1

3. 시퀀스 수정
ALTER SEQUENCE sequence_name
increment by n
maxvalue n | nomaxvalue
minvalue n | nominvalue
cycle | nocycle . . .
* start with 는 수정할 수 없음. 지우고 다시 만들어야 함.

4. 시퀀스 삭제
DROP SEQUENCE test_seq;


<INDEX>
: 원하는 정보의 위치를 빠르고, 정확하고, 지능적으로 알아낼 수 있는 방법을 제공.
테이블의 컬럼에 대한 제약 조건을 설정할 때 Priamary key나 Unique로 설정하면
Oracle은 자동으로 이 컬럼에 대해 Unique Index 를 설정함.

1) 자동 생성 : 테이블 정의에 Primary key 또는 Unique 제약 조건을 정의하면
                  공유 인덱스가 자동적으로 생성됨.

2) 수동 생성 : 사용자가 열에 고유하지 않은 인덱스를 생성하여 행에 대한 엑세스 시간을 줄일 수 있음.

- 유일한 값을 가지는 컬럼에 인덱스 설정 : Unique Index
CREATE UNIQUE INDEX dname_idx ON dept (dname);

- 유일한 값을 가지지 않는 컬럼에 인덱스 설정 : Non Unique Index
CREATE INDEX emp_ename_idx ON emp (ename);


<동의어> : 객체의 다른 이름(별칭). 객체 액세스를 단순화.

- 사용 목적
1) 다른 사용자가 소유한 테이블을 쉽게 참조하기 위해
2) 긴 객체 이름을 짧게 만들기 위해

1. 동의어 생성       --동의어     --기존객체(뷰)
CREATE SYNONYM emp20 FOR emp20_view;
SELECT * FROM emp20;

2. 동의어 삭제
DROP SYNONYM emp20;
