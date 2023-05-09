--04/13---------------------------------------------------------------------------------------------------------------------------------------------------------------------
--LOOP������ if�� �ܿ� �ڵ带 ���̴� ���
DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    test_number := 1;  
    LOOP
        result_num := 2* test_number;
        EXIT WHEN result_num > 20;  --�ش� ���� �� loop���� ��������
        DBMS_OUTPUT.PUT_LINE (result_num);
        test_number := test_number + 1;
    END LOOP;
END;


2. WHILE ~ LOOP ��

DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    test_number := 1;
    result_num := 0;  --while���� �������� ����ϹǷ� �̸� �ʱ�ȭ
    WHILE result_num < 20 LOOP
        result_num := 2 * test_number;
        DBMS_OUTPUT.PUT_LINE (result_num);
        test_number := test_number + 1;
    END LOOP;
END;


3. FOR ~ LOOP ��

DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    FOR test_number IN 1..10 LOOP
        result_num := 2 * test_number;
        DBMS_OUTPUT.PUT_LINE (result_num);
    END LOOP;
END;

DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    FOR test_number IN REVERSE 1..10 LOOP  --REVERSE -> ����
        result_num := 2 * test_number;
        DBMS_OUTPUT.PUT_LINE (result_num);
    END LOOP;
END;


<Ŀ��>
: SELECT ������ �����ϸ� ���ǿ� ���� ����� ����ǰ�, ���� ����� �� ���� �� ���� ���� ���� �� ���� �����Ƿ�
 �̸� ��� ��(RESULT SET) Ȥ�� ��� �����̶�� �θ�.
 ����� �޸� �� ��ġ�ϰ� �Ǵµ� PL/SQL������ �ٷ� Ŀ��(CURSOR)�� ����Ͽ� �� ��� ���տ� ������.

DECLARE
    --Ŀ�� ����
    CURSOR emp_csr IS
    SELECT empno FROM emp WHERE deptno=10;
    emp_no emp.empno%type;  --%type�� �̿��� emp���̺� empno�÷��� �ڷ����� �о��.
BEGIN
    OPEN emp_csr;   
    LOOP
        FETCH emp_csr INTO emp_no;
        --%notfound : Ŀ�������� ��� ������ �Ӽ�
        --                �� �̻� ��ġ(�Ҵ�)�� row�� ������ �ǹ�
        EXIT WHEN emp_csr%notfound;
        DBMS_OUTPUT.PUT_LINE (emp_no);
    END LOOP;       
    CLOSE emp_csr;
END;


<�Լ�>

/�Է¹��� �����κ��� 10%�� ������ ��� �Լ�
--                                        �Լ���    ����&�ڷ���
CREATE OR REPLACE FUNCTION tax (p_value IN NUMBER)  --�ڷ��� ������ ���x
    RETURN NUMBER IS  --�ڷ��� ������ ���x
BEGIN
    RETURN p_value * 0.1;
END;

SELECT TAX(100) FROM dual;
SELECT ename, sal, TAX(sal) tax, sal-TAX(sal) "�� ���� �޿�" FROM emp;


/�޿��� Ŀ�̼��� ���ļ� ���� ���

CREATE OR REPLACE FUNCTION tax2(p_sal IN emp.sal%type,
                                                   p_bonus emp.comm%type) --IN ���� ����
    RETURN NUMBER IS
BEGIN
    RETURN (p_sal + NVL(p_bonus, 0)) * 0.1;
END;

SELECT empno, ename, sal, comm, TAX2(sal, comm) tax FROM emp;


/�޿� (���ʽ� ����)�� ���� ������ ������ ���� ������.
 �޿��� �� $1,000�̸��̸� ������ 5%, $1,000 �̻� $2,000 ���ϸ� 10%, 
 $2,000 �ʰ��� 20%�� ����.
 
CREATE OR REPLACE FUNCTION tax3 (p_sal IN emp.sal%type,
                                                     p_bonus IN emp.comm%type)
    RETURN NUMBER IS
    --���� ����
    e_sum NUMBER;
    e_tax NUMBER;
