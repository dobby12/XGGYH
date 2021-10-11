package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminShowDao;
import dto.XShow;
import util.Paging;

public class AdminShowDaoImpl implements AdminShowDao {

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int selectCntAll(Connection connection) {
		String sql = "";
		sql += "SELECT count(*) FROM xshow";
	
		int count = 0;
		
		
		try {
			ps = connection.prepareStatement(sql);
			
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
	public List<XShow> selectShowAll(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	 SELECT rownum rnum, S.* FROM (";
		sql += "		 SELECT show_no, admin_id, show_title, show_date, show_start, show_end";
		sql += "	 FROM xshow ORDER BY show_no DESC";
		sql += "	 ) S";
		sql += " ) xshow";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<XShow> showList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				XShow xshow = new XShow();
				
				xshow.setShowNo(rs.getInt("show_no"));
				xshow.setAdminId(rs.getString("admin_id"));
				xshow.setShowTitle(rs.getString("show_title"));
				xshow.setShowDate(rs.getDate("show_date"));
				xshow.setShowStart(rs.getDate("show_start"));
				xshow.setShowEnd(rs.getDate("show_end"));
				
				showList.add(xshow);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return showList;
		
	}

}
