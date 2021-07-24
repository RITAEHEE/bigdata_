--DROP
DROP TABLE STUDENT;
DROP TABLE MAJOR;
DROP SEQUENCE STUDENT_SEQ;
--CREATE TABLE
CREATE TABLE MAJOR(
  mNO   NUMBER(2),
  mNAME VARCHAR2(30) NOT NULL,
  PRIMARY KEY(mNO));
CREATE TABLE STUDENT(
  sNO   VARCHAR2(7),
  sNAME VARCHAR2(50) NOT NULL,
  mNO   NUMBER(2),
  SCORE NUMBER(3) CHECK(SCORE>=0 AND SCORE<=100),
  sEXPEL NUMBER(1) DEFAULT 0 CHECK(sEXPEL=0 OR sEXPEL=1),
  PRIMARY KEY(sNO),
  FOREIGN KEY(mNO) REFERENCES MAJOR(mNO));
CREATE SEQUENCE STUDENT_SEQ MAXVALUE 999 NOCACHE NOCYCLE;
-- �а����� �Է�
INSERT INTO MAJOR VALUES (1, '��������');
INSERT INTO MAJOR VALUES (2, '�濵������');
INSERT INTO MAJOR VALUES (3, '��ǻ�Ͱ���');
INSERT INTO MAJOR VALUES (4, '����Ʈ����');
INSERT INTO MAJOR VALUES (5, '�ΰ�������');

INSERT INTO STUDENT (sNO, sNAME, mNO, SCORE) VALUES
    (TO_CHAR(SYSDATE, 'YYYY')
    ||TRIM(TO_CHAR(STUDENT_SEQ.NEXTVAL,'000')),
    '���켺',(SELECT mNO FROM MAJOR WHERE mNAME='��������'), 80) ;
    
-- �й� ����(�⵵4�ڸ�+������)
SELECT TO_CHAR(SYSDATE,'YYYY')||TO_CHAR(STUDENT_SEQ.CURRVAL,'000') FROM DUAL;--(X)
SELECT EXTRACT(YEAR FROM SYSDATE)||TRIM(TO_CHAR(STUDENT_SEQ.CURRVAL,'000')) 
  FROM DUAL;
  
INSERT INTO STUDENT VALUES
    (TO_CHAR(SYSDATE, 'YYYY')
    ||TRIM(TO_CHAR(STUDENT_SEQ.NEXTVAL,'000')),
    '������',(SELECT mNO FROM MAJOR WHERE mNAME='��ǻ�Ͱ���'), 10, 1) ;
SELECT * FROM STUDENT;
COMMIT;
-- SwingStudentMng���� �ʿ��� Query
-- 0. ùȭ�鿡 �����̸��� �޺��ڽ��� �߰�(mName) : DAO���� public Vector<String> getMNamelist()
SELECT * FROM MAJOR;
-- 1. �й��˻� : DAO���� public StudentDto sNogetStudent(String sNo)
-- ��� sNo, sName, mName, score
SELECT SNO, SNAME, MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND SNO='2021001';
-- 2. �̸��˻�  : DAO���� public ArrayList<StudentDto> sNamegetStudent(String sName)
-- ��� sNo, sName, mName, score
SELECT SNO, SNAME, MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND SNAME='���켺';
-- 3. �����˻�  : DAO���� public ArrayList<StudentDto> mNamegetStudent(String mName)
--- ��� : rank, sName(sNo����), mName(mNo����), score  /  ex.   1 ���켺(2021001) ��������(1) 90 
SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND MNAME='��������'
  ORDER BY SCORE DESC; -- INLINE �信 �� ��������
SELECT ROWNUM RANK, A.*
  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
          FROM STUDENT S, MAJOR M
          WHERE S.MNO=M.MNO AND MNAME='��������'
          ORDER BY SCORE DESC) A; -- �ڹٿ� �� ����
-- 4. �л��Է� : DAO���� public int insertStudent(StudentDto dto)
INSERT INTO STUDENT (sNO, sNAME, mNO, SCORE) VALUES
    (TO_CHAR(SYSDATE, 'YYYY')
    ||TRIM(TO_CHAR(STUDENT_SEQ.NEXTVAL,'000')),
    'ȫ�浿',(SELECT mNO FROM MAJOR WHERE mNAME='��ǻ�Ͱ���'), 95) ;
-- 5. �л����� : DAO���� public int updateStudent(StudentDto dto)
SELECT * FROM STUDENT;
UPDATE STUDENT SET sNAME='ȫ�浿',
            mNO=(SELECT mNO FROM MAJOR WHERE mNAME='�ΰ�������'),
            SCORE = 66
        WHERE sNO='2021004';
-- 6. �л���� : DAO���� public ArrayList<StudentDto> getStudents()
-- ��� : rank, sName(sNo����), mName(mNo����), score / ex.  1 ���켺(2021001) ��������(1) 90
SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND sEXPEL=0
  ORDER BY SCORE DESC; -- INLINE �信 �� ��������
SELECT ROWNUM RANK, A.*
  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
          FROM STUDENT S, MAJOR M
          WHERE S.MNO=M.MNO AND sEXPEL=0
          ORDER BY SCORE DESC) A; -- �ڹٿ� �� ����
-- 7. ���������  () : DAO���� public ArrayList<StudentDto> getStudentsExpel()
-- ��� : rank, sName(sNo����), mName(mNo����), score / ex. 1 ������(2021004) ��ǻ�Ͱ���(3) 10
SELECT ROWNUM RANK, A.*
  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
          FROM STUDENT S, MAJOR M
          WHERE S.MNO=M.MNO AND sEXPEL=1
          ORDER BY SCORE DESC) A; -- �ڹٿ� �� ����
-- 8. ����ó�� : DAO���� public int sNoExpel(String sNo)
UPDATE STUDENT SET sEXPEL=1 WHERE sNO='2021002';
COMMIT;