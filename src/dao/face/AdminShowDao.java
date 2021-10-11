package dao.face;

import java.sql.Connection;

import dto.XFile;
import dto.XShow;

import java.util.List;

import util.Paging;

public interface AdminShowDao {
	
	/**
	 * 특정 공연 정보 조회
	 * 
	 * @param conn
	 * @param showno
	 * @return 공연 객체
	 */
	public XShow selectShowByShowno(Connection conn, XShow showno);
	
	/**
	 * kind_no를 이용해서 kind_name 조회
	 * 
	 * @param conn
	 * @param showno
	 * @return kind_name
	 */
	public String selectKindByKindno(Connection conn, XShow viewShow);

	/**
	 * genre_no를 이용해서 genre_name조회
	 * 
	 * @param conn
	 * @param showno
	 * @return genre_name
	 */
	public String selectGenrebyGenreno(Connection conn, XShow viewShow);

	/**
	 * hall_no를 이용해서 hall_name 조회
	 * 
	 * @param conn
	 * @param showno
	 * @return hall_name
	 */
	public String selectHallnameByHallno(Connection conn, XShow viewShow);

	/**
	 * 첨부파일 조회
	 *
	 * @param conn
	 * @param viewShow
	 * @return
	 */
	public XFile selectFile(Connection conn, XShow viewShow);

	/**
	 * 페이지 수 구하기
	 * 
	 * @param connection - DB 정보 객체
	 * @return int
	 */
	public int selectCntAll(Connection connection);

	/**
	 * 게시판 리스트 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param paging - paging 정보 객체
	 * @return List<XShow> - XShow 테이블 전체 조회
	 */
	public List<XShow> selectShowAll(Connection conn, Paging paging);

}
