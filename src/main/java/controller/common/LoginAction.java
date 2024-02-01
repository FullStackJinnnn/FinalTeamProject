package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(request.getParameter("memberID"));
		memberDTO.setPw(request.getParameter("memberPW"));
		memberDTO.setSearchCondition("로그인");
		memberDTO = memberDAO.selectOne(memberDTO);
		System.out.println(memberDTO);
		
		if (memberDTO != null) { // 로그인 성공시 세션 저장 후 메인으로 이동 , 이동 할 정보 없음
			HttpSession session = request.getSession();
			session.setAttribute("member", memberDTO.getId());

			forward.setPath("/chalKag/main.do");
			forward.setRedirect(true);
			System.out.println("로그인 성공"+memberDTO.getId());
		} else { // 로그인 실패시 alert창으로 이동
//			forward.setPath("error/alertPage.jsp");
//			forward.setRedirect(false);
			request.setAttribute("msg", "로그인 실패!");

		}

		return forward;
	}

}
