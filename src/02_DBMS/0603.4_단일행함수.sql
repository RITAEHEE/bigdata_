-- [IV] �������Լ�;�����Լ�
-- �Լ� = �������Լ� + �׷��Լ�(�����Լ�)
SELECT HIREDATE, TO_CHAR(HIREDATE, 'YY"��"MM"��"DD"��"') FROM EMP; -- �������Լ�
SELECT ENAME, INITCAP(ENAME) FROM EMP; -- �������Լ�
SELECT SUM(SAL) FROM EMP; -- �׷��Լ�(�����Լ�)
SELECT DEPTNO, SUM(SAL) FROM EMP GROUP BY DEPTNO; -- �׷��Լ�(�μ���ȣ�� �հ�)
SELECT SAL FROM EMP;
-- EX. ���ڰ����Լ�, ���ڰ����Լ�, ��¥�����Լ�, ����ȯ�Լ�, NVL(), ETC...
-- 1. �����Լ�
-- DUAL ���̺�  : ����Ŭ���� �����ϴ� 1��1���ڸ� DUMMY TABLE
SELECT * FROM DUAL;
DESC DUAL;
SELECT * FROM DUMMY;
DESC DUMMY;
SELECT ABS(-9) FROM DUAL; -- ���밪
SELECT FLOOR(34.5678) FROM DUAL; -- �Ҽ������� ����
SELECT FLOOR(34.5678*100)/100 FROM DUAL; -- �Ҽ��� ���ڸ����� ����
SELECT TRUNC(34.5678) FROM DUAL; -- �Ҽ������� ����
SELECT TRUNC(34.5678, 2) FROM DUAL; -- �Ҽ��� ���ڸ����� ����
SELECT TRUNC(34.5678, -1) FROM DUAL; -- ���� �ڸ����� ����
  -- EMP ���̺��� �̸�, �޿�(���� �ڸ����� ����)
  SELECT ENAME, SAL, TRUNC(SAL, -2) FROM EMP;
SELECT CEIL(34.5678) FROM DUAL; -- �Ҽ������� �ø�
SELECT ROUND(34.5678) FROM DUAL; -- �Ҽ������� �ݿø�
SELECT ROUND(34.5678, 2) FROM DUAL; -- �Ҽ��� �ι�° �ڸ����� �ݿø�
SELECT ROUND(34.5678, -1) FROM DUAL; -- ���� �ڸ����� �ݿø�

SELECT MOD(9,2) FROM DUAL; -- ������ ������
  -- Ȧ���� �Ի��� ����� ��� ����
  SELECT * FROM EMP WHERE MOD(TO_CHAR(HIREDATE, 'MM'), 2)=1;

-- 2. �����Լ�
SELECT UPPER('abcABC') FROM DUAL;
SELECT LOWER('abcABC') FROM DUAL;
SELECT INITCAP('WELCOME TO ORACLE') FROM DUAL;
  -- JOB�� MANAGER�� ������ ��� �ʵ�
  SELECT * FROM EMP WHERE UPPER(JOB)='MANAGER';
  -- �̸��� SCOTT�� ������ ��� �ʵ�
  SELECT * FROM EMP WHERE INITCAP(ENAME) = 'Scott';
-- ���ڿ���(CONCAT�Լ�, ||������)
SELECT 'AB'||'CD'||'EF'||'GH' FROM DUAL;
SELECT CONCAT(CONCAT('AB','CD'), CONCAT('EF','GH')) FROM DUAL;
  -- XXX�� XX�� (SCOTT�� JOB�̴�) ���
  SELECT CONCAT(CONCAT(ENAME, '�� '), CONCAT(JOB, '�̴�'))FROM EMP;
-- SUBSTR(STR, ������ġ, ���ڰ���) ù��°��ġ�� 1, ������ġ�� ����
-- SUBSTRB(STR, ���۹���Ʈ��ġ, ���ڹ���Ʈ��)
SELECT SUBSTR('ORACLE',3,2) FROM DUAL; -- 3��° ���ں��� 2���� ����
SELECT SUBSTRB('ORACLE',3,2) FROM DUAL; -- 3��° BYTE���� 2BYTE ����
SELECT SUBSTR('�����ͺ��̽�',4,2) FROM DUAL; -- 4��° ���ں��� 2���� ����
SELECT SUBSTRB('�����ͺ��̽�',3,6) FROM DUAL; -- 4��° BYTE���� 3BYTE ����
  -- ����� �ѱ��ڰ� 1BYTE. �ѱ��� �ѱ��� 3BYTE
