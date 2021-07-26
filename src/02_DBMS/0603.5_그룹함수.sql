-- [V] �׷��Լ� ; SUM, AVG, MIN, MAX, COUNT, STDDEV, VARIANCE
SELECT ENAME, ROUND(SAL, -3) FROM EMP; -- �������Լ�
SELECT MAX(SAL) FROM EMP; -- �׷��Լ�
SELECT ENAME, MAX(SAL) FROM EMP; --> VI�� ������������ �ذ�
SELECT DEPTNO, MAX(SAL) FROM EMP GROUP BY DEPTNO;
-- �� 1. �׷��Լ��� �ǽ�
SELECT ROUND(AVG(SAL),2) FROM EMP;
SELECT COUNT(*) FROM EMP; -- EMP ���̺��� ��(�ο�)��
SELECT AVG(SAL), SUM(SAL)/COUNT(SAL) FROM EMP;
SELECT COMM FROM EMP; -- (300+500+1400+0) / 4 = 550 SUM/14=157.XXXX
SELECT SUM(COMM) FROM EMP;
-- ��� �׷��Լ� NULL ���� ����
SELECT AVG(COMM) "���", SUM(COMM) "�հ�", COUNT(COMM) "����", 
  SUM(COMM)/COUNT(COMM) "������������" FROM EMP;
-- SAL�� ���, ��, �ּҰ�, �ִ밪, �л�, ǥ������, ����
SELECT ROUND(AVG(SAL)), SUM(SAL), MIN(SAL), 
        MAX(SAL), ROUND(VARIANCE(SAL)), ROUND(STDDEV(SAL)),
        COUNT(SAL) FROM EMP;
SELECT SQRT(VARIANCE(SAL)), STDDEV(SAL) FROM EMP;

-- �׷��Լ� MAX, MIN, COUNT�� ������, ������, ��¥�� ��� ��밡��
  -- ���� �ֱٿ� �Ի��� ����� �Ի��ϰ� �Ի����� ���� �����Ի��� ����� �Ի���
SELECT MAX(HIREDATE), MIN(HIREDATE) FROM EMP;
  -- 83/01/12 �ֱ��Ի� : X,XXX�� �ٹ�    80/12/17 �����Ի� : XX,XXX�� �ٹ�
SELECT MAX(HIREDATE) || '�ֱ��Ի� : ' || 
          TO_CHAR(MIN(TRUNC(SYSDATE-HIREDATE)),'99,999') || '�� �ٹ�',
       MIN(HIREDATE) || '�����Ի� : ' || 
          TO_CHAR(MAX(TRUNC(SYSDATE-HIREDATE)), '99,999') || '�� �ٹ�'
  FROM EMP;
  -- 10�� �μ� �Ҽ��� ��� �޿� ���
  SELECT AVG(SAL) FROM EMP WHERE DEPTNO=10;

-- �� 2. GROUP BY ��
-- �μ���ȣ�� �ִ�޿�
SELECT DEPTNO, MAX(SAL) FROM EMP GROUP BY DEPTNO;
-- �μ���ȣ�� �����, �ִ�޿�, �ּұ޿�, ��ձ޿�(�Ҽ���2�ڸ�)
SELECT DEPTNO, COUNT(*), MAX(SAL), MIN(SAL), ROUND(AVG(SAL), 2) 
  FROM EMP GROUP BY DEPTNO
  ORDER BY DEPTNO;
-- SAL�� 5000�̸��� ����� ���ؼ��� �μ���ȣ�� �����, �ִ�޿�, �ּұ޿�, ��ձ޿�
SELECT DEPTNO, COUNT(*), MAX(SAL), MIN(SAL), AVG(SAL)
  FROM EMP
  WHERE SAL<5000
  GROUP BY DEPTNO
  ORDER BY DEPTNO;
-- �μ��� �����, �ִ�޿�, �ּұ޿�, ��ձ޿�
SELECT DNAME, COUNT(*), MAX(SAL), MIN(SAL), AVG(SAL)
  FROM EMP E, DEPT D
  WHERE E.DEPTNO=D.DEPTNO
  GROUP BY DNAME
  ORDER BY DNAME;

-- �� 3. HAVING �� ; �׷��Լ� ����� ����
-- �μ���ȣ�� ��� �޿�(��ձ޿��� 2000�̻��� �μ��� ���)
SELECT DEPTNO, AVG(SAL) AVG_SAL FROM EMP 
  GROUP BY DEPTNO
  HAVING AVG(SAL) >= 2000
  ORDER BY AVG_SAL DESC; -- �ʵ��� ��Ī�� ORDER BY �������� ��� ����. 
                                      -- HAVING ��Ī�� �� �� ����
