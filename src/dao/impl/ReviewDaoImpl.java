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
				
				r.setReviewNo( rs.getInt("review_no") );
				r.setShowNo( rs.getInt("show_no") );
				r.setFileNo( rs.getInt("file_no") );
				r.setMemId( rs.getString("Mem_id") );
				r.setReviewTitle( rs.getString("review_title") );
				r.setReviewContent( rs.getString("review_content") );
				r.setReviewDate( rs.getDate("review_date") );
				r.setReviewScore( rs.getInt("review_score") );
				r.setReviewHit( rs.getInt("review_hit") );
				
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
		sql += "SELECT mem_nick FROM mem";
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
	public int insert(Connection conn, XReview review) {
		
		String sql = "";
		sql += "INSERT INTO board(REVIEW_NO, SHOW_NO, FILE_NO, MEM_ID, REVIEW_TITLE, REVIEW_CONTENT, REVIEW_DATE, REVIEW_SCORE, REVIEW_HIT)";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review.getReviewNo());
			ps.setInt(2, review.getShowNo());
			ps.setInt(3, review.getFileNo());
			ps.setString(4, review.getMemId());
			ps.setString(5, review.getReviewTitle());
			ps.setString(6, review.getReviewContent());
			ps.setInt(7, review.getReviewScore());
			ps.setInt(8, review.getReviewHit());

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
	public int insertFile(Connection conn, XFile xFile) {

		String sql = "";
		sql += "INSERT INTO xfile( file_no, review_no, file_origin_name, file_stored_name, file_size )";
		sql += " VALUES( xreviewfile_seq.nextval, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, xFile.getFileNo());
			ps.setString(2, xFile.getFileOriginName());
			ps.setString(3, xFile.getFileStoredName());
			ps.setString(4, xFile.getFileSize());
			
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

		XFile xFile = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, viewReview.getReviewNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				xFile = new XFile();
				
				xFile.setFileNo( rs.getInt("file_no") );
				xFile.setFileOriginName( rs.getString("file_origin_name") );
				xFile.setFileStoredName( rs.getString("file_stored_name") );
				xFile.setFileSize( rs.getString("file_size") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
				
		return xFile;
	}
	
	@Override
	public int update(Connection conn, XReview review) {
		
		String sql = "";
		sql += "UPDATE xreview";
		sql += " SET review_title = ?,";
		sql += " 	review_content = ?";
		sql += " WHERE review_no = ?";
		
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, review.getReviewTitle());
			ps.setString(2, review.getReviewContent());
			ps.setInt(3, review.getReviewNo());

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