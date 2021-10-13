package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.FileDao;
import dto.XFile;

public class FileDaoImpl implements FileDao {

	PreparedStatement ps = null;
	ResultSet rs = null;
	
	@Override
	public int selectNextFileno(Connection conn) {
		String sql = "SELECT XFILE_SEQ.NEXTVAL FROM DUAL";
		int nextFileno = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				nextFileno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		 
		return nextFileno;
	}

	@Override
	public int insertFile(Connection conn, XFile file) {
		
		String sql = "INSERT INTO XFILE(FILE_NO, FILE_ORIGIN_NAME, FILE_STORED_NAME, FILE_SIZE)"
				+ " VALUES(?, ?, ?, ?)";
		int res = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, file.getFileNo());
			ps.setString(2, file.getFileOriginName());
			ps.setString(3, file.getFileStoredName());
			ps.setString(4, file.getFileSize());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		return res;
	}

	
	
	@Override
	public int deleteFile(Connection conn, int fileNo) {
		
		String sql = "DELETE XFILE WHERE FILE_NO=?";
		int res = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fileNo);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
				
		return res;
	}
	
	
}
