-- [IV] 단일행함수;내장함수
-- 함수 = 단일행함수 + 그룹함수(집계함수)
SELECT HIREDATE, TO_CHAR(HIREDATE, 'YY"년"MM"월"DD"일"') FROM EMP; -- 단일행함수
SELECT ENAME, INITCAP(ENAME) FROM EMP; -- 단일행함수
SELECT SUM(SAL) FROM EMP; -- 그룹함수(집계함수)
SELECT DEPTNO, SUM(SAL) FROM EMP GROUP BY DEPTNO; -- 그룹함수(부서번호별 합계)
SELECT SAL FROM EMP;
-- EX. 숫자관련함수, 문자관련함수, 날짜관련함수, 형변환함수, NVL(), ETC...
-- 1. 숫자함수
-- DUAL 테이블  : 오라클에서 제공하는 1행1열자리 DUMMY TABLE
SELECT * FROM DUAL;
DESC DUAL;
SELECT * FROM DUMMY;
DESC DUMMY;
SELECT ABS(-9) FROM DUAL; -- 절대값
SELECT FLOOR(34.5678) FROM DUAL; -- 소수점에서 내림
SELECT FLOOR(34.5678*100)/100 FROM DUAL; -- 소수점 두자리에서 내림
SELECT TRUNC(34.5678) FROM DUAL; -- 소수점에서 내림
SELECT TRUNC(34.5678, 2) FROM DUAL; -- 소수점 두자리에서 내림
SELECT TRUNC(34.5678, -1) FROM DUAL; -- 일의 자리에서 내림
  -- EMP 테이블에서 이름, 급여(십의 자리에서 내림)
  SELECT ENAME, SAL, TRUNC(SAL, -2) FROM EMP;
SELECT CEIL(34.5678) FROM DUAL; -- 소수점에서 올림
SELECT ROUND(34.5678) FROM DUAL; -- 소수점에서 반올림
SELECT ROUND(34.5678, 2) FROM DUAL; -- 소수점 두번째 자리에서 반올림
SELECT ROUND(34.5678, -1) FROM DUAL; -- 일의 자리에서 반올림

SELECT MOD(9,2) FROM DUAL; -- 나머지 연산자
  -- 홀수에 입사한 사원의 모든 정보
  SELECT * FROM EMP WHERE MOD(TO_CHAR(HIREDATE, 'MM'), 2)=1;

-- 2. 문자함수
SELECT UPPER('abcABC') FROM DUAL;
SELECT LOWER('abcABC') FROM DUAL;
SELECT INITCAP('WELCOME TO ORACLE') FROM DUAL;
  -- JOB이 MANAGER인 직원의 모든 필드
  SELECT * FROM EMP WHERE UPPER(JOB)='MANAGER';
  -- 이름이 SCOTT인 직원의 모든 필드
  SELECT * FROM EMP WHERE INITCAP(ENAME) = 'Scott';
-- 문자연결(CONCAT함수, ||연산자)
SELECT 'AB'||'CD'||'EF'||'GH' FROM DUAL;
SELECT CONCAT(CONCAT('AB','CD'), CONCAT('EF','GH')) FROM DUAL;
  -- XXX는 XX다 (SCOTT는 JOB이다) 출력
  SELECT CONCAT(CONCAT(ENAME, '는 '), CONCAT(JOB, '이다'))FROM EMP;
-- SUBSTR(STR, 시작위치, 문자갯수) 첫번째위치가 1, 시작위치는 음수
-- SUBSTRB(STR, 시작바이트위치, 문자바이트수)
SELECT SUBSTR('ORACLE',3,2) FROM DUAL; -- 3번째 글자부터 2글자 추출
SELECT SUBSTRB('ORACLE',3,2) FROM DUAL; -- 3번째 BYTE부터 2BYTE 추출
SELECT SUBSTR('데이터베이스',4,2) FROM DUAL; -- 4번째 글자부터 2글자 추출
SELECT SUBSTRB('데이터베이스',3,6) FROM DUAL; -- 4번째 BYTE부터 3BYTE 추출
  -- 영어는 한글자가 1BYTE. 한글은 한글자 3BYTE
