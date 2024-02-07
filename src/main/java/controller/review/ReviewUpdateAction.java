package controller.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.review.ReviewDAO;
import model.review.ReviewDTO;


@WebServlet("/reviewUpdateAction.do")
public class ReviewUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReviewUpdateAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewDTO reviewDTO = new ReviewDTO();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		reviewDTO.setReviewNum(Integer.parseInt(request.getParameter("reviewNum")));
		reviewDTO.setReviewContents(request.getParameter("updatedContents"));
		
		System.out.println("[로그] " + reviewDTO.getReviewNum());
		System.out.println("[로그] " + reviewDTO.getReviewContents());
		
		boolean flag = reviewDAO.update(reviewDTO);
		
		if(flag) {
			System.out.println("업데이트 완료");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out= response.getWriter();
		    out.print(reviewDTO.getReviewContents());
		    System.out.println(reviewDTO.getReviewContents());
		}
		else {
			System.out.println("업데이트 실패");
		}
		
	}

}
