package model.commend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.common.JDBCUtil;

public class CommandDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String INSERT = "INSERT INTO COMMEND VALUES ((SELECT NVL(MAX(BOARDNUM),0)+1 FROM COMMEND), ?, ?)";
	private static final String DELETE = "DELETE FROM COMMEND WHERE BOARDNUM = ? AND MEMBERNUM = ?";

	public boolean insert(CommendDTO commendDTO) {
	      conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(INSERT);
	         pstmt.setInt(1, commendDTO.getMemberNum());
	         pstmt.setInt(2, commendDTO.getBoardNum());

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
	
	public boolean delete(CommendDTO commendDTO) {
		conn=JDBCUtil.connect();
	      try {
	         pstmt=conn.prepareStatement(DELETE);
	         pstmt.setInt(1, commendDTO.getBoardNum());
	         pstmt.setInt(2, commendDTO.getMemberNum());
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
