--04/10---------------------------------------------------------------------------------------------------------------------------------------------------------------------

- 조건 비교
1) CASE WHEN THEN ELSE  --if문과 비슷
CASE 컬럼 WHEN 비교값 THEN 결과값
            WHEN 비교값 THEN 결과값
            WHEN 비교값 THEN 결과값
            (ELSE 결과값)
END

/=비교
SELECT ename, sal, job,
          CASE job WHEN 'SALESMAN' THEN sal*0.1
                      WHEN 'MANAGER' THEN sal*0.2
                      WHEN 'ANALYST' THEN sal*0.3
                      ELSE sal*0.4
          END "BONUS"  --알리아스
FROM emp;

/비교연산자 사용 시 WHEN 안에 컬럼명이 들어갈 수 있다.
SELECT ename, sal, job, 
          CASE WHEN sal>=4000 AND sal<=5000 THEN 'A'
                 WHEN sal>=3000 AND sal<4000 THEN 'B'
                 WHEN sal>=2000 AND sal<3000 THEN 'C'
                 WHEN sal>=1000 AND sal<2000 THEN 'D'
                 ELSE 'F'
          END "GRADE"
FROM emp ORDER BY "GRADE", sal DESC;

2) DECODE (ORACLE 전용) : =비교만 가능 --switch문과 비슷
DECODE(컬럼, 비교값, 반환값,
                  비교값, 반환값,
                  비교값, 반환값,
                  반환값)
                  
SELECT ename, sal, job,
          DECODE(job, 'SALESMAN', sal*0.1,
                          'MANAGER', sal*0.2,
                          'ANALYST', sal*0.3,
                          sal*0.4) "Bonus" 
FROM emp;

SELECT ename, sal, job,
          DECODE(TRUNC(sal/1000), 5, 'A',
                                           4, 'A',
                                           3, 'B',
                                           2, 'C',
                                           1, 'D',
                                           'F') "Grade"
FROM emp ORDER BY "GRADE", sal DESC;


- 그룹함수 : 행 집합 연산을 수행하여 그룹별로 하나의 결과를 산출

1) AVG() : NULL을 제외한 모든 값들의 평균을 반환. NULL값은 평균 계산에서 무시됨.
SELECT AVG(sal) FROM emp;

2) COUNT() : NULL을 제외한 값을 가진 모든 레코드의 수를 반환.
              COUNT(*)형식을 사용하면 NULL도 계산에 포함.
SELECT COUNT(*) FROM emp;  --12
SELECT COUNT(comm) FROM emp;  --4

3) MAX() : 레코드 내에 있는 여러 값 중 가장 큰 값을 반환
SELECT MAX(sal) FROM emp;  --숫자
SELECT MAX(ename) FROM emp;  --문자 (Z에 가까울수록 큰 값으로 인식)
SELECT MAX(hiredate) FROM emp;  --날짜 (최근일수록 큰 값으로 인식)

4) MIN() : 레코드 내에 있는 여러 값 중 가장 작은 값을 반환
SELECT MIN(sal) FROM emp;
SELECT MIN(ename) FROM emp;
SELECT MIN(hiredate) FROM emp;

5) SUM() : 레코드들이 포함하고 있는 모든 값을 더하여 반환
SELECT SUM(sal) FROM emp;

SELECT MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp;


- GROUP BY 절 : SELECT절에 집합함수(그룹합수) 적용 시 개별 컬럼은 지정할 수 없기 때문에
                     GROUP BY 절을 이용해서 그룹으로 묶어서 집합 함수를 적용함.
                     그룹(지정한 개별 컬럼)별 집합 함수에 의해 만들어진 값을 반환.
                     (*알리아스 사용X)
SELECT deptno, MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp GROUP BY deptno; --deptno를 기준으로 그룹을 묶겠다는 뜻 
SELECT job, MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp GROUP BY job;
SELECT job, MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp WHERE job='SALESMAN' GROUP BY job;
SELECT deptno, job, SUM(sal) FROM emp GROUP BY deptno, job ORDER BY deptno;

- HAVING 절 : 그룹처리 결과를 제한하고자 할 때 사용. 
                    WHERE절에는 집합함수를 사용할 수 없고 HAVING절 사용.
                    (*알리아스 사용X)
/오류 발생의 경우
SELECT deptno, ROUND(AVG(sal)) FROM emp WHERE ROUND(AVG(sal)) >= 2000 GROUP BY deptno;
/정상 구문
SELECT deptno, ROUND(AVG(sal)) FROM emp GROUP BY deptno HAVING ROUND(AVG(sal))>=2000;
SELECT deptno, MAX(sal) FROM emp GROUP BY deptno HAVING MAX(sal)>3000;

