--04/07---------------------------------------------------------------------------------------------------------------------------------------------------------------------

- NULL ���� ���
SELECT * FROM emp WHERE comm IS NULL;  --comm = null �Ұ�
SELECT * FROM emp WHERE comm IS NOT NULL;


<�� ������> - AND, OR, NOT

1) AND : ���� ��� ������ ��� TRUE�̸� TRUE�� ��ȯ
SELECT empno, ename, job, sal FROM emp WHERE sal>=2000 AND job LIKE '%MAN%';

2) OR : ���� �� �ϳ��� TRUE�̸� TRUE�� ��ȯ
SELECT empno, ename, job, sal FROM emp WHERE sal>=2000 OR job LIKE '%MAN%';

3) NOT 
SELECT ename, job FROM emp WHERE job NOT IN ('CLERK', 'SALESMAN');


[�ǽ�����]
5) 81�� 05�� 01�ϰ� 81�� 12�� 03�� ���̿� �Ի��� ����� �̸�, �޿�, �ϻ����� ����Ͻÿ�.
SELECT ename, sal, hiredate FROM emp WHERE hiredate BETWEEN '81-05-01' AND '81-12-03';

6) �����ȣ�� 7566, 7782, 7934�� ����� ������ ������� �����ȣ, �̸�, ������ ����Ͻÿ�.
SELECT empno, ename, sal FROM emp WHERE empno NOT IN (7566, 7782, 7934);

7) �μ���ȣ(deptno) 30���� �ٹ��ϸ� �� 2,000�޷� ���ϸ� �޴� 81�� 05�� 01�� ������ �Ի��� ����� �̸�, �޿�, �μ���ȣ, �Ի����� ����Ͻÿ�.
SELECT ename, sal, deptno, hiredate FROM emp WHERE deptno = 30 AND sal<=2000 AND hiredate<'81-05-01';

8) �޿��� 2,000 ~ 5,000�޷� ���̰� �μ���ȣ�� 10 �Ǵ� 30�� ����� �̸�, �޿�, �μ���ȣ�� ����Ͻÿ�.
SELECT ename, sal, deptno FROM emp WHERE (sal BETWEEN 2000 AND 5000) AND deptno IN (10, 30);

9) ������ SALESMAN �Ǵ� MANAGER�̸鼭 �޿��� 1,600�� 2,850�� �ƴ� ��� ����� �̸�, ����, �޿��� ����Ͻÿ�. 
SELECT ename, job, sal FROM emp WHERE job IN ('SALESMAN', 'MANAGER') AND sal NOT IN (1600, 2850);


< ORDER BY ��> : ����

1) �������� ����
SELECT *FROM emp ORDER BY sal;
SELECT *FROM emp ORDER BY sal ASC;
2) �������� ����
SELECT * FROM emp ORDER BY sal DESC;
SELECT * FROM emp WHERE deptno=10 ORDER BY sal DESC;

/���� �� ������ �Ǵ� �÷��� ���� �ߺ��Ǹ� 2�� ���� ����
SELECT * FROM emp ORDER BY sal ASC, ename ASC;

/�� �˸��ƽ��� �������� ����
SELECT empno, ename, sal*12 annsal FROM emp ORDER BY annsal;

/���� ���� ��ġ�� ����Ͽ� ����
SELECT ename, job, deptno, hiredate FROM emp ORDER BY 3;  --index3�� deptno�������� ���� (�ڹٶ� �޸� 1���� ����)

/���̺� ������ ������� �� ���� ��ġ �ο�
SELECT * FROM emp ORDER BY 2; --�������� ���� *���ٴ� �÷����� ���ִ� �� ����

NULLS FIRST �Ǵ� NULLS LAST : ��ȯ�� �� �� NULL���� �����ϴ� ���� ���� ���� �� �� ó���� ��Ÿ���ų� �������� ��Ÿ������ ����
SELECT * FROM emp ORDER BY comm NULLS FIRST;
SELECT * FROM emp ORDER BY comm DESC NULLS LAST;


[�ǽ�����]
1) �����ȣ, ����̸�, �Ի����� ����ϴµ� �Ի����� ���� ��� ������ �����Ͻÿ�.
SELECT empno, ename, hiredate FROM emp ORDER BY hiredate;

2) ����̸�, �޿�, ������ ���ϰ� ������ ���� ������ �����Ͻÿ�.
SELECT ename, sal, sal*12 ansal FROM emp ORDER BY ansal DESC;

3) 10�� �μ� �Ǵ� 20�� �μ����� �ٹ��ϰ� �ִ� ����� �̸��� �μ���ȣ�� ����ϴµ� �̸��� �����ڼ����� ǥ���Ͻÿ�.
SELECT ename, deptno FROM emp WHERE deptno IN (10, 20) ORDER BY ename;

