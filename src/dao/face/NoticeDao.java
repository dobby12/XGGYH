package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XNotice;
import util.Paging;

public interface NoticeDao {

	/**
	 * XNotice 테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return List<XNotice> - XNotice 테이블 전체 조회 결과 리스트
	 */
	public List<XNotice> selectAll(Connection conn);

	/**
	 * XNotice 테이블 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @return List<Board> - XNotice 테이블 전체 조회 결과 리스트
	 */
	public List<XNotice> selectAll(Connection conn, Paging paging);
	
	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - Board테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);
	
	/**
	 * 특정 게시글 조회
	 * 
	 * @param noticeNo - 조회할 noticeNo를 가진 객체
	 * @return XNotice - 조회된 결과 객체
	 */
	public XNotice selectNoticeByNoticeNo(Connection conn, XNotice noticeNo);

	/**
	 * id를 이용해 name을 조회한다
	 * 
	 * @param viewNotice - 조회할 id를 가진 객체
	 * @return String - 관리자 이름
	 */
	public String selectNameByAdminId(Connection conn, XNotice viewNotice);
	
	/**
	 * 다음 게시글 번호 조회
	 * 
	 * 	게시글 테이블과 첨부파일 테이블에 입력될 공통 boardno값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextNoticeno(Connection conn);

	/**
	 * 첨부파일 조회
	 * 
	 * @param connection - DB연결 객체
	 * @param viewNotice - 첨부파일을 조회할 게시글번호 객체
	 * @return BoardFile - 조회된 첨부파일
	 */
	
}
