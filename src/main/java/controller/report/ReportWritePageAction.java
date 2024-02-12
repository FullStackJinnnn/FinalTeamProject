package controller.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;

public class ReportWritePageAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ActionForward forward = new ActionForward();

        BoardDAO boardDAO = new BoardDAO();
        BoardDTO boardDTO = new BoardDTO();
        
        // 신고하는 게시글의 정보를 찾기 위한 값 설정
        boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
        boardDTO.setUpdatePage("");
        boardDTO.setCategory("");
        
        // boardDAO.selectOne 메서드로 해당 boardNum을 가지고 있는 게시글의 정보를 가져옴
        BoardDTO boardData = boardDAO.selectOne(boardDTO);

        // System.out.println("[로그] boardData: " + boardData);
        if (boardData != null) {
        	
            // 찾은 정보를 클라이언트에게 응답
            request.setAttribute("boardData", boardData);
            forward.setPath("report/reportWritePage.jsp");
            forward.setRedirect(false);
        } else {
            forward.setPath("error/alertPage.jsp");
            forward.setRedirect(true);
        }
        return forward;
    }

}