4) Ŀ�̼� ����� ���� ��� ����� �̸�, �޿�, Ŀ�̼��� ����ϴµ� Ŀ�̼��� �������� �������� �����Ͻÿ�.
SELECT ename, sal, comm FROM emp WHERE comm IS NOT NULL ORDER BY comm DESC;


<�Լ�>

1. ���� �Լ�

LOWER, UPPER, INITCAP : ��ҹ��� ���� �Լ�
/�ҹ��ڷ� ��ȯ
SELECT LOWER ('HELLO') FROM dual;
SELECT LOWER (ename) FROM emp;
/�빮�ڷ� ��ȯ
SELECT UPPER ('wave') FROM dual;
/ù ���ڴ� �빮��, �������� �ҹ���
SELECT INITCAP ('hello wORLD') FROM dual;
SELECT INITCAP (ename) FROM emp;

- CONCAT(���ڿ�1, ���ڿ�2) : ���ڿ�1�� ���ڿ�2�� �����Ͽ� �ϳ��� ���ڿ��� ��ȯ
SELECT CONCAT ('Hello', 'World') FROM dual;

- SUBSTR(����ڿ�, �ε���) : ��� ���ڿ����� ������ �ε������� ���ڿ��� ��ȯ. (����)�ε����� 1���� ����.
SELECT SUBSTR ('Hello World', 3) FROM dual;
SELECT SUBSTR ('Hello World', 3, 3) FROM dual;  --�ε���3���� 3���� ���� ����
SELECT SUBSTR ('Hello World', -3) FROM dual;  --�ڿ��� 3��°���� ������ ����
SELECT SUBSTR ('Hello World', -3, 2) FROM dual;  --�ڿ��� 3��°���� 2���� ���� ����

- LENGTH(����ڿ�) : ���ڿ��� ���� ��ȯ
SELECT LENGTH ('Hello World') FROM dual;  --11
SELECT LENGTH (ename) FROM emp;

- LENGTHB(����ڿ�) : ���ڿ��� ����Ʈ �� ��ȯ
SELECT LENGTHB ('SMITH') FROM dual;  --5
SELECT LENGTHB ('ȫ�浿') FROM dual;  --9 /����Ŭ������ �ѱ��� 3����Ʈ�� �Ҵ�

- INSTR(����ڿ�, �˻�����) : �˻������� ��ġ �� ��ȯ
SELECT INSTR ('Hello World', 'e') FROM dual;
SELECT INSTR ('Hello World', 's') FROM dual; --�˻����ڰ� ������ 0
SELECT INSTR ('Hello World', 'o') FROM dual;  --5
/����ڿ�, �˻�����, �˻��ε��� : �ش���ġ���� �˻�
SELECT INSTR ('Hello World', 'o', 6) FROM dual;  --8
/����ڿ�, �˻�����, �˻��ε���, �ݺ�Ƚ�� : ��� ���ڿ� 1�� �˻����� �˻����ڸ� ã����, ������ Ƚ����ŭ �� ���� ���ڸ� �˻��Ѵٴ� �ǹ�.
SELECT INSTR ('Hello World', 'o', 1, 2) FROM dual;  --8

- TRIM : ���ڿ����� �����̳� Ư�� ���ڸ� ������ ���� ���� ��ȯ
���� : ���� - leading, ������ - trailing, ���� - both
SELECT TRIM (LEADING 'h' FROM 'habchh') FROM dual;
SELECT TRIM (BOTH 'h' FROM 'habchh') FROM dual;

- REPLACE(����ڿ�, OLD, NEW) : ����ڿ����� OLD���ڸ� NEW���ڷ� ��ü
SELECT REPLACE ('010.1234.5678', '.', '-') FROM dual;

�Լ� ��ø
SELECT ename, LOWER (SUBSTR(ename, 1, 3)) FROM emp;


2. ���� �Լ�

- CEIL(�Ǽ�) : �ø� ó���� ���� ���� ��ȯ
SELECT CEIL (1.4) FROM dual;

- FLOOR(�Ǽ�) : ���� ó���� ���� ���� ��ȯ
SELECT FLOOR (1.7) FROM dual;

- ROUND(������, �����ڸ���) : �ݿø�
SELECT ROUND (45.926, 2) FROM dual;  --45.93
SELECT ROUND (45.926) FROM dual;  --46

- TRUNC(������, �����ڸ���) : ����
SELECT TRUNC (45.926, 2) FROM dual;  --45.92
SELECT TRUNC (45.926) FROM dual;  --45


