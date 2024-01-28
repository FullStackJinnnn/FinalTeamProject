package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.front.Action;
import controller.front.ActionForward;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class FindIdResultPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();

		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();

		// 사용자의 이름 입력
		memberDTO.setName(request.getParameter("name"));

		// 사용자의 전화번호 입력
		memberDTO.setPh(request.getParameter("PH"));

		// searchCondition 설정
		memberDTO.setSearchCondition("아이디 찾기");

		// 아이디 찾기 로직 실행
		memberDTO = memberDAO.selectOne(memberDTO);

		if (memberDTO != null) {

			request.setAttribute("findIdResult", memberDTO.getId());

		} else {

			request.setAttribute("findIdResult", "가입 된 아이디가 없습니다!");

		}

		return forward;
	}
}
