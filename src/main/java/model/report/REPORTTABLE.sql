CREATE TABLE REPORT(
	REPORTNUM INT PRIMARY KEY,
	MEMBERNUM INT,
	REPORTER VARCHAR(100),
	SUSPECT VARCHAR(100),
	REPORTCONTENTS VARCHAR(500),
	REPORTDATE DATE DEFAULT SYSDATE,
	ACCOUNTSTOP DATE DEFAULT SYSDATE
);







-------------------------------QUERY TEST---------------------------------

INSERT INTO REPORT (REPORTNUM, MEMBERNUM, REPORTCONTENTS) VALUES(1,1,'가나다');
DROP TABLE REPORT;
VALUES(?)
SELECT TO_CHAR(REPORTDATE,'YYYY-MM-DD'), TO_CHAR(ACCOUNTSTOP,'YYYY-MM-DD') FROM REPORT;

SELECT TO_CHAR(BIRTHDAY,'YYYY-MM-DD') FROM MEMBER;
INSERT INTO REPORT REPORTCONTENTS
SELECT * FROM REPORT;
SELECT M.MEMBERNUM,ID,NICKNAME, BOARDNUM, TITLE, REPORTNUM, REPORTER, SUSPECT, REPORTCONTENTS, REPORTDATE, ACCOUNTSTOP FROM REPORT R JOIN MEMBER M ON M.MEMBERNUM = R.MEMBERNUM JOIN BOARD B ON M.MEMBERNUM = B.MEMBERNUM WHERE M.MEMBERNUM=1;
SELECT REPORTNUM, REPORTER FROM REPORT ORDER BY REPORTNUM DESC;