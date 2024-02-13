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

public class FreeBoardSelectAllPageAction implements Action { // 자유게시판 카테고리 페이지로 이동. 전미지

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		// BoardDAO와 BoarDTO 객체 생성
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();

		// selectAll 결과(BoardDTO 객체)를 담는 boardDatas라는 ArrayList 생성
		ArrayList<BoardDTO> boardDatas = new ArrayList<BoardDTO>();

		// 자유 게시판 카테고리의 게시글 전체 목록 받아오기
		boardDTO.setCategory("자유게시판");	
		boardDTO.setSearchCondition("");
		
//		System.out.println("[로그] FreeBoardSelectAllPageAction 1 카테고리 : " + boardDTO);

		// selectAll 메서드를 통해 데이터 베이스에서 모든 게시글 정보를 가져와서 boardDatas에 저장
		boardDatas = boardDAO.selectAll(boardDTO);
		System.out.println(boardDatas);
//		System.out.println("[로그] FreeBoardSelectAllPageAction 2" + boardDTO)
//		System.out.println("[로그] FreeBoardSelectAllPageAction 3" + boardDatas);
		
		if (boardDatas != null) { // 게시글 정보(boardDatas)가 있다면
			// 가져온 게시글 정보(boardDatas)를 request 객체에 "freeBoardDTO"라는 이름으로 저장
			
			//request.setAttribute("boardDatas", boardDatas);
//			System.out.println("[로그] FreeBoardSelectAllPageAction 4 " + boardDatas);
			Gson gson = new Gson();
			String jsonBoardDatas = gson.toJson(boardDatas);
			request.setAttribute("jsonBoardDatas", jsonBoardDatas);
			request.setAttribute("category", boardDTO.getCategory());
			
			// 데이터를 보내줄 페이지와 데이터 전송 방식
			forward.setPath("board/freeBoardSelectAllPage.jsp");
			// 데이터를 freeBoardSelectAllPage.jsp로 보냄
			forward.setRedirect(false);
			// 데이터를 보낼 때 리다이렉트(==데이터 없음)가 아니라면 (결과적을 데이터가 있다면) 포워드 방식(==데이터 있음)으로 보냄
		} else { // 게시글 정보(boardDatas)가 없다면
			request.setAttribute("msg", "등록된 글이 없습니다! 가장 먼저 새 글을 작성해 주세요!");
			// '등록된 글이 없습니다! 가장 먼저 새 글을 작성해 주세요!'라는 에러 메시지를 request 객체에 'messages' 라는 이름으로 저장

			// 데이터를 보내줄 페이지와 데이터 전송 방식
		    forward.setPath("error/alertPage.jsp");
			// 에러 메시지 정보를 alertPage.jsp로 보냄
			forward.setRedirect(false);
			// 데이터를 보낼 때 리다이렉트(==데이터 없음)가 아니라면 (결과적을 데이터가 있다면) 포워드 방식(==데이터 있음)으로 보냄
			// 보내줄 데이터가 없다면 리다이텍트 방식으로 보내려면 'fals'를 'true'로 바꿔줘 함
		}

		return forward;

	}

}