package controller.async;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/kaKaoLogin.do")
public class KaKaoLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public KaKaoLoginAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		System.out.println("[KaKaoLoginAction] 카카오톡 서버와 연결되는지 확인용");
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(request.getParameter("memberID"));
		System.out.println("[KaKaoLoginAction] 카카오톡 서버와 연결되는지 확인용 : " + memberDTO.getId());
		/*
		 * memberDTO.setPw(request.getParameter("memberPW"));
		 * System.out.println("[KaKaoLoginAction] 카카오톡 서버와 연결되는지 확인용 : " +
		 * memberDTO.getPw());
		 */
		memberDTO.setSearchCondition("로그인");
		memberDTO.setSnsLoginCondition("SNS로그인");
		memberDTO = memberDAO.selectOne(memberDTO);
		
		System.out.println("카카오톡 : " + memberDTO);

		if (memberDTO != null) { // 로그인 성공시 세션 저장 후 메인으로 이동 , 이동 할 정보 없음
			if (!memberDTO.getGrade().equals("탈퇴")) {
				HttpSession session = request.getSession();
				session.setAttribute("member", memberDTO.getId());
				System.out.println("[KaKaoLoginAction] 로그인 성공");
				response.getWriter().println("{\"isNewUser\": false}");
			} else {
				System.out.println("[KaKaoLoginAction] 로그인 실패");
			}
		} else {
			System.out.println("[KaKaoLoginAction] 없는 회원입니다.");
			response.getWriter().println("{\"isNewUser\": true}");
		}

	}

}
