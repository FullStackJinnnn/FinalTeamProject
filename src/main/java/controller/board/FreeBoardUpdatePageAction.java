package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class FreeBoardUpdatePageAction implements Action {

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
