package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class SellBoardUpdatePageAction implements Action { // 카메라 판매글 수정 페이지로 이동

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();

		boardDTO.setBoardNum((Integer) request.getAttribute("boardNum"));
		boardDAO.selectOne(boardDTO);
		request.setAttribute("boardSelectOne", boardDTO);

		forward.setPath("board/sellBoardUpdatePage.jsp");
		forward.setRedirect(false);

		return forward;

	}
}

// V 보드셀렉트원 >> 뭔가 보내줄수있음
// C 1. 내가 보드셀렉트원의 모든 정보를 그대로받는방법

//   2. DAO.selectOne()을 내가 쓸수있음
//     결론 bid만 받음

// V 보드업데이트페이지로 보내줄예정