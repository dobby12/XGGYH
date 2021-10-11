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
	
	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public List<XReview> selectAll(Connection conn) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM xreview";
		sql += " ORDER BY review_no DESC";
		
		//결과 저장할 List
		List<XReview> reviewList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				XReview r = new XReview(); //결과값 저장 객체
				
				//결과값 한 행 처리
				r.setReview_no( rs.getInt("review_no") );
				r.setShow_no( rs.getInt("show_no") );
				r.setFile_no( rs.getInt("file_no") );
				r.setMem_id( rs.getString("Mem_id") );
				r.setReview_title( rs.getString("review_title") );
				r.setReview_content( rs.getString("review_content") );
				r.setReview_date( rs.getDate("review_date") );
				r.setReview_score( rs.getInt("review_score") );
				r.setReview_hit( rs.getInt("review_hit") );
				
				//리스트에 결과값 저장
				reviewList.add(r);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return reviewList;
	}
	
	@Override
	public List<XReview> selectAll(Connection conn, Paging paging) {
		
		//SQL작성
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

		//결과 저장할 List
		List<XReview> reviewList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XReview review = new XReview();
				
				review.setReview_no( rs.getInt("review_no") );
				review.setShow_no( rs.getInt("show_no") );
				review.setFile_no( rs.getInt("file_no") );
				review.setMem_id( rs.getString("Mem_id") );
				review.setReview_title( rs.getString("review_title") );
				review.setReview_content( rs.getString("review_content") );
				review.setReview_date( rs.getDate("review_date") );
				review.setReview_score( rs.getInt("review_score") );
				review.setReview_hit( rs.getInt("review_hit") );

				//리스트에 결과값 저장
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
	public XReview selectReviewByReview_no(Connection conn, XReview review_no) {
			
		String sql = "";
		sql += "SELECT * FROM xreview";
		sql += " WHERE review_no = ?";
		
		XReview viewReview = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, review_no.getReview_no()); //조회할 게시글 번호 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			while(rs.next()) {
				viewReview = new XReview(); //결과값 저장 객체
				
				viewReview.setReview_no( rs.getInt("review_no") );
				viewReview.setShow_no( rs.getInt("show_no") );
				viewReview.setFile_no( rs.getInt("file_no") );
				viewReview.setMem_id( rs.getString("Mem_id") );
				viewReview.setReview_title( rs.getString("review_title") );
				viewReview.setReview_content( rs.getString("review_content") );
				viewReview.setReview_date( rs.getDate("review_date") );
				viewReview.setReview_score( rs.getInt("review_score") );
				viewReview.setReview_hit( rs.getInt("review_hit") );
				
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
	public int updateReview_hit(Connection conn, XReview review_no) {
		
		//SQL 작성
		String sql = "";
		sql += "UPDATE xreview";
		sql += " SET review_hit = review_hit + 1";
		sql += " WHERE review_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, review_no.getReview_no()); //조회할 게시글 번호 적용
			
			res = ps.executeUpdate(); //SQL 수행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	
	@Override
	public String selectNickByMem_id(Connection conn, XReview viewReview) {
		
		String sql = "";
		sql += "SELECT mem_nick FROM mem";
		sql += " WHERE mem_id = ?";
		
		String mem_nick = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setString(1, viewReview.getMem_id()); //조회할 id 적용
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
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
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO board(REVIEW_NO, SHOW_NO, FILE_NO, MEM_ID, REVIEW_TITLE, REVIEW_CONTENT, REVIEW_DATE, REVIEW_SCORE, REVIEW_HIT)";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, review.getReview_no());
			ps.setInt(2, review.getShow_no());
			ps.setInt(3, review.getFile_no());
			ps.setString(4, review.getMem_id());
			ps.setString(5, review.getReview_title());
			ps.setString(6, review.getReview_content());
			ps.setInt(7, review.getReview_score());
			ps.setInt(8, review.getReview_hit());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int selectNextReview_no(Connection conn) {
		
		String sql = "";
		sql += "SELECT xreview_seq.nextval FROM dual";
		
		//결과 저장 변수
		int nextReview_no = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextReview_no = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextReview_no;
	}
	
	@Override
	public int insertFile(Connection conn, XFile xFile) {

		String sql = "";
		sql += "INSERT INTO xfile( file_no, review_no, file_origin_name, file_stored_name, file_size )";
		sql += " VALUES( xreviewfile_seq.nextval, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, xFile.getFile_no());
			ps.setString(2, xFile.getFile_origin_name());
			ps.setString(3, xFile.getFile_stored_name());
			ps.setString(4, xFile.getFile_size());
			
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
			
			ps.setInt(1, viewReview.getReview_no());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				xFile = new XFile();
				
				xFile.setFile_no( rs.getInt("file_no") );
				xFile.setFile_origin_name( rs.getString("file_origin_name") );
				xFile.setFile_stored_name( rs.getString("file_stored_name") );
				xFile.setFile_size( rs.getString("file_size") );
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
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE xreview";
		sql += " SET review_title = ?,";
		sql += " 	review_content = ?";
		sql += " WHERE review_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, review.getReview_title());
			ps.setString(2, review.getReview_content());
			ps.setInt(3, review.getReview_no());

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
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE xreview";
		sql += " WHERE review_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());

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
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "DELETE xfile";
		sql += " WHERE file_no = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		int res = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getReview_no());

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}