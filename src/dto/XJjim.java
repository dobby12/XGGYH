package dto;

public class XJjim {
	
	private int jjim_no;
	private String mem_id;
	private int show_no;
	
	@Override
	public String toString() {
		return "Xjjim [jjim_no=" + jjim_no + ", mem_id=" + mem_id + ", show_no=" + show_no + "]";
	}

	public int getJjim_no() {
		return jjim_no;
	}

	public void setJjim_no(int jjim_no) {
		this.jjim_no = jjim_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getShow_no() {
		return show_no;
	}

	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}
	
}
