package model.board;

import java.sql.Date;

public class BoardDTO {
	private int boardNum;		// 게시글 번호
	private int memberNum;		// 게시글을 작성한 회원의 번호
	private String ID;			// 게시글을 작성한 회원의 ID
	private String nickname;	// 게시글을 작성한 회원의 닉네임
	private String grade;		// 게시글을 작성한 회원의 등급
	private String category;	// 게시글의 카테고리
	private String title;		// 게시글의 제목
	private String contents;	// 게시글 내용
	private String boardDate;		// 게시글 작성일
	private int price;					// 판매글 및 리뷰 게시글의 상품 가격
	private String image;				// 게시글 이미지 주소
	private String productcategory;		// 판매글 및 리뷰 게시글의 상품의 종류
	private String company;				// 판매글 및 리뷰 게시글의 상품 제조사
	private String state;		// 게시글의 상태(판매, 판매완료)
	private int viewCount;		// 게시글 조회수
	private int commendNum;		// 게시글과 조인한 추천테이블의 번호
	private int commendCount;	// 게시글의 추천수를 파악할 수 있는 변수
	private int reviewNum;		// 게시글과 조인한 댓글테이블의 번호
	private String reviewDate;	// 댓글의 작성일
	private String reviewContents;	// 댓글 내용

	private String searchCondision;	// 게시글 목록 검색용 변수

	private String updatePage;	 // 페이지 증가용 확인  변수

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCommendNum() {
		return commendNum;
	}

	public void setCommendNum(int commendNum) {
		this.commendNum = commendNum;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
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

	public int getCommendCount() {
		return commendCount;
	}

	public void setCommendCount(int commendCount) {
		this.commendCount = commendCount;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSearchCondision() {
		return searchCondision;
	}

	public void setSearchCondision(String searchCondision) {
		this.searchCondision = searchCondision;
	}

	public String getUpdatePage() {
		return updatePage;
	}

	public void setUpdatePage(String updatePage) {
		this.updatePage = updatePage;
	}

}
