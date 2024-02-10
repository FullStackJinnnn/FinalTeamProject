package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class CameraReviewUpdatePageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();

		if (session.getAttribute("member") == null) {
			forward.setPath("/chalKag/loginPage.do");
			forward.setRedirect(true);
		}

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setCategory("리뷰게시판");
		boardDTO.setUpdatePage("수정");

		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO = boardDAO.selectOne(boardDTO);

		if (boardDTO != null) {
			// 절대 경로를 상대경로로 치환하기 위한 로직_ 2024.01.31_김도연
//			String prefix = "D:/PLZJUN/workspace_infinityStone/chalKag/src/main/webapp";
//			String relativePath = boardDTO.getImage().replace(prefix, "");		// 절대경로를 bimg/이미지.확장자로 줄인다.
//			boardDTO.setImage(relativePath); // 상대 경로로 변경된 주소를 image에 저장한 뒤에 V로 전달한다.
//			System.out.println("상대경로 확인용 : " + relativePath);
			request.setAttribute("boardData", boardDTO);

			forward.setPath("/chalKag/board/cameraReviewUpdatePage.jsp");
			forward.setRedirect(false);
		} else {
			request.setAttribute("msg", "없거나 볼 수 없는 글입니다!");

			forward.setPath("alert.do");
			forward.setRedirect(false);
		}

		return forward;

	}

}
