package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XFile;
import dto.XNotice;
import util.Paging;

public interface AdminNoticeService {

	/**
	 * 모든 XNotice객체를 담은 List 요청
	 * @return XNotice객체가 담긴 List
	 */
	public List<XNotice> getNoticeList();

	/**
	 * 절달받은 숫자를 noticeno로 가진 XNotice객체 반환
	 * @param noticeno - 조회할 noticeno
	 * @return DB에서 조회한 XNotice객체
	 */
	public XNotice getNoticeDetail(int noticeno);

	/**
	 * 전달받은 숫자를 noticeno로 가진 XFile객체 반환
	 * @param noticeno - 조회할 noticeno
	 * @return DB에서 조회한 XFile객체
	 */
	public XFile getFile(int noticeno);

	/**
	 * 
	 * @param req
	 */
	public void setNotice(HttpServletRequest req);

	/**
	 * 
	 * @param req
	 */
	public void setNoticeUpdate(HttpServletRequest req);

	/**
	 * 
	 * @param noticeno
	 */
	public void setNoticeDelete(int noticeno);
	
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
	 * @param paging
	 * @return
	 */
	public List<XNotice> getNoticeList(Paging paging);


}
