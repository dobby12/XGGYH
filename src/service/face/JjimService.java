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
	public Paging getPaging(HttpServletRequest req, String memid);
	
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
	 * 멤버id 게시글 조회
	 *  페이징 처리 추가
	 *  
	 * @param paging - 페이징 정보 객체
	 * @param memid - 로그인 된 회원 아이디
	 * @return List<XShow>
	 */
	public List<XShow> getShowNoByMemId(Paging paging, String memid);

	/**
	 * XJjim 테이블의 정보를 memId, showNo 정보를 받아 DAO에 삭제를 요청함
	 * @param memId - 삭제를 요청한 회원 아이디
	 * @param showNo - 삭제를 해야 하는 공연 번호
	 * @return int - 삭제에 성공 했는지 실패 했는지 여부를 반환함
	 */
	int setJjimDelete(String memId, String showNo);
	
	/**
	 * 찜 여부를 true, false로 반환하는 메소드
	 * @param memId - 현재 로그인한 유저의 아이디
	 * @param showNo - 현재 상세보기 한 공연 정보의 공연번호
	 * @return 유저가 이미 찜을 했는지 안했는지 여부
	 */
	boolean getisJjim(String memId, int showNo);
}
