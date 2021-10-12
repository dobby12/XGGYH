package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XShow;
import util.Paging;

public interface ShowDao {

	/**
	 * XShow 테이블의 정보 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<XReview> - XShow테이블의 전체 조회 결과 리스트를 반환함
	 */
	public List<XShow> selectShowAll(Connection conn);

	/**
	 * XShow 테이블의 정보 전체 조회 페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn   - DB연결 객체
	 * @return List<XReview> - XShow테이블의 전체 조회 결과 리스트를 반환함
	 */
	public List<XShow> selectShowAll(Connection conn, Paging paging);

	/**
	 * XShow 테이블의 정보 전체 조회 페이징 처리 추가, 카테고리 기능 추가
	 * 
	 * @param conn - DB 연결 객체
	 * @param paging - 페이징 정보 객체
	 * @param kindNo - 카테고리 종류
	 * @return List<XReview> - XShow테이블의 종류로만 따로 걸러진 리스트 반환함
	 */
	public List<XShow> selectShowAllByKindNo(Connection conn, Paging paging, int kindNo);

	/**
	 * XShow 테이블의 전체 행 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - XShow 테이블의 전체 행 수가 몇개인지 반환
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 특정 게시글 조회
	 * 
	 * @param showNo - 조회할 showNo를 가진 객체
	 * @return XShow - 조회된 결과 객체
	 */
	public XShow selectShowByShowno(Connection conn, XShow showNo);

	/**
	 * KindNo를 통해 KindName을 조회 : 나중에 이걸로 통일할거 같음
	 * 
	 * @param conn
	 * @param kindNo - 공연장 번호
	 * @return 공연 종류 이름
	 */
	public String selectKindNameByKindNo(Connection conn, int kindNo);
	
	/**
	 * showInfo의 GenreNo를 통해 GenreName을 조회
	 * 
	 * @param conn
	 * @param showInfo - 현재 상세보기 하고 있는 게시물의 GenreNo를 가진 객체
	 * @return 공연 장르 이름
	 */
	public String selectGenreNameByGenreNo(Connection conn, XShow showInfo);

	/**
	 * showInfo의 hallNo를 통해 hallName을 조회
	 * 
	 * @param conn
	 * @param showInfo - 현재 상세보기 하고 있는 게시물의 hallNo를 가진 객체
	 * @return 공연장 이름
	 */
	public String selectHallNameByHallNo(Connection conn, XShow showInfo);
}
