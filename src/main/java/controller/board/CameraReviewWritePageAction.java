package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;

public class CameraReviewWritePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();

		if (session.getAttribute("member") == null) {
			forward.setPath("/chalKag/loginPage.do");
			forward.setRedirect(true);
		} else {
			forward.setPath("/chalKag/board/cameraReviewWritePage.jsp");
			forward.setRedirect(true);
		}
		return forward;
	}

}
