package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class FindPwResultPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		forward.setPath("common/changePwPage.jsp");
		forward.setRedirect(false);

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		// 사용자의 아이디 입력
		memberDTO.setId(request.getParameter("ID"));

		// 사용자의 전화번호 입력
		memberDTO.setPh(request.getParameter("PH"));

		// searchCondition 설정
		memberDTO.setSearchCondition("비밀번호 찾기");

		// 비밀번호 찾기 로직 실행
		memberDTO = memberDAO.selectOne(memberDTO);

		if (memberDTO != null) {

			request.setAttribute("findPwResult", memberDTO.getId());

		} else {

			request.setAttribute("findPwResult", "가입 된 아이디가 없습니다!");

		}

		return forward;
	}

}
