
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
)









-------------------------------QUERY TEST---------------------------------

INSERT INTO MEMBER(MEMBERNUM,ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES((SELECT NVL(MAX(MEMBERNUM),0)+1 FROM MEMBER),'IRONMAN','1234','아이언맨','아이언맨짱',(TO_DATE('19930910','YYYY-MM-DD')),'01012345678','jpg','일반');
	
INSERT INTO MEMBER(MEMBERNUM,ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)
	VALUES((SELECT NVL(MAX(MEMBERNUM),0)+1 FROM MEMBER),'1','1234','아','나',(TO_DATE('19930910','YYYY-MM-DD')),'01022222222','jpg','일반');
		
INSERT INTO MEMBER(MEMBERNUM,ID,PW,NAME,NICKNAME,BIRTH,PH,GRADE)
	VALUES((SELECT NVL(MAX(MEMBERNUM),0)+1 FROM MEMBER),'CAPTAIN','1234','캡틴','캡틴큐','1995-08-16','01055555555','신입');
	
INSERT INTO MEMBER(MEMBERNUM,ID,PW,NAME,NICKNAME,BIRTH,PH,GRADE)
	VALUES((SELECT NVL(MAX(MEMBERNUM),0)+1 FROM MEMBER),'THANOS','1234','타노스','반반맨','1995-08-17','01066666666','관리자');
	
	SELECT M.MEMBERNUM,ID,PW,NAME,NICKNAME, TO_CHAR(BIRTH,'YYYY-MM-DD'),PH,PROFILE,GRADE, BOARDNUM, TITLE FROM MEMBER M INNER JOIN BOARD B ON M.MEMBERNUM = B.MEMBERNUM WHERE ID='IRONMAN';
DROP TABLE MEMBER;
DELETE FROM BOARD;
INSERT INTO BOARD(BOARDNUM, MEMBERNUM, CATEGORY, TITLE, CONTENTS) VALUES((SELECT NVL(MAX(BOARDNUM),0) FROM BOARD)+1, 1, '자유게시판','카메라안삼~','사실 삼 ...');
SELECT * FROM ALL_TABLES;