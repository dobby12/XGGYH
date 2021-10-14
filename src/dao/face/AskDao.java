package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XAsk;
import dto.XComment;
import util.Paging;

public interface AskDao {

	/**
	 * XAsk 테이블 전체 조회
	 * 
	 * @param connection - DB연결 객체
	 * @return List<XList> - XList 테이블 전체 조회 결과 리스트
	 */
	public List<XAsk> selectAll(Connection conn);

	/**
	 * XAsk 테이블 전체 조회
	 * 페이징 처리 추가
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<XAsk> - XAsk 테이블 전체 조회 결과 리스트
	 */
	public List<XAsk> selectAll(Connection conn, Paging paging);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - XAsk 테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 다음 게시글 번호 조회
	 * 
	 * 게시글 테이블에 입력될 공통 askno값을 시퀀스를 통해 조회한다
	 * 
	 * @param conn - DB연결 객체
	 * @return 다음 게시글 번호
	 */
	public int selectNextAskNo(Connection conn);
	
	/**
	 * 게시글 입력
	 * 
	 * @param ask - 삽입될 게시글 내용
	 */
	public int insert(Connection conn, XAsk ask);

	/**
	 * XAsk테이블 memid 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @param memid
	 * @return List<XAsk> - XAsk테이블 memid 조회 결과 리스트
	 */
	public List<XAsk> selectAllByMemid(Connection connection, Paging paging, String memid);

	/**
	 * 멤버 게시글 수 조회
	 * 
	 * @param conn
	 * @param memid
	 * @return int - XAsk테이블 memid 행 수 조회 결과
	 */
	public int selectCntByMemId(Connection connection, String memid);

	/**
	 * 
	 * @param conn
	 * @param askNo
	 * @return
	 */
	public XAsk selectAskByAskNo(Connection conn, XAsk askNo);

	/**
	 * 
	 * @param conn
	 * @param askNo
	 * @return
	 */
	public XComment selectCommentByAskNo(Connection conn, XAsk askNo);
	
}
