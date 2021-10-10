package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminReviewDao;
import dto.XReview;
import util.Paging;

public class AdminReviewDaoImpl implements AdminReviewDao {

	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int selectCntReviewAll(Connection conn) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT count(*) FROM xreview";

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

		return count; //전체 게시글의 수 반환
	}

	@Override
	public List<XReview> selectReviewAll(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM ("; 
		sql +=		"	SELECT rownum rnum, XR.* FROM (";
		sql +=		"		SELECT review_no, show_no, file_no, mem_id, review_title, review_content, review_date, review_score, review_hit ";
		sql +=		"			FROM xreview";
		sql +=		"			ORDER BY review_no DESC"; 
		sql +=		"		) XR"; 
		sql +=		"	)XREVIEW";
		sql +=		" WHERE rnum BETWEEN ? AND ?";
		
		List<XReview> reviewList = new ArrayList<XReview>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XReview review = new XReview();
				
				review.setReview_no(rs.getInt("review_no"));
				review.setShow_no(rs.getInt("show_no"));
				review.setFile_no(rs.getInt("file_no"));
				review.setMem_id(rs.getString("mem_id"));
				review.setReview_title(rs.getString("review_title"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_date(rs.getDate("review_date"));
				review.setReview_score(rs.getInt("review_score"));
				review.setReview_hit(rs.getInt("review_hit"));
				
				reviewList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewList;
	}

	@Override
	public XReview selectReviewbyReviewno(Connection conn, XReview reviewno) {
		
		String sql = "";
		sql += "SELECT * FROM Xreview";
		sql += " WHERE review_no = ?";
		
		XReview viewReview = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewno.getReview_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				viewReview = new XReview();
				
				viewReview.setReview_no(rs.getInt("review_no"));
				viewReview.setShow_no(rs.getInt("show_no"));
				viewReview.setFile_no(rs.getInt("file_no"));
				viewReview.setMem_id(rs.getString("mem_id"));
				viewReview.setReview_title(rs.getString("review_title"));
				viewReview.setReview_content(rs.getString("review_content"));
				viewReview.setReview_date(rs.getDate("review_date"));
				viewReview.setReview_score(rs.getInt("review_score"));
				viewReview.setReview_hit(rs.getInt("review_hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return viewReview;
	}

	@Override
	public String selectNickByMemid(Connection conn, XReview viewReview) {
		
		String sql = "";
		sql += "SELECT mem_nick FROM Xmem";
		sql += " WHERE mem_id = ?";
		
		String mem_nick = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, viewReview.getMem_id());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				mem_nick = rs.getString("mem_nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		 
		return mem_nick;
	}
	
	@Override
	public String selectShowTitleByShowno(Connection conn, XReview viewReview) {
		
		String sql = "";
		sql += "SELECT show_title FROM XShow";
		sql += " WHERE show_no = ?";
		
		String show_title = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewReview.getShow_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				show_title = rs.getString("show_title");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return show_title;
	}
	
	
}
