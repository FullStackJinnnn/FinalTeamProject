package controller.common;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class ChangePwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		
		HttpSession session = request.getSession();
		memberDTO.setMemberID((String)session.getAttribute("member"));
		memberDTO.setPh(request.getParameter("newPw"));
		memberDTO.setSearchCondition("비밀번호변경");

		boolean flag = memberDAO.update(memberDTO);

		if (flag) {

			forward.setPath("common/main.do");
			forward.setRedirect(true);

		} else {

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "비밀번호 변경 실패!");

		}

		return forward;
	}

}
