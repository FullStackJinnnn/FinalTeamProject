package controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;
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
		
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		
		boardDTO.setCategory("리뷰");
		boardDTO.setBoardNum(1);
		boardDAO.selectOne(boardDTO);
		
		String category = boardDTO.getCategory();
		

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
			return forward;
		}
		
		HttpSession session = request.getSession();
		
		// boardNum과 id는 각각 DTO와 세션에 존재하는 것이기 때문에 getAttribute로 가져옴
		// reviewContents는 사용자의 입력값이기 때문에 getParameter로 가져옴

		reviewDTO.setBoardNum(1);
		reviewDTO.setId((String)session.getAttribute("member"));
		reviewDTO.setReviewContents(request.getParameter("reviewContents"));
		
		// 로그 작성자 | 김성민
//		System.out.println("[로그]" + reviewDTO.getBoardNum());
//		System.out.println("[로그]" + reviewDTO.getId());
//		System.out.println("[로그]" + reviewDTO.getReviewContents());
		
		
		boolean flag = reviewDAO.insert(reviewDTO);
		
		if(flag) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}


		return forward;
	}

}
