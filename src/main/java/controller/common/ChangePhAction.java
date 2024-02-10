package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class ChangePhAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		memberDTO.setPh(request.getParameter("ph"));
		memberDTO.setSearchCondition("전화번호변경");

		boolean flag = memberDAO.update(memberDTO);

		if (flag) {

			forward.setPath("commom/myPage.do");
			forward.setRedirect(true);

		} else {

			forward.setPath("common/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "전화번호변경실패!");

		}

		return forward;
	}

}
