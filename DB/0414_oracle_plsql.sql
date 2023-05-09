--04/14---------------------------------------------------------------------------------------------------------------------------------------------------------------------

[�ǽ� ����]
1) ������ �Է��ϸ� �ش� ������ �����ϴ� ������� �����ȣ, �̸�, �޿�, ������ ����Ͻÿ�.
    (���ν����� : job_info)
CREATE OR REPLACE PROCEDURE job_info (e_job emp.job%type)
IS
    CURSOR emp_cur IS
    SELECT empno, ename, sal, job FROM emp
    WHERE job = UPPER(e_job);   
    e_emp emp%rowtype;
BEGIN
    OPEN emp_cur;
    LOOP
        FETCH emp_cur INTO e_emp.empno, e_emp.ename, e_emp.sal, e_emp.job;
        EXIT WHEN emp_cur%notfound;
        DBMS_OUTPUT.PUT_LINE (e_emp.empno || ', ' || e_emp.ename || ', ' || e_emp.sal || ', ' || e_emp.job);
    END LOOP;
    CLOSE emp_cur;
END;

EXEC JOB_INFO ('MANAGER');


2) �����ȣ�� �� ������ �Է��ϸ� emp ���̺��� �ش� ����� ������ ������ �� �ִ� ���ν����� �ۼ��Ͻÿ�.
    (���ν����� : change_job / ���ڸ� : e_no, e_job)
CREATE OR REPLACE PROCEDURE change_job (e_no emp.empno%type,
                                                               e_job emp.job%type)
IS
BEGIN
    UPDATE emp SET job = UPPER(e_job) WHERE empno = e_no;
    COMMIT;
    EXCEPTION WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE (e_no || ' update is failed');
    ROLLBACK;
END;

EXEC CHANGE_JOB (7369, 'DRIVER');


3) �μ� �̸��� �Է� ������ �ش� �μ��� ����� ���� �޿��� ���� ������ ������ �����Ͻÿ�.
    (���ν����� : emp_salary_info / ���ڸ� : p_dept)
CREATE OR REPLACE PROCEDURE emp_salary_info (p_dept dept.dname%type)
IS
    CURSOR emp_cur IS
    SELECT e.empno, e.ename, e.sal
    FROM emp e JOIN dept d
    ON e.deptno = d.deptno
    WHERE d.dname = UPPER(p_dept)
    ORDER BY sal DESC;
    e_emp emp%rowtype;
BEGIN
    OPEN emp_cur;
    LOOP
        FETCH emp_cur INTO e_emp.empno, e_emp.ename, e_emp.sal;
        EXIT WHEN emp_cur%notfound;  --Ŀ������ ���� ���ڵ� �� ��ȯ
                                                    --���� �μ��̸��� �Է����� ��� ��
        DBMS_OUTPUT.PUT_LINE (e_emp.empno || ', ' || e_emp.ename || ', ' || e_emp.sal);
    END LOOP;       
     IF emp_cur%rowcount = 0 THEN
        DBMS_OUTPUT.PUT_LINE ('�˻��� �����Ͱ� �����ϴ�.');
    END IF;
    CLOSE emp_cur;
END;

EXEC EMP_SALARY_INFO ('ACCOUNTING');

