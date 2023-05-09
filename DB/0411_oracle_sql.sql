--04/11---------------------------------------------------------------------------------------------------------------------------------------------------------------------

9) 10번 부서에서 근무하는 사원들의 부서번호, 부서이름, 사원이름, 월급, 급여등급을 출력하시오.
SELECT d.deptno, d.dname, e.ename, e.sal, s.grade FROM dept d, emp e, salgrade s
WHERE e.deptno = d.deptno
AND e.sal BETWEEN s.losal AND s.hisal
AND e.deptno = 10;

SELECT d.deptno, d.dname, e.ename, e.sal, s.grade FROM dept d JOIN emp e ON e.deptno = d.deptno
JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal
WHERE e.deptno = 10;


<집합 연산자> : 테이블 행 단위 연산

1) UNION (합집합 중복값 제거) : 두 테이블의 결합을 나타내며, 결합시키는 두 테이블의 중복되지 않는 값들을 반환.
SELECT deptno FROM emp
UNION
SELECT deptno FROM dept;

2) UNION ALL (합집합) : UNION과 같으나 두 테이블의 중복되는 값까지 반환.
SELECT deptno FROM emp
UNION ALL
SELECT deptno FROM dept;

3) INTERSECT (교집합) : 두 행의 집합 중 공통된 행을 반환
SELECT deptno FROM emp 
INTERSECT
SELECT deptno FROM dept;

4) MINUS (차집합) : 첫번째 SELECT문에 의해 반환되는 행 중에서 
                          두번째 SELECT문에 의해 반환되는 행에 존재하지 않는 행들을 보여줌.
SELECT deptno FROM dept
MINUS
SELECT deptno FROM emp;


<SUBQUERY> : 다른 하나의 SQL 문장의 절에 nested된 SELECT 문장

1) 단일행 서브쿼리 : 항상 한 개의 행(값)을 반환
--절이 다중행이거나(deptno) 새로운 값이 생길 수 있는 경우(ename)는 쓸 수 X
SELECT empno, ename, job FROM emp 
WHERE job = (SELECT job FROM emp WHERE empno = 7369);

SELECT empno, ename, sal FROM emp
WHERE sal > (SELECT sal FROM emp WHERE empno=7698);

2) 다중행 서브쿼리 : 하나 이상의 행을 반환

- IN 연산자의 사용 (= / OR)
/부서 별로 가장 급여를 적게 받는 사원과 동일한 급여를 받는 사원의 정보를 출력하시오.
SELECT empno, ename, sal FROM emp
WHERE sal IN (SELECT MIN(sal) FROM emp GROUP BY deptno);

- ANY 연산자의 사용 (>,< 등 / OR)
: 서브쿼리의 결과값 중 어느 하나의 값이라도 만족이 되면 결과값을 반환
--(=)비교 일 경우엔 IN 사용. 
SELECT sal FROM emp WHERE job = 'SALESMAN';
SELECT ename, sal FROM emp WHERE sal >1250 OR sal > 1500 OR sal > 1600;
/위의 SQL문을 서브쿼리 형식으로 작성
SELECT ename, sal FROM emp 
WHERE sal > ANY (SELECT sal FROM emp WHERE job = 'SALESMAN');

- ALL 연산자의 사용 (>,< 등 / AND)
: 서브쿼리의 결과와 모든 값이 일치
SELECT sal FROM emp WHERE deptno=20;
SELECT empno, ename, sal, deptno FROM emp WHERE sal>800 AND sal>2975 AND sal>3000;
/위의 SQL문을 서브쿼리 형식으로 작성
SELECT empno, ename, sal, deptno FROM emp
WHERE sal > ALL (SELECT sal FROM emp WHERE deptno=20);

3) 다중열 서브쿼리 : 서브쿼리의 결과가 두 개 이상의 컬럼으로 반환되어 메인 쿼리에 전달
SELECT empno, ename, sal, deptno FROM emp
WHERE (deptno, sal) IN (SELECT deptno, sal FROM emp WHERE deptno=30);
/부서 별 가장 급여를 적게 받는 사원의 정보를 출력하시오.
SELECT empno, ename, sal, deptno FROM emp
WHERE (deptno, sal) IN (SELECT deptno, MIN(sal) FROM emp GROUP BY deptno);
/SMITH사원과 부서번호, 급여가 같은 사람의 정보를 출력하시오. (=SMITH)
SELECT empno, ename, sal, deptno FROM emp
WHERE (deptno, sal) IN (SELECT deptno, sal FROM emp WHERE ename = 'SMITH');

