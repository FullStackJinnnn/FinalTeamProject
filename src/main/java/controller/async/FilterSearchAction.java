package controller.async;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.board.BoardDTO;
import model.board.SearchDAO;
import model.board.SearchDTO;

/**
 * FilterSearchAction: 비동기적으로 필터링된 검색을 처리하는 서블릿
 */
@WebServlet("/filterSearch.do")
public class FilterSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 기본 생성자
	 */
	public FilterSearchAction() {
		super();
	}

	/**
	 * GET 요청 처리
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("[로그] doPost 50번째 라인 진입");

		// 파라미터로부터 필터링에 사용될 값들을 받아옴
		String company[] = request.getParameterValues("company");
		String productcategory[] = request.getParameterValues("productcategory");
		String state[] = request.getParameterValues("state");
		String category = request.getParameter("category");
		String searchField = request.getParameter("searchField");
		String searchInput = request.getParameter("searchInput");
		String jsonOrderColumnDirection = request.getParameter("jsonOrderColumnDirection");
		String priceSort = request.getParameter("priceSort");
		
		String id = request.getParameter("id");
		System.out.println("[로그] 59번 라인" + jsonOrderColumnDirection);

		// Map을 사용하여 정렬기준과 방향 정보를 담음
		Map<String, String> orderMap = new HashMap<>();

		ArrayList<String> cList = new ArrayList<String>();
		ArrayList<String> pList = new ArrayList<String>();
		ArrayList<String> sList = new ArrayList<String>();

		SearchDTO searchDTO = new SearchDTO();
		SearchDAO searchDAO = new SearchDAO();

		//마이보드, 멤버보드 정렬을 위한 set
		searchDTO.setCategory(category);
		searchDTO.setId(id);
		searchDTO.setPriceSort(priceSort);
		

		if (jsonOrderColumnDirection != null) {
			// 중괄호를 제거하고 문자열을 키-값 쌍으로 나눔
			String[] keyValuePairs = jsonOrderColumnDirection.replace("{", "").replace("}", "").split(",");

			// 키-값 쌍을 반복
			for (String pair : keyValuePairs) {
				String[] entry = pair.split(":");

				// 키와 값에서 따옴표와 공백을 제거
				String key = entry[0].trim().replace("\"", "");
				String value = entry[1].trim().replace("\"", "");

				// 키-값 쌍을 맵에 추가
				orderMap.put(key, value);
			}

			System.out.println("[로그] 받아온 정렬 파라미터 값 :" + orderMap);
			searchDTO.setOrderColumnDirection(orderMap);
		}

		if (searchField != null && !searchInput.equals("")) {
			System.out.println("[로그] 받아온 검색 파라미터 값 :" + searchField + "," + searchInput);
			searchDTO.setSearchField(searchField);
			searchDTO.setSearchInput(searchInput);

		}

		if (request.getParameter("minPrice") != null && request.getParameter("maxPrice") != null) {
			int minPrice = Integer.parseInt(request.getParameter("minPrice"));
			int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
			System.out.println("[로그] 받아온 가격 파라미터 값 :" + minPrice + "," + maxPrice);
			searchDTO.setMinPrice(minPrice);
			searchDTO.setMaxPrice(maxPrice);
		}

		if (company != null) {
			for (int i = 0; i < company.length; i++) {
				cList.add(company[i]);
				System.out.println("[로그] 받아온 회사 파라미터 값 : " + company[i]);
			}
			searchDTO.setCompanyList(cList);
		}

		if (productcategory != null) {
			for (int i = 0; i < productcategory.length; i++) {
				pList.add(productcategory[i]);
				System.out.println("[로그] 받아온 제품 카테고리 파라미터 값 : " + productcategory[i]);
			}
			searchDTO.setProductcategoryList(pList);
		}

		if (state != null) {
			for (int i = 0; i < state.length; i++) {
				sList.add(state[i]);
				System.out.println("[로그] 받아온 상태 파라미터 값 : " + state[i]);
			}
			searchDTO.setStateList(sList);
		}

		// 검색 DAO를 통해 필터링된 데이터를 가져옴
		ArrayList<BoardDTO> filteredBoardDatas = searchDAO.selectAll(searchDTO);
		System.out.println("[로그] 필터된 데이터 : " + filteredBoardDatas);

		// 검색 결과를 JSON 형식으로 응답
		if (filteredBoardDatas != null) {
			Gson gson = new Gson();
			String jsonFilterBoardDatasStr = gson.toJson(filteredBoardDatas);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonFilterBoardDatasStr);
		}
	}

	/**
	 * POST 요청 처리
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}