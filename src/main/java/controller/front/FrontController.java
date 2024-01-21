package controller.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.board.CameraReviewSelctOnePageAction;
import controller.board.CameraReviewSelectAllPageAction;
import controller.board.CameraReviewUpdateAction;
import controller.board.CameraReviewUpdatePageAction;
import controller.board.CameraReviewWriteAction;
import controller.board.CameraReviewWritePageAction;
import controller.board.FreeBoardSelectAllPageAction;
import controller.board.FreeBoardSelectOnePageAction;
import controller.board.FreeBoardUpdateAction;
import controller.board.FreeBoardUpdatePageAction;
import controller.board.FreeBoardWriteAction;
import controller.board.FreeBoardWritePageAction;
import controller.board.MemberBoardSelectAllPageAction;
import controller.board.MemberBoardSelectOnePageAction;
import controller.board.MyBoardSelectAllPageAction;
import controller.board.MyBoardSelectOnePageAction;
import controller.board.SellBoardSelectAllPageAction;
import controller.board.SellBoardSelectOnePageAction;
import controller.board.SellBoardUpdateAction;
import controller.board.SellBoardUpdatePageAction;
import controller.board.SellBoardWriteAction;
import controller.board.SellBoardWritePageAction;
import controller.common.ChangeNickNameAction;
import controller.common.ChangePhAction;
import controller.common.ChangePwAction;
import controller.common.ChangePwPageAction;
import controller.common.CheckPwAction;
import controller.common.CheckPwPageAction;
import controller.common.DeleteAccountAction;
import controller.common.FindIdAction;
import controller.common.FindIdPageAction;
import controller.common.FindPwAction;
import controller.common.FindPwPageAction;
import controller.common.JoinAction;
import controller.common.JoinPageAction;
import controller.common.LoginAction;
import controller.common.LoginPageAction;
import controller.common.LogoutAction;
import controller.common.MainAction;
import controller.common.MemberPageAction;
import controller.common.MyPageAction;
import controller.common.ProfileUploadAction;
import controller.error.ErrorPageAction;
import controller.report.ReportManageAction;
import controller.report.ReportManagePageAction;
import controller.report.ReportWriteAction;
import controller.report.ReportWritePageAction;
import controller.review.ReviewDeleteAction;
import controller.review.ReviewUpdateAction;
import controller.review.ReviewWriteAction;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MainAction mainAction;
	private JoinPageAction joinPageAction;
	private JoinAction joinAction;
	private LoginPageAction loginPageAction;
	private LoginAction loginAction;
	private LogoutAction logoutAction;
	private FindIdPageAction findIdPageAction;
	private FindIdAction findIdAction;
	private FindPwPageAction findPwPageAction;
	private FindPwAction findPwAction;
	private ChangePwPageAction changePwPageAction;
	private ChangePwAction changePwAction;
	private CheckPwPageAction checkPwPageAction;
	private CheckPwAction checkPwAction;
	private MyPageAction myPagePageAction;
	private MemberPageAction memberPageAction;
	private ChangeNickNameAction changeNickNameAction;
	private ChangePhAction changePhAction;
	private MyBoardSelectAllPageAction myboardSelectAllPageAction;
	private MyBoardSelectOnePageAction myBoardSelectOnePageAction;
	private DeleteAccountAction deleteAccountAction;
	private MemberBoardSelectAllPageAction memberBoardSelectAllPageAction;
	private MemberBoardSelectOnePageAction memberBoardSelectOnePageAction;
	private SellBoardSelectAllPageAction sellBoardSelectAllPageAction;
	private SellBoardSelectOnePageAction sellBoardSelectOnePageAction;
	private CameraReviewSelectAllPageAction cameraReviewSelectAllPageAction;
	private CameraReviewSelctOnePageAction cameraReviewSelctOnePageAction;
	private FreeBoardSelectAllPageAction freeBoardSelectAllPageAction;
	private FreeBoardSelectOnePageAction freeBoardSelectOnePageAction;
	private ReviewWriteAction reviewWriteAction;
	private ReviewUpdateAction reviewUpdateAction;
	private ReviewDeleteAction reviewDeleteAction;
	private SellBoardWritePageAction sellBoardWritePageAction;
	private SellBoardWriteAction sellBoardWriteAction;
	private SellBoardUpdatePageAction sellBoardUpdatePageAction;
	private SellBoardUpdateAction sellBoardUpdateAction;
	private CameraReviewWritePageAction cameraReviewWritePageAction;
	private CameraReviewWriteAction cameraReviewWriteAction;
	private CameraReviewUpdatePageAction cameraReviewUpdatePageAction;
	private CameraReviewUpdateAction cameraReviewUpdateAction;
	private FreeBoardWritePageAction freeBoardWritePageAction;
	private FreeBoardWriteAction freeBoardWriteAction;
	private FreeBoardUpdatePageAction freeBoardUpdatePageAction;
	private FreeBoardUpdateAction freeBoardUpdateAction;
	private ReportWritePageAction reportWritePageAction;
	private ReportWriteAction reportWriteAction;
	private ReportManagePageAction reportManagePageAction;
	private ReportManageAction reportManageAction;
	private ErrorPageAction errorPageAction;
