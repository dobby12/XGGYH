package dto;

import java.util.Date;

public class XNotice {
	
	private int notice_no;
	private String admin_id;
	private int file_no;
	private String notice_title;
	private String notice_content;
	private Date notice_date;
	
	@Override
	public String toString() {
		return "XNotice [notice_no=" + notice_no + ", admin_id=" + admin_id + ", file_no=" + file_no + ", notice_title="
				+ notice_title + ", notice_content=" + notice_content + ", notice_date=" + notice_date + "]";
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	
	
	

}
