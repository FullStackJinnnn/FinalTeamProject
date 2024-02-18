package controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;
import model.review.ReviewDAO;
import model.review.ReviewDTO;

public class ReviewDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		System.out.println("[로그] ReviewDeleteAction 접근 ");
		
		forward.setRedirect(false);
		
		ReviewDTO reviewDTO = new ReviewDTO();
		ReviewDAO reviewDAO = new ReviewDAO();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();

		
		reviewDTO.setReviewNum(Integer.parseInt(request.getParameter("reviewNum")));

		
		boolean flag = reviewDAO.delete(reviewDTO);
		// 로그 작성자 | 김성민
		if(flag) {
			System.out.println("[로그] 댓글 삭제 성공");
		}
		else {
			System.out.println("[로그] 댓글 삭제 실패");
			forward.setPath("errorPage.do");
		}
		
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO.setCategory(request.getParameter("category"));
		boardDTO.setUpdatePage("");

		boardDAO.selectOne(boardDTO);
		
		String category = boardDTO.getCategory();
		
		if(category.equals("자유게시판")) {
			forward.setPath("freeBoardSelectOnePage.do");
		}
		else if(category.equals("판매게시판")) {
			forward.setPath("sellBoardSelectOnePage.do");
		}
		else if(category.equals("리뷰게시판")) {
			forward.setPath("cameraReviewSelectOnePage.do");
		}
		else {
			forward.setPath("errorPage.do");
		}
		
		return forward;
		
	}

}
