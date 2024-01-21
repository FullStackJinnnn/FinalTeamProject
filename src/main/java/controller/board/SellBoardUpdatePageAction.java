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
		
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setTitle(request.getParameter("title"));
		boardDTO.setContents(request.getParameter("contents"));
		boardDTO.setPrice(Integer.parseInt(request.getParameter("price")));
		boardDTO.setImage(request.getParameter("image"));
		boardDTO.setProductcategory(request.getParameter("productcategory"));
		boardDTO.setCompany(request.getParameter("company"));
		boardDTO.setState(request.getParameter("state"));
		boardDTO.setViewCount(0);
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		
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
