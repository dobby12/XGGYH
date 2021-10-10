package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XReview;
import util.Paging;

public interface AdminReviewDao {
	
	/**
	 * 리뷰게시판 게시글 전체 개수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return 게시글 전체 개수
	 */
	public int selectCntReviewAll(Connection conn);

	/**
	 * 리뷰게시판 전체리스트 조회(페이징 있음)
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - paging객체
	 * @return 리뷰게시판 전체 리스트(페이징 있음)
	 */
	public List<XReview> selectReviewAll(Connection conn, Paging paging);

}
