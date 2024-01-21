package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		// 로그인 성공 시 메인으로 .노승현
		forward.setPath("common/main.do");
		
		// 값을 가지고 이동해야하기 때문에 redirect	.노승현
		forward.setRedirect(true);

		request.setCharacterEncoding("UTF-8");

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberID(request.getParameter("memberID"));
		memberDTO.setMemberPW(request.getParameter("memberPW"));
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setNickname(request.getParameter("nickname"));
		
		// 이거 어떻게 수정함?	.노승현
		memberDTO.setBirth(request.getParameter("year"));
		memberDTO.setBirth(request.getParameter("month"));
		memberDTO.setBirth(request.getParameter("day"));
		
		memberDTO.setPh(request.getParameter("ph"));
		memberDTO.setProfile(request.getParameter("profile"));

		boolean flag = memberDAO.insert(memberDTO);

		if (flag) { // 성공시 메인으로 이동

			forward.setPath("/main.do");
			forward.setRedirect(true);

		} else { // 실패시 alert 창으로 이동

			forward.setPath("error/alert.jsp");
			forward.setRedirect(false);
			request.setAttribute("msg", "회원가입 실패! 다시 이용해 주세요");

		}

		return forward;
	}

}
