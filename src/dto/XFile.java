package dto;

public class XFile {
	
	private int fileNo;
	private String fileOriginName;
	private String fileStoredName;
	private String fileSize;
	
	@Override
	public String toString() {
		return "XFile [fileNo=" + fileNo + ", fileOriginName=" + fileOriginName + ", fileStoredName=" + fileStoredName
				+ ", fileSize=" + fileSize + "]";
	}
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileOriginName() {
		return fileOriginName;
	}
	public void setFileOriginName(String fileOriginName) {
		this.fileOriginName = fileOriginName;
	}
	public String getFileStoredName() {
		return fileStoredName;
	}
	public void setFileStoredName(String fileStoredName) {
		this.fileStoredName = fileStoredName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}
