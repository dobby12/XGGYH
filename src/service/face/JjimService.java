package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XJjim;
import dto.XShow;

import util.Paging;

public interface JjimService {

	/**
	 * 페이징 객체 생성
	 * 
	 * @param req - 요청정보 객체
	 * @param memid
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	Paging getPagingByMemId(HttpServletRequest req, String memid);

	/**
	 * 멤버id 찜 목록 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param memid - 로그인 된 회원 아이디
	 * @return List<XJjim> - 멤버id 찜 목록 조회 결과 리스트
	 */
	List<XShow> getJjimListMemid(Paging paging, String memid);
	
	/**
	 * 
	 * @param jjim
	 * @return
	 */
	public int setJjim(XJjim jjim);

	/**
	 * jjim에 필요한 정보를 XJjim 형식 객체로 반환함
	 * @param req 연결 정보 객체
	 * @return req연결 객체로 받아온 XJjim 데이터
	 */
	public XJjim getJjimInfo(HttpServletRequest req);

	/**
	 * XJjim 테이블의 정보를 memId, showNo 정보를 받아 DAO에 삭제를 요청함
	 * @param memId - 삭제를 요청한 회원 아이디
	 * @param showNo - 삭제를 해야 하는 공연 번호
	 * @return int - 삭제에 성공 했는지 실패 했는지 여부를 반환함
	 */
	int setJjimDelete(String memId, String showNo);

}
