package model.commend;

import java.util.Date;

public class CommendDTO {
	private int commendNum;	// 추천수 테이블 번호
	private int boardNum;	// 게시글 번호
	private int memberNum; // 회원 번호

	public int getCommendNum() {
		return commendNum;
	}

	public void setCommendNum(int commendNum) {
		this.commendNum = commendNum;
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
