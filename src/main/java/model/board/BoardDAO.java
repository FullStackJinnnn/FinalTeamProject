package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	// 게시글 목록 전체 출력. 전미지	
	private static final String SELECTALL = "SELECT * FROM (" +
		    "SELECT FILTER_DATA.*, COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT FROM (" +
	        "SELECT *" +
	        " FROM BOARD" +
	        " WHERE CATEGORY = ?" +
	    ") FILTER_DATA" +
	    " LEFT JOIN (" +
	        "SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT" +
	        " FROM RECOMMEND" +
	        " GROUP BY BOARDNUM" +
	    ") RECOMMEND_COUNT" +
	    " ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM ORDER BY FILTER_DATA.BOARDNUM" +
	") SORT_DATA" +
	" LEFT JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID";
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 글 제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블), 
	// 작성일, 좋아요 넘버(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 검색 (선택한 카테고리의 게시글만 전체 출력 / CATEGORY에 값이 없으면 오류 발생 주의)	
	
	// 게시글 검색 기능 - 제목(TITLE)으로 검색. 전미지
	private static final String SELECTALL_SEARCHTITLE = "SELECT BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, "
			+ "CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD "
			+ "JOIN MEMBER ON BOARD.ID = MEMBER.ID "
			+ "LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID "
			+ "WHERE CATEGORY = ? AND BOARD.TITLE LIKE '%'||?||'%' "
			+ "GROUP BY BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE "
			+ "ORDER BY BOARD.BOARDNUM DESC";
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 글 제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블), 
	// 작성일, 좋아요 넘버(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 + 제목 검색 (선택한 카테고리의 게시글만 전체 출력 / CATEGORY와 TITLE 값이 없으면 오류 발생 주의)		

	// 게시글 검색 기능 - 작성자(NICKNAME)로 검색. 전미지
	private static final String SELECTALL_SEARCHWRITER = "SELECT BOARD.BOARDNUM, BOARD.TITLE,  MEMBER.ID, MEMBER.NICKNAME, "
			+ "TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, "
			+ "CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD "
			+ "JOIN MEMBER ON BOARD.ID = MEMBER.ID "
			+ "LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID "
			+ "WHERE CATEGORY = ? AND MEMBER.NICKNAME LIKE '%'||?||'%' "
			+ "GROUP BY BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE "
			+ "ORDER BY BOARD.BOARDNUM DESC";
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 글 제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블), 
	// 작성일, 좋아요 넘버(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 + 작성자 검색 (선택한 카테고리 & 작성자와 동일한 조건의 게시글만 전체 출력 / CATEGORY와 NICKNAME 값이 없으면 오류 발생 주의)		
		
	// 게시글 검색 기능 - 상품명(PRODUCTNAME)으로 검색. 전미지
	private static final String SELECTALL_SEARCHPRODUCTNAME = "SELECT BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, "
			+ "CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD "
			+ "JOIN MEMBER ON BOARD.ID = MEMBER.ID "
			+ "LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID "
			+ "WHERE CATEGORY = ?  AND BOARD.PRODUCTNAME LIKE '%'||?||'%' "
			+ "GROUP BY BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE "
			+ "ORDER BY BOARD.BOARDNUM DESC";
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 작성일, 좋아요 넘버(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 + 상품명 검색 (선택한 카테고리 & 상품명과 동일한 조건의 게시글만 전체 출력 / CATEGORY와 PRODUCTNAME 값이 없으면 오류 발생 주의)			

	// 게시글 검색 기능 - 제조사(COMPANY)로 검색. 전미지
	private static final String SELECTALL_SEARCHCOMPANY= "SELECT BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, "
			+ "CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD "
			+ "JOIN MEMBER ON BOARD.ID = MEMBER.ID "
			+ "LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID "
			+ "WHERE CATEGORY = ? AND BOARD.COMPANY LIKE '%'||?||'%' "
			+ "GROUP BY BOARD.BOARDNUM, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE "
			+ "ORDER BY BOARD.BOARDNUM DESC";
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 작성일, 좋아요 넘버(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 + 제조사 검색 (선택한 카테고리 & 상품명과 동일한 조건의 게시글만 전체 출력 / CATEGORY와 COMPANY 값이 없으면 오류 발생 주의)			
		
	// 본인이 작성한 게시글 또는 타 유저가 작성한 게시글 목록 전체 출력. 전미지
	private static final String SELECTALL_MEMBER= "SELECT BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE, "
			+ "CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD "
			+ "JOIN MEMBER ON BOARD.ID = MEMBER.ID "
			+ "LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID "
			+ "WHERE CATEGORY = ? AND MEMBER.ID = ? "
			+ "GROUP BY BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARDDATE, RECOMMEND.RECOMMENDNUM, BOARD.VIEWCOUNT, BOARD.STATE "
			+ "ORDER BY BOARD.BOARDNUM DESC";
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 작성일, 좋아요 넘버(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 + 제조사 검색 (선택한 카테고리 & 상품명과 동일한 조건의 게시글만 전체 출력 / CATEGORY와 COMPANY 값이 없으면 오류 발생 주의)			
	
	// 게시글 상세보기 - 판매글, 리뷰 게시판. 전미지
	private static final String SELECTONE =  " SELECT BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARD.STATE, BOARD.VIEWCOUNT, RECOMMEND.RECOMMENDNUM, "
			+ "BOARD.CONTENTS, BOARD.IMAGE, BOARD.PRICE, BOARD.PRODUCTCATEGORY, BOARD.PRODUCTNAME, BOARD.COMPANY, "
			+ "TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD " 
			+ "JOIN MEMBER ON BOARD.ID = MEMBER.ID "
			// BOARD 테이블과 MEMBER 테이블의 회원 ID를 기준으로 INNER JOIN해 게시글의 작성자 정보를 가져온다.
			+ "LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID "
			+ "WHERE BOARD.BOARDNUM = ? "
			+ "GROUP BY BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARD.STATE, BOARD.VIEWCOUNT, RECOMMEND.RECOMMENDNUM, "
			+ "BOARD.CONTENTS, BOARD.IMAGE, BOARD.PRICE, BOARD.PRODUCTCATEGORY, BOARD.PRODUCTNAME, BOARD.COMPANY, BOARDDATE ";
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 판매상태, 조회수, 좋아요 넘버(좋아요 테이블),
	// 글내용, 이미지, 가격, 상품 종류, 상품명, 상품 제조사, 
	// 작성일, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 게시글 넘버 검색 (선택한 게시글 넘버의 게시글만 출력 / BOARDNUM 값이 없으면 오류 발생 주의)		
		
	// 게시글 상세보기_자유게시판. 전미지
	private static final String SELECTONE_FREEBOARD =  " SELECT BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARD.VIEWCOUNT, RECOMMEND.RECOMMENDNUM, BOARD.CONTENTS, BOARD.IMAGE, "
			+ "TO_CHAR(BOARD.BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "CASE WHEN RECOMMEND.RECOMMENDNUM IS NOT NULL THEN COUNT(BOARD.BOARDNUM) ELSE 0 END AS RECOMMENDCNT FROM BOARD " 
			+ "JOIN MEMBER ON BOARD.ID = MEMBER.ID "
			+ "LEFT JOIN RECOMMEND ON BOARD.ID = RECOMMEND.ID AND MEMBER.ID = RECOMMEND.ID "
			+ "WHERE BOARD.BOARDNUM = ? "
			+ "GROUP BY BOARD.BOARDNUM, BOARD.CATEGORY, BOARD.TITLE, MEMBER.ID, MEMBER.NICKNAME, "
			+ "BOARD.VIEWCOUNT, RECOMMEND.RECOMMENDNUM, BOARD.CONTENTS, BOARD.IMAGE, BOARDDATE";
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 넘버, 게시글 카테고리, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블), 
	// 조회수, 좋아요 넘버(좋아요 테이블), 글내용, 이미지, 
	// 작성일, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 게시글 넘버 검색 (선택한 게시글 넘버의 게시글만 출력 / BOARDNUM 값이 없으면 오류 발생 주의)		


	// 게시글 작성. 김도연
	private static final String INSERT = "INSERT INTO BOARD"
			+ "(BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, PRICE, IMAGE, PRODUCTNAME, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT)"
			+ "VALUES((SELECT NVL(MAX(BOARDNUM),0)+1 FROM BOARD),?,?,?,?,?,?,?,?,?,?,0)";
	// 게시글 수정. 김도연
	private static final String UPDATE = "UPDATE BOARD SET TITLE=?, CONTENTS=?, IMAGE=?, PRICE=?, PRODUCTCATEGORY=?, PRODUCTNAME=?, COMPANY=?,  STATE=? WHERE BOARDNUM=?";
	// 조회수 증가. 김도연
	private static final String UPDATE_PAGE = "UPDATE BOARD SET VIEWCOUNT = (VIEWCOUNT+1) WHERE BOARDNUM=?";
	// 게시글 삭제. 전미지
	private static final String DELETE = "DELETE FROM BOARD WHERE BOARDNUM = ?";

	
	// 게시글 목록 출력. 전미지
	// 게시글 목록을 조회하는 메서드
	public ArrayList<BoardDTO>selectAll(BoardDTO boardDTO) {
//			System.out.println("[로그] selecAll 진입 1 boardDAO : " + boardDTO);
			ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>(); // 결과를 저장할 'datas'라는 ArrayList 생성
			BoardDTO data = null; // 조회된 데이터를 담을 ' BoardDTO 클래스 타입의 변수 data'객체 선언 및 'null'로 초기화
			conn = JDBCUtil.connect(); // 데이터 베이스 연결
//			System.out.println("[로그] selectAll 진입 2 : " + boardDTO.getCategory());
			 
			if (boardDTO.getCategory().equals("판매")) { // 판매게시판 선택 시
//				System.out.println("[로그] boardDAO 판매 게시판 SELECTALL 들어옴 1" + boardDTO);
				try {
					if (boardDTO.getSearchCondition().equals("제목")) { // 제목으로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHTITLE);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getTitle());
					} else if (boardDTO.getSearchCondition().equals("작성자")) { // 작성자로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHWRITER);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getId());
					} else if (boardDTO.getSearchCondition().equals("상품명")) { // 상품명으로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHPRODUCTNAME);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getProductName());
					} else if (boardDTO.getSearchCondition().equals("제조사")) { // 제조사로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHCOMPANY);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getCompany());
					} else { // 조건 검색을 하지 않을 경우
						pstmt = conn.prepareStatement(SELECTALL); // 조건 검색을 하지 않을 경우. 기본 설정
						pstmt.setString(1, boardDTO.getCategory());
//						System.out.println("[로그] boardDAO 리뷰 게시판 SELECTALL 전체 출력 들어옴 2 " + boardDTO);
					}
					// 쿼리 실행 결과를 ResultSet에 저장하고, 데이터를 DTO에 담아 ArrayList에 담아줌
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) { 
						data = new BoardDTO();// 새로운 BoardDTO 객체 생성
						// ResultSet에서 읽은 각 열의 값을 해당 객체에 담음
						data.setBoardNum(rs.getInt("BOARDNUM"));
						data.setTitle(rs.getString("TITLE"));
						data.setId(rs.getString("ID"));
						data.setNickname(rs.getString("NICKNAME"));
						data.setBoardDate(rs.getString("BOARDDATE"));
						data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
						data.setViewCount(rs.getInt("VIEWCOUNT"));
						data.setState(rs.getString("STATE"));
						datas.add(data); // 게시글의 정보를 담은 'data'를'datas'에 담아줌
					}
					
					rs.close(); // ResultSet을 닫음으로써 자원을 해제
				} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
					e.printStackTrace();
				} finally {
					JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
				}
			} else if (boardDTO.getCategory().equals("리뷰")) { // 리뷰게시판 선택 시
//				System.out.println("[로그] boardDAO 리뷰 게시판 SELECTALL 들어옴 1" + boardDTO);
				try {
					if (boardDTO.getSearchCondition().equals("제목")) { // 제목으로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHTITLE);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getTitle());
					} else if (boardDTO.getSearchCondition().equals("작성자")) { // 작성자로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHWRITER);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getNickname());
					} else if (boardDTO.getSearchCondition().equals("상품명")) { // 상품명으로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHPRODUCTNAME);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getProductName());
					} else if (boardDTO.getSearchCondition().equals("제조사")) { // 제조사로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHCOMPANY);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getCompany());
					} else { // 조건 검색을 하지 않을 경우
						pstmt = conn.prepareStatement(SELECTALL);
						pstmt.setString(1, boardDTO.getCategory());
//						System.out.println("[로그] boardDAO 리뷰 게시판 SELECTALL 전체 출력 들어옴 2 " + boardDTO);						
					}
					
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						data = new BoardDTO();
						data.setBoardNum(rs.getInt("BOARDNUM"));
						data.setTitle(rs.getString("TITLE"));
						data.setId(rs.getString("ID"));
						data.setNickname(rs.getString("NICKNAME"));
						data.setBoardDate(rs.getString("BOARDDATE"));
						data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
						data.setViewCount(rs.getInt("VIEWCOUNT"));
						data.setPrice(rs.getInt("PRICE"));
						data.setCompany(rs.getString("COMPANY"));
						datas.add(data);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.disconnect(pstmt, conn);
				}
			} else { // 자유게시판 선택 시
				try {
					if (boardDTO.getSearchCondition().equals("제목")) { // 제목으로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHTITLE);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getTitle());
					} else if (boardDTO.getSearchCondition().equals("작성자")) { // 작성자로 검색
						pstmt = conn.prepareStatement(SELECTALL_SEARCHWRITER);
						pstmt.setString(1, boardDTO.getCategory());
						pstmt.setString(2, boardDTO.getNickname());
					} else if (boardDTO.getSearchCondition().equals("유저보드")) { // 본인 또는 타 유저로 검색
	                    pstmt = conn.prepareStatement(SELECTALL_MEMBER);
	                    pstmt.setString(1, boardDTO.getId());
					} else { // 조건 검색을 하지 않을 경우
						pstmt = conn.prepareStatement(SELECTALL);
						pstmt.setString(1, boardDTO.getCategory());
					}
//					System.out.println("[로그] boardDAO 자유 게시판 SELECTALL 전체 출력 들어옴 2 " + boardDTO);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) { // 유저에게 보여줄 게시글의 데이터들을 'data'라는 변수에 담아줌
						data = new BoardDTO();
						data.setBoardNum(rs.getInt("BOARDNUM"));
						data.setTitle(rs.getString("TITLE"));
						data.setId(rs.getString("ID"));
						data.setNickname(rs.getString("NICKNAME"));
						data.setBoardDate(rs.getString("BOARDDATE"));
						data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
						data.setViewCount(rs.getInt("VIEWCOUNT"));
						datas.add(data);
					}
					
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.disconnect(pstmt, conn);
				}
			}		
			
			return datas; // 조회된 데이터를 담은 'datas'라는 ArrayList 반환
		}

		// 게시글 상세보기. 전미지
		// 단일 게시글을 조회하는 메서드
        public BoardDTO selectOne(BoardDTO boardDTO) {
        	// 조회수 증가를 위해 update 메서드 호출
        	if (boardDTO.getUpdatePage().equals("조회수 증가")) {
    			update(boardDTO);
    		} 		
    		BoardDTO data = null; // 조회된 데이터를 담을 'data'객체 선언
    		conn = JDBCUtil.connect(); // 데이터 베이스 연결
    		if (boardDTO.getCategory().equals("자유")) { // 자유 게시판 선택 시
   	            try {
					pstmt = conn.prepareStatement(SELECTONE_FREEBOARD);
					pstmt.setInt(1, boardDTO.getBoardNum());
					ResultSet rs = pstmt.executeQuery();

					if (rs.next()) { 
						data = new BoardDTO(); // 새로운 BoardDTO 객체 생성
						// ResultSet에서 읽은 각 열의 값을 해당 객체에 담음
						data.setBoardNum(rs.getInt("BOARDNUM"));
						data.setCategory(rs.getString("CATEGORY"));
						data.setTitle(rs.getString("TITLE"));
						data.setId(rs.getString("ID"));
						data.setNickname(rs.getString("NICKNAME"));
						data.setViewCount(rs.getInt("VIEWCOUNT"));
						data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
						data.setContents(rs.getString("CONTENTS"));
						data.setImage(rs.getString("IMAGE"));
						data.setBoardDate(rs.getString("BOARDDATE"));
					} else { 
						return null; // 조회된 데이터가 없을 경우 'null'로 반환
					}			

					rs.close(); // ResultSet을 닫음으로써 자원을 해제
					 
				} catch (SQLException e) { 
					e.printStackTrace();
				} finally {
					JDBCUtil.disconnect(pstmt, conn);
				}
			} else { // 판매게시판, 리뷰게시판 선택 시
				try {
					pstmt = conn.prepareStatement(SELECTONE);
					pstmt.setInt(1, boardDTO.getBoardNum());
					ResultSet rs = pstmt.executeQuery();

					if (rs.next()) { // 유저에게 보여줄 게시글의 데이터들을 'data'라는 변수에 담아줌
						data = new BoardDTO();
						data.setBoardNum(rs.getInt("BOARDNUM"));
						data.setCategory(rs.getString("CATEGORY"));
						data.setTitle(rs.getString("TITLE"));
						data.setId(rs.getString("ID"));
						data.setNickname(rs.getString("NICKNAME"));
						data.setViewCount(rs.getInt("VIEWCOUNT"));
						data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
						data.setContents(rs.getString("CONTENTS"));
						data.setImage(rs.getString("IMAGE"));
						data.setBoardDate(rs.getString("BOARDDATE"));
						data.setState(rs.getString("STATE"));
						data.setProductcategory(rs.getString("PRODUCTCATEGORY"));
						data.setProductName(rs.getString("PRODUCTNAME"));
						data.setCompany(rs.getString("COMPANY"));
						data.setPrice(rs.getInt("PRICE"));			
					} else {
						return null;  // 반환할 데이터가 없을 경우
					}
					
					rs.close();
				} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
					e.printStackTrace();
				} finally {
					JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
				}
			}
			
			return data; // 조회된 데이터를 담은 'datas'라는 ArrayList 반환
		}

	// 게시글 작성. 김도연
	  public boolean insert(BoardDTO boardDTO) {
	      conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(INSERT);
	         pstmt.setString(1, boardDTO.getId());
	         pstmt.setString(2, boardDTO.getCategory());
	         pstmt.setString(3, boardDTO.getTitle());
	         pstmt.setString(4, boardDTO.getContents());
	         pstmt.setInt(5, boardDTO.getPrice());
	         pstmt.setString(6, boardDTO.getImage());
	         pstmt.setString(7, boardDTO.getProductName());
	         pstmt.setString(8, boardDTO.getProductcategory());
	         pstmt.setString(9, boardDTO.getCompany());
	         pstmt.setString(10, boardDTO.getState());
	         int rs=pstmt.executeUpdate();
	         if(rs<=0) {
	            return false;
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	         return false;
	      } finally {
	         JDBCUtil.disconnect(pstmt, conn);
	      }
	      return true;
	   }


	  // 조회수 증가와 게시글 수정. 김도연
	  public boolean update(BoardDTO boardDTO) {
	      conn=JDBCUtil.connect();
	     // 조회수 증가
	      if (boardDTO.getUpdatePage().equals("조회수증가")) {
	    	  try {
			         pstmt=conn.prepareStatement(UPDATE_PAGE);
			         pstmt.setInt(1, boardDTO.getBoardNum());
			         int rs=pstmt.executeUpdate();
			         if(rs<=0) {
			            return false;
			         }
			      } catch (SQLException e) {
			         e.printStackTrace();
			         return false;
			      } finally {
			         JDBCUtil.disconnect(pstmt, conn);
			      }
		}  {	// 게시글 수정
			 try {
		         pstmt=conn.prepareStatement(UPDATE);
		         pstmt.setString(1, boardDTO.getTitle());
		         pstmt.setString(2, boardDTO.getContents());
		         pstmt.setString(3, boardDTO.getImage());
		         pstmt.setInt(4, boardDTO.getPrice());
		         pstmt.setString(5, boardDTO.getProductcategory());
		         pstmt.setString(6, boardDTO.getProductName());
		         pstmt.setString(7, boardDTO.getCompany());
		         pstmt.setString(8, boardDTO.getState());
		         pstmt.setInt(9, boardDTO.getBoardNum());
		         int rs=pstmt.executeUpdate();
		         if(rs<=0) {
		            return false;
		         }
		      } catch (SQLException e) {
		         e.printStackTrace();
		         return false;
		      } finally {
		         JDBCUtil.disconnect(pstmt, conn);
		      }
		}
	      
	      return true;
	   }
	  
	// 게시글 삭제. 전미지
	public boolean delete(BoardDTO boardDTO) {
		conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(DELETE);
	         pstmt.setInt(1, boardDTO.getBoardNum());
	         int rs=pstmt.executeUpdate();
	         if(rs<=0) {
	            return false;
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	         return false;
	      } finally {
	         JDBCUtil.disconnect(pstmt, conn);
	      }
	      return true;
	}
}