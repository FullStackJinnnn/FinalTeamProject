-- 게시글 테이블
CREATE TABLE BOARD(
	BOARDNUM INT PRIMARY KEY,
	ID VARCHAR(100),
	CATEGORY VARCHAR(100) NOT NULL,
	TITLE VARCHAR(100) NOT NULL,
	CONTENTS VARCHAR() NOT NULL,
	BOARDDATE DATE DEFAULT SYSDATE,
	PRICE INT,
	IMAGE VARCHAR(300),
	PRODUCTNAME VARCHAR(100),
	PRODUCTCATEGORY VARCHAR(100),
	COMPANY VARCHAR(100),
	STATE VARCHAR(100),
	VIEWCOUNT INT
)

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
	REPORTER VARCHAR(100),S
	SUSPECT VARCHAR(100),
	REPORTCONTENTS VARCHAR(500),
	REPORTDATE DATE DEFAULT SYSDATE,
	ACCOUNTSTOP DATE DEFAULT SYSDATE
);

 
 DROP TABLE BOARD;
 DROP TABLE MEMBER 
 DROP TABLE REVIEW;
 DROP TABLE RECOMMEND;
 SELECT * FROM RECOMMEND;
 DELETE FROM BOARD WHERE BOARDNUM = 119
 SELECT * FROM BOARD WHERE CATEGORY = '판매게시판'
 SELECT * FROM MEMBER;
 DELETE FROM BOARD WHERE BOARDNUM = 5;
 DELETE FROM MEMBER WHERE NAME = '정석진';
UPDATE BOARD SET CATEGORY='자유게시판' WHERE BOARDNUM=1;
 UPDATE BOARD SET VIEWCOUNT=768 WHERE BOARDNUM =1;
 UPDATE RECOMMEND 
---------------------------------------------- T E S T ----------------------------------------------
 

INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES('rornfl@naver.com','123123123','정석진','티모는오소리',(TO_DATE('19990731','YYYY-MM-DD')),'010-6622-1689','default.jpg','일반');

SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID 
FROM 
    (SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT 
    FROM 
        (SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT 
        FROM BOARD 
        WHERE CATEGORY = '판매게시판'
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
 


SELECT 
    SORT_DATA.*, 
    MEMBER.NICKNAME 
FROM (
    SELECT 
        FILTER_DATA.*, 
        COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT 
    FROM (
        SELECT 
            ROWNUM, 
            ROWNUM_DATA.* 
        FROM (
            SELECT 
                BOARDNUM, ID, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, 
                VIEWCOUNT, STATE, CATEGORY, PRICE 
            FROM BOARD WHERE ID = 'rornfl@naver.com'
            ORDER BY BOARDNUM ASC
        ) ROWNUM_DATA
    ) FILTER_DATA 
    LEFT JOIN (
        SELECT 
            BOARDNUM, 
            COUNT(BOARDNUM) AS RECOMMENDCNT 
        FROM RECOMMEND 
        GROUP BY BOARDNUM
    ) RECOMMEND_COUNT ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM 
    ORDER BY ROWNUM DESC
) SORT_DATA 
JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID ;


SELECT SORT_DATA.*, MEMBER.NICKNAME FROM (    SELECT         FILTER_DATA.*,         COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT     FROM (SELECT ROWNUM, ROWNUM_DATA.*         FROM (            SELECT                 BOARDNUM, ID, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, VIEWCOUNT, STATE, CATEGORY, PRICE             FROM BOARD WHERE ID='a@a.c'             ORDER BY BOARDNUM ASC        ) ROWNUM_DATA) FILTER_DATA     LEFT JOIN (        SELECT             BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT         FROM RECOMMEND         GROUP BY BOARDNUM    ) RECOMMEND_COUNT ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM     ORDER BY ROWNUM DESC) SORT_DATA JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID  ORDER BY SORT_DATA.BOARDNUM DESC



---------REALISTIC DUMMY DATA----------

 INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,VIEWCOUNT,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'rornfl@naver.com','판매게시판','캐논 RF24-105mm F2.8 L IS USM Z 리뷰','사에서도 감히 기획 못했던 상상속의 그 렌즈.
일단 이 스펙으로 설계하려면 무게와 크기가 부담스러워지고
상승되는 가격에 따른 수요도 감안해야 하는 제품화가 결코 쉽지 않은 렌즈다.
24-105mm는 만능 표준 줌 렌즈를 대변하는 초점거리로서
기동성을 극대화한 엔트리급 컴팩트 렌즈를 병행 운영하는 것이 일반적이다. ',4590000,'캐논','DSLR',892,'판매중');
 INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,VIEWCOUNT,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'rornfl2@naver.com','판매게시판','캐논 RF 15-30 / 600 판매합니다','Canon EOS 10D(이하 10D)는 2003년 4월 한국에 정식 발매되었다.
그 이후로 캐논의 보급형 DSLR EOS 300D가 발매되었고, 니콘도 이에 맞서 D70을 내놓으며 치열한 보급형 DSLR 전쟁이 벌어졌다.
하지만 니콘 D70의 맞상대는 사실상 10D가 맞아 보인다.
플라스틱 바디를 쓴 300D에 비해 10D는 마그네슘 바디로 마감을 했고, AF성능도 지금 살펴봐도 전혀 손색이 없다.',200000,'캐논','DSLR',76,'판매중');
 INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,VIEWCOUNT,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'rornfl3@naver.com','판매게시판','[첫인상] 니콘 | Nikon Z fc','카메라가좋아요123',1380000,'니콘','미러리스',701);
 INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,VIEWCOUNT,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'rornfl4@naver.com','판매게시판','소니|SonyE16mmF2.8 판매합니다.','카메라가좋아요123',237600,'소니','DSLR',352,'판매중');
 INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS,PRICE,COMPANY,PRODUCTCATEGORY,VIEWCOUNT,STATE)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'rornfl5@naver.com','판매게시판','캐논 50mm 1.2 정품 팔아요','카메라가좋아요123',120000,'캐논','DSLR',242,'판매중');

	
	
	
	
		INSERT INTO RECOMMEND(RECOMMENDNUM, BOARDNUM, ID) VALUES((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND),1,'a@a.c');
	INSERT INTO RECOMMEND(RECOMMENDNUM, BOARDNUM, ID) VALUES((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND),2,'a@a.c');
	INSERT INTO RECOMMEND(RECOMMENDNUM, BOARDNUM, ID) VALUES((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND),3,'a@a.c');
	INSERT INTO RECOMMEND(RECOMMENDNUM, BOARDNUM, ID) VALUES((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND),4,'a@a.c');
	INSERT INTO RECOMMEND(RECOMMENDNUM, BOARDNUM, ID) VALUES((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND),5,'a@a.c');
x
	INSERT INTO RECOMMEND(RECOMMENDNUM, BOARDNUM, ID) VALUES((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND),2,'b@b.c');
	INSERT INTO RECOMMEND(RECOMMENDNUM, BOARDNUM, ID) VALUES((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND),3,'b@b.c');
	
	
	INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES('rornfl2@naver.com','123123123','정재훈','별사탕',(TO_DATE('19990131','YYYY-MM-DD')),'010-2322-2389','default.jpg','일반');
	INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES('rornfl3@naver.com','123123123','류인환','rih4262',(TO_DATE('19980131','YYYY-MM-DD')),'010-2329-2132','default.jpg','일반');
	INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES('rornfl4@naver.com','123123123','정석주','하우림',(TO_DATE('19930123','YYYY-MM-DD')),'010-6372-7295','default.jpg','일반');
	INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES('rornfl5@naver.com','123123123','이승민','월레스와그로밋',(TO_DATE('19880111','YYYY-MM-DD')),'010-4579-6712','default.jpg','일반');
	INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES('rornfl6@naver.com','123123123','이소천','은솔아빠',(TO_DATE('19800527','YYYY-MM-DD')),'010-7138-4382','default.jpg','일반');
