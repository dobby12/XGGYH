package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XMem;
import util.Paging;

public interface AdminMemberService {
	
	/**
	 * 
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
	 * 
	 * 회원 정보 전체 리스트 형식으로 조회
	 * 페이징 처리 있음.
	 *  
	 * @param paging
	 * @return List<XMem> - 전체 회원 정보 리스트
	 */
	public List<XMem> getMemList(Paging paging);

	/**
	 * 요청파라미터로 memId를 가지고 있는 Xmem객체를 반환
	 * 
	 * @param req
	 * @return memId정보를 가지고 있는 Xmem객체
	 */
	public XMem getMemId(HttpServletRequest req);

	/**
	 * 회원정보 삭제
	 * 
	 * @param memid - 삭제할 mem객체
	 */
	public void setMemDelete(XMem memid);
	
	/**
	 * 
	 * @req - 요청파라미터로 searchtype, keyword가지고 있음
	 * @return parameter로 찾은 mem객체 반환
	 */
	public List<XMem> searchMemList(HttpServletRequest req, Paging paging);

}
