-- [VII] DDL, DML, DCL
-- SQL = DDL(���̺� ����, ���̺�����, ���̺���������, ���̺� ��� ������ ����) +
--       DML(SELECT, INSERT, UPDATE, DELETE) +
--       DCL(����ڰ�������, ����ڿ��Ա��Ѻο�, ���ѹ�Ż, ����ڰ�������, Ʈ�����Ǹ��ɾ�)
-- �� �� DDL �� �� --
-- 1. ���̺� ����(CREATE TABLE)
CREATE TABLE BOOK (
  BOOKID NUMBER(4),      -- ������ȣ BOOKID �ʵ��� Ÿ���� ����4�ڸ�
  BOOKNAME VARCHAR2(20), -- �����̸� BOOKNAME�ʵ��� Ÿ���� ���� 20BYTE
  PUBLISHER VARCHAR2(20),-- ���ǻ�
  RDATE     DATE,        -- ������ RADATE �ʵ��� Ÿ���� DATE��
  PRICE     NUMBER(8),   -- ����
  PRIMARY KEY(BOOKID) ); -- ���̺� �� ��Ű(PRIMARY KEY) ����(����, NOT NULL)
SELECT * FROM BOOK;
DROP TABLE BOOK; -- ���̺� ����
CREATE TABLE BOOK (
  BOOKID NUMBER(4) PRIMARY KEY, -- PRIMARY KEY(��������)
  BOOKNAME VARCHAR2(100),
  PUBLISHER VARCHAR2(20),
  RDATE DATE,
  PRICE NUMBER(8)
);
SELECT * FROM BOOK;
-- EMP�� ������ EMP01 : EMPNO(����4), ENAME(����20), SAL(����7,2)
CREATE TABLE EMP01(
  EMPNO NUMBER(4),
  ENAME VARCHAR2(20),
  SAL   NUMBER(7,2)  -- SAL�� ��ü 7�ڸ�(�Ҽ��� ��5�ڸ�, �Ҽ�����2�ڸ�.�Ҽ������ڸ���������)
);
SELECT * FROM EMP01;
-- DEPT�� ������ DEPT01 : DEPTNO(����2, PK), DNAME(����14), LOC(����13)
CREATE TABLE DEPT01(
  DEPTNO NUMBER(2) PRIMARY KEY,
  DNAME  VARCHAR2(14),
  LOC    VARCHAR2(13)
);
SELECT * FROM DEPT01;
-- ���������� �̿��� ���̺� ����
CREATE TABLE EMP02
  AS
  SELECT * FROM EMP; -- �������� ����� EMP02 ���̺������� ��(���������� ������)
SELECT * FROM EMP02;
-- PRIMARY KEY �������� ������
DESC EMP02; 
-- EMP03 : EMP���̺����� EMPNO, ENAME, DEPTNO�� ������ ������
CREATE TABLE EMP03 AS SELECT EMPNO, ENAME, DEPTNO FROM EMP;
-- EMP04 : EMP���̺����� 10�� �μ��� ����
CREATE TABLE EMP04
  AS
  SELECT * FROM EMP WHERE DEPTNO=10;
SELECT * FROM EMP04;
-- EMP05 : EMP���̺��� ������ ����
CREATE TABLE EMP05
  AS 
  SELECT * FROM EMP WHERE 1=0;
SELECT * FROM EMP05;
DESC EMP05
-- 
SELECT * FROM EMP;
SELECT ROWNUM, EMPNO, ENAME FROM EMP; -- ROWNUM : ���̺����� ���� ������ ����(�о���� ����)
SELECT ROWID, EMPNO, ENAME FROM EMP; -- ROWID : ���� ������ �ּ�

