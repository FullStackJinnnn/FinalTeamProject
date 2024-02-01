package controller.report;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.front.Action;
import controller.front.ActionForward;
import model.report.ReportDAO;
import model.report.ReportDTO;

public class ReportWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		ReportDAO reportDAO = new ReportDAO();
		ReportDTO reportDTO = new ReportDTO();
		HttpSession session = request.getSession();
		reportDTO.setId((request.getParameter("suspectId")));
		reportDTO.setReporter((String)session.getAttribute("member"));
		reportDTO.setSuspect(request.getParameter("suspectId")); // 카테고리 세팅 안하면 오류남! else문으로 가기위한 더미값 입력
		reportDTO.setReportContents(request.getParameter("reportContents"));

		boolean flag = reportDAO.insert(reportDTO);
		System.out.println(reportDTO);
		if (flag) {
			forward.setPath("main.do");
			forward.setRedirect(false);
		} else {
			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);
		}
		return forward;

	}

}