* 분기 별 입사자의 수
/TO_CHAR(hiredate, 'Q') 이용
SELECT TO_CHAR(hiredate, 'Q') AS QUARTER, COUNT(empno) AS employee_number
FROM emp GROUP BY  TO_CHAR(hiredate, 'Q') ORDER BY QUARTER;

/CEIL(EXTRACT(MONTH FROM hiredate)/3) 이용
SELECT CEIL(EXTRACT(MONTH FROM hiredate)/3) AS QUARTER, COUNT(empno) AS count_member
FROM emp GROUP BY CEIL(EXTRACT(MONTH FROM hiredate)/3) ORDER BY QUARTER;


[실습문제]
10) 사원이름, 월급, 월급과 커미션을 더한 값을 컬럼명 '실급여'라고 해서 출력하시오.
     단, NULL값은 나타나지 않게 작성하시오. (NVL 통해 0으로 대체.)
SELECT ename, sal, NVL(sal+comm, 0) 실급여 FROM emp;

11) 월급과 커미션을 합친 금액이 2,000이상인 급여를 받는 사원의 이름, 업무, 월급, 커미션, 고용날짜를 출력하시오.
     단, 고용날짜는 1980-12-17 형태로 출력하시오.
SELECT ename, job, sal, comm, TO_CHAR(hiredate, 'YYYY-MM-DD') hiredate FROM emp WHERE sal+NVL(comm, 0) >= 2000;
     
12) CASE 함수와 DECODE 함수를 사용하여 다음 데이터에 따라 JOB열의 값을 기준으로 모든 사원의 등급을 표시하시오.
ex)    업무             등급
    PRESIDENT          A
     ANALYST           B
    MANAGER         C
    SALSEMAN         D
      CLERK              E
       기타               0

SELECT ename, sal, job,
          CASE job WHEN 'PRESIDENT' THEN 'A'
                        WHEN 'ANALYST' THEN 'B'
                        WHEN 'MANAGER' THEN 'C'
                        WHEN 'SALESMAN' THEN 'D'
                        WHEN 'CLERK' THEN 'E'
                        ELSE '0'
          END GRADE
FROM emp ORDER BY GRADE;

SELECT ename, sal, job,
          DECODE (job, 'PRESIDENT', 'A',
                            'ANALYST', 'B',
                            'MANAGER', 'C',
                            'SALESMAN' ,'D',
                            'CLERK', 'E',
                            '0') GRADE
FROM emp ORDER BY GRADE;


[실습문제]
1) 모든 사원의 급여 최고액, 최저액, 총액 및 평균액을 표시하시오.
   열 레이블을 각각 maximum, minimum, sum, average로 지정하고 결과를 정수로 반올림하고 세자리 단위로 ,(쉼표)를 명시하시오.
SELECT TO_CHAR(MAX(sal), '9,999') "maximum",
           TO_CHAR(MIN(sal), '9,999') "minimum",
           TO_CHAR(SUM(sal), '99,999') "sum",
           TO_CHAR(ROUND(AVG(sal)), '9,999') "average"
FROM emp;

2) 급여와 커미션을 더한 금액의 최고, 최저, 평균금액을 구하시오. 평균금액은 소수점 첫째자리까지 표시하시오.
SELECT MAX(sal+NVL(comm,0)), MIN(sal+NVL(comm,0)), ROUND(AVG(sal+NVL(comm,0)),1) FROM emp;

3) 업무, 업무가 동일한 사원 수를 표시하시오. (업무 별 사원 수를 구하시오.)
SELECT job, COUNT(*) FROM emp GROUP BY job;  --*이 아니더라도 null값이 없는 컬럼명 넣어도 됨.

4) 30번 부서의 사원 수를 구하시오.
SELECT COUNT(*) FROM emp WHERE deptno=30; --사원수만 명시할 경우
SELECT deptno, COUNT(*) FROM emp WHERE deptno=30 GROUP BY deptno; --부서번호도 같이 쓸 경우

5) 업무 별 최고 월급을 구하고 업무, 최고 월급을 출력하시오.
SELECT job, MAX(sal) FROM emp GROUP BY job;

6) 부서 별로 지급되는 총 월급에서 금액이 9,000이상을 받는 사원들의 부서번호, 총 월급을 출력하시오.
SELECT deptno, SUM(sal) FROM emp GROUP BY deptno HAVING SUM(sal)>=9000;

7) 업무 별로 사원 번호가 제일 늦은 사람을 구하고, 그 결과 내에서 사원번호 79로 시작하는 결과만 보여주시오. (업무, 제일 늦은 사원번호 출력)
SELECT job, MAX(empno) FROM emp WHERE empno LIKE '79%' GROUP BY job;
SELECT job, MAX(empno) FROM emp GROUP BY job HAVING MAX(empno) LIKE '79%';

