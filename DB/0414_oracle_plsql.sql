--04/14---------------------------------------------------------------------------------------------------------------------------------------------------------------------

[실습 문제]
1) 업무를 입력하면 해당 업무를 수행하는 사원들의 사원번호, 이름, 급여, 업무를 출력하시오.
    (프로시저명 : job_info)
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


2) 사원번호와 새 업무를 입력하면 emp 테이블의 해당 사원의 업무를 갱신할 수 있는 프로시저를 작성하시오.
    (프로시저명 : change_job / 인자명 : e_no, e_job)
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


3) 부서 이름을 입력 받으면 해당 부서의 사원에 대해 급여가 많은 순으로 정보를 제공하시오.
    (프로시저명 : emp_salary_info / 인자명 : p_dept)
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
        EXIT WHEN emp_cur%notfound;  --커서에서 얻은 레코드 수 반환
                                                    --없는 부서이름을 입력했을 경우 등
        DBMS_OUTPUT.PUT_LINE (e_emp.empno || ', ' || e_emp.ename || ', ' || e_emp.sal);
    END LOOP;       
     IF emp_cur%rowcount = 0 THEN
        DBMS_OUTPUT.PUT_LINE ('검색된 데이터가 없습니다.');
    END IF;
    CLOSE emp_cur;
END;

EXEC EMP_SALARY_INFO ('ACCOUNTING');

