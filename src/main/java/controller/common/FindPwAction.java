package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class FindPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(request.getParameter("memberID"));
		memberDTO.setPh("ph");
		memberDTO.setSearchCondition("비밀번호찾기");

		memberDTO = memberDAO.selectOne(memberDTO);

		if (memberDTO != null) {

			forward.setPath("common/findPwResultPage.jsp");
			forward.setRedirect(true);

		} else {

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "비밀번호 확인 실패");

		}

		return forward;
	}

}
