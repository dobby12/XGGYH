package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.HallDao;
import dto.XHall;

public class HallDaoImpl implements HallDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public XHall selectHallNameByHallNo(Connection conn, int hallNo) {
		
		String sql = "";
		sql += "SELECT * FROM XHALL";
		sql += " WHERE hall_no = ?";
		
		XHall hallInfo = new XHall();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, hallNo);

			rs = ps.executeQuery();

			while (rs.next()) {
				hallInfo.setHallNo(rs.getInt("hall_no"));
				hallInfo.setHallName(rs.getString("hall_name"));
				hallInfo.setHallAddress(rs.getString("hall_address"));
				hallInfo.setHallCall(rs.getString("hall_call"));
				hallInfo.setHallLink(rs.getString("hall_link"));
				hallInfo.setHallLocation(rs.getString("hall_location"));
				hallInfo.setHallNavigation(rs.getString("hall_navigation"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return hallInfo;
	}
}
