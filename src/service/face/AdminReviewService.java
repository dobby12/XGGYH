package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

}
