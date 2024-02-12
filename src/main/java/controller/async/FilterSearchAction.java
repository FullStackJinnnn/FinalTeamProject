package controller.async;

import java.io.IOException;
import java.io.PrintWriter;
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
        // 클라이언트로부터 전달받은 파라미터들을 사용하여 필터링 및 정렬 작업을 위한 값들을 설정
        String company[] = request.getParameterValues("company"); // 다중 선택이 가능한 항목들은 배열 형태로 저장
        String productcategory[] = request.getParameterValues("productcategory");
        String state[] = request.getParameterValues("state");
        String category = request.getParameter("category");
        String searchField = request.getParameter("searchField");
        String searchInput = request.getParameter("searchInput");
        String jsonOrderColumnDirection = request.getParameter("jsonOrderColumnDirection"); // 정렬 기준과 정렬 방향을 Map 형태로 받아옴
        String priceSort = request.getParameter("priceSort");
        String id = request.getParameter("id");

        // Map을 사용하여 정렬 기준과 방향 정보를 담기 위한 객체 생성
        Map<String, String> orderMap = new HashMap<>();

        // 선택된 checkbox 정보를 담기 위한 ArrayList 객체 생성
        ArrayList<String> cList = new ArrayList<>();
        ArrayList<String> pList = new ArrayList<>();
        ArrayList<String> sList = new ArrayList<>();

        SearchDTO searchDTO = new SearchDTO();
        SearchDAO searchDAO = new SearchDAO();

        // 게시판 구분을 위한 값 설정
        searchDTO.setCategory(category);
        searchDTO.setId(id);

        // 가격 정렬을 선택 했다면 값 설정, null 이어도 문제없음
        searchDTO.setPriceSort(priceSort);

        // json 형태의 데이터를 Map 타입으로 변경  {"boardNum":"DESC"} => {boardNum=DESC}
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

            searchDTO.setOrderColumnDirection(orderMap);
        }

        // 제목, 작성자, 내용 등 일반 검색을 위한 값 설정
        if (searchField != null && !searchInput.equals("")) {
            searchDTO.setSearchField(searchField);
            searchDTO.setSearchInput(searchInput);
        }

        // 카메라 가격의 최대, 최소값 구간 검색을 위한 값 설정
        if (request.getParameter("minPrice") != null && request.getParameter("maxPrice") != null) {
            int minPrice = Integer.parseInt(request.getParameter("minPrice"));
            int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
            searchDTO.setMinPrice(minPrice);
            searchDTO.setMaxPrice(maxPrice);
        }

        // 카메라 제조사 검색을 위한 값 설정
        if (company != null) {
            for (int i = 0; i < company.length; i++) {
                cList.add(company[i]);
            }
            searchDTO.setCompanyList(cList);
        }

        // 카메라 종류 검색을 위한 값 설정
        if (productcategory != null) {
            for (int i = 0; i < productcategory.length; i++) {
                pList.add(productcategory[i]);
            }
            searchDTO.setProductcategoryList(pList);
        }

        // 판매글의 경우 카메라의 판매 상태 검색을 위한 값 설정
        if (state != null) {
            for (int i = 0; i < state.length; i++) {
                sList.add(state[i]);
            }
            searchDTO.setStateList(sList);
        }

        // searchDAO를 통해 필터링된 데이터를 가져옴
        ArrayList<BoardDTO> filteredBoardDatas = searchDAO.selectAll(searchDTO);
        System.out.println("[로그] 필터된 데이터: " + filteredBoardDatas);

        // 검색 결과를 JSON 형식으로 클라이언트에게 응답
        if (filteredBoardDatas != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String jsonFilterBoardDatasStr = gson.toJson(filteredBoardDatas);
            out.print(jsonFilterBoardDatasStr);
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
