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
		
		if (memberDTO != null) { // 로그인 성공시 세션 저장 후 메인으로 이동 , 이동 할 정보 없음
			if (!memberDTO.getGrade().equals("탈퇴")) {
				HttpSession session = request.getSession();
				session.setAttribute("member", memberDTO.getId());

				forward.setPath("/chalKag/main.do");
				request.setAttribute("msg", "로그인 성공");
				forward.setRedirect(true);
			} else {
				forward.setPath("/chalKag/main.do");
				request.setAttribute("msg", "이미 탈퇴한 회원의 아이디입니다.");
				forward.setRedirect(true);
			}
		} else{ 
			forward.setRedirect(false);
		}
		

		return forward;
	}

}
