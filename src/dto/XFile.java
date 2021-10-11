package dto;

public class XFile {
	
	private int file_no;
	private String fileOriginName;
	private String fileStoredName;
	private String fileSize;
	
	@Override
	public String toString() {
		return "XFile [file_no=" + file_no + ", fileOriginName=" + fileOriginName + ", fileStoredName=" + fileStoredName
				+ ", fileSize=" + fileSize + "]";
	}
	
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
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
