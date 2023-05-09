--04/11---------------------------------------------------------------------------------------------------------------------------------------------------------------------

9) 10�� �μ����� �ٹ��ϴ� ������� �μ���ȣ, �μ��̸�, ����̸�, ����, �޿������ ����Ͻÿ�.
SELECT d.deptno, d.dname, e.ename, e.sal, s.grade FROM dept d, emp e, salgrade s
WHERE e.deptno = d.deptno
AND e.sal BETWEEN s.losal AND s.hisal
AND e.deptno = 10;

SELECT d.deptno, d.dname, e.ename, e.sal, s.grade FROM dept d JOIN emp e ON e.deptno = d.deptno
JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal
WHERE e.deptno = 10;


<���� ������> : ���̺� �� ���� ����

1) UNION (������ �ߺ��� ����) : �� ���̺��� ������ ��Ÿ����, ���ս�Ű�� �� ���̺��� �ߺ����� �ʴ� ������ ��ȯ.
SELECT deptno FROM emp
UNION
SELECT deptno FROM dept;

2) UNION ALL (������) : UNION�� ������ �� ���̺��� �ߺ��Ǵ� ������ ��ȯ.
SELECT deptno FROM emp
UNION ALL
SELECT deptno FROM dept;

3) INTERSECT (������) : �� ���� ���� �� ����� ���� ��ȯ
SELECT deptno FROM emp 
INTERSECT
SELECT deptno FROM dept;

4) MINUS (������) : ù��° SELECT���� ���� ��ȯ�Ǵ� �� �߿��� 
                          �ι�° SELECT���� ���� ��ȯ�Ǵ� �࿡ �������� �ʴ� ����� ������.
SELECT deptno FROM dept
MINUS
SELECT deptno FROM emp;


<SUBQUERY> : �ٸ� �ϳ��� SQL ������ ���� nested�� SELECT ����

1) ������ �������� : �׻� �� ���� ��(��)�� ��ȯ
--���� �������̰ų�(deptno) ���ο� ���� ���� �� �ִ� ���(ename)�� �� �� X
SELECT empno, ename, job FROM emp 
WHERE job = (SELECT job FROM emp WHERE empno = 7369);

SELECT empno, ename, sal FROM emp
WHERE sal > (SELECT sal FROM emp WHERE empno=7698);

2) ������ �������� : �ϳ� �̻��� ���� ��ȯ

- IN �������� ��� (= / OR)
/�μ� ���� ���� �޿��� ���� �޴� ����� ������ �޿��� �޴� ����� ������ ����Ͻÿ�.
SELECT empno, ename, sal FROM emp
WHERE sal IN (SELECT MIN(sal) FROM emp GROUP BY deptno);

- ANY �������� ��� (>,< �� / OR)
: ���������� ����� �� ��� �ϳ��� ���̶� ������ �Ǹ� ������� ��ȯ
--(=)�� �� ��쿣 IN ���. 
SELECT sal FROM emp WHERE job = 'SALESMAN';
SELECT ename, sal FROM emp WHERE sal >1250 OR sal > 1500 OR sal > 1600;
/���� SQL���� �������� �������� �ۼ�
SELECT ename, sal FROM emp 
WHERE sal > ANY (SELECT sal FROM emp WHERE job = 'SALESMAN');

- ALL �������� ��� (>,< �� / AND)
: ���������� ����� ��� ���� ��ġ
SELECT sal FROM emp WHERE deptno=20;
SELECT empno, ename, sal, deptno FROM emp WHERE sal>800 AND sal>2975 AND sal>3000;
/���� SQL���� �������� �������� �ۼ�
SELECT empno, ename, sal, deptno FROM emp
WHERE sal > ALL (SELECT sal FROM emp WHERE deptno=20);

3) ���߿� �������� : ���������� ����� �� �� �̻��� �÷����� ��ȯ�Ǿ� ���� ������ ����
SELECT empno, ename, sal, deptno FROM emp
WHERE (deptno, sal) IN (SELECT deptno, sal FROM emp WHERE deptno=30);
/�μ� �� ���� �޿��� ���� �޴� ����� ������ ����Ͻÿ�.
SELECT empno, ename, sal, deptno FROM emp
WHERE (deptno, sal) IN (SELECT deptno, MIN(sal) FROM emp GROUP BY deptno);
/SMITH����� �μ���ȣ, �޿��� ���� ����� ������ ����Ͻÿ�. (=SMITH)
SELECT empno, ename, sal, deptno FROM emp
WHERE (deptno, sal) IN (SELECT deptno, sal FROM emp WHERE ename = 'SMITH');

