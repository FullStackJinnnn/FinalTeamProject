package controller.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;

@WebServlet("/checkPh.do")
public class CheckPhAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckPhAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO memberDAO=new MemberDAO();
		MemberDTO memberDTO=new MemberDTO();
		
		memberDTO.setSearchCondition("전화번호중복검사");
		memberDTO.setPh(request.getParameter("ph"));
		memberDTO=memberDAO.selectOne(memberDTO);
		
		int flag=0;
		if(memberDTO==null) {
			flag=1;
		}
		
		PrintWriter out=response.getWriter();
		out.print(flag);
		System.out.println(flag);
	}

}