-- O R A C L E
-- 1 2 3 4 5 6 (위치)
---6-5-4-3-2-1 (위치)
SELECT SUBSTR('WELCOME TO ORACLE', -1,1) FROM DUAL;
  
  SELECT SUBSTR(123, 2,1) FROM DUAL; -- 숫자도 가능
  -- 9월에 입사한 사원의 모든 필드 81/09/01
  ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD';
  SELECT * FROM EMP WHERE SUBSTR(HIREDATE, 4, 2)='09';
  -- 9일에 입사한 사원의 모든 필드 81/09/09
  SELECT * FROM EMP WHERE SUBSTR(HIREDATE, -2, 2) = '09';
SELECT LENGTH('ABCD') FROM DUAL;  -- 글자수
SELECT LENGTHB('ABCD') FROM DUAL; -- BYTE수
SELECT LENGTH('오라클') FROM DUAL; -- 글자수 : 3
SELECT LENGTHB('오라클') FROM DUAL; -- BYTE수 : 9
-- INSTR(STR,찾을글자) ; STR에서 찾을 글자의 위치(첫번째1). 없으면 0
-- INSTR(STR, 찾을글자, 시작위치) ; STR에서 시작위치부터 찾아서 찾는 글자의 위치
SELECT INSTR('ABCABC','B') FROM DUAL; -- 처음부터 찾아서 처음나오는 그 B의 위치 2
SELECT INSTR('ABCABC','B',3) FROM DUAL; -- 앞3번째부터 찾아서 처음 나오는 B의 위치 5
SELECT INSTR('ABCABC','B',-3) FROM DUAL; -- 뒤3번째부터 뒤로찾아 처음 나오는 B의 위치 5
SELECT INSTR('ABCABCABC','B',-3) FROM DUAL;
  -- 9월에 입사한 사원(INSTR이용) 81/09/01 09/09/09
  SELECT * FROM EMP WHERE INSTR(HIREDATE, '09',3)=4;
  -- 9일에 입사한 사원(INSTR이용)
  SELECT * FROM EMP WHERE INSTR(HIREDATE, '09',6) =7;
  -- 9일이 아닌 다른 날에 입사한 사원
  SELECT * FROM EMP WHERE INSTR(HIREDATE, '09', 6) = 0;
