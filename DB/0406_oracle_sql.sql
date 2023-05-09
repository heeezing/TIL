--04/06---------------------------------------------------------------------------------------------------------------------------------------------------------------------

<SELECT ��>
�����ͺ��̽��κ��� ����Ǿ� �ִ� �����͸� �˻��ϴµ� ���

- ��ü ���̺� ��
SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM emp;
SELECT * FROM emp;

- Ư�� �� ���� 
SELECT ename,job FROM emp;

- �ּ�
SELECT * /*�ּ�*/ FROM emp; --�ּ�

- dual
DUAL : �Լ� �� ����� ����� �� �� ����� �� �ִ� ����(PUBLIC) ���̺�
       ����� �����Ͱ� �ִ� ���̺��� �������� ���� ��� ��
       ���� ��¥, �ð� ����, �Ͻ����� ���, ��¥ ���� � �ַ� ���
SELECT ASCII(0) FROM dual;
SELECT SYSDATE FROM dual;
SELECT 7 + 10 FROM dual;

- �����
SELECT ename, sal, sal+300 FROM emp;
SELECT ename, sal, (sal+300)*12 FROM emp;

- NULL ���� ����
NULL : ����� �� ���ų�, �Ҵ���� �ʾҰų�, �� �� ���ų�, ������ �� ���� ��
       (0�̳� ������� �ٸ�)
SELECT empno, ename, job, comm FROM emp; 
- NULL���� �����ϴ� ������� null�� ���
SELECT empno, ename, sal+comm FROM emp; --ex) 200+(null)=(null)

- �˸��ƽ� (ALIAS) ����
�� �̸��� �ٲ�
�� �̸� �ٷ� �ڿ� ����. �� �̸��� ALIAS ���̿� ���� ������ as Ű���尡 �� ���� ����.
SELECT sal*12 Asal FROM emp; --��ҹ��� ����x
SELECT sal*12 as Asal FROM emp;

�˸��ƽ��� ū ����ǥ�� ����ϴ� ��� --�ڹ� ���� �� ���x. �ܾ� ���� ����.
- ��ҹ��� ������ ���� ��
- ���� ���� ��
- _(�����), #(��) �� Ư������ ��� ��
- ���ڷ� ������ ��
SELECT ename "Name" FROM emp;
SELECT sal*12 "Annual Salary" FROM emp;
SELECT sal*12 "365sal" FROM emp;

- ���� ������
���̳� ���ڿ��� �ٸ� ���� ����. �� ���� ���μ�(||)���� ��Ÿ��. ��� ���� ���ڽ��� ����.
SELECT ename || ' has $' || sal FROM emp;  --����,���ڿ� ������ ����. ��� ���� ����ǥ ���.
- ���� �����ڿ� NULL�� : ���ڿ��� NULL���� ������ ��� ����� ���ڿ��� ����. (NULL�� �����)
SELECT ename || comm FROM emp;

- DISTINCT : �ߺ��� ���� ������
SELECT DISTINCT deptno FROM emp;
SELECT DISTINCT (deptno) FROM emp;


[�ǽ�����]
1) emp���̺��� �����ȣ, ����̸�, ������ ����Ͻÿ�.
SELECT empno, ename, sal FROM emp;

2) emp���̺��� ����̸�,������ ����ϴµ� �÷����� "�� ��", "�� ��"���� �ٲ㼭 ����Ͻÿ�.
SELECT ename "�� ��",  sal "�� ��" FROM emp;

3) emp���̺��� �����ȣ, ����̸�, ����, ������ ���ϰ� ���� �÷����� "�����ȣ", "����̸�", "����", "����"���� ����Ͻÿ�.
SELECT empno �����ȣ, ename ����̸�,  sal ����, sal*12 ���� FROM emp;

4) emp���̺��� ������ �ߺ����� �ʰ� ǥ���Ͻÿ�.
SELECT DISTINCT job from emp;

5) emp���̺��� ������ ������ ��ǥ(,)�� �����ؼ� ǥ���ϰ� �÷����� "Employee and Job"���� ǥ���Ͻÿ�.
SELECT ename || ',' || job "Employee and Job" FROM emp; 


- WHERE���� �̿��� �� ����
  ���� üũ ��� ���ǿ� �´� �ุ �о� ��.
SELECT * FROM emp WHERE deptno=10;
SELECT * FROM emp WHERE ename='SMITH';
SELECT * FROM emp WHERE hiredate>'81-12-03';  --'/'�ε� ������ 

(����)WHERE������ �˸��ƽ��� ����� �� ����!
SELECT ename, sal, sal*12 ansal FROM emp WHERE sal*12 > 15000;

- �� �������� ���
SELECT * FROM emp WHERE hiredate >= '81/04/02'; --'-'�ε� ������
���� ������ true
SELECT * FROM emp WHERE hiredate <> '81-04-02';
SELECT * FROM emp WHERE hiredate ^= '81-04-02';
SELECT * FROM emp WHERE hiredate != '81-04-02';

�̻�~����
SELECT * FROM emp WHERE sal >= 2000 AND sal <=5000;

BETWEEN ~ AND : �̻�~���� ������
SELECT * FROM emp WHERE sal BETWEEN 2000 AND 5000;
SELECT * FROM emp WHERE sal NOT BETWEEN 2000 AND 5000;

IN : �� ��� �߿� ���� ��ġ�ϴ� ������ �о�� 
SELECT * FROM emp WHERE sal IN (1300, 2450, 3000); --���� �� ���� �� �Ұ�ȣ �ؾ���.
SELECT * FROM emp WHERE sal NOT IN (1300, 2450, 3000);
SELECT ename, mgr, deptno FROM emp WHERE ename IN ('ALLEN', 'FORD');

LIKE : ���� �˻�
% : 0�� �̻��� ���ڸ� ��Ÿ��
_ : �� ���ڸ� ��Ÿ��

S�� ���ԵǾ� �ִ� ����(��)�� ����
SELECT * FROM emp WHERE ename LIKE '%S%'; --S�� ó��, �߰�, ���� ���� �̸�
�Ի����� 22�� ������ �Ի����� ����
SELECT * FROM emp WHERE hiredate LIKE '%22';
'FOR'�ڿ� �� ���ڰ� �ִ� ����(��)�� ����
SELECT * FROM emp WHERE ename LIKE 'FOR_'; --FOR ������ '��' ���ڰ� ����


[�ǽ�����]
1) emp���̺��� �����ȣ�� 7698�� ����� �̸�, ����, �޿��� ����Ͻÿ�.
SELECT ename, job, sal FROM emp WHERE empno = 7698;

2) emp���̺��� ����̸��� SMITH�� ����� �̸�, ����, �μ���ȣ�� ���Ͻÿ�.
SELECT ename, sal, deptno FROM emp WHERE ename = 'SMITH';

3) ������ 2500�̻� 3500�̸��� ����� �̸�, �Ի���, ������ ���Ͻÿ�.
SELECT ename, hiredate, sal FROM emp WHERE 2500<=sal AND sal<3500; 

4) �޿��� 2000���� 3000 ���̿� ���Ե��� ���� ����� �̸�, ����, �޿��� ����Ͻÿ�.
SELECT ename, job, sal FROM emp WHERE sal NOT BETWEEN 2000 AND 3000;
SELECT ename, job, sal FROM emp WHERE NOT (2000<=sal AND sal<=3000);
