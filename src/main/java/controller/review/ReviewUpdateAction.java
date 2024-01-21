package controller.review;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.review.ReviewDAO;
import model.review.ReviewDTO;

public class ReviewUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		request.setCharacterEncoding("UTF-8");

		ReviewDAO reviewDAO = new ReviewDAO();
		ReviewDTO reviewDTO = new ReviewDTO();

		reviewDTO.setReviewContents(request.getParameter("reviewContents"));

		boolean flag = reviewDAO.update(reviewDTO);

		if (flag) {

			forward.setPath("main.do");
			forward.setRedirect(true);

		} else {

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "댓글수정실패!");

		}

		return forward;
	}

}
