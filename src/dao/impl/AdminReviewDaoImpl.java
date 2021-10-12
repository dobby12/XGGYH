package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminReviewDao;
import dto.XFile;
import dto.XMem;
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
				
				review.setReviewNo(rs.getInt("review_no"));
				review.setShowNo(rs.getInt("show_no"));
				review.setFileNo(rs.getInt("file_no"));
				review.setMemId(rs.getString("mem_id"));
				review.setReviewTitle(rs.getString("review_title"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewDate(rs.getDate("review_date"));
				review.setReviewScore(rs.getInt("review_score"));
				review.setReviewHit(rs.getInt("review_hit"));
				
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
			ps.setInt(1, reviewno.getReviewNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				viewReview = new XReview();
				
				viewReview.setReviewNo(rs.getInt("review_no"));
				viewReview.setShowNo(rs.getInt("show_no"));
				viewReview.setFileNo(rs.getInt("file_no"));
				viewReview.setMemId(rs.getString("mem_id"));
				viewReview.setReviewTitle(rs.getString("review_title"));
				viewReview.setReviewContent(rs.getString("review_content"));
				viewReview.setReviewDate(rs.getDate("review_date"));
				viewReview.setReviewScore(rs.getInt("review_score"));
				viewReview.setReviewHit(rs.getInt("review_hit"));
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
			ps.setString(1, viewReview.getMemId());
			
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
			ps.setInt(1, viewReview.getShowNo());
			
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
	
	@Override
	public XFile selectFile(Connection conn, XReview viewReview) {
		
		String sql = "";
		sql += "SELECT * FROM XFile";
		sql += " WHERE file_no = ?";
		
		XFile reviewFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewReview.getFileNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				reviewFile = new XFile();
				
				reviewFile.setFileNo(rs.getInt("file_no"));
				reviewFile.setFileOriginName(rs.getString("file_origin_name"));
				reviewFile.setFileStoredName(rs.getString("file_stored_name"));
				reviewFile.setFileSize(rs.getString("file_size"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return reviewFile;
	}
	
	@Override
	public int deleteReview(Connection conn, XReview reviewno) {
		
		String sql = "";
		sql += "DELETE xreview";
		sql += " WHERE review_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewno.getReviewNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}
	
	@Override
	public int deleteReviewFile(Connection conn, XReview reviewno) {
		
		String sql = "";
		sql += "DELETE xfile";
		sql += " WHERE file_no = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewno.getFileNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public List<XReview> selectReviewByMemid(Connection conn, Paging paging, XMem reviewMem) {
		
		String sql = "";
		sql += "SELECT * FROM ("; 
		sql +=		"	SELECT rownum rnum, XR.* FROM (";
		sql +=		"		SELECT review_no, show_no, file_no, mem_id, review_title, review_content, review_date, review_score, review_hit ";
		sql +=		"			FROM xreview";
		sql +=		"				WHERE mem_id = ?";
		sql +=		"			ORDER BY review_no DESC"; 
		sql +=		"		) XR"; 
		sql +=		"	)XREVIEW";
		sql +=		" WHERE rnum BETWEEN ? AND ?";
		
		List<XReview> memReviewList = new ArrayList<XReview>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, reviewMem.getMemId());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XReview review = new XReview();
				
				review.setReviewNo(rs.getInt("review_no"));
				review.setShowNo(rs.getInt("show_no"));
				review.setFileNo(rs.getInt("file_no"));
				review.setMemId(rs.getString("mem_id"));
				review.setReviewTitle(rs.getString("review_title"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewDate(rs.getDate("review_date"));
				review.setReviewScore(rs.getInt("review_score"));
				review.setReviewHit(rs.getInt("review_hit"));
				
				memReviewList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return memReviewList;
		
	}
}
