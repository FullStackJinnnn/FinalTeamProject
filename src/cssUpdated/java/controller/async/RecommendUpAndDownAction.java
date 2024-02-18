package controller.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.recommend.RecommendDAO;
import model.recommend.RecommendDTO;

@WebServlet("/recommendUpAndDown.do")
public class RecommendUpAndDownAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecommendUpAndDownAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RecommendDAO recommendDAO = new RecommendDAO();	// DAO 연결
		RecommendDTO recommendDTO = new RecommendDTO();	// 게시글 에서 받아온 데이터를 받는 DTO
		RecommendDTO selectedRecommendDTO  = new RecommendDTO();	// selectOne으로 받아온 데이터를 받아두는 객체
		
		boolean flag = false; 

		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();	
		PrintWriter out = response.getWriter();
		
		recommendDTO.setBoardNum(Integer.parseInt(request.getParameter("boardNum")));
		recommendDTO.setId((String) session.getAttribute("member"));

		System.out.println("추천 확인용:" + recommendDTO.getBoardNum());
		selectedRecommendDTO = recommendDAO.selectOne(recommendDTO);	// selectOne으로 받아온 값들이

		if (selectedRecommendDTO  == null) {	// null이면 
			flag = recommendDAO.insert(recommendDTO);	// 추천 등록

			if (flag) {
				System.out.println("업데이트 완료");

			} else {
				System.out.println("업데이트 실패");
			}
		} else {										// 있으면 delete
			flag = recommendDAO.delete(recommendDTO);

			if (flag) {
				System.out.println("업데이트 완료");

			} else {
				System.out.println("업데이트 실패");
			}
		}

	}
}