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
import util.Paging;

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
	public int selectCntNoticeAll(Connection conn) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT count(*) FROM xnotice";

		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
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

		return count; //전체 공지 개수 반환
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

		String sql = "UPDATE XNOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=?";
		if(notice.getFileNo()==0) {
			sql += " WHERE NOTICE_NO=?";			
		} else {
			sql += ", FILE_NO=? WHERE NOTICE_NO=?";
		}
		int res = -1;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, notice.getNoticeTitle());
			ps.setString(2, notice.getNoticeContent());
			if(notice.getFileNo()==0) {
				ps.setInt(3, notice.getNoticeNo());
			} else {
				ps.setInt(3, notice.getFileNo());
				ps.setInt(4, notice.getNoticeNo());
			}
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

		System.out.println(noticeno);
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
		System.out.println(res);
		return res;
	}
































	@Override
	public List<XNotice> selectNoticeAll(Connection connection, Paging paging) {

		String sql = "SELECT * FROM (SELECT rownum rnum, XN.* FROM (SELECT NOTICE_NO, ADMIN_ID, FILE_NO, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_DATE FROM XNOTICE ORDER BY NOTICE_NO DESC) XN) XNOTICE WHERE RNUM BETWEEN ? AND ?";
		
		List<XNotice> list = new ArrayList<>();

		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
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
	public int deleteFileno(Connection conn, XNotice notice) {
		String sql = "UPDATE XNOTICE SET FILE_NO='' WHERE NOTICE_NO=?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getNoticeNo());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}





}
