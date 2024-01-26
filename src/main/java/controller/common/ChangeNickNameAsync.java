package controller.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberDTO;

/**
 * Servlet implementation class ChangeNickNameAsync
 */
@WebServlet("/changeNickname2.do")
public class ChangeNickNameAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeNickNameAsync() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		HttpSession session = request.getSession();
		memberDTO.setMemberID((String)session.getAttribute("member"));
		memberDTO.setSearchCondition("내정보출력");
		memberDTO=memberDAO.selectOne(memberDTO);
		int outFlag=0;
		System.out.println(outFlag);
		PrintWriter out=response.getWriter();
		
		if (memberDTO.getNickname().equals(request.getParameter("myPageNickname"))) {
			out.print(outFlag);
			
		} 
		
		memberDTO.setNickname(request.getParameter("myPageNickname"));
		memberDTO.setSearchCondition("닉네임변경");
		boolean flag = memberDAO.update(memberDTO);
		if (flag) {
			outFlag=1;
		} 
		out.print(outFlag);
		
	}

}
