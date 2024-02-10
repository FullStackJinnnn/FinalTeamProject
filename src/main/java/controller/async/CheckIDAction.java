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

@WebServlet("/checkID.do")
public class CheckIDAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckIDAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDAO=new MemberDAO();
		MemberDTO mDTO=new MemberDTO();
		
		//
		mDTO.setSearchCondition("ID중복검사");
		
		//
		mDTO.setId(request.getParameter("id"));
		mDTO=mDAO.selectOne(mDTO);
		
		//System.out.println("mDTO"+mDTO);
		int flag=0;
		
		// 저장된 아이디가 없다면 중복이 아니라면? 
		if(mDTO==null) {
			flag=1;
		}
		else {
			// 아이디가 중복이라면
		}
		
		PrintWriter out=response.getWriter();
		out.print(flag);
		System.out.println(flag);
	}

}
