package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XFile;
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

	/**
	 * 요청파라미터로 Show번호 구하고, 객체 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return - Show객체
	 */
	public XShow getShowno(HttpServletRequest req);
	
	/**
	 * showno 객체 
	 * 
	 * @param showno
	 * @return XShow - 조회된 게시글
	 */
	public XShow getShowDetail(XShow showno);

	/**
	 * Show객체의 genre_no를 통해 genre_name을 조회
	 * 
	 * @param viewShow
	 * @return genre_name
	 */
	public String getGenre(XShow viewShow);

	/**
	 * Show객체의 kind_no를 통해 kind_name을 조회
	 * 
	 * @param viewShow
	 * @return genre_name
	 */
	public String getKind(XShow viewShow);

	/**
	 * Show객체의 hall_no를 통해 hall_name을 조회
	 * 
	 * @param viewShow
	 * @return hall_name
	 */
	public String getHallname(XShow viewShow);

	/**
	 * 첨부파일 정보 조회
	 * 
	 * @param viewShow - 파일 번호를 가지고 있는 공연 객체
	 * @return showFile - 첨부파일 정보 객체
	 */
	public XFile getFile(XShow viewShow);
	
	/**
	 * 공연정보 삭제 
	 * 
	 * @param showno - 공연번호를 가진 공연 객체
	 */
	public void setShowDelete(XShow showno);

	/**
	 * 공연정보 작성
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void setShow(HttpServletRequest req);

	/**
	 * 공연정보 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void setShowUpdate(HttpServletRequest req);


}
