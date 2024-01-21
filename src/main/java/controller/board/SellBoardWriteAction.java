package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;


public class SellBoardWriteAction implements Action { // 카메라 판매글 작성

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
		
		boolean flag = boardDAO.insert(boardDTO);

		if (flag) { // 성공시 메인으로 이동

			forward.setPath("/main.do");
			forward.setRedirect(true);

		} else { // 실패시 alert 창으로 이동

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "게시글 등록 실패! 다시 이용해 주세요");

		}

		return forward;
			
	} 
}