[�ǽ�����]
1) "BLAKE"�� ���� �μ��� �ִ� ������� �̸��� �Ի����� ���ϴµ�, "BLAKE"�� �����ϰ� ����Ͻÿ�.
SELECT ename, hiredate FROM emp
WHERE deptno = (SELECT deptno FROM emp WHERE ename = 'BLAKE') AND ename != 'BLAKE';

2) ��� �޿����� ���� �޿��� �޴� ������� �����ȣ, �̸�, ������ ����ϴµ� ������ ���� ��� ������ ����Ͻÿ�.
SELECT empno, ename, sal FROM emp
WHERE sal > (SELECT ROUND(AVG(sal)) FROM emp) ORDER BY sal DESC;

3) 10�� �μ����� �޿��� ���� ���� �޴� ����� ������ �޿��� �޴� ����� �̸��� ������ ����Ͻÿ�.
SELECT ename, sal FROM emp
WHERE sal = (SELECT MIN(sal) FROM emp WHERE deptno=10);

4) �μ� �� ��� ���� ���ϰ� ��� ���� 3���� �Ѵ� �μ��� �μ���� ��� ���� ����Ͻÿ�.
[JOIN]
SELECT d.dname, COUNT(e.empno) FROM emp e, dept d
WHERE e.deptno(+) = d.deptno GROUP BY d.dname HAVING COUNT(e.empno)>3;

[SUBQUERY]                                                   --�ϳ��� ���̺��� �����ߴٰ� ���� ��(Inline View)              �˸��ƽ�
SELECT d.dname, NVL(e.cnt,0) FROM dept d, (SELECT deptno, COUNT(empno) cnt FROM emp GROUP BY deptno) e
WHERE d.deptno = e.deptno(+) AND e.cnt>3;

5) �����ȣ�� 7844�� ������� ���� �Ի��� ����� �̸��� �Ի����� ����Ͻÿ�.
SELECT ename, hiredate FROM emp
WHERE hiredate < (SELECT hiredate FROM emp WHERE empno = 7844);

6) ���� ���(mgr)�� KING�� ��� ����� �̸��� �޿��� ����Ͻÿ�.
SELECT ename, sal FROM emp
WHERE mgr IN (SELECT empno FROM emp WHERE ename = 'KING');

7) 20�� �μ����� ���� �޿��� ���� �޴� ����� ������ �޿��� �޴� ����� �̸�, �μ���, �޿�, �޿� ����� ����Ͻÿ�.
SELECT e.ename, d.dname, e.sal, s.grade FROM emp e, dept d, salgrade s
WHERE e.deptno = d.deptno
AND e.sal BETWEEN s.losal AND s.hisal
AND e.sal = (SELECT MAX(sal) FROM emp WHERE deptno = 20);

8) �� �޿�(sal+comm)�� ��� �޿����� ���� �޿��� �޴� ����� �μ���ȣ, �̸�, �� �޿�, Ŀ�̼��� ����Ͻÿ�.
    (Ŀ�̼��� ��(O), ��(X)�� ǥ���ϰ�, �÷����� "comm����"�� ���)
SELECT deptno, ename, sal+NVL(comm,0), NVL2(comm,'O','X') "comm����" FROM emp
WHERE sal+NVL(comm,0) > (SELECT ROUND(AVG(sal)) FROM emp);

SELECT deptno, ename, sal+NVL(comm,0), 
            CASE WHEN comm IS NOT NULL THEN 'O'
                    ELSE 'X'
            END "comm ����"
FROM emp
WHERE sal+NVL(comm,0) > (SELECT ROUND(AVG(sal)) FROM emp);

9) CHICAGO �������� �ٹ��ϴ� ����� ��� �޿����� ���� �޿��� �޴� ����� �̸�, �޿�, �������� ����Ͻÿ�.
SELECT e.ename, e.sal, d.loc FROM emp e, dept d
WHERE e.deptno = d.deptno
AND e.sal > (SELECT ROUND(AVG(e.sal)) FROM emp e, dept d
                 WHERE e.deptno = d.deptno AND d.loc = 'CHICAGO');

10) Ŀ�̼��� ���� ����� �� ������ ���� ���� ����� �޿� ����� ����Ͻÿ�.
SELECT e.ename, s.grade FROM emp e, salgrade s
WHERE e.sal BETWEEN s.losal AND s.hisal
AND e.sal = (SELECT MAX(sal) FROM emp WHERE comm IS NULL);

11) ALLEN���� �޿��� ���� �޴� ��� �߿��� �Ի����� ���� ���� ����� ������ ��¥�� �Ի��� ����� �̸�, �Ի���, �޿��� ����Ͻÿ�.
SELECT ename, hiredate, sal FROM emp
WHERE hiredate = (SELECT MIN(hiredate) FROM emp
                          WHERE sal > (SELECT sal FROM emp
                                            WHERE ename = 'ALLEN'));