-- 2. ���̺� ��������(ALTER TABLE)
-- ALTER TABLE ���̺��� ADD || MODIFY || DROP ~
-- (1) �ʵ� �߰�(ADD)
SELECT * FROM EMP03; -- EMPNO(��) ENAME(��) DEPTNO(��)
ALTER TABLE EMP03 ADD (JOB VARCHAR2(20), SAL NUMBER(7,2));
SELECT * FROM EMP03; -- �ʵ� �߰��� �����ʹ� NULL�� 
ALTER TABLE EMP03 ADD (COMM NUMBER(7,2));
-- (2) �ʵ� Ÿ�� ���� (MODIFY)
SELECT * FROM EMP03; -- EMPNO(��) ENAME(��10) DEPTNO(��), JOB��SAL��COMM�����Ͱ� NULL
ALTER TABLE EMP03 MODIFY (EMPNO VARCHAR2(5)); -- ���ڵ����Ͱ� ����־ ���ڷθ� ����
DESC EMP03; -- ENAME ����10BYTE
ALTER TABLE EMP03 MODIFY (ENAME VARCHAR2(20));
DESC EMP03;-- ENAME ���� 20BYTE�� �����
ALTER TABLE EMP03 MODIFY (ENAME VARCHAR2(8)); -- ����
ALTER TABLE EMP03 MODIFY (ENAME VARCHAR2(5)); -- �Ұ���
SELECT MAX(LENGTH(ENAME)) FROM EMP; -- ���� �� �̸��� BYTE �� : 6
ALTER TABLE EMP03 MODIFY (JOB VARCHAR2(3)); -- NULL�̸� �� �ٲ�
ALTER TABLE EMP03 MODIFY (JOB NUMBER(4));
ALTER TABLE EMP03 MODIFY (JOB VARCHAR2(4000));
-- (3) �ʵ� ����(DROP)
ALTER TABLE EMP03 DROP COLUMN JOB; -- NULL�� �ʵ� ����
SELECT * FROM EMP03;
ALTER TABLE EMP03 DROP COLUMN DEPTNO; -- DEPTNO�ʵ� ������ �����ͱ��� ����
-- ���������� Ư�� �ʵ带 ���� ���ϵ��� (��)
ALTER TABLE EMP03 SET UNUSED (COMM); -- COMM ���ٺҰ�
-- ���������� ���� �Ұ��ߴ� �ʵ带 ���������� ���� (����)
ALTER TABLE EMP03 DROP UNUSED COLUMNS;

-- 3. ���̺� ���� (DROP TABLE)
DROP TABLE EMP01;
SELECT * FROM EMP01;
SELECT * FROM DEPT;
DROP TABLE DEPT; -- �ٸ� ���̺����� �����ϴ� �����Ͱ� ���� ��� DROP �Ұ�

-- 4. ���̺��� ��� ���� ���� (TRUNCATE)
SELECT * FROM EMP03;
TRUNCATE TABLE EMP03; -- EMP03���� ������ ����(��ҺҰ�)
SELECT * FROM EMP03;

-- 5. ���̺��� ����(RENAME)
SELECT * FROM EMP02;
RENAME EMP02 TO EMP2; -- EMP02�� EMP2�� ���̺��� ����
SELECT * FROM EMP2;

-- 6. ������ ��ųʸ�(���ٺҰ�) VS. ������ ��ųʸ� ��(����� ���ٿ�)
-- ���� 
-- USER_XXX : �� ������ �����ϰ� �ִ� ��ü(���̺�, �ε���, ��������, ��)
    -- USER_TABLES, USER_INDEXES, USER_CONSTRAINTS, USER_VIEWS
-- ALL_XXX  : �� ������ ���� ������ ��ü
    -- ALL_TABLES, ALL_INDEXES, ALL_CONSTRAINTS, ALL_VIEWS
-- DBA_XXX : DBA�� ���ٰ���. DBMS�� ��� ��ü
    -- DBA_TABLES, DBA_INDEXES, DBA_CONSTRAINTS, DBA_VIEWS