BEGIN
    e_sum := p_sal + NVL(p_bonus, 0);
    IF e_sum < 1000 THEN
    e_tax := e_sum * 0.05;
    ELSIF e_sum <= 2000 THEN
    e_tax := e_sum * 0.1;
    ELSE
    e_tax := e_sum * 0.2;
    END IF;
    RETURN e_tax;
END;

SELECT empno, ename, sal, comm, TAX3(sal, comm) tax FROM emp;


/�����ȣ�� ���ؼ� �޿��� �˷��ִ� �Լ�
--Ư�� ���̺�(emp)�� �����ؼ� ������� �Լ�

CREATE OR REPLACE FUNCTION emp_salaries(emp_no NUMBER)
    RETURN NUMBER IS
    --���� ����
    nSalaries NUMBER(9);
BEGIN
    nSalaries := 0; --�ʱ�ȭ
    SELECT sal
    --������� �������� ���(primary key) INTO�� �̿��ؼ� �о�� ���� ������ ���� �� ����.
    --�������� ��� Ŀ�� ���.
    INTO nSalaries
    FROM emp WHERE empno = emp_no;
    RETURN nSalaries;
END;

SELECT emp_salaries (7839) FROM dual; --7839�� ����� �޿� Ȯ��
--���ʿ� �Լ� ��ü�� emp���̺� �����ϱ⶧���� dual�� �ص� ����.

SELECT empno, ename, emp_salaries(empno) FROM emp;  --�����ȣ�� ���� ��ü ����� �޿� Ȯ��


/�μ���ȣ�� �����ϸ� �μ����� ���� �� �ִ� �Լ�

CREATE OR REPLACE FUNCTION get_dept_name (dept_no NUMBER)
    RETURN VARCHAR2 IS
    --���� ����
    sDeptName VARCHAR2(30);
BEGIN
    SELECT dname
    INTO sDeptName 
    FROM dept
    WHERE deptno = dept_no;
    RETURN sDeptName;
END;

SELECT get_dept_name (10) FROM dual;
SELECT empno, ename, sal, GET_DEPT_NAME (deptno) "Department Name" FROM emp;


[�ǽ�����]

1) �� ���ڸ� �����ϸ� ������ �ؼ� ������� ��ȯ�ϴ� �Լ��� �����Ͻÿ�.
    (�Լ��� : add_num)
    
CREATE OR REPLACE FUNCTION add_num(first_num INTEGER, second_num INTEGER)
    RETURN INTEGER IS
BEGIN
    RETURN first_num + second_num;
END;

SELECT ADD_NUM (10,20) FROM dual;
SELECT ename, ADD_NUM (sal, NVL(comm, 0)) �Ǳ޿� FROM emp;


2) �μ���ȣ�� �Է��ϸ� �ش� �μ����� �ٹ��ϴ� ��� ���� ��ȯ�ϴ� �Լ��� �����Ͻÿ�.
    (�Լ��� : get_emp_count)
    
CREATE OR REPLACE FUNCTION get_emp_count(dept_no emp.deptno%type)
    RETURN INTEGER IS
    emp_count INTEGER;
BEGIN
    SELECT COUNT(empno) 
    INTO emp_count
    FROM emp 
    WHERE deptno = dept_no;
    RETURN emp_count;
END;

SELECT deptno, dname, GET_EMP_COUNT (deptno) ����� FROM dept;


3) emp���̺��� �Ի����� �Է��ϸ� �ٹ������� ���ϴ� �Լ��� �����Ͻÿ�.
    (�Ҽ��� �ڸ��� ���� / �Լ��� : get_info_hiredate)
    
CREATE OR REPLACE FUNCTION get_info_hiredate (hire_date emp.hiredate%type)
    RETURN INTEGER IS  --number�� ����
BEGIN
    RETURN TRUNC (MONTHS_BETWEEN(SYSDATE, hire_date)/12) ;
END;

SELECT ename, GET_INFO_HIREDATE (hiredate) �ٹ����� FROM emp;


4) emp���̺��� �̿��ؼ� �����ȣ�� �Է��ϸ� �ش� ����� ������ �̸��� ���ϴ� �Լ��� �����Ͻÿ�.
    (�Լ��� : get_mgr_name)
    
