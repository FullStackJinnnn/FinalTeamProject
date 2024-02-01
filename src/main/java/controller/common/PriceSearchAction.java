/*
 * package controller.common;
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import controller.front.Action; import controller.front.ActionForward; import
 * model.board.SearchDTO;
 * 
 * public class PriceSearchAction implements Action { }
 * 
 * @Override public ActionForward execute(HttpServletRequest request,
 * HttpServletResponse response) throws ServletException, IOException {
 * ActionForward forward = new ActionForward(); SearchDTO searchDTO = new
 * SearchDTO(); if (request.getParameter("pmin") != null &&
 * request.getParameter("pmax") !=null) { int price_min =
 * Integer.parseInt(request.getParameter("pmin")); int price_max =
 * Integer.parseInt(request.getParameter("pmax"));
 * searchDTO.setPrice_min(price_min); searchDTO.setPrice_max(price_max); }
 * request.setAttribute("boardDatas", boardDatas);
 * forward.setPath("board/cameraReviewSelectAllPage.jsp");
 * forward.setRedirect(false);
 * 
 * return forward; } }
 */