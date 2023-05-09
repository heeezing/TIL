--04/10---------------------------------------------------------------------------------------------------------------------------------------------------------------------

- ���� ��
1) CASE WHEN THEN ELSE  --if���� ���
CASE �÷� WHEN �񱳰� THEN �����
            WHEN �񱳰� THEN �����
            WHEN �񱳰� THEN �����
            (ELSE �����)
END

/=��
SELECT ename, sal, job,
          CASE job WHEN 'SALESMAN' THEN sal*0.1
                      WHEN 'MANAGER' THEN sal*0.2
                      WHEN 'ANALYST' THEN sal*0.3
                      ELSE sal*0.4
          END "BONUS"  --�˸��ƽ�
FROM emp;

/�񱳿����� ��� �� WHEN �ȿ� �÷����� �� �� �ִ�.
SELECT ename, sal, job, 
          CASE WHEN sal>=4000 AND sal<=5000 THEN 'A'
                 WHEN sal>=3000 AND sal<4000 THEN 'B'
                 WHEN sal>=2000 AND sal<3000 THEN 'C'
                 WHEN sal>=1000 AND sal<2000 THEN 'D'
                 ELSE 'F'
          END "GRADE"
FROM emp ORDER BY "GRADE", sal DESC;

2) DECODE (ORACLE ����) : =�񱳸� ���� --switch���� ���
DECODE(�÷�, �񱳰�, ��ȯ��,
                  �񱳰�, ��ȯ��,
                  �񱳰�, ��ȯ��,
                  ��ȯ��)
                  
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


- �׷��Լ� : �� ���� ������ �����Ͽ� �׷캰�� �ϳ��� ����� ����

1) AVG() : NULL�� ������ ��� ������ ����� ��ȯ. NULL���� ��� ��꿡�� ���õ�.
SELECT AVG(sal) FROM emp;

2) COUNT() : NULL�� ������ ���� ���� ��� ���ڵ��� ���� ��ȯ.
              COUNT(*)������ ����ϸ� NULL�� ��꿡 ����.
SELECT COUNT(*) FROM emp;  --12
SELECT COUNT(comm) FROM emp;  --4

3) MAX() : ���ڵ� ���� �ִ� ���� �� �� ���� ū ���� ��ȯ
SELECT MAX(sal) FROM emp;  --����
SELECT MAX(ename) FROM emp;  --���� (Z�� �������� ū ������ �ν�)
SELECT MAX(hiredate) FROM emp;  --��¥ (�ֱ��ϼ��� ū ������ �ν�)

4) MIN() : ���ڵ� ���� �ִ� ���� �� �� ���� ���� ���� ��ȯ
SELECT MIN(sal) FROM emp;
SELECT MIN(ename) FROM emp;
SELECT MIN(hiredate) FROM emp;

5) SUM() : ���ڵ���� �����ϰ� �ִ� ��� ���� ���Ͽ� ��ȯ
SELECT SUM(sal) FROM emp;

SELECT MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp;


- GROUP BY �� : SELECT���� �����Լ�(�׷��ռ�) ���� �� ���� �÷��� ������ �� ���� ������
                     GROUP BY ���� �̿��ؼ� �׷����� ��� ���� �Լ��� ������.
                     �׷�(������ ���� �÷�)�� ���� �Լ��� ���� ������� ���� ��ȯ.
                     (*�˸��ƽ� ���X)
SELECT deptno, MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp GROUP BY deptno; --deptno�� �������� �׷��� ���ڴٴ� �� 
SELECT job, MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp GROUP BY job;
SELECT job, MAX(sal), MIN(sal), ROUND(AVG(sal)), SUM(sal) FROM emp WHERE job='SALESMAN' GROUP BY job;
SELECT deptno, job, SUM(sal) FROM emp GROUP BY deptno, job ORDER BY deptno;

