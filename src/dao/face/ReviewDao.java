package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XReview;
import util.Paging;

public interface ReviewDao {
	
	/**
	 * XReview테이블 전체 조회(페이징처리)
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<XReview> - XReview테이블 전체 조회 결과 리스트
	 */
	public List<XReview> selectAll(Connection conn, Paging paging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Review테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
}
