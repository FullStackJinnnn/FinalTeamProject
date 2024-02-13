
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

	String PRICESQL = "";
	String COMPANYSQL = "";
	String PRODUCTCATEGORYSQL = "";
	String STATESQL = "";

	String USERSEARCHSQL = "";
	String ORDERSQL = "";
	String CATEGORYSQL = "";

	String SQL_SELECTALL = "";

	public ArrayList<BoardDTO> selectAll(SearchDTO searchDTO) {
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();
		BoardDTO data = null;
		conn = JDBCUtil.connect();
		try {
			CATEGORYSQL = searchDTO.getCategory();
			System.out.println(CATEGORYSQL);
			System.out.println(searchDTO);

			if (searchDTO.getSearchField() != null && searchDTO.getSearchInput() != null) {
				if (searchDTO.getSearchField().equals("title")) {
					USERSEARCHSQL = "AND TITlE LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
				}
				if (searchDTO.getSearchField().equals("contents")) {
					USERSEARCHSQL = "AND CONTENTS LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
				}
				if (searchDTO.getSearchField().equals("writer")) {
					USERSEARCHSQL = "AND NICKNAME LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
				}
				if (searchDTO.getSearchField().equals("titleAndContents")) {
					USERSEARCHSQL = "AND (TITLE LIKE '%'||'" + searchDTO.getSearchInput()
							+ "'||'%' OR CONTENTS LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%')";
				}
			}

			if (searchDTO.getMaxPrice() != 0) {
				PRICESQL = "AND PRICE BETWEEN " + searchDTO.getMinPrice() + " AND " + searchDTO.getMaxPrice();
			}

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

			if (searchDTO.getProductcategoryList().size() > 0) {
			    StringBuilder bProductcategorySb = new StringBuilder();
			    ArrayList<String> productcategoryDatas = searchDTO.getProductcategoryList();

			    for (int i = 0; i < searchDTO.getProductcategoryList().size(); i++) {
			        String productCategory = productcategoryDatas.get(i);
			        // 대소문자 구분 없이 검색하기 위해, 대문자와 소문자 둘 다 쿼리문에 추가
			        bProductcategorySb.append("\'" + productCategory.toUpperCase() + "\',");
			        bProductcategorySb.append("\'" + productCategory.toLowerCase() + "\'");
			        if (i + 1 < searchDTO.getProductcategoryList().size()) {
			            bProductcategorySb.append(",");
			        }
			    }
			    PRODUCTCATEGORYSQL = "AND PRODUCTCATEGORY IN (" + bProductcategorySb.toString() + ")";
			}

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
			
			
			String element ="";

			if (!searchDTO.getOrderColumnDirection().isEmpty()) {
				if (searchDTO.getOrderColumnDirection().containsKey("boardNum")) {
					String direction = searchDTO.getOrderColumnDirection().get("boardNum");
					element = "SORT_DATA.BOARDNUM " + direction;
				}
				if (searchDTO.getOrderColumnDirection().containsKey("title")) {
					String direction = searchDTO.getOrderColumnDirection().get("title");
					element = "TITLE " + direction;
				}
				if (searchDTO.getOrderColumnDirection().containsKey("writer")) {
					String direction = searchDTO.getOrderColumnDirection().get("writer");
					element = "NICKNAME " + direction;
				}
				if (searchDTO.getOrderColumnDirection().containsKey("boardDate")) {
					String direction = searchDTO.getOrderColumnDirection().get("boardDate");
					element = "SORT_DATA.BOARDNUM " + direction;
				}

				if (searchDTO.getOrderColumnDirection().containsKey("views")) {
					String direction = searchDTO.getOrderColumnDirection().get("views");
					element = "VIEWCOUNT " + direction;

				}
				if (searchDTO.getOrderColumnDirection().containsKey("recommendCNT")) {
					String direction = searchDTO.getOrderColumnDirection().get("recommendCNT");
					element = "RECOMMENDCNT " + direction;
				}
				if (searchDTO.getOrderColumnDirection().containsKey("price")) {
					String direction = searchDTO.getOrderColumnDirection().get("price");
					element = "PRICE " + direction;
				}
				ORDERSQL = "ORDER BY " + element;
				System.out.println("[로그] element값 : " + element);
					
			}
	
				
			
			if (!CATEGORYSQL.equals("")) {
				System.out.println("[로그] SELECTALL 진입!");
				SQL_SELECTALL = "SELECT " + "SORT_DATA.*, " + "MEMBER.NICKNAME, " + "MEMBER.ID " + "FROM (" + "SELECT "
						+ "FILTER_DATA.*, " + "COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT " + "FROM ("
						+ "SELECT ROWNUM, "
						+ "BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
						+ "PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT " + "FROM BOARD " + "WHERE CATEGORY = '"
						+ CATEGORYSQL + "' " + PRICESQL
						+ " " + COMPANYSQL + " " + PRODUCTCATEGORYSQL + " " + STATESQL + "ORDER BY BOARDNUM ASC"
						+ ") FILTER_DATA " + "LEFT JOIN (" + "SELECT " + "BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT "
						+ "FROM RECOMMEND " + "GROUP BY BOARDNUM"
						+ ") RECOMMEND_COUNT ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM ORDER BY ROWNUM DESC" 
						+ ") SORT_DATA " + "JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID "  + USERSEARCHSQL  +" " + ORDERSQL;
				System.out.println(SQL_SELECTALL);
				pstmt = conn.prepareStatement(SQL_SELECTALL);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					data = new BoardDTO();// 새로운 BoardDTO 객체 생성
					// ResultSet에서 읽은 각 열의 값을 해당 객체에 담음
					data.setBoardNum(rs.getInt("BOARDNUM"));
					data.setRownum(rs.getInt("ROWNUM"));
					data.setTitle(rs.getString("TITLE"));
					data.setNickname(rs.getString("NICKNAME"));
					data.setBoardDate(rs.getString("BOARDDATE"));
					data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
					data.setViewCount(rs.getInt("VIEWCOUNT"));
					data.setPrice(rs.getInt("PRICE"));
					data.setState(rs.getString("STATE"));
					data.setCategory(rs.getString("CATEGORY"));
					datas.add(data); // 게시글의 정보를 담은 'data'를'datas'에 담아줌
				}
				rs.close(); // ResultSet을 닫음으로써 자원을 해제
			} else {
				SQL_SELECTALL = "SELECT " +
	                      "SORT_DATA.*, " +
	                      "MEMBER.NICKNAME " +
	                      "FROM (" +
	                      "    SELECT " +
	                      "        FILTER_DATA.*, " +
	                      "        COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT " +
	                      "    FROM (" +
	                      "        SELECT " +
	                      "            ROWNUM, BOARDNUM, ID, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, VIEWCOUNT, STATE, CATEGORY, PRICE " +
	                      "        FROM BOARD " +
	                      "        ORDER BY BOARDNUM ASC" +
	                      "    ) FILTER_DATA " +
	                      "    LEFT JOIN (" +
	                      "        SELECT " +
	                      "            BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT " +
	                      "        FROM RECOMMEND " +
	                      "        GROUP BY BOARDNUM" +
	                      "    ) RECOMMEND_COUNT ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM ORDER BY ROWNUM DESC" +
	                      ") SORT_DATA " +
	                      "JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID " +
	                      "WHERE MEMBER.ID='" + searchDTO.getId() + "' " + ORDERSQL;
				System.out.println(SQL_SELECTALL);
				pstmt = conn.prepareStatement(SQL_SELECTALL);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					data = new BoardDTO();// 새로운 BoardDTO 객체 생성
					// ResultSet에서 읽은 각 열의 값을 해당 객체에 담음
					data.setBoardNum(rs.getInt("BOARDNUM"));
					data.setRownum(rs.getInt("ROWNUM"));
					data.setTitle(rs.getString("TITLE"));
					data.setNickname(rs.getString("NICKNAME"));
					data.setId(rs.getString("ID"));
					data.setBoardDate(rs.getString("BOARDDATE"));
					data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
					data.setViewCount(rs.getInt("VIEWCOUNT"));
					data.setState(rs.getString("STATE"));
					datas.add(data); // 게시글의 정보를 담은 'data'를'datas'에 담아줌
					
				}
				rs.close(); // ResultSet을 닫음으로써 자원을 해제
			}
		} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
		}
		return datas;
	}
}
