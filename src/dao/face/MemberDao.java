package dao.face;

import java.sql.Connection;

import dto.XMem;

public interface MemberDao {

	/**
	 * 전달받은 mem의 memid와 mempw가 일치하는 행의 개수 반환
	 * @param connection - DB연결객체
	 * @param mem - DB를 조회해 보려는 memid와 mempw가 저장된 XMem객체
	 * @return 1 == 아이디가 있으며 패스워드가 일치함
	 */
	public int selectCntMemByMemidMempw(Connection connection, XMem mem);

	/**
	 * 전달받은 mem의 memid와 일치하는 행의 mem_id, mem_nick 반환
	 * @param connection - DB연결객체
	 * @param mem - DB를 조회해 보려는 memid가 저장된 XMem객체
	 * @return mem_id, mem_nick이 저장된 XMem객체
	 */
	public XMem selectMemByMemid(Connection connection, XMem mem);

	/**
	 * 회원가입정보 삽입하기
	 * 
	 * @param member - 회원가입 정보 객체
	 */
	public int insert(Connection conn, XMem member);

	/**
	 * 
	 * @param parameter
	 * @return
	 */
	public String selectMemidByMemmail(Connection connection, String parameter);

	/**
	 * 
	 * @param connection
	 * @param mailForPw
	 * @param uuidPw
	 */
	public int updateMempw(Connection connection, String mailForPw, String uuidPw);

	/**
	 * 
	 * 
	 * @param connection - DB연결객체
	 * @param memid - 현재 로그인 중인 아이디
	 * @return
	 */
	public XMem selectMemByMemid(Connection connection, String memid);

	/**
	 * 
	 * @param conn
	 * @param mem
	 * @return
	 */
	public int updateMem(Connection conn, XMem mem);

	/**
	 * 
	 * @param conn
	 * @param memId
	 * @return
	 */
	public int deleteMem(Connection conn, XMem mem);
}
