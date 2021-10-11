package dto;

import java.util.Date;

public class XAsk {
	
	private int askNo;
	private String memId;
	private String askTitle;
	private String askContent;
	private Date askDate;
	private String askKind;
	private String askState;
	
	@Override
	public String toString() {
		return "XAsk [askNo=" + askNo + ", memId=" + memId + ", askTitle=" + askTitle + ", askContent=" + askContent
				+ ", askDate=" + askDate + ", askKind=" + askKind + ", askState=" + askState + "]";
	}
	
	public int getAskNo() {
		return askNo;
	}
	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getAskTitle() {
		return askTitle;
	}
	public void setAskTitle(String askTitle) {
		this.askTitle = askTitle;
	}
	public String getAskContent() {
		return askContent;
	}
	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}
	public Date getAskDate() {
		return askDate;
	}
	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}
	public String getAskKind() {
		return askKind;
	}
	public void setAskKind(String askKind) {
		this.askKind = askKind;
	}
	public String getAskState() {
		return askState;
	}
	public void setAskState(String askState) {
		this.askState = askState;
	}	
}
