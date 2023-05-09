--04/07---------------------------------------------------------------------------------------------------------------------------------------------------------------------

- NULL 조건 사용
SELECT * FROM emp WHERE comm IS NULL;  --comm = null 불가
SELECT * FROM emp WHERE comm IS NOT NULL;


<논리 연산자> - AND, OR, NOT

1) AND : 구성 요소 조건이 모두 TRUE이면 TRUE를 반환
SELECT empno, ename, job, sal FROM emp WHERE sal>=2000 AND job LIKE '%MAN%';

2) OR : 조건 중 하나가 TRUE이면 TRUE를 반환
SELECT empno, ename, job, sal FROM emp WHERE sal>=2000 OR job LIKE '%MAN%';

3) NOT 
SELECT ename, job FROM emp WHERE job NOT IN ('CLERK', 'SALESMAN');


[실습문제]
5) 81년 05월 01일과 81년 12월 03일 사이에 입사한 사원의 이름, 급여, 일사일을 출력하시오.
SELECT ename, sal, hiredate FROM emp WHERE hiredate BETWEEN '81-05-01' AND '81-12-03';

6) 사원번호가 7566, 7782, 7934인 사원을 제외한 사람들의 사원번호, 이름, 월급을 출력하시오.
SELECT empno, ename, sal FROM emp WHERE empno NOT IN (7566, 7782, 7934);

7) 부서번호(deptno) 30에서 근무하며 월 2,000달러 이하를 받는 81년 05월 01일 이전에 입사한 사원의 이름, 급여, 부서번호, 입사일을 출력하시오.
SELECT ename, sal, deptno, hiredate FROM emp WHERE deptno = 30 AND sal<=2000 AND hiredate<'81-05-01';

8) 급여가 2,000 ~ 5,000달러 사이고 부서번호가 10 또는 30인 사원의 이름, 급여, 부서번호를 출력하시오.
SELECT ename, sal, deptno FROM emp WHERE (sal BETWEEN 2000 AND 5000) AND deptno IN (10, 30);

9) 업무가 SALESMAN 또는 MANAGER이면서 급여가 1,600과 2,850이 아닌 모든 사원의 이름, 업무, 급여를 출력하시오. 
SELECT ename, job, sal FROM emp WHERE job IN ('SALESMAN', 'MANAGER') AND sal NOT IN (1600, 2850);


< ORDER BY 절> : 정렬

1) 오름차순 정렬
SELECT *FROM emp ORDER BY sal;
SELECT *FROM emp ORDER BY sal ASC;
2) 내림차순 정렬
SELECT * FROM emp ORDER BY sal DESC;
SELECT * FROM emp WHERE deptno=10 ORDER BY sal DESC;

/정렬 시 기준이 되는 컬럼의 값이 중복되면 2차 정렬 가능
SELECT * FROM emp ORDER BY sal ASC, ename ASC;

/열 알리아스를 기준으로 정렬
SELECT empno, ename, sal*12 annsal FROM emp ORDER BY annsal;

/열의 숫자 위치를 사용하여 정렬
SELECT ename, job, deptno, hiredate FROM emp ORDER BY 3;  --index3인 deptno기준으로 정렬 (자바랑 달리 1부터 시작)

/테이블에 생성된 순서대로 열 숫자 위치 부여
SELECT * FROM emp ORDER BY 2; --가독성을 위해 *보다는 컬럼명을 써주는 게 좋음

NULLS FIRST 또는 NULLS LAST : 반환된 행 중 NULL값을 포함하는 행이 정렬 순서 상 맨 처음에 나타나거나 마지막에 나타나도록 지정
SELECT * FROM emp ORDER BY comm NULLS FIRST;
SELECT * FROM emp ORDER BY comm DESC NULLS LAST;


[실습문제]
1) 사원번호, 사원이름, 입사일을 출력하는데 입사일이 빠른 사람 순으로 정렬하시오.
SELECT empno, ename, hiredate FROM emp ORDER BY hiredate;

2) 사원이름, 급여, 연봉을 구하고 연봉이 많은 순으로 정렬하시오.
SELECT ename, sal, sal*12 ansal FROM emp ORDER BY ansal DESC;

