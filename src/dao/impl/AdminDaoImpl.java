package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.AdminDao;
import dto.XAdmin;

public class AdminDaoImpl implements AdminDao {

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int selectCntAdminByAdminidAdminpw(Connection connection, XAdmin admin) {
		
		int count = 0;	//성공 시 1로 반환될 결과 변수
		
		String sql = "SELECT COUNT(*) FROM XADMIN WHERE ADMIN_ID=? AND ADMIN_PW=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, admin.getAdmin_id());
			ps.setString(2, admin.getAdmin_pw());
			rs = ps.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return count;
		
	}

	@Override
	public XAdmin selectAdminByAdminid(Connection connection, XAdmin admin) {
		
		XAdmin res = null;
		
		String sql = "SELECT ADMIN_ID, ADMIN_NAME, ADMIN_AUTHORITY FROM XADMIN WHERE ADMIN_ID=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, admin.getAdmin_id());
			rs = ps.executeQuery();
			while(rs.next()) {
				res = new XAdmin();
				res.setAdmin_id(rs.getString("admin_id"));
				res.setAdmin_name(rs.getString("admin_name"));
				res.setAdmin_authority(rs.getString("admin_authority"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
}
