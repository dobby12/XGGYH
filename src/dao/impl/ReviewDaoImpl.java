package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dto.XFile;
import dto.XReview;
import util.Paging;

public class ReviewDaoImpl implements ReviewDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<XReview> selectAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT * FROM xreview";
		sql += " ORDER BY review_no DESC";
		
		List<XReview> reviewList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XReview r = new XReview();
				
				r.setReviewNo( rs.getInt("reviewNo") );
				r.setShowNo( rs.getInt("showNo") );
				r.setFileNo( rs.getInt("fileNo") );
				r.setMemId( rs.getString("MemId") );
				r.setReviewTitle( rs.getString("reviewTitle") );
				r.setReviewContent( rs.getString("reviewContent") );
				r.setReviewDate( rs.getDate("reviewDate") );
				r.setReviewScore( rs.getInt("reviewScore") );
				r.setReviewHit( rs.getInt("reviewHit") );
				
				reviewList.add(r);
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
	public List<XReview> selectAll(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			review_no, show_no, file_no, mem_id, review_title";
		sql += "			, review_content, review_date, review_score, review_hit";
		sql += "		FROM xreview";
		sql += "		ORDER BY review_no DESC";
		sql += "	) X";
		sql += " ) XREVIEW";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<XReview> reviewList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XReview review = new XReview();
				
				review.setReviewNo( rs.getInt("review_no") );
				review.setShowNo( rs.getInt("show_no") );
				review.setFileNo( rs.getInt("file_no") );
				review.setMemId( rs.getString("Mem_id") );
				review.setReviewTitle( rs.getString("review_title") );
				review.setReviewContent( rs.getString("review_content") );
				review.setReviewDate( rs.getDate("review_date") );
				review.setReviewScore( rs.getInt("review_score") );
				review.setReviewHit( rs.getInt("review_hit") );

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
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM xreview";
		
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
	public XReview selectReviewByReviewNo(Connection conn, XReview reviewNo) {
			
		String sql = "";
		sql += "SELECT * FROM xreview";
		sql += " WHERE review_no = ?";
		
		XReview viewReview = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewNo.getReviewNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				viewReview = new XReview();
				
				viewReview.setReviewNo( rs.getInt("review_no") );
				viewReview.setShowNo( rs.getInt("show_no") );
				viewReview.setFileNo( rs.getInt("file_no") );
				viewReview.setMemId( rs.getString("Mem_id") );
				viewReview.setReviewTitle( rs.getString("review_title") );
				viewReview.setReviewContent( rs.getString("review_content") );
				viewReview.setReviewDate( rs.getDate("review_date") );
				viewReview.setReviewScore( rs.getInt("review_score") );
				viewReview.setReviewHit( rs.getInt("review_hit") );
				
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
	public XFile selectFileByFileNo(Connection conn, int reviewNo) {
		
		String sql = ""; 
		sql += "SELECT file_no, file_origin_name, file_stored_name, file_size FROM xfile";
		sql += " WHERE file_no = (SELECT file_no FROM xreview WHERE review_no = ?)";
		XFile res = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reviewNo);
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
	public int updateReviewHit(Connection conn, XReview reviewNo) {
		
		String sql = "";
		sql += "UPDATE xreview";
		sql += " SET review_hit = review_hit + 1";
		sql += " WHERE review_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reviewNo.getReviewNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public String selectNickByMemId(Connection conn, XReview viewReview) {
		
		String sql = "";
		sql += "SELECT mem_nick FROM xmem";
		sql += " WHERE mem_id = ?";
		
		String memNick = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, viewReview.getMemId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				memNick = rs.getString("mem_nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return memNick;
	}
	
	@Override
	public String selectShowTitleByShowNo(Connection conn, XReview viewReview) {
		
		String sql = "";
		sql += "SELECT show_title FROM xshow";
		sql += " WHERE show_no = ?";
		
		String showTitle = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, viewReview.getShowNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				showTitle = rs.getString("show_title");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return showTitle;
	}
	
	
	@Override
	public int insert(Connection conn, XReview review) {
		
		String sql = "";
		sql += "INSERT INTO xreview(review_no, show_no, mem_id, review_title,";
		sql += " review_content, review_score, review_hit)";
		sql += " VALUES (?, ?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review.getReviewNo());
			ps.setInt(2, review.getShowNo());
			ps.setString(3, review.getMemId());
			ps.setString(4, review.getReviewTitle());
			ps.setString(5, review.getReviewContent());
			ps.setInt(6, review.getReviewScore());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int selectNextReviewNo(Connection conn) {
		
		String sql = "";
		sql += "SELECT xreview_seq.nextval FROM dual";
		
		int nextReviewNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextReviewNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextReviewNo;
	}
	
	@Override
	public int selectNextShowNo(Connection conn) {
		
		String sql = "";
		sql += "SELECT xshow_seq.nextval FROM dual";
		
		int nextShowNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextShowNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextShowNo;
	}
	
	@Override
	public int insertFile(Connection conn, XFile xFile) {

		String sql = "";
		sql += "INSERT INTO xfile( file_no, file_origin_name, file_stored_name, file_size )";
		sql += " VALUES( xfile_seq.nextval, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, xFile.getFileOriginName());
			ps.setString(2, xFile.getFileStoredName());
			ps.setString(3, xFile.getFileSize());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public XFile selectFile(Connection conn, XReview viewReview) {
		
		String sql = "";
		sql += "SELECT * FROM xfile";
		sql += " WHERE file_no = ?";
		sql += " ORDER BY file_no";

		XFile reviewFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewReview.getFileNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				reviewFile = new XFile();
				
				reviewFile.setFileNo( rs.getInt("file_no") );
				reviewFile.setFileOriginName( rs.getString("file_origin_name") );
				reviewFile.setFileStoredName( rs.getString("file_stored_name") );
				reviewFile.setFileSize( rs.getString("file_size") );
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
	public int update(Connection conn, XReview review) {
		
		String sql = "";
		sql += "UPDATE xreview";
		sql += " SET review_title = ?,";
		sql += " review_content = ?,";
		sql += " review_score = ?";
		sql += " WHERE review_no = ?";
		
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, review.getReviewTitle());
			ps.setString(2, review.getReviewContent());
			ps.setInt(3,  review.getReviewScore());
			ps.setInt(4, review.getReviewNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int delete(Connection conn, XReview review) {
		
		String sql = "";
		sql += "DELETE xreview";
		sql += " WHERE review_no = ?";
		
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReviewNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public int deleteFile(Connection conn, XReview review) {
		
		String sql = "";
		sql += "DELETE xfile";
		sql += " WHERE file_no = ?";
		
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReviewNo());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}


}