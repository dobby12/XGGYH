package dto;

import java.util.Date;

public class XReview {
	
	private int reviewNo;
	private int showNo;
	private int fileNo;
	private String memId;
	private String reviewTitle;
	private String reviewContent;
	private Date reviewDate;
	private int reviewScore;
	private int reviewHit;
	private String showTitle;
	
	@Override
	public String toString() {
		return "XReview [reviewNo=" + reviewNo + ", showNo=" + showNo + ", fileNo=" + fileNo + ", memId=" + memId
				+ ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", reviewDate=" + reviewDate
				+ ", reviewScore=" + reviewScore + ", reviewHit=" + reviewHit + ", showTitle=" + showTitle + "]";
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
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

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}

	public int getReviewHit() {
		return reviewHit;
	}

	public void setReviewHit(int reviewHit) {
		this.reviewHit = reviewHit;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

}