3) 10번 부서 또는 20번 부서에서 근무하고 있는 사원의 이름과 부서번호를 출력하는데 이름을 영문자순으로 표시하시오.
SELECT ename, deptno FROM emp WHERE deptno IN (10, 20) ORDER BY ename;

4) 커미션 계약을 맞은 모든 사원의 이름, 급여, 커미션을 출력하는데 커미션을 기준으로 내림차순 정렬하시오.
SELECT ename, sal, comm FROM emp WHERE comm IS NOT NULL ORDER BY comm DESC;


<함수>

1. 문자 함수

LOWER, UPPER, INITCAP : 대소문자 조작 함수
/소문자로 변환
SELECT LOWER ('HELLO') FROM dual;
SELECT LOWER (ename) FROM emp;
/대문자로 변환
SELECT UPPER ('wave') FROM dual;
/첫 글자는 대문자, 나머지는 소문자
SELECT INITCAP ('hello wORLD') FROM dual;
SELECT INITCAP (ename) FROM emp;

- CONCAT(문자열1, 문자열2) : 문자열1과 문자열2를 연결하여 하나의 문자열로 변환
SELECT CONCAT ('Hello', 'World') FROM dual;

- SUBSTR(대상문자열, 인덱스) : 대상 문자열에서 지정한 인덱스부터 문자열을 반환. (주의)인덱스는 1부터 시작.
SELECT SUBSTR ('Hello World', 3) FROM dual;
SELECT SUBSTR ('Hello World', 3, 3) FROM dual;  --인덱스3부터 3개의 문자 추출
SELECT SUBSTR ('Hello World', -3) FROM dual;  --뒤에서 3번째부터 끝까지 추출
SELECT SUBSTR ('Hello World', -3, 2) FROM dual;  --뒤에서 3번째부터 2개의 문자 추출

- LENGTH(대상문자열) : 문자열의 개수 반환
SELECT LENGTH ('Hello World') FROM dual;  --11
SELECT LENGTH (ename) FROM emp;

- LENGTHB(대상문자열) : 문자열의 바이트 수 반환
SELECT LENGTHB ('SMITH') FROM dual;  --5
SELECT LENGTHB ('홍길동') FROM dual;  --9 /오라클에서는 한글을 3바이트로 할당

- INSTR(대상문자열, 검색문자) : 검색문자의 위치 값 반환
SELECT INSTR ('Hello World', 'e') FROM dual;
SELECT INSTR ('Hello World', 's') FROM dual; --검색문자가 없으면 0
SELECT INSTR ('Hello World', 'o') FROM dual;  --5
/대상문자열, 검색문자, 검색인덱스 : 해당위치부터 검색
SELECT INSTR ('Hello World', 'o', 6) FROM dual;  --8
/대상문자열, 검색문자, 검색인덱스, 반복횟수 : 대상 문자열 1번 검색으로 검색문자를 찾으면, 지정한 횟수만큼 그 뒤의 문자를 검색한다는 의미.
SELECT INSTR ('Hello World', 'o', 1, 2) FROM dual;  --8

- TRIM : 문자열에서 공백이나 특정 문자를 제거한 다음 값을 반환
방향 : 왼쪽 - leading, 오른쪽 - trailing, 양쪽 - both
SELECT TRIM (LEADING 'h' FROM 'habchh') FROM dual;
SELECT TRIM (BOTH 'h' FROM 'habchh') FROM dual;

- REPLACE(대상문자열, OLD, NEW) : 대상문자열에서 OLD문자를 NEW문자로 대체
SELECT REPLACE ('010.1234.5678', '.', '-') FROM dual;

함수 중첩
SELECT ename, LOWER (SUBSTR(ename, 1, 3)) FROM emp;


2. 숫자 함수

- CEIL(실수) : 올림 처리한 정수 값을 반환
SELECT CEIL (1.4) FROM dual;

- FLOOR(실수) : 버림 처리한 정수 값을 반환
SELECT FLOOR (1.7) FROM dual;

- ROUND(대상숫자, 지정자릿수) : 반올림
SELECT ROUND (45.926, 2) FROM dual;  --45.93
SELECT ROUND (45.926) FROM dual;  --46

- TRUNC(대상숫자, 지정자릿수) : 절삭
SELECT TRUNC (45.926, 2) FROM dual;  --45.92
SELECT TRUNC (45.926) FROM dual;  --45


