package controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import controller.front.ActionForward;
import model.board.BoardDTO;
import model.board.SearchDAO;
import model.board.SearchDTO;

/**
 * Servlet implementation class FilterSearchAction2
 */
@WebServlet("/filterSearchAction2.do")
public class FilterSearchAction2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilterSearchAction2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[로그] doPost 50번쨰라인 진입");
		String company[] = request.getParameterValues("company");
		String productcategory[] = request.getParameterValues("productcategory");
		String state[] = request.getParameterValues("state");
		String category = request.getParameter("category");
		
		
		
		ArrayList<String> cList = new ArrayList<String>();
		ArrayList<String> pList = new ArrayList<String>();
		ArrayList<String> sList = new ArrayList<String>();

		SearchDTO searchDTO = new SearchDTO();
		SearchDAO searchDAO = new SearchDAO();
		searchDTO.setCategory(category);
		
		
		if  (request.getParameter("minPrice") !=null && request.getParameter("maxPrice") !=null) {
			int minPrice = Integer.parseInt(request.getParameter("minPrice"));
			int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
			System.out.println("[로그]받아온 지역 파라미터 값 :" + minPrice+","+maxPrice);
			searchDTO.setMinPrice(minPrice);
			searchDTO.setMaxPrice(maxPrice);
		}

		if (company != null) {
			for (int i = 0; i < company.length; i++) {
				cList.add(company[i]);
				System.out.println("[로그]받아온 지역 파라미터 값 : " + company[i]);
			}
			searchDTO.setCompanyList(cList);
		}
		
		if (productcategory != null) {
			for (int i = 0; i < productcategory.length; i++) {
				pList.add(productcategory[i]);
				System.out.println("[로그]받아온 지역 파라미터 값 : " + productcategory[i]);
			}
			searchDTO.setProductcategoryList(pList);
		}
		
		if (state != null) {
			for (int i = 0; i < state.length; i++) {
				sList.add(state[i]);
				System.out.println("[로그]받아온 지역 파라미터 값 : " + state[i]);
			}
			searchDTO.setStateList(sList);
		}
		//request.setAttribute("cList", cList);
		
		ArrayList<BoardDTO> filterDatas = searchDAO.selectAll(searchDTO);
		System.out.println("[로그] 필터된 데이터 : " + filterDatas);
		if (filterDatas != null) {
			Gson gson = new Gson();
			String jsonFilterDatas = gson.toJson(filterDatas);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonFilterDatas);
		}
	}

}
