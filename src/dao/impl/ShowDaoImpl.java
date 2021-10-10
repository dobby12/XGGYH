package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ShowDao;
import dto.XShow;

public class ShowDaoImpl implements ShowDao {

	//PreparedStatement 객체, ResultSet 객체 추가
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
	@Override
	public List<XShow> selectShowAll(Connection conn) {
		List<XShow> showList = null;
		
		String sql = "";
		sql += "SELECT * FROM XSHOW ";
		sql += "ORDER BY show_no";
		
		showList = new ArrayList<>();
		
		try {
			//쿼리 실행
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				//입력받을 객체
				XShow showInfo = new XShow();
				
				//행 하나씩 가져오기
				showInfo.setShow_no(rs.getInt("show_no"));
				showInfo.setFile_no(rs.getInt("file_no"));
				showInfo.setAdmin_id(rs.getString("admin_id"));
				showInfo.setKind_no(rs.getInt("kind_no"));
				showInfo.setGenre_no(rs.getInt("genre_no"));
				showInfo.setHall_no(rs.getInt("hall_no"));
				showInfo.setShow_title(rs.getString("show_title"));
				showInfo.setShow_content(rs.getString("show_content"));
				showInfo.setShow_date(rs.getDate("show_date"));
				showInfo.setShow_age(rs.getString("show_age"));
				showInfo.setShow_director(rs.getString("show_director"));
				showInfo.setShow_actor(rs.getString("show_actor"));
				showInfo.setShow_start(rs.getDate("show_start"));
				showInfo.setShow_end(rs.getDate("show_end"));
				
				showList.add(showInfo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//rs, ps객체 제거
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return showList;
	} 
}
