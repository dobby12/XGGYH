package dto;

import java.util.Date;

public class XMem {
	private String memId;
	private int genreNo;
	private String memPw;
	private String memNick;
	private String memMail;
	private String mailState;
	private Date memDate;
	
	@Override
	public String toString() {
		return "XMem [memId=" + memId + ", genreNo=" + genreNo + ", memPw=" + memPw + ", memNick=" + memNick
				+ ", memMail=" + memMail + ", mailState=" + mailState + ", memDate=" + memDate + "]";
	}
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemNick() {
		return memNick;
	}
	public void setMemNick(String memNick) {
		this.memNick = memNick;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMailState() {
		return mailState;
	}
	public void setMailState(String mailState) {
		this.mailState = mailState;
	}
	public Date getMemDate() {
		return memDate;
	}
	public void setMemDate(Date memDate) {
		this.memDate = memDate;
	}
}
