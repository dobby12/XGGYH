package dto;

public class XJjim {
	
	private int jjimNo;
	private String memId;
	private int showNo;
	
	@Override
	public String toString() {
		return "XJjim [jjimNo=" + jjimNo + ", memId=" + memId + ", showNo=" + showNo + "]";
	}
	
	public int getJjimNo() {
		return jjimNo;
	}
	public void setJjimNo(int jjimNo) {
		this.jjimNo = jjimNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getShowNo() {
		return showNo;
	}
	public void setShowNo(int showNo) {
		this.showNo = showNo;
	}
}
