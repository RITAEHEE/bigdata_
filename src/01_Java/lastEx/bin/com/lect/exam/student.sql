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
-- 학과정보 입력
INSERT INTO MAJOR VALUES (1, '빅데이터학');
INSERT INTO MAJOR VALUES (2, '경영정보학');
INSERT INTO MAJOR VALUES (3, '컴퓨터공학');
INSERT INTO MAJOR VALUES (4, '소프트웨어');
INSERT INTO MAJOR VALUES (5, '인공지능학');

INSERT INTO STUDENT (sNO, sNAME, mNO, SCORE) VALUES
    (TO_CHAR(SYSDATE, 'YYYY')
    ||TRIM(TO_CHAR(STUDENT_SEQ.NEXTVAL,'000')),
    '정우성',(SELECT mNO FROM MAJOR WHERE mNAME='빅데이터학'), 80) ;
    
-- 학번 생성(년도4자리+시퀀스)
SELECT TO_CHAR(SYSDATE,'YYYY')||TO_CHAR(STUDENT_SEQ.CURRVAL,'000') FROM DUAL;--(X)
SELECT EXTRACT(YEAR FROM SYSDATE)||TRIM(TO_CHAR(STUDENT_SEQ.CURRVAL,'000')) 
  FROM DUAL;
  
INSERT INTO STUDENT VALUES
    (TO_CHAR(SYSDATE, 'YYYY')
    ||TRIM(TO_CHAR(STUDENT_SEQ.NEXTVAL,'000')),
    '김제적',(SELECT mNO FROM MAJOR WHERE mNAME='컴퓨터공학'), 10, 1) ;
SELECT * FROM STUDENT;
COMMIT;
-- SwingStudentMng에서 필요한 Query
-- 0. 첫화면에 전공이름들 콤보박스에 추가(mName) : DAO에서 public Vector<String> getMNamelist()
SELECT * FROM MAJOR;
-- 1. 학번검색 : DAO에서 public StudentDto sNogetStudent(String sNo)
-- 출력 sNo, sName, mName, score
SELECT SNO, SNAME, MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND SNO='2021001';
-- 2. 이름검색  : DAO에서 public ArrayList<StudentDto> sNamegetStudent(String sName)
-- 출력 sNo, sName, mName, score
SELECT SNO, SNAME, MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND SNAME='정우성';
-- 3. 전공검색  : DAO에서 public ArrayList<StudentDto> mNamegetStudent(String mName)
--- 출력 : rank, sName(sNo포함), mName(mNo포함), score  /  ex.   1 정우성(2021001) 빅데이터학(1) 90 
SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND MNAME='빅데이터학'
  ORDER BY SCORE DESC; -- INLINE 뷰에 들어갈 서브쿼리
SELECT ROWNUM RANK, A.*
  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
          FROM STUDENT S, MAJOR M
          WHERE S.MNO=M.MNO AND MNAME='빅데이터학'
          ORDER BY SCORE DESC) A; -- 자바에 들어갈 쿼리
-- 4. 학생입력 : DAO에서 public int insertStudent(StudentDto dto)
INSERT INTO STUDENT (sNO, sNAME, mNO, SCORE) VALUES
    (TO_CHAR(SYSDATE, 'YYYY')
    ||TRIM(TO_CHAR(STUDENT_SEQ.NEXTVAL,'000')),
    '홍길동',(SELECT mNO FROM MAJOR WHERE mNAME='컴퓨터공학'), 95) ;
-- 5. 학생수정 : DAO에서 public int updateStudent(StudentDto dto)
SELECT * FROM STUDENT;
UPDATE STUDENT SET sNAME='홍길동',
            mNO=(SELECT mNO FROM MAJOR WHERE mNAME='인공지능학'),
            SCORE = 66
        WHERE sNO='2021004';
-- 6. 학생출력 : DAO에서 public ArrayList<StudentDto> getStudents()
-- 출력 : rank, sName(sNo포함), mName(mNo포함), score / ex.  1 정우성(2021001) 빅데이터학(1) 90
SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
  FROM STUDENT S, MAJOR M
  WHERE S.MNO=M.MNO AND sEXPEL=0
  ORDER BY SCORE DESC; -- INLINE 뷰에 들어갈 서브쿼리
SELECT ROWNUM RANK, A.*
  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
          FROM STUDENT S, MAJOR M
          WHERE S.MNO=M.MNO AND sEXPEL=0
          ORDER BY SCORE DESC) A; -- 자바에 들어갈 쿼리
-- 7. 제적자출력  () : DAO에서 public ArrayList<StudentDto> getStudentsExpel()
-- 출력 : rank, sName(sNo포함), mName(mNo포함), score / ex. 1 김제적(2021004) 컴퓨터공학(3) 10
SELECT ROWNUM RANK, A.*
  FROM (SELECT SNAME||'('||SNO||')' SNAME, MNAME||'('||S.MNO||')' MNAME, SCORE
          FROM STUDENT S, MAJOR M
          WHERE S.MNO=M.MNO AND sEXPEL=1
          ORDER BY SCORE DESC) A; -- 자바에 들어갈 쿼리
-- 8. 제적처리 : DAO에서 public int sNoExpel(String sNo)
UPDATE STUDENT SET sEXPEL=1 WHERE sNO='2021002';
COMMIT;