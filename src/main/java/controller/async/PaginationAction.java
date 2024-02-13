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
 * Servlet implementation class Pagenation
 */
@WebServlet("/pagination.do")
public class PaginationAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaginationAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String jsonFilteredBoardDatasStr = request.getParameter("jsonFilteredBoardDatas");
		String jsonBoardDatasStr = request.getParameter("jsonBoardDatas");
		int currentPage = Integer.parseInt(request.getParameter("page"));  
	//	boolean isFilterChanged = Boolean.parseBoolean(request.getParameter("isFilterChanged"));
		System.out.println(currentPage);
		System.out.println(jsonFilteredBoardDatasStr);
		System.out.println(jsonBoardDatasStr);
		
		
		
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<BoardDTO>>() {
		}.getType();
		ArrayList<BoardDTO> displayDatas = new ArrayList<>();

		if (jsonFilteredBoardDatasStr != null && !jsonFilteredBoardDatasStr.isEmpty()) {
			ArrayList<BoardDTO> filteredBoardDatas = gson.fromJson(jsonFilteredBoardDatasStr, listType);
			displayDatas = filteredBoardDatas;
		} else {
			ArrayList<BoardDTO> jsonBoardDatas = gson.fromJson(jsonBoardDatasStr, listType);
			displayDatas = jsonBoardDatas;
		}

	    int pageSize = 5;
	    int totalSize = displayDatas.size();
	    int totalPages = (int) Math.ceil((double) totalSize / pageSize);

	    
	    int startIndex = (currentPage - 1) * pageSize;
	    int endIndex = Math.min(startIndex + pageSize, totalSize);

	    ArrayList<BoardDTO> currentPageDatas = new ArrayList<>(displayDatas.subList(startIndex, endIndex));

	    JsonObject jsonPaginationgDatas = new JsonObject();
	    jsonPaginationgDatas.add("data", gson.toJsonTree(currentPageDatas));
	    jsonPaginationgDatas.addProperty("currentPage", currentPage);
	    jsonPaginationgDatas.addProperty("totalPages", totalPages);
	    
	    

	    PrintWriter out = response.getWriter();
	    out.write(jsonPaginationgDatas.toString());
	    out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
