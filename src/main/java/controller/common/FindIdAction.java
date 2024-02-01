package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class FindIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setPh(request.getParameter("PH"));
		memberDTO.setSearchCondition("아이디 찾기");
		memberDTO = memberDAO.selectOne(memberDTO);

		if (memberDTO != null) {

			forward.setPath("common/findIdResultPage.jsp");

		} else {

			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "가입 된 아이디가 없습니다!");

		}

		return null;
	}

}