-- LPAD(STR, 자리수, 채울문자) STR을 자리수만큼 확보하고 왼쪽 빈자리에 채울문자 출력
SELECT LPAD('ORACLE', 10, '#'), RPAD('ORACLE',10,'*') FROM DUAL;  
  SELECT ENAME, SAL FROM EMP;
  SELECT ENAME, LPAD(SAL, 6, '*') FROM EMP;
  -- 사번 S****(이름은 앞 한글자만 출력하고 나머지는 *)
  -- 사번 W***  (RPAD, SUBSTR, LENGTH)
  SELECT RPAD('S',5,'*') FROM DUAL;
  SELECT EMPNO, RPAD(SUBSTR(ENAME, 1, 1), LENGTH(ENAME), '*') NAME FROM EMP;
  -- 7499 _****H 80/12/**
  -- 7521 __***D 81/01/**
  DESC EMP;
  SELECT EMPNO, LPAD(LPAD(SUBSTR(ENAME, -1, 1), LENGTH(ENAME), '*'), 10, ' ') NAME,
      RPAD(SUBSTR(HIREDATE, 1, 6), LENGTH(HIREDATE), '*') HIRE
    FROM EMP; -- 이름자리수맞춤
  -- 이름의 세번째 자리가 R인 사원의 모든 필드(LIKE, INSTR, SUBSTR)
  SELECT * FROM EMP WHERE ENAME LIKE '__R%';
  SELECT * FROM EMP WHERE INSTR(ENAME, 'R',3)=3; -- RARA
  SELECT * FROM EMP WHERE SUBSTR(ENAME, 3, 1) ='R';
SELECT TRIM('   ORACLE DB    ') MSG FROM DUAL;
SELECT LTRIM('   ORACLE DB    ') MSG FROM DUAL;
SELECT RTRIM('   ORACLE DB    ') MSG FROM DUAL;
SELECT REPLACE(ENAME, 'A','XX') FROM EMP;
SELECT REPLACE(HIREDATE, '0','XX') FROM EMP;

-- 3. 날짜관련 함수 및 예약어
SELECT SYSDATE FROM DUAL; -- SYSDATE:지금
ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD DY HH24:MI';
ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD';--원상복귀
SELECT TO_CHAR(SYSDATE, 'YY-MM-DD HH24:MI:SS') FROM DUAL;
  -- 오라클 타입 : 문자, 숫자, DATE (숫자, DATE는 연산 가능)
SELECT SYSDATE-1 어제이시간, SYSDATE 지금, SYSDATE+1 내일이시간 FROM DUAL;
-- 14일후
SELECT SYSDATE+14 FROM DUAL;
  -- EMP에서 이름, 입사일, 근무일수
  SELECT ENAME, HIREDATE, FLOOR(SYSDATE-HIREDATE) FROM EMP;
  SELECT ENAME, HIREDATE, TRUNC(SYSDATE-HIREDATE) FROM EMP;
  -- 이름, 입사일, 근무주수, 근무년수
  SELECT ENAME, HIREDATE, TRUNC((SYSDATE-HIREDATE)/7) WEEK,
          FLOOR((SYSDATE-HIREDATE)/365) YEAR 
      FROM EMP;
  -- 이름, 입사일, 근무월수 (MONTHS_BETWEEN()함수:두날짜간 개월수)
  SELECT ENAME, HIREDATE, TRUNC(MONTHS_BETWEEN(SYSDATE, HIREDATE)) MONTH FROM EMP;
  -- 이름, 입사일, 수습종료시점 (ADD_MONTHS()함수 ; 특정시점부터 몇개월후)
  SELECT ENAME, HIREDATE, ADD_MONTHS(HIREDATE, 3) FROM EMP;
  -- 이름, 입사한 이후 받은 급여와 상여 (급여는 매달 SAL을 받고 상여금은 2번 COMM을 받음)
  SELECT ENAME, TRUNC(MONTHS_BETWEEN(SYSDATE,HIREDATE))*SAL + 
                TRUNC((SYSDATE-HIREDATE)/365)*2*NVL(COMM,0) "COST" FROM EMP;
-- NEXT_DAY(특정날짜, '수') 특정날빠로부터 처음 도래하는 수요일
SELECT NEXT_DAY(SYSDATE, '토') FROM DUAL;
-- LAST_DAY(특정날짜) ; 특정날짜의 달의 말일
SELECT LAST_DAY(SYSDATE) FROM DUAL;
  -- EMP 이름, 입사일, 첫월급날(월급날 말일)
  SELECT ENAME, HIREDATE, LAST_DAY(HIREDATE) FROM EMP;
-- ROUND;날짜 반올림  TRUNC;날짜버림
SELECT ROUND(34.5678, 2) FROM DUAL;
SELECT ROUND(SYSDATE, 'YEAR') FROM DUAL; -- 결과 : 가까운 1월1일
SELECT ROUND(SYSDATE, 'MONTH') FROM DUAL; -- 결과 : 가까운 1일 (1~15일까지는 이번달1일)
SELECT ROUND(SYSDATE, 'DAY') FROM DUAL; -- 결과 : 가까운 일요일
SELECT ROUND(SYSDATE) FROM DUAL; -- 결과 : 가까운 0시
SELECT TRUNC(SYSDATE, 'YEAR') FROM DUAL; -- 결과 : 올해1월1일
SELECT TRUNC(SYSDATE, 'MONTH') FROM DUAL; -- 결과 : 이번달 1일
SELECT TRUNC(SYSDATE, 'DAY') FROM DUAL; -- 결과 : 지난 일요일
SELECT TRUNC(SYSDATE) FROM DUAL; -- 결과 : 오늘 0시
  -- EX1.이름, 입사일, 입사한달의 5일
  SELECT ENAME, HIREDATE, TRUNC(HIREDATE, 'MONTH')+4 FROM EMP;
  -- EX2.이릅, 입사일, 월급날(5일) ; 월급은 입사한지 한달이 안되어도 5일되면 무조건 지급
  SELECT ENAME, HIREDATE, ROUND(HIREDATE+11, 'MONTH')+4 FROM EMP; --15~16기준을 4~5로
  -- EX3.이릅, 입사일, 월급날(15일) ROUND기준은 15~16. 14~15로 기준변경
  SELECT ENAME, HIREDATE, ROUND(HIREDATE+1, 'MONTH')+14 FROM EMP;
  -- EX4.이릅, 입사일, 월급날(25일) 15~16기준을 24~25로 기준 변경
  SELECT ENAME, HIREDATE, ROUND(HREDATE-9, 'MONTH')+24 FROM EMP;
  
-- 4. 형변환함수 (TO_CHAR()문자로 형변환, TO_DATE() DATE형로 형변환)
--(1)TO_CHAR(DATE형 데이터, '출력형식')
SELECT TO_CHAR(SYSDATE, 'YY"년"MM"월"DD"일" DY"요일" HH24:MI:SS') FROM DUAL;
    -- YYYY 년도4자리 / YY 년도2자리 / RR 년도 2자리 / MM 월 / DD 일
    -- DAY 요일 / DY 요일요약표현
    -- AM 이나 PM / HH12 12시간 / HH24 24시간
    -- MI 분 / SS 초
  SELECT ENAME, TO_CHAR(HIREDATE, 'YY/MM/DD DY AM HH12:MI:SS') FROM EMP;
--(2)TO_CHAR(숫자데이터, '출력형식')
    -- 0 : 자릿수. 출력형식의 자릿수가 많으면 0으로 채움.  
    -- 9 : 자릿수. 출력형식이 자릿수가 많아도 숫자만큼만 출력
    -- , : 세자리마다 , 가능 . : 소숫점
    -- L : 지역통화단위 
    -- $ : 통화단위 $가 앞에 붙을 수 있음
SELECT TO_CHAR(12345678, '999,999,999.9') FROM DUAL; -- 12,345,678
SELECT TO_CHAR(12345678, '000,000,000.0') FROM DUAL; -- 012,345,678
SELECT TO_CHAR(1000, 'L9,999') FROM DUAL;
SELECT ENAME, SAL, TO_CHAR(SAL, '$99,999') FROM EMP;

--(2)TO_DATE(문자, "패턴") 
SELECT TO_DATE('20210603','YYYYMMDD') FROM EMP;
  -- 81/5/1 ~ 83/5/1 사이에 입사한 직원의 모든 필드 출력
  SELECT * FROM EMP 
    WHERE HIREDATE BETWEEN TO_DATE('810501','RRMMDD') AND 
                          TO_DATE('830501','RRMMDD');
  ALTER SESSION SET NLS_DATE_FORMAT='MM/DD/YYYY';
  ALTER SESSION SET NLS_DATE_FORMAT='RR/MM/DD';

-- TO_NUMBER(문자, 패턴) 
SELECT TO_NUMBER('3,456.78', '9,999.99')+1 FROM DUAL;
SELECT '3456.78'+1 FROM DUAL;
SELECT '3,456.78'+1 FROM DUAL;

-- 5. NVL(널일수도있는데이터, 널이면대신할값) -- 매개변수 2개는 타입 일치
-- 이름, 상사의 사번(MGR)을 출력. 직속상사가 없을 경우 'CEO'로 출력
SELECT ENAME, NVL(TO_CHAR(MGR), 'CEO') BOSS FROM EMP;

-- 6. ETC
-- (1) EXTRACT ; 년도나 월만 추출하고자 할 때
SELECT EXTRACT(YEAR FROM SYSDATE) FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'YYYY') FROM DUAL;
SELECT EXTRACT(MONTH FROM SYSDATE) FROM DUAL;
SELECT TO_CHAR(SYSDATE, 'MM') FROM DUAL;
  -- 81년도 입사한 직원
  SELECT * FROM EMP WHERE EXTRACT(YEAR FROM HIREDATE) = 1981;
  SELECT * FROM EMP WHERE TO_CHAR(HIREDATE, 'YY') = '81';
  SELECT * FROM EMP WHERE HIREDATE BETWEEN
              TO_DATE('1981-01-01','YYYY-MM-DD') AND
              TO_DATE('1981-12-31','YYYY-MM-DD');
-- (2) 레벨별 출력 : LEVEL, START WITH 조건, CONNECT BY PRIOR 조건
SELECT LEVEL,LPAD(' ', LEVEL*2)||ENAME FROM EMP
  START WITH MGR IS NULL
  CONNECT BY PRIOR EMPNO=MGR;

-- ★ <총 연습문제>
-- 1. 현재 날짜를 출력하고 TITLE에 “Current Date”로 출력하는 SELECT 문장을 기술하시오.
SELECT SYSDATE "Current Date" FROM DUAL;
-- 2. EMP 테이블에서 현재 급여에 15%가 증가된 급여를 계산하여,
-- 사원번호,이름,업무,급여,증가된 급여(New Salary),증가액(Increase)를 출력하는 SELECT 문장
SELECT EMPNO, ENAME, JOB, SAL, 
        SAL*1.15 "New Salary", SAL*0.15 "Increase" 
    FROM EMP;
--3. 이름, 입사일, 입사일로부터 6개월 후 돌아오는 월요일 구하여 출력하는 SELECT 문장을 기술하시오.
SELECT ENAME, HIREDATE, 
        NEXT_DAY(ADD_MONTHS(HIREDATE, 6), '월') "6MonthsLaterMon"
    FROM EMP;
--4. 이름, 입사일, 입사일로부터 현재까지의 개월수, 급여, 입사일부터 현재까지의 받은 급여의 총계를 출력
SELECT ENAME, HIREDATE, 
        TRUNC(MONTHS_BETWEEN(SYSDATE, HIREDATE)) MONTHS, SAL, 
        ROUND(MONTHS_BETWEEN(SYSDATE, HIREDATE)*SAL,2) SUM_SAL 
    FROM EMP;
    
SELECT ENAME, HIREDATE, 
    TRUNC(MONTHS_BETWEEN(SYSDATE, HIREDATE)) MONTHS, SAL, 
    TO_CHAR(ROUND(MONTHS_BETWEEN(SYSDATE, HIREDATE),2)*SAL, 
                                    '$9,999,999.99') SUM_SAL
    FROM EMP;
--5. 모든 사원의 이름과 급여(15자리로 출력 좌측의 빈 곳은 “*”로 대치)를 출력
SELECT ENAME, LPAD(SAL, 15, '*') FROM EMP;
--6. 모든 사원의 정보를 이름,업무,입사일,입사한 요일을 출력하는 SELECT 문장을 기술하시오.
SELECT ENAME, JOB, HIREDATE, TO_CHAR(HIREDATE, 'DAY') from EMP;
SELECT ENAME, JOB, HIREDATE, TO_CHAR(HIREDATE, 'DY"요일"') from EMP;
--7. 이름의 길이가 6자 이상인 사원의 정보를 이름,이름의 글자수,업무를 출력
SELECT ENAME, LENGTH(ENAME), JOB FROM EMP WHERE LENGTH(ENAME)>= 6;
--8. 모든 사원의 정보를 이름, 업무, 급여, 보너스, 급여+보너스를 출력
SELECT ENAME, JOB, SAL, COMM, SAL+NVL(COMM,0) "SAL_COMM" FROM EMP;
-- 9.사원 테이블의 사원명에서 2번째 문자부터 3개의 문자를 추출하시오. 
SELECT  ENAME, SUBSTR(ENAME, 2,3) FROM EMP;
--10. 사원 테이블에서 입사일이 12월인 사원의 사번, 사원명, 입사일을 검색하시오. 
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE HIREDATE LIKE '%/12/%';
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE SUBSTR(HIREDATE, 4, 2)='12'; 
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE INSTR(HIREDATE, '12', 4)=4;
  --  시스템에 따라 DATEFORMAT 다를 수 있으므로 아래의 방법 추천
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE EXTRACT(MONTH FROM HIREDATE)=12;
SELECT EMPNO, ENAME, HIREDATE FROM EMP WHERE TO_CHAR(HIREDATE, 'MM') = '12';

--11. 다음과 같은 결과를 검색할 수 있는 SQL 문장을 작성하시오
--EMPNO		ENAME		급여
--7369		SMITH		*******800
--7499		ALLEN		******1600
--7521		WARD		******1250
--……. 
SELECT EMPNO, ENAME, LPAD(SAL, 10, '*') 급여 FROM EMP;
-- 12. 다음과 같은 결과를 검색할 수 있는 SQL 문장을 작성하시오
-- EMPNO	 ENAME 	입사일
-- 7369	  SMITH		1980-12-17
-- ….
SELECT EMPNO, ENAME, TO_CHAR(HIREDATE, 'YYYY-MM-DD') 입사일 FROM EMP;
--13. 사원 테이블에서 부서 번호가 20인 사원의 사번, 이름, 직무, 급여를 출력하시오.
    --(급여는 앞에 $를 삽입하고3자리마다 ,를 출력한다)
SELECT EMPNO, ENAME, JOB, TO_CHAR(SAL, '$9,999,999') FROM EMP 
    WHERE DEPTNO=20;
DESC EMP;