package dto;

public class XHall {
	
	private int hallNo;
	private String hallName;
	private String hallAddress;
	private String hallCall;
	private String hallLink;
	private String hallLocation;
	private String hallNavigation;
	
	@Override
	public String toString() {
		return "XHall [hallNo=" + hallNo + ", hallName=" + hallName + ", hallAddress=" + hallAddress + ", hallCall="
				+ hallCall + ", hallLink=" + hallLink + ", hallLocation=" + hallLocation + ", hallNavigation="
				+ hallNavigation + "]";
	}
	
	public int getHallNo() {
		return hallNo;
	}
	public void setHallNo(int hallNo) {
		this.hallNo = hallNo;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getHallAddress() {
		return hallAddress;
	}
	public void setHallAddress(String hallAddress) {
		this.hallAddress = hallAddress;
	}
	public String getHallCall() {
		return hallCall;
	}
	public void setHallCall(String hallCall) {
		this.hallCall = hallCall;
	}
	public String getHallLink() {
		return hallLink;
	}
	public void setHallLink(String hallLink) {
		this.hallLink = hallLink;
	}
	public String getHallLocation() {
		return hallLocation;
	}
	public void setHallLocation(String hallLocation) {
		this.hallLocation = hallLocation;
	}
	public String getHallNavigation() {
		return hallNavigation;
	}
	public void setHallNavigation(String hallNavigation) {
		this.hallNavigation = hallNavigation;
	}	
	
}
