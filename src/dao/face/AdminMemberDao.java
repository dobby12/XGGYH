package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XMem;
import util.Paging;

public interface AdminMemberDao {

	/**
	 * 멤버 전체 명수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return - 회원 총 인원
	 */
	public int selectCntMemAll(Connection conn);

	/**
	 * 검색된 전체 회원 수 조회
	 * 
	 * @param conn
	 * @param searchtype - 검색 타입
	 * @param keyword - 검색어
	 * @return 전체 인원
	 */
	public int selectCntSearchMemAll(Connection conn, String searchtype, String keyword);
	
	/**
	 * 회원 전체 리스트 조회(페이징 있음)
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - paging객체
	 * @return 회원 전체 리스트 반환
	 */
	public List<XMem> selectMemAll(Connection conn, Paging paging);

	/**
	 * 회원 정보 삭제
	 * 
	 * @param conn
	 * @param memid
	 * @return
	 */
	public int deleteMem(Connection conn, XMem memid);
	
	/**
	 * 멤버 아이디로 검색
	 * 
	 * @param conn
	 * @param keyword
	 * @return 멤버 객체
	 */
	public List<XMem> selectMemSearchByMemid(Connection conn, String keyword, Paging paging);

	/**
	 * 멤버 닉네임으로 검색
	 * 
	 * @param conn
	 * @param keyword
	 * @return 멤버 객체
	 */
	public List<XMem> selectMemSearchByMemnick(Connection conn, String keyword, Paging paging);

}
