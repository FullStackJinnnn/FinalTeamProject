package controller.board; 

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;

public class SellBoardSelectAllPageAction implements Action { // 카메라 판매글 카테고리 페이지로 이동

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("board/sellBoardSelectAllPage.jsp");
		forward.setRedirect(true);
		
		return forward;
	}

}
