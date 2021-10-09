package dto;

public class XComment {

	private int comment_no;
	private int ask_no;
	private String admin_id;
	private String comment_content;
	
	@Override
	public String toString() {
		return "XComment [comment_no=" + comment_no + ", ask_no=" + ask_no + ", admin_id=" + admin_id
				+ ", comment_content=" + comment_content + "]";
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getAsk_no() {
		return ask_no;
	}
	public void setAsk_no(int ask_no) {
		this.ask_no = ask_no;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	
	
}