- HAVING �� : �׷�ó�� ����� �����ϰ��� �� �� ���. 
                    WHERE������ �����Լ��� ����� �� ���� HAVING�� ���.
                    (*�˸��ƽ� ���X)
/���� �߻��� ���
SELECT deptno, ROUND(AVG(sal)) FROM emp WHERE ROUND(AVG(sal)) >= 2000 GROUP BY deptno;
/���� ����
SELECT deptno, ROUND(AVG(sal)) FROM emp GROUP BY deptno HAVING ROUND(AVG(sal))>=2000;
SELECT deptno, MAX(sal) FROM emp GROUP BY deptno HAVING MAX(sal)>3000;

* �б� �� �Ի����� ��
/TO_CHAR(hiredate, 'Q') �̿�
SELECT TO_CHAR(hiredate, 'Q') AS QUARTER, COUNT(empno) AS employee_number
FROM emp GROUP BY  TO_CHAR(hiredate, 'Q') ORDER BY QUARTER;

/CEIL(EXTRACT(MONTH FROM hiredate)/3) �̿�
SELECT CEIL(EXTRACT(MONTH FROM hiredate)/3) AS QUARTER, COUNT(empno) AS count_member
FROM emp GROUP BY CEIL(EXTRACT(MONTH FROM hiredate)/3) ORDER BY QUARTER;


[�ǽ�����]
10) ����̸�, ����, ���ް� Ŀ�̼��� ���� ���� �÷��� '�Ǳ޿�'��� �ؼ� ����Ͻÿ�.
     ��, NULL���� ��Ÿ���� �ʰ� �ۼ��Ͻÿ�. (NVL ���� 0���� ��ü.)
SELECT ename, sal, NVL(sal+comm, 0) �Ǳ޿� FROM emp;

11) ���ް� Ŀ�̼��� ��ģ �ݾ��� 2,000�̻��� �޿��� �޴� ����� �̸�, ����, ����, Ŀ�̼�, ��볯¥�� ����Ͻÿ�.
     ��, ��볯¥�� 1980-12-17 ���·� ����Ͻÿ�.
SELECT ename, job, sal, comm, TO_CHAR(hiredate, 'YYYY-MM-DD') hiredate FROM emp WHERE sal+NVL(comm, 0) >= 2000;
     
12) CASE �Լ��� DECODE �Լ��� ����Ͽ� ���� �����Ϳ� ���� JOB���� ���� �������� ��� ����� ����� ǥ���Ͻÿ�.
ex)    ����             ���
    PRESIDENT          A
     ANALYST           B
    MANAGER         C
    SALSEMAN         D
      CLERK              E
       ��Ÿ               0

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


[�ǽ�����]
1) ��� ����� �޿� �ְ��, ������, �Ѿ� �� ��վ��� ǥ���Ͻÿ�.
   �� ���̺��� ���� maximum, minimum, sum, average�� �����ϰ� ����� ������ �ݿø��ϰ� ���ڸ� ������ ,(��ǥ)�� ����Ͻÿ�.
SELECT TO_CHAR(MAX(sal), '9,999') "maximum",
           TO_CHAR(MIN(sal), '9,999') "minimum",
           TO_CHAR(SUM(sal), '99,999') "sum",
           TO_CHAR(ROUND(AVG(sal)), '9,999') "average"
FROM emp;

2) �޿��� Ŀ�̼��� ���� �ݾ��� �ְ�, ����, ��ձݾ��� ���Ͻÿ�. ��ձݾ��� �Ҽ��� ù°�ڸ����� ǥ���Ͻÿ�.
SELECT MAX(sal+NVL(comm,0)), MIN(sal+NVL(comm,0)), ROUND(AVG(sal+NVL(comm,0)),1) FROM emp;

3) ����, ������ ������ ��� ���� ǥ���Ͻÿ�. (���� �� ��� ���� ���Ͻÿ�.)
SELECT job, COUNT(*) FROM emp GROUP BY job;  --*�� �ƴϴ��� null���� ���� �÷��� �־ ��.

