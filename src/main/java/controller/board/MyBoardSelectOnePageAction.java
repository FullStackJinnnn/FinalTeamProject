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

public class MyBoardSelectOnePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO.setCategory(""); // 카테고리 세팅 안하면 오류남! else문으로 가기위한 더미값 입력
		boardDTO.setUpdatePage("조회수증가"); // 게시글 한개 선택해서 들어가면 조회수 증가가 되어야해서 업데이트 하고 selectOne 진행
		boardDTO=boardDAO.selectOne(boardDTO);
		System.out.println(boardDTO);
		if (boardDTO != null) {
			forward.setPath("board/myBoardSelectOnePage.jsp");
			forward.setRedirect(false);
			request.setAttribute("boardDTO", boardDTO);
		}
		else {
			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);
		}
		return forward;
	}

}
