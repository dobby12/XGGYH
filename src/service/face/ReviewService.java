package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XFile;
import dto.XReview;
import dto.XShow;
import util.Paging;

public interface ReviewService {

	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<XReview> - 게시글 전체 조회 결과 리스트
	 */
	public List<XReview> getList();

	/**
	 * 게시글 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<XReview> - 게시글 전체 조회 결과 리스트
	 */
	public List<XReview> getList(Paging paging);
	
	/**
	 * 멤버id 게시글 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param memid - 로그인 된 회원 아이디
	 * @return List<XReview> - 멤버id 게시글 조회 결과 리스트
	 */
	public List<XReview> getReviewListByMemid(Paging paging, String memid);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * XReview테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청정보 객체
	 * @param memid
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPagingByMemId(HttpServletRequest req, String memid);
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return XReview - 전달파라미터 review_no를 포함한 객체
	 */
	public XReview getReviewNo(HttpServletRequest req);

	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return XSHow - 전달파라미터 show_no를 포함한 객체
	 */
	public XShow getShowNo(HttpServletRequest req);
	

	/**
	 * 주어진 review_no를 이용하여 게시글을 조회한다
	 * 조회된 게시글의 조회수를 1 증가시킨다
	 * 
	 * @param review_no - review_no를 가지고 있는 객체
	 * @return XReview - 조회된 게시글
	 */
	public XReview view(XReview review_no);
	
	
	/**
	 * XReview 객체의 id 를 이용한 닉네임 조회
	 * 
	 * @param viewReview - 조회할 게시글 정보
	 * @return String - 게시글 작성자의 닉네임
	 */
	public String getMemNick(XReview viewReview);
	
	/**
	 * XReview테이블 show_no를 통해 show_title 조회
	 * 
	 * @param viewReview
	 * @return - 공연제목
	 */
	public String getShowTitle(XReview viewReview);
	
	/**
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 *  첨부파일을 함께 업로드 할 수 있도록 처리
	 * 
	 * @param req - 요청정보 객체(게시글내용 + 첨부파일)
	 * 
	 */
	public void write(HttpServletRequest req);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param viewReview - 첨부파일과 연결된 게시글번호를 포함한 DTO객체
	 * @return XFile - 첨부파일 정보 DTO객체
	 */
	public XFile viewFile(XReview viewReview);

	/**
	 * 전달받은 숫자를 reviewNo로 가진 XFile객체 반환
	 * 
	 * @param reviewNo - 조회할 reviewno
	 * @return DB에서 조회한 XFile 객체
	 */
	public XFile getFile(int reviewno);
	
	/**
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);

	/**
	 * 게시글 삭제
	 * 
	 * @param reviewno - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(int reviewno);

	/**
	 * 전달받은 숫자를 reviewno로 가진 XReview객체 반환
	 * @param reviewno - 조회할 reviewno
	 * @return DB에서 조회한 XReview객체
	 */
	public XReview getReviewDetail(int reviewno);
	
	
	/**
	 * 검색결과 페이징
	 * 
	 * @param req
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getParameterPaging(HttpServletRequest req);
	
	/**
	 * 
	 * @req - 요청파라미터로 searchtype, keyword가지고 있음
	 * @return parameter로 찾은 review객체 반환
	 */
	public List<XReview> searchReviewList(HttpServletRequest req, Paging paging);



}















