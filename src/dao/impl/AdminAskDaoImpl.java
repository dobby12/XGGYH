package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminAskDao;
import dto.XAsk;
import dto.XComment;
import util.Paging;

public class AdminAskDaoImpl implements AdminAskDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int selectCntAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT count(*) FROM xask";
		
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
	public List<XAsk> selectAskAll(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	 SELECT rownum rnum, A.* FROM (";
		sql += "		 SELECT ask_no, mem_id, ask_title, ask_date, ask_kind, ask_state";
		sql += "	 FROM xask ORDER BY ask_no DESC";
		sql += "	 ) A ORDER BY ask_state ASC";
		sql += " ) xask";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		List<XAsk> askList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				XAsk xask = new XAsk();
				
				xask.setAskNo( rs.getInt("ask_no"));
				xask.setMemId( rs.getString("mem_id"));
				xask.setAskTitle( rs.getString("ask_title"));
				xask.setAskDate( rs.getDate("ask_date"));
				xask.setAskKind( rs.getString("ask_kind"));
				xask.setAskState( rs.getString("ask_state"));
				
				askList.add(xask);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return askList;
	}

	@Override
	public XAsk selectAskByAskNo(Connection conn, XAsk xaskno) {
		
		String sql ="";
		sql += "SELECT ask_no, mem_id, ask_title, ask_content, ask_date, ask_kind, ask_state";
		sql += " FROM XAsk WHERE ask_no = ?";
		
		//조회된 결과를 저장할 객체
		XAsk result = new XAsk();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, xaskno.getAskNo() );
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result.setAskNo( rs.getInt("ask_no") );
				result.setMemId( rs.getString("mem_id") );
				result.setAskTitle( rs.getString("ask_title") );
				result.setAskContent( rs.getString("ask_content") );
				result.setAskDate( rs.getDate("ask_date") );
				result.setAskKind( rs.getString("ask_kind") );
				result.setAskState( rs.getString("ask_state") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

	@Override
	public String getNickByMemId(Connection conn, XAsk xask) {
		
		String sql = "";
		sql += "SELECT mem_nick FROM xmem";
		sql += " WHERE mem_id = ?";
		
		String nick = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, xask.getMemId());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				nick = rs.getString("mem_nick");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nick;
	}

	@Override
	public int insertComment(Connection conn, XComment comment) {
		
		String sql = "";
		sql += "INSERT INTO xcomment( comment_no, ask_no, admin_id, comment_content)";
		sql += " VALUES ( xcomment_seq.nextval, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, comment.getAskNo());
			ps.setString(2, comment.getAdminId());
			ps.setString(3, comment.getCommentContent());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		
		return res;
	}

	@Override
	public XComment selectCommentByAskNo(Connection conn, XAsk xaskno) {
		
		String sql ="";
		sql += "SELECT comment_no, admin_id, comment_content";
		sql += " FROM XComment WHERE ask_no = ?";
		
		//조회된 결과를 저장할 객체
		XComment com = new XComment();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, xaskno.getAskNo() );
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				com.setCommentNo(rs.getInt("comment_no"));
				com.setAdminId(rs.getString("admin_id"));
				com.setCommentContent(rs.getString("comment_content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return com;
	}

	@Override
	public void deleteCommentByAskNo(Connection conn, XAsk xaskno) {
		
		String sql = "";
		sql += "DELETE xcomment WHERE ask_no = ?";
		
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, xaskno.getAskNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
	}

	@Override
	public int updateAskStateToN(Connection conn, XAsk xask) {
		
		String sql = "";
		sql += "UPDATE xask SET ask_state = 'n'";
		sql += " WHERE ask_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, xask.getAskNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int updateAskStateToY(Connection conn, XAsk xask) {
		String sql = "";
		sql += "UPDATE xask SET ask_state = 'y'";
		sql += " WHERE ask_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, xask.getAskNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int updateComment(Connection conn, XComment comment) {
		String sql = "";
		sql += "UPDATE xcomment SET admin_id = ?, comment_content = ?";
		sql += " WHERE comment_no = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, comment.getAdminId());
			ps.setString(2, comment.getCommentContent());
			ps.setInt(3, comment.getCommentNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