<INSERT ��> : ���̺� ���� ����

- ��ü ������ ���� (��ü �÷� ���)
INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno)
VALUES (8000, 'DENNIS', 'SALESMAN', 7698, '99/01/22', 1700, 200, 30);

/��ü �����͸� ������ ���� �÷��� ���� ����
INSERT INTO emp
VALUES (8001, 'MARIA', 'CLERK', 7839, '99/02/02', 1500, NULL, 20);

/���� �Էµ��� �ʴ� �÷��� ����
INSERT INTO emp (empno, ename, job,mgr, hiredate, sal, deptno)  --comm�� ��
VALUES (8002, 'PETER', 'CLERK', 7698, '99/03/01', 1000, 30);


<UPDATE ��> : �� ������ ������ ����
--(����) WHERE���� �Է����� ������ ��� �����Ͱ� �� ���ŵ�.
UPDATE emp SET mgr=7900 WHERE empno=8000;  --8000�� ����� ����ȣ�� 7900���� ����
UPDATE emp SET ename='JOHN', sal=1800, comm=500 WHERE empno=8000;


<DELETE ��> : �� ����
--(����) WHERE���� �Է����� ������ ��� �����Ͱ� �� ������.
DELETE FROM emp WHERE empno=8000;


<�����ͺ��̽� Ʈ�����>
: ������ ó���� �� ����. ����Ŭ �������� �߻��ϴ� SQL������ �ϳ��� ������ �۾� �����ν� �����ϰų� �����ϴ� �Ϸ��� SQL��.

1) Ʈ������� ����
���� ������ SQL������ ���� ó�� ����� ��

2) Ʈ������� ����
- COMMIT or ROLLBACK ����
- DDL or DCL ���� ���� (�ڵ� COMMIT ��)
- ��� ��� �Ǵ� �ý��� �浹
- ����ڰ� ���� ����

3) �ڵ� COMMIT �� ������ ��� �߻�
- DDL, DCL ������ �Ϸ�� ��
- ������� COMMIT �̳� ROLLBACK ���� SQL*PLUS �� ���� �������� ���

4) �ڵ� ROLLBACK �� ������ ��� �߻�
- SQL*PLUS�� ������ �������� ���
- SYSTEM FAILURE 

* COMMIT (������� ����) & ROLLBACK (������� ���) �� ����
- �������� �ϰ����� ����
- �����͸� ���������� �����ϱ� ���� ������ ������ Ȯ���ϰ� ��
- ���õ� �۾��� �������� �׷�ȭ��


<���̺�> : �⺻���� ������ ���� ����. ���ڵ�� �÷����� ����.
- ���ڵ�(RECORD, ROW) : ���̺��� �����ʹ� �࿡ ����
- �÷�(COLUMN) : ���̺��� �� �÷��� �����͸� ������ �� �ִ� �Ӽ��� ǥ��

/����Ŭ �����ͺ��̽��� ���̺�
- ����� ���̺�
- ������ ��ųʸ�
1) ����ڰ� ������ ���̺��� �̸� ���ϱ�
SELECT table_name FROM user_tables;

2) ����ڰ� ������ ���� ��ü ���� ���ϱ�
SELECT DISTINCT object_type FROM user_objects;

3) ����ڰ� ������ ���̺�, ��, ���Ǿ� �� ������ ���ϱ�
SELECT * FROM user_catalog;  --�������� ��, ���Ǿ�, ������ �� ���� �� ��

/���̺� ����
1) ���̺� �̸� : ������� ���̺��� �̸�
2) �� �̸� : ���̺� ���� ������� ���� �̸�
3) ������ Ÿ�� : ������ ���� �ڽ��� ������ Ÿ���� ����
4) default <ǥ����> : ������ ������ insert ������ ���� ���� �������� ���� ��쿡 �̿�� ����Ʈ ���� ������ �� ����.
5) ���� ���� : ������� �� ���� ���������� ���� ������ ������ �� �ִ�.

CREATE TABLE employee(  --���̺��� ����� DDL����
    empno number (6),  --10���ڸ���6 = 100000 / ������ ���� �� �� �� �ִ밪 ����
    name varchar2 (30) not null,  --�ѱ� ���� 10�� / ������ ���� �ʼ�
    salary number (8, 2),  --10���ڸ���8, �Ҽ��� ��°�ڸ�
    hire_date date default sysdate,  --����Ʈ�� �ο�
    constraint employee_pk primary key (empno)  --�������� / employee_pk : ����Ŭ ���� �ĺ���
);
