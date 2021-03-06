DROP TABLE CUSTOMER;
CREATE TABLE CUSTOMER(
  ID     NUMBER(9)    PRIMARY KEY,
  PHONE  VARCHAR2(30) NOT NULL,
  NAME   VARCHAR2(30) NOT NULL,
  POINT NUMBER(9)    DEFAULT 1000);
DROP SEQUENCE CUSTOMER_ID_SQ;
CREATE SEQUENCE CUSTOMER_ID_SQ
  MAXVALUE 999999999 NOCACHE NOCYCLE;
-- 입력
INSERT INTO CUSTOMER VALUES (CUSTOMER_ID_SQ.NEXTVAL, '010-9999-9999','홍길동',1000);
INSERT INTO CUSTOMER VALUES (CUSTOMER_ID_SQ.NEXTVAL, '010-8888-9999','박길동',2000);
INSERT INTO CUSTOMER VALUES (CUSTOMER_ID_SQ.NEXTVAL, '010-8888-8888','김길동',3000);
-- 폰번호 조회(뒷자리4자리조회와 풀폰번호 조회 모두 가능)
SELECT * FROM CUSTOMER WHERE PHONE LIKE '%9999';
SELECT * FROM CUSTOMER WHERE PHONE LIKE '%' || '9999'; -- 자바 DAO에서 쓸 쿼리
-- 출력
SELECT * FROM CUSTOMER;





