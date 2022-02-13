package user.model.vo;

public class User {
	private String userId;
	private String userPw;
	private String name;
	private String userSSN;
	private String phone;
	private String email;
	private String psptNum;
	private String airlineMemberNum;
	private int milage;
	
	public User() {
		super();
	}
	
	public User(String userId, String userPw, String name, String userSSN, String phone, String email, String psptNum,
			String airlineMemberNum, int milage) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.userSSN = userSSN;
		this.phone = phone;
		this.email = email;
		this.psptNum = psptNum;
		this.airlineMemberNum = airlineMemberNum;
		this.milage = milage;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserSSN() {
		return userSSN;
	}
	public void setUserSSN(String userSSN) {
		this.userSSN = userSSN;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPsptNum() {
		return psptNum;
	}
	public void setPsptNum(String psptNum) {
		this.psptNum = psptNum;
	}
	public String getAirlineMemberNum() {
		return airlineMemberNum;
	}
	public void setAirlineMemberNum(String airlineMemberNum) {
		this.airlineMemberNum = airlineMemberNum;
	}
	public int getMilage() {
		return milage;
	}
	public void setMilage(int milage) {
		this.milage = milage;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPw=" + userPw + ", name=" + name + ", userSSN=" + userSSN
				+ ", phone=" + phone + ", email=" + email + ", psptNum=" + psptNum + ", airlineMemberNum="
				+ airlineMemberNum + ", milage=" + milage + "]";
	}
}
