package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XNotice;
import util.Paging;

public interface NoticeService {
	
	/**
	 * 게시글 전체 조회
	 * 
	 * @return List<XNotice> - 게시글 전체 조회 결과 리스트
	 */
	public List<XNotice> getNoticeList();

	/**
	 * 게시글 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<XNotice> - 게시글 전체 조회 결과 리스트
	 */
	public List<XNotice> getNoticeList(Paging paging);
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * XNotice 테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return XNotice - 전달파라미터 NoticeNo를 포함한 객체
	 */
	public XNotice getNoticeNo(HttpServletRequest req);
	
	/**
	 * XNotice 객체의 ID를 이용한 닉네임 조회
	 * 
	 * @param viewNotice - 조회할 게시글 정보
	 * @return String - 게시글 관리자의 닉네임
	 */
	public String getAdminName(XNotice viewNotice);

}