SHOW USER;
SELECT * FROM USER_TABLES; -- �� ������ ������ ���̺� ��ü
SELECT * FROM USER_INDEXES; -- �� ������ ������ �ε��� ��ü
SELECT * FROM USER_CONSTRAINTS; -- �������� ������ �������ǵ�
SELECT * FROM USER_VIEWS;   -- �������� ������ ��
SELECT * FROM ALL_TABLES; -- �� ������ ���� ������ ���̺� ��ü
SELECT * FROM ALL_TABLES WHERE TABLE_NAME='EMP';
SELECT * FROM ALL_INDEXES;
SELECT * FROM ALL_CONSTRAINTS;
SELECT * FROM ALL_VIEWS;
SELECT * FROM DBA_TABLES; -- DBA������ ��츸 ����
SELECT * FROM DBA_INDEXES;

-- �� �� DCL(����� ��������, ����ڱ��Ѻο�, ���ѹ�Ż, ����ڰ�������)
CREATE USER joeun IDENTIFIED BY tiger; -- joeun ���� ����
-- ���� �ο�(���Ǳ���, scott.emp, scott.dept�� ���� ��� ����)
GRANT CREATE SESSION TO joeun;
GRANT ALL ON EMP TO joeun; -- �������� emp���̺��� ���� ��� ���� �ο�
GRANT SELECT ON DEPT TO joeun; -- �������� dept���̺��� ���� selete ���� �ο�
SHOW USER;
-- ���ѹ�Ż(���ѹ�Ż�� ID�� �������� ��)
REVOKE ALL ON EMP FROM joeun; -- �������� emp ���̺��� ���� ��� ���� ��Ż
REVOKE ALL ON DEPT FROM joeun;
-- ����� ���� ����(���� �� �������� �Ұ�)
DROP USER joeun CASCADE;

-- �� �� DML(SELECT, INSERT, UPDATE,  DELETE) �� ��
--1. INSERT INTO ���̺��� VALUES (��1, ��2, ...);
--   INSERT INTO ���̺��� (�ʵ��1, �ʵ��2,...) VALUES (��1, ��2, ..);
SELECT * FROM DEPT01;
INSERT INTO DEPT01 VALUES (50, 'ACCOUNTING','NEW YORK');
INSERT INTO DEPT01 (DEPTNO, DNAME, LOC) VALUES (60, 'SALES','BOSTON');
INSERT INTO DEPT01 (DNAME, LOC, DEPTNO) VALUES ('IT',NULL,70);-- ���������� NULL�߰�
INSERT INTO DEPT01 (DEPTNO, DNAME) VALUES (80, 'OPERATION'); -- ���������� NULL�߰�
SELECT * FROM DEPT01;
-- ���������� �̿��� INSERT 
  -- DEPT���̺����� 10~30�� �μ��� INSERT
  DESC DEPT01;
TRUNCATE TABLE DEPT01;
INSERT INTO DEPT01 SELECT * FROM DEPT WHERE DEPTNO<40;
SELECT * FROM DEPT01;
-- BOOK���̺����� 11(å��ȣ)��, '����������','�Ѽ�����', �������� ����, ������ 90000.
DESC BOOK;
SELECT * FROM BOOK;
INSERT INTO BOOK (BOOKID, BOOKNAME, PUBLISHER, RDATE, PRICE)
  VALUES (11, '����������','�Ѽ�����', SYSDATE, 90000);
INSERT INTO BOOK VALUES (12, '����������','�Ѽ�����', SYSDATE, 90000);

-- Ʈ������ ���ɾ�(DML���ɾ)
COMMIT; -- Ʈ������ ������ ����. �� Ʈ������� �۾��� DB�� �ݿ�
ROLLBACK; 

-- PDF 2PT. ��������
DROP TABLE SAM01;
CREATE TABLE SAM01(
    EMPNO NUMBER(4) CONSTRAINT SAM_PK PRIMARY KEY,
    ENAME VARCHAR2(10),
    JOB VARCHAR2(9),
    SAL NUMBER(7,2));
SELECT * FROM USER_CONSTRAINTS;

DROP TABLE SAM01;
CREATE TABLE SAM01(
    EMPNO NUMBER(4),
    ENAME VARCHAR2(10),
    JOB VARCHAR2(9),
    SAL NUMBER(7,2),
    PRIMARY KEY(EMPNO));
