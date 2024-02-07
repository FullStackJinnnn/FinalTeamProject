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
import model.member.MemberDAO;
import model.member.MemberDTO;

public class MemberBoardSelectAllPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		memberDTO.setNickname(request.getParameter("nickname"));
		memberDTO.setSearchCondition("유저정보출력");
		memberDTO = memberDAO.selectOne(memberDTO);
		boardDTO.setCategory(""); // 카테고리 세팅 안하면 오류남! else문으로 가기위한 더미값 입력
		boardDTO.setSearchCondition("유저보드");
		boardDTO.setId(memberDTO.getId());

		ArrayList<BoardDTO> boardDatas = boardDAO.selectAll(boardDTO);

		if (boardDatas != null) {

			request.setAttribute("boardDatas", boardDatas);
			forward.setPath("/chalKag/board/myBoardSelectAllPage.jsp");
			forward.setRedirect(false);

		} else {

			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);

		}

		return forward;

	}
}
