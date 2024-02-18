package model.review;


public class ReviewDTO {
	private int reviewNum;
	private int boardNum;
	private String id;
	private String reviewDate;
	private String writer; // 닉네임
	String reviewContents; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewContents() {
		return reviewContents;
	}

	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}

	@Override
	public String toString() {
		return "ReviewDTO [reviewNum=" + reviewNum + ", boardNum=" + boardNum + ", id=" + id + ", reviewDate="
				+ reviewDate + ", writer=" + writer + ", reviewContents=" + reviewContents + "]";
	}
	
}
