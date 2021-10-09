package dto;

public class XKind {
	
	private int kind_no;
	private String kind_name;
	
	@Override
	public String toString() {
		return "XKind [kind_no=" + kind_no + ", kind_name=" + kind_name + "]";
	}

	public int getKind_no() {
		return kind_no;
	}

	public void setKind_no(int kind_no) {
		this.kind_no = kind_no;
	}

	public String getKind_name() {
		return kind_name;
	}

	public void setKind_name(String kind_name) {
		this.kind_name = kind_name;
	}

}
