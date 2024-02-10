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
	private static final String SELECTONE_FINDID = "SELECT ID FROM MEMBER WHERE NAME=? AND PH=?";

	// 비밀번호 찾기 ▶ 아이디, 전화번호 받아서 비밀번호 찾기 .정석진
	private static final String SELECTONE_FINDPW = "SELECT PW FROM MEMBER WHERE ID=? AND PH=?";

	// 로그인 ▶ 로그인시 세션에 memberID만 저장하기 때문에 ID만 가져옴 .정석진
	private static final String SELECTONE_LOGIN = "SELECT ID, GRADE FROM MEMBER WHERE ID=? AND PW=?";
	
	// SNS 로그인 ▶ 카카오톡, 네이버 등 API로 로그인 할 수 있는 쿼리문 .김도연
	private static final String SELECTONE_SNS_LOGIN = "SELECT ID, GRADE FROM MEMBER WHERE ID=?";

	// 내 정보 출력 및 다른 유저 정보 출력 ▶
	// 생년월일 출력 형식은 '1993-09-10' 으로 지정
	// 자신과 다른유저가 작성한 글 볼때 필요한 BOARDNUM, TITLE을 INNER JOIN으로 가져옴
	// 아이디자체가 고유값이기 때문에 아이디로 확인해서 정보 가져옴 .정석진
	private static final String SELECTONE_MYINFO = "SELECT MEMBER.ID,PW,NAME,NICKNAME, TO_CHAR(BIRTH,'YYYY-MM-DD') AS BIRTH_DATE,PH,PROFILE,GRADE, BOARDNUM"
			+ " FROM MEMBER LEFT JOIN BOARD ON MEMBER.ID = BOARD.ID WHERE MEMBER.ID=?";

	private static final String SELECTONE_MEMBERINFO = "SELECT MEMBER.ID,NICKNAME, PH, PROFILE, GRADE, BOARDNUM"
			+ " FROM MEMBER LEFT JOIN BOARD ON MEMBER.ID = BOARD.ID WHERE NICKNAME=?";

	private static final String SELECTONE_REPORT = "SELECT MEMBER.ID,NICKNAME, BOARDNUM, TITLE, REPORTCONTENTS"
			+ "FROM MEMBER INNER JOIN BOARD ON MEMEBER.ID = BOARD.ID WHERE MEMBER.ID=?";

	// 아이디 중복확인 확인용 .노승현
	private static final String SELECTONE_IDCHECK = "SELECT ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE "
			+ "FROM MEMBER WHERE ID=?";

	// 닉네임 중복확인 확인용 .노승현
	private static final String SELECTONE_NICKNAMECHECK = "SELECT ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE "
			+ "FROM MEMBER WHERE NICKNAME=?";

	// 회원가입 .정석진
	private static final String INSERT_JOIN = "INSERT INTO MEMBER(ID,PW,NAME,NICKNAME,BIRTH,PH,PROFILE,GRADE)"
			+ "VALUES(?,?,?,?,TO_DATE(?, 'yyyy-MM-dd'),?,COALESCE(?, 'default.jpg'),'신입')";

	// 비밀번호변경 ▶ 현재 비밀번호입력 후 확인은 SELECTONE_LOGIN으로 진행. 새비밀번호, 새비밀번호확인은 뷰에서 체크 .정석진
	private static final String UPDATE_CHANGEPW = "UPDATE MEMBER SET PW=? WHERE ID=?";

	// 닉네임 변경 ▶ 중복확인은 selectOne으로 진행 .정석진
	private static final String UPDATE_CHANGENICKNAME = "UPDATE MEMBER SET NICKNAME=? WHERE ID=?";

	// 전화번호 변경 ▶ 새롭게 입력받은 전화번호 본인인증 후 변경 .정석진
	private static final String UPDATE_CHANGEPH = "UPDATE MEMBER SET PH=? WHERE ID=?";

	// 프로필 이미지 변경
	private static final String UPDATE_CHANGEPROFILE = "UPDATE MEMBER SET PROFILE=? WHERE ID=?";

	// 회원탈퇴 ▶ GRADE '탈퇴'로 변경 .정석진
	private static final String UPDATE_DELETEACCOUNT = "UPDATE MEMBER SET GRADE='탈퇴' WHERE ID=?";

	// [관리자용] 신고당한 회원 상태 변경 .정석진
	private static final String UPDATE_ACCOUNTLOCK = "UPDATE MEMBER SET GRADE=? WHERE ID=?";

	// 회원DB삭제 ▶ 일정식잔 지나면 DB에서 회원삭제 .정석진
	private static final String DELETE = "DELETE FROM MEMBER WHERE ID=?";

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
					data.setId(rs.getString("ID"));
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
				pstmt.setString(1, memberDTO.getId());
				pstmt.setString(2, memberDTO.getPh());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setPw(rs.getString("PW"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("로그인")) {
			try {
				System.out.println("로그인 memberDTO"+memberDTO);
				if (memberDTO.getSnsLoginCondition().equals("SNS로그인")) {
					pstmt = conn.prepareStatement(SELECTONE_SNS_LOGIN);
					pstmt.setString(1, memberDTO.getId());
				} else {
					pstmt = conn.prepareStatement(SELECTONE_LOGIN);
					pstmt.setString(1, memberDTO.getId());
					pstmt.setString(2, memberDTO.getPw());
				}
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setId(rs.getString("ID"));
					data.setGrade(rs.getString("GRADE"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("내정보출력")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_MYINFO);
				pstmt.setString(1, memberDTO.getId());
				ResultSet rs = pstmt.executeQuery();
				System.out.println("내정보 출력memberDTO"+memberDTO);
				if (rs.next()) {
					data = new MemberDTO();
					data.setId(rs.getString("ID"));
					data.setPw(rs.getString("PW"));
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
		} else if (memberDTO.getSearchCondition().equals("유저정보출력")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_MEMBERINFO);
				pstmt.setString(1, memberDTO.getNickname());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setId(rs.getString("ID"));
					data.setNickname(rs.getString("NICKNAME"));
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
		} else if (memberDTO.getSearchCondition().equals("ID중복검사")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_IDCHECK);
				pstmt.setString(1, memberDTO.getId());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setId(rs.getString("ID"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		} else if (memberDTO.getSearchCondition().equals("닉네임중복검사")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_NICKNAMECHECK);
				pstmt.setString(1, memberDTO.getNickname());
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					data = new MemberDTO();
					data.setNickname(rs.getString("NICKNAME"));
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
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setString(4, memberDTO.getNickname());
			pstmt.setString(5, memberDTO.getBirth());
			pstmt.setString(6, memberDTO.getPh());
			// 만약 memberDTO.getProfile()이 null이거나 비어 있다면 기본 값 'default_profile'을 사용하여 프로필
			// 매개변수 설정
			pstmt.setString(7,
					(memberDTO.getProfile() != null && !memberDTO.getProfile().isEmpty()) ? memberDTO.getProfile()
							: "default.jpg");

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
				pstmt.setString(1, memberDTO.getPw());
				pstmt.setString(2, memberDTO.getId());

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
				pstmt.setString(2, memberDTO.getId());

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
				pstmt.setString(2, memberDTO.getId());

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
				pstmt.setString(2, memberDTO.getId());

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
				pstmt.setString(1, memberDTO.getId());

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
				pstmt.setString(2, memberDTO.getId());

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
			pstmt.setString(1, memberDTO.getPw());

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
