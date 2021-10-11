package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XFile;
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
	 * @return 리뷰 객체
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
	 * show_no를 이용해서 show_title을 조회한다
	 * 
	 * @param conn
	 * @param viewReview - 조회할 showno를 가진 객체
	 * @return - 공연 이름
	 */
	public String selectShowTitleByShowno(Connection conn, XReview viewReview);
	
	/**
	 * 리뷰글 삭제
	 * 
	 * @param conn
	 * @param reviewno
	 * @return
	 */
	public int deleteReview(Connection conn, XReview reviewno);
	
	/**
	 * 첨부파일 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param viewReview - 첨부파일을 조회할 리뷰 객체
	 * @return - 조회할 첨부파일 객체
	 */
	public XFile selectFile(Connection conn, XReview viewReview);
	
	/**
	 * 게시글 파일 삭제
	 * 
	 * @param conn
	 * @param reviewno
	 * @return
	 */
	public int deleteReviewFile(Connection conn, XReview reviewno);

}
