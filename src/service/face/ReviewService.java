package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XReview;
import util.Paging;

public interface ReviewService {

	/**
	 * 리뷰 전체 조회 페이징처리
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Review> - 리뷰 전체 조회 결과 리스트
	 */
	public List<XReview> getList(Paging reviewpaging);

	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * Review테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 요청 파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return XReview - 전달파라미터 reviewno를 포함한 객체
	 */
	public XReview getReviewno(HttpServletRequest req);
}
