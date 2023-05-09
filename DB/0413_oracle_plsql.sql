--04/13---------------------------------------------------------------------------------------------------------------------------------------------------------------------
--LOOP문에서 if문 외에 코드를 줄이는 방식
DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    test_number := 1;  
    LOOP
        result_num := 2* test_number;
        EXIT WHEN result_num > 20;  --해당 조건 시 loop블럭을 빠져나감
        DBMS_OUTPUT.PUT_LINE (result_num);
        test_number := test_number + 1;
    END LOOP;
END;


2. WHILE ~ LOOP 문

DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    test_number := 1;
    result_num := 0;  --while에서 조건으로 사용하므로 미리 초기화
    WHILE result_num < 20 LOOP
        result_num := 2 * test_number;
        DBMS_OUTPUT.PUT_LINE (result_num);
        test_number := test_number + 1;
    END LOOP;
END;


3. FOR ~ LOOP 문

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
    FOR test_number IN REVERSE 1..10 LOOP  --REVERSE -> 역순
        result_num := 2 * test_number;
        DBMS_OUTPUT.PUT_LINE (result_num);
    END LOOP;
END;


<커서>
: SELECT 문장을 실행하면 조건에 따른 결과가 추출되고, 추출 결과는 한 건이 될 수도 여러 건이 될 수도 있으므로
 이를 결과 셋(RESULT SET) 혹은 결과 집합이라고 부름.
 결과는 메모리 상에 위치하게 되는데 PL/SQL에서는 바로 커서(CURSOR)를 사용하여 이 결과 집합에 접근함.

DECLARE
    --커서 선언
    CURSOR emp_csr IS
    SELECT empno FROM emp WHERE deptno=10;
    emp_no emp.empno%type;  --%type을 이용해 emp테이블 empno컬럼의 자료형을 읽어옴.
BEGIN
    OPEN emp_csr;   
    LOOP
        FETCH emp_csr INTO emp_no;
        --%notfound : 커서에서만 사용 가능한 속성
        --                더 이상 패치(할당)할 row가 없음을 의미
        EXIT WHEN emp_csr%notfound;
        DBMS_OUTPUT.PUT_LINE (emp_no);
    END LOOP;       
    CLOSE emp_csr;
END;


<함수>

/입력받은 값으로부터 10%의 세율을 얻는 함수
--                                        함수명    인자&자료형
CREATE OR REPLACE FUNCTION tax (p_value IN NUMBER)  --자료형 사이즈 명시x
    RETURN NUMBER IS  --자료형 사이즈 명시x
BEGIN
    RETURN p_value * 0.1;
END;

SELECT TAX(100) FROM dual;
SELECT ename, sal, TAX(sal) tax, sal-TAX(sal) "실 지급 급여" FROM emp;


/급여와 커미션을 합쳐서 세금 계산

CREATE OR REPLACE FUNCTION tax2(p_sal IN emp.sal%type,
                                                   p_bonus emp.comm%type) --IN 생략 가능
    RETURN NUMBER IS
BEGIN
    RETURN (p_sal + NVL(p_bonus, 0)) * 0.1;
END;

SELECT empno, ename, sal, comm, TAX2(sal, comm) tax FROM emp;


/급여 (보너스 포함)에 대한 세율을 다음과 같이 정의함.
 급여가 월 $1,000미만이면 세율을 5%, $1,000 이상 $2,000 이하면 10%, 
 $2,000 초과면 20%를 적용.
 
CREATE OR REPLACE FUNCTION tax3 (p_sal IN emp.sal%type,
                                                     p_bonus IN emp.comm%type)
    RETURN NUMBER IS
    --변수 선언
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


/사원번호를 통해서 급여를 알려주는 함수
--특정 테이블(emp)에 의존해서 만들어진 함수

CREATE OR REPLACE FUNCTION emp_salaries(emp_no NUMBER)
    RETURN NUMBER IS
    --변수 선언
    nSalaries NUMBER(9);
BEGIN
    nSalaries := 0; --초기화
    SELECT sal
    --결과행이 단일행일 경우(primary key) INTO를 이용해서 읽어온 값을 변수에 담을 수 있음.
    --다중행일 경우 커서 사용.
    INTO nSalaries
    FROM emp WHERE empno = emp_no;
    RETURN nSalaries;
