package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDAO;
import model.board.BoardDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class MemberBoardSelectAllPageAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ActionForward forward = new ActionForward();
        MemberDAO memberDAO = new MemberDAO();
        MemberDTO memberDTO = new MemberDTO();
        BoardDAO boardDAO = new BoardDAO();
        BoardDTO boardDTO = new BoardDTO();

        // 유저가 작성한 게시글을 보기 위한 값 설정
        memberDTO.setNickname(request.getParameter("nickname"));
        memberDTO.setSearchCondition("유저정보출력");
        memberDTO = memberDAO.selectOne(memberDTO);
        boardDTO.setCategory(""); 
        boardDTO.setSearchCondition("유저보드");
        boardDTO.setId(memberDTO.getId());

        // 유저 nickname을 클라이언트로부터 받아 memberDAO.selectOne메서드로 id를 가져온 후
        // boardDAO.selectAll메서드로 해당 유저가 작성한 게시글을 모두 가져옴
        ArrayList<BoardDTO> boardDatas = boardDAO.selectAll(boardDTO);

        if (boardDatas != null) {
        	
            // 찾은 게시글을 JSON 형식으로 클라이언트에게 응답
            Gson gson = new Gson();
            String jsonBoardDatas = gson.toJson(boardDatas);
            request.setAttribute("jsonBoardDatas", jsonBoardDatas);
            forward.setPath("board/myBoardSelectAllPage.jsp");
            forward.setRedirect(false);

        } else {
            forward.setPath("error/alertPage.jsp");
            forward.setRedirect(true);
        }
        
        // 세션과 비교해 게시글 작성자를 표현,
        // 내가 작성한 게시글, 유저가 작성한 게시글 정렬 및 페이징 처리를 위한 값 설정
        request.setAttribute("nickname", memberDTO.getNickname());
        request.setAttribute("id", memberDTO.getId());

        return forward;
    }
}
