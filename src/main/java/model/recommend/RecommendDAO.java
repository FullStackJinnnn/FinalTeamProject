package model.recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.common.JDBCUtil;

public class RecommendDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String INSERT = "INSERT INTO RECOMMEND VALUES ((SELECT NVL(MAX(BOARDNUM),0)+1 FROM RECOMMEND), ?, ?)";
	private static final String DELETE = "DELETE FROM RECOMMEND WHERE BOARDNUM = ? AND ID = ?";

	public boolean insert(RecommendDTO recommendDTO) {
	      conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(INSERT);
	         pstmt.setString(1, recommendDTO.getId());
	         pstmt.setInt(2, recommendDTO.getBoardNum());

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
	
	public boolean delete(RecommendDTO recommendDTO) {
		conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(DELETE);
	         pstmt.setInt(1, recommendDTO.getBoardNum());
	         pstmt.setString(2, recommendDTO.getId());
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
