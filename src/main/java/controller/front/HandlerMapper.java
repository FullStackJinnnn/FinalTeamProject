package controller.front;

import java.util.HashMap;
import java.util.Map;

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
import controller.board.MyBoardSelectAllPageAction;
import controller.board.SellBoardSelectAllPageAction;
import controller.board.SellBoardSelectOnePageAction;
import controller.board.SellBoardUpdateAction;
import controller.board.SellBoardUpdatePageAction;
import controller.board.SellBoardWriteAction;
import controller.board.SellBoardWritePageAction;
import controller.common.ChangePhAction;
import controller.common.ChangePwAction;
import controller.common.ChangePwPageAction;
import controller.common.CheckPwAction;
import controller.common.CheckPwPageAction;
import controller.common.DeleteAccountAction;
import controller.common.FindIdPageAction;
import controller.common.FindIdResultPageAction;
import controller.common.FindPwPageAction;
import controller.common.FindPwResultPageAction;
import controller.common.JoinAction;
import controller.common.JoinPageAction;
import controller.common.LoginAction;
import controller.common.LoginPageAction;
import controller.common.LogoutAction;
import controller.common.MainAction;
import controller.common.MemberPageAction;
import controller.common.MyPageAction;
import controller.error.BackPageAction;
import controller.error.ErrorPageAction;
import controller.recommend.RecommendDownAction;
import controller.recommend.RecommendUpAction;
import controller.report.ReportSelectAllPageAction;
import controller.report.ReportWriteAction;
import controller.report.ReportWritePageAction;
import controller.review.ReviewDeleteAction;
import controller.review.ReviewUpdateAction;
import controller.review.ReviewWriteAction;

public class HandlerMapper {

	private Map<String, Action> handlerMap;

	public HandlerMapper() {
		this.handlerMap = new HashMap<String, Action>();

		System.out.println(this.handlerMap);

		handlerMap.put("/main.do", new MainAction());
		handlerMap.put("/joinPage.do", new JoinPageAction());
		handlerMap.put("/join.do", new JoinAction());
		handlerMap.put("/loginPage.do", new LoginPageAction());
		handlerMap.put("/login.do", new LoginAction());
		handlerMap.put("/logout.do", new LogoutAction());
		handlerMap.put("/findIdPage.do", new FindIdPageAction());
		handlerMap.put("/findPwPage.do", new FindPwPageAction());
		handlerMap.put("/changePwPage.do", new ChangePwPageAction());
		handlerMap.put("/changePw.do", new ChangePwAction());
		handlerMap.put("/checkPwPage.do", new CheckPwPageAction());
		handlerMap.put("/checkPw.do", new CheckPwAction());
		handlerMap.put("/myPage.do", new MyPageAction());
		handlerMap.put("/memberPage.do", new MemberPageAction());
		handlerMap.put("/changePh.do", new ChangePhAction());
		handlerMap.put("/myboardSelectAllPage.do", new MyBoardSelectAllPageAction());
		handlerMap.put("/deleteAccount.do", new DeleteAccountAction());
		handlerMap.put("/memberBoardSelectAllPage.do", new MemberBoardSelectAllPageAction());
		handlerMap.put("/sellBoardSelectAllPage.do", new SellBoardSelectAllPageAction());
		handlerMap.put("/sellBoardSelectOnePage.do", new SellBoardSelectOnePageAction());
		handlerMap.put("/cameraReviewSelectAllPage.do", new CameraReviewSelectAllPageAction());
		handlerMap.put("/cameraReviewSelctOnePage.do", new CameraReviewSelctOnePageAction());
		handlerMap.put("/freeBoardSelectAllPage.do", new FreeBoardSelectAllPageAction());
		handlerMap.put("/freeBoardSelectOnePage.do", new FreeBoardSelectOnePageAction());
		handlerMap.put("/reviewWrite.do", new ReviewWriteAction());
		handlerMap.put("/reviewUpdate.do", new ReviewUpdateAction());
		handlerMap.put("/reviewDelete.do", new ReviewDeleteAction());
		handlerMap.put("/sellBoardWritePage.do", new SellBoardWritePageAction());
		handlerMap.put("/sellBoardWrite.do", new SellBoardWriteAction());
		handlerMap.put("/sellBoardUpdatePage.do", new SellBoardUpdatePageAction());
		handlerMap.put("/sellBoardUpdate.do", new SellBoardUpdateAction());
		handlerMap.put("/cameraReviewWritePage.do", new CameraReviewWritePageAction());
		handlerMap.put("/cameraReviewWrite.do", new CameraReviewWriteAction());
		handlerMap.put("/cameraReviewUpdatePage.do", new CameraReviewUpdatePageAction());
		handlerMap.put("/cameraReviewUpdate.do", new CameraReviewUpdateAction());
		handlerMap.put("/freeBoardWritePage.do", new FreeBoardWritePageAction());
		handlerMap.put("/freeBoardWrite.do", new FreeBoardWriteAction());
		handlerMap.put("/freeBoardUpdatePage.do", new FreeBoardUpdatePageAction());
		handlerMap.put("/freeBoardUpdate.do", new FreeBoardUpdateAction());
		handlerMap.put("/reportWritePage.do", new ReportWritePageAction());
		handlerMap.put("/reportWrite.do", new ReportWriteAction());
		handlerMap.put("/reportManagePage.do", new ReportSelectAllPageAction());
		handlerMap.put("/recommenUp.do", new RecommendUpAction());
		handlerMap.put("/recommenDown.do", new RecommendDownAction());
		handlerMap.put("/errorPage.do", new ErrorPageAction());
		handlerMap.put("/backPage.do", new BackPageAction());
		handlerMap.put("/findIdResultPage.do", new FindIdResultPageAction());
		handlerMap.put("/findPwResultPage.do", new FindPwResultPageAction());

	}

	public Action getAction(String commend) {
		return handlerMap.get(commend);
	}

}
