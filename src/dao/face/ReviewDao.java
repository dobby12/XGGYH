package dao.face;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XFile;
import dto.XReview;
import dto.XShow;
import util.Paging;

public interface ReviewDao {

//	/**
//	 * XReview테이블 전체 조회
//	 * 
//	 * @param conn - DB연결 객체
//	 * @return List<XReview> - XReview테이블 전체 조회 결과 리스트
//	 */
//	public List<XReview> selectAll(Connection conn);

	/**
	 * XReview테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<XReview> - XReview테이블 전체 조회 최신순 결과 리스트
	 */
	public List<XReview> selectAll(Connection conn, Paging paging);

	/**
	 * XReview테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<XReview> - XReview테이블 전체 조회 조회순 결과 리스트
	 */
	public List<XReview> selectAllHit(Connection connection, Paging paging);

	
	/**
	 * XReview테이블 memid 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @param memid
	 * @return List<XReview> - XReview테이블 memid 조회 결과 리스트
	 */
	public List<XReview> selectAllByMemid(Connection conn, Paging paging, String memid);
	
	/**
	 * XReview테이블 memid 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @param memid
	 * @return List<XReview> - XReview테이블 memid 조회 결과 리스트
	 */

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - XReview테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 멤버 게시글 수 조회
	 * 
	 * @param conn
	 * @param memid
	 * @return int - XReview테이블 memid 행 수 조회 결과
	 */
	public int selectCntByMemId(Connection conn, String memid);
	
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
	public int insert(Connection conn, XReview review, XShow show);

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
	 * 다음 게시글 번호 조회
	 * 
	 * 	게시글 테이블과 공연 테이블에 입력될 공통 show_no값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextShowNo(Connection conn);

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
	 * reviewNo에 해당하는 XReview의 fileNo를 가진 XFile객체를 반환
	 * 
	 * @param conn - DB연결 객체
	 * @param reviewNo - 조회할 fileNo를 가진 XReview의 PK
	 * @return DB에서 가져온 XFile의 객체
	 */
	public XFile selectFileByFileNo(Connection conn, int reviewno);

	/**
	 * 게시글 수정 
	 * 
	 * @param review - 수정할 내용을 담은 객체
	 */
	public int update(Connection conn, XReview review);

	/**
	 * 게시글 삭제
	 * 
	 * @param reviewno - 삭제할 게시글번호를 담은 객체
	 */
	public int delete(Connection conn, int reviewno);

	/**
	 * DB를 조회하여 reviewno와 일치하는 행을 XReview객체로 반환
	 * @param conn - DB연결객체
	 * @param reviewno - 조회할 공지사항 번호
	 * @return DB에서 가져 온 XReivew객체
	 */
	public XReview selectReviewToReviewno(Connection conn, int reviewno);

	/**
	 * 검색된 전체 리뷰 수 조회
	 * 
	 * @param conn
	 * @param searchtype - 검색 타입
	 * @param keyword - 검색어
	 * @return 전체 리뷰
	 */
	public int selectCntSearchReviewAll(Connection conn, String searchtype, String keyword);

	/**
	 * 리뷰 제목으로 검색
	 * 
	 * @param conn
	 * @param keyword
	 * @return 리뷰 객체
	 */
	public List<XReview> selectReviewSearchByReviewTitle(Connection conn, String keyword, Paging paging);

	/**
	 * review_no로 조회하여 xreivew의 file_no만 null로 바꾼다
	 * @param conn
	 * @param review
	 * @return
	 */
	public int deleteFileno(Connection conn, XReview review);

	/**
	 * XReview의 showno로 조회하여 reviewScore의 평균값을 구한다
	 * @param conn
	 * @param showno 공연 번호
	 * @return 공연 평균값
	 */
	public double selectAvgReviewScoreByShowNo(Connection conn, int showNo);

	/**
	 * XReview에서 showno와 memid가 모두 일치하는 행의 개수를 반환 
	 * @param connection
	 * @param req
	 * @return
	 */
	public int selectCntReviewByShowNoAndMemId(Connection connection, HttpServletRequest req);

	public int selectCntAllByShowNo(Connection connection, int showNo);
}

















