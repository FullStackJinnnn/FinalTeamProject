package model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class SearchDAO {

	private Connection conn; 
	private PreparedStatement pstmt; 

	// 검색 조건에 사용되는 SQL문을 저장할 변수들
	String PRICESQL = "";
	String COMPANYSQL = "";
	String PRODUCTCATEGORYSQL = "";
	String STATESQL = "";
	String USERSEARCHSQL = "";
	String ORDERSQL = "";
	String CATEGORYSQL = "";

	// 게시글 목록을 조회하는 메서드
	public ArrayList<BoardDTO> selectAll(SearchDTO searchDTO) {
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>(); // 검색 결과를 저장할 ArrayList
		BoardDTO data = null; // 한 개의 게시글 정보를 담을 객체
		conn = JDBCUtil.connect(); 

		try {
			CATEGORYSQL = searchDTO.getCategory(); // 검색 조건으로 선택된 카테고리

			// 검색 필드 및 입력값이 있는 경우에 대한 처리
			if (searchDTO.getSearchField() != null && searchDTO.getSearchInput() != null) {
				if (searchDTO.getSearchField().equals("title")) {
					USERSEARCHSQL = "AND TITLE LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
				}
				if (searchDTO.getSearchField().equals("contents")) {
					USERSEARCHSQL = "AND CONTENTS LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
				}
				if (searchDTO.getSearchField().equals("writer")) {
					USERSEARCHSQL = "AND NICKNAME LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
				}
				if (searchDTO.getSearchField().equals("titleAndContents")) {
					USERSEARCHSQL = "AND TITLE LIKE '%'||'" + searchDTO.getSearchInput()
							+ "'||'%' AND CONTENTS LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
				}
			}

			// 가격 범위 조건이 있는 경우
			if (searchDTO.getMaxPrice() != 0) {
				PRICESQL = "AND PRICE BETWEEN " + searchDTO.getMinPrice() + " AND " + searchDTO.getMaxPrice();
			}

			// 회사 목록 조건이 있는 경우
			if (searchDTO.getCompanyList().size() > 0) {
				StringBuilder bCompanySb = new StringBuilder();
				ArrayList<String> companyDatas = searchDTO.getCompanyList();

				for (int i = 0; i < searchDTO.getCompanyList().size(); i++) {
					bCompanySb.append("\'" + companyDatas.get(i) + "\'");
					if (i + 1 < searchDTO.getCompanyList().size()) {
						bCompanySb.append(",");
					}
					COMPANYSQL = "AND COMPANY IN (" + bCompanySb.toString() + ")";
				}
			}

			// 제품 카테고리 목록 조건이 있는 경우
			if (searchDTO.getProductcategoryList().size() > 0) {
				StringBuilder bProductcategorySb = new StringBuilder();
				ArrayList<String> productcategoryDatas = searchDTO.getProductcategoryList();

				for (int i = 0; i < searchDTO.getProductcategoryList().size(); i++) {
					bProductcategorySb.append("\'" + productcategoryDatas.get(i) + "\'");
					if (i + 1 < searchDTO.getProductcategoryList().size()) {
						bProductcategorySb.append(",");
					}
					PRODUCTCATEGORYSQL = "AND PRODUCTCATEGORY IN (" + bProductcategorySb.toString() + ")";
				}
			}

			// 지역 목록 조건이 있는 경우
			if (searchDTO.getStateList().size() > 0) {
				StringBuilder bStateSb = new StringBuilder();
				ArrayList<String> stateDatas = searchDTO.getStateList();

				for (int i = 0; i < searchDTO.getStateList().size(); i++) {
					bStateSb.append("\'" + stateDatas.get(i) + "\'");
					if (i + 1 < searchDTO.getStateList().size()) {
						bStateSb.append(",");
					}
					STATESQL = "AND STATE IN (" + bStateSb.toString() + ")";
				}
			}

			// 정렬기준과 방법이 지정된 경우
			if (!searchDTO.getOrderColumnDirection().isEmpty()) {
				System.out.println("[로그]" + searchDTO.getOrderColumnDirection());
				String element = "";
				if (searchDTO.orderColumnDirection.containsKey("boardNum")) {
					String direction = searchDTO.orderColumnDirection.get("boardNum");
					element = "FILTER_DATA.BOARDNUM " + direction;
				}
				if (searchDTO.orderColumnDirection.containsKey("title")) {
					String direction = searchDTO.orderColumnDirection.get("title");
					element = "TITLE " + direction;
				}
				if (searchDTO.orderColumnDirection.containsKey("writer")) {
					String direction = searchDTO.orderColumnDirection.get("writer");
					element = "PRICE " + direction;
				}
				if (searchDTO.orderColumnDirection.containsKey("boardDate")) {
					String direction = searchDTO.orderColumnDirection.get("boardDate");
					element = "FILTER_DATA.BOARDDATE " + direction;
				}

				if (searchDTO.orderColumnDirection.containsKey("views")) {
					String direction = searchDTO.orderColumnDirection.get("views");
					element = "VIEWCOUNT " + direction;
				}
				if (searchDTO.orderColumnDirection.containsKey("recommendCNT")) {
					String direction = searchDTO.orderColumnDirection.get("recommendCNT");
					element = "RECOMMENDCNT " + direction;
				}

				ORDERSQL = "ORDER BY " + element;
				System.out.println("[로그] element값 : " + element);
			}

			// 전체 SQL문을 조합하여 최종 조회 SQL문 생성
			String SQL_SELECTALL = "SELECT " +
					"SORT_DATA.*, " +
					"MEMBER.NICKNAME, " +
					"MEMBER.ID " +
					"FROM (" +
					"SELECT " +
					"FILTER_DATA.*, " +
					"COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT " +
					"FROM (" +
					"SELECT " +
					"BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, " +
					"PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT " +
					"FROM BOARD " +
					"WHERE 1=1 " + PRICESQL + " " + COMPANYSQL + " " + PRODUCTCATEGORYSQL + " " + STATESQL +
					"ORDER BY BOARDNUM ASC" +
					") FILTER_DATA " +
					"LEFT JOIN (" +
					"SELECT " +
					"BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT " +
					"FROM RECOMMEND " +
					"GROUP BY BOARDNUM" +
					") RECOMMEND_COUNT ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM " + ORDERSQL +
					") SORT_DATA " +
					"RIGHT JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID " +
					"WHERE CATEGORY = '" + CATEGORYSQL + "' " + USERSEARCHSQL;

			System.out.println(SQL_SELECTALL); 
			pstmt = conn.prepareStatement(SQL_SELECTALL); 
			ResultSet rs = pstmt.executeQuery(); 

			// 결과 집합을 반복문을 통해 처리
			while (rs.next()) {
				data = new BoardDTO(); // 새로운 BoardDTO 객체 생성
				// ResultSet에서 읽은 각 열의 값을 해당 객체에 담음
				data.setBoardNum(rs.getInt("BOARDNUM"));
				data.setTitle(rs.getString("TITLE"));
				data.setNickname(rs.getString("NICKNAME"));
				data.setBoardDate(rs.getString("BOARDDATE"));
				data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
				data.setViewCount(rs.getInt("VIEWCOUNT"));
				datas.add(data); // 게시글의 정보를 담은 'data'를 'datas'에 담아줌
			}

			rs.close(); // ResultSet을 닫음으로써 자원을 해제
		} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
		}
		return datas; // 최종 검색 결과를 반환
	}
}