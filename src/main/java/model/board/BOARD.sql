-- 게시글 테이블
CREATE TABLE BOARD(
	BOARDNUM INT PRIMARY KEY,
	ID VARCHAR(100),
	CATEGORY VARCHAR(100) NOT NULL,
	TITLE VARCHAR(100) NOT NULL,
	CONTENTS VARCHAR(500) NOT NULL,
	BOARDDATE DATE DEFAULT SYSDATE,
	PRICE INT,
	IMAGE VARCHAR(300),
	PRODUCTNAME VARCHAR(100),
	PRODUCTCATEGORY VARCHAR(100),
	COMPANY VARCHAR(100),
	STATE VARCHAR(100),
	VIEWCOUNT INT
);

-- 회원 테이블
CREATE TABLE MEMBER(
	ID 	VARCHAR(100) PRIMARY KEY,
	PW 	VARCHAR(100) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	NICKNAME VARCHAR(100) UNIQUE NOT NULL,
	BIRTH DATE,
	PH 	VARCHAR(100) UNIQUE,
	PROFILE VARCHAR(500) NOT NULL,
	GRADE VARCHAR(100)
);

-- 댓글 테이블
CREATE TABLE REVIEW(
	REVIEWNUM INT PRIMARY KEY, 
	BOARDNUM INT,
	ID VARCHAR(100),
	REVIEWDATE DATE DEFAULT SYSDATE, 
	REVIEWCONTENTS VARCHAR(500) NOT NULL,
	FOREIGN KEY (BOARDNUM) REFERENCES BOARD(BOARDNUM) ON DELETE CASCADE
); 

-- 추천 테이블
CREATE TABLE RECOMMEND(
	RECOMMENDNUM INT PRIMARY KEY,
	BOARDNUM INT,
	ID VARCHAR(100),
	FOREIGN KEY (BOARDNUM) REFERENCES BOARD(BOARDNUM) ON DELETE CASCADE
); 
 
 DROP TABLE BOARD;
 DROP TABLE MEMBER;
 DROP TABLE REVIEW;
 DROP TABLE RECOMMEND;
 DELETE FROM BOARD;
 
 SELECT * FROM MEMBER;
 SELECT * FROM BOARD;
 SELECT * FROM REVIEW;
 SELECT * FROM RECOMMEND;
 DELETE FROM MEMBER;
UPDATE BOARD SET CATEGORY='자유게시판' WHERE BOARDNUM=1;	
 


SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID 
FROM (
    SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT 
    FROM (
        SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT
        FROM BOARD
        WHERE BOARDNUM = 11
    ) BOARD_DATA
    LEFT JOIN (
        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT
        FROM RECOMMEND
        GROUP BY BOARDNUM
    ) RECOMMEND_DATA
    ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM 
) FINAL_DATA 
JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID;



UPDATE BOARD SET VIEWCOUNT = (VIEWCOUNT+1) WHERE BOARDNUM=21;


SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID
			FROM (SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT 
			FROM (SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, 
			PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT  FROM BOARD WHERE CATEGORY = '리뷰게시판'
			ORDER BY BOARDNUM DESC) BOARD_DATA LEFT JOIN (SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT
			FROM RECOMMEND GROUP BY BOARDNUM) RECOMMEND_DATA  ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM) 
			FINAL_DATA JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID;


SELECT FINAL_DATA.*, MEMBER.NICKNAME
			FROM (SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT 
			FROM (SELECT BOARDNUM, ID, TITLE, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, 
			VIEWCOUNT FROM BOARD 
			ORDER BY BOARDNUM DESC) BOARD_DATA LEFT JOIN (SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT 
			FROM RECOMMEND GROUP BY BOARDNUM) RECOMMEND_DATA ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM) 
			FINAL_DATA JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID
			WHERE MEMBER.ID = 'a@a.c';



