package dto;

import java.util.Date;

public class XAsk {
	
	private int ask_no;
	private String mem_id;
	private String ask_title;
	private String ask_content;
	private Date ask_date;
	private String ask_kind;
	private String ask_state;
	
	@Override
	public String toString() {
		return "XAsk [ask_no=" + ask_no + ", mem_id=" + mem_id + ", ask_title=" + ask_title + ", ask_content="
				+ ask_content + ", ask_date=" + ask_date + ", ask_kind=" + ask_kind + ", ask_state=" + ask_state + "]";
	}

	public int getAsk_no() {
		return ask_no;
	}

	public void setAsk_no(int ask_no) {
		this.ask_no = ask_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getAsk_title() {
		return ask_title;
	}

	public void setAsk_title(String ask_title) {
		this.ask_title = ask_title;
	}

	public String getAsk_content() {
		return ask_content;
	}

	public void setAsk_content(String ask_content) {
		this.ask_content = ask_content;
	}

	public Date getAsk_date() {
		return ask_date;
	}

	public void setAsk_date(Date ask_date) {
		this.ask_date = ask_date;
	}

	public String getAsk_kind() {
		return ask_kind;
	}

	public void setAsk_kind(String ask_kind) {
		this.ask_kind = ask_kind;
	}

	public String getAsk_state() {
		return ask_state;
	}

	public void setAsk_state(String ask_state) {
		this.ask_state = ask_state;
	}
	
}
