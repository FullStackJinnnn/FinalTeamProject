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

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberID((String) session.getAttribute("member"));
		memberDTO.setSearchCondition("내정보출력");
		memberDTO = memberDAO.selectOne(memberDTO);
		if (memberDTO != null) {
			request.setAttribute("memberDTO", memberDTO);
			forward.setPath("common/myPage.jsp");
			forward.setRedirect(false);
		} else {
			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);
			request.setAttribute("msg", "로그인 실패!");
		}

		return forward;
	}

}
