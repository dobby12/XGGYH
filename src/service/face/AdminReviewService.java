package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XFile;
import dto.XReview;
import util.Paging;

public interface AdminReviewService {
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * XReview테이블과 curPage값을 이용, Paging객체를 구하여 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * XReview 게시판 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<XReview> - 리뷰게시판 전체 조회 결과 리스트
	 */
	public List<XReview> getReviewList(Paging paging);
	
	/**
	 * 요청파라미터로 Review번호 구하고, 객체 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return - Review객체
	 */
	public XReview getReviewno(HttpServletRequest req);

	/**
	 * reviewno객체를 통해 게시글 조회
	 * 
	 * @param reviewno
	 * @return Review - 조회된 게시글
	 */
	public XReview getReviewDetail(XReview reviewno);
	
	/**
	 * Review 객체의 mem_id를 통해 mem_nick조회
	 * 
	 * @param viewReview - 조회할 리뷰글 정보
	 * @return - 리뷰 작성자의 닉네임
	 */
	public String getNick(XReview viewReview);
	
	/**
	 * Review 객체의 show_no를 통해 show_title조회
	 * 
	 * @param viewReview
	 * @return - 리뷰 대상 공연
	 */
	public String getTitle(XReview viewReview);

	/**
	 * 게시글 삭제
	 * 
	 * @param reviewno - 요청 정보 객체
	 */
	public void setReviewDelete(XReview reviewno);
	
	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param viewReview - 파일 번호를 가지고 있는 리뷰 객체
	 * @return reviewFile - 첨부파일 정보 DTO객체
	 */
	public XFile getFile(XReview viewReview);

}
