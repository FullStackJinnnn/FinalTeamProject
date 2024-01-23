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
import model.member.MemberDAO;
import model.member.MemberDTO;

public class MemberBoardSelectOnePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		forward.setPath("board/myBoardSelectOnePage.jsp");
		forward.setRedirect(false);

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO.setCategory("내 게시글 자세히 출력");
		boardDTO=boardDAO.selectOne(boardDTO);
		if (boardDTO != null) {
			request.setAttribute("boardDTO", boardDTO);
		} else {
			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);
			request.setAttribute("msg", "로그인 실패!");
		}
		

		return forward;
	}
 
}
