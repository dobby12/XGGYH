package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XAsk;
import util.Paging;

public interface AdminAskDao {

	/**
	 * 
	 * @param connection
	 * @return
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 게시판 리스트 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param paging - paging 정보 객체
	 * @return List<XAsk> - XAsk 테이블 전체 조회 결과
	 */
	public List<XAsk> selectAskAll(Connection conn, Paging paging);

	/**
	 * ask_no을 통해서 해당 게시글 정보 조회
	 * 
	 * @param conn - DB 연결 객체
	 * @param xaskno - 게시글 조회를 위한 ask_no
	 * @return XAsk 객체
	 */
	public XAsk selectAskByAskNo(Connection conn, XAsk xaskno);

	/**
	 * id를 이용
	 * @param conn - DB 이용 객체
	 * @param xask - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String getNickByMem_id(Connection conn, XAsk xask);


}