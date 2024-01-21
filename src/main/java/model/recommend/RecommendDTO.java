package model.recommend;

import java.util.Date;

public class RecommendDTO {
	private int recommendNum; // 추천수 테이블 번호
	private int boardNum; // 게시글 번호
	private int memberNum; // 회원 번호

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

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

}
