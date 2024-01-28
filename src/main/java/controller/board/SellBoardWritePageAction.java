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

public class SellBoardWritePageAction implements Action{ // 카메라 판매글 작성 페이지로 이동

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		
		
		forward.setPath("board/sellBoardWritePage.jsp");
		forward.setRedirect(true);
		
		return forward;
	}				

}
