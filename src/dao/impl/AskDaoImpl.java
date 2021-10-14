package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AskDao;
import dto.XAsk;
import dto.XComment;
import util.Paging;

public class AskDaoImpl implements AskDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<XAsk> selectAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT * FROM xask";
		sql += " ORDER BY ask_no DESC";
		
		List<XAsk> askList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XAsk ask = new XAsk();
				
				ask.setAskNo( rs.getInt("askNo") );
				ask.setMemId( rs.getString("MemId") );
				ask.setAskTitle( rs.getString("askTitle") );
				ask.setAskContent( rs.getString("askContent") );
				ask.setAskDate( rs.getDate("askDate") );
				ask.setAskKind( rs.getString("askKind") );
				ask.setAskState( rs.getString("askState") );
				
				askList.add(ask);
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
	public List<XAsk> selectAll(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			ask_no, mem_id, ask_title, ask_content";
		sql += "			, ask_date, ask_kind, ask_state";
		sql += "		FROM xask";
		sql += "		ORDER BY ask_no DESC";
		sql += "	) X";
		sql += " ) XASK";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<XAsk> askList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XAsk ask = new XAsk();
				
				ask.setAskNo( rs.getInt("ask_no") );
				ask.setMemId( rs.getString("Mem_id") );
				ask.setAskTitle( rs.getString("ask_title") );
				ask.setAskContent( rs.getString("ask_content") );
				ask.setAskDate( rs.getDate("ask_date") );
				ask.setAskKind( rs.getString("ask_kind") );
				ask.setAskState( rs.getString("ask_state") );

				askList.add(ask);
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
	public int selectCntAll(Connection conn) {
		
		//SQL 작성
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
	public int selectNextAskNo(Connection conn) {
		
		String sql = "";
		sql += "SELECT xask_seq.nextval FROM dual";
		
		int nextAskNo = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nextAskNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return nextAskNo;
	}
	
	@Override
	public int insert(Connection conn, XAsk ask) {
		
		String sql = "";
		sql += "INSERT INTO xask(ASK_NO, MEM_ID, ASK_TITLE, ASK_CONTENT, ASK_KIND, ASK_STATE)";
		sql += " VALUES (?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, ask.getAskNo());
			ps.setString(2, ask.getMemId());
			ps.setString(3, ask.getAskTitle());
			ps.setString(4, ask.getAskContent());
			ps.setString(5, ask.getAskKind());
			ps.setString(6, ask.getAskState());

			System.out.println("[TEST]" + ask.getAskContent());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public List<XAsk> selectAllByMemid(Connection conn, Paging paging, String memid) {
		
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, X.* FROM (";
		sql += "		SELECT";
		sql += "			ask_no, mem_id, ask_title, ask_content";
		sql += "			, ask_date, ask_kind, ask_state";
		sql += "		FROM xask";
		sql += "		WHERE mem_id = ?";
		sql += "		ORDER BY ask_no DESC";
		sql += "	) X";
		sql += " ) XASK";
		sql += " WHERE rnum BETWEEN ? AND ?";

		List<XAsk> askList = new ArrayList<>(); 
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memid);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XAsk ask = new XAsk();
				
				ask.setAskNo( rs.getInt("ask_no") );
				ask.setMemId( rs.getString("Mem_id") );
				ask.setAskTitle( rs.getString("ask_title") );
				ask.setAskContent( rs.getString("ask_content") );
				ask.setAskDate( rs.getDate("ask_date") );
				ask.setAskKind( rs.getString("ask_kind") );
				ask.setAskState( rs.getString("ask_state") );

				askList.add(ask);
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
	public int selectCntByMemId(Connection conn, String memid) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM xask";
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
	public XAsk selectAskByAskNo(Connection conn, XAsk askNo) {
		
	String sql = "";
	sql += "SELECT * FROM xask";
	sql += " WHERE ask_no = ?";
	
	XAsk detailAsk = null;
	
	try {
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, askNo.getAskNo());
		
		rs = ps.executeQuery();
		
		while(rs.next()) {
			
			detailAsk = new XAsk(); //결과값 저장 객체
			
			detailAsk.setAskNo( rs.getInt("ask_no") );
			detailAsk.setMemId( rs.getString("mem_id") );
			detailAsk.setAskTitle( rs.getString("ask_title") );
			detailAsk.setAskContent( rs.getString("ask_content") );
			detailAsk.setAskDate( rs.getDate("ask_date") );
			detailAsk.setAskKind( rs.getString("ask_kind") );
			detailAsk.setAskState( rs.getString("ask_state") );
			
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		JDBCTemplate.close(rs);
		JDBCTemplate.close(ps);
	}
	
	return detailAsk;
	
	}
	
	@Override
	public XComment selectCommentByAskNo(Connection conn, XAsk askNo) {
		
		String sql ="";
		sql += "SELECT comment_no, ask_no, admin_id, comment_content";
		sql += " FROM XComment WHERE ask_no = ?";
		
		//조회된 결과를 저장할 객체
		XComment com = new XComment();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, askNo.getAskNo() );
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				com.setCommentNo(rs.getInt("comment_no"));
				com.setAskNo(rs.getInt("ask_no"));
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
}