[실습문제]
1) "BLAKE"와 같은 부서에 있는 사원들의 이름과 입사일을 구하는데, "BLAKE"는 제외하고 출력하시오.
SELECT ename, hiredate FROM emp
WHERE deptno = (SELECT deptno FROM emp WHERE ename = 'BLAKE') AND ename != 'BLAKE';

2) 평균 급여보다 많은 급여를 받는 사원들의 사원번호, 이름, 월급을 출력하는데 월급이 높은 사람 순으로 출력하시오.
SELECT empno, ename, sal FROM emp
WHERE sal > (SELECT ROUND(AVG(sal)) FROM emp) ORDER BY sal DESC;

3) 10번 부서에서 급여를 가장 적게 받는 사원과 동일한 급여를 받는 사원의 이름과 월급을 출력하시오.
SELECT ename, sal FROM emp
WHERE sal = (SELECT MIN(sal) FROM emp WHERE deptno=10);

4) 부서 별 사원 수를 구하고 사원 수가 3명이 넘는 부서의 부서명과 사원 수를 출력하시오.
[JOIN]
SELECT d.dname, COUNT(e.empno) FROM emp e, dept d
WHERE e.deptno(+) = d.deptno GROUP BY d.dname HAVING COUNT(e.empno)>3;

[SUBQUERY]                                                   --하나의 테이블을 생성했다고 보면 됨(Inline View)              알리아스
SELECT d.dname, NVL(e.cnt,0) FROM dept d, (SELECT deptno, COUNT(empno) cnt FROM emp GROUP BY deptno) e
WHERE d.deptno = e.deptno(+) AND e.cnt>3;

5) 사원번호가 7844인 사원보다 빨리 입사한 사원의 이름과 입사일을 출력하시오.
SELECT ename, hiredate FROM emp
WHERE hiredate < (SELECT hiredate FROM emp WHERE empno = 7844);

6) 직속 상사(mgr)가 KING인 모든 사원의 이름과 급여를 출력하시오.
SELECT ename, sal FROM emp
WHERE mgr IN (SELECT empno FROM emp WHERE ename = 'KING');

7) 20번 부서에서 가장 급여를 많이 받는 사원과 동일한 급여를 받는 사원의 이름, 부서명, 급여, 급여 등급을 출력하시오.
SELECT e.ename, d.dname, e.sal, s.grade FROM emp e, dept d, salgrade s
WHERE e.deptno = d.deptno
AND e.sal BETWEEN s.losal AND s.hisal
AND e.sal = (SELECT MAX(sal) FROM emp WHERE deptno = 20);

8) 총 급여(sal+comm)가 평균 급여보다 많은 급여를 받는 사원의 부서번호, 이름, 총 급여, 커미션을 출력하시오.
    (커미션은 유(O), 무(X)로 표시하고, 컬럼명은 "comm유무"로 출력)
SELECT deptno, ename, sal+NVL(comm,0), NVL2(comm,'O','X') "comm유무" FROM emp
WHERE sal+NVL(comm,0) > (SELECT ROUND(AVG(sal)) FROM emp);

SELECT deptno, ename, sal+NVL(comm,0), 
            CASE WHEN comm IS NOT NULL THEN 'O'
                    ELSE 'X'
            END "comm 유무"
FROM emp
WHERE sal+NVL(comm,0) > (SELECT ROUND(AVG(sal)) FROM emp);

9) CHICAGO 지역에서 근무하는 사원의 평균 급여보다 높은 급여를 받는 사원의 이름, 급여, 지역명을 출력하시오.
SELECT e.ename, e.sal, d.loc FROM emp e, dept d
WHERE e.deptno = d.deptno
AND e.sal > (SELECT ROUND(AVG(e.sal)) FROM emp e, dept d
                 WHERE e.deptno = d.deptno AND d.loc = 'CHICAGO');

10) 커미션이 없는 사원들 중 월급이 가장 높은 사원의 급여 등급을 출력하시오.
SELECT e.ename, s.grade FROM emp e, salgrade s
WHERE e.sal BETWEEN s.losal AND s.hisal
AND e.sal = (SELECT MAX(sal) FROM emp WHERE comm IS NULL);

11) ALLEN보다 급여를 많이 받는 사람 중에서 입사일이 가장 빠른 사원과 동일한 날짜에 입사한 사원의 이름, 입사일, 급여를 출력하시오.
SELECT ename, hiredate, sal FROM emp
WHERE hiredate = (SELECT MIN(hiredate) FROM emp
                          WHERE sal > (SELECT sal FROM emp
                                            WHERE ename = 'ALLEN'));


