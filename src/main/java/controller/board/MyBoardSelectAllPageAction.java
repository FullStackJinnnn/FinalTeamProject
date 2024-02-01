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
        BoardDAO boardDAO = new BoardDAO();
        BoardDTO boardDTO = new BoardDTO();

        // 카테고리를 세팅하지 않으면 오류가 나기 때문에 더미값("")을 입력
        boardDTO.setCategory("");
        
        // 검색조건을 '유저보드'로 설정
        boardDTO.setSearchCondision("유저보드");

        // 요청 파라미터로부터 닉네임을 가져와서 BoardDTO에 설정
        boardDTO.setId(request.getParameter("id"));
        
        // BoardDAO를 이용하여 해당 닉네임의 사용자가 작성한 게시물을 모두 가져옴
        ArrayList<BoardDTO> boardDatas = boardDAO.selectAll(boardDTO);

        // 게시물 데이터가 존재하면
        if (boardDatas != null) {
            // 게시물 데이터를 request 속성에 설정
            request.setAttribute("boardDatas", boardDatas);

            // board/myBoardSelectAllPage.jsp로 이동
            forward.setPath("board/myBoardSelectAllPage.jsp");

            // forward방식으로 이동
            forward.setRedirect(false);
        } else {
            // error/alertPage.jsp로 이당
            forward.setPath("error/alertPage.jsp");

            // Redirect방식으로 이동
            forward.setRedirect(true);
        }

        return forward;
    }
}
