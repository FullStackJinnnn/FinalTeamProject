package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;
import model.recommend.RecommendDAO;
import model.recommend.RecommendDTO;
import model.review.ReviewDAO;
import model.review.ReviewDTO;

public class SellBoardSelectOnePageAction implements Action { // 카메라 판매글 상세보기 페이지로 이동

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board/sellBoardSelectOnePage.jsp");

		// 필요한 model
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		RecommendDTO recommendDTO = new RecommendDTO();
		RecommendDAO recommendDAO = new RecommendDAO();
		ReviewDTO reviewDTO = new ReviewDTO();
		ReviewDAO reviewDAO = new ReviewDAO();

		String memberId = (String)session.getAttribute("member");
		// boardNum을 인자로 받아옴
		boardDTO.setCategory("");
		boardDTO.setUpdatePage("조회수증가");
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));   
		
		recommendDTO.setBoardNum(boardDTO.getBoardNum());
		if(memberId == null) {
			recommendDTO.setId("");
		} else {
			recommendDTO.setId(memberId);
		}
		recommendDTO = recommendDAO.selectOne(recommendDTO);

		// System.out.println("[로그]" + boardDTO.getBoardNum());

		// 해당하는 객체를 boardDTO에 저장
		boardDTO = boardDAO.selectOne(boardDTO);

		// boardDTO의 boardNum을 이용해서 reviewDTO의 BoardNum을 설정
		reviewDTO.setBoardNum(boardDTO.getBoardNum());

		// 해당 게시글의 댓글 전체를 배열에 저장
		// System.out.println("[로그]" + reviewDTO.getBoardNum());

		ArrayList<ReviewDTO> reviewDatas = reviewDAO.selectAll(reviewDTO);

//		for(int i = 0 ; i < reviews.size() ; i++) {
//			System.out.println("[로그]" + reviews.get(i).getReviewContents());
//			

//		}

		// 게시글 정보와 해당 게시글의 댓글들을 전송
		request.setAttribute("reviewDatas", reviewDatas);
		request.setAttribute("recommendData", recommendDTO);
		request.setAttribute("boardData", boardDTO);

		// System.out.println("[로그] request 완료");

		return forward;
	}

}
