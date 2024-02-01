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
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("member") != null) {
			
			memberDTO.setId((String)session.getAttribute("member"));
			
		} else {
			
			memberDTO.setId((String)request.getAttribute("id"));
			
		}
		memberDTO.setPw(request.getParameter("pw"));
		memberDTO.setSearchCondition("비밀번호변경");

		boolean flag = memberDAO.update(memberDTO);

		if (flag) {
			
			if(session.getAttribute("member") != null) {
				session.invalidate();
			}
	
			forward.setPath("/chalKag/main.do");
			forward.setRedirect(true);
			

		} else {

			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "비밀번호 변경 실패!");

		}

		return forward;
	}

}