SELECT * FROM SAM01;
INSERT INTO SAM01 (EMPNO, ENAME, JOB, SAL) 
    VALUES (1000,'APPLE','POLICE',10000);
INSERT INTO SAM01 VALUES (1010,'BANANA','NURSE',15000);
INSERT INTO SAM01 VALUES (1020,'ORANGE','DOCTOR',25000);
INSERT INTO SAM01 (EMPNO, ENAME, SAL) VALUES (1030,'VERY',25000);
INSERT INTO SAM01 VALUES (1040,'CAT',NULL, 2000);
INSERT INTO SAM01 
    SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE DEPTNO=10;
SELECT * FROM SAM01;
-- 2. UPDATE ���̺��� SET �ʵ��1=��1[, �ʵ��2=��2,...] [WHERE  ����];
DROP TABLE EMP01;
CREATE TABLE EMP01 AS SELECT * FROM EMP;
-- �μ���ȣ�� 30���� ����
UPDATE EMP01 SET DEPTNO=30;
ROLLBACK; -- Ʈ������ ���
SELECT * FROM EMP01;
-- ��� ������ �޿��� 10%�� �λ��Ͻÿ�.
UPDATE EMP01 SET SAL = SAL*1.1;
SELECT * FROM EMP01;
-- 10�� �μ� ������ �Ի����� ���÷�, �μ���ȣ�� 30�� �μ��� �����Ͻÿ�.
UPDATE EMP01 SET HIREDATE=SYSDATE, DEPTNO=30 WHERE DEPTNO=10;
SELECT * FROM EMP01;
-- SAL�� 3000�̻��� ����� �޿�(SAL)�� 10%�λ�
UPDATE EMP01 SET SAL=SAL*1.1 WHERE SAL>=3000;
-- 'DALLAS'�� �ٹ��ϴ� ������ �޿��� 1000$�� �λ�(��������)
UPDATE EMP01 SET SAL = SAL+1000
  WHERE DEPTNO = (SELECT DEPTNO FROM DEPT WHERE LOC='DALLAS');
-- SCOTT �� �μ���ȣ�� 20����, JOB�� MANAGER, SAL�� COMM�� 500�� �λ�, ���� KING���� ����
UPDATE EMP01 SET DEPTNO=20,
                JOB='MANAGER',
                SAL = SAL + 500,
                COMM = NVL(COMM, 0) +500,
                MGR = (SELECT EMPNO FROM EMP WHERE ENAME='KING')
          WHERE ENAME='SCOTT'; -- ���������� �̿��� UPDATE
SELECT * FROM EMP01 WHERE ENAME='SCOTT';
-- DEPT01���� 20������ �������� 60�� ������ ���������� ���� (���������̿�)
UPDATE DEPT01 SET LOC=(SELECT LOC FROM DEPT WHERE DEPTNO=60)
  WHERE DEPTNO=20; -- DEPT���̺��� 60�� �μ��� ��� NULL�� ����
UPDATE DEPT01 SET LOC=(SELECT LOC FROM DEPT01 WHERE DEPTNO=60)
  WHERE DEPTNO=20;
SELECT * FROM DEPT01;
-- EMP01���̺��� ��� ����� �޿��� �Ի����� 'KING'�� �޿��� �Ի��Ϸ� ����
UPDATE EMP01 SET SAL=(SELECT SAL FROM EMP01 WHERE ENAME='KING'),
                HIREDATE=(SELECT HIREDATE FROM EMP01 WHERE ENAME='KING');
UPDATE EMP01 SET 
  (SAL, HIREDATE) = (SELECT SAL, HIREDATE FROM EMP01 WHERE ENAME='KING');
COMMIT;
-- 3. DELETE FROM ���̺��� WHERE ����;
SELECT * FROM EMP01;
DELETE FROM EMP01;
ROLLBACK; -- DML ��� ����
-- EMP01�׺����� 30�μ� ������ ����
DELETE FROM EMP01 WHERE DEPTNO=30;
SELECT * FROM EMP01;
-- EMP01���̺����� ������� 'FORD'�� ������ ����
DELETE FROM EMP01 WHERE ENAME='FORD';
SELECT * FROM EMP01;
-- SAM01���̺����� JOB�� �������� �ʴ� ����� ����
DELETE FROM SAM01 WHERE JOB IS NULL;
-- EMP01���̺����� �μ���(DNAME)�� SALES�� ����� ����(�������� �̿�)
DELETE FROM EMP01
  WHERE DEPTNO = (SELECT DEPTNO FROM DEPT WHERE DNAME='SALES');
