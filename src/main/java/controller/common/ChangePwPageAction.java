package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;

public class ChangePwPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		forward.setPath("/chalKag/common/changePwPage.jsp");
		forward.setRedirect(false);
		request.setAttribute("id", request.getParameter("id"));

		return forward;
	}

}
