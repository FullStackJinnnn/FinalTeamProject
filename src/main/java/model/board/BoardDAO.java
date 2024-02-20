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

	// 게시글 목록 전체 출력. 전미지 // 최종 쿼리 : 게시글들의 정보를 받아오며 작성자의 닉네임과 아이디를 받아옴
	private static final String SELECTALL = "SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID " 
			+ "FROM ( " // 4번째 서브쿼리 : 일련번호를 부여한 게시글의 정보를 받아오는데 좋아요 수의 값이 'null'일 경우 0으로 초기화
			+ "    SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT " 
			+ "    FROM ( " // 2번째 서브쿼리 : 받아온 게시글들의 일련번호를 새로 부여																											
			+ "        SELECT ROWNUM, ROWNUM_DATA.* " 
			+ "        FROM ( " // 1번째 서브쿼리 : 요청받은 게시판 종류과 동일한 게시판의 게시글 정보들을 받아옴
			+ "            SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
			+ "                PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT " 
			+ "            FROM BOARD "
			+ "            WHERE CATEGORY = ?" // 특정 카테고리의 게시글을 가져옴
			+ "            ORDER BY BOARDNUM ASC " // 게시글 번호 순으로 오름차순 정렬
			+ "        ) ROWNUM_DATA "
			+ "    ) BOARD_DATA " 
			+ "    LEFT JOIN ( " // 3번째 서브쿼리 : 게시글의 좋아요 수를 합산
			+ "        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT " 
			+ "        FROM RECOMMEND "
			+ "        GROUP BY BOARDNUM " 
			+ "    ) RECOMMEND_DATA ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM "
			+ "    ORDER BY BOARD_DATA.BOARDNUM DESC " // 게시글 번호 역순으로 정렬
			+ ") FINAL_DATA " 
			+ "JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID"; // 회원 테이블과 게시글 테이블인 FINAL_DATA를 JOIN
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 번호, 글 제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 작성일, 좋아요 번호(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 검색 (선택한 카테고리의 게시글만 전체 출력 / CATEGORY에 값이 없으면 오류 발생 주의)

	// 본인이 작성한 게시글 또는 타 유저가 작성한 게시글 목록 전체 출력. 전미지 // 최종 쿼리 : 게시글들의 정보를 받아오며 작성자의 닉네임을 받아옴
	private static final String SELECTALL_MEMBER = "SELECT FINAL_DATA.*, MEMBER.NICKNAME " 
			+ "FROM ( "
			+ "    SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT " 
			+ "    FROM ( "
			+ "        SELECT ROWNUM, ROWNUM_DATA.* FROM ( "
			+ "            SELECT BOARDNUM, ID, TITLE, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, VIEWCOUNT, CATEGORY "
			+ "            FROM BOARD WHERE ID = ?" // 특정 회원의 게시글을 가져옴
			+ "            ORDER BY BOARDNUM ASC " // 게시글 번호 순으로 오름차순 정렬
			+ "        ) ROWNUM_DATA " 
			+ "    ) BOARD_DATA " 
			+ "    LEFT JOIN ( "
			+ "        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT " 
			+ "        FROM RECOMMEND "
			+ "        GROUP BY BOARDNUM " 
			+ "    ) RECOMMEND_DATA ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM "
			+ "    ORDER BY BOARD_DATA.BOARDNUM DESC " // 게시글 번호 역순으로 정렬
			+ ") FINAL_DATA " 
			+ "JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID "; // 회원 테이블과 게시글 테이블인 FINAL_DATA를 JOIN
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 번호, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 작성일, 좋아요 번호(좋아요 테이블), 조회수, 판매상태, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 카테고리 + 제조사 검색 (선택한 카테고리 & 상품명과 동일한 조건의 게시글만 전체 출력 / CATEGORY와 COMPANY 값이 없으면 오류 발생 주의)

	// 게시글 상세보기 - 판매글, 리뷰 게시판. 전미지  // 최종 쿼리 : 게시글의 정보를 받아오며 작성자의 닉네임과 아이디를 받아옴 
	private static final String SELECTONE = "SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID "
			+ "FROM ( " // 3번째 서브쿼리 : 게시글의 정보를 받아오는데 좋아요 수의 값이 'null'일 경우 0으로 초기화
			+ "    SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT "
			+ "    FROM ( " // 1번째 서브쿼리 : 요청받은 게시글 번호 동일한 게시글의 정보들을 받아옴
			+ "        SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE,"
			+ "			IMAGE, PRICE, PRODUCTCATEGORY, PRODUCTNAME, COMPANY, STATE, VIEWCOUNT "
			+ "        FROM BOARD "
			+ "        WHERE BOARDNUM = ?" // 특정 게시글 번호의 게시글을 가져옴
			+ "    ) BOARD_DATA "
			+ "    LEFT JOIN ( " // 2번째 서브쿼리 : 게시글의 좋아요 수를 합산
			+ "        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT "
			+ "        FROM RECOMMEND "
			+ "        GROUP BY BOARDNUM "
			+ "    ) RECOMMEND_DATA "
			+ "    ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM "
			+ ") FINAL_DATA "
			+ "JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID"; // 회원 테이블과 게시글 테이블인 FINAL_DATA를 JOIN
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 게시판 테이블과 회원 테이블의 회원 ID를 기준으로 INNER JOIN해 게시글의 작성자 정보를 가져옴
	// 사용한 컬럼(보여줄 목록) : 게시글 번호, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 판매상태, 조회수, 좋아요 번호(좋아요 테이블),
	// 글내용, 이미지, 가격, 상품 종류, 상품명, 상품 제조사,
	// 작성일, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 게시글 번호 검색 (선택한 게시글 번호의 게시글만 출력 / BOARDNUM 값이 없으면 오류 발생 주의)	

	// 게시글 상세보기_자유게시판. 전미지
	private static final String SELECTONE_FREEBOARD = "SELECT FINAL_DATA.*, MEMBER.NICKNAME, MEMBER.ID " 
			+ "FROM ( "
			+ "    SELECT BOARD_DATA.*, COALESCE(RECOMMEND_DATA.RECOMMENDCNT, 0) AS RECOMMENDCNT " 
			+ "    FROM ( "
			+ "        SELECT BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, IMAGE, VIEWCOUNT "
			+ "        FROM BOARD " 
			+ "        WHERE BOARDNUM = ?" 
			+ "    ) BOARD_DATA " 
			+ "    LEFT JOIN ( "
			+ "        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT " 
			+ "        FROM RECOMMEND "
			+ "        GROUP BY BOARDNUM " 
			+ "    ) RECOMMEND_DATA "
			+ "    ON BOARD_DATA.BOARDNUM = RECOMMEND_DATA.BOARDNUM " 
			+ ") FINAL_DATA "
			+ "JOIN MEMBER ON MEMBER.ID = FINAL_DATA.ID"; // 회원 테이블과 게시글 테이블인 FINAL_DATA를 JOIN
	// 조인한 게시판 테이블 : 회원 테이블, 좋아요 테이블
	// 사용한 컬럼(보여줄 목록) : 게시글 번호, 게시글 카테고리, 글제목, 작성자 아이디(회원 테이블), 작성자 닉네임(회원 테이블),
	// 조회수, 좋아요 번호(좋아요 테이블), 글내용, 이미지,
	// 작성일, 카운트 함수 사용(좋아요수-좋아요 테이블 / 좋아요 값이 있을 때만 보여짐)
	// 검색 조건 : 게시글 번호 검색 (선택한 게시글 번호의 게시글만 출력 / BOARDNUM 값이 없으면 오류 발생 주의)

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
	public ArrayList<BoardDTO> selectAll(BoardDTO boardDTO) {
//	System.out.println("[로그] boardDAO.java selectAll 진입 1 boardDAO : " + boardDTO);

		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>(); // 결과를 저장할 'datas'라는 ArrayList를 생성
		BoardDTO data = null; //각 게시판 데이터를 담을 'BoardDTO 클래스 타입의 변수 data'객체 선언 및 'null'로 초기화
		conn = JDBCUtil.connect(); // 데이터베이스 연결을 위해 JDBCUtil 클래스의 connect 메서드 호출
//		System.out.println("[로그] boardDAO.java selectAll 진입 2 : " + boardDTO.getCategory());
		if (boardDTO.getCategory().equals("판매게시판")) { // 판매게시판 선택 시
//		System.out.println("[로그] boardDAO.java 판매 게시판 SELECTALL 들어옴 1" + boardDTO);
			try {
				pstmt = conn.prepareStatement(SELECTALL); // 게시글 전체 출력 실행
				// PreparedStatement 객체인 'pstmt'를 사용하여 SQL 쿼리의 조건에 게시판 카테고리를 설정
				// setString() 메서드를 사용하여 SQL 쿼리에서 첫 번째(인덱스 1) 물음표에 boardDTO에서 가져온 게시판 카테고리를 설정
				pstmt.setString(1, boardDTO.getCategory());
				// executeQuery() 메서드를 호출하여 SQL 쿼리를 실행하고, 그 결과를 ResultSet 객체인 'rs'에 저장
				// 실행된 SQL 쿼리는 PreparedStatement 객체에 설정된 파라미터를 포함 함
				// 결과로 데이터베이스에서 검색된 결과 집합이 생성되고, 이 결과 집합은 ResultSet 객체에 저장
				ResultSet rs = pstmt.executeQuery(); // 쿼리 실행 결과를 ResultSet에 저장하고, 데이터를 DTO에 담아 ArrayList에 담아줌
//				System.out.println("[로그] boardDAO.java 판매 게시판 SELECTALL 전체 출력 들어옴 2 " + boardDTO);	
				while (rs.next()) { // ResultSet에서 데이터가 존재하는 동안 반복문 실행
					data = new BoardDTO(); // 새로운 BoardDTO 객체를 생성하고 ResultSet에서 읽은 각 데이터의 값을 'data'라는 객에 담음
					data.setBoardNum(rs.getInt("BOARDNUM"));
					data.setRownum(rs.getInt("ROWNUM"));
					data.setTitle(rs.getString("TITLE"));
					data.setId(rs.getString("ID"));
					data.setNickname(rs.getString("NICKNAME"));
					data.setBoardDate(rs.getString("BOARDDATE"));
					data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
					data.setPrice(rs.getInt("PRICE"));
					data.setViewCount(rs.getInt("VIEWCOUNT"));
					data.setState(rs.getString("STATE"));
					data.setCategory(rs.getString("CATEGORY"));
					datas.add(data); // 저장한 데이터를 'datas'라는 ArrayList에 추가
				}

				rs.close(); // ResultSet을 닫음으로써 자원을 해제
			} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
			}
		} else if (boardDTO.getCategory().equals("리뷰게시판")) { // 리뷰게시판 선택 시
//		System.out.println("[로그] boardDAO.java 리뷰 게시판 SELECTALL 들어옴 1" + boardDTO);
			try {
				pstmt = conn.prepareStatement(SELECTALL); // 게시글 전체 출력 실행
				pstmt.setString(1, boardDTO.getCategory());
//				System.out.println("[로그] boardDAO.java 리뷰 게시판 SELECTALL 전체 출력 들어옴 2 " + boardDTO);						
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) { // ResultSet에서 데이터가 존재하는 동안 반복문 실행
					data = new BoardDTO(); // 새로운 BoardDTO 객체를 생성하고 ResultSet에서 읽은 각 데이터의 값을 'data'라는 객에 담음
					data.setBoardNum(rs.getInt("BOARDNUM"));
					data.setRownum(rs.getInt("ROWNUM"));
					data.setTitle(rs.getString("TITLE"));
					data.setId(rs.getString("ID"));
					data.setNickname(rs.getString("NICKNAME"));
					data.setBoardDate(rs.getString("BOARDDATE"));
					data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
					data.setPrice(rs.getInt("PRICE"));
					data.setViewCount(rs.getInt("VIEWCOUNT"));
					data.setCategory(rs.getString("CATEGORY"));
					datas.add(data); // 저장한 데이터를 'datas'라는 ArrayList에 추가
				}

				rs.close(); // ResultSet을 닫음으로써 자원을 해제
			} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
			}
		} else { // 자유게시판 선택 시
//		System.out.println("[로그] boardDAO.java 자유 게시판 SELECTALL 들어옴 1" + boardDTO);
			try {
				if (boardDTO.getSearchCondition().equals("유저보드")) { // 본인 또는 타 유저로 검색 선택 시
					pstmt = conn.prepareStatement(SELECTALL_MEMBER); // 본인 또는 타 유저로 검색 실행
					pstmt.setString(1, boardDTO.getId());
				} else { // 조건 검색을 하지 않을 경우
					pstmt = conn.prepareStatement(SELECTALL); // 조건 검색을 하지 않을 경우. 기본 설정
					pstmt.setString(1, boardDTO.getCategory());
				}
//				System.out.println("[로그] boardDAO.java 자유 게시판 SELECTALL 들어옴 2" + boardDTO);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) { // ResultSet에서 데이터가 존재하는 동안 반복문 실행
					data = new BoardDTO(); // 새로운 BoardDTO 객체를 생성하고 유저에게 보여줄 게시글의 데이터들을 'data'라는 객체에 담아줌
					data.setBoardNum(rs.getInt("BOARDNUM"));
					data.setRownum(rs.getInt("ROWNUM"));
					data.setTitle(rs.getString("TITLE"));
					data.setId(rs.getString("ID"));
					data.setNickname(rs.getString("NICKNAME"));
					data.setBoardDate(rs.getString("BOARDDATE"));
					data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
					data.setViewCount(rs.getInt("VIEWCOUNT"));
					data.setCategory(rs.getString("CATEGORY"));
					datas.add(data); // 저장한 데이터를 'datas'라는 ArrayList에 추가
				}

				rs.close(); // ResultSet을 닫음으로써 자원을 해제
			} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
			}
		}
		return datas; // 조회된 데이터를 담은 'datas'라는 ArrayList 반환
	}

	
	// 게시글 상세보기. 전미지
	// 단일 게시글을 조회하는 메서드
	public BoardDTO selectOne(BoardDTO boardDTO) {
		// 조회수 증가를 위해 update 메서드 호출
		if (boardDTO.getUpdatePage().equals("조회수증가")) {
			update(boardDTO);
		}
		BoardDTO data = null; // ResultSet에서 읽어온 각 게시판 데이터를 담을 'BoardDTO 클래스 타입의 변수 data'객체 선언 및 'null'로 초기화
		conn = JDBCUtil.connect(); // 데이터 베이스 연결
		if (boardDTO.getCategory().equals("자유게시판")) { // 자유 게시판 선택 시
			try { 
				pstmt = conn.prepareStatement(SELECTONE_FREEBOARD); // 자유게시판 게시글 상세 보기 실행
				pstmt.setInt(1, boardDTO.getBoardNum());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) { // ResultSet에서 데이터가 존재하는 경우
					data = new BoardDTO(); // 새로운 BoardDTO 객체를 생성하고 유저에게 보여줄 게시글의 데이터들을 'data'라는 객체에 담아줌
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
				System.out.println("[로그]" + data);
				rs.close(); // ResultSet을 닫음으로써 자원을 해제
			} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
			}
		} else { // 판매게시판, 리뷰게시판 선택 시
			try {
				pstmt = conn.prepareStatement(SELECTONE); // 판매게시판, 리뷰게시판의 게시글 상세 보기 실행
				pstmt.setInt(1, boardDTO.getBoardNum());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) { // ResultSet에서 데이터가 존재하는 경우
					data = new BoardDTO(); // 새로운 BoardDTO 객체를 생성하고 유저에게 보여줄 게시글의 데이터들을 'data'라는 객체에 담아줌
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
					data.setProductCategory(rs.getString("PRODUCTCATEGORY"));
					data.setProductName(rs.getString("PRODUCTNAME"));
					data.setCompany(rs.getString("COMPANY"));
					data.setPrice(rs.getInt("PRICE"));
				} else {
					return null; // 조회된 데이터가 없을 경우 'null'로 반환
				}

				rs.close(); // ResultSet을 닫음으로써 자원을 해제
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
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, boardDTO.getId());
			pstmt.setString(2, boardDTO.getCategory());
			pstmt.setString(3, boardDTO.getTitle());
			pstmt.setString(4, boardDTO.getContents());
			pstmt.setInt(5, boardDTO.getPrice());
			pstmt.setString(6, boardDTO.getImage());
			pstmt.setString(7, boardDTO.getProductName());
			pstmt.setString(8, boardDTO.getProductCategory());
			pstmt.setString(9, boardDTO.getCompany());
			pstmt.setString(10, boardDTO.getState());
			int rs = pstmt.executeUpdate();
			if (rs <= 0) {
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
		conn = JDBCUtil.connect();
		// 조회수 증가
		if (boardDTO.getUpdatePage().equals("조회수증가")) {
			try {
				pstmt = conn.prepareStatement(UPDATE_PAGE);
				pstmt.setInt(1, boardDTO.getBoardNum());
				System.out.println("[로그] 조회수 증가중?");
				int rs = pstmt.executeUpdate();
				if (rs <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else { // 게시글 수정
			try {
				pstmt = conn.prepareStatement(UPDATE);
				pstmt.setString(1, boardDTO.getTitle());
				pstmt.setString(2, boardDTO.getContents());
				pstmt.setString(3, boardDTO.getImage());
				pstmt.setInt(4, boardDTO.getPrice());
				pstmt.setString(5, boardDTO.getProductCategory());
				pstmt.setString(6, boardDTO.getProductName());
				pstmt.setString(7, boardDTO.getCompany());
				pstmt.setString(8, boardDTO.getState());
				pstmt.setInt(9, boardDTO.getBoardNum());
				int rs = pstmt.executeUpdate();
				if (rs <= 0) {
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
	// delete 메서드의 반환 타입은 boolean 타입으로 게시글 삭제 여부를 나타냄
	public boolean delete(BoardDTO boardDTO) {
		conn = JDBCUtil.connect(); // JDBCUtil을 사용하여 데이터베이스 연결
		try {
			pstmt = conn.prepareStatement(DELETE);  // DELETE 쿼리를 실행하기 위한 PreparedStatement 생성
			pstmt.setInt(1, boardDTO.getBoardNum()); // PreparedStatement에 삭제할 게시글의 번호를 설정
			int rs = pstmt.executeUpdate();  // 게시글이 삭제에 성공하면 1을, 그렇지 않으면 0을 반환
			if (rs <= 0) { //게시글 삭제에 실패할 경우
				return false; // 'false'를 반환하여 메서드를 종료
			}
		} catch (SQLException e) { // SQLException 예외 발생 시 
			e.printStackTrace(); // 콘솔에 예외 내용을 출력하고 'false'로 반환
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 및 PreparedStatement를 닫음
		}
		return true;  // 게시글 삭제를 성공하면 'true'로 반환
	}
}