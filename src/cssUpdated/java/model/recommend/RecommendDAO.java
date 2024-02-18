package model.recommend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JDBCUtil;

public class RecommendDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTONE = "SELECT ID, BOARDNUM FROM RECOMMEND WHERE ID = ? AND BOARDNUM = ? ";
	private static final String INSERT = "INSERT INTO RECOMMEND (RECOMMENDNUM, BOARDNUM, ID) VALUES ((SELECT NVL(MAX(RECOMMENDNUM),0)+1 FROM RECOMMEND), ?, ?)";
	private static final String DELETE = "DELETE FROM RECOMMEND WHERE BOARDNUM = ? AND ID = ?";
	
	public RecommendDTO selectOne(RecommendDTO recommendDTO) {
		RecommendDTO data = null;
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setString(1, recommendDTO.getId());
			pstmt.setInt(2, recommendDTO.getBoardNum());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				data = new RecommendDTO();
				data.setId(rs.getString("ID"));
				data.setBoardNum(rs.getInt("BOARDNUM"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		
		return data;
	}

	public boolean insert(RecommendDTO recommendDTO) {
	      conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(INSERT);
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