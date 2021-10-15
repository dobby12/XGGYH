package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.JjimDao;
import dto.XJjim;
import dto.XShow;
import util.Paging;

public class JjimDaoImpl implements JjimDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int insertJjim(Connection conn, XJjim jjim) {
		String sql = "";
		sql += "INSERT INTO XJJIM( jjim_no, mem_id, show_no)";
		sql += " VALUES ( xjjim_seq.nextval, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, jjim.getMemId());
			ps.setInt(2, jjim.getShowNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

//	@Override
//	public XJjim selectAllByMemId(Connection conn, String memid) {
//
//		String sql = "";
//		sql += "SELECT * FROM XJJIM";
//		sql += "	WHERE MEM_ID = ?";
//		
//		XJjim jjim = null; 
//		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, memid);
//			
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				
//				jjim = new XJjim();
//				
//				jjim.setJjimNo( rs.getInt("jjim_no") );
//				jjim.setMemId( rs.getString("Mem_id") );
//				jjim.setShowNo( rs.getInt("show_no") );
//				
//				System.out.println(rs.getInt("jjim_no"));
//				System.out.println(rs.getString("Mem_id"));
//				System.out.println(rs.getInt("show_no"));
//
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(ps);
//		}
//		
//		return jjim;
//	}

	@Override
	public int selectCntByMemId(Connection conn, String memid) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM xjjim";
		sql += " WHERE mem_id = ?";
				
		//총 게시글 수
		int count = 0;
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memid);
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
	public List<XShow> selectShowByMemId(Connection conn, Paging paging, String memid) {

		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT * FROM XSHOW";
		sql += "	WHERE show_no IN (";
		sql += "		SELECT show_no FROM XJJIM";
		sql += "		WHERE mem_id = ?))";
		sql += "WHERE rownum BETWEEN ? AND ?";
		
		List<XShow> showList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memid);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			System.out.println(paging.getStartNo());
			System.out.println(paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XShow show = new XShow();

				show.setShowNo(rs.getInt("show_no"));
				show.setFileNo(rs.getInt("file_no"));
				show.setAdminId(rs.getString("admin_id"));
				show.setKindNo(rs.getInt("kind_no"));
				show.setGenreNo(rs.getInt("genre_no"));
				show.setHallNo(rs.getInt("hall_no"));
				show.setShowTitle(rs.getString("show_title"));
				show.setShowContent(rs.getString("show_content"));
				show.setShowDate(rs.getDate("show_date"));
				show.setShowAge(rs.getString("show_age"));
				show.setShowDirector(rs.getString("show_director"));
				show.setShowActor(rs.getString("show_actor"));
				show.setShowStart(rs.getDate("show_start"));
				show.setShowEnd(rs.getDate("show_end"));

				showList.add(show);
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