8) 업무 별 총 월급을 출력하는데 업무가 'MANAGER'인 사원은 제외하고 총 월급이 5,000보다 많은 업무와 총 월급만 출력하시오.
SELECT job, SUM(sal) FROM emp WHERE job != 'MANAGER' GROUP BY job HAVING SUM(sal)>5000;
SELECT job, SUM(sal) FROM emp GROUP BY job HAVING SUM(sal)>5000 AND job!='MANAGER';


- 분석 함수
RANK() : 순위를 표현할 때 사용하는 함수

1) RANK(조건값) WITHIN GROUP (ORDER BY 조건값의 컬럼명 [ASC|DESC]) : 특정 데이터의 순위 확인하기
(주의)RANK 뒤에 나오는 데이터와 ORDER BY 뒤에 나오는 데이터는 같은 컬럼이어야 함.

SELECT ename FROM emp ORDER BY ename;  --SMITH는 10번째
SELECT RANK('SMITH') WITHIN GROUP (ORDER BY ename) "RANK" FROM emp;

2) RANK() OVER(정렬값) : 전체 순위 확인하기 --WITHIN GROUP 대신 OVER

ex) 사원들의 empno, ename, sal, 급여 순위 출력
SELECT empno, ename, sal, RANK() OVER (ORDER BY sal) AS RANK_ASC,
          RANK() OVER (ORDER BY sal DESC) AS RANK_DESC 
FROM emp;

ex) 10번 부서에 속한 직원들의 사번과 이름, 급여, 해당 부서 내의 급여 순위를 출력하시오.
SELECT empno, ename, sal, RANK() OVER (ORDER BY sal DESC) RANK FROM emp WHERE deptno=10 ; 

3) PARTITION BY : 그룹 별 순위 확인하기

사번, 이름, 급여, 부서번호, 부서 별 급여 순위를 출력
SELECT empno, ename, sal, deptno, RANK() OVER (PARTITION BY deptno ORDER BY sal DESC) RANK FROM emp;


- JOIN : 둘 이상의 테이블을 연결하여 데이터를 검색하는 방법

카티션 곱 : 검색하고자 했던 데이터 뿐 아니라 조인에 사용된 테이블들의 모든 데이터가 반환되는 현상
SELECT * FROM emp, dept;  --조인 조건 명시 안 함

1. ORACLE 전용

1-1) 동등 조인 (Equi Join) : 조건절에 조건이 Equality Condition(=)에 의해 조인이 이루어짐.
SELECT emp.ename, dept.dname FROM emp, dept WHERE emp.deptno = dept.deptno;

/테이블에 알리아스 부여하기
SELECT e.ename, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno;

/컬럼명을 호출할 때 테이블명 또는 테이블 알리아스를 생략할 수 있음.
(조인에 참가하는 테이블들에 같은 컬럼이 존재하면, 반드시 테이블명 또는 테이블 알리아스를 명시해야 함.)
SELECT ename, dname FROM emp e, dept d WHERE e.deptno = d.deptno;
SELECT ename, dname, deptno FROM emp e, dept d WHERE e.deptno = d.deptno;  --오류

/추가적인 조건 명시하기 (이미 WHERE절을 썼기 때문에 AND 활용)
SELECT e.ename, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno AND e.ename = 'ALLEN';
SELECT e.ename, e.sal, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno AND e.sal BETWEEN 3000 AND 4000;


1-2) 비동등 조인 (Non Equi Join) : 테이블의 어떤 컬럼도 조인할 테이블의 컬럼에 일치하지 않을 때 사용.
                                        조인 조건은 동등(=) 이외의 연산자를 갖음.(BETWEEN AND, IS NULL, IN)
                                        
/사원이름, 급여, 급여등급 구하기 (emp, salgrade 테이블 이용)
SELECT e.ename, e.sal, s.grade FROM emp e, salgrade s WHERE e.sal BETWEEN s.losal AND s.hisal; 
--같은 데이터가 있으면 =를 사용했겠지만, 그렇지 않기 때문에 다른 연산자 사용.


2) 셀프 조인 (Self Join) : 한 테이블 안에 공통의 데이터가 있는 경우 사용.
/사원이름과 해당 사원의 관리자(상사)이름 구하기 (관리자가 없는 사원 제외)
SELECT e.ename 사원이름, m.ename 관리자이름 FROM emp e, emp m WHERE e.mgr = m.empno; 


3) 외부 조인 (Outer Join) : 조인할 때 두 테이블, 두 컬럼에 공통된 값이 없다면 테이블로부터 데이터를 반환하지 않음.
                                  두 개 컬럼에서 공통되지 않은 값(ex.한 컬럼에만 있는 값)을 표시하고 싶을 때 사용.
/누락된 행의 반대 테이블의 조인 조건 컬럼에 (+) 기호 표시
SELECT DISTINCT (e.deptno), d.deptno FROM emp e, dept d WHERE e.deptno(+) = d.deptno;
--dept 테이블의 데이터 40이 누락(공통되지 않아서) -> emp 테이블의 조건에 (+) 표시

