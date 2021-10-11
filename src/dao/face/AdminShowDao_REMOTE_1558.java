package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XShow;
import util.Paging;

public interface AdminShowDao {

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