4) 30�� �μ��� ��� ���� ���Ͻÿ�.
SELECT COUNT(*) FROM emp WHERE deptno=30; --������� ����� ���
SELECT deptno, COUNT(*) FROM emp WHERE deptno=30 GROUP BY deptno; --�μ���ȣ�� ���� �� ���

5) ���� �� �ְ� ������ ���ϰ� ����, �ְ� ������ ����Ͻÿ�.
SELECT job, MAX(sal) FROM emp GROUP BY job;

6) �μ� ���� ���޵Ǵ� �� ���޿��� �ݾ��� 9,000�̻��� �޴� ������� �μ���ȣ, �� ������ ����Ͻÿ�.
SELECT deptno, SUM(sal) FROM emp GROUP BY deptno HAVING SUM(sal)>=9000;

7) ���� ���� ��� ��ȣ�� ���� ���� ����� ���ϰ�, �� ��� ������ �����ȣ 79�� �����ϴ� ����� �����ֽÿ�. (����, ���� ���� �����ȣ ���)
SELECT job, MAX(empno) FROM emp WHERE empno LIKE '79%' GROUP BY job;
SELECT job, MAX(empno) FROM emp GROUP BY job HAVING MAX(empno) LIKE '79%';

8) ���� �� �� ������ ����ϴµ� ������ 'MANAGER'�� ����� �����ϰ� �� ������ 5,000���� ���� ������ �� ���޸� ����Ͻÿ�.
SELECT job, SUM(sal) FROM emp WHERE job != 'MANAGER' GROUP BY job HAVING SUM(sal)>5000;
SELECT job, SUM(sal) FROM emp GROUP BY job HAVING SUM(sal)>5000 AND job!='MANAGER';


- �м� �Լ�
RANK() : ������ ǥ���� �� ����ϴ� �Լ�

1) RANK(���ǰ�) WITHIN GROUP (ORDER BY ���ǰ��� �÷��� [ASC|DESC]) : Ư�� �������� ���� Ȯ���ϱ�
(����)RANK �ڿ� ������ �����Ϳ� ORDER BY �ڿ� ������ �����ʹ� ���� �÷��̾�� ��.

SELECT ename FROM emp ORDER BY ename;  --SMITH�� 10��°
SELECT RANK('SMITH') WITHIN GROUP (ORDER BY ename) "RANK" FROM emp;

2) RANK() OVER(���İ�) : ��ü ���� Ȯ���ϱ� --WITHIN GROUP ��� OVER

ex) ������� empno, ename, sal, �޿� ���� ���
SELECT empno, ename, sal, RANK() OVER (ORDER BY sal) AS RANK_ASC,
          RANK() OVER (ORDER BY sal DESC) AS RANK_DESC 
FROM emp;

ex) 10�� �μ��� ���� �������� ����� �̸�, �޿�, �ش� �μ� ���� �޿� ������ ����Ͻÿ�.
SELECT empno, ename, sal, RANK() OVER (ORDER BY sal DESC) RANK FROM emp WHERE deptno=10 ; 

3) PARTITION BY : �׷� �� ���� Ȯ���ϱ�

���, �̸�, �޿�, �μ���ȣ, �μ� �� �޿� ������ ���
SELECT empno, ename, sal, deptno, RANK() OVER (PARTITION BY deptno ORDER BY sal DESC) RANK FROM emp;


- JOIN : �� �̻��� ���̺��� �����Ͽ� �����͸� �˻��ϴ� ���

īƼ�� �� : �˻��ϰ��� �ߴ� ������ �� �ƴ϶� ���ο� ���� ���̺���� ��� �����Ͱ� ��ȯ�Ǵ� ����
SELECT * FROM emp, dept;  --���� ���� ��� �� ��

1. ORACLE ����

1-1) ���� ���� (Equi Join) : �������� ������ Equality Condition(=)�� ���� ������ �̷����.
SELECT emp.ename, dept.dname FROM emp, dept WHERE emp.deptno = dept.deptno;

