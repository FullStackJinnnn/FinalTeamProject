package controller.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.board.BoardDTO;

/**
 * PaginationAction: 비동기적으로 페이지 처리를 하는 서블릿
 */
@WebServlet("/pagination.do")
public class PaginationAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * 기본 생성자
     */
    public PaginationAction() {
        super();
    }

    /**
     * GET 요청 처리
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 클라이언트로부터 받은 데이터를 추출
        String jsonFilteredBoardDatasStr = request.getParameter("jsonFilteredBoardDatas");
        String jsonBoardDatasStr = request.getParameter("jsonBoardDatas");
        int currentPage = Integer.parseInt(request.getParameter("page"));  

        Gson gson = new Gson();
        
        // Gson 라이브러리를 사용하여 JSON 문자열을 Java 객체로 변환할 때 사용
        // Gson은 JSON 문자열을 Java 객체로 변환하는데 필요한 타입 정보를 알아야 하는데, 이를 위해 TypeToken 클래스를 제공
        // Gson은 Type 객체를 통해 변환 대상이 ArrayList<BoardDTO>라는 것을 알 수 있음
        Type listType = new TypeToken<ArrayList<BoardDTO>>() {}.getType();
        ArrayList<BoardDTO> displayDatas = new ArrayList<>();

        // 필터링된 데이터가 있을 경우 필터링된 데이터를 사용하고, 없을 경우 원본 데이터를 사용
        if (jsonFilteredBoardDatasStr != null && !jsonFilteredBoardDatasStr.isEmpty()) {
            ArrayList<BoardDTO> filteredBoardDatas = gson.fromJson(jsonFilteredBoardDatasStr, listType);
            displayDatas = filteredBoardDatas;
        } else {
            ArrayList<BoardDTO> jsonBoardDatas = gson.fromJson(jsonBoardDatasStr, listType);
            displayDatas = jsonBoardDatas;
        }

        // 페이지당 데이터 개수와 전체 데이터 개수를 설정
        int pageSize = 20;
        int totalSize = displayDatas.size();
        int totalPages = (int) Math.ceil((double) totalSize / pageSize);

        // 현재 페이지의 시작 인덱스 계산
        int startIndex = (currentPage - 1) * pageSize;
        
        // 현재 페이지의 끝 인덱스를 계산
        // 인덱스가 전체 크기를 초과하지 않도록 Math.min을 사용
        int endIndex = Math.min(startIndex + pageSize, totalSize);

        // 현재 페이지의 데이터를 추출
        ArrayList<BoardDTO> currentPageDatas = new ArrayList<>(displayDatas.subList(startIndex, endIndex));

        // JSON 객체를 생성하고, 필요한 정보를 추가
        JsonObject jsonPaginationDatas = new JsonObject();
        jsonPaginationDatas.add("boardDatas", gson.toJsonTree(currentPageDatas));
        jsonPaginationDatas.addProperty("currentPage", currentPage);
        jsonPaginationDatas.addProperty("totalPages", totalPages);

        // 찾은 정보를 JSON 형식으로 클라이언트에게 응답
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonPaginationDatas.toString());
        //out.close();
    }

    /**
     * POST 요청 처리
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); 
    }
}