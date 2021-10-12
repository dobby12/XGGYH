package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.face.AdminMemberDao;
import dto.XMem;
import util.Paging;

public class AdminMemberDaoImpl implements AdminMemberDao {
	
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public int selectCntMemAll(Connection conn) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT count(*) FROM xmem";

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

		return count; //전체 회원 수 반환
	}

	@Override
	public List<XMem> selectMemAll(Connection conn, Paging paging) {
		
		String sql = "";
		sql += "SELECT * FROM ("; 
		sql +=	"	SELECT rownum rnum, XM.* FROM (";
		sql +=	"		SELECT mem_id, genre_no, mem_pw, mem_nick, mem_mail, mail_state, mem_date";
		sql +=	"			FROM xmem";
		sql +=	"			ORDER BY mem_date DESC"; 
		sql +=	"		) XM"; 
		sql +=	"	)XMEM";
		sql +=	" WHERE rnum BETWEEN ? AND ?";
		
		List<XMem> memList = new ArrayList<XMem>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XMem member = new XMem();
				
				member.setMemId(rs.getString("mem_id"));
				member.setGenreNo(rs.getInt("genre_no"));
				member.setMemNick(rs.getString("mem_nick"));
				member.setMemPw(rs.getString("mem_pw"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMailState(rs.getString("mail_state"));
				member.setMemDate(rs.getDate("mem_date"));
				
				memList.add(member);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return memList;
	}
	
	@Override
	public int deleteMem(Connection conn, XMem memid) {
		
		String sql = "";
		sql += "DELETE xmem";
		sql += " WHERE mem_id = ?";
		
		int res = -1;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, memid.getMemId());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	
	}
	
	@Override
	public List<XMem> selectMemSearchByMemid(Connection conn, String keyword) {
		
		String sql = "";
		sql += "SELECT * FROM XMEM"; 
		sql += " WHERE mem_id like ?";

		List<XMem> searchMemList = new ArrayList<XMem>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XMem member = new XMem();
				
				member.setMemId(rs.getString("mem_id"));
				member.setGenreNo(rs.getInt("genre_no"));
				member.setMemNick(rs.getString("mem_nick"));
				member.setMemPw(rs.getString("mem_pw"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMailState(rs.getString("mail_state"));
				member.setMemDate(rs.getDate("mem_date"));
				
				searchMemList.add(member);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return searchMemList;
	}
	
	@Override
	public List<XMem> selectMemSearchByMemnick(Connection conn, String keyword) {
		
		String sql = "";
		sql += "SELECT * FROM XMEM"; 
		sql += " WHERE mem_nick like ?";

		List<XMem> searchMemList = new ArrayList<XMem>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				XMem member = new XMem();
				
				member.setMemId(rs.getString("mem_id"));
				member.setGenreNo(rs.getInt("genre_no"));
				member.setMemNick(rs.getString("mem_nick"));
				member.setMemPw(rs.getString("mem_pw"));
				member.setMemMail(rs.getString("mem_mail"));
				member.setMailState(rs.getString("mail_state"));
				member.setMemDate(rs.getDate("mem_date"));
				
				searchMemList.add(member);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return searchMemList;
	}

}
