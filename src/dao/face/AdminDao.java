package dao.face;

import java.sql.Connection;

import dto.XAdmin;

public interface AdminDao {

	/**
	 * 전달받은 admin의 adminid와 adminpw가 일치하는 행의 개수 반환
	 * @param connection - DB연결객체
	 * @param admin - DB를 조회해 보려는 adminid와 adminpw가 저장된 XAdmin객체
	 * @return 1 == 아이디가 있으며 패스워드가 일치함
	 */
	public int selectCntAdminByAdminidAdminpw(Connection connection, XAdmin admin);

	/**
	 * 전달받은 admin의 adminid와 일치하는 행의 admin_id, admin_name, admin_authority 반환
	 * @param connection - DB연결객체
	 * @param admin - DB를 조회해 보려는 adminid가 저장된 XAdmin객체
	 * @return admin_id, admin_name, admin_authority가 저장된 XAdmin객체
	 */
	public XAdmin selectAdminByAdminid(Connection connection, XAdmin admin);
	
}
