package dto;

public class XFile {
	
	private int file_no;
	private String file_origin_name;
	private String file_store_name;
	private String file_size;
	
	@Override
	public String toString() {
		return "XFile [file_no=" + file_no + ", file_origin_name=" + file_origin_name + ", file_store_name="
				+ file_store_name + ", file_size=" + file_size + "]";
	}

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public String getFile_origin_name() {
		return file_origin_name;
	}

	public void setFile_origin_name(String file_origin_name) {
		this.file_origin_name = file_origin_name;
	}

	public String getFile_store_name() {
		return file_store_name;
	}

	public void setFile_store_name(String file_store_name) {
		this.file_store_name = file_store_name;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	
	
}