---------------------------------------------- T E S T ----------------------------------------------
 INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE )
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','판매게시판','팔아요','카메라가좋아요123',1000,'캐논','DSLR','판매완료');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','판매게시판','팔아요2','카메라가좋아요4224',2000,'캐논','DSLR','판매완료');
 
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE )
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','캐논DSLR판매완료1000원','카메라가좋아요',1000,'캐논','DSLR','판매완료');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','캐논DSLR판매완료~2000원~~~','카메라가좋아요',2000,'캐논','DSLR','판매완료');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','캐논미러리스!!!3000원~','카메라가좋아요',3000,'캐논','미러리스');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','니콘미러리스~4000원~~','카메라가좋아요',4000,'니콘','미러리스');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','니콘미러리스판매중~~~5000','카메라가좋아요',5000,'니콘','미러리스','판매중');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','니콘미러리스판매중6000','카메라가좋아요',6000,'니콘','미러리스','판매중');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','소니미러리스판매중7000','카메라가좋아요',7000,'소니','미러리스','판매중');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','소니미러리스판매중8000','카메라가좋아요',8000,'소니','미러리스','판매중');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','리뷰게시판','소니컴팩트판매중9000','카메라가좋아요',9000,'소니','컴팩트','판매중');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','판매게시판','소니미러리스판매중7000','카메라가좋아요',7000,'소니','미러리스','판매중');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','판매게시판','소니미러리스판매중8000','카메라가좋아요',8000,'소니','미러리스','판매중');
	INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'a@a.c','자유게시판','소니컴팩트판매중9000','카메라가좋아요',9000,'소니','컴팩트','판매중');
	
	
	
	
	SELECT FINAL_DATA.*, MEMBER.NICKNAME, RECOMMEND_ID
FROM (
    SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT, RECOMMEND_DATA.ID AS RECOMMEND_ID
    FROM (
        SELECT BOARDNUM, ID, TITLE, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, VIEWCOUNT
        FROM BOARD
        WHERE BOARDNUM = 2
    ) BOARD_DATA
    LEFT JOIN (
        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT, ID
        FROM RECOMMEND
        GROUP BY BOARDNUM, ID
    ) RECOMMEND_DATA ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM
) FINAL_DATA
JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID;


SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID
FROM (
    SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT
    FROM (
        SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, IMAGE, PRICE, PRODUCTCATEGORY, PRODUCTNAME, COMPANY, STATE, VIEWCOUNT
        FROM BOARD
        WHERE BOARDNUM = 1
    ) BOARD_DATA
    LEFT JOIN (
        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT
        FROM RECOMMEND
        GROUP BY BOARDNUM
    ) RECOMMEND_DATA ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM
) FINAL_DATA
JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID;
	
	
	
	SELECT * FROM BOARD;
	
	
	
	
	
	
 -- 게시글 목록 전체 출력
SELECT BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, 
TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, 
CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD 
JOIN MEMBER ON BOARD.ID = MEMBER.ID 
LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID 
WHERE CATEGORY = '리뷰게시판'
GROUP BY BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME,
BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE 
ORDER BY BOARD.BOARDNUM DESC;
	
-- 게시글 검색 기능 - 제목(TITLE)으로 검색
SELECT BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, 
TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, 
CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD 
JOIN MEMBER ON BOARD.ID = MEMBER.ID 
LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID 
WHERE CATEGORY = '카메라판매' AND BOARD.TITLE LIKE '카메라팝니다' 
GROUP BY BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME,
BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE 
ORDER BY BOARD.BOARDNUM DESC;
 
-- 게시글 검색 기능 - 제조사(COMPANY)로 검색
SELECT BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, 
TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, 
CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD 
JOIN MEMBER ON BOARD.ID = MEMBER.ID 
LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID 
WHERE CATEGORY = '카메라판매' AND BOARD.COMPANY LIKE '소니' 
GROUP BY BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME,
BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE 
ORDER BY BOARD.BOARDNUM DESC;
 




SELECT SORT_DATA.*, MEMBER.NICKNAME, MEMBER.ID 
FROM (
    SELECT FILTER_DATA.*, COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT 
    FROM (
        SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT
        FROM BOARD
        WHERE 1=1 
        ORDER BY BOARDNUM ASC
    ) FILTER_DATA
    LEFT JOIN (
        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT
        FROM RECOMMEND
        GROUP BY BOARDNUM
    ) RECOMMEND_COUNT
    ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM 
) SORT_DATA 
RIGHT JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID 
WHERE CATEGORY ='리뷰게시판';