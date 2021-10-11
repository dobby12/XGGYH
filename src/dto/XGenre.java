package dto;

public class XGenre {
	
	private int genreNo;
	private String genreName;
	
	@Override
	public String toString() {
		return "XGenre [genreNo=" + genreNo + ", genreName=" + genreName + "]";
	}
	
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
