
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

	String BPRICESQL = "";
	String BCOMPANYSQL = "";
	String BPRODUCTCATEGORYSQL = "";
	String BSTATESQL = "";
	String CHECK = "";
	String CATEGORY = "";

	public ArrayList<BoardDTO> selectAll(SearchDTO searchDTO) {
		ArrayList<BoardDTO> datas = new ArrayList<BoardDTO>();
		BoardDTO data = null;
		conn = JDBCUtil.connect();
		try {
			 CATEGORY = searchDTO.getCategory();
			 
			 
			 if (searchDTO.getMaxPrice() !=0) {
				 BPRICESQL = "AND PRICE BETWEEN " + searchDTO.getMinPrice() + " AND " + searchDTO.getMaxPrice();
			 }
			 
			 
			if (searchDTO.getCompanyList().size() > 0) {
				StringBuilder bCompanySb = new StringBuilder();
				ArrayList<String> companyDatas = searchDTO.getCompanyList();
				
				for (int i = 0; i < searchDTO.getCompanyList().size(); i++) {
					bCompanySb.append("\'" + companyDatas.get(i) + "\'");
					if (i + 1 < searchDTO.getCompanyList().size()) {
						bCompanySb.append(",");
					}
					BCOMPANYSQL = "AND COMPANY IN (" + bCompanySb.toString() + ")";
				}
			}
			
			if (searchDTO.getProductcategoryList().size() > 0) {
				StringBuilder bProductcategorySb = new StringBuilder();
				ArrayList<String> productcategoryDatas = searchDTO.getProductcategoryList();
				
				for (int i = 0; i < searchDTO.getProductcategoryList().size(); i++) {
					bProductcategorySb.append("\'" + productcategoryDatas.get(i) + "\'");
					if (i + 1 < searchDTO.getProductcategoryList().size()) {
						bProductcategorySb.append(",");
					}
					BPRODUCTCATEGORYSQL = "AND PRODUCTCATEGORY IN (" + bProductcategorySb.toString() + ")";
				}
			}
			
			if (searchDTO.getStateList().size() > 0) {
				StringBuilder bStateSb = new StringBuilder();
				ArrayList<String> stateDatas = searchDTO.getStateList();
				
				for (int i = 0; i < searchDTO.getStateList().size(); i++) {
					bStateSb.append("\'" + stateDatas.get(i) + "\'");
					if (i + 1 < searchDTO.getStateList().size()) {
						bStateSb.append(",");
					}
					BSTATESQL = "AND STATE IN (" + bStateSb.toString() + ")";
				}
			}
			
			if (searchDTO.getChecksort() != null) {
				String element = "";
				if (searchDTO.getChecksort().equals("최신순")) {
					element = "FILTER_DATA.BOARDNUM";
				}
				if (searchDTO.getChecksort().equals("제목순")) {
					element = "TITLE";
				}
				if (searchDTO.getChecksort().equals("가격순")) {
					element = "PRICE";
				}
				if (searchDTO.getChecksort().equals("조회순")) {
					element = "VIEWCOUNT";
				}
				if (searchDTO.getChecksort().equals("좋아요순")) {
					element = "RECCOMENDCNT";
				}
				
				CHECK = "ORDER BY " + element + " ASC ";
				System.out.println("[로그] element값 : " + element);
				
			}
			
			String SQL_SELECTALL = "SELECT * FROM ("
				    + "    SELECT FILTER_DATA.*, COALESCE(RECOMMEND_COUNT.RECOMMENDCNT, 0) AS RECOMMENDCNT FROM ("
				    + "        SELECT * FROM BOARD WHERE 1=1 " 
				    + BPRICESQL + " " + BCOMPANYSQL + " " + BPRODUCTCATEGORYSQL + " " + BSTATESQL + "ORDER BY BOARDNUM ASC"
				    + "    ) FILTER_DATA"
				    + "    LEFT JOIN ("
				    + "        SELECT BOARDNUM, COUNT(BOARDNUM) AS RECOMMENDCNT FROM RECOMMEND GROUP BY BOARDNUM"
				    + "    ) RECOMMEND_COUNT"
				    + "    ON FILTER_DATA.BOARDNUM = RECOMMEND_COUNT.BOARDNUM"  
				    + CHECK 
				    + ") SORT_DATA LEFT JOIN MEMBER ON MEMBER.ID = SORT_DATA.ID WHERE CATEGORY = '" + CATEGORY + "'";
			pstmt = conn.prepareStatement(SQL_SELECTALL);
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
				data.setCompany(rs.getString("COMPANY"));
				datas.add(data); // 게시글의 정보를 담은 'data'를'datas'에 담아줌
				
			}

			rs.close(); // ResultSet을 닫음으로써 자원을 해제
		} catch (SQLException e) { // SQLException 예외가 발생할 경우 해당 예외를 출력
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn); // 데이터베이스 연결 해제
		}
		return datas;
	}
}
