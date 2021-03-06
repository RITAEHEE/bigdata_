--  부서이름 받아 해당 부서 정보와 사원 정보(사번, 이름, 급여, 상사이름)를 출력
SELECT * FROM DEPT WHERE DNAME='OPERATIONS';
-- 부서명으로 사원 정보(사번, 이름, 급여, 상사이름) 출력
SELECT W.EMPNO, W.ENAME, W.SAL, M.ENAME MANAGER
  FROM EMP W, EMP M, DEPT D
  WHERE W.MGR=M.EMPNO AND W.DEPTNO=D.DEPTNO AND DNAME='SALES';
-- 부서 추가
INSERT INTO DEPT VALUES (50,'IT','MAPO');
COMMIT;
-- 해당부서번호가 있는지 검색
SELECT * FROM DEPT WHERE DEPTNO=40;
desc dept;
DELETE FROM DEPT WHERE DEPTNO>40;
COMMIT;
-- 수정
SELECT * FROM DEPT;
SELECT * FROM DEPT WHERE DEPTNO=50;
UPDATE DEPT SET DNAME='IT', LOC='MAPO' WHERE DEPTNO=50;
COMMIT;
-- 삭제
SELECT * FROM EMP WHERE DEPTNO=10;
DELETE FROM DEPT WHERE DEPTNO=10;
COMMIT;
exit;
-- pstmt
SELECT * FROM DEPT WHERE DEPTNO=50;
INSERT INTO DEPT VALUES (50, 'IT','SEOUL');
COMMIT;
-- 부서명 받아 사원 조회
SELECT * FROM DEPT WHERE DNAME='SALES';
SELECT W.EMPNO, W.ENAME, W.SAL, M.ENAME MANAGER
  FROM EMP W, EMP M, DEPT D
  WHERE DNAME='SALES' AND W.MGR=M.EMPNO AND D.DEPTNO=W.DEPTNO;

SELECT * FROM DEPT WHERE DEPTNO=50;


