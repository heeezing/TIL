--04/12---------------------------------------------------------------------------------------------------------------------------------------------------------------------
[ PL/SQL ]
: Procedural language extension to Structured Query Language 의 약자
SQL을 확장한 순차적 처리 언어

<기본 구조>
기본 단위는 블록 (block)
1) 선언부 (declarative part) : 사용할 변수나 상수를 선언 (선언부에만 변수/상수 선언 가능)
2) 실행부 (executable part) : 실제 처리할 로직을 담당하는 부분
3) 예외 처리부 (exception-building part) : 실행부에서 로직을 처리하던 중 발생할 수 있는 각종 오류들에 대한 처리

BEGIN
    DBMS_OUTPUT.PUT_LINE ('Hello World!');
END;


DECLARE
--변수를 선언할 수 있는 선언부
    message VARCHAR2(100);  --변수 선언
BEGIN
--실행부에 사용할 변수는 선언부에서 미리 선언되어야 함.
    message := 'Hello World!!';  --대입연산자 :=
    DBMS_OUTPUT.PUT_LINE (message);  --DBMS에 출력 (마치 자바의 콘솔)
END;


DECLARE
    --변수 선언 및 초기화
    message VARCHAR2(100) := 'HELLO WORLD';
BEGIN
    DBMS_OUTPUT.PUT_LINE (message);
END;


DECLARE
--변수 선언
    counter INTEGER;
BEGIN
    counter := counter + 1;
    if counter IS NULL then 
        DBMS_OUTPUT.PUT_LINE ('Result : counter is null');
    end if;
END;


DECLARE
--변수 선언
    counter INTEGER;
    i INTEGER;
BEGIN
    for i in 1..9 loop
        counter := 2 * i;
        DBMS_OUTPUT.PUT_LINE ('2 * ' || i || ' = ' || counter);
    end loop;
END;


DECLARE
--변수 선언
    counter INTEGER;
BEGIN
    counter := 10;  --변수 초기화
    counter := counter / 0;
    DBMS_OUTPUT.PUT_LINE (counter);
EXCEPTION WHEN OTHERS THEN  --예외 처리 (OTHERS->모든 예외들의 부모)
    DBMS_OUTPUT.PUT_LINE ('errors');
END;


<컬렉션>
: 배열 형태의 데이터 타입. 같은 자료형을 가진 요소들로만 구성할 수 있음.
 
1. varray
: variable array의 약자. 고정 길이(fixed number)를 가진 배열.

DECLARE
        --자료형 생성
    TYPE varray_test IS VARRAY(3) OF INTEGER;
--위에서 선언한 varray_test 타입의 변수 선언
    varray1 varray_test;
BEGIN
    varray1 := varray_test (10, 20, 30);
--DBMS 출력                          --인덱스
    DBMS_OUTPUT.PUT_LINE (varray1(1));  --10
    DBMS_OUTPUT.PUT_LINE (varray1(2));  --20
    DBMS_OUTPUT.PUT_LINE (varray1(3));  --30
END;


2. 중첩 테이블
: varray 와 흡사하지만 선언 시에 전체 크기를 명시할 필요가 없음.

DECLARE
    TYPE nested_test IS TABLE OF VARCHAR2(10);
--위에서 선언한 nested_test 타입 변수 선언
    nested1 nested_test;
BEGIN                         --1   2   3   4
    nested1 := nested_test ('A', 'B', 'C', 'D');
    DBMS_OUTPUT.PUT_LINE (nested1(2));  --B
END;


3. Associative array (index-by table, 연관 배열)
: 키와 값의 쌍으로 구성. 하나의 키는 하나의 값과 연관.

DECLARE                                            --값 자료형                      key 자료형
    TYPE assoc_array_str_type IS TABLE OF VARCHAR2(32) INDEX BY VARCHAR2(64);
    --key는 VARCHAR2(64)형이며, 값은 VARCHAR2(32)형인 요소들로 구성
    assoc assoc_array_str_type;
BEGIN 
    assoc ('K') := 'KOREA';  --키는 K, 값은 KOREA가 됨.
    DBMS_OUTPUT.PUT_LINE (assoc('K'));
END;


<레코드>
: 컬렉션과 달리 필드들이 서로 다른 유형의 자료형을 가질 수 있음.

DECLARE
    TYPE record1 IS RECORD (deptno number not null := 50,
                                       dname varchar2(14),
                                       loc varchar2(13));
--위에서 선언한 record1을 받는 변수 선언
    rec1 record1; 
BEGIN
--record1 레코드 변수 rec1의 dname 필드에 값 할당
    rec1.dname := 'RECORD';
--record1 레코드 변수 rec1의 loc 필드에 값 할당
    rec1.loc := 'SEOUL';
--rec1 레코드 값을 dept 테이블에 insert
    INSERT INTO dept VALUES rec1;
    COMMIT;  --INSERT문이 정상 처리 되면 COMMIT
EXCEPTION WHEN OTHERS THEN
    ROLLBACK;  --예외 발생 시 ROLLBACK
END;

SELECT * FROM dept;


<조건문>

1. IF 문  --(주의) 다중 처리의 경우 ELSE IF가 아닌 ELSIF임
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

2. CASE 문
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
    END CASE;  --SQL문과 다르게 END와 CASE를 같이 써주어야함.
END;


<반복문>

1. LOOP 문  --무한루프
DECLARE
    test_number INTEGER;
    result_num INTEGER;
BEGIN
    test_number := 1;      
    LOOP
        result_num := 2 * test_number;
            IF result_num > 20 THEN
                exit;  --블록 종료 (빠져나감)
            ELSE 
                DBMS_OUTPUT.PUT_LINE (result_num);
            END IF;
        test_number := test_number + 1;
    END LOOP;
--loop 블럭을 빠져나오면 아래 코드를 실행함.
    DBMS_OUTPUT.PUT_LINE ('프로그램 끝');
END;
