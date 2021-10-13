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
	 * 공연 정보 (XShow 테이블) 전체 조회
	 * 페이징 처리 추가 
	 * @return List<XShow> - 공연 정보가 전체 조회 된 리스트
	 */
	public List<XShow> getShowList(Paging paging, int kindNo);
	
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
	
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * XShow테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	public Paging getParameterPaging(HttpServletRequest req, int kindNo);
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return XShow - 전달파라미터 ShowNo를 포함한 객체
	 */
	public XShow getShowNo(HttpServletRequest req);
	
	/**
	 * 요청파라미터 얻기
	 * 
	 * @param req - 요청정보객체
	 * @return XShow - 전달파라미터 kindNo를 포함한 객체
	 */
	public int getKindNo(HttpServletRequest req);

	/**
	 * 주어진 showNo를 이용하여 게시글을 조회한다
	 * 
	 * @param showNo - showNo를 가지고 있는 객체
	 * @return XShow - 조회된 게시글
	 */
	public XShow viewShowInfo(XShow showNo);

	/**
	 * kindNo를 통해 XKIND 테이블의 kind_name을 조회하고 반환 
	 * @param kindNo - 현재 보고있는 페이지의 kind_no
	 * @return - 현재 보고 있는 페이지의 종류
	 */
	public String getkindName(int kindNo);
	
	/**
	 * showInfo의 genreNo를 통해 XGENRE 테이블의 genre_name을 조회하고 반환
	 * @param showInfo - genreNo를 가지고 있는 객체
	 * @return - 공연장 이름
	 */
	public String getGenreName(XShow showInfo);
	
	/**
	 * showInfo의 hallNo를 통해 XHALL 테이블의 hall_name을 조회하고 반환
	 * @param showInfo - hallNo를 가지고 있는 객체
	 * @return - 공연장 이름
	 */
	public String getHallName(XShow showInfo);
}
