package model.recommend;

import java.util.Date;

public class RecommendDTO {
	private int recommendNum; // 추천수 테이블 번호
	private int boardNum; // 게시글 번호
	private String id;		// memberNum 및 memberId ID로 변경 - 김도연 2024/01/27
	
	public int getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
