package dto;

import java.util.Date;

public class XShow {
	
	private int show_no;
	private int file_no;
	private String admin_id;
	private int kind_no;
	private int genre_no;
	private int hall_no;
	private String show_title;
	private String show_content;
	private Date show_date;
	private String show_age;
	private String show_director;
	private String show_actor;
	private Date show_start;
	private Date show_end;
	
	@Override
	public String toString() {
		return "XShow [show_no=" + show_no + ", file_no=" + file_no + ", admin_id=" + admin_id + ", kind_no=" + kind_no
				+ ", genre_no=" + genre_no + ", hall_no=" + hall_no + ", show_title=" + show_title + ", show_content="
				+ show_content + ", show_date=" + show_date + ", show_age=" + show_age + ", show_director="
				+ show_director + ", show_actor=" + show_actor + ", show_start=" + show_start + ", show_end=" + show_end
				+ "]";
	}

	public int getShow_no() {
		return show_no;
	}

	public void setShow_no(int show_no) {
		this.show_no = show_no;
	}

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public int getKind_no() {
		return kind_no;
	}

	public void setKind_no(int kind_no) {
		this.kind_no = kind_no;
	}

	public int getGenre_no() {
		return genre_no;
	}

	public void setGenre_no(int genre_no) {
		this.genre_no = genre_no;
	}

	public int getHall_no() {
		return hall_no;
	}

	public void setHall_no(int hall_no) {
		this.hall_no = hall_no;
	}

	public String getShow_title() {
		return show_title;
	}

	public void setShow_title(String show_title) {
		this.show_title = show_title;
	}

	public String getShow_content() {
		return show_content;
	}

	public void setShow_content(String show_content) {
		this.show_content = show_content;
	}

	public Date getShow_date() {
		return show_date;
	}

	public void setShow_date(Date show_date) {
		this.show_date = show_date;
	}

	public String getShow_age() {
		return show_age;
	}

	public void setShow_age(String show_age) {
		this.show_age = show_age;
	}

	public String getShow_director() {
		return show_director;
	}

	public void setShow_director(String show_director) {
		this.show_director = show_director;
	}

	public String getShow_actor() {
		return show_actor;
	}

	public void setShow_actor(String show_actor) {
		this.show_actor = show_actor;
	}

	public Date getShow_start() {
		return show_start;
	}

	public void setShow_start(Date show_start) {
		this.show_start = show_start;
	}

	public Date getShow_end() {
		return show_end;
	}

	public void setShow_end(Date show_end) {
		this.show_end = show_end;
	}
	
	

}
