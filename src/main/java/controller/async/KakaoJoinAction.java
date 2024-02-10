package controller.async;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/kakaoJoin.do")
public class KakaoJoinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public KakaoJoinAction() {
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

		HttpSession session = request.getSession();

		memberDTO.setId(request.getParameter("memberID"));

		// UUID 생성
		UUID uuid = UUID.randomUUID();
		// UUID의 앞 8자리를 임시 비밀번호로 사용
		String temporaryPassword = uuid.toString().substring(0, 8);
		System.out.println("temporaryPassword 확인 [" + temporaryPassword + "]");

		// memberDTO에 임시 비밀번호 설정
		memberDTO.setPw(temporaryPassword);

		memberDTO.setName(request.getParameter("name"));
		memberDTO.setNickname(request.getParameter("nickname"));
		memberDTO.setBirth(request.getParameter("memberBirth"));

		String ph = request.getParameter("ph");
		System.out.println(ph);
		ph = ph.replace("+82 ", "0");
		System.out.println(ph);
		memberDTO.setPh(ph);
		memberDTO.setGrade(request.getParameter("grade"));
		memberDTO.setProfile("default.jpg");

		boolean flag = memberDAO.insert(memberDTO);

		if (flag) { // 성공시 메인으로 이동
			request.setAttribute("msg", "회원가입 성공!");
			session.invalidate();
		} else { // 실패시 alert 창으로 이동
			request.setAttribute("msg", "회원가입 실패! 다시 이용해 주세요");
		}

	}

}