/���̺� �˸��ƽ� �ο��ϱ�
SELECT e.ename, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno;

/�÷����� ȣ���� �� ���̺�� �Ǵ� ���̺� �˸��ƽ��� ������ �� ����.
(���ο� �����ϴ� ���̺�鿡 ���� �÷��� �����ϸ�, �ݵ�� ���̺�� �Ǵ� ���̺� �˸��ƽ��� ����ؾ� ��.)
SELECT ename, dname FROM emp e, dept d WHERE e.deptno = d.deptno;
SELECT ename, dname, deptno FROM emp e, dept d WHERE e.deptno = d.deptno;  --����

/�߰����� ���� ����ϱ� (�̹� WHERE���� ��� ������ AND Ȱ��)
SELECT e.ename, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno AND e.ename = 'ALLEN';
SELECT e.ename, e.sal, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno AND e.sal BETWEEN 3000 AND 4000;


1-2) �񵿵� ���� (Non Equi Join) : ���̺��� � �÷��� ������ ���̺��� �÷��� ��ġ���� ���� �� ���.
                                        ���� ������ ����(=) �̿��� �����ڸ� ����.(BETWEEN AND, IS NULL, IN)
                                        
/����̸�, �޿�, �޿���� ���ϱ� (emp, salgrade ���̺� �̿�)
SELECT e.ename, e.sal, s.grade FROM emp e, salgrade s WHERE e.sal BETWEEN s.losal AND s.hisal; 
--���� �����Ͱ� ������ =�� ����߰�����, �׷��� �ʱ� ������ �ٸ� ������ ���.


2) ���� ���� (Self Join) : �� ���̺� �ȿ� ������ �����Ͱ� �ִ� ��� ���.
/����̸��� �ش� ����� ������(���)�̸� ���ϱ� (�����ڰ� ���� ��� ����)
SELECT e.ename ����̸�, m.ename �������̸� FROM emp e, emp m WHERE e.mgr = m.empno; 


3) �ܺ� ���� (Outer Join) : ������ �� �� ���̺�, �� �÷��� ����� ���� ���ٸ� ���̺�κ��� �����͸� ��ȯ���� ����.
                                  �� �� �÷����� ������� ���� ��(ex.�� �÷����� �ִ� ��)�� ǥ���ϰ� ���� �� ���.
/������ ���� �ݴ� ���̺��� ���� ���� �÷��� (+) ��ȣ ǥ��
SELECT DISTINCT (e.deptno), d.deptno FROM emp e, dept d WHERE e.deptno(+) = d.deptno;
--dept ���̺��� ������ 40�� ����(������� �ʾƼ�) -> emp ���̺��� ���ǿ� (+) ǥ��

/����̸��� �ش� ����� ������(���)�̸� ���ϱ� (�����ڰ� ���� ��� ����)
SELECT e.ename ����̸�, m.ename �������̸� FROM emp e, emp m WHERE e.mgr = m.empno(+);


[�ǽ�����]
1) ��� ����� �̸�, �μ���ȣ, �μ��̸��� ����Ͻÿ�. (emp, dept)
SELECT e.ename, e.deptno, d.dname FROM emp e, dept d WHERE e.deptno = d.deptno;

2) ������ MANAGER�� ����� ������ �̸�, ����, �μ���, �ٹ��� ������ ����Ͻÿ�. (emp, dept)
SELECT e.ename, e.job, d.dname, d.loc FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.job = 'MANAGER';

3) Ŀ�̼��� �ް� �޿��� 1,600 �̻��� ����� �̸�, �޿�, �μ���, �ٹ����� ����Ͻÿ�. (emp, dept)
SELECT e.ename, e.sal, d.dname, d.loc FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.comm IS NOT NULL AND e.sal >= 1600; 

