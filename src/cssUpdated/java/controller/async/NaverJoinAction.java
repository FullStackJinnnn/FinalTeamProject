package controller.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/naverJoin.do")
public class NaverJoinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NaverJoinAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		JSONObject naverMember = (JSONObject) request.getAttribute("naverMember");

		HttpSession session = request.getSession();

		memberDTO.setId((String) naverMember.get("email"));

		// UUID 생성.안승준
		UUID uuid = UUID.randomUUID();
		// UUID의 앞 8자리를 임시 비밀번호로 사용.안승준
		String temporaryPassword = uuid.toString().substring(0, 8);
		System.out.println("temporaryPassword 확인 ["+temporaryPassword+"]");

		// memberDTO에 임시 비밀번호 설정.안승준
		memberDTO.setPw(temporaryPassword);

		memberDTO.setName((String) naverMember.get("name"));
		memberDTO.setNickname((String) naverMember.get("nickname"));
		memberDTO.setBirth((String) naverMember.get("birthyear") + '-' + (String) naverMember.get("birthday"));
		
		String ph = (String) naverMember.get("mobile");
		
		ph = ph.replace("-", "");
		
		memberDTO.setPh(ph);

		boolean flag = memberDAO.insert(memberDTO);

		if (flag) { // 성공시 메인으로 이동.안승준

			System.out.println("회원가입 성공!");
			session.invalidate();	
			
			String PageUrl = "/chalKag/loginPage.do";
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('JOIN SUCCESS'); location.href='"+ PageUrl +"';</script>"); 
			writer.close();
			
//			response.sendRedirect("/chalKag/loginPage.do");

		} else { // 실패시 alert 창으로 이동.안승준

			System.out.println("회원가입 실패! 다시 이용해 주세요");
			response.sendRedirect("/chalKag/loginPage.do");

		}
	}
}
