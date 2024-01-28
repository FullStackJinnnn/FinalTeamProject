CREATE TABLE BOARD(
	BOARDNUM INT PRIMARY KEY,
	ID VARCHAR(100),
	CATEGORY VARCHAR(100) NOT NULL,
	TITLE VARCHAR(100) NOT NULL,
	CONTENTS VARCHAR(500) NOT NULL,
	BOARDDATE DATE DEFAULT SYSDATE,
	PRICE INT,
	IMAGE VARCHAR(300),
	PRODUCTCATEGORY VARCHAR(100),
	COMPANY VARCHAR(100),
	STATE VARCHAR(100),
	VIEWCOUNT INT,
	COMMENDNUM INT
);

CREATE TABLE MEMBER(
	MEMBERNUM INT PRIMARY KEY,
	ID VARCHAR(100) UNIQUE NOT NULL,
	PW VARCHAR(100) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	NICKNAME VARCHAR(100) UNIQUE NOT NULL,
	BIRTH DATE DEFAULT SYSDATE,
	PH VARCHAR(100) UNIQUE,
	PROFILE VARCHAR(500) NOT NULL,
	GRADE VARCHAR(100)
);

CREATE TABLE REVIEW(
	REVIEWNUM INT PRIMARY KEY, 
	BOARDNUM INT,
	ID VARCHAR(100),
	REVIEWDATE DATE DEFAULT SYSDATE, 
	REVIEWCONTENTS VARCHAR(500) NOT NULL,
	FOREIGN KEY (BOARDNUM) REFERENCES  BOARD(BOARDNUM) ON DELETE CASCADE
);


CREATE TABLE RECOMMEND(
	RECOMMENDNUM INT PRIMARY KEY,
	BOARDNUM INT,
	ID VARCHAR(100),
	FOREIGN KEY (BOARDNUM) REFERENCES BOARD(BOARDNUM) ON DELETE CASCADE
);
SELECT MEMBERNUM,M.ID,PW,NAME,NICKNAME, TO_CHAR(BIRTH,'YYYY-MM-DD') AS BIRTH_DATE,PH,PROFILE,GRADE, BOARDNUM
FROM MEMBER M LEFT JOIN BOARD B ON M.ID = B.ID WHERE M.ID='IRONMAN@gmail.com';

INSERT INTO BOARD(BOARDNUM,MEMBERNUM,CATEGORY,TITLE,CONTENTS)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'1','a','제목','내용');

 DROP TABLE REVIEW;
 DROP TABLE RECOMMEND;
-- DROP TABLE MEMBER;
 DROP TABLE BOARD;

SELECT * FROM  BOARD;
SELECT * FROM  COMMEND;
SELECT * FROM  MEMBER;
SELECT * FROM  REVIEW;


SELECT * FROM REVIEW where BOARDNUM = 1;

SELECT * FROM BOARD 
JOIN MEMBER ON BOARD.MEMBERNUM = MEMBER.MEMBERNUM 
JOIN COMMEND ON BOARD.BOARDNUM = COMMEND.BOARDNUM
WHERE BOARD.BOARDNUM = 1
ORDER BY BOARD.BOARDNUM DESC
;

INSERT INTO BOARD(BOARDNUM,ID,CATEGORY,TITLE,CONTENTS)
	VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),'IRONMAN@gmail.com','판매','오소리티모','귀엽다');


SELECT BOARD.BOARDNUM, MEMBER.MEMBERNUM, BOARD.CATEGORY, BOARD.TITLE, BOARD.BOARDDATE , BOARD.STATE, BOARD.VIEWCOUNT,  COMMEND.COMMENDNUM, MEMBER.NICKNAME, MEMBER.ID, COMMEND.COMMENDCOUNT FROM BOARD 
JOIN MEMBER ON BOARD.MEMBERNUM = MEMBER.MEMBERNUM JOIN COMMEND ON BOARD.BOARDNUM = COMMEND.BOARDNUM WHERE CATEGORY = '판매' ORDER BY BOARD.BOARDNUM DESC;


SELECT BOARD.BOARDNUM, MEMBER.MEMBERNUM, BOARD.CATEGORY, BOARD.TITLE, BOARD.BOARDDATE, BOARD.STATE, BOARD.VIEWCOUNT,
       COMMEND.COMMENDNUM, MEMBER.NICKNAME, MEMBER.ID, COUNT(COMMEND.COMMENDNUM) AS COMMENDCOUNT
FROM BOARD
JOIN MEMBER ON BOARD.MEMBERNUM = MEMBER.MEMBERNUM
JOIN COMMEND ON BOARD.BOARDNUM = COMMEND.BOARDNUM
WHERE CATEGORY = '판매'  
GROUP BY BOARD.BOARDNUM, MEMBER.MEMBERNUM, BOARD.CATEGORY, BOARD.TITLE, BOARD.BOARDDATE, BOARD.STATE, BOARD.VIEWCOUNT,
         COMMEND.COMMENDNUM, MEMBER.NICKNAME, MEMBER.ID
ORDER BY BOARD.BOARDNUM DESC;


AND MEMBER.NICKNAME LIKE '%DD%'




SELECT BOARD.BOARDNUM, MEMBER.MEMBERNUM, BOARD.CATEGORY, BOARD.TITLE, BOARD.BOARDDATE,
		BOARD.STATE, BOARD.VIEWCOUNT, COMMEND.COMMENDNUM, MEMBER.NICKNAME, MEMBER.ID, COUNT(BOARD.BOARDNUM) AS CNT FROM BOARD
			JOIN MEMBER ON BOARD.MEMBERNUM = MEMBER.MEMBERNUM
			JOIN COMMEND ON BOARD.BOARDNUM = COMMEND.BOARDNUM WHERE CATEGORY = 'ddd'
			GROUP BY BOARD.BOARDNUM, MEMBER.MEMBERNUM, BOARD.CATEGORY, BOARD.TITLE, BOARD.BOARDDATE,
			BOARD.STATE, BOARD.VIEWCOUNT, COMMEND.COMMENDNUM, MEMBER.NICKNAME, MEMBER.ID
			ORDER BY BOARD.BOARDNUM DESC;
			
			
			SELECT BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, 
			TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, BOARD.STATE, BOARD.VIEWCOUNT, RECOMMEND.RECOMMENDNUM, MEMBER.NICKNAME, MEMBER.ID, 
			COUNT(BOARD.BOARDNUM) AS CNT FROM BOARD
			JOIN MEMBER ON BOARD.ID = MEMBER.ID
			LEFT JOIN RECOMMEND ON BOARD.BOARDNUM = RECOMMEND.BOARDNUM WHERE MEMBER.NICKNAME = '아아아'
			GROUP BY BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, BOARD.BOARDDATE,
			BOARD.STATE, BOARD.VIEWCOUNT, RECOMMEND.RECOMMENDNUM, MEMBER.NICKNAME, MEMBER.ID 
			ORDER BY BOARD.BOARDNUM DESC;