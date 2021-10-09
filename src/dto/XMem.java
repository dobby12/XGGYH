package dto;

import java.util.Date;

public class XMem {
	
	private String mem_id;
	private int genre_no;
	private String mem_pw;
	private String mem_nick;
	private String mem_mail;
	private String mail_state;
	private Date mem_date;
	
	@Override
	public String toString() {
		return "XMem [mem_id=" + mem_id + ", genre_no=" + genre_no + ", mem_pw=" + mem_pw + ", mem_nick=" + mem_nick
				+ ", mem_mail=" + mem_mail + ", mail_state=" + mail_state + ", mem_date=" + mem_date + "]";
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getGenre_no() {
		return genre_no;
	}

	public void setGenre_no(int genre_no) {
		this.genre_no = genre_no;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getMem_mail() {
		return mem_mail;
	}

	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}

	public String getMail_state() {
		return mail_state;
	}

	public void setMail_state(String mail_state) {
		this.mail_state = mail_state;
	}

	public Date getMem_date() {
		return mem_date;
	}

	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}
	
	
	

}