[실습문제]
1) 업무(job)을 첫글자는 대문자, 나머지는 소문자로 출력하시오.
SELECT INITCAP (job) FROM emp;

2) 사원이름 중 A가 포함된 사원 이름을 구하고 그 이름 중 앞에서 3자만 추출하여 출력하시오.
SELECT SUBSTR (ename, 1, 3) FROM emp WHERE ename LIKE '%A%';

3) 이름의 세번째 문자가 A인 모든 사원의 이름을 표시하시오.
SELECT ename FROM emp WHERE SUBSTR(ename, 3, 1) = 'A';
SELECT ename FROM emp WHERE ename LIKE '__A%';

4) 이름이 J, A 또는 M으로 시작하는 모든 사원의 이름을 첫글자는 대문자로 나머지는 소문자로 표시하고 이름의 길이를 표시하시오.
SELECT INITCAP(ename), LENGTH(ename) FROM emp WHERE ename LIKE 'J%' OR ename LIKE 'A%' OR ename LIKE 'M%';
SELECT INITCAP(ename), LENGTH(ename) FROM emp WHERE SUBSTR (ename, 1, 1) IN ('J', 'A', 'M');

5) 이름의 글자 수가 6자 이상인 사원의 이름을 앞에서 3자만 구하여 소문자로 출력하시오.
SELECT LOWER(SUBSTR(ename, 1, 3)) FROM emp WHERE LENGTH(ename)>=6;


3. 날짜 함수

- SYSDATE : ORACLE 서버의 현재 날짜와 시간을 반환 (함수는 아님)
SELECT SYSDATE FROM dual;

- 날짜에 산술 연산자 사용
SELECT ename, (SYSDATE-hiredate)/7 AS WEEKS FROM emp WHERE deptno=10;

- MONTHS_BETWEEN(날짜1, 날짜2) : 두 날짜 간의 개월 수를 반환
SELECT MONTHS_BETWEEN ('2012-03-23', '2010-01-23') FROM dual;
/근무 월 수
SELECT ename, ROUND (MONTHS_BETWEEN (SYSDATE, hiredate)) months_worked FROM emp ORDER BY months_worked;

- ADD_MONTHS(날짜, 숫자) : 특정 날짜의 월에 정수를 더한 다음 해당 날짜를 반환
SELECT ADD_MONTHS ('2022-01-01', 8) FROM dual;

- NEXT_DAY(날짜, 요일) : 지정한 요일의 다음 날짜 반환
SELECT NEXT_DAY('2023-04-07', '월요일') FROM dual;  --23/04/10

- LAST_DAY(날짜) : 월의 마지막 날 반환
SELECT LAST_DAY('2023-04-07') FROM dual;

- EXTRACT(년/월/일/시/분/초 FROM 날짜) : 날짜 정보에서 특정한 연도, 월, 일, 시, 분, 초 등을 반환
SELECT EXTRACT (YEAR FROM SYSDATE),
           EXTRACT (MONTH FROM SYSDATE),
           EXTRACT (DAY FROM SYSDATE)
           FROM dual;


4. 변환 함수

TO_CHAR : 숫자->문자, 날짜->문자
TO_DATE : 문자->날짜
TO_NUMBER : 문자->숫자


1-1) TO_CHAR (날짜, 포맷문자)
SELECT TO_CHAR (SYSDATE, 'YYYY-MM-DD') FROM dual;
SELECT TO_CHAR (SYSDATE, 'YYYY-MM-DD HH:MI:SS') FROM dual;
/분기 별 입사자의 수
SELECT TO_CHAR (hiredate, 'Q') AS Quarter,
           COUNT(empno) AS employee_number
           FROM emp
GROUP BY TO_CHAR (hiredate, 'Q');

1-2) TO_CHAR(숫자, 포맷문자) --실제 자리수와 일치해야한다. / 기본 명시 9. 다른 건 쓰려면 작은 따옴표 붙여야.
SELECT TO_CHAR (1234, 9999) FROM dual; --1234
SELECT TO_CHAR (1234, '9999') FROM dual;  --1234
SELECT TO_CHAR (1234, 0000) FROM dual;  --##
SELECT TO_CHAR (1234, '0000') FROM dual;  --1234

