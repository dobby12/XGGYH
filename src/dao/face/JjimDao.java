package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XJjim;
import dto.XShow;
import util.Paging;

public interface JjimDao {

	/**
	 * XJjim테이블 memid 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @param conn - DB연결 객체
	 * @param memid
	 * @return List<XJjim> - XJjim테이블 memid 조회 결과 리스트
	 */
	public List<XShow> selectAllByMemid(Connection conn, Paging paging, String memid);

	/**
	 * 총 게시글 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - XJjim테이블 전체 행 수 조회 결과
	 */
	public int selectCntByMemId(Connection conn, String memid);
	
	public int insertJjim(Connection conn, XJjim jjim);

}
