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
		reportDTO.setId((request.getParameter("suspectMemberID"))); //신고 당한사람 ID
		reportDTO.setReporter((String)session.getAttribute("member")); //신고자 ID
		reportDTO.setSuspect(request.getParameter("suspectMemberID")); //신고 당한사람 ID
		reportDTO.setReportContents(request.getParameter("reportContents")); //신고내용 
		String URL = request.getParameter("reportPageURL");
		System.out.println(URL);
        String[] parts = URL.split("/", 5);
        System.out.println(parts[4]);
        String reportPageURL = parts[4];
        
		boolean flag = reportDAO.insert(reportDTO);
		System.out.println(reportDTO);
		if (flag) {
			forward.setPath("error/alertPage.jsp");
			request.setAttribute("status","reportSuccess");
			request.setAttribute("path",reportPageURL);
			forward.setRedirect(false);
		} else {
			forward.setPath("error/alertPage.jsp");
			forward.setRedirect(true);
		}
		return forward;

	}

}
