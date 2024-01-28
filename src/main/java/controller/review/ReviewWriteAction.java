package controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.review.ReviewDAO;
import model.review.ReviewDTO;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		request.setCharacterEncoding("UTF-8");

		ReviewDAO reviewDAO = new ReviewDAO();
		ReviewDTO reviewDTO = new ReviewDTO();

		HttpSession session = request.getSession();

		reviewDTO.setBoardNum((Integer) request.getAttribute("boardNum"));
		reviewDTO.setId((String)session.getAttribute("member"));
		reviewDTO.setReviewContents(request.getParameter("reviewContents"));

		boolean flag = reviewDAO.insert(reviewDTO);

		if (flag) {

			forward.setPath("main.do");
			forward.setRedirect(true);

		} else {

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "댓글작성실패!");

		}

		return forward;
	}

}
