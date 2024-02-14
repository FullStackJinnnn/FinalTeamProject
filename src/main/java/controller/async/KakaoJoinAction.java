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

		// 카카오 계정의 이메일을 저장한다.
		memberDTO.setId(request.getParameter("memberID"));

		// UUID 생성
		UUID uuid = UUID.randomUUID();
		// UUID의 앞 8자리를 임시 비밀번호로 사용
		String temporaryPassword = uuid.toString().substring(0, 8);
		System.out.println("temporaryPassword 확인 [" + temporaryPassword + "]");

		// memberDTO에 임시 비밀번호 설정
		memberDTO.setPw(temporaryPassword);

		// 카카오 계정에서 가져온 이름, 닉네임, 생일을 저장한다.
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setNickname(request.getParameter("nickname"));
		memberDTO.setBirth(request.getParameter("memberBirth"));

		// 카카오 계정에서 가져온 번호 : +82 10-xxxx-xxxx
		String ph = request.getParameter("ph");
		// +82 를 replace 메소드로 제거
		ph = ph.replace("+82 ", "0");
		// -를 replace 메소드로 제거
		ph = ph.replace("-", "");
		// 010xxxxxxxx형식으로 변경된 번호를 저장한다.
		memberDTO.setPh(ph);	

		// DTO에 저장된 값들을 DAO로 전달하여 회원가입
		boolean flag = memberDAO.insert(memberDTO);

		if (flag) { // 성공시 메인으로 이동
			request.setAttribute("msg", "회원가입 성공!");
			session.invalidate();
		} else { // 실패시 alert 창으로 이동
			request.setAttribute("msg", "회원가입 실패! 다시 이용해 주세요");
		}

	}

}
