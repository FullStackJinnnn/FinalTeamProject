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

INSERT INTO BOARD (BOARDNUM, ID, CATEGORY, TITLE, CONTENTS)
VALUES
    (2, 'a@a.c', '리뷰게시판', '아아아아아악', '맥북 프로 2022년형입니다');

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

--신고 테이블
CREATE TABLE REPORT(
	REPORTNUM INT PRIMARY KEY,
	ID VARCHAR(100),
	REPORTER VARCHAR(100),
	SUSPECT VARCHAR(100),
	REPORTCONTENTS VARCHAR(500),
	REPORTDATE DATE DEFAULT SYSDATE,
	ACCOUNTSTOP DATE DEFAULT SYSDATE
);

 
 DROP TABLE BOARD;
 DROP TABLE MEMBER;
 DROP TABLE REVIEW;
 DROP TABLE RECOMMEND;
 
 SELECT * FROM BOARD;
 SELECT * FROM MEMBER;
 DELETE FROM BOARD;
UPDATE BOARD SET CATEGORY='자유게시판' WHERE BOARDNUM=1;
 
---------------------------------------------- T E S T ----------------------------------------------
 

INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES('a@a.c','123123123','ㅌ1모','오소리',(TO_DATE('19990731','YYYY-MM-DD')),'01039757635','default.jpg','일반');

SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID 
FROM 
    (SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT 
    FROM 
        (SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT 
        FROM BOARD 
        WHERE CATEGORY = '리뷰게시판'
        ORDER BY BOARDNUM DESC) BOARD_DATA 
    LEFT JOIN 
        (SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT 
        FROM RECOMMEND 
        GROUP BY BOARDNUM) RECOMMEND_DATA 
    ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM) FINAL_DATA 
JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID;




 -- 게시글 목록 전체 출력
SELECT BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, 
TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, 
CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD 
JOIN MEMBER ON BOARD.ID = MEMBER.ID 
LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID 
WHERE CATEGORY = '카메라판매'
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
 