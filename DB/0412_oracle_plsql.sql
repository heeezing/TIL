--04/12---------------------------------------------------------------------------------------------------------------------------------------------------------------------
[ PL/SQL ]
: Procedural language extension to Structured Query Language �� ����
SQL�� Ȯ���� ������ ó�� ���

<�⺻ ����>
�⺻ ������ ��� (block)
1) ����� (declarative part) : ����� ������ ����� ���� (����ο��� ����/��� ���� ����)
2) ����� (executable part) : ���� ó���� ������ ����ϴ� �κ�
3) ���� ó���� (exception-building part) : ����ο��� ������ ó���ϴ� �� �߻��� �� �ִ� ���� �����鿡 ���� ó��

BEGIN
    DBMS_OUTPUT.PUT_LINE ('Hello World!');
END;


DECLARE
--������ ������ �� �ִ� �����
    message VARCHAR2(100);  --���� ����
BEGIN
--����ο� ����� ������ ����ο��� �̸� ����Ǿ�� ��.
    message := 'Hello World!!';  --���Կ����� :=
    DBMS_OUTPUT.PUT_LINE (message);  --DBMS�� ��� (��ġ �ڹ��� �ܼ�)
END;


DECLARE
    --���� ���� �� �ʱ�ȭ
    message VARCHAR2(100) := 'HELLO WORLD';
BEGIN
    DBMS_OUTPUT.PUT_LINE (message);
END;


DECLARE
--���� ����
    counter INTEGER;
BEGIN
    counter := counter + 1;
    if counter IS NULL then 
        DBMS_OUTPUT.PUT_LINE ('Result : counter is null');
    end if;
END;


DECLARE
--���� ����
    counter INTEGER;
    i INTEGER;
BEGIN
    for i in 1..9 loop
        counter := 2 * i;
        DBMS_OUTPUT.PUT_LINE ('2 * ' || i || ' = ' || counter);
    end loop;
END;


DECLARE
--���� ����
    counter INTEGER;
BEGIN
    counter := 10;  --���� �ʱ�ȭ
    counter := counter / 0;
    DBMS_OUTPUT.PUT_LINE (counter);
EXCEPTION WHEN OTHERS THEN  --���� ó�� (OTHERS->��� ���ܵ��� �θ�)
    DBMS_OUTPUT.PUT_LINE ('errors');
END;


<�÷���>
: �迭 ������ ������ Ÿ��. ���� �ڷ����� ���� ��ҵ�θ� ������ �� ����.
 
1. varray
: variable array�� ����. ���� ����(fixed number)�� ���� �迭.

DECLARE
        --�ڷ��� ����
    TYPE varray_test IS VARRAY(3) OF INTEGER;
--������ ������ varray_test Ÿ���� ���� ����
    varray1 varray_test;
BEGIN
    varray1 := varray_test (10, 20, 30);
--DBMS ���                          --�ε���
    DBMS_OUTPUT.PUT_LINE (varray1(1));  --10
    DBMS_OUTPUT.PUT_LINE (varray1(2));  --20
    DBMS_OUTPUT.PUT_LINE (varray1(3));  --30
END;


2. ��ø ���̺�
: varray �� ��������� ���� �ÿ� ��ü ũ�⸦ ����� �ʿ䰡 ����.

DECLARE
    TYPE nested_test IS TABLE OF VARCHAR2(10);
--������ ������ nested_test Ÿ�� ���� ����
    nested1 nested_test;
BEGIN                         --1   2   3   4
    nested1 := nested_test ('A', 'B', 'C', 'D');
    DBMS_OUTPUT.PUT_LINE (nested1(2));  --B
END;


3. Associative array (index-by table, ���� �迭)
: Ű�� ���� ������ ����. �ϳ��� Ű�� �ϳ��� ���� ����.

DECLARE                                            --�� �ڷ���                      key �ڷ���
    TYPE assoc_array_str_type IS TABLE OF VARCHAR2(32) INDEX BY VARCHAR2(64);
    --key�� VARCHAR2(64)���̸�, ���� VARCHAR2(32)���� ��ҵ�� ����
    assoc assoc_array_str_type;
BEGIN 
    assoc ('K') := 'KOREA';  --Ű�� K, ���� KOREA�� ��.
    DBMS_OUTPUT.PUT_LINE (assoc('K'));
END;


<���ڵ�>
: �÷��ǰ� �޸� �ʵ���� ���� �ٸ� ������ �ڷ����� ���� �� ����.

DECLARE
    TYPE record1 IS RECORD (deptno number not null := 50,
                                       dname varchar2(14),
                                       loc varchar2(13));
--������ ������ record1�� �޴� ���� ����
    rec1 record1; 
BEGIN
--record1 ���ڵ� ���� rec1�� dname �ʵ忡 �� �Ҵ�
    rec1.dname := 'RECORD';
--record1 ���ڵ� ���� rec1�� loc �ʵ忡 �� �Ҵ�
    rec1.loc := 'SEOUL';
--rec1 ���ڵ� ���� dept ���̺� insert
    INSERT INTO dept VALUES rec1;
    COMMIT;  --INSERT���� ���� ó�� �Ǹ� COMMIT
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;  --���� �߻� �� ROLLBACK
END;

SELECT * FROM dept;


<���ǹ�>

1. IF ��  --(����) ���� ó���� ��� ELSE IF�� �ƴ� ELSIF��
DECLARE
    grade CHAR(1);
BEGIN
    grade := 'B';
    IF grade = 'A' THEN
        DBMS_OUTPUT.PUT_LINE ('Excellent');
    ELSIF grade = 'B' THEN
        DBMS_OUTPUT.PUT_LINE ('Good');
    ELSIF grade = 'C' THEN
        DBMS_OUTPUT.PUT_LINE ('Fair');
    ELSIF grade = 'D' THEN
        DBMS_OUTPUT.PUT_LINE ('Poor');
    END IF;
END;

2. CASE ��
DECLARE
    grade CHAR(1);
BEGIN
    grade := 'B';
    CASE grade
    WHEN 'A' THEN
        DBMS_OUTPUT.PUT_LINE ('Excellent');
    WHEN 'B' THEN
        DBMS_OUTPUT.PUT_LINE ('Good');
    WHEN 'C' THEN
        DBMS_OUTPUT.PUT_LINE ('Fair');
    WHEN 'D' THEN
        DBMS_OUTPUT.PUT_LINE ('Poor');
    ELSE 
        DBMS_OUTPUT.PUT_LINE ('Not Found');
    END CASE;  --SQL���� �ٸ��� END�� CASE�� ���� ���־����.
END;


<�ݺ���>

1. LOOP ��  --���ѷ���
DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    test_number := 1;      
    LOOP
        result_num := 2 * test_number;
            IF result_num > 20 THEN
                exit;  --��� ���� (��������)
            ELSE 
                DBMS_OUTPUT.PUT_LINE (result_num);
            END IF;
        test_number := test_number + 1;
    END LOOP;
--loop ���� ���������� �Ʒ� �ڵ带 ������.
    DBMS_OUTPUT.PUT_LINE ('���α׷� ��');
END;
