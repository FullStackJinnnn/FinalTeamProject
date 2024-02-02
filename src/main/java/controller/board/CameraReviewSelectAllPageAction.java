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

public class CameraReviewSelectAllPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setCategory("리뷰"); // 카테고리 세팅 안하면 오류남! else문으로 가기위한 더미값 입력
		boardDTO.setSearchCondition("");
		ArrayList<BoardDTO> boardDatas = boardDAO.selectAll(boardDTO);
		System.out.println(boardDatas);
		if (boardDatas != null) {
			// Gson 인스턴스 생성
			request.setAttribute("boardDatas", boardDatas);
			forward.setPath("board/cameraReviewSelectAllPage.jsp");
			forward.setRedirect(false);
		} else {
			forward.setPath("error/alertPage.jsp");
		}

		return forward;
	}

}
