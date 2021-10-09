package dto;

public class XHall {
	
	private int hall_no;
	private String hall_name;
	private String hall_address;
	private String hall_call;
	private String hall_link;
	private String hall_location;
	private String hall_navigation;
	
	@Override
	public String toString() {
		return "Xhall [hall_no=" + hall_no + ", hall_name=" + hall_name + ", hall_address=" + hall_address
				+ ", hall_call=" + hall_call + ", hall_link=" + hall_link + ", hall_location=" + hall_location
				+ ", hall_navigation=" + hall_navigation + "]";
	}

	public int getHall_no() {
		return hall_no;
	}

	public void setHall_no(int hall_no) {
		this.hall_no = hall_no;
	}

	public String getHall_name() {
		return hall_name;
	}

	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}

	public String getHall_address() {
		return hall_address;
	}

	public void setHall_address(String hall_address) {
		this.hall_address = hall_address;
	}

	public String getHall_call() {
		return hall_call;
	}

	public void setHall_call(String hall_call) {
		this.hall_call = hall_call;
	}

	public String getHall_link() {
		return hall_link;
	}

	public void setHall_link(String hall_link) {
		this.hall_link = hall_link;
	}

	public String getHall_location() {
		return hall_location;
	}

	public void setHall_location(String hall_location) {
		this.hall_location = hall_location;
	}

	public String getHall_navigation() {
		return hall_navigation;
	}

	public void setHall_navigation(String hall_navigation) {
		this.hall_navigation = hall_navigation;
	}

}