END;

SELECT emp_salaries (7839) FROM dual; --7839번 사원의 급여 확인
--애초에 함수 자체가 emp테이블에 의존하기때문에 dual에 해도 가능.

SELECT empno, ename, emp_salaries(empno) FROM emp;  --사원번호에 따른 전체 사원의 급여 확인


/부서번호를 전달하면 부서명을 구할 수 있는 함수

CREATE OR REPLACE FUNCTION get_dept_name (dept_no NUMBER)
    RETURN VARCHAR2 IS
    --변수 선언
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


[실습문제]

1) 두 숫자를 제공하면 덧셈을 해서 결과값을 반환하는 함수를 정의하시오.
    (함수명 : add_num)
    
CREATE OR REPLACE FUNCTION add_num(first_num INTEGER, second_num INTEGER)
    RETURN INTEGER IS
BEGIN
    RETURN first_num + second_num;
END;

SELECT ADD_NUM (10,20) FROM dual;
SELECT ename, ADD_NUM (sal, NVL(comm, 0)) 실급여 FROM emp;


2) 부서번호를 입력하면 해당 부서에서 근무하는 사원 수를 반환하는 함수를 정의하시오.
    (함수명 : get_emp_count)
    
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

SELECT deptno, dname, GET_EMP_COUNT (deptno) 사원수 FROM dept;


3) emp테이블의 입사일을 입력하면 근무연차를 구하는 함수를 정의하시오.
    (소수점 자리는 절삭 / 함수명 : get_info_hiredate)
    
CREATE OR REPLACE FUNCTION get_info_hiredate (hire_date emp.hiredate%type)
    RETURN INTEGER IS  --number도 가능
BEGIN
    RETURN TRUNC (MONTHS_BETWEEN(SYSDATE, hire_date)/12) ;
END;

SELECT ename, GET_INFO_HIREDATE (hiredate) 근무연차 FROM emp;


4) emp테이블을 이용해서 사원번호를 입력하면 해당 사원의 관리자 이름을 구하는 함수를 정의하시오.
    (함수명 : get_mgr_name)
    
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

SELECT ename, GET_MGR_NAME (empno) "관리자 이름" FROM emp;


5) emp테이블을 이용해서 사원번호를 입력하면 급여 등급을 구하는 함수를 정의하시오.
    (함수명 : get_sal_grade) 
    
/CASE문 이용
CREATE OR REPLACE FUNCTION get_sal_grade (emp_no emp.empno%type)
    RETURN CHAR IS
    sal_grade CHAR(1);
BEGIN
    SELECT CASE WHEN sal >= 4000 THEN 'A'
                      WHEN sal >= 3000 AND sal<4000 THEN 'B'
                      WHEN sal >= 2000 AND sal<3000 THEN 'C'
                      WHEN sal >= 1000 AND sal<2000 THEN 'D'
                      ELSE 'F'
              END "급여 등급"  --SQL문장 안에서는 END CASE (X), END (O)
    INTO sal_grade FROM emp
    WHERE empno = emp_no;
    RETURN sal_grade;
END;

/조인 이용
SELECT ename, sal, GET_SAL_GRADE (empno) "급여 등급" FROM emp;
    
CREATE OR REPLACE FUNCTION get_sal_grade (emp_no emp.empno%type)
    RETURN CHAR IS
    sal_grade CHAR(1);
BEGIN
    SELECT s.grade INTO sal_grade FROM emp e, salgrade s
    WHERE e.sal BETWEEN losal AND hisal
    AND e.empno = emp_no;
    RETURN sal_grade;
END;

SELECT ename, sal, GET_SAL_GRADE (empno) "급여 등급" FROM emp;


6) 사원번호를 입력하면 근무지를 구하는 함수를 정의하시오.
    (함수명 : find_loc)
    
/서브쿼리 이용
CREATE OR REPLACE FUNCTION find_loc (emp_no emp.empno%type)
    RETURN VARCHAR2 IS
    f_loc VARCHAR2(14);
