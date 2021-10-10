package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.ReviewDao;
import dto.XReview;
import util.Paging;

public class ReviewDaoImpl implements ReviewDao{
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public List<XReview> selectAll(Connection conn, Paging paging) {
		
				String sql = "";
				sql += "SELECT * FROM xreview";
				sql += " ORDER BY review_no DESC";
				
				List<XReview> reviewList = new ArrayList<>();
				
				try {
					ps = conn.prepareStatement(sql);
					
					rs = ps.executeQuery();
					
					while(rs.next()) {
						XReview r = new XReview();

						r.setReview_no( rs.getInt("review_no") );
						r.setShow_no( rs.getInt("show_no") );
						r.setFile_no( rs.getInt("file_no") );
						r.setMem_id( rs.getString("mem_id") );
						r.setReview_title( rs.getString("review_title") );
						r.setReview_content( rs.getString("review_content") );
						r.setReview_date( rs.getDate("review_date") );
						r.setReview_score( rs.getInt("review_score") );
						r.setReview_hit( rs.getInt("review_hit") );
						
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
	public int selectCntAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT count(*) FROM board";
		
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
}
