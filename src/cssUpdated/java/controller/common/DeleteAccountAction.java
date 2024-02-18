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

public class DeleteAccountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		HttpSession session = request.getSession();
		memberDTO.setId((String) session.getAttribute("member"));
		memberDTO.setGrade("탈퇴");
		memberDTO.setSearchCondition("회원탈퇴");

		boolean flag = memberDAO.update(memberDTO);

		if (flag) {
			forward.setPath("main.do");
			forward.setRedirect(true);
			session.invalidate();

		} else {

			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);
			request.setAttribute("msg", "회원탈퇴실패!");

		}

		return forward;
	}

}
