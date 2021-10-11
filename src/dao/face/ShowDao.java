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
	 * XShow 테이블의 정보 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<XReview> - XShow테이블의 전체 조회 결과 리스트를 반환함
	 */
	public List<XShow> selectShowAll(Connection conn, Paging paging);

	/**
	 * XShow 테이블의 전체 행 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - XShow 테이블의 전체 행 수가 몇개인지 반환
	 */
	public int selectCntAll(Connection conn);
}
