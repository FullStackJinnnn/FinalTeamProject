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

public class ChangeNickNameAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		HttpSession session = request.getSession();
		memberDTO.setId((String)session.getAttribute("member"));
		memberDTO.setSearchCondition("정보출력");
		memberDTO=memberDAO.selectOne(memberDTO);
		if (memberDTO.getNickname().equals(request.getParameter("myPageNickname"))) {
			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "닉네임중복!!");
			return forward;
		} 
		
		memberDTO.setNickname(request.getParameter("myPageNickname"));
		memberDTO.setSearchCondition("닉네임변경");
		boolean flag = memberDAO.update(memberDTO);

		if (flag) {

			forward.setPath("myPage.do");
			forward.setRedirect(true);

		} else {

			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "닉네임변경실패!");

		}

		return forward;
	}

}