/사원이름과 해당 사원의 관리자(상사)이름 구하기 (관리자가 없는 사원 포함)
SELECT e.ename 사원이름, m.ename 관리자이름 FROM emp e, emp m WHERE e.mgr = m.empno(+);


[실습문제]
1) 모든 사원의 이름, 부서번호, 부서이름을 출력하시오. (emp, dept)
SELECT e.ename, e.deptno, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno;

2) 업무가 MANAGER인 사원의 정보를 이름, 업무, 부서명, 근무지 순으로 출력하시오. (emp, dept)
SELECT e.ename, e.job, d.dname, d.loc FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.job = 'MANAGER';

3) 커미션을 받고 급여가 1,600 이상인 사원의 이름, 급여, 부서명, 근무지를 출력하시오. (emp, dept)
SELECT e.ename, e.sal, d.dname, d.loc FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.comm IS NOT NULL AND e.sal >= 1600; 

4) 근무지가 CHICAGO인 모든 사원의 이름, 업무, 부서번호 및 부서이름을 출력하시오. (emp, dept)
SELECT e.ename, e.job, e.deptno, d.dname FROM emp e, dept d
WHERE e.deptno = d.deptno AND d.loc = 'CHICAGO'; 

5) 근무지(LOC) 별로 근무하는 사원의 수가 5명 이하인 경우, 인원이 적은 도시 순으로 정렬하시오.
    (근무 인원이 0명인 곳도 표시하시오.)
SELECT d.loc, COUNT(e.empno) FROM emp e, dept d WHERE e.deptno(+) = d.deptno
GROUP BY d.loc HAVING COUNT(e.empno) <= 5 ORDER BY COUNT(e.deptno);
--(주의) COUNT(*)을 하게 되면 NULL도 세기 때문에 X. 고유의 값 넣어주어야함.


2. 표준 SQL

1) 내부 조인 (Inner Join)
SELECT emp.ename, dept.deptno FROM emp INNER JOIN dept ON emp.deptno = dept.deptno;
/알리아스 부여
SELECT e.ename, d.deptno FROM emp e INNER JOIN dept d ON e.deptno = d.deptno;
/부가적인 조건이 있으면 WHERE 절 사용
SELECT e.ename, d.deptno FROM emp e INNER JOIN dept d ON e.deptno = d.deptno WHERE e.ename = 'ALLEN';

/USING 절 : 조인 조건에 사용된 컬럼의 이름이 같을 때 사용
SELECT e.ename, d.dname FROM emp e INNER JOIN dept d USING(deptno);

(주의) USING에 사용된 컬럼은 테이블명 또는 테이블 알리아스를 붙이지 않음.
SELECT e.ename, deptno FROM emp e INNER JOIN dept d USING(deptno);
--SELECT e.ename, d.deptno FROM emp e INNER JOIN dept d USING(deptno);
--오류. USING에 deptno가 들어가는 순간 두 테이블의 deptno가 합쳐진다고 생각하자.

2) 셀프 조인 (Self Join) 
/사원이름과 해당 사원의 관리자 이름 구하기 (관리자가 없는 사원은 제외)
SELECT e.ename name, m.ename manager_name FROM emp e JOIN emp m ON e.mgr = m.empno;

3) 외부 조인 (Outer Join) : 누락된 행의 방향 표시
/사원이름과 해당 사원의 관리자 이름 구하기 (관리자가 없는 사원도 포함)           --누락된 행
SELECT e.ename name, m.ename manager_name FROM emp e LEFT OUTER JOIN emp m ON e.mgr = m.empno;


[실습문제]
6) 업무가 SALESMAN인 사원의 정보를 이름, 업무, 부서명, 근무지 순으로 출력하시오. (emp, dept)
SELECT e.ename, e.job, d.dname, d.loc FROM emp e INNER JOIN dept d ON e.deptno = d.deptno AND e.job = 'SALESMAN';

7) 근무지가 DALLAS인 모든 사원의 이름, 업무, 부서번호, 부서이름을 출력하시오.
SELECT e.ename, e.job, d.deptno, d.dname FROM emp e INNER JOIN dept d ON e.deptno = d.deptno AND d.loc = 'DALLAS';

8) 관리자보다 먼저 입사한 모든 사원의 이름 및 입사일을 관리자의 이름 및 입사일과 함께 표시하고
    열 레이블을 각각 employee, emp hired, manager, mgr hired로 지정하시오. (관리자가 없는 사원 미출력)
SELECT e.ename "employee", e.hiredate "emp hired", m.ename "manager", m.hiredate "mgr hired"
FROM emp e JOIN emp m ON e.mgr = m.empno WHERE e.hiredate < m.hiredate;
