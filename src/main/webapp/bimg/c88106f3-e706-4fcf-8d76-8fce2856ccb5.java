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

public class MyBoardSelectAllPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		forward.setPath("board/myBoardSelectAllPage.jsp");
		forward.setRedirect(false);

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		memberDTO.setSearchCondition("정보출력");
		HttpSession session = request.getSession();
		memberDTO.setMemberID((String)session.getAttribute("member"));
		memberDTO=memberDAO.selectOne(memberDTO);
		boardDTO.setCategory("내가 작성한 글"); // 카테고리 세팅 안하면 오류남! else문으로 가기위한 더미값 입력
		boardDTO.setSearchCondision("작성자");
		boardDTO.setNickname(memberDTO.getNickname());
		ArrayList<BoardDTO> boardDatas=boardDAO.selectAll(boardDTO);
	
		request.setAttribute("boardDatas", boardDatas);
		
		

		return forward;
	}

}