//	private RecommendUp recommendUp;
//	private RecommendDown recommendDown;
	private ProfileUploadAction profileUploadAction;

	public FrontController() {
		super();

		mainAction = new MainAction();
		joinPageAction = new JoinPageAction();
		joinAction = new JoinAction();
		loginPageAction = new LoginPageAction();
		loginAction = new LoginAction();
		logoutAction = new LogoutAction();
		findIdPageAction = new FindIdPageAction();
		findIdAction = new FindIdAction();
		findPwPageAction = new FindPwPageAction();
		findPwAction = new FindPwAction();
		changePwPageAction = new ChangePwPageAction();
		changePwAction = new ChangePwAction();
		checkPwPageAction = new CheckPwPageAction();
		checkPwAction = new CheckPwAction();
		myPagePageAction = new MyPageAction();
		memberPageAction = new MemberPageAction();
		changeNickNameAction = new ChangeNickNameAction();
		changePhAction = new ChangePhAction();
		myboardSelectAllPageAction = new MyBoardSelectAllPageAction();
		myBoardSelectOnePageAction = new MyBoardSelectOnePageAction();
		deleteAccountAction = new DeleteAccountAction();
		memberBoardSelectAllPageAction = new MemberBoardSelectAllPageAction();
		memberBoardSelectOnePageAction = new MemberBoardSelectOnePageAction();
		sellBoardSelectAllPageAction = new SellBoardSelectAllPageAction();
		sellBoardSelectOnePageAction = new SellBoardSelectOnePageAction();
		cameraReviewSelectAllPageAction = new CameraReviewSelectAllPageAction();
		cameraReviewSelctOnePageAction = new CameraReviewSelctOnePageAction();
		freeBoardSelectAllPageAction = new FreeBoardSelectAllPageAction();
		freeBoardSelectOnePageAction = new FreeBoardSelectOnePageAction();
		reviewWriteAction = new ReviewWriteAction();
		reviewUpdateAction = new ReviewUpdateAction();
		reviewDeleteAction = new ReviewDeleteAction();
		sellBoardWritePageAction = new SellBoardWritePageAction();
		sellBoardWriteAction = new SellBoardWriteAction();
		sellBoardUpdatePageAction = new SellBoardUpdatePageAction();
		sellBoardUpdateAction = new SellBoardUpdateAction();
		cameraReviewWritePageAction = new CameraReviewWritePageAction();
		cameraReviewWriteAction = new CameraReviewWriteAction();
		cameraReviewUpdatePageAction = new CameraReviewUpdatePageAction();
		cameraReviewUpdateAction = new CameraReviewUpdateAction();
		freeBoardWritePageAction = new FreeBoardWritePageAction();
		freeBoardWriteAction = new FreeBoardWriteAction();
		freeBoardUpdatePageAction = new FreeBoardUpdatePageAction();
		freeBoardUpdateAction = new FreeBoardUpdateAction();
		reportWritePageAction = new ReportWritePageAction();
		reportWriteAction = new ReportWriteAction();
		reportManagePageAction = new ReportManagePageAction();
		reportManageAction = new ReportManageAction();
		errorPageAction = new ErrorPageAction();
//		recommendUp = new RecommendUp();
//		recommendDown = new RecommendDown();
		profileUploadAction = new ProfileUploadAction();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cp = request.getContextPath();

		System.out.println(uri);
		System.out.println(cp);

		String action = uri.substring(cp.length());

		System.out.println("[로그]" + uri);

		ActionForward forward = null;
		if (action.equals("/main.do")) { // 메인페이지로 이동

			forward = mainAction.execute(request, response);

		} else if (action.equals("/joinPage.do")) { // 회원가입 페이지로 이동

			forward = joinPageAction.execute(request, response);

		} else if (action.equals("/join.do")) { // 회원가입

			forward = joinAction.execute(request, response);

		} else if (action.equals("/loginPage.do")) { // 로그인 페이지로 이동

			forward = loginPageAction.execute(request, response);
		} else if (action.equals("/login.do")) { // 로그인

			forward = loginAction.execute(request, response);

		} else if (action.equals("/logout.do")) { // 로그아웃

			forward = logoutAction.execute(request, response);

		} else if (action.equals("/findIdPage.do")) { // 아이디 찾기 페이지로 이동

			forward = findIdPageAction.execute(request, response);

		} else if (action.equals("/findId.do")) { // 아이디 찾기

			forward = findIdAction.execute(request, response);

		} else if (action.equals("/findPwPage.do")) { // 비밀번호 찾기 페이지로 이동

			forward = findPwPageAction.execute(request, response);

		} else if (action.equals("/findPw.do")) { // 비밀번호 찾기

			forward = findPwAction.execute(request, response);

		} else if (action.equals("/changePwPage.do")) { // 비밀번호 변경 페이지로 이동

			forward = changePwPageAction.execute(request, response);

		} else if (action.equals("/changePw.do")) { // 비밀번호 변경

			forward = changePwAction.execute(request, response);

		} else if (action.equals("/checkPwPage.do")) { // 비밀번호 확인 페이지로 이동

			forward = checkPwPageAction.execute(request, response);

		} else if (action.equals("/checkPw.do")) { // 비밀번호 확인

			forward = checkPwAction.execute(request, response);

		} else if (action.equals("/myPage.do")) { // 마이페이지로 이동

			forward = myPagePageAction.execute(request, response);

		} else if (action.equals("/memberPage.do")) { // 유저 페이지로 이동

			forward = memberPageAction.execute(request, response);

		} else if (action.equals("/changeNickname.do")) { // 닉네임 변경

			forward = changeNickNameAction.execute(request, response);

		} else if (action.equals("/changePh.do")) { // 전화번호 변경

			forward = changePhAction.execute(request, response);

		} else if (action.equals("/myBoardSelectAllPage.do")) { // 내가 작성한 글 목록보기 페이지로 이동

			forward = myboardSelectAllPageAction.execute(request, response);

		} else if (action.equals("/myBoardSelectOnePage.do")) { // 내가 작성한 글 상세보기 페이지로 이동

			forward = myBoardSelectOnePageAction.execute(request, response);

		} else if (action.equals("/deleteAccount.do")) { // 회원 탈퇴

			forward = deleteAccountAction.execute(request, response);

		} else if (action.equals("/memberBoardSelectAllPage.do")) { // 유저가 작성한 글 목록보기 페이지로 이동

			forward = memberBoardSelectAllPageAction.execute(request, response);

		} else if (action.equals("/memberBoardSelectOnePage.do")) { // 유저가 작성한 글 상세보기 페이지로 이동

			forward = memberBoardSelectOnePageAction.execute(request, response);

		} else if (action.equals("/sellBoardSelectAllPage.do")) { // 카메라 판매글 카테고리 페이지로 이동

			forward = sellBoardSelectAllPageAction.execute(request, response);

		} else if (action.equals("/sellBoardSelectOnePage.do")) { // 카메라 판매글 상세보기 페이지로 이동

			forward = sellBoardSelectOnePageAction.execute(request, response);

		} else if (action.equals("/cameraReviewSelectAllPage.do")) { // 카메라 리뷰글 카테고리 페이지로 이동

			forward = cameraReviewSelectAllPageAction.execute(request, response);

		} else if (action.equals("/cameraReviewSelectOnePage.do")) { // 카메라 리뷰글 상세보기 페이지로 이동

			forward = cameraReviewSelctOnePageAction.execute(request, response);

		} else if (action.equals("/freeBoardSelectAllPage.do")) { // 자유게시판 카테고리 페이지로 이동

			forward = freeBoardSelectAllPageAction.execute(request, response);

		} else if (action.equals("/freeBoardSelectOnePage.do")) { // 자유게시판 상세보기 페이지로 이동

			forward = freeBoardSelectOnePageAction.execute(request, response);

		} else if (action.equals("/reviewWrite.do")) { // 댓글 입력

			forward = reviewWriteAction.execute(request, response);

		} else if (action.equals("/reviewUpdate.do")) { // 댓글 수정

			forward = reviewUpdateAction.execute(request, response);

		} else if (action.equals("/reviewDelete.do")) { // 댓글 삭제

			forward = reviewDeleteAction.execute(request, response);

		} else if (action.equals("/sellBoardWritePage.do")) { // 카매라 판매글 작성 페이지로 이동

			forward = sellBoardWritePageAction.execute(request, response);

		} else if (action.equals("/sellBoardWrite.do")) { // 카메라 판매글 작성

			forward = sellBoardWriteAction.execute(request, response);

		} else if (action.equals("/sellBoardUpdatePage.do")) { // 카메라 판매글 수정 페이지로 이동

			forward = sellBoardUpdatePageAction.execute(request, response);

		} else if (action.equals("/sellBoardUpdate.do")) { // 카메라 판매글 수정

			forward = sellBoardUpdateAction.execute(request, response);

		} else if (action.equals("/cameraReviewWritePage.do")) { // 카메라 리뷰글 작성 페이지로 이동

			forward = cameraReviewWritePageAction.execute(request, response);

		} else if (action.equals("/cameraReviewWrite.do")) { // 카메라 리뷰글 작성

			forward = cameraReviewWriteAction.execute(request, response);

		} else if (action.equals("/cameraReviewUpdatePage.do")) { // 카메라 리뷰글 수정 페이지로 이동

			forward = cameraReviewUpdatePageAction.execute(request, response);

		} else if (action.equals("/cameraReviewUpdate.do")) { // 카메라 리뷰글 수정

			forward = cameraReviewUpdateAction.execute(request, response);

		} else if (action.equals("/freeBoardWritePage.do")) { // 자유 게시판 글 작성 페이지로 이동

			forward = freeBoardWritePageAction.execute(request, response);

		} else if (action.equals("/freeBoardWrite.do")) { // 자유 게시판 글 작성

			forward = freeBoardWriteAction.execute(request, response);

		} else if (action.equals("/freeBoardUpdatePage.do")) { // 자유 게시판 글 수정 페이지로 이동

			forward = freeBoardUpdatePageAction.execute(request, response);

		} else if (action.equals("/freeBoardUpdate.do")) { // 자유 게시판 글 수정

			forward = freeBoardUpdateAction.execute(request, response);

		} else if (action.equals("/reportWritePage.do")) { // 신고 작성 페이지로 이동

			forward = reportWritePageAction.execute(request, response);

		} else if (action.equals("/reportWrite.do")) { // 신고 작성

			forward = reportWriteAction.execute(request, response);

		} else if (action.equals("/reportManagePage.do")) { // 신고 관리 페이지로 이동

			forward = reportManagePageAction.execute(request, response);

		} else if (action.equals("/reportManage.do")) { // 신고 관리

			forward = reportManageAction.execute(request, response);

		} else if (action.equals("/errorPage.do")) { // 에러 페이지로 이동

			forward = errorPageAction.execute(request, response);

//		} else if (action.equals("/recommendUp.do")) { // 추천수 증가
//
//			forward = recommendUp.execute(request, response);
//
//		} else if (action.equals("/recommendDown.do")) { // 추천수 감소
//
//			forward = recommendDown.execute(request, response);

		} else if (action.equals("/profileUpload.do")) {
			forward = profileUploadAction.execute(request, response);
		}
		

		if (forward == null) {
			forward.setPath("/errorPage.do");
			// 에러페이지 이동
		}

		if (forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}

	}

}
