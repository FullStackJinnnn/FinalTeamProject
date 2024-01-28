package model.report;

public class ReportDTO {

	private int reportNum; // PK
	private String reporter;
	private String suspect;
	private String reportContents;
	private String reportDate;
	private String accountStop;
	// join 으로 가져올떄 필요한 멤버변수 .정석진
	private String id;				// memberNum 및 memberId ID로 변경 - 김도연 2024/01/27
	private int boardNum;
	private String title;

	public int getReportNum() {
		return reportNum;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getSuspect() {
		return suspect;
	}

	public void setSuspect(String suspect) {
		this.suspect = suspect;
	}

	public String getReportContents() {
		return reportContents;
	}

	public void setReportContents(String reportContents) {
		this.reportContents = reportContents;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getAccountStop() {
		return accountStop;
	}

	public void setAccountStop(String accountStop) {
		this.accountStop = accountStop;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ReportDTO [reportNum=" + reportNum + ", reporter=" + reporter + ", suspect=" + suspect
				+ ", reportContents=" + reportContents + ", reportDate=" + reportDate + ", accountStop=" + accountStop
				+ "]";
	}

}
