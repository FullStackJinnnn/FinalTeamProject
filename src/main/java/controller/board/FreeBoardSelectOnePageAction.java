package controller.board;

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

public class FreeBoardSelectOnePageAction implements Action { // 자유게시판 상세보기 페이지로 이동

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		forward.setPath("/board/freeBoardSelectOnePage.jsp");
		forward.setRedirect(false);

		// 필요한 model
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();
		ReviewDTO reviewDTO = new ReviewDTO();
		ReviewDAO reviewDAO = new ReviewDAO();

		// boardNum을 인자로 받아옴
		boardDTO.setCategory("자유게시판");
		boardDTO.setUpdatePage("조회수증가");
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));

		// System.out.println("[로그]" + boardDTO.getBoardNum());

		// 해당하는 객체를 boardDTO에 저장
		boardDTO = boardDAO.selectOne(boardDTO);
		// System.out.println("보드 번호" + boardDTO);
		// System.out.println("[로그]: FreeBoardSelectOnePageAction.java 01");

		// boardDTO의 boardNum을 이용해서 reviewDTO의 BoardNum을 설정
		reviewDTO.setBoardNum(boardDTO.getBoardNum());
		// System.out.println("댓글의 보드 번호" + reviewDTO.getBoardNum());
		// System.out.println("[로그]: FreeBoardSelectOnePageAction.java 02");

		// 해당 게시글의 댓글 전체를 배열에 저장
		// System.out.println("[로그]" + reviewDTO.getBoardNum());

		ArrayList<ReviewDTO> reviews = reviewDAO.selectAll(reviewDTO);
		// System.out.println(reviews.size());
		// System.out.println("[로그]: FreeBoardSelectOnePageAction.java 03");

//		for(int i = 0 ; i < reviews.size() ; i++) {
//			System.out.println("[로그]" + reviews.get(i).getReviewContents());
//			
//		}

		// 절대 경로를 상대경로로 치환하기 위한 로직_ 2024.01.31_김도연
		String prefix = "D:\\PLZJUN\\workspace_infinityStone\\chalKag\\src\\main\\webapp\\bimg\\";
		String relativePath = boardDTO.getImage().replace(prefix, ""); // 절대경로를 bimg/이미지.확장자로 줄인다.
		boardDTO.setImage(relativePath); // 상대 경로로 변경된 주소를 image에 저장한 뒤에 V로 전달한다.
		// System.out.println("상대경로 확인용 : " + relativePath);
		// 게시글 정보와 해당 게시글의 댓글들을 전송
		request.setAttribute("board", boardDTO);
		request.setAttribute("reviews", reviews);
		// System.out.println("[로그]: FreeBoardSelectOnePageAction.java 04");


		// System.out.println("[로그] request 완료");

		return forward;
	}

}
