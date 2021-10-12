package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.XFile;
import dto.XNotice;

public interface AdminNoticeDao {

	/**
	 * DB를 조회하여 모든 XNotice의 행을 List로 반환
	 * @param connection - DB연결객체
	 * @return XNotice의 각 행이 모두 담긴 List
	 */
	public List<XNotice> selectNoticeAll(Connection connection);
	
	/**
	 * DB를 조회하여 noticeno와 일치하는 행을 XNotice객체로 반환
	 * @param connection - DB연결객체
	 * @param noticeno - 조회할 공지사항 번호
	 * @return DB에서 가져 온 XNotice객체
	 */
	public XNotice selectNoticeByNoticeno(Connection connection, int noticeno);

	/**
	 * noticeno에 해당하는 XNOTICE의 fileno를 가진 XFile객체를 반환 
	 * @param connection - DB연결객체
	 * @param noticeno - 조회할 fileno를 가진 XNotice의 PK
	 * @return DB에서 가져 온 XFile객체
	 */
	public XFile selectFileByFileno(Connection connection, int noticeno);

	/**
	 * 
	 * @param conn
	 * @return
	 */
	public int selectNextNoticeno(Connection conn);

	/**
	 * 
	 * @param conn
	 * @param notice
	 * @return
	 */
	public int insertNotice(Connection conn, XNotice notice);

}