-- EMP01���̺����� RESEARCH �μ� �Ҽ��� ����� ����(��������)
DELETE FROM EMP01
  WHERE DEPTNO = (SELECT DEPTNO FROM DEPT WHERE DNAME='RESEARCH');
COMMIT;

-- �������� PDT PT.1
-- 1. ���̺�����
DROP TABLE MY_DATA;
CREATE TABLE MY_DATA(
    ID NUMBER(4),
    NAME VARCHAR2(10),
    USERID VARCHAR2(30),
    SALARY NUMBER(10,2),
    PRIMARY KEY(ID));
-- 2. ������ �Է�
INSERT INTO MY_DATA (ID, NAME, USERID, SALARY)
  VALUES (1, INITCAP('SCOTT'), 'sscott',10000.00);
INSERT INTO MY_DATA VALUES (2, 'Ford', 'fford',13000.00);
INSERT INTO MY_DATA 
  VALUES (3, 'Patel', 'ppatel',TO_NUMBER('33,000.00','99,999.99'));
INSERT INTO MY_DATA 
  VALUES (4,'Report','rreport',TO_NUMBER('23,500.00','00,000.00'));
INSERT INTO MY_DATA 
  VALUES (5, 'Good', 'ggood',44450.00);
-- 3. �Է��� �ڷ� Ȯ��
SELECT * FROM MY_DATA;
SELECT ID, NAME, USERID, TO_CHAR(SALARY, '99,999.00') SALARY
    FROM MY_DATA;
-- 4. Ʈ������ �۾� �ݿ�
COMMIT;
-- 6.
UPDATE MY_DATA SET SALARY=65000.00 WHERE ID=3;
UPDATE MY_DATA SET SALARY=TO_NUMBER('65,000.00','99,999.99') 
    WHERE ID=3;
COMMIT;
-- 7. Ford ���� ����
DELETE FROM MY_DATA WHERE NAME='Ford';
DELETE FROM MY_DATA WHERE INITCAP(NAME)='Ford';
COMMIT;
-- 8. 
UPDATE MY_DATA SET SALARY = 15000
    WHERE SALARY <= 15000;
-- 9.
DROP TABLE MY_DATA;
-- cf. Report�� salary�� Ford�� salary������ ����
UPDATE MY_DATA SET SALARY=(SELECT SALARY FROM MY_DATA WHERE NAME='Ford')
  WHERE NAME='Report';
  
-- �� �� ��������
-- (1) PRIMARY KEY : �����ϰ� ���̺��� �� ���� �ĺ�
-- (2) NOT NULL    : NULL���� �������� ����
-- (3) UNIQUE  : ��� �࿡ ���� �����ؾ�. NULL���� ���
-- (4) FOREIGN KEY : ���̺��� ���� �ٸ� ���̺��� ���� ����
-- (5) CHECK(����) : �ش� ������ ����.
-- (6) DEFAULT : �⺻�� ���� (�ش� ���� ������ �Է��� ������ NULL)
CREATE TABLE DEPT1(
  DEPTNO NUMBER(2) PRIMARY KEY,
  DNAME  VARCHAR2(14) UNIQUE,
  LOC    VARCHAR2(13) NOT NULL
);
DROP TABLE DEPT1;
CREATE TABLE DEPT1(
  DEPTNO NUMBER(2),
  DNAME VARCHAR2(14),
  LOC VARCHAR2(13) NOT NULL,
  PRIMARY KEY(DEPTNO),
  UNIQUE(DNAME));
