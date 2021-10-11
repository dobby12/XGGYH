package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.AdminShowDao;
import dto.XFile;
import dto.XShow;

public class AdminShowDaoImpl implements AdminShowDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;


	@Override
	public XShow selectShowByShowno(Connection conn, XShow showno) {

		String sql = "";
		sql += "SELECT * FROM Xshow";
		sql += " WHERE show_no = ?";

		XShow viewShow = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, showno.getShowNo());

			rs = ps.executeQuery();

			while(rs.next()) {
				viewShow = new XShow();

				viewShow.setShowNo(rs.getInt("show_no"));
				viewShow.setFileNo(rs.getInt("file_no"));
				viewShow.setAdminId(rs.getString("admin_id"));
				viewShow.setKindNo(rs.getInt("kind_no"));
				viewShow.setGenreNo(rs.getInt("genre_no"));
				viewShow.setHallNo(rs.getInt("hall_no"));
				viewShow.setShowTitle(rs.getString("show_title"));
				viewShow.setShowContent(rs.getString("show_content"));
				viewShow.setShowDate(rs.getDate("show_date"));
				viewShow.setShowAge(rs.getString("show_age"));
				viewShow.setShowDirector(rs.getString("show_director"));
				viewShow.setShowActor(rs.getString("show_actor"));
				viewShow.setShowStart(rs.getDate("show_start"));
				viewShow.setShowEnd(rs.getDate("show_end"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return viewShow;
	}


	@Override
	public String selectKindByKindno(Connection conn, XShow viewShow) {
		
		String sql = "";
		sql += "SELECT kind_name FROM XKIND";
		sql += " WHERE kind_no = ?";
		
		String kind_name = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewShow.getKindNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				kind_name = rs.getString("kind_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kind_name;
	}

	@Override
	public String selectGenrebyGenreno(Connection conn, XShow viewShow) {
		
		String sql = "";
		sql += "SELECT genre_name FROM XGENRE";
		sql += " WHERE genre_no = ?";
		
		String genre_name = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewShow.getGenreNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				genre_name = rs.getString("genre_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return genre_name;
	}

	@Override
	public String selectHallnameByHallno(Connection conn, XShow viewShow) {
		
		String sql = "";
		sql += "SELECT hall_name FROM XHALL";
		sql += " WHERE hall_no = ?";
		
		String hall_name = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewShow.getHallNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				hall_name = rs.getString("hall_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hall_name;
	}

	@Override
	public XFile selectFile(Connection conn, XShow viewShow) {

		String sql = "";
		sql += "SELECT * FROM XFile";
		sql += " WHERE file_no = ?";
		
		XFile showFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewShow.getFileNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				showFile = new XFile();
				
				showFile.setFileNo(rs.getInt("file_no"));
				showFile.setFileOriginName(rs.getString("file_origin_name"));
				showFile.setFileStoredName(rs.getString("file_stored_name"));
				showFile.setFileSize(rs.getString("file_size"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return showFile;
	
	}

}
