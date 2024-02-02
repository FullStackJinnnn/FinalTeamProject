
package controller.common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.board.BoardDTO;
import model.board.SearchDAO;
import model.board.SearchDTO;

public class FilterSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String company[]= request.getParameterValues("company");
		String productcategory[] = request.getParameterValues("productcategory");
		String status[] = request.getParameterValues("status");
		
		ArrayList<String> cList = new ArrayList<String>();
		ArrayList<String> pList = new ArrayList<String>();
		ArrayList<String> sList = new ArrayList<String>();
		
		ActionForward forward = new ActionForward();
		SearchDTO searchDTO = new SearchDTO();
		SearchDAO searchDAO = new SearchDAO();
		if (request.getParameter("pmin") != null && request.getParameter("pmax") != null) {
			int price_min = Integer.parseInt(request.getParameter("pmin"));
			int price_max = Integer.parseInt(request.getParameter("pmax"));
			searchDTO.setPrice_min(price_min);
			searchDTO.setPrice_max(price_max);
		}
		
		if(company != null) {
			for(int i=0; i<company.length; i++) {
				cList.add(company[i]);
				System.out.println("[로그]받아온 지역 파라미터 값 : " + company[i]);
			}
			searchDTO.setCompanyList(cList);
		}
		request.setAttribute("cList",cList );
		
		ArrayList<BoardDTO> filterDatas =  searchDAO.selectAll(searchDTO);
		request.setAttribute("filterDatas",filterDatas);
		
		forward.setRedirect(false);
		forward.setPath("board/cameraReviewSelectAllPage.jsp");
		
		
		
		
		
		
		
		
		return forward;

	}

}
