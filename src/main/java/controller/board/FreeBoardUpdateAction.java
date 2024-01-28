package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class FreeBoardUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		request.setCharacterEncoding("UTF-8");

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();

		// 수정 할 보드 PK 를 받아 옴.안승준
		boardDTO.setBoardNum((Integer) request.getAttribute("boardNum"));

		// 수정 할 글 제목 사용자가 입력.안승준
		boardDTO.setTitle((String) request.getParameter("title"));

		// 수정 할 글 내용 사용자가 입력.안승준
		boardDTO.setContents((String) request.getParameter("contents"));

		// 수정 할 글 사진 사용자가 저장.안승준
		boardDTO.setImage((String) request.getParameter("image"));

		boolean flag = boardDAO.update(boardDTO);

		if (flag) { // 성공시 메인으로 이동

			forward.setPath("/main.do");
			forward.setRedirect(true);

		} else { // 실패시 alert 창으로 이동

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "게시글 수정 실패! 다시 이용해 주세요");

		}

		return forward;
	}

}
