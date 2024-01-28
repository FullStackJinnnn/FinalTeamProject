package model.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.board.BoardDTO;
import model.common.JDBCUtil;

public class ReviewDAO {

	private Connection conn;
	private PreparedStatement pstmt;

	// 댓글 출력 댓글 테이블과 회원 테이블을 조인
	private static final String SELECTALL = "SELECT B.REVIEWNUM, B.BOARDNUM, M.ID,  TO_CHAR(B.REVIEWDATE, 'YYYY-MM-DD') AS B.REVIEWDATE, B.REVIEWCONTENTS FROM BOARD B "
			+ "JOIN MEMBER M ON B.MEMBERNUM = M.MEMBERNUM WHERE B.BOARDNUM = ?";
	// 댓글 상세보기 댓글 테이블과 회원 테이블을 조인
	private static final String SELECTONE = "SELECT B.REVIEWNUM, B.BOARDNUM, M.ID, TO_CHAR(B.REVIEWDATE, 'YYYY-MM-DD') AS B.REVIEWDATE, B.REVIEWCONTENTS FROM BOARD B "
			+ "JOIN MEMBER M ON B.MEMBERNUM = M.MEMBERNUM WHERE B.REVIEWNUM = ?";
	// 댓글 작성
	private static final String INSERT = "INSERT INTO REVIEW VALUES((SELECT NVL(MAX(REVIEWNUM),0)+1 FROM REVIEW),?,?,?)";
	// 댓글 수정
	private static final String UPDATE = "UPDATE REVIEW SET REVIEWCONTENTS=? WHERE REVIEWNUM=?";
	// 댓글 삭제
	private static final String DELETE = "DELETE FROM REVIEW WHERE REVIEWNUM = ?";

	// 한 게시글의 댓글 전체 목록 출력
	public ArrayList<ReviewDTO> selectAll(ReviewDTO reviewDTO) {
		ArrayList<ReviewDTO> datas = new ArrayList<ReviewDTO>();
		ReviewDTO data = null;
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SELECTALL);
			pstmt.setInt(1, reviewDTO.getBoardNum());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				data = new ReviewDTO();
				data.setReviewNum(rs.getInt("REVIEWNUM"));
				data.setBoardNum(rs.getInt("BOARDNUM"));
				data.setId(rs.getString("ID"));
				data.setReviewDate(rs.getString("REVIEWDATE"));
				data.setReviewContents(rs.getString("REVIEWCONTENTS"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	// 댓글 상세보기
	protected ReviewDTO selectOne(ReviewDTO reviewDTO) {
		ReviewDTO data = null;
		conn = JDBCUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, reviewDTO.getReviewNum());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new ReviewDTO();
				data.setReviewNum(rs.getInt("REVIEWNUM"));
				data.setBoardNum(rs.getInt("BOARDNUM"));
				data.setId(rs.getString("ID"));
				data.setReviewDate(rs.getString("REVIEWDATE"));
				data.setReviewContents(rs.getString("REVIEWCONTENTS"));
			} else {
				return null;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return reviewDTO;
	}

	// 댓글 작성
	public boolean insert(ReviewDTO reviewDTO) {

		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, reviewDTO.getBoardNum());
			pstmt.setString(2, reviewDTO.getId());
			pstmt.setString(3, reviewDTO.getReviewContents());
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
	// 댓글 수정
	public boolean update(ReviewDTO reviewDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, reviewDTO.getReviewContents());
			pstmt.setInt(2, reviewDTO.getBoardNum());
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
	// 댓글 삭제
	public boolean delete(ReviewDTO reviewDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, reviewDTO.getReviewNum());
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

}