[�ǽ�����]
1) ����(job)�� ù���ڴ� �빮��, �������� �ҹ��ڷ� ����Ͻÿ�.
SELECT INITCAP (job) FROM emp;

2) ����̸� �� A�� ���Ե� ��� �̸��� ���ϰ� �� �̸� �� �տ��� 3�ڸ� �����Ͽ� ����Ͻÿ�.
SELECT SUBSTR (ename, 1, 3) FROM emp WHERE ename LIKE '%A%';

3) �̸��� ����° ���ڰ� A�� ��� ����� �̸��� ǥ���Ͻÿ�.
SELECT ename FROM emp WHERE SUBSTR(ename, 3, 1) = 'A';
SELECT ename FROM emp WHERE ename LIKE '__A%';

4) �̸��� J, A �Ǵ� M���� �����ϴ� ��� ����� �̸��� ù���ڴ� �빮�ڷ� �������� �ҹ��ڷ� ǥ���ϰ� �̸��� ���̸� ǥ���Ͻÿ�.
SELECT INITCAP(ename), LENGTH(ename) FROM emp WHERE ename LIKE 'J%' OR ename LIKE 'A%' OR ename LIKE 'M%';
SELECT INITCAP(ename), LENGTH(ename) FROM emp WHERE SUBSTR (ename, 1, 1) IN ('J', 'A', 'M');

5) �̸��� ���� ���� 6�� �̻��� ����� �̸��� �տ��� 3�ڸ� ���Ͽ� �ҹ��ڷ� ����Ͻÿ�.
SELECT LOWER(SUBSTR(ename, 1, 3)) FROM emp WHERE LENGTH(ename)>=6;


3. ��¥ �Լ�

- SYSDATE : ORACLE ������ ���� ��¥�� �ð��� ��ȯ (�Լ��� �ƴ�)
SELECT SYSDATE FROM dual;

- ��¥�� ��� ������ ���
SELECT ename, (SYSDATE-hiredate)/7 AS WEEKS FROM emp WHERE deptno=10;

- MONTHS_BETWEEN(��¥1, ��¥2) : �� ��¥ ���� ���� ���� ��ȯ
SELECT MONTHS_BETWEEN ('2012-03-23', '2010-01-23') FROM dual;
/�ٹ� �� ��
SELECT ename, ROUND (MONTHS_BETWEEN (SYSDATE, hiredate)) months_worked FROM emp ORDER BY months_worked;

- ADD_MONTHS(��¥, ����) : Ư�� ��¥�� ���� ������ ���� ���� �ش� ��¥�� ��ȯ
SELECT ADD_MONTHS ('2022-01-01', 8) FROM dual;

- NEXT_DAY(��¥, ����) : ������ ������ ���� ��¥ ��ȯ
SELECT NEXT_DAY('2023-04-07', '������') FROM dual;  --23/04/10

- LAST_DAY(��¥) : ���� ������ �� ��ȯ
SELECT LAST_DAY('2023-04-07') FROM dual;

- EXTRACT(��/��/��/��/��/�� FROM ��¥) : ��¥ �������� Ư���� ����, ��, ��, ��, ��, �� ���� ��ȯ
SELECT EXTRACT (YEAR FROM SYSDATE),
           EXTRACT (MONTH FROM SYSDATE),
           EXTRACT (DAY FROM SYSDATE)
           FROM dual;


4. ��ȯ �Լ�

TO_CHAR : ����->����, ��¥->����
TO_DATE : ����->��¥
TO_NUMBER : ����->����


1-1) TO_CHAR (��¥, ���˹���)
SELECT TO_CHAR (SYSDATE, 'YYYY-MM-DD') FROM dual;
SELECT TO_CHAR (SYSDATE, 'YYYY-MM-DD HH:MI:SS') FROM dual;
/�б� �� �Ի����� ��
SELECT TO_CHAR (hiredate, 'Q') AS Quarter,
           COUNT(empno) AS employee_number
           FROM emp
GROUP BY TO_CHAR (hiredate, 'Q');

1-2) TO_CHAR(����, ���˹���) --���� �ڸ����� ��ġ�ؾ��Ѵ�. / �⺻ ��� 9. �ٸ� �� ������ ���� ����ǥ �ٿ���.
SELECT TO_CHAR (1234, 9999) FROM dual; --1234
SELECT TO_CHAR (1234, '9999') FROM dual;  --1234
SELECT TO_CHAR (1234, 0000) FROM dual;  --##
SELECT TO_CHAR (1234, '0000') FROM dual;  --1234

