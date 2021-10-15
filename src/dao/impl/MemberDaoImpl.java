package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dto.XMem;

public class MemberDaoImpl implements MemberDao {

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int selectCntMemByMemidMempw(Connection connection, XMem mem) {
		System.out.println("###TEST### MemberDaoImpl selectCntMemByMemidMempw()");
//		System.out.println("id : " + mem.getMem_id() +", pw : "+ mem.getMem_pw());
		
		int count = 0;	//성공 시 1로 반환될 결과 변수
		
		String sql = "SELECT COUNT(*) FROM XMEM WHERE MEM_ID=? AND MEM_PW=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, mem.getMemId());
			ps.setString(2, mem.getMemPw());
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
		
//		System.out.println("count : " +count);
		
		return count;
	}

	@Override
	public XMem selectMemByMemid(Connection connection, XMem mem) {
		System.out.println("###TEST### MemberDaoImpl selectMemByMemid()");		
		
		XMem res = null;
		
		String sql = "SELECT MEM_ID, MEM_NICK FROM XMEM WHERE MEM_ID=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, mem.getMemId());
			rs = ps.executeQuery();
			while(rs.next()) {
				res = new XMem();
				res.setMemId(rs.getString("mem_id"));
				res.setMemNick(rs.getString("mem_nick"));
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
	public int insert(Connection conn, XMem mem) {
		//쿼리작성
		String sql = "";
		sql += "INSERT INTO XMem ( mem_id, mem_pw, mem_nick, mem_mail, mail_state, genre_no )";
		sql += " VALUES( ?, ?, ?, ?, ?, ? )";
		
		int res = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, mem.getMemId());
			ps.setString(2, mem.getMemPw());
			ps.setString(3, mem.getMemNick());
			ps.setString(4, mem.getMemMail());
			ps.setString(5, mem.getMailState());
			ps.setInt(6, mem.getGenreNo());
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public String selectMemidByMemmail(Connection connection, String parameter) {

		String res = null;
	
		String sql = "SELECT MEM_ID FROM XMEM WHERE MEM_MAIL=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, parameter);
			rs = ps.executeQuery();
			while(rs.next()) {				
				res = rs.getString("mem_id");
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
	public int updateMempw(Connection connection, String mailForPw, String uuidPw) {
		
		String sql = "UPDATE XMEM SET MEM_PW=? WHERE MEM_MAIL=?";
		
		int res = -1;
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, uuidPw);
			ps.setString(2, mailForPw);
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	@Override
	public XMem selectMemByMemid(Connection connection, String memid) {
		
		String sql = "";
		sql += "SELECT * FROM XMEM";
		sql += "	WHERE MEM_ID = ? ";
			
		XMem mem = null;
			
		try {
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, memid);
			rs = ps.executeQuery();

			while (rs.next()) {
					
				mem = new XMem();
					
				mem.setMemId(rs.getString("mem_id"));
				mem.setMemPw(rs.getString("mem_pw"));
				mem.setMemNick(rs.getString("mem_nick"));
				mem.setMemMail(rs.getString("mem_mail"));
				mem.setMailState(rs.getString("mail_state"));
				mem.setGenreNo(rs.getInt("genre_no"));
				mem.setMemDate(rs.getDate("mem_date"));
				
			}
			
		} catch (SQLException e) {
	            e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
			return mem;
	}

	@Override
	public int updateMem(Connection conn, XMem mem) {

		String sql = "";
		sql += "UPDATE xmem";
		sql += " SET mem_pw = ?";
		sql += " , mem_nick = ?";
		sql += " , mem_mail = ?";
		sql += " , mail_state = ?";
		sql += " , genre_no = ?";
		sql += " WHERE mem_id = ?";
		
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, mem.getMemPw());
			ps.setString(2, mem.getMemNick());
			ps.setString(3,  mem.getMemMail());
			ps.setString(4,  mem.getMailState());
			ps.setInt(5,  mem.getGenreNo());
			ps.setString(6, mem.getMemId());
			
			System.out.println("[TEST update]\n" + mem);

			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	@Override
	public int deleteMem(Connection conn, XMem mem) {

		String sql = "";
		sql += "DELETE xmem";
		sql += " WHERE mem_id = ?";

		int res = -1;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mem.getMemId());

			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;

	}
}
