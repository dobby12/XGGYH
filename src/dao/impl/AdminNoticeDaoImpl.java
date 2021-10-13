package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminNoticeDao;
import dto.XFile;
import dto.XNotice;

public class AdminNoticeDaoImpl implements AdminNoticeDao {

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public List<XNotice> selectNoticeAll(Connection connection) {

		String sql = "SELECT NOTICE_NO, ADMIN_ID, FILE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE FROM XNOTICE ORDER BY NOTICE_NO DESC";
		
		List<XNotice> list = new ArrayList<>();
		
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				XNotice notice = new XNotice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setAdminId(rs.getString("admin_id"));
				notice.setFileNo(rs.getInt("file_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeDate(rs.getDate("notice_date"));
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public XNotice selectNoticeByNoticeno(Connection connection, int noticeno) {
		
		String sql = "SELECT NOTICE_NO, ADMIN_ID, FILE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE FROM XNOTICE WHERE NOTICE_NO=?";
		XNotice res = null;

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, noticeno);
			rs = ps.executeQuery();
			while(rs.next()) {
				res = new XNotice();
				res.setNoticeNo(rs.getInt("notice_no"));
				res.setAdminId(rs.getString("admin_id"));
				res.setFileNo(rs.getInt("file_no"));
				res.setNoticeTitle(rs.getString("notice_title"));
				res.setNoticeContent(rs.getString("notice_content"));
				res.setNoticeDate(rs.getDate("notice_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}










	
	
	
	
	
	

	@Override
	public XFile selectFileByFileno(Connection connection, int noticeno) {

		String sql = "SELECT FILE_NO, FILE_ORIGIN_NAME, FILE_STORED_NAME, FILE_SIZE FROM XFILE WHERE FILE_NO=(SELECT FILE_NO FROM XNOTICE WHERE NOTICE_NO=?)";
		XFile res = null;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, noticeno);
			rs = ps.executeQuery();
			while(rs.next()) {
				res = new XFile();
				res.setFileNo(rs.getInt("file_no"));
				res.setFileOriginName(rs.getString("file_origin_name"));
				res.setFileStoredName(rs.getString("file_stored_name"));
				res.setFileSize(rs.getString("file_size"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}











	@Override
	public int selectNextNoticeno(Connection conn) {
		String sql = "SELECT XNOTICE_SEQ.NEXTVAL FROM DUAL";
		int nextNoticeno = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				nextNoticeno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		 
		return nextNoticeno;
	}











	@Override
	public int insertNotice(Connection conn, XNotice notice) {
		
		String sql = "INSERT INTO XNOTICE(NOTICE_NO, ADMIN_ID, FILE_NO, NOTICE_TITLE, NOTICE_CONTENT)"
				+ " VALUES(?, ?, ?, ?, ?)";

		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getNoticeNo());
			ps.setString(2,  notice.getAdminId());
			if(notice.getFileNo()==0) {
				ps.setObject(3, null);
			} else {
				ps.setInt(3, notice.getFileNo());
			}
			ps.setString(4, notice.getNoticeTitle());
			ps.setString(5, notice.getNoticeContent());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}






	
	
	
	
	
	





	@Override
	public int updateNotice(Connection connection, XNotice notice) {

		String sql = "UPDATE XNOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=?, FILE_NO=? WHERE NOTICE_NO=?";
		int res = -1;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, notice.getNoticeTitle());
			ps.setString(2, notice.getNoticeContent());
			ps.setInt(3, notice.getFileNo());
			ps.setInt(4, notice.getNoticeNo());
			System.out.println("@@@1@@@" + notice);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
	
		return res;
	}





	
	



	@Override
	public int deleteNotice(Connection conn, int noticeno) {
		
		String sql = "DELETE XNOTICE WHERE NOTICE_NO=?";
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeno);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}






	

	
	



	
	
	
	
	
	
	
	













	
	
	
	
	
	
	
	
	
	
	
	

}