<INSERT 문> : 테이블에 행을 삽입

- 전체 데이터 삽입 (전체 컬럼 명시)
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (8000, 'DENNIS', 'SALESMAN', 7698, '99/01/22', 1700, 200, 30);

/전체 데이터를 삽입할 때는 컬럼명 생략 가능
INSERT INTO emp
VALUES (8001, 'MARIA', 'CLERK', 7839, '99/02/02', 1500, NULL, 20);

/값이 입력되지 않는 컬럼은 제외
INSERT INTO emp (empno, ename, job,mgr, hiredate, sal, deptno)  --comm을 뺌
VALUES (8002, 'PETER', 'CLERK', 7698, '99/03/01', 1000, 30);


<UPDATE 문> : 행 단위로 데이터 갱신
--(주의) WHERE절을 입력하지 않으면 모든 데이터가 다 갱신됨.
UPDATE emp SET mgr=7900 WHERE empno=8000;  --8000번 사원의 상사번호를 7900으로 갱신
UPDATE emp SET ename='JOHN', sal=1800, comm=500 WHERE empno=8000;


<DELETE 문> : 행 삭제
--(주의) WHERE절을 입력하지 않으면 모든 데이터가 다 삭제됨.
DELETE FROM emp WHERE empno=8000;


<데이터베이스 트랜잭션>
: 데이터 처리의 한 단위. 오라클 서버에서 발생하는 SQL문들을 하나의 논리적인 작업 단위로써 성공하거나 실패하는 일련의 SQL문.

1) 트랜잭션의 시작
실행 가능한 SQL문장이 제일 처음 실행될 때

2) 트랜잭션의 종료
- COMMIT or ROLLBACK 실행
- DDL or DCL 문장 실행 (자동 COMMIT 됨)
- 기계 장애 또는 시스템 충돌
- 사용자가 정상 종료

3) 자동 COMMIT 은 다음의 경우 발생
- DDL, DCL 문장이 완료될 때
- 명시적인 COMMIT 이나 ROLLBACK 없이 SQL*PLUS 를 정상 종료했을 경우

4) 자동 ROLLBACK 은 다음의 경우 발생
- SQL*PLUS를 비정상 종료했을 경우
- SYSTEM FAILURE 

* COMMIT (변경사항 저장) & ROLLBACK (변경사항 취소) 의 장점
- 데이터의 일관성을 제공
- 데이터를 영구적으로 변경하기 전에 데이터 변경을 확인하게 함
- 관련된 작업을 논리적으로 그룹화함


<테이블> : 기본적인 데이터 저장 단위. 레코드와 컬럼으로 구성.
- 레코드(RECORD, ROW) : 테이블의 데이터는 행에 저장
- 컬럼(COLUMN) : 테이블의 각 컬럼은 데이터를 구별할 수 있는 속성을 표현

/오라클 데이터베이스의 테이블
- 사용자 테이블
- 데이터 딕셔너리
1) 사용자가 소유한 테이블의 이름 구하기
SELECT table_name FROM user_tables;

2) 사용자가 소유한 개별 객체 유형 구하기
SELECT DISTINCT object_type FROM user_objects;

3) 사용자가 소유한 테이블, 뷰, 동의어 및 시퀀스 구하기
SELECT * FROM user_catalog;  --아직까지 뷰, 동의어, 시퀀스 안 만들어서 안 뜸

/테이블 생성
1) 테이블 이름 : 만들어질 테이블의 이름
2) 열 이름 : 테이블 내에 만들어질 열의 이름
3) 데이터 타입 : 각각의 열은 자신의 데이터 타입을 가짐
4) default <표현식> : 각각의 열에는 insert 구문에 열의 값이 지정되지 않을 경우에 이용될 디폴트 값을 지정할 수 있음.
5) 제약 조건 : 만들어질 각 열에 선택적으로 제약 조건을 정의할 수 있다.

CREATE TABLE employee(  --테이블을 만드는 DDL문장
    empno number (6),  --10진자릿수6 = 100000 / 사이즈 지정 안 할 시 최대값 적용
    name varchar2 (30) not null,  --한글 기준 10자 / 사이즈 지정 필수
    salary number (8, 2),  --10진자릿수8, 소숫점 둘째자리
    hire_date date default sysdate,  --디폴트값 부여
    constraint employee_pk primary key (empno)  --제약조건 / employee_pk : 오라클 내부 식별자
);
