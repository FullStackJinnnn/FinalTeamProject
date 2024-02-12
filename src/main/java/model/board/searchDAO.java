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

    // 검색 및 필터링을 위한 SQL 구문을 저장하는 변수들
    String PRICESQL = "";
    String COMPANYSQL = "";
    String PRODUCTCATEGORYSQL = "";
    String STATESQL = "";
    String USERSEARCHSQL = "";
    String ORDERSQL = "";
    String CATEGORYSQL = "";
    String SQL_SELECTALL = "";

    // 필터링 후 데이터리스트를 반환하는 메서드
    public ArrayList<BoardDTO> selectAll(SearchDTO searchDTO) {
        ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();
        BoardDTO data = null;
        conn = JDBCUtil.connect();

        try {
            // 각 게시판에 맞는 게시글을 출력해주기 위한 값 설정
            CATEGORYSQL = searchDTO.getCategory();

            // 제목, 작성자, 내용 등의 검색어 및 검색 필드 설정
            if (searchDTO.getSearchField() != null && searchDTO.getSearchInput() != null) {
                if (searchDTO.getSearchField().equals("title")) {
                    USERSEARCHSQL = "AND TITLE LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
                } else if (searchDTO.getSearchField().equals("contents")) {
                    USERSEARCHSQL = "AND CONTENTS LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
                } else if (searchDTO.getSearchField().equals("writer")) {
                    USERSEARCHSQL = "AND NICKNAME LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%'";
                } else if (searchDTO.getSearchField().equals("titleAndContents")) {
                    USERSEARCHSQL = "AND (TITLE LIKE '%'||'" + searchDTO.getSearchInput() +
                            "'||'%' OR CONTENTS LIKE '%'||'" + searchDTO.getSearchInput() + "'||'%')";
                }
            }

            // 카메라 가격 검색 조건 설정
            if (searchDTO.getMaxPrice() != 0) {
                PRICESQL = "AND PRICE BETWEEN " + searchDTO.getMinPrice() + " AND " + searchDTO.getMaxPrice();
            }

	         // 카메라 제조사 검색 조건 설정
	         if (searchDTO.getCompanyList().size() > 0) {
	
	             // 문자열을 조작하기 위한 StringBuilder 객체를 생성.
	             StringBuilder bCompanySb = new StringBuilder();
	
	             ArrayList<String> companyDatas = searchDTO.getCompanyList();
	
	             for (int i = 0; i < searchDTO.getCompanyList().size(); i++) {
	
	                 // 각 회사 이름 앞뒤로 작은따옴표(')를 붙여 StringBuilder에 추가
	                 bCompanySb.append("\'" + companyDatas.get(i) + "\'");
	
	                 // 현재 회사가 리스트의 마지막이 아니라면, StringBuilder에 쉼표(,)를 추가합니다.
	                 if (i + 1 < searchDTO.getCompanyList().size()) {
	                     bCompanySb.append(",");
	                 }
	
	                 // StringBuilder에 저장된 회사 이름들을 문자열로 변환
	                 // 이렇게 만들어진 문자열은 "AND COMPANY IN (회사1', '회사2', '회사3'...)" 형태
	                 COMPANYSQL = "AND COMPANY IN (" + bCompanySb.toString() + ")";
	             }
	         }

            // 카메라 종류 검색 조건 설정
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

            // 지역 검색 조건 설정
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

            // 가격 정렬 조건 설정
            String element = "";
            if (searchDTO.getPriceSort() != null && !searchDTO.getPriceSort().isEmpty()) {
                element = "PRICE " + searchDTO.getPriceSort();
                ORDERSQL = "ORDER BY " + element;
                System.out.println("[로그] element값 : " + element);
            } else if (!searchDTO.getOrderColumnDirection().isEmpty()) {
            	
                // 다른 컬럼에 대한 정렬 조건 설정
                if (searchDTO.getOrderColumnDirection().containsKey("boardNum")) {
                    String direction = searchDTO.getOrderColumnDirection().get("boardNum");
                    element = "SORT_DATA.BOARDNUM " + direction;
                } else if (searchDTO.getOrderColumnDirection().containsKey("title")) {
                    String direction = searchDTO.getOrderColumnDirection().get("title");
                    element = "TITLE " + direction;
                } else if (searchDTO.getOrderColumnDirection().containsKey("writer")) {
                    String direction = searchDTO.getOrderColumnDirection().get("writer");
                    element = "NICKNAME " + direction;
                } else if (searchDTO.getOrderColumnDirection().containsKey("boardDate")) {
                    String direction = searchDTO.getOrderColumnDirection().get("boardDate");
                    element = "SORT_DATA.BOARDDATE " + direction;
                } else if (searchDTO.getOrderColumnDirection().containsKey("views")) {
                    String direction = searchDTO.getOrderColumnDirection().get("views");
                    element = "VIEWCOUNT " + direction;
                } else if (searchDTO.getOrderColumnDirection().containsKey("recommendCNT")) {
                    String direction = searchDTO.getOrderColumnDirection().get("recommendCNT");
                    element = "RECOMMENDCNT " + direction;
                }
                ORDERSQL = "ORDER BY " + element;
                //System.out.println("[로그] element값 : " + element);
            }

            // 카테고리가 설정되어 있으면 해당 카테고리의 게시글 목록을 조회
            if (!CATEGORYSQL.equals("")) {
               // System.out.println("[로그] SELECTALL 진입!");
                SQL_SELECTALL = "SELECT " + "SORT_DATA.*, " + "MEMBER.NICKNAME, " + "MEMBER.ID " + "FROM (" + "SELECT "
                        + "FILTER_DATA.*, " + "COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT " + "FROM ("
                        + "SELECT "
                        + "BOARDNUM, ID, CATEGORY, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, "
                        + "PRICE, PRODUCTCATEGORY, COMPANY, STATE, VIEWCOUNT " + "FROM BOARD " + "WHERE 1=1 " + PRICESQL
                        + " " + COMPANYSQL + " " + PRODUCTCATEGORYSQL + " " + STATESQL + "ORDER BY BOARDNUM DESC"
                        + ") FILTER_DATA " + "LEFT JOIN (" + "SELECT " + "BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT "
                        + "FROM RECOMMEND " + "GROUP BY BOARDNUM"
                        + ") RECOMMEND_COUNT ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM " 
                        + ") SORT_DATA " + "RIGHT JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID " + "WHERE CATEGORY = '"
                        + CATEGORYSQL + "' " + USERSEARCHSQL + " " + ORDERSQL;
                System.out.println(SQL_SELECTALL);
                pstmt = conn.prepareStatement(SQL_SELECTALL);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    data = new BoardDTO(); 
                    data.setBoardNum(rs.getInt("BOARDNUM"));
                    data.setTitle(rs.getString("TITLE"));
                    data.setNickname(rs.getString("NICKNAME"));
                    data.setBoardDate(rs.getString("BOARDDATE"));
                    data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
                    data.setViewCount(rs.getInt("VIEWCOUNT"));
                    data.setState(rs.getString("STATE"));
                    data.setCategory(rs.getString("CATEGORY"));
                    datas.add(data); 
                }
                rs.close(); 
            } else {
                // 카테고리가 설정되어 있지 않으면 해당 사용자가 작성한 게시글 목록을 조회
                SQL_SELECTALL = "SELECT " + "SORT_DATA.*, " + "MEMBER.NICKNAME " + "FROM (" + "    SELECT "
                        + "        FILTER_DATA.*, "
                        + "        COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT " + "    FROM ("
                        + "        SELECT "
                        + "            BOARDNUM, ID, TITLE, CONTENTS, TO_CHAR(BOARDDATE, 'YYYY-MM-DD') AS BOARDDATE, VIEWCOUNT, STATE, CATEGORY, PRICE"
                        + "        FROM BOARD " + "        ORDER BY BOARDNUM DESC" + "    ) FILTER_DATA "
                        + "    LEFT JOIN (" + "        SELECT "
                        + "            BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT " + "        FROM RECOMMEND "
                        + "        GROUP BY BOARDNUM"
                        + "    ) RECOMMEND_COUNT ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM " 
                        + ") SORT_DATA " + "RIGHT JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID " + "WHERE MEMBER.ID=" + "\'" + searchDTO.getId() + "\' "  + ORDERSQL;
                System.out.println(SQL_SELECTALL);
                pstmt = conn.prepareStatement(SQL_SELECTALL);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    data = new BoardDTO(); 
                    data.setBoardNum(rs.getInt("BOARDNUM"));
                    data.setTitle(rs.getString("TITLE"));
                    data.setNickname(rs.getString("NICKNAME"));
                    data.setId(rs.getString("ID"));
                    data.setBoardDate(rs.getString("BOARDDATE"));
                    data.setRecommendCNT(rs.getInt("RECOMMENDCNT"));
                    data.setViewCount(rs.getInt("VIEWCOUNT"));
                    data.setState(rs.getString("STATE"));
                    datas.add(data); 
                }
                rs.close(); 
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        } finally {
            JDBCUtil.disconnect(pstmt, conn); 
        }
        return datas;
    }
}
