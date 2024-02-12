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
        HttpSession session = request.getSession();
        
        ReportDAO reportDAO = new ReportDAO();
        ReportDTO reportDTO = new ReportDTO();
        
        // 신고를 하기 위한 값 설정
        reportDTO.setId(request.getParameter("suspectMemberID")); // 신고 당한 사람 ID
        reportDTO.setReporter((String)session.getAttribute("member")); // 신고자 ID
        reportDTO.setSuspect(request.getParameter("suspectMemberID")); // 신고 당한 사람 ID
        reportDTO.setReportContents(request.getParameter("reportContents")); // 신고 내용
        
        // 신고 후 신고한 페이지로 돌아가기
        String URL = request.getParameter("reportPageURL");
        String[] parts = URL.split("/", 5);
        String reportPageURL = parts[4];
        
        // System.out.println("로그 parts[4] : " + parts[4]);
        // cameraReviewSelectOnePage.do?boardNum=46
        
        boolean flag = reportDAO.insert(reportDTO);
        
        if (flag) {
            forward.setPath(reportPageURL);
            forward.setRedirect(false);
        } else {
            forward.setPath("error/alertPage.jsp");
            forward.setRedirect(true);
        }
        return forward;
    }
}