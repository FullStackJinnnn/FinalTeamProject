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

@WebServlet("/checkNickname.do")
public class CheckNickname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckNickname() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDAO=new MemberDAO();
		MemberDTO mDTO=new MemberDTO();
		mDTO.setSearchCondition("닉네임중복검사");
		//
		mDTO.setNickname(request.getParameter("Nickname"));
		mDTO=mDAO.selectOne(mDTO);
		
		int flag=0;
		if(mDTO==null) {
			flag=1;
		}
		
		PrintWriter out=response.getWriter();
		out.print(flag);
		System.out.println(flag);
	}

}
