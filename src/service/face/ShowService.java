package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XShow;
import util.Paging;

public interface ShowService {

	/**
	 * 공연 정보 (XShow 테이블) 전체 조회
	 * @return List<XShow> - 공연 정보가 전체 조회 된 리스트
	 */
	public List<XShow> getShowList();

	/**
	 * 공연 정보 (XShow 테이블) 전체 조회
	 * 페이징 처리 추가 
	 * @return List<XShow> - 공연 정보가 전체 조회 된 리스트
	 */
	public List<XShow> getShowList(Paging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * XShow테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);
}