BEGIN
    SELECT loc INTO f_loc FROM dept
    WHERE deptno = (SELECT deptno FROM emp WHERE empno =  emp_no); 
    RETURN f_loc;
END;    

SELECT FIND_LOC (7698) FROM dual;
SELECT empno, ename, FIND_LOC (empno) 근무지 FROM emp;
    
/조인 이용 
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
SELECT empno, ename, FIND_LOC (empno) 근무지 FROM emp;


<프로시저>

CREATE OR REPLACE PROCEDURE hello_world --인자가 없을 경우 소괄호 명시X
IS
    message VARCHAR2(100);
BEGIN
    message := 'Hello World!!';
    DBMS_OUTPUT.PUT_LINE (message);
END;

- 프로시저의 실행
EXEC 혹은 EXECUTE 프로시저명
EXEC HELLO_WORLD;


CREATE OR REPLACE PROCEDURE hello_world (p_message VARCHAR2)
IS
BEGIN
        DBMS_OUTPUT.PUT_LINE (p_message);
END;

EXEC HELLO_WORLD ('Korea');


/부서 테이블에 부서 정보를 입력하는 프로시저를 생성
CREATE OR REPLACE PROCEDURE add_department (p_deptno dept.deptno%type, 
                                                                     p_dname dept.dname%type,
                                                                     p_loc dept.loc%type)
IS
BEGIN
    INSERT INTO dept VALUES (p_deptno, p_dname, p_loc);
    COMMIT;
    
    EXCEPTION WHEN OTHERS THEN  --테이블을 액세스 할 땐 예외 처리 필수
        DBMS_OUTPUT.PUT_LINE (p_dname || ' register is failed');
        ROLLBACK;
END;

EXEC ADD_DEPARTMENT (60, 'IT SERVICE', 'BUSAN');


/사원 테이블에 사원 정보를 저장하는 프로시저 정의
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


/부서번호를 통해서 부서명과 부서의 위치 구하기
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


/연도를 입력하면 해당 연도에 입사한 사원번호, 사원명, 급여를 출력 --다중행의 경우 커서 사용
CREATE OR REPLACE PROCEDURE info_hiredate (p_year VARCHAR2)
IS
--%ROWYPE으로 데이터 타입이 지정되어 있는 사원 테이블(emp)의 
--하나의 행이 가지는 모든 컬럼의 데이터 타입을 가져옴.
    e_emp emp%rowtype;  --타입을 행 단위로 쫙 가져오기 때문에 마치 레코드 만든 것과 같다.
    --커서의 선언
    CURSOR emp_cur IS
    SELECT empno, ename, sal
    FROM emp WHERE TO_CHAR (hiredate, 'YYYY') = p_year;
BEGIN
    OPEN emp_cur;
    --커서로부터 데이터 읽기
    LOOP                          --컬럼으로 인식해서 집어넣음
        FETCH emp_cur INTO e_emp.empno, e_emp.ename, e_emp.sal;
        EXIT WHEN emp_cur%notfound;
        DBMS_OUTPUT.PUT_LINE (e_emp.empno || ', ' || e_emp.ename || ', ' || e_emp.sal);
    END LOOP;
    CLOSE emp_cur;
END;

EXEC INFO_HIREDATE ('1981');


/SALES 부서에 속한 사원의 정보 보기
CREATE OR REPLACE PROCEDURE emp_info (p_dname dept.dname%type)
IS
--커서 선언
    CURSOR emp_cur IS
    SELECT empno, ename
    FROM emp e JOIN dept d
    ON e.deptno = d.deptno
    WHERE dname = UPPER(p_dname);
--변수 선언
    e_no emp.empno%type;
    e_name emp.ename%type;
BEGIN
    OPEN emp_cur;
    --커서로부터 데이터 읽기
    LOOP
        FETCH emp_cur INTO e_no, e_name;
        EXIT WHEN emp_cur%notfound;
        DBMS_OUTPUT.PUT_LINE (e_no || ', ' || e_name);
    END LOOP;
    CLOSE emp_cur;
END;

EXEC EMP_INFO ('SALES');










