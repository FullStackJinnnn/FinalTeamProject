package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class CheckPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		memberDTO.setMemberPW(request.getParameter("pw"));
		memberDTO.setSearchCondition("비밀번호확인");

		memberDTO = memberDAO.selectOne(memberDTO);

		if (memberDTO != null) {

			forward.setPath("common/main.do");
			forward.setRedirect(true);

		} else {

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "비밀번호를 잘못 입력하였습니다!");
		}

		return forward;
	}

}
