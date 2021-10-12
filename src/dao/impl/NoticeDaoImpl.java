package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dto.XNotice;
import util.Paging;

public class NoticeDaoImpl implements NoticeDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<XNotice> selectAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT * FROM xnotice";
		sql += " ORDER BY notice_no DESC";
		
		List<XNotice> noticeList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XNotice n = new XNotice();
				
				n.setNoticeNo( rs.getInt("noticeno") );
				n.setAdminId( rs.getString("adminid") );
				n.setFileNo( rs.getInt("fileNo") );
				n.setNoticeTitle( rs.getString("noticeTitle") );
				n.setNoticeContent( rs.getString("noticeContent") );
				n.setNoticeDate( rs.getDate("noticeDate") );

				noticeList.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeList;
	}
	
	@Override
	public List<XNotice> selectAll(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			notice_no, admin_id, file_no, notice_title";
		sql += "			, notice_content, notice_date";
		sql += "		FROM xnotice";
		sql += "		ORDER BY notice_no DESC";
		sql += "	) X";
		sql += " ) xnotice";
		sql += " WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 list
		List<XNotice> noticeList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XNotice notice = new XNotice();
				
				notice.setNoticeNo( rs.getInt("notice_no") );
				notice.setAdminId( rs.getString("admin_id") );
				notice.setFileNo( rs.getInt("file_no") );
				notice.setNoticeTitle( rs.getString("notice_title") );
				notice.setNoticeContent( rs.getString("notice_content") );
				notice.setNoticeDate( rs.getDate("notice_date") );

				noticeList.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return noticeList;
	}

	@Override
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM xnotice";
		
		//총 게시글 수
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
		
		return count;
	}
	
	@Override
	public XNotice selectNoticeByNoticeNo(Connection conn, XNotice noticeNo) {
			
		String sql = "";
		sql += "SELECT * FROM xnotice";
		sql += " WHERE notice_no = ?";
		
		XNotice viewNotice = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, noticeNo.getNoticeNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				viewNotice = new XNotice(); //결과값 저장 객체
				
				viewNotice.setNoticeNo( rs.getInt("notice_no") );
				viewNotice.setFileNo( rs.getInt("file_no") );
				viewNotice.setAdminId( rs.getString("Admin_id") );
				viewNotice.setNoticeTitle( rs.getString("notice_title") );
				viewNotice.setNoticeContent( rs.getString("notice_content") );
				viewNotice.setNoticeDate( rs.getDate("notice_date") );
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return viewNotice;
	}
	
	@Override
	public String selectNameByAdminId(Connection conn, XNotice viewNotice) {
		
		String sql = "";
		sql += "SELECT admin_name FROM xadmin";
		sql += " WHERE admin_id = ?";
		
		String adminname = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, viewNotice.getAdminId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				adminname = rs.getString("admin_name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return adminname;
	}
	
	@Override
	public int selectNextNoticeno(Connection conn) {
		
		String sql = "";
		sql += "SELECT xnotice_seq.nextval FROM dual";
		
		int nextNoticeNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextNoticeNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextNoticeNo;
	}
}
