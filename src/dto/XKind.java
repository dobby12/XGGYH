package dto;

public class XKind {
	private int kindNo;
	private String kindName;
	
	@Override
	public String toString() {
		return "XKind [kindNo=" + kindNo + ", kindName=" + kindName + "]";
	}
	
	public int getKindNo() {
		return kindNo;
	}
	public void setKindNo(int kindNo) {
		this.kindNo = kindNo;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
}
