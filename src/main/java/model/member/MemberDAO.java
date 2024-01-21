package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JDBCUtil;

public class MemberDAO {

	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	// 유저 전체출력 기능 필요없음 .정석진
	private static final String SELECTALL = "";

	// 아이디 찾기 ▶ 이름, 전화번호 받아서 아이디 찾기 .정석진
	private static final String SELECTONE_FINDID = "SELECT ID" + " FROM MEMBER WHERE NAME=? AND PH=?";

	// 비밀번호 찾기 ▶ 아이디, 전화번호 받아서 비밀번호 찾기 .정석진
	private static final String SELECTONE_FINDPW = "SELECT PW" + " FROM MEMBER WHERE ID=? AND PH=?";

	// 로그인 ▶ 로그인시 세션에 memberID만 저장하기 때문에 ID만 가져옴 .정석진
	private static final String SELECTONE_LOGIN = "SELECT ID" + " FROM MEMBER WHERE ID=? AND PW=?";

	// 내 정보 출력 및 다른 유저 정보 출력 ▶
	// 생년월일 출력 형식은 '1993-09-10' 으로 지정
	// 자신과 다른유저가 작성한 글 볼때 필요한 BOARDNUM, TITLE을 INNER JOIN으로 가져옴
	// 아이디자체가 고유값이기 때문에 아이디로 확인해서 정보 가져옴 .정석진
	private static final String SELECTONE_MEMBERINFO = "SELECT M.MEMBERNUM,ID,PW,NAME,NICKNAME, TO_CHAR(BIRTH,'YYYY-MM-DD') AS BIRTH_DATE,PH,PROFILE,GRADE, BOARDNUM"
			+ " FROM MEMBER M LEFT JOIN BOARD B ON M.MEMBERNUM = B.MEMBERNUM WHERE ID=?";

	private static final String SELECTONE_REPORT = "SELECT M.MEMBERNUM,ID,NICKNAME, BOARDNUM, TITLE, REPORTCONTENTS"
			+ " FROM MEMBER M LEFT JOIN BOARD B ON M.MEMBERNUM = B.MEMBERNUM WHERE ID=?";

	// 회원가입 .정석진
	private static final String INSERT_JOIN = "INSERT INTO MEMBER(MEMBERNUM,ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)"
			+ " VALUES((SELECT NVL(MAX(MEMBERNUM),0)+1 FROM MEMBER),?,?,?,?,TO_DATE(?, 'yyyyMMdd'),?,?,'신입')";

	// 비밀번호변경 ▶ 현재 비밀번호입력 후 확인은 SELECTONE_LOGIN으로 진행. 새비밀번호, 새비밀번호확인은 뷰에서 체크 .정석진
	private static final String UPDATE_CHANGEPW = "UPDATE MEMBER SET PW=? WHERE ID=?";

	// 닉네임 변경 ▶ 중복확인은 selectOne으로 진행 .정석진
	private static final String UPDATE_CHANGENICKNAME = "UPDATE MEMBER SET NICKNAME=? WHERE ID=?";

	// 전화번호 변경 ▶ 새롭게 입력받은 전화번호 본인인증 후 변경 .정석진
	private static final String UPDATE_CHANGEPH = "UPDATE MEMBER SET PH=? WHERE ID=?";

	private static final String UPDATE_CHANGEPROFILE = "UPDATE MEMBER SET PROFILE=? WHERE ID=?";

	// 회원탈퇴 ▶ GRADE '탈퇴'로 변경 .정석진
	private static final String UPDATE_DELETEACCOUNT = "UPDATE MEMBER SET GRADE='탈퇴' WHERE ID=?";

	// [관리자용] 신고당한 회원 상태 변경 .정석진
	private static final String UPDATE_ACCOUNTLOCK = "UPDATE MEMBER SET GRADE=? WHERE ID=?";

	// 회원DB삭제 ▶ 일정식잔 지나면 DB에서 회원삭제 .정석진
	private static final String DELETE = "DELETE FROM MEMBER WHERE MEMBERNUM=?";

	public MemberDTO selectOne(MemberDTO memberDTO) {
		MemberDTO data = null;
		conn = JDBCUtil.connect();

		if (memberDTO.getSearchCondition().equals("아이디찾기")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_FINDID);
				pstmt.setString(1, memberDTO.getName());
				pstmt.setString(2, memberDTO.getPh());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setMemberID(rs.getString("ID"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("비밀번호찾기")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_FINDPW);
				pstmt.setString(1, memberDTO.getMemberID());
				pstmt.setString(2, memberDTO.getPh());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setMemberPW(rs.getString("PW"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("로그인")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_LOGIN);
				pstmt.setString(1, memberDTO.getMemberID());
				pstmt.setString(2, memberDTO.getMemberPW());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setMemberID(rs.getString("ID"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("정보출력")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_MEMBERINFO);
				pstmt.setString(1, memberDTO.getMemberID());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setMemberNum(rs.getInt("MEMBERNUM"));
					data.setMemberID(rs.getString("ID"));
					data.setMemberPW(rs.getString("PW"));
					data.setName(rs.getString("NAME"));
					data.setNickname(rs.getString("NICKNAME"));
					data.setBirth(rs.getString("BIRTH_DATE"));
					data.setPh(rs.getString("PH"));
					data.setProfile(rs.getString("PROFILE"));
					data.setGrade(rs.getString("GRADE"));
					data.setBoardNum(rs.getInt("BOARDNUM"));

				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}

		return data;
	}

	public boolean insert(MemberDTO memberDTO) {
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(INSERT_JOIN);
			pstmt.setString(1, memberDTO.getMemberID());
			pstmt.setString(2, memberDTO.getMemberPW());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setString(4, memberDTO.getNickname());
			pstmt.setString(5, memberDTO.getBirth());
			pstmt.setString(6, memberDTO.getPh());
			pstmt.setString(7, memberDTO.getProfile());
			pstmt.setString(8, memberDTO.getGrade());

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

	public boolean update(MemberDTO memberDTO) {
		conn = JDBCUtil.connect();

		if (memberDTO.getSearchCondition().equals("비밀번호변경")) {
			try {
				pstmt = conn.prepareStatement(UPDATE_CHANGEPW);
				pstmt.setString(1, memberDTO.getMemberPW());
				pstmt.setString(2, memberDTO.getMemberID());

				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("닉네임변경")) {
			try {
				pstmt = conn.prepareStatement(UPDATE_CHANGENICKNAME);
				pstmt.setString(1, memberDTO.getNickname());
				pstmt.setString(2, memberDTO.getMemberID());

				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("전화번호변경")) {
			try {
				pstmt = conn.prepareStatement(UPDATE_CHANGENICKNAME);
				pstmt.setString(1, memberDTO.getPh());
				pstmt.setString(2, memberDTO.getMemberID());

				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("프로필변경")) {
			try {
				pstmt = conn.prepareStatement(UPDATE_CHANGEPROFILE);
				pstmt.setString(1, memberDTO.getProfile());
				pstmt.setString(2, memberDTO.getMemberID());

				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("회원탈퇴")) {
			try {
				pstmt = conn.prepareStatement(UPDATE_DELETEACCOUNT);
				pstmt.setString(1, memberDTO.getMemberID());

				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("회원상태변경")) {
			try {
				pstmt = conn.prepareStatement(UPDATE_DELETEACCOUNT);
				pstmt.setString(1, memberDTO.getGrade());
				pstmt.setString(2, memberDTO.getMemberID());

				int result = pstmt.executeUpdate();
				if (result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		return true;
	}

	public boolean delete(MemberDTO memberDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, memberDTO.getMemberNum());

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
