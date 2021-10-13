package dto;

import java.util.Date;

public class XShow {
	
	private int showNo;
	private int fileNo;
	private String adminId;
	private int kindNo;
	private int genreNo;
	private int hallNo;
	private String showTitle;
	private String showContent;
	private String showAge;
	private String showDirector;
	private String showActor;
	private Date showStart;
	private Date showEnd;
	private Date showDate;
	
	@Override
	public String toString() {
		return "XShow [showNo=" + showNo + ", fileNo=" + fileNo + ", adminId=" + adminId + ", kindNo=" + kindNo
				+ ", genreNo=" + genreNo + ", hallNo=" + hallNo + ", showTitle=" + showTitle + ", showContent="
				+ showContent + ", showDate=" + showDate + ", showAge=" + showAge + ", showDirector=" + showDirector
				+ ", showActor=" + showActor + ", showStart=" + showStart + ", showEnd=" + showEnd + "]";
	}
	
	public int getShowNo() {
		return showNo;
	}
	public void setShowNo(int showNo) {
		this.showNo = showNo;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public int getKindNo() {
		return kindNo;
	}
	public void setKindNo(int kindNo) {
		this.kindNo = kindNo;
	}
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}
	public int getHallNo() {
		return hallNo;
	}
	public void setHallNo(int hallNo) {
		this.hallNo = hallNo;
	}
	public String getShowTitle() {
		return showTitle;
	}
	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}
	public String getShowContent() {
		return showContent;
	}
	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}
	public Date getShowDate() { //공연정보 등록일 (sysdate)
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public String getShowAge() {
		return showAge;
	}
	public void setShowAge(String showAge) {
		this.showAge = showAge;
	}
	public String getShowDirector() {
		return showDirector;
	}
	public void setShowDirector(String showDirector) {
		this.showDirector = showDirector;
	}
	public String getShowActor() {
		return showActor;
	}
	public void setShowActor(String showActor) {
		this.showActor = showActor;
	}
	public Date getShowStart() {
		return showStart;
	}
	public void setShowStart(Date showStart) {
		this.showStart = showStart;
	}
	public Date getShowEnd() {
		return showEnd;
	}
	public void setShowEnd(Date showEnd) {
		this.showEnd = showEnd;
	}
}
