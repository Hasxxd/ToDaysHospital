-- CREATE TABLE MEMBER_TEST (
--     MEM_ID        VARCHAR2(15) PRIMARY KEY,
--     MEM_LOGIN_ID  VARCHAR2(20),
--     MEM_PW        VARCHAR2(50),
--     MEM_NAME      VARCHAR2(50),
--     MEM_BIR       DATE,
--     MEM_REGNO1    CHAR(6),
--     MEM_REGNO2    CHAR(7),
--     MEM_ZIP       CHAR(7),
--     MEM_ADDR1     VARCHAR2(100),
--     MEM_ADDR2     VARCHAR2(100),
--     MEM_HOMETEL   VARCHAR2(20),
--     MEM_HP        VARCHAR2(20),
--     MEM_MAIL      VARCHAR2(100),
--     MEM_JOB       VARCHAR2(50),
--     MEM_STATUS    VARCHAR2(10)
-- );

INSERT INTO MEMBER_TEST (
  MEM_ID, MEM_LOGIN_ID, MEM_PW, MEM_NAME, MEM_BIR,
  MEM_REGNO1, MEM_REGNO2, MEM_ZIP,
  MEM_ADDR1, MEM_ADDR2, MEM_HOMETEL, MEM_HP,
  MEM_MAIL, MEM_JOB, MEM_STATUS
) VALUES (
  'M0055', 'jasdohn123', 'wpw1234!', 'John Doe', TO_DATE('1990-05-15', 'YYYY-MM-DD'),
  '900515', '1234567', '062d53',
  '서울특별시 강남구 테헤란로 45523', 'ABC빌딩 5층', '02-123-4567', '010-9876-5432',
  'john@example.com', '개발자', '정상'
);
COMMIT;

SELECT * FROM MEMBER_TEST;
