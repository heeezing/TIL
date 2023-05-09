--04/06---------------------------------------------------------------------------------------------------------------------------------------------------------------------

<SELECT 문>
데이터베이스로부터 저장되어 있는 데이터를 검색하는데 사용

- 전체 테이블 명세
SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM emp;
SELECT * FROM emp;

- 특정 열 선택 
SELECT ename,job FROM emp;

- 주석
SELECT * /*주석*/ FROM emp; --주석

- dual
DUAL : 함수 및 계산의 결과를 볼 때 사용할 수 있는 공용(PUBLIC) 테이블
       사용자 데이터가 있는 테이블에서 유래하지 않은 상수 값
       현재 날짜, 시간 정보, 일시적인 산술, 날짜 연산 등에 주로 사용
SELECT ASCII(0) FROM dual;
SELECT SYSDATE FROM dual;
SELECT 7 + 10 FROM dual;

- 산술식
SELECT ename, sal, sal+300 FROM emp;
SELECT ename, sal, (sal+300)*12 FROM emp;

- NULL 값의 정의
NULL : 사용할 수 없거나, 할당되지 않았거나, 알 수 없거나, 적용할 수 없는 값
       (0이나 공백과는 다름)
SELECT empno, ename, job, comm FROM emp; 
- NULL값을 포함하는 산술식은 null로 계산
SELECT empno, ename, sal+comm FROM emp; --ex) 200+(null)=(null)

- 알리아스 (ALIAS) 정의
열 이름을 바꿈
열 이름 바로 뒤에 나옴. 열 이름과 ALIAS 사이에 선택 사항인 as 키워드가 올 수도 있음.
SELECT sal*12 Asal FROM emp; --대소문자 구분x
SELECT sal*12 as Asal FROM emp;

알리아스에 큰 따옴표를 사용하는 경우 --자바 연동 시 사용x. 단어 위주 생성.
- 대소문자 구별을 원할 시
- 공백 포함 시
- _(언더바), #(샵) 등 특수문자 사용 시
- 숫자로 시작할 시
SELECT ename "Name" FROM emp;
SELECT sal*12 "Annual Salary" FROM emp;
SELECT sal*12 "365sal" FROM emp;

- 연결 연산자
열이나 문자열을 다른 열에 연결. 두 개의 세로선(||)으로 나타냄. 결과 열로 문자식을 생성.
SELECT ename || ' has $' || sal FROM emp;  --문자,문자열 구분이 없다. 모두 작은 따옴표 사용.
- 연결 연산자와 NULL값 : 문자열에 NULL값을 결함할 경우 결과는 문자열만 남음. (NULL이 사라짐)
SELECT ename || comm FROM emp;

- DISTINCT : 중복행 삭제 연산자
SELECT DISTINCT deptno FROM emp;
SELECT DISTINCT (deptno) FROM emp;


[실습문제]
1) emp테이블에서 사원번호, 사원이름, 월급을 출력하시오.
SELECT empno, ename, sal FROM emp;

2) emp테이블에서 사원이름,월급을 출력하는데 컬럼명은 "이 름", "월 급"으로 바꿔서 출력하시오.
SELECT ename "이 름",  sal "월 급" FROM emp;

3) emp테이블에서 사원번호, 사원이름, 월급, 연봉을 구하고 각각 컬럼명은 "사원번호", "사원이름", "월급", "연봉"으로 출력하시오.
SELECT empno 사원번호, ename 사원이름,  sal 월급, sal*12 연봉 FROM emp;

4) emp테이블에서 업무를 중복되지 않게 표시하시오.
SELECT DISTINCT job from emp;

5) emp테이블에서 사원명과 업무를 쉼표(,)로 연결해서 표시하고 컬럼명은 "Employee and Job"으로 표시하시오.
SELECT ename || ',' || job "Employee and Job" FROM emp; 


- WHERE절을 이용한 행 제한
  조건 체크 결과 조건에 맞는 행만 읽어 옴.
SELECT * FROM emp WHERE deptno=10;
SELECT * FROM emp WHERE ename='SMITH';
SELECT * FROM emp WHERE hiredate>'81-12-03';  --'/'로도 가능함 

(주의)WHERE절에는 알리아스를 사용할 수 없음!
SELECT ename, sal, sal*12 ansal FROM emp WHERE sal*12 > 15000;

- 비교 연산자의 사용
SELECT * FROM emp WHERE hiredate >= '81/04/02'; --'-'로도 가능함
같지 않으면 true
SELECT * FROM emp WHERE hiredate <> '81-04-02';
SELECT * FROM emp WHERE hiredate ^= '81-04-02';
SELECT * FROM emp WHERE hiredate != '81-04-02';

이상~이하
SELECT * FROM emp WHERE sal >= 2000 AND sal <=5000;

BETWEEN ~ AND : 이상~이하 연산자
SELECT * FROM emp WHERE sal BETWEEN 2000 AND 5000;
SELECT * FROM emp WHERE sal NOT BETWEEN 2000 AND 5000;

IN : 값 목록 중에 값과 일치하는 데이터 읽어옴 
SELECT * FROM emp WHERE sal IN (1300, 2450, 3000); --여러 값 나열 시 소괄호 해야함.
SELECT * FROM emp WHERE sal NOT IN (1300, 2450, 3000);
SELECT ename, mgr, deptno FROM emp WHERE ename IN ('ALLEN', 'FORD');

LIKE : 패턴 검사
% : 0개 이상의 문자를 나타냄
_ : 한 문자를 나타냄

S가 포함되어 있는 문자(열)를 구함
SELECT * FROM emp WHERE ename LIKE '%S%'; --S가 처음, 중간, 끝에 오는 이름
입사일이 22로 끝나는 입사일을 구함
SELECT * FROM emp WHERE hiredate LIKE '%22';
'FOR'뒤에 한 글자가 있는 문자(열)를 구함
SELECT * FROM emp WHERE ename LIKE 'FOR_'; --FOR 다음에 '한' 글자가 있음


[실습문제]
1) emp테이블에서 사원번호가 7698인 사원의 이름, 업무, 급여를 출력하시오.
SELECT ename, job, sal FROM emp WHERE empno = 7698;

2) emp테이블에서 사원이름이 SMITH인 사원의 이름, 월급, 부서번호를 구하시오.
SELECT ename, sal, deptno FROM emp WHERE ename = 'SMITH';

3) 월급이 2500이상 3500미만인 사원의 이름, 입사일, 월급을 구하시오.
SELECT ename, hiredate, sal FROM emp WHERE 2500<=sal AND sal<3500; 

4) 급여가 2000에서 3000 사이에 포함되지 않은 사원의 이름, 업무, 급여를 출력하시오.
SELECT ename, job, sal FROM emp WHERE sal NOT BETWEEN 2000 AND 3000;
SELECT ename, job, sal FROM emp WHERE NOT (2000<=sal AND sal<=3000);
