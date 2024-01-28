package model.member;

public class MemberDTO {
	private int memberNum;
	private String id;		// memberNum 및 memberId ID로 변경 - 김도연 2024/01/27
	private String memberPW;
	private String name;
	private String nickname;
	private String birth;
	private String ph;
	private String profile;
	private String grade;

	private String searchCondition;
	// join 으로 가져올떄 필요한 멤버변수 .정석진
	private int boardNum;
	private String title;

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberPW() {
		return memberPW;
	}

	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
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

	@Override // 디버깅용 .정석진
	public String toString() {
		return "MemberDTO [memberNum=" + memberNum + ", ID=" + id + ", memberPW=" + memberPW + ", name=" + name
				+ ", nickname=" + nickname + ", birth=" + birth + ", ph=" + ph + ", profile=" + profile + ", grade="
				+ grade + "]";
	}

}
