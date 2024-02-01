package model.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBCUtil;

public class ReportDAO {

	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	// 신고전체출력 신고 목록 전체 출력시 리포트넘버와 신고자만 출력 .정석진
	private static final String SELECTALL = "SELECT REPORTNUM, REPORTER FROM REPORT ORDER BY REPORTNUM DESC";

	// 신고 상세보기 ▶ 신고된 글을 파악할수 있게 BOARDNUM, TITLE 출력, 신고를 INSERT할때 REPORTER, SUSPECT에
	// 멤버 ID가 들어가는 거라면 NICNNAME, ID는 필요없을 것 같아 추가 안함 다중조인이 불필요한 것 같기도 함 .정석진
	private static final String SELECTONE_REPORTMANAGE = "SELECT M.ID, BOARDNUM, TITLE, REPORTNUM, REPORTER, SUSPECT, REPORTCONTENTS, REPORTDATE, ACCOUNTSTOP"
			+ "FROM REPORT R JOIN MEMBER ON MEMBER.ID = REPORT.ID JOIN BOARD ON MEMBER.ID = BOARD.ID WHERE REPORTNUM=?";

	// 신고하기 ▶ 신고페이지 출력에 필요한 정보는 (게시글 제목, 신고할 유저 정보)
	// MemberDAO의 SELECTONE_MEMBERINFO사용 .정석진
	private static final String INSERT_REPORT = "INSERT INTO REPORT(REPORTNUM, ID, SUSPECT, REPORTER, REPORTCONTENTS"
			+ "VALUES((SELECT NVL(MAX(REPORTNUM),0)+1 FROM REPORT),?,?,?,?)";

	private static final String UPDATE = "";

	// 신고삭제 .정석진
	private static final String DELETE = "DELETE FROM REPORT WHERE REPORTNUM=?";

	public ArrayList<ReportDTO> selectAll(ReportDTO reportDTO) {
		ArrayList<ReportDTO> datas = new ArrayList<ReportDTO>();
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ReportDTO data = new ReportDTO();
				data.setReportNum(rs.getInt("REPORTNUM"));
				data.setReporter(rs.getString("REPORTER"));
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

	public ReportDTO selectOne(ReportDTO reportDTO) {
		ReportDTO data = null;
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SELECTONE_REPORTMANAGE);
			pstmt.setInt(1, reportDTO.getReportNum());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				data = new ReportDTO();
				data.setId(rs.getString("ID"));
				data.setBoardNum(rs.getInt("BOARDNUM"));
				data.setTitle(rs.getString("TITLE"));
				data.setReportNum(rs.getInt("REPORTNUM"));
				data.setReporter(rs.getString("REPORTER"));
				data.setSuspect(rs.getString("SUSPECT"));
				data.setReportContents(rs.getString("REPORTCONTENTS"));
				data.setReportDate(rs.getString("REPORTDATE"));
				data.setAccountStop(rs.getString("ACCOUNTSTOP"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	public boolean insert(ReportDTO reportDTO) {
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(INSERT_REPORT);
			pstmt.setString(1, reportDTO.getId());
			pstmt.setString(2, reportDTO.getSuspect());
			pstmt.setString(3, reportDTO.getReporter());
			pstmt.setString(4, reportDTO.getReportContents());

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;

	}

	public boolean update(ReportDTO reportDTO) {

		return false;
	}

	private boolean delete(ReportDTO reportDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, reportDTO.getReportNum());

			int result = pstmt.executeUpdate();
			if (result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

}