CREATE TABLE EMP1(
  EMPNO NUMBER(4) PRIMARY KEY,
  ENAME VARCHAR2(10) NOT NULL,
  JOB   VARCHAR2(9)  NOT NULL,
  MGR   NUMBER(4),
  HIREDATE DATE DEFAULT SYSDATE,
  SAL   NUMBER(7,2) CHECK (SAL>0),
  COMM  NUMBER(7,2),
  DEPTNO NUMBER(2) REFERENCES DEPT1(DEPTNO) );
DROP TABLE EMP1;
CREATE TABLE EMP1(
  EMPNO NUMBER(4),
  ENAME VARCHAR2(10) NOT NULL,
  JOB   VARCHAR2(9) NOT NULL,
  MGR   NUMBER(4),
  HIREDATE DATE DEFAULT SYSDATE,
  SAL   NUMBER(7,2),
  COMM  NUMBER(7,2),
  DEPTNO NUMBER(2),
  PRIMARY KEY(EMPNO),
  CHECK(SAL>0),
  FOREIGN KEY(DEPTNO) REFERENCES DEPT1(DEPTNO));
INSERT INTO DEPT1 SELECT * FROM DEPT;
SELECT * FROM DEPT1;
INSERT INTO DEPT1 (DEPTNO, DNAME, LOC)
  VALUES (50,'IT','MAPO');
INSERT INTO DEPT1 (DEPTNO, DNAME, LOC)
  VALUES (60,'PLANNING','MAPO');
-- EMP PK, NOT NULL(ENAME, JOB), DEFAULT, CHECK
INSERT INTO EMP1 (EMPNO, ENAME, JOB, SAL, COMM, DEPTNO)
  VALUES (1001, 'KIM','MANAGER',9000,9000,50); -- DEFAULT SYSDATE
SELECT * FROM EMP1;
INSERT INTO EMP1 (EMPNO, ENAME, JOB, DEPTNO)
  VALUES (1002, 'PARK','MANAGER',60); -- PK ��������
INSERT INTO EMP1 (EMPNO, JOB, DEPTNO) VALUES (1003, 'MANAGER',40); -- NOT NULL ��������
INSERT INTO EMP1 (EMPNO, ENAME, JOB, SAL, DEPTNO)
  VALUES (1004, 'YUN', 'MANAGER', 0, 30); -- CHECK ��������
SELECT * FROM DEPT1;
INSERT INTO EMP1 (EMPNO, ENAME, JOB, DEPTNO) 
  VALUES (1005, 'YI','MANAGER',80);--�ܷ�Ű ��������
  
-- BOOK & BOOKCATEGORY
DROP TABLE BOOK;
DROP TABLE BOOKCATEGORY;
DROP TABLE BOOKCATEGORY CASCADE CONSTRAINTS; -- �����ϴ� ���̺��� ������� DROP

CREATE TABLE BOOKCATEGORY(
    categoryCODE NUMBER(3),
    categoryName VARCHAR2(20),
    OFFICE_LOC VARCHAR2(50) NOT NULL,
    PRIMARY KEY(categoryCODE),
    UNIQUE(categoryName));
    
CREATE TABLE BOOKCATEGORY(
    categoryCODE NUMBER(3) PRIMARY KEY,
    categoryName VARCHAR2(20) UNIQUE,
    OFFICE_LOC VARCHAR2(50) NOT NULL);
    
INSERT INTO BOOKCATEGORY VALUES (100, 'ö��','3�� �ι���');
INSERT INTO BOOKCATEGORY VALUES (200, '�ι�','3�� �ι���');
INSERT INTO BOOKCATEGORY VALUES (300, '�ڿ�����','3�� ���н�');
INSERT INTO BOOKCATEGORY VALUES (400, 'IT','4�� ���н�');
SELECT * FROM BOOKCATEGORY;

CREATE TABLE BOOK(
  categoryCODE NUMBER(3) NOT NULL,
  bookNO VARCHAR2(20),
  bookNAME VARCHAR2(100),
  PUBLISHER VARCHAR2(100),
  PUBYEAR NUMBER(4) DEFAULT EXTRACT(YEAR FROM SYSDATE),
  PRIMARY KEY(BOOKNO),
  FOREIGN KEY(categoryCODE) REFERENCES BOOKCATEGORY(categoryCODE));
