package dto;

public class XAdmin {
	
	private String admin_id;
	private String admin_pw;
	private String admin_name;
	private String admin_authority;
	
	@Override
	public String toString() {
		return "XAdmin [admin_id=" + admin_id + ", admin_pw=" + admin_pw + ", admin_name=" + admin_name
				+ ", admin_authority=" + admin_authority + "]";
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_pw() {
		return admin_pw;
	}

	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_authority() {
		return admin_authority;
	}

	public void setAdmin_authority(String admin_authority) {
		this.admin_authority = admin_authority;
	}
	
	
	

}
