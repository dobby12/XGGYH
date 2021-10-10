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
		sql += "	 ) A";
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
				
				xask.setAsk_no( rs.getInt("ask_no"));
				xask.setMem_id( rs.getString("mem_id"));
				xask.setAsk_title( rs.getString("ask_title"));
				xask.setAsk_date( rs.getDate("ask_date"));
				xask.setAsk_kind( rs.getString("ask_kind"));
				xask.setAsk_state( rs.getString("ask_state"));
				
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
			ps.setInt(1, xaskno.getAsk_no() );
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				result.setAsk_no( rs.getInt("ask_no") );
				result.setMem_id( rs.getString("mem_id") );
				result.setAsk_title( rs.getString("ask_title") );
				result.setAsk_content( rs.getString("ask_content") );
				result.setAsk_date( rs.getDate("ask_date") );
				result.setAsk_kind( rs.getString("ask_kind") );
				result.setAsk_state( rs.getString("ask_state") );
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
	public String getNickByMem_id(Connection conn, XAsk xask) {
		
		String sql = "";
		sql += "SELECT mem_nick FROM xmem";
		sql += " WHERE mem_id = ?";
		
		String nick = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, xask.getMem_id());
			
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

}
