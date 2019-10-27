import java.util.GregorianCalendar;

public class Member {
	
	private String memberId;
	private String name;
	private int phoneNumber;
	private double feePaid;
	private String address;
	private GregorianCalendar joinDate;
	
	public Member(String name, String address, int phoneNumber, double feePaid) {
		// TODO Auto-generated constructor stub
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GregorianCalendar getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(GregorianCalendar joinDate) {
		this.joinDate = joinDate;
	}
	
	
	
	
}
