package vo;

public class Member {

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private int phone;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(int memberNo, String memberId, String membmerPw, String memberName, int phone) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = membmerPw;
		this.memberName = memberName;
		this.phone = phone;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String membmerPw) {
		this.memberPw = membmerPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	
}
