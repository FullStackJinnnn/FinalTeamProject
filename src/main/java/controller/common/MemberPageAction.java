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

public class MemberPageAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ActionForward forward = new ActionForward();

        MemberDAO memberDAO = new MemberDAO();
        MemberDTO memberDTO = new MemberDTO();

        // 사용자의 닉네임을 받아와서 MemberDTO에 설정
        memberDTO.setNickname(request.getParameter("nickname"));

        // 검색조건을 '유저정보출력'으로 설정
        memberDTO.setSearchCondition("유저정보출력");

        // MemberDAO를 이용하여 MemberDTO에 해당하는 회원 정보를 데이터베이스에서 검색
        MemberDTO memberData= memberDAO.selectOne(memberDTO);

        // 검색 결과가 존재하면
        if (memberData != null) {
            // 검색 결과인 memberDTO를 request 속성에 설정
            request.setAttribute("memberData", memberData);

            // common/memberPage.jsp로 이동
            forward.setPath("common/memberPage.jsp");

            // forward 방식으로 이동
            forward.setRedirect(false);
        } else {
            // error/alertPage.jsp로 이동
            forward.setPath("error/alertPage.jsp");

            // Redirect 방식으로 이동
            forward.setRedirect(true);

        }
        return forward;
    }
}
