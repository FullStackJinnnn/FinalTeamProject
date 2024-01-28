package controller.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;
import model.review.ReviewDAO;
import model.review.ReviewDTO;

public class ReviewPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		String category;
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		ReviewDTO reviewDTO = new ReviewDTO();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		reviewDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO.setBoardNum(reviewDTO.getBoardNum());
		boardDAO.selectOne(boardDTO);
		category = boardDTO.getCategory();
		
		
		// 카테고리에 맞는 페이지로 Path 설정
		
		if(category.equals("cameraReview")) { 
			forward.setPath("board/cameraReviewSelectOnePage.jsp");
		}
		else if(category.equals("freeboard")) {
			forward.setPath("board/freeBoardSelectOnePage.jsp");
		}
		else if(category.equals("sellBoard")) {
			forward.setPath("board/sellBoardSelectOnePage.jsp");
		}
		else {
			forward.setPath("error/errorPage.jsp");
			return forward;
		}
		
		// 해당 게시글의 댓글 전체를 배열에 저장하여 request
		
		ArrayList<ReviewDTO> reviews = reviewDAO.selectAll(reviewDTO);
		request.setAttribute("reviews", reviews);
		
		return forward;
	}

}
