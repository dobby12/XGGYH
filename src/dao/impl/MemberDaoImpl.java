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
		System.out.println("id : " + mem.getMem_id() +", pw : "+ mem.getMem_pw());
		
		
		int count = 0;	//성공 시 1로 반환될 결과 변수
		
		String sql = "SELECT COUNT(*) FROM XMEM WHERE MEM_ID=? AND MEM_PW=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, mem.getMem_id());
			ps.setString(2, mem.getMem_pw());
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
		
		System.out.println("count : " +count);
		
		return count;
	}

	@Override
	public XMem selectMemByMemid(Connection connection, XMem mem) {
		System.out.println("###TEST### MemberDaoImpl selectMemByMemid()");		
		
		XMem res = null;
		
		String sql = "SELECT MEM_ID, MEM_NICK FROM XMEM WHERE MEM_ID=?";
		
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, mem.getMem_id());
			rs = ps.executeQuery();
			while(rs.next()) {
				res = new XMem();
				res.setMem_id(rs.getString("mem_id"));
				res.setMem_nick(rs.getString("mem_nick"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

}
