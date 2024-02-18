package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class MyBoardSelectAllPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setCategory(""); // 카테고리 세팅 안하면 오류남! else문으로 가기위한 더미값 입력
		boardDTO.setSearchCondition("유저보드");
		boardDTO.setId((String)session.getAttribute("member"));
		ArrayList<BoardDTO> boardDatas = boardDAO.selectAll(boardDTO);
		if (boardDatas != null) {
			//request.setAttribute("boardDatas", boardDatas);
			Gson gson = new Gson();
			String jsonBoardDatas = gson.toJson(boardDatas);
			request.setAttribute("jsonBoardDatas", jsonBoardDatas);
			request.setAttribute("id",boardDTO.getId());
			forward.setPath("board/myBoardSelectAllPage.jsp");
			forward.setRedirect(false);
		} else {
			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);
		}

		return forward;
	}

}
