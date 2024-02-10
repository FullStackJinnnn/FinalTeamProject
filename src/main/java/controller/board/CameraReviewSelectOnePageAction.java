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

public class CameraReviewSelectOnePageAction implements Action { // 카메라 리뷰글 상세보기 페이지로 이동

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session= request.getSession();
		System.out.println("[로그] 리뷰selectOne 진입!");
		// System.out.println("로그 : 컨트롤러 접근");
		// 카테고리에 맞는 페이지로 Path 설정		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board/cameraReviewSelectOnePage.jsp");
		
		// 필요한 model
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		RecommendDTO recommendDTO = new RecommendDTO();
		RecommendDAO recommendDAO = new RecommendDAO();
		ReviewDTO reviewDTO = new ReviewDTO();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		
		String memberId = (String)session.getAttribute("member");
		// boardNum을 인자로 받아옴
	 	boardDTO.setCategory("리뷰게시판");
		boardDTO.setUpdatePage("조회수증가");
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		System.out.println(boardDTO.getBoardNum());
		
		recommendDTO.setBoardNum(boardDTO.getBoardNum());
		if(memberId == null) {
			recommendDTO.setId("");
		} else {
			recommendDTO.setId(memberId);
		}
		recommendDTO = recommendDAO.selectOne(recommendDTO);
		// System.out.println("[로그]" + boardDTO.getBoardNum());
		System.out.println("[로그] 리뷰selectOne 진입!");
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
		
//		// 절대 경로를 상대경로로 치환하기 위한 로직_ 2024.01.31_김도연
//		String prefix = "D:\\PLZJUN\\workspace_infinityStone\\chalKag\\src\\main\\webapp\\bimg\\";
//		String relativePath = boardDTO.getImage().replace(prefix, ""); // 절대경로를 bimg/이미지.확장자로 줄인다.
//		boardDTO.setImage(relativePath); // 상대 경로로 변경된 주소를 image에 저장한 뒤에 V로 전달한다.
//		System.out.println("상대경로 확인용 : " + relativePath);
//		
		// 게시글 정보와 해당 게시글의 댓글들을 전송
		request.setAttribute("reviewDatas", reviewDatas);
		request.setAttribute("recommendData", recommendDTO);
		request.setAttribute("boardData", boardDTO);
		
		// System.out.println("[로그] request 완료");
		
		return forward;
	}

}
