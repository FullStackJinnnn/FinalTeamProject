package controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        // 유저의 정보를 보기 위한 값 설정
        memberDTO.setNickname(request.getParameter("nickname"));
        memberDTO.setSearchCondition("유저정보출력");
        
        // memberDAO.selectOne 메서드로 해당 닉네임을 가지고 있는 유저의 정보 가져옴
        MemberDTO memberData = memberDAO.selectOne(memberDTO);

        if (memberData != null) {
        	
            // 찾은 정보를 클라이언트에게 응답
            request.setAttribute("memberData", memberData);
            forward.setPath("common/memberPage.jsp");
            forward.setRedirect(false);
        } else {
            forward.setPath("error/alertPage.jsp");
            forward.setRedirect(true);
        }
        return forward;
    }
}