/자리수보다 많은 자리수 지정 시
SELECT TO_CHAR (1234, 99999) FROM dual;  -- 1234 (앞쪽에 하나 띄움)
SELECT TO_CHAR (1234, '99999') FROM dual;  --위와 마찬가지
SELECT TO_CHAR (1234, '00000') FROM dual;  --01234

/소수점 자리
SELECT TO_CHAR (1234, 9999.99) FROM dual;  --1234.00
SELECT TO_CHAR (1234, '9999.99') FROM dual;  --1234.00
SELECT TO_CHAR (1234, '0000.00') FROM dual;  --1234.00

/반올림해서 소수점 둘째자리까지 표시 (ROUND와 같은 기능. ROUND결과값은 숫자! 이건 문자! 허나 큰 차이X)
SELECT TO_CHAR (25.897, '99.99') FROM dual;  --25.90 

/인상된 급여를 소수점 첫째 자리까지 표시
SELECT ename, TO_CHAR(sal*1.15, '9,999.9') FROM emp;

/통화 표시
SELECT TO_CHAR(1234, '$0000') FROM dual;
SELECT TO_CHAR(1234, 'L0000') FROM dual;  --\1234 (L을 넣으면 지역의 통화 단위를 읽어서 표시한다.)


2) TO_DATE (문자, 포맷문자) : 문자를 날짜로 변환 (생략해도 오라클에서 자동 변환됨.)
SELECT TO_DATE ('23-04-07', 'YYYY-MM-DD') FROM dual;  -- , . - 등 구분자는 자유롭게 가능
/포맷형식 생략 가능
SELECT TO_DATE ('23-04-07') FROM dual;

3) TO_NUMBER (문자, 포맷문자) : 문자를 숫자로 변환
SELECT TO_NUMBER('100', '999') FROM dual;
/포맷형식 생략 가능
SELECT TO_NUMBER('200') FROM dual;


5. 일반 함수

**NVL (value1, value2) : value1이 null이면 value2를 씀. 단, value1과 value2의 자료형이 일치해야 함.
SELECT ename, sal, NVL(comm, 0) FROM emp;
SELECT ename, NVL(TO_CHAR(comm), 'No Commission') FROM emp; --comm은 숫자형이므로 변환을 통해 자료형을 맞춤.

NVL2 (value1, value2, value3) : value1이 null인지 평가. null이면 value3, null이 아니면 value2를 사용. 자료형 일치 안 해도 됨.
SELECT ename, NVL2 (comm, 'commission', 'no commision') FROM emp;

NULLIF (value1, value2) : 2개의 값이 일치하면 null, 불일치하면 value1 사용.
SELECT NULLIF (LENGTH(ename), LENGTH(job)) "NULLIF" FROM emp;

COALESCE (value1, value2, value3 ... ) : null값이 아닌 값을 사용. 자료형이 일치해야 함.
SELECT comm, mgr, sal, COALESCE (comm, mgr, sal) FROM emp; --comm에 null값 있으면 mgr을, mgr도 null값이 있으면 sal을 ...


[실습 문제]

6) 오늘부터 이번 달의 마지막 날까지의 남은 날 수를 구하시오.
SELECT LAST_DAY(SYSDAY) - SYSDAY FROM dual;

7) 각 사원에 대해 사원번호, 이름, 급여 및 15% 인상된 급여를 정수(반올림)로 표시하시오.
    인상된 급여 열의 레이블을 New Salary로 지정하시오.
SELECT empno, ename, sal, ROUND(sal*1.15) "New Salary" FROM emp;

8) 각 사원의 이름을 표시하고 근무 월 수 (입사일로부터 현재까지의 월 수)를 계산하여 열 레이블을 MONTHS_WORKED로 지정하시오.
    결과는 정수로 반올림하여 표시하고 근무 월 수를 기준으로 오름차순으로 정렬하시오.
SELECT ename, ROUND (MONTHS_BETWEEN (SYSDATE, hiredate)) "MONTHS_WORKED" FROM emp ORDER BY "MONTHS_WORKED";

9) 이름을 소문자로 표시, 업무, 근무 연차를 출력하시오.
SELECT LOWER(ename), job, TRUNC(MONTHS_BETWEEN(SYSDATE, hiredate)/12) FROM emp;