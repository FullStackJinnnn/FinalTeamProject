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

        // 클라이언트에서 전달된 boardNum 파라미터를 가져와서 boardDTO에 설정한다.
        boardDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));

        // request의 인코딩을 UTF-8로 설정한다.
        request.setCharacterEncoding("UTF-8");

        // 카테고리를 세팅하지 않으면 오류를 방지하기 위한 더미값 입력
        boardDTO.setCategory("");

        // boardDAO를 통해 boardDTO에 해당하는 게시글을 검색한다.
        boardDTO = boardDAO.selectOne(boardDTO);

        // 검색된 boardDTO를 콘솔에 출력한다.
        System.out.println(boardDTO);

        // 검색된 게시글이 존재하는 경우
        if (boardDTO != null) {
            // reportWritePage.jsp로 이동
            forward.setPath("/chalKag/report/reportWritePage.jsp");
            // forward방식으로 이동
            forward.setRedirect(false);
            // request에 검색된 boardDTO를 저장한다.
            request.setAttribute("boardDTO", boardDTO);
        } else {
            // alertPage.jsp로 이동
            forward.setPath("error/alertPage.jsp");
            // Redirect방식으로 이동
            forward.setRedirect(true);
        }
        return forward;
    }

}