-- O R A C L E
-- 1 2 3 4 5 6 (��ġ)
---6-5-4-3-2-1 (��ġ)
SELECT SUBSTR('WELCOME TO ORACLE', -1,1) FROM DUAL;
  
  SELECT SUBSTR(123, 2,1) FROM DUAL; -- ���ڵ� ����
  -- 9���� �Ի��� ����� ��� �ʵ� 81/09/01
  ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD';
  SELECT * FROM EMP WHERE SUBSTR(HIREDATE, 4, 2)='09';
  -- 9�Ͽ� �Ի��� ����� ��� �ʵ� 81/09/09
  SELECT * FROM EMP WHERE SUBSTR(HIREDATE, -2, 2) = '09';
SELECT LENGTH('ABCD') FROM DUAL;  -- ���ڼ�
SELECT LENGTHB('ABCD') FROM DUAL; -- BYTE��
SELECT LENGTH('����Ŭ') FROM DUAL; -- ���ڼ� : 3
SELECT LENGTHB('����Ŭ') FROM DUAL; -- BYTE�� : 9
-- INSTR(STR,ã������) ; STR���� ã�� ������ ��ġ(ù��°1). ������ 0
-- INSTR(STR, ã������, ������ġ) ; STR���� ������ġ���� ã�Ƽ� ã�� ������ ��ġ
SELECT INSTR('ABCABC','B') FROM DUAL; -- ó������ ã�Ƽ� ó�������� �� B�� ��ġ 2
SELECT INSTR('ABCABC','B',3) FROM DUAL; -- ��3��°���� ã�Ƽ� ó�� ������ B�� ��ġ 5
SELECT INSTR('ABCABC','B',-3) FROM DUAL; -- ��3��°���� �ڷ�ã�� ó�� ������ B�� ��ġ 5
SELECT INSTR('ABCABCABC','B',-3) FROM DUAL;
  -- 9���� �Ի��� ���(INSTR�̿�) 81/09/01 09/09/09
  SELECT * FROM EMP WHERE INSTR(HIREDATE, '09',3)=4;
  -- 9�Ͽ� �Ի��� ���(INSTR�̿�)
  SELECT * FROM EMP WHERE INSTR(HIREDATE, '09',6) =7;
  -- 9���� �ƴ� �ٸ� ���� �Ի��� ���
  SELECT * FROM EMP WHERE INSTR(HIREDATE, '09', 6) = 0;
