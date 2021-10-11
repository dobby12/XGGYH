package dto;

public class XAdmin {
	
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminAuthority;
	
	@Override
	public String toString() {
		return "XAdmin [adminId=" + adminId + ", adminPw=" + adminPw + ", adminName=" + adminName + ", adminAuthority="
				+ adminAuthority + "]";
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminAuthority() {
		return adminAuthority;
	}
	public void setAdminAuthority(String adminAuthority) {
		this.adminAuthority = adminAuthority;
	}	
}