CREATE TABLE BOOK(
  categoryCODE NUMBER(3) REFERENCES BOOKCATEGORY(categoryCODE) NOT NULL,
  BOOKNO VARCHAR2(20) PRIMARY KEY,
  BOOKNAME VARCHAR2(100),
  PUBLISHER VARCHAR2(100),
  PUBYEAR NUMBER(4) DEFAULT TO_NUMBER(TO_CHAR(SYSDATE,'YYYY')) );
  
INSERT INTO BOOK (categoryCODE,BOOKNO,  BOOKNAME, PUBLISHER)
  VALUES (100,'100A01','ö������ ��','��������');
INSERT INTO BOOK VALUES (400,'400A01','�̰��� DB��','��������',2018);
INSERT INTO BOOK VALUES (900,'400A02','�̰��� DB��','��������',2018); --�ܷ�Ű ���� ����
INSERT INTO BOOK VALUES (NULL, '400B02','�ƹ�å','�ƹ�����',2020);
SELECT * FROM BOOK;
SELECT B.*, categoryNAME, OFFICE_LOC
  FROM BOOK B, BOOKCATEGORY C
  WHERE B.categoryCODE = C.categoryCODE;

-- MAJOR & STUDENT
DROP TABLE STUDENT;
DROP TABLE MAJOR;
CREATE TABLE MAJOR(
    MAJOR_CODE NUMBER(2) PRIMARY KEY,
    MAJOR_NAME VARCHAR2(50) UNIQUE,
    MAJOR_OFFICE_LOC VARCHAR2(255) NOT NULL);
CREATE TABLE STUDENT(
    STUDENT_CODE VARCHAR2(10) PRIMARY KEY,
    STUDENT_NAME VARCHAR2(50) NOT NULL,
    SCORE NUMBER(3) CHECK (SCORE BETWEEN 0 AND 100),
    MAJOR_CODE NUMBER(2) REFERENCES MAJOR(MAJOR_CODE) );
DROP TABLE STUDENT;
DROP TABLE MAJOR;
CREATE TABLE MAJOR(
    MAJOR_CODE NUMBER(2),
    MAJOR_NAME VARCHAR2(50),
    MAJOR_OFFICE_LOC VARCHAR2(255) NOT NULL,
    PRIMARY KEY(MAJOR_CODE),
    UNIQUE(MAJOR_NAME));
CREATE TABLE STUDENT(
    STUDENT_CODE VARCHAR2(10),
    STUDENT_NAME VARCHAR2(50) NOT NULL,
    SCORE NUMBER(3),
    MAJOR_CODE NUMBER(2),
    PRIMARY KEY(STUDENT_CODE),
    CHECK (SCORE>=0 AND SCORE<=100),
    FOREIGN KEY(MAJOR_CODE) REFERENCES MAJOR(MAJOR_CODE));
INSERT INTO MAJOR VALUES (1, '�濵����','�濵�� 305ȣ');
INSERT INTO MAJOR VALUES (2, '����Ʈ�������','������ 808ȣ');
INSERT INTO MAJOR VALUES (3, '������','������ 606ȣ');
INSERT INTO MAJOR VALUES (4, '����','���� 202ȣ');
SELECT * FROM MAJOR;
INSERT INTO STUDENT VALUES ('A01','��浿',100, 1);
INSERT INTO STUDENT VALUES ('A02','���浿',90, 2);
INSERT INTO STUDENT VALUES ('A03','ȫ�浿',95, 3);
INSERT INTO STUDENT VALUES ('B03','�ű浿',-9, 3); -- CHECK ��������
INSERT INTO STUDENT VALUES (NULL,'�ű浿',90, 3);  -- PRIMARY KEY ��������=(NOT NULL+UNIQUE)
SELECT S.*, MAJOR_NAME FROM STUDENT S, MAJOR M WHERE S.MAJOR_CODE=M.MAJOR_CODE;
