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

        // 세션에서 회원 아이디를 가져와서 MemberDTO에 설정
        memberDTO.setId((String) session.getAttribute("member"));

        // 검색조건을 '내정보출력'으로 설정
        memberDTO.setSearchCondition("내정보출력");

        // MemberDAO를 이용하여 MemberDTO에 해당하는 회원 정보를 데이터베이스에서 검색
        memberDTO = memberDAO.selectOne(memberDTO);
        System.out.println(memberDTO);
        // 검색 결과가 존재하면
        if (memberDTO != null) {
            // 검색 결과인 memberDTO를 request 속성에 설정
            request.setAttribute("memberDTO", memberDTO);

            // common/myPage.jsp로 이동
            forward.setPath("common/myPage.jsp");

            // forward 방식으로 이동
            forward.setRedirect(false);
        } else {
            // error/alertPage.jsp로 이동
            forward.setPath("error/alertPage.jsp");

            // Redirect 방식으로 이동
            forward.setRedirect(true);
        }

        // 생성한 ActionForward 객체 반환
        return forward;
    }
}