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

	/**
	 * 특정 리뷰 조회
	 * 
	 * @param connection
	 * @param reviewno
	 * @return
	 */
	public XReview selectReviewbyReviewno(Connection conn, XReview reviewno);
	
	/**
	 * id를 이용해서 nick을 조회한다
	 * 
	 * @param conn
	 * @param viewReview - 조회할 id를 가진 객체
	 * @return - 작성자 닉네임
	 */
	public String selectNickByMemid(Connection conn, XReview viewReview);

	/**
	 * showno를 이용해서 show_title을 조회한다
	 * 
	 * @param conn
	 * @param viewReview - 조회할 showno를 가진 객체
	 * @return - 공연 이름
	 */
	public String selectShowTitleByShowno(Connection conn, XReview viewReview);

}
