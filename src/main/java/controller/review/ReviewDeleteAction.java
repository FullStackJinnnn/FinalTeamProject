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
		
		System.out.println("[로그] ReviewDeleteAction.do 접근 ");
		
		forward.setRedirect(false);
		
		ReviewDTO reviewDTO = new ReviewDTO();
		ReviewDAO reviewDAO = new ReviewDAO();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		
		boardDTO.setCategory("리뷰");
		boardDTO.setBoardNum(1);
		boardDAO.selectOne(boardDTO);
		
		String category = boardDTO.getCategory();
		
		reviewDTO.setReviewNum(Integer.parseInt(request.getParameter("reviewNum")));

		
		boolean flag = reviewDAO.delete(reviewDTO);
		// 로그 작성자 | 김성민
		if(flag) {
			System.out.println("[로그] 댓글 삭제 성공");
		}
		else {
//			System.out.println("[로그] 댓글 삭제 실패");
			forward.setPath("errorPage.do");
		}
		
		if(category.equals("리뷰")) { 
			forward.setPath("board/cameraReviewSelectOnePage.jsp");
		}
		else if(category.equals("자유")) {
			forward.setPath("board/freeBoardSelectOnePage.jsp");
		}
		else if(category.equals("판매")) {
			forward.setPath("board/sellBoardSelectOnePage.jsp");
		}
		else {
			forward.setPath("errorPage.do");
		}
		
		return forward;
		
	}

}