-- LPAD(STR, �ڸ���, ä�﹮��) STR�� �ڸ�����ŭ Ȯ���ϰ� ���� ���ڸ��� ä�﹮�� ���
SELECT LPAD('ORACLE', 10, '#'), RPAD('ORACLE',10,'*') FROM DUAL;  
  SELECT ENAME, SAL FROM EMP;
  SELECT ENAME, LPAD(SAL, 6, '*') FROM EMP;
  -- ��� S****(�̸��� �� �ѱ��ڸ� ����ϰ� �������� *)
  -- ��� W***  (RPAD, SUBSTR, LENGTH)
  SELECT RPAD('S',5,'*') FROM DUAL;
  SELECT EMPNO, RPAD(SUBSTR(ENAME, 1, 1), LENGTH(ENAME), '*') NAME FROM EMP;
  -- 7499 _****H 80/12/**
  -- 7521 __***D 81/01/**
  DESC EMP;
  SELECT EMPNO, LPAD(LPAD(SUBSTR(ENAME, -1, 1), LENGTH(ENAME), '*'), 10, ' ') NAME,
      RPAD(SUBSTR(HIREDATE, 1, 6), LENGTH(HIREDATE), '*') HIRE
    FROM EMP; -- �̸��ڸ�������
  -- �̸��� ����° �ڸ��� R�� ����� ��� �ʵ�(LIKE, INSTR, SUBSTR)
  SELECT * FROM EMP WHERE ENAME LIKE '__R%';
  SELECT * FROM EMP WHERE INSTR(ENAME, 'R',3)=3; -- RARA
  SELECT * FROM EMP WHERE SUBSTR(ENAME, 3, 1) ='R';
SELECT TRIM('   ORACLE DB    ') MSG FROM DUAL;
SELECT LTRIM('   ORACLE DB    ') MSG FROM DUAL;
SELECT RTRIM('   ORACLE DB    ') MSG FROM DUAL;
SELECT REPLACE(ENAME, 'A','XX') FROM EMP;
SELECT REPLACE(HIREDATE, '0','XX') FROM EMP;

-- 3. ��¥���� �Լ� �� �����
SELECT SYSDATE FROM DUAL; -- SYSDATE:����
ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD DY HH24:MI';
ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD';--���󺹱�
SELECT TO_CHAR(SYSDATE, 'YY-MM-DD HH24:MI:SS') FROM DUAL;
  -- ����Ŭ Ÿ�� : ����, ����, DATE (����, DATE�� ���� ����)
SELECT SYSDATE-1 �����̽ð�, SYSDATE ����, SYSDATE+1 �����̽ð� FROM DUAL;
-- 14����
SELECT SYSDATE+14 FROM DUAL;
  -- EMP���� �̸�, �Ի���, �ٹ��ϼ�
  SELECT ENAME, HIREDATE, FLOOR(SYSDATE-HIREDATE) FROM EMP;
  SELECT ENAME, HIREDATE, TRUNC(SYSDATE-HIREDATE) FROM EMP;
  -- �̸�, �Ի���, �ٹ��ּ�, �ٹ����
  SELECT ENAME, HIREDATE, TRUNC((SYSDATE-HIREDATE)/7) WEEK,
          FLOOR((SYSDATE-HIREDATE)/365) YEAR 
      FROM EMP;
  -- �̸�, �Ի���, �ٹ����� (MONTHS_BETWEEN()�Լ�:�γ�¥�� ������)
  SELECT ENAME, HIREDATE, TRUNC(MONTHS_BETWEEN(SYSDATE, HIREDATE)) MONTH FROM EMP;
  -- �̸�, �Ի���, ����������� (ADD_MONTHS()�Լ� ; Ư���������� �����)
  SELECT ENAME, HIREDATE, ADD_MONTHS(HIREDATE, 3) FROM EMP;
  -- �̸�, �Ի��� ���� ���� �޿��� �� (�޿��� �Ŵ� SAL�� �ް� �󿩱��� 2�� COMM�� ����)
  SELECT ENAME, TRUNC(MONTHS_BETWEEN(SYSDATE,HIREDATE))*SAL + 
                TRUNC((SYSDATE-HIREDATE)/365)*2*NVL(COMM,0) "COST" FROM EMP;
-- NEXT_DAY(Ư����¥, '��') Ư�������κ��� ó�� �����ϴ� ������
SELECT NEXT_DAY(SYSDATE, '��') FROM DUAL;
-- LAST_DAY(Ư����¥) ; Ư����¥�� ���� ����
SELECT LAST_DAY(SYSDATE) FROM DUAL;
  -- EMP �̸�, �Ի���, ù���޳�(���޳� ����)
  SELECT ENAME, HIREDATE, LAST_DAY(HIREDATE) FROM EMP;
-- ROUND;��¥ �ݿø�  TRUNC;��¥����
SELECT ROUND(34.5678, 2) FROM DUAL;
SELECT ROUND(SYSDATE, 'YEAR') FROM DUAL; -- ��� : ����� 1��1��
SELECT ROUND(SYSDATE, 'MONTH') FROM DUAL; -- ��� : ����� 1�� (1~15�ϱ����� �̹���1��)
SELECT ROUND(SYSDATE, 'DAY') FROM DUAL; -- ��� : ����� �Ͽ���
SELECT ROUND(SYSDATE) FROM DUAL; -- ��� : ����� 0��
SELECT TRUNC(SYSDATE, 'YEAR') FROM DUAL; -- ��� : ����1��1��
SELECT TRUNC(SYSDATE, 'MONTH') FROM DUAL; -- ��� : �̹��� 1��
SELECT TRUNC(SYSDATE, 'DAY') FROM DUAL; -- ��� : ���� �Ͽ���
SELECT TRUNC(SYSDATE) FROM DUAL; -- ��� : ���� 0��
  -- EX1.�̸�, �Ի���, �Ի��Ѵ��� 5��
  SELECT ENAME, HIREDATE, TRUNC(HIREDATE, 'MONTH')+4 FROM EMP;
  -- EX2.�̸�, �Ի���, ���޳�(5��) ; ������ �Ի����� �Ѵ��� �ȵǾ 5�ϵǸ� ������ ����
  SELECT ENAME, HIREDATE, ROUND(HIREDATE+11, 'MONTH')+4 FROM EMP; --15~16������ 4~5��
  -- EX3.�̸�, �Ի���, ���޳�(15��) ROUND������ 15~16. 14~15�� ���غ���
  SELECT ENAME, HIREDATE, ROUND(HIREDATE+1, 'MONTH')+14 FROM EMP;
  -- EX4.�̸�, �Ի���, ���޳�(25��) 15~16������ 24~25�� ���� ����
  SELECT ENAME, HIREDATE, ROUND(HREDATE-9, 'MONTH')+24 FROM EMP;
  
-- 4. ����ȯ�Լ� (TO_CHAR()���ڷ� ����ȯ, TO_DATE() DATE���� ����ȯ)
--(1)TO_CHAR(DATE�� ������, '�������')
SELECT TO_CHAR(SYSDATE, 'YY"��"MM"��"DD"��" DY"����" HH24:MI:SS') FROM DUAL;
    -- YYYY �⵵4�ڸ� / YY �⵵2�ڸ� / RR �⵵ 2�ڸ� / MM �� / DD ��
    -- DAY ���� / DY ���Ͽ��ǥ��
    -- AM �̳� PM / HH12 12�ð� / HH24 24�ð�
    -- MI �� / SS ��
  SELECT ENAME, TO_CHAR(HIREDATE, 'YY/MM/DD DY AM HH12:MI:SS') FROM EMP;
--(2)TO_CHAR(���ڵ�����, '�������')
    -- 0 : �ڸ���. ��������� �ڸ����� ������ 0���� ä��.  
    -- 9 : �ڸ���. ��������� �ڸ����� ���Ƶ� ���ڸ�ŭ�� ���
    -- , : ���ڸ����� , ���� . : �Ҽ���
    -- L : ������ȭ���� 
    -- $ : ��ȭ���� $�� �տ� ���� �� ����
SELECT TO_CHAR(12345678, '999,999,999.9') FROM DUAL; -- 12,345,678
SELECT TO_CHAR(12345678, '000,000,000.0') FROM DUAL; -- 012,345,678
SELECT TO_CHAR(1000, 'L9,999') FROM DUAL;
SELECT ENAME, SAL, TO_CHAR(SAL, '$99,999') FROM EMP;

--(2)TO_DATE(����, "����") 
SELECT TO_DATE('20210603','YYYYMMDD') FROM EMP;
  -- 81/5/1 ~ 83/5/1 ���̿� �Ի��� ������ ��� �ʵ� ���
  SELECT * FROM EMP 
    WHERE HIREDATE BETWEEN TO_DATE('810501','RRMMDD') AND 
                          TO_DATE('830501','RRMMDD');
  ALTER SESSION SET NLS_DATE_FORMAT='MM/DD/YYYY';
  ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD';

-- TO_NUMBER(����, ����) 
SELECT TO_NUMBER('3,456.78', '9,999.99')+1 FROM DUAL;
SELECT '3456.78'+1 FROM DUAL;
SELECT '3,456.78'+1 FROM DUAL;

-- 5. NVL(���ϼ����ִµ�����, ���̸����Ұ�) -- �Ű����� 2���� Ÿ�� ��ġ
-- �̸�, ����� ���(MGR)�� ���. ���ӻ�簡 ���� ��� 'CEO'�� ���
SELECT ENAME, NVL(TO_CHAR(MGR), 'CEO') BOSS FROM EMP;

-- 6. ETC
-- (1) EXTRACT ; �⵵�� ���� �����ϰ��� �� ��
SELECT EXTRACT(YEAR FROM SYSDATE) FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YYYY') FROM DUAL;
SELECT EXTRACT(MONTH FROM SYSDATE) FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'MM') FROM DUAL;
  -- 81�⵵ �Ի��� ����
  SELECT * FROM EMP WHERE EXTRACT(YEAR FROM HIREDATE) = 1981;
  SELECT * FROM EMP WHERE TO_CHAR(HIREDATE, 'YY') = '81';
  SELECT * FROM EMP WHERE HIREDATE BETWEEN
              TO_DATE('1981-01-01','YYYY-MM-DD') AND
              TO_DATE('1981-12-31','YYYY-MM-DD');
-- (2) ������ ��� : LEVEL, START WITH ����, CONNECT BY PRIOR ����
SELECT LEVEL,LPAD(' ', LEVEL*2)||ENAME FROM EMP
  START WITH MGR IS NULL
  CONNECT BY PRIOR EMPNO=MGR;

-- �� <�� ��������>
-- 1. ���� ��¥�� ����ϰ� TITLE�� ��Current Date���� ����ϴ� SELECT ������ ����Ͻÿ�.
SELECT SYSDATE "Current Date" FROM DUAL;
-- 2. EMP ���̺��� ���� �޿��� 15%�� ������ �޿��� ����Ͽ�,
-- �����ȣ,�̸�,����,�޿�,������ �޿�(New Salary),������(Increase)�� ����ϴ� SELECT ����
SELECT EMPNO, ENAME, JOB, SAL, 
        SAL*1.15 "New Salary", SAL*0.15 "Increase" 
    FROM EMP;
--3. �̸�, �Ի���, �Ի��Ϸκ��� 6���� �� ���ƿ��� ������ ���Ͽ� ����ϴ� SELECT ������ ����Ͻÿ�.
SELECT ENAME, HIREDATE, 
        NEXT_DAY(ADD_MONTHS(HIREDATE, 6), '��') "6MonthsLaterMon"
    FROM EMP;
--4. �̸�, �Ի���, �Ի��Ϸκ��� ��������� ������, �޿�, �Ի��Ϻ��� ��������� ���� �޿��� �Ѱ踦 ���
SELECT ENAME, HIREDATE, 
        TRUNC(MONTHS_BETWEEN(SYSDATE, HIREDATE)) MONTHS, SAL, 
        ROUND(MONTHS_BETWEEN(SYSDATE, HIREDATE)*SAL,2) SUM_SAL 
    FROM EMP;
    
SELECT ENAME, HIREDATE, 
    TRUNC(MONTHS_BETWEEN(SYSDATE, HIREDATE)) MONTHS, SAL, 
    TO_CHAR(ROUND(MONTHS_BETWEEN(SYSDATE, HIREDATE),2)*SAL, 
                                    '$9,999,999.99') SUM_SAL
    FROM EMP;
--5. ��� ����� �̸��� �޿�(15�ڸ��� ��� ������ �� ���� ��*���� ��ġ)�� ���
SELECT ENAME, LPAD(SAL, 15, '*') FROM EMP;
--6. ��� ����� ������ �̸�,����,�Ի���,�Ի��� ������ ����ϴ� SELECT ������ ����Ͻÿ�.
SELECT ENAME, JOB, HIREDATE, TO_CHAR(HIREDATE, 'DAY') from EMP;
SELECT ENAME, JOB, HIREDATE, TO_CHAR(HIREDATE, 'DY"����"') from EMP;
--7. �̸��� ���̰� 6�� �̻��� ����� ������ �̸�,�̸��� ���ڼ�,������ ���
SELECT ENAME, LENGTH(ENAME), JOB FROM EMP WHERE LENGTH(ENAME)>= 6;
--8. ��� ����� ������ �̸�, ����, �޿�, ���ʽ�, �޿�+���ʽ��� ���
SELECT ENAME, JOB, SAL, COMM, SAL+NVL(COMM,0) "SAL_COMM" FROM EMP;
-- 9.��� ���̺��� ������� 2��° ���ں��� 3���� ���ڸ� �����Ͻÿ�. 
SELECT  ENAME, SUBSTR(ENAME, 2,3) FROM EMP;
--10. ��� ���̺��� �Ի����� 12���� ����� ���, �����, �Ի����� �˻��Ͻÿ�. 
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE HIREDATE LIKE '%/12/%';
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE SUBSTR(HIREDATE, 4, 2)='12'; 
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE INSTR(HIREDATE, '12', 4)=4;
  --  �ý��ۿ� ���� DATEFORMAT �ٸ� �� �����Ƿ� �Ʒ��� ��� ��õ
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE EXTRACT(MONTH FROM HIREDATE)=12;
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE TO_CHAR(HIREDATE, 'MM') = '12';

--11. ������ ���� ����� �˻��� �� �ִ� SQL ������ �ۼ��Ͻÿ�
--EMPNO		ENAME		�޿�
--7369		SMITH		*******800
--7499		ALLEN		******1600
--7521		WARD		******1250
--����. 
SELECT EMPNO, ENAME, LPAD(SAL, 10, '*') �޿� FROM EMP;
-- 12. ������ ���� ����� �˻��� �� �ִ� SQL ������ �ۼ��Ͻÿ�
-- EMPNO	 ENAME 	�Ի���
-- 7369	  SMITH		1980-12-17
-- ��.
SELECT EMPNO, ENAME, TO_CHAR(HIREDATE, 'YYYY-MM-DD') �Ի��� FROM EMP;
--13. ��� ���̺��� �μ� ��ȣ�� 20�� ����� ���, �̸�, ����, �޿��� ����Ͻÿ�.
    --(�޿��� �տ� $�� �����ϰ�3�ڸ����� ,�� ����Ѵ�)
SELECT EMPNO, ENAME, JOB, TO_CHAR(SAL, '$9,999,999') FROM EMP 
    WHERE DEPTNO=20;
DESC EMP;