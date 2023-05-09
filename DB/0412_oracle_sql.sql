--04/12---------------------------------------------------------------------------------------------------------------------------------------------------------------------
/ primary key & foreign key ���� ����

CREATE TABLE suser (
    id varchar2(30),
    name varchar2(30),
    constraint suser_pk primary key (id)  --primary key ���� ���� ����
);

CREATE TABLE sboard (
    num number,
    id varchar2(30) not null,
    content varchar2(4000) not null,
    constraint sboard_pk primary key (num),  --primary key ���� ���� ����
    constraint sboard_fk foreign key (id) references suser (id)  --foreign key ���� ���� ����
);

INSERT INTO suser (id, name) VALUES ('star', 'ȫ�浿');
INSERT INTO suser (id, name) VALUES ('blue', '�ڹ���');
COMMIT;
SELECT * FROM suser;

INSERT INTO sboard (num, id, content) VALUES (1, 'star', '�ȳ�');
COMMIT;
DELETE FROM suser WHERE id = 'star';  --����. �ڽķ��ڵ带 �ΰ� �ִٸ� �θ��ڵ�� ������ �� X.
                                                      --����� �ʹٸ� �ڽ� ���ڵ� ���� ����� �θ��ڵ� ������.
SELECT * FROM sboard;


<���̺� ����> 
--ALTER : ���� ��ü ���� (DDL)
1) ADD ������ : ���̺� ���ο� �÷��� �߰�.
ALTER TABLE employee ADD (addr VARCHAR2(50));
/���� ���� �߰�
ALTER TABLE employee ADD CONSTRAINT employee_pk PRIMARY KEY (empno);

2) MODIFY ������ : ���̺��� �÷��� �����ϰų� not null �÷����� ������ �� ����.
ALTER TABLE employee MODIFY (salary NUMBER(10,2) NOT NULL);

3) DROP ������ : �÷��� ����
ALTER TABLE employee DROP COLUMN name;

4) RENAME ������ : �÷��� ����
ALTER TABLE employee RENAME COLUMN salary TO sal;

/ ���̺�� ����
RENAME employee TO employee2;
/ ���̺�� ����
DROP TABLE employee2;

5) on delete cascade (�ɼ�) : �θ� ���̺��� �÷��� �����ϸ� �ڽ� ���̺��� �ڽ� �����͸� ��� ����

CREATE TABLE s_member (
    id varchar2(20) primary key,  --primary key ���� ���� ����
    name varchar2(30)
);

CREATE TABLE s_member_detail (
    num number primary key,
    content varchar2(4000) not null,
    id varchar2(20) not null references s_member (id) on delete cascade
);


[�ǽ�����]
1) student��� �̸����� ���̺� ����
 �÷��� id, name, age, score
 ������Ÿ�� varchar2(10), varchar2(30), number(3), number(3)
 �������� primary key, not null, not null, not null
 
CREATE TABLE student (
    id varchar2(10) primary key,
    name varchar2(30) not null,
    age number(3) not null,
    score number(3) not null
);

2) ������ ���̺� �����͸� �Ʒ��� ���� �Է�
id          name         age        score
dragon    ȫ�浿         21         100
sky         �念��         22          99
blue       �ڹ���         34           88

INSERT INTO student (id, name, age, score) VALUES ('dragon', 'ȫ�浿', 21, 100);
INSERT INTO student (id, name, age, score) VALUES ('sky', '�念��', 22, 99);
INSERT INTO student (id, name, age, score) VALUES ('blue', '�ڹ���', 34, 88);
COMMIT;

3) ������ �б� -> student ���̺��� ���� �հ� ���Ͻÿ�.
SELECT SUM(score) FROM student;


< ��(View) > : �������� �ϳ� �̻��� ���̺� �ִ� �������� �κ� ����

- ��� ����
1) ������ �׼����� �����ϱ� ����
-- ���� ���̺��� �ʿ��� ������ �̾Ƴ��� �� �ϳ��� ���̺��� ����� �Ͱ� ����.
-- (������ ���� ������ ������ �� �� �ִٴ� ����)
2) ������ ���Ǹ� ���� �ۼ��ϱ� ����
-- ex) ����ϰ� ������ �� ��� ������ SQL���� �ܼ�ȭ�ϱ� ���� �並 Ȱ���� �� �ִ�.
3) ������ �������� �����ϱ� ����
4) ������ �����ͷκ��� �پ��� ����� ��� ����

1.  VIEW ����

CREATE [OR REPLACE] VIEW ���̸� AS ����;
--�� ���� : CREATE / �� ����, ���� �� ���� : CREATE OR REPLACE
CREATE OR REPLACE VIEW emp10_view
AS SELECT empno id_number, ename name, sal*12 ann_salary FROM emp WHERE deptno=10;
--sal*12ó�� ���� �����Ϳ� ���� �޸𸮻󿡼� ������ ������(���� ��)�� ���� �ÿ� ��ϰ� ���濡 ������ ����.
--���� �ַ� ����(��ȸ) ������ ���δ�.

/�����ؼ� ������� ����� ��� ����
CREATE OR REPLACE VIEW emp_info_view 
AS SELECT e.empno, e.ename, d.deptno, d.loc, d.dname
     FROM emp e, dept d 
     WHERE e.deptno = d.deptno;

SELECT * FROM emp_info_view;

2. VIEW �� ���� ������ ����
--�Ϲ������� VIEW�� ��ȸ������ ���� ��������, ������ ���浵 ������.
UPDATE emp10_view SET name = 'SCOTT' WHERE id_number = 7839;
SELECT * FROM emp;  --VIEW�� �����͸� �����ϸ� ���� ���̺��� ������ �����.

INSERT INTO emp10_view (id_number, name, ann_salary) VALUES (8000, 'JOHN', 19000);
-- ����. ���� ��(ann_salary)�� ����� �� ����.

INSERT INTO emp10_view (id_number, name) VALUES (8000, 'JOHN');
-- ���� ���� �����ϸ� ���� ����.

SELECT * FROM emp10_view;
--10�� �μ��� �������� ���ѵ� ���̱� ������ ������ ���� �� ����. (���� -> �μ���ȣ �Է� �� ��)
SELECT * FROM emp;
-- emp���̺� 1���� �߰��� ���� Ȯ���� �� ����.
ROLLBACK;

- WITH READ ONLY : �б� ���� �並 �����ϴ� �ɼ�
CREATE OR REPLACE VIEW emp20_view
AS SELECT empno id_number, ename name, sal*12 ann_salary 
    FROM emp WHERE deptno = 20
    WITH READ ONLY;

UPDATE emp20_view SET name = 'DAVID' WHERE id_number = 7902;
-- ����. �б� ���� �信���� DML �۾�(����,����,����)�� ������ �� ����.

3.  VIEW ����
DROP VIEW emp10_view;


<SEQUENCE> : ������ ���� �������ִ� ����Ŭ ��ü

- �������� �����ϸ� �⺻Ű�� ���� ���������� �����ϴ� �÷��� �ڵ������� ������ �� ����.
- primary key �� ���� �� ����.

1. ������ ����
CREATE SEQUENCE test_seq
start with 1
increment by 1
maxvalue 100000;
--���� ���� 1�̰�, 1�� �����ϰ�, �ִ밪�� 100000�� �Ǵ� ������ ����

2. ������ ȣ��
- currval : ���� ���� ��ȯ
- nextval : ���� ������ ���� ���� ���� ��ȯ
SELECT test_seq.nextval FROM dual;  --1. ���ʿ� nextval�� �ؾ� ���۰��� ���۵�.
SELECT test_seq.currval FROM dual;  --1

3. ������ ����
ALTER SEQUENCE sequence_name
increment by n
maxvalue n | nomaxvalue
minvalue n | nominvalue
cycle | nocycle . . .
* start with �� ������ �� ����. ����� �ٽ� ������ ��.

4. ������ ����
DROP SEQUENCE test_seq;


<INDEX>
: ���ϴ� ������ ��ġ�� ������, ��Ȯ�ϰ�, ���������� �˾Ƴ� �� �ִ� ����� ����.
���̺��� �÷��� ���� ���� ������ ������ �� Priamary key�� Unique�� �����ϸ�
Oracle�� �ڵ����� �� �÷��� ���� Unique Index �� ������.

1) �ڵ� ���� : ���̺� ���ǿ� Primary key �Ǵ� Unique ���� ������ �����ϸ�
                  ���� �ε����� �ڵ������� ������.

2) ���� ���� : ����ڰ� ���� �������� ���� �ε����� �����Ͽ� �࿡ ���� ������ �ð��� ���� �� ����.

- ������ ���� ������ �÷��� �ε��� ���� : Unique Index
CREATE UNIQUE INDEX dname_idx ON dept (dname);

- ������ ���� ������ �ʴ� �÷��� �ε��� ���� : Non Unique Index
CREATE INDEX emp_ename_idx ON emp (ename);


<���Ǿ�> : ��ü�� �ٸ� �̸�(��Ī). ��ü �׼����� �ܼ�ȭ.

- ��� ����
1) �ٸ� ����ڰ� ������ ���̺��� ���� �����ϱ� ����
2) �� ��ü �̸��� ª�� ����� ����

1. ���Ǿ� ����       --���Ǿ�     --������ü(��)
CREATE SYNONYM emp20 FOR emp20_view;
SELECT * FROM emp20;

2. ���Ǿ� ����
DROP SYNONYM emp20;