-- �� 4. ��� ���� ���� ���谪 ����
SELECT DEPTNO, SUM(SAL) FROM EMP GROUP BY ROLLUP(DEPTNO);
SELECT DEPTNO, JOB, SUM(SAL) FROM EMP GROUP BY DEPTNO, JOB ORDER BY DEPTNO;
SELECT DEPTNO, JOB, SUM(SAL) FROM EMP GROUP BY ROLLUP(DEPTNO, JOB);

-- ��<�� ��������>
-- 1. �ο���,�ִ� �޿�,�ּ� �޿�,�޿��� ���� ���
SELECT COUNT(*), MAX(SAL), MIN(SAL), SUM(SAL) FROM EMP;
-- 2. ������ �ο����� ���Ͽ� ���
SELECT JOB, COUNT(*) FROM EMP GROUP BY JOB;
--- 3. �ְ� �޿��� �ּ� �޿��� ���̴� ���ΰ� ���
SELECT MAX(SAL) - MIN(SAL) FROM EMP;
-- 4. �� �μ����� �ο���, �޿� ���, ���� �޿�, �ְ� �޿�, �޿��� ���� ���
  -- (�޿��� ���� ���� ������)
SELECT DEPTNO, COUNT(*), AVG(SAL), MIN(SAL), MAX(SAL), SUM(SAL) 
    FROM EMP
    GROUP BY DEPTNO 
    ORDER BY SUM(SAL) DESC; 
    
SELECT DEPTNO, COUNT(*), AVG(SAL), MIN(SAL), MAX(SAL), SUM(SAL) SUM_SAL 
    FROM EMP 
    GROUP BY DEPTNO 
    ORDER BY SUM_SAL DESC; -- order by ������ �ʵ��� ��Ī �� �� ����
    
SELECT DNAME, COUNT(*), AVG(SAL), MIN(SAL), MAX(SAL), SUM(SAL) 
    FROM EMP E, DEPT D
    WHERE E.DEPTNO=D.DEPTNO
    GROUP BY DNAME
    ORDER BY SUM(SAL) DESC;
-- 5. �μ���, ������ �׷��Ͽ� ����� �μ���ȣ, ����, �ο���, �޿��� ���, �޿��� ���� ���(�μ���ȣ, ���������� �������� ����)
SELECT DEPTNO, JOB, COUNT(*), AVG(SAL), SUM(SAL) 
    FROM EMP 
    GROUP BY DEPTNO, JOB 
    ORDER BY DEPTNO, JOB;  -- �μ���ȣ, ������
    
SELECT DNAME, JOB, COUNT(*), AVG(SAL), SUM(SAL)
    FROM EMP E, DEPT D
    WHERE E.DEPTNO=D.DEPTNO
    GROUP BY DNAME, JOB ORDER BY DNAME, JOB; -- �μ��̸�, ������
-- 6. ������, �μ��� �׷��Ͽ� �����  ����, �μ���ȣ, �ο���, �޿��� ���, �޿��� ���� ���
  -- (��°���� ������, �μ���ȣ �� �������� ����)
SELECT JOB, DEPTNO, COUNT(*), AVG(SAL), SUM(SAL) 
    FROM EMP 
    GROUP BY JOB, DEPTNO 
    ORDER BY JOB, DEPTNO;
--7. ������� 5���̻� �Ѵ� �μ���ȣ�� ������� ���
SELECT DEPTNO, COUNT (*) 
  FROM EMP 
  GROUP BY DEPTNO 
  HAVING COUNT(*)>5; --�׷�(group by)�� ���� ������ having����
-- 8. ������� 5���̻� �Ѵ� �μ���� ������� ���
SELECT DNAME, COUNT(*) FROM EMP E, DEPT D
    	WHERE E.DEPTNO=D.DEPTNO 
      GROUP BY DNAME 
      HAVING COUNT(*)>5;
--9. ������ �޿��� ����� 3000�̻��� ������ ���ؼ� ������, ��� �޿�, �޿��� ���� ���
SELECT JOB, AVG(SAL), SUM(SAL) 
    FROM EMP 
    GROUP BY JOB 
    HAVING AVG(SAL)>=3000;
--10. �޿� ���� 5000�� �ʰ��ϴ� �� ������ ���ؼ� ������ �޿����� ���(�޿� �հ�� �������� ����)
SELECT JOB, SUM(SAL)
    FROM EMP 
    GROUP BY JOB 
    HAVING SUM(SAL)>5000 
    ORDER BY SUM(SAL) DESC;
    
SELECT JOB, SUM(SAL) SUMSAL
    FROM EMP 
    GROUP BY JOB 
    HAVING SUM(SAL)>5000 
    ORDER BY SUMSAL DESC; -- ORDER BY ������ �ʵ� ��Ī ��� ����
--11. �μ��� �޿����, �μ��� �޿��հ�, �μ��� �ּұ޿��� ���
SELECT DEPTNO, AVG(SAL), SUM(SAL), MIN(SAL) 
    FROM EMP 
    GROUP BY DEPTNO; -- �μ���ȣ��
SELECT DNAME, AVG(SAL), SUM(SAL), MIN(SAL) 
    FROM EMP E, DEPT D
    WHERE E.DEPTNO=D.DEPTNO 
    GROUP BY DNAME; -- �μ��̸���
--12. ���� 11���� �����Ͽ�, �μ��� �޿���� �ִ�ġ, �μ��� �޿����� �ִ�ġ, �μ��� �ּұ޿��� �ִ�ġ�� ���
SELECT max(AVG(SAL)), max(SUM(SAL)), max(MIN(SAL))
    FROM EMP 
    GROUP BY DEPTNO;
--13. ��� ���̺��� �Ʒ��� ����� �����(�⵵�� ������)
--H_YEAR COUNT(*)	MIN(SAL)	MAX(SAL)	AVG(SAL)	SUM(SAL)
--  80	  1		    800		    800		    800		    800
--	81	 10		    950		    5000	    2282.5	  22825
--	82	  2		    1300	    3000	    2150		  4300
--	83	  1		    1100	    1100	    1100	    1100
SELECT TO_CHAR(HIREDATE,'YY') H_YEAR, COUNT(*), MIN(SAL), MAX(SAL), AVG(SAL), 
        SUM(SAL) 
    FROM EMP GROUP BY TO_CHAR(HIREDATE,'YY') ORDER BY H_YEAR;

SELECT SUBSTR(EXTRACT(YEAR FROM HIREDATE),3,2) H_YEAR, 
        COUNT(*), MIN(SAL), MAX(SAL), AVG(SAL), SUM(SAL) 
    FROM EMP GROUP BY SUBSTR(EXTRACT(YEAR FROM HIREDATE),3,2) 
    ORDER BY H_YEAR;
-- 14.  �Ʒ��� ����� ���(�Ի�⵵�� �����)
--  1980     1	
--  1981     10
--  1982     2
--  1983     1
--  total    14	
SELECT NVL(TO_CHAR(HIREDATE,'YYYY'),'total') YEAR, COUNT(*) CNT FROM EMP
    GROUP BY ROLLUP(TO_CHAR(HIREDATE,'YYYY'));
SELECT NVL(TO_CHAR(EXTRACT(YEAR FROM HIREDATE)),'TOTAL') YEAR, COUNT(*) CNT FROM EMP
    GROUP BY ROLLUP(EXTRACT(YEAR FROM HIREDATE));
--15. �ִ�޿�, �ּұ޿�, ��ü�޿���, ����� ���
SELECT MAX(SAL), MIN(SAL), SUM(SAL), AVG(SAL) FROM EMP;
--16. �μ��� �ο��� ���
SELECT DEPTNO, COUNT(*) FROM EMP GROUP BY DEPTNO;
SELECT DNAME, COUNT(*) FROM EMP E, DEPT D 
        WHERE E.DEPTNO=D.DEPTNO GROUP BY DNAME ORDER BY DNAME;
--17. �μ��� �ο����� 6���̻��� �μ���ȣ ���
SELECT DEPTNO, COUNT(*) 
    FROM EMP 
    GROUP BY DEPTNO 
    HAVING COUNT(*)>=6;
--18. �޿��� ���� ������� ����� �ο��Ͽ� ������ ���� ����� ������ �Ͻÿ�. 
-- (��Ʈ self join, group by, count���)
--ENAME	    ���
--________________________
--KING		1
--SCOTT		2
--����
SELECT E1.ENAME, E1.SAL, E2.ENAME, E2.SAL
  FROM EMP E1, EMP E2
  WHERE E1.SAL<E2.SAL(+); -- 1�ܰ� : SELF JOIN
SELECT E1.ENAME, COUNT(E2.ENAME)+1 "RANK"
  FROM EMP E1, EMP E2
  WHERE E1.SAL<E2.SAL(+)
  GROUP BY E1.ENAME
  ORDER BY RANK; -- �ϼ��ܰ� : GROUP BY, COUNT
  
SELECT ENAME, RANK() OVER(ORDER BY SAL DESC) "RANK",
              DENSE_RANK() OVER(ORDER BY SAL DESC) "DENSE_RANK",
              ROW_NUMBER() OVER(ORDER BY SAL DESC) "ROW_NUM"
        FROM EMP; -- �Լ� ���





