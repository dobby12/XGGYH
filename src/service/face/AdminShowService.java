package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XShow;
import util.Paging;

public interface AdminShowService {

	/**
	 * 페이징 객체 생성
	 * 
	 * 요청 파라미터 curPage를 구함
	 * XShow 테이블과 curPage값을 이용하여 Paging 객체 반환
	 * 
	 * @param req - 요청 정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 공연 리스트 조회, paging 처리
	 * 
	 * @param paging - paging 객체
	 * @return 공연 정보 리스트 반환
	 */
	public List<XShow> getShowList(Paging paging);


}
