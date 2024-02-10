package controller.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/loginCheck.do")
public class LoginCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginCheckAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		memberDTO.setId(request.getParameter("id"));
		memberDTO.setPw(request.getParameter("pw"));
		memberDTO.setSearchCondition("로그인");
		memberDTO.setSnsLoginCondition("");
		//System.out.println("LoginCheck " + memberDTO);
		memberDTO = memberDAO.selectOne(memberDTO);

		int flag = 0;
		
		 // 회원 정보가 존재한다면 로그인 성공
		if (memberDTO != null) {
			flag = 1;
			HttpSession session = request.getSession();
			session.setAttribute("member", memberDTO.getId());
			
		} else {
			// 로그인 실패 했을 때
		}
		
		PrintWriter out = response.getWriter();
		out.print(flag);
		System.out.println(flag);
	}

}