CREATE OR REPLACE FUNCTION get_mgr_name (emp_no emp.empno%type)
    RETURN VARCHAR2 IS
    mgr_name VARCHAR2(10);
BEGIN 
    SELECT ename
    INTO mgr_name
    FROM emp
    WHERE empno = (SELECT mgr FROM emp WHERE empno = emp_no);
    RETURN mgr_name;
END;

SELECT ename, GET_MGR_NAME (empno) "������ �̸�" FROM emp;


5) emp���̺��� �̿��ؼ� �����ȣ�� �Է��ϸ� �޿� ����� ���ϴ� �Լ��� �����Ͻÿ�.
    (�Լ��� : get_sal_grade) 
    
/CASE�� �̿�
CREATE OR REPLACE FUNCTION get_sal_grade (emp_no emp.empno%type)
    RETURN CHAR IS
    sal_grade CHAR(1);
BEGIN
    SELECT CASE WHEN sal >= 4000 THEN 'A'
                      WHEN sal >= 3000 AND sal<4000 THEN 'B'
                      WHEN sal >= 2000 AND sal<3000 THEN 'C'
                      WHEN sal >= 1000 AND sal<2000 THEN 'D'
                      ELSE 'F'
              END "�޿� ���"  --SQL���� �ȿ����� END CASE (X), END (O)
    INTO sal_grade FROM emp
    WHERE empno = emp_no;
    RETURN sal_grade;
END;

/���� �̿�
SELECT ename, sal, GET_SAL_GRADE (empno) "�޿� ���" FROM emp;
    
CREATE OR REPLACE FUNCTION get_sal_grade (emp_no emp.empno%type)
    RETURN CHAR IS
    sal_grade CHAR(1);
BEGIN
    SELECT s.grade INTO sal_grade FROM emp e, salgrade s
    WHERE e.sal BETWEEN losal AND hisal
    AND e.empno = emp_no;
    RETURN sal_grade;
END;

SELECT ename, sal, GET_SAL_GRADE (empno) "�޿� ���" FROM emp;


6) �����ȣ�� �Է��ϸ� �ٹ����� ���ϴ� �Լ��� �����Ͻÿ�.
    (�Լ��� : find_loc)
    
/�������� �̿�
CREATE OR REPLACE FUNCTION find_loc (emp_no emp.empno%type)
    RETURN VARCHAR2 IS
    f_loc VARCHAR2(14);
BEGIN
    SELECT loc INTO f_loc FROM dept
    WHERE deptno = (SELECT deptno FROM emp WHERE empno =  emp_no); 
    RETURN f_loc;
END;    

SELECT FIND_LOC (7698) FROM dual;
SELECT empno, ename, FIND_LOC (empno) �ٹ��� FROM emp;
    
/���� �̿� 
CREATE OR REPLACE FUNCTION find_loc (emp_no emp.empno%type)
    RETURN VARCHAR2 IS
    f_loc VARCHAR2(14);
BEGIN
    SELECT d.loc INTO f_loc FROM emp e, dept d
    WHERE e.deptno = d.deptno
    AND e.empno = emp_no;    
    RETURN f_loc;
END;

SELECT FIND_LOC (7698) FROM dual;
SELECT empno, ename, FIND_LOC (empno) �ٹ��� FROM emp;


<���ν���>

CREATE OR REPLACE PROCEDURE hello_world --���ڰ� ���� ��� �Ұ�ȣ ���X
IS
    message VARCHAR2(100);
BEGIN
    message := 'Hello World!!';
    DBMS_OUTPUT.PUT_LINE (message);
END;

- ���ν����� ����
EXEC Ȥ�� EXECUTE ���ν�����
EXEC HELLO_WORLD;


CREATE OR REPLACE PROCEDURE hello_world (p_message VARCHAR2)
IS
BEGIN
        DBMS_OUTPUT.PUT_LINE (p_message);
END;

EXEC HELLO_WORLD ('Korea');


/�μ� ���̺� �μ� ������ �Է��ϴ� ���ν����� ����
CREATE OR REPLACE PROCEDURE add_department (p_deptno dept.deptno%type, 
                                                                     p_dname dept.dname%type,
                                                                     p_loc dept.loc%type)
IS
BEGIN
    INSERT INTO dept VALUES (p_deptno, p_dname, p_loc);
    COMMIT;
    
    EXCEPTION WHEN OTHERS THEN  --���̺��� �׼��� �� �� ���� ó�� �ʼ�
        DBMS_OUTPUT.PUT_LINE (p_dname || ' register is failed');
        ROLLBACK;
END;

EXEC ADD_DEPARTMENT (60, 'IT SERVICE', 'BUSAN');


/��� ���̺� ��� ������ �����ϴ� ���ν��� ����
CREATE OR REPLACE PROCEDURE register_emp(e_no number, e_name varchar2,
                                                                e_job varchar2, e_mgr number,
                                                                e_sal number, e_comm number,
                                                                e_deptno number)
IS
BEGIN
    INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
    VALUES (e_no, e_name, e_job, e_mgr, SYSDATE, e_sal, e_comm, e_deptno);
    COMMIT;
    EXCEPTION WHEN OTHERS THEN
       DBMS_OUTPUT.PUT_LINE (e_name || ' register is failed');
       ROLLBACK;
END;

EXECUTE REGISTER_EMP (9000, 'PETER', 'MANAGER', 7902, 6000, 200, 30);


/�μ���ȣ�� ���ؼ� �μ���� �μ��� ��ġ ���ϱ�
CREATE OR REPLACE PROCEDURE output_department (p_deptno dept.deptno%type)
IS
    d_name dept.dname%type;
    d_loc dept.loc%type;
BEGIN
    SELECT dname, loc
    INTO d_name, d_loc 
    FROM dept WHERE deptno = p_deptno;
    DBMS_OUTPUT.PUT_LINE (d_name || ', ' || d_loc);
END;

EXEC OUTPUT_DEPARTMENT (10);


/������ �Է��ϸ� �ش� ������ �Ի��� �����ȣ, �����, �޿��� ��� --�������� ��� Ŀ�� ���
CREATE OR REPLACE PROCEDURE info_hiredate (p_year VARCHAR2)
IS
--%ROWYPE���� ������ Ÿ���� �����Ǿ� �ִ� ��� ���̺�(emp)�� 
--�ϳ��� ���� ������ ��� �÷��� ������ Ÿ���� ������.
    e_emp emp%rowtype;  --Ÿ���� �� ������ �� �������� ������ ��ġ ���ڵ� ���� �Ͱ� ����.
    --Ŀ���� ����
    CURSOR emp_cur IS
    SELECT empno, ename, sal
    FROM emp WHERE TO_CHAR (hiredate, 'YYYY') = p_year;
BEGIN
    OPEN emp_cur;
    --Ŀ���κ��� ������ �б�
    LOOP                          --�÷����� �ν��ؼ� �������
        FETCH emp_cur INTO e_emp.empno, e_emp.ename, e_emp.sal;
        EXIT WHEN emp_cur%notfound;
        DBMS_OUTPUT.PUT_LINE (e_emp.empno || ', ' || e_emp.ename || ', ' || e_emp.sal);
    END LOOP;
    CLOSE emp_cur;
END;

EXEC INFO_HIREDATE ('1981');


/SALES �μ��� ���� ����� ���� ����
CREATE OR REPLACE PROCEDURE emp_info (p_dname dept.dname%type)
IS
--Ŀ�� ����
    CURSOR emp_cur IS
    SELECT empno, ename
    FROM emp e JOIN dept d
    ON e.deptno = d.deptno
    WHERE dname = UPPER(p_dname);
--���� ����
    e_no emp.empno%type;
    e_name emp.ename%type;
BEGIN
    OPEN emp_cur;
    --Ŀ���κ��� ������ �б�
    LOOP
        FETCH emp_cur INTO e_no, e_name;
        EXIT WHEN emp_cur%notfound;
        DBMS_OUTPUT.PUT_LINE (e_no || ', ' || e_name);
    END LOOP;
    CLOSE emp_cur;
END;

EXEC EMP_INFO ('SALES');










