
package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class BoardDeleteAction implements Action { //  

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
//		System.out.println("[로그] BoardDeleteAction.java 1 컨트롤러 진입 ");
		
		// BoardDAO와 BoarDTO 객체 생성
		BoardDTO boardDTO = new BoardDTO();
		BoardDAO boardDAO = new BoardDAO();

		// 삭제할 게시글의 넘버와 게시판 카테고리를 BoardDTO에 저장
		boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		boardDTO.setCategory(request.getParameter("category"));
		
		// boardDTO에 저장한 게시글 카테고리를 'category'라는 변수에 저장
		String category = boardDTO.getCategory();
		
		// delete 메서드를 통해 데이터 베이스에 저장된 게시글 정보를 삭제
		boolean flag = boardDAO.delete(boardDTO);			
		if(flag) { // 게시글 삭제를 성공 한다면 alert 창을 통해 게시글 삭제 성공 메시지를 보여준 뒤 게시글이 속한 카테고리 페이지로 보냄
//		System.out.println("[로그] BoardDeleteAction.java 2 게시글 삭제 성공 진입");
			if (category.equals("판매게시판")) {
				forward.setPath("error/alertPage.jsp");
				request.setAttribute("status","sellBoardDeleteSuccess");
			} 
			else if (category.equals("리뷰게시판")) { 
				forward.setPath("error/alertPage.jsp");
				request.setAttribute("status","cameraReviewDeleteSuccess");
			}
			else if (category.equals("자유게시판")) { 
				forward.setPath("error/alertPage.jsp");
				request.setAttribute("status","freeBoardDeleteSuccess");
			}
			request.setAttribute("msg","게시글이 성공적으로 삭제되었습니다!");
			forward.setRedirect(false);
//			forward.setRedirect(true);
		}
		else {
//		System.out.println("[로그] BoardDeleteAction.java 3 게시글 삭제 실패");
		
				// 데이터를 보내줄 페이지와 데이터 전송 방식
			    forward.setPath("error/alertPage.jsp");
				// 에러 메시지 정보를 alertPage.jsp로 보냄
			    request.setAttribute("status","DeleteFail");
			    request.setAttribute("msg", "게시글 삭제에 실패했습니다! 다시 시도해 주세요.");
			    // '게시글 삭제에 실패했습니다! 다시 시도해 주세요.'라는 에러 메시지를 request 객체에 'msg' 라는 이름으로 저장
				forward.setRedirect(false);
				// 데이터를 보낼 때 리다이렉트(==데이터 없음)가 아니라면 (결과적을 데이터가 있다면) 포워드 방식(==데이터 있음)으로 보냄
				// 보내줄 데이터가 없다면 리다이텍트 방식으로 보내려면 'fals'를 'true'로 바꿔줘 함			
		}
		return forward;
	}

}