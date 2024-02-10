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
		
		
		forward.setRedirect(false);
		
		ReviewDAO reviewDAO = new ReviewDAO();
		ReviewDTO reviewDTO = new ReviewDTO();
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();

		// 세션에 있는 정보를 가져오기 위해 HttpSession 인터페이스를 사용

		HttpSession session = request.getSession();

		// 현재 로그인한 유저의 ID와 request 객체에 있는 Parameter를 가져옴
		// Parameter에는 게시글 번호(boardNum)와 댓글 내용(reviewContents)이 존재

		reviewDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		reviewDTO.setId((String) session.getAttribute("member"));
		reviewDTO.setReviewContents(request.getParameter("reviewContents"));

		boolean flag = reviewDAO.insert(reviewDTO);
		
		// 댓글 추가 실행 후 결과를 boolean타입으로 받아서 로그 확인
		if (flag) {
			System.out.println("[로그] 댓글 작성 성공");
		} else {
			System.out.println("[로그] 댓글 작성 실패");
		}

		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO.setCategory(request.getParameter("category"));
		boardDTO.setUpdatePage("");

		boardDAO.selectOne(boardDTO);
		
		System.out.println("[로그] boardNum : " + boardDTO.getBoardNum());
		
		String category = boardDTO.getCategory();
		
		if(category.equals("자유게시판")) {
			forward.setPath("freeBoardSelectOnePage.do");
		}
		else if(category.equals("판매게시판")) {
			forward.setPath("sellBoardSelectOnePage.do");
		}
		else if(category.equals("리뷰게시판")) {
			forward.setPath("cameraReviewBoardSelectOnePage.do");
		}
		else {
			forward.setPath("errorPage.do");
		}

		// 로그 작성자 | 김성민
//		System.out.println("[로그]" + reviewDTO.getBoardNum());
//		System.out.println("[로그]" + reviewDTO.getId());
//		System.out.println("[로그]" + reviewDTO.getReviewContents());

		

		return forward;
	}

}
