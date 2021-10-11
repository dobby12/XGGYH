package dto;

public class XComment {

	private int commentNo;
	private int askNo;
	private String adminId;
	private String commentContent;
	
	@Override
	public String toString() {
		return "XComment [commentNo=" + commentNo + ", askNo=" + askNo + ", adminId=" + adminId + ", commentContent="
				+ commentContent + "]";
	}
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getAskNo() {
		return askNo;
	}
	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}	
}