/�ڸ������� ���� �ڸ��� ���� ��
SELECT TO_CHAR (1234, 99999) FROM dual;  -- 1234 (���ʿ� �ϳ� ���)
SELECT TO_CHAR (1234, '99999') FROM dual;  --���� ��������
SELECT TO_CHAR (1234, '00000') FROM dual;  --01234

/�Ҽ��� �ڸ�
SELECT TO_CHAR (1234, 9999.99) FROM dual;  --1234.00
SELECT TO_CHAR (1234, '9999.99') FROM dual;  --1234.00
SELECT TO_CHAR (1234, '0000.00') FROM dual;  --1234.00

/�ݿø��ؼ� �Ҽ��� ��°�ڸ����� ǥ�� (ROUND�� ���� ���. ROUND������� ����! �̰� ����! �㳪 ū ����X)
SELECT TO_CHAR (25.897, '99.99') FROM dual;  --25.90 

/�λ�� �޿��� �Ҽ��� ù° �ڸ����� ǥ��
SELECT ename, TO_CHAR(sal*1.15, '9,999.9') FROM emp;

/��ȭ ǥ��
SELECT TO_CHAR(1234, '$0000') FROM dual;
SELECT TO_CHAR(1234, 'L0000') FROM dual;  --\1234 (L�� ������ ������ ��ȭ ������ �о ǥ���Ѵ�.)


2) TO_DATE (����, ���˹���) : ���ڸ� ��¥�� ��ȯ (�����ص� ����Ŭ���� �ڵ� ��ȯ��.)
SELECT TO_DATE ('23-04-07', 'YYYY-MM-DD') FROM dual;  -- , . - �� �����ڴ� �����Ӱ� ����
/�������� ���� ����
SELECT TO_DATE ('23-04-07') FROM dual;

3) TO_NUMBER (����, ���˹���) : ���ڸ� ���ڷ� ��ȯ
SELECT TO_NUMBER('100', '999') FROM dual;
/�������� ���� ����
SELECT TO_NUMBER('200') FROM dual;


5. �Ϲ� �Լ�

**NVL (value1, value2) : value1�� null�̸� value2�� ��. ��, value1�� value2�� �ڷ����� ��ġ�ؾ� ��.
SELECT ename, sal, NVL(comm, 0) FROM emp;
SELECT ename, NVL(TO_CHAR(comm), 'No Commission') FROM emp; --comm�� �������̹Ƿ� ��ȯ�� ���� �ڷ����� ����.

NVL2 (value1, value2, value3) : value1�� null���� ��. null�̸� value3, null�� �ƴϸ� value2�� ���. �ڷ��� ��ġ �� �ص� ��.
SELECT ename, NVL2 (comm, 'commission', 'no commision') FROM emp;

NULLIF (value1, value2) : 2���� ���� ��ġ�ϸ� null, ����ġ�ϸ� value1 ���.
SELECT NULLIF (LENGTH(ename), LENGTH(job)) "NULLIF" FROM emp;

COALESCE (value1, value2, value3 ... ) : null���� �ƴ� ���� ���. �ڷ����� ��ġ�ؾ� ��.
SELECT comm, mgr, sal, COALESCE (comm, mgr, sal) FROM emp; --comm�� null�� ������ mgr��, mgr�� null���� ������ sal�� ...


[�ǽ� ����]

6) ���ú��� �̹� ���� ������ �������� ���� �� ���� ���Ͻÿ�.
SELECT LAST_DAY(SYSDAY) - SYSDAY FROM dual;

7) �� ����� ���� �����ȣ, �̸�, �޿� �� 15% �λ�� �޿��� ����(�ݿø�)�� ǥ���Ͻÿ�.
    �λ�� �޿� ���� ���̺��� New Salary�� �����Ͻÿ�.
SELECT empno, ename, sal, ROUND(sal*1.15) "New Salary" FROM emp;

8) �� ����� �̸��� ǥ���ϰ� �ٹ� �� �� (�Ի��Ϸκ��� ��������� �� ��)�� ����Ͽ� �� ���̺��� MONTHS_WORKED�� �����Ͻÿ�.
    ����� ������ �ݿø��Ͽ� ǥ���ϰ� �ٹ� �� ���� �������� ������������ �����Ͻÿ�.
SELECT ename, ROUND (MONTHS_BETWEEN (SYSDATE, hiredate)) "MONTHS_WORKED" FROM emp ORDER BY "MONTHS_WORKED";

9) �̸��� �ҹ��ڷ� ǥ��, ����, �ٹ� ������ ����Ͻÿ�.
SELECT LOWER(ename), job, TRUNC(MONTHS_BETWEEN(SYSDATE, hiredate)/12) FROM emp;