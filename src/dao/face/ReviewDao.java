package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XFile;
import dto.XReview;
import util.Paging;

public interface ReviewDao {

	/**
	 * XReview테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<XReview> - XReview테이블 전체 조회 결과 리스트
	 */
	public List<XReview> selectAll(Connection conn);

	/**
	 * XReview테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<XReview> - XReview테이블 전체 조회 결과 리스트
	 */
	public List<XReview> selectAll(Connection conn, Paging paging);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - XReview테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 특정 게시글 조회
	 * 
	 * @param review_no - 조회할 review_no를 가진 객체
	 * @return XReview - 조회된 결과 객체
	 */
	public XReview selectReviewByReviewNo(Connection conn, XReview reviewNo);

	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param review_no - 조회된 게시글 번호를 가진 객체
	 */
	public int updateReviewHit(Connection conn, XReview reviewNo);

	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param viewReview - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByMemId(Connection conn, XReview viewReview);
	
	/**
	 * showNo를 이용해서 showTitle을 조회한다
	 * 
	 * @param conn
	 * @param viewReview - 조회할 showNo를 가진 객체
	 * @return 공연 제목
	 */
	public String selectShowTitleByShowNo(Connection conn, XReview viewReview);
	
	/**
	 * 게시글 입력
	 * 
	 * @param review - 삽입될 게시글 내용
	 */
	public int insert(Connection conn, XReview review);

	/**
	 * 다음 게시글 번호 조회
	 * 
	 * 	게시글 테이블과 첨부파일 테이블에 입력될 공통 review_no값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextReviewNo(Connection conn);

	/**
	 * 첨부파일 입력
	 * 
	 * @param conn - DB연결 객체
	 * @param reviewFile - 첨부파일 정보
	 * @return 삽입 결과
	 */
	public int insertFile(Connection conn, XFile xFile);

	/**
	 * 첨부파일 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param viewReview - 첨부파일을 조회할 게시글번호 객체
	 * @return ReviewFile - 조회된 첨부파일
	 */
	public XFile selectFile(Connection conn, XReview viewReview);

	/**
	 * 게시글 수정 
	 * 
	 * @param review - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, XReview review);

	/**
	 * 게시글 삭제
	 * 
	 * @param review - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, XReview review);
	
	/**
	 * 게시글에 첨부된 파일 기록 삭제
	 * 
	 * @param review - 삭제할 게시글번호를 담은 객체
	 */
	public int deleteFile(Connection conn, XReview review);


}

