4) �ٹ����� CHICAGO�� ��� ����� �̸�, ����, �μ���ȣ �� �μ��̸��� ����Ͻÿ�. (emp, dept)
SELECT e.ename, e.job, e.deptno, d.dname FROM emp e, dept d
WHERE e.deptno = d.deptno AND d.loc = 'CHICAGO'; 

5) �ٹ���(LOC) ���� �ٹ��ϴ� ����� ���� 5�� ������ ���, �ο��� ���� ���� ������ �����Ͻÿ�.
    (�ٹ� �ο��� 0���� ���� ǥ���Ͻÿ�.)
SELECT d.loc, COUNT(e.empno) FROM emp e, dept d WHERE e.deptno(+) = d.deptno
GROUP BY d.loc HAVING COUNT(e.empno) <= 5 ORDER BY COUNT(e.deptno);
--(����) COUNT(*)�� �ϰ� �Ǹ� NULL�� ���� ������ X. ������ �� �־��־����.


2. ǥ�� SQL

1) ���� ���� (Inner Join)
SELECT emp.ename, dept.deptno FROM emp INNER JOIN dept ON emp.deptno = dept.deptno;
/�˸��ƽ� �ο�
SELECT e.ename, d.deptno FROM emp e INNER JOIN dept d ON e.deptno = d.deptno;
/�ΰ����� ������ ������ WHERE �� ���
SELECT e.ename, d.deptno FROM emp e INNER JOIN dept d ON e.deptno = d.deptno WHERE e.ename = 'ALLEN';

/USING �� : ���� ���ǿ� ���� �÷��� �̸��� ���� �� ���
SELECT e.ename, d.dname FROM emp e INNER JOIN dept d USING(deptno);

(����) USING�� ���� �÷��� ���̺�� �Ǵ� ���̺� �˸��ƽ��� ������ ����.
SELECT e.ename, deptno FROM emp e INNER JOIN dept d USING(deptno);
--SELECT e.ename, d.deptno FROM emp e INNER JOIN dept d USING(deptno);
--����. USING�� deptno�� ���� ���� �� ���̺��� deptno�� �������ٰ� ��������.

2) ���� ���� (Self Join) 
/����̸��� �ش� ����� ������ �̸� ���ϱ� (�����ڰ� ���� ����� ����)
SELECT e.ename name, m.ename manager_name FROM emp e JOIN emp m ON e.mgr = m.empno;

3) �ܺ� ���� (Outer Join) : ������ ���� ���� ǥ��
/����̸��� �ش� ����� ������ �̸� ���ϱ� (�����ڰ� ���� ����� ����)           --������ ��
SELECT e.ename name, m.ename manager_name FROM emp e LEFT OUTER JOIN emp m ON e.mgr = m.empno;


[�ǽ�����]
6) ������ SALESMAN�� ����� ������ �̸�, ����, �μ���, �ٹ��� ������ ����Ͻÿ�. (emp, dept)
SELECT e.ename, e.job, d.dname, d.loc FROM emp e INNER JOIN dept d ON e.deptno = d.deptno AND e.job = 'SALESMAN';

7) �ٹ����� DALLAS�� ��� ����� �̸�, ����, �μ���ȣ, �μ��̸��� ����Ͻÿ�.
SELECT e.ename, e.job, d.deptno, d.dname FROM emp e INNER JOIN dept d ON e.deptno = d.deptno AND d.loc = 'DALLAS';

8) �����ں��� ���� �Ի��� ��� ����� �̸� �� �Ի����� �������� �̸� �� �Ի��ϰ� �Բ� ǥ���ϰ�
    �� ���̺��� ���� employee, emp hired, manager, mgr hired�� �����Ͻÿ�. (�����ڰ� ���� ��� �����)
SELECT e.ename "employee", e.hiredate "emp hired", m.ename "manager", m.hiredate "mgr hired"
FROM emp e JOIN emp m ON e.mgr = m.empno WHERE e.hiredate < m.hiredate;
