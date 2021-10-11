package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.XHall;

public interface HallService {
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return - req로 받아온 HallNo
	 */
	public int getHallNo(HttpServletRequest req);
	
	/**
	 * 주어진 HallNo를 이용하여 게시글을 조회한다
	 * 
	 * @param hallNo - getHallno로 받아온 hall_no
	 * @return XHall - 조회된 공연장 정보
	 */
	public XHall getHallInfo(int hallNo);

}
