-- 쇼핑몰 DB의 예
DROP TABLE CART;
DROP TABLE ORDERDETAIL;
DROP TABLE ORDERS;
DROP TABLE MEMBER;
DROP TABLE PRODUCT; -- 테이블 삭제가 안 되면 참조되는 테이블이 있는 경우
                    -- 무시하고 삭제하고자 하면 CASCADE CONSTRAINTS 추가
CREATE TABLE MEMBER(
    mID VARCHAR2(10) PRIMARY KEY,
    mNAME VARCHAR2(50),
    mADDR VARCHAR2(255),
    mTEL VARCHAR2(30),    
    mMAIL VARCHAR2(40));
CREATE TABLE PRODUCT(
    pCODE VARCHAR2(5) PRIMARY KEY,
    pNAME VARCHAR2(50),
    PRICE NUMBER(9),
    pSTOCK NUMBER(3));
DROP SEQUENCE CART_SEQ;
CREATE SEQUENCE CART_SEQ MAXVALUE 999999999999 NOCACHE;
CREATE TABLE CART(
    cNO NUMBER(12) PRIMARY KEY,    -- 순차번호
    mID VARCHAR2(10) REFERENCES MEMBER(mID),
    pCODE VARCHAR2(5) REFERENCES PRODUCT(pCODE),
    QTY NUMBER(3),
    COST NUMBER(9));
CREATE TABLE ORDERS(
    oNO NUMBER(12) PRIMARY KEY,
    mID VARCHAR2(10) REFERENCES MEMBER(mID),
    oADDR VARCHAR2(255),
    oTEL VARCHAR2(30),
    oDATE DATE);
DROP SEQUENCE ORDERDETAIL_SEQ;
CREATE SEQUENCE ORDERDETAIL_SEQ MAXVALUE 999999999999 NOCYCLE NOCACHE;
CREATE TABLE ORDERDETAIL(
    odNO  NUMBER(12) PRIMARY KEY,
    oNO   NUMBER REFERENCES ORDERS(oNO),
    pCODE VARCHAR2(5) REFERENCES PRODUCT(pCODE),
    QTY   NUMBER(3),
    COST  NUMBER(9));
SELECT * FROM MEMBER;
SELECT * FROM PRODUCT;
SELECT * FROM CART;
SELECT * FROM ORDERS;
SELECT * FROM ORDERDETAIL;