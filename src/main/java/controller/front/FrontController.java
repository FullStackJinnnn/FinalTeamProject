package controller.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapper handler;

	public FrontController() {

		super();
		handler = new HandlerMapper();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String uri = request.getRequestURI();
			String cp = request.getContextPath(); 

			String command = uri.substring(cp.length());

			System.out.println("[FrontController] action: " + command);
			System.out.println("[FrontController] uri : "+uri);
			System.out.println("[FrontController] cp : "+cp);
			System.out.println("[FrontController] command : "+command);

			Action action = handler.getAction(command);
			ActionForward forward = action.execute(request, response);
			
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
		
			// 오류가 발생한 경우, 오류 내용을 콘솔에 출력
			System.err.println("[FrontController] Error: " + e.getMessage());
			e.printStackTrace();

			// errorPage.do로 리다이렉트
			String errorCommand = "/errorPage.do";
			Action errorAction = handler.getAction(errorCommand);
			ActionForward errorForward = errorAction.execute(request, response);

			
			if (errorForward.isRedirect()) {
				response.sendRedirect(errorForward.getPath());
			} else {
				RequestDispatcher errorDispatcher = request.getRequestDispatcher(errorForward.getPath());
				errorDispatcher.forward(request, response);
			}
			
		}

	}

}
